/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.module.web.sound;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.sound.SoundCaptchaService;

/**
 * Helper class
 *
 * @author Benoit Doumas
 * @version 1.0
 */
public class SoundToWavHelper {
	private static final Log log = LogFactory.getLog(SoundToWavHelper.class);

    /**
     * retrieve a new SoundCaptcha using SoundCaptchaService and flush it to the response.  Captcha are localized
     * using request locale. This method returns a 404 to the client instead of the image if the request isn't
     * correct (missing parameters, etc...).. The log may be null. 
     *
     * @param theRequest  the request
     * @param theResponse the response
     * @param service     an SoundCaptchaService instance
     * @param id the id
     * @param locale the locale
     *
     * @throws java.io.IOException if a problem occurs during the jpeg generation process
     */
    public static void flushNewCaptchaToResponse(HttpServletRequest theRequest,
                                                 HttpServletResponse theResponse, 
                                                 SoundCaptchaService service, 
                                                 String id,
                                                 Locale locale) throws IOException {

        // call the ImageCaptchaService method to retrieve a captcha
        ByteArrayOutputStream wavOutputStream = new ByteArrayOutputStream();
        try {
            AudioInputStream stream = service.getSoundChallengeForID(id, locale);

            // call the ImageCaptchaService method to retrieve a captcha

            AudioSystem.write(stream, AudioFileFormat.Type.WAVE, wavOutputStream);
            //AudioSystem.(pAudioInputStream, AudioFileFormat.Type.WAVE, pFile);

        }
        catch (IllegalArgumentException e) {
            //    log a security warning and return a 404...
            log.warn("There was a try from " + theRequest.getRemoteAddr()
                    + " to render an captcha with invalid ID :'" + id + "' or with a too long one");
            theResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        catch (CaptchaServiceException e) {
            // log and return a 404 instead of an image...
            log.warn("Error trying to generate a captcha and " + "render its challenge as WAV", e);
            theResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        byte[] captchaChallengeAsWav = wavOutputStream.toByteArray();

        // render the captcha challenge as a JPEG image in the response
        theResponse.setHeader("Cache-Control", "no-store");
        theResponse.setHeader("Pragma", "no-cache");
        theResponse.setDateHeader("Expires", 0);
        theResponse.setContentLength(captchaChallengeAsWav.length);

        theResponse.setContentType("audio/x-wav");
        ServletOutputStream responseOutputStream = theResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsWav);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

}

/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.module.web.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

import javax.imageio.ImageIO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * Helper class
 *
 * @author <a href="mailto:marc.antoine.garrigue@gmail.com">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class ImageToJpegHelper {

	 private static final Log log = LogFactory.getLog(ImageToJpegHelper.class);
	 
    /**
     * retrieve a new ImageCaptcha using ImageCaptchaService and flush it to the response. Captcha are localized
     * using request locale.
     * <p>
     * This method returns a 404 to the client instead of the image if the request isn't correct (missing parameters,
     * etc...)..<br> The log may be null.<br>
     * </p>
     *
     * @param theRequest  the request
     * @param theResponse the response
     * @param service     an ImageCaptchaService instance
     * @param id the id
     * @param locale the locale
     *
     * @throws java.io.IOException if a problem occurs during the jpeg generation process
     */
    public static void flushNewCaptchaToResponse(HttpServletRequest theRequest,
                                                 HttpServletResponse theResponse,
                                                 ImageCaptchaService service,
                                                 String id,
                                                 Locale locale, 
                                                 ImageFormat format)
            throws IOException {
    	
    	ImageFormat imageFormat = Objects.requireNonNullElse(format, ImageFormat.jpg);

        try {
        	// call the ImageCaptchaService method to retrieve a captcha
        	ByteArrayOutputStream imageOutputStream = new ByteArrayOutputStream();
            BufferedImage challenge = service.getImageChallengeForID(id, locale);
            // the output stream to render the captcha image as jpeg into
           ImageIO.write(challenge, imageFormat.name(),imageOutputStream);
           byte[] captchaChallengeAsJpeg = imageOutputStream.toByteArray();
           flushToResponse(captchaChallengeAsJpeg, theResponse, imageFormat);
        } catch (IllegalArgumentException e) {
            //    log a security warning and return a 404...
            log.warn(
                    "There was a try from "
                            + theRequest.getRemoteAddr()
                            + " to render an captcha with invalid ID :'" + id
                            + "' or with a too long one");
            theResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (CaptchaServiceException e) {
            // log and return a 404 instead of an image...
            log.warn("Error trying to generate a captcha and render its challenge as JPEG", e);
            theResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        }     
    }
    
    private static void flushToResponse(byte[] imageData, HttpServletResponse response, ImageFormat format) throws IOException {
    	
    	String contentType = "image/" + format.name();
		// Set to expire far in the past.
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		
		// return contentype
		response.setContentType(contentType);
		response.setContentLength(imageData.length);
		
		try (ServletOutputStream responseOutputStream = response.getOutputStream()){
			responseOutputStream.write(imageData);
			responseOutputStream.flush();
		}
    }
    
    public enum ImageFormat {
    	png,
    	jpg;    	
    }
}

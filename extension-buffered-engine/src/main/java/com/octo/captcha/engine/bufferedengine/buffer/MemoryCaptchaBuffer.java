/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.engine.bufferedengine.buffer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.octo.captcha.Captcha;

/**
 * Simple implmentation of a memory captcha buffer with HashedMap from commons collection.
 *
 * @author Benoit Doumas
 */
public class MemoryCaptchaBuffer implements CaptchaBuffer {

    private static final Log log = LogFactory.getLog(MemoryCaptchaBuffer.class);

    protected Map<Locale, LinkedList<Captcha>> buffers = new HashMap<>();

    /**
     * @see com.octo.captcha.engine.bufferedengine.buffer.CaptchaBuffer#removeCaptcha(java.util.Locale)
     */
    public synchronized Captcha removeCaptcha(Locale locale) throws NoSuchElementException {

        if (buffers.containsKey(locale)) {
        	logDebug("Get captcha from MemoryBuffer with locale : " + locale);
        	Captcha captcha = buffers.get(locale).remove();            
            return captcha;
        } else {
            logDebug("Locale not present : " + locale);
            return null;
        }
    }

    /**
     * @see CaptchaBuffer#removeCaptcha(int, java.util.Locale)
     */
    public synchronized Collection<Captcha> removeCaptcha(int number, Locale locale) {
        List<Captcha> list = new ArrayList<>(number);

        LinkedList<Captcha> buffer =  buffers.get(locale);
        if (buffer == null) {
            logDebug("Locale not found in Memory buffer map : " + locale.toString());
            return list;
        }

        try {
            for (int i = 0; i < number; i++) {
                list.add(buffer.remove());
            }
        }
        catch (NoSuchElementException e) {
        	// Stop retrieving captchas, used in order to use the "remove" without calling the expensive "size" method
        	logDebug("Buffer empty for locale : " + locale.toString());
        }
        
        logDebug("Removed from locale :'" + locale + "' a list of '" + list.size() + "' elements.");
        return list;
    }


	private void logDebug(String message) {
		if (log.isDebugEnabled()) {
            log.debug(message);
        }
	}

    /**
     * @see com.octo.captcha.engine.bufferedengine.buffer.CaptchaBuffer#removeCaptcha(int)
     */
    public Collection<Captcha> removeCaptcha(int number) {
        return removeCaptcha(number, Locale.getDefault());
    }

    public Captcha removeCaptcha() throws NoSuchElementException {
        return removeCaptcha(Locale.getDefault());
    }

    public synchronized void putCaptcha(Captcha captcha, Locale locale) {
        if (!buffers.containsKey(locale)) {
            buffers.put(locale, new LinkedList<>());
        }
        buffers.get(locale).add(captcha);
    }

    /**
     * @see com.octo.captcha.engine.bufferedengine.buffer.CaptchaBuffer#putAllCaptcha(java.util.Collection)
     */
    public synchronized void putAllCaptcha(Collection<Captcha> captchas, Locale locale) {
    	
        if (!buffers.containsKey(locale)) {
            buffers.put(locale, new LinkedList<>());
        }

        buffers.get(locale).addAll(captchas);

        logDebug("put into mem  : " + captchas.size() + " for locale :" + locale.toString()
                    + " with size : " + buffers.get(locale).size());
    }

    /**
     * @see com.octo.captcha.engine.bufferedengine.buffer.CaptchaBuffer#size()
     */
    public int size() {
        int total = 0;
        
        for (LinkedList<?> list: buffers.values()) {
        	total += list.size();
        }

        return total;
    }

    /**
     * @see com.octo.captcha.engine.bufferedengine.buffer.CaptchaBuffer#size()
     */
    public int size(Locale locale) {
        if (!buffers.containsKey(locale)) {
            buffers.put(locale, new LinkedList<>());
        }
        return buffers.get(locale).size();
    }

    /**
     * @see com.octo.captcha.engine.bufferedengine.buffer.CaptchaBuffer#putCaptcha(com.octo.captcha.Captcha)
     */
    public void putCaptcha(Captcha captcha) {
        putCaptcha(captcha, Locale.getDefault());
    }

    /**
     * @see com.octo.captcha.engine.bufferedengine.buffer.CaptchaBuffer#putAllCaptcha(java.util.Collection)
     */
    public void putAllCaptcha(Collection<Captcha> captchas) {
        putAllCaptcha(captchas, Locale.getDefault());
    }

    /**
     * @see com.octo.captcha.engine.bufferedengine.buffer.CaptchaBuffer#dispose()
     */
    public void dispose() {

    }

    /**
     * @see com.octo.captcha.engine.bufferedengine.buffer.CaptchaBuffer#clear()
     */
    public void clear() {
        buffers.clear();
    }

    /**
     * @see com.octo.captcha.engine.bufferedengine.buffer.CaptchaBuffer#getLocales()
     */
    public Collection<Locale> getLocales() {
        return buffers.keySet();
    }

}

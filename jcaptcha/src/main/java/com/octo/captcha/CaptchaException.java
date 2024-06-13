/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha;

/**
 * Runtime exception for the captcha implementations.
 *
 * @author gandin
 * @version $Id$
 */
public class CaptchaException extends RuntimeException {

    private static final long serialVersionUID = 5857302393426134260L;

    public CaptchaException() {
    	super();
    }


    public CaptchaException(final String message) {
        super(message);
    }


    public CaptchaException(final String message, final Throwable cause) {
        super(message, cause);
    }


    public CaptchaException(final Throwable cause) {
        super(cause);
    }
}

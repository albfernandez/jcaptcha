/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.module;

/**
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class CaptchaModuleException extends RuntimeException {

    private static final long serialVersionUID = 4917296845073722095L;

    public CaptchaModuleException() {
        super();
    }


    public CaptchaModuleException(String message) {
        super(message);
    }


    public CaptchaModuleException(Throwable cause) {
        super(cause);
    }


    public CaptchaModuleException(String message, Throwable cause) {
        super(message, cause);
    }

}

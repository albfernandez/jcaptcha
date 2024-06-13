/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.service;

/**
 * <p>To be thrown when a service user is doing an illegal operation (typically wrang operation flow errors).</p>
 *
 * @author Marc-Antoine Garrigue
 * @version $Id$
 */
public class CaptchaServiceException extends RuntimeException {

    private static final long serialVersionUID = -1578888319888540163L;


    public CaptchaServiceException(final String message) {
        super(message);
    }


    public CaptchaServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }


    public CaptchaServiceException(final Throwable cause) {
        super(cause);
    }
}

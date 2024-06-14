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
 */
public class CaptchaServiceException extends RuntimeException {

    private static final long serialVersionUID = -1578888319888540163L;

    

    /** Constructs a new CaptchaServiceException with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */

    public CaptchaServiceException() {
    	super();
    }
    
    /** Constructs a new CaptchaServiceException with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public CaptchaServiceException(final String message) {
        super(message);
    }

    /**
    * Constructs a new CaptchaServiceException with the specified detail message and
    * cause.  <p>Note that the detail message associated with
    * {@code cause} is <i>not</i> automatically incorporated in
    * this runtime exception's detail message.
    *
    * @param  message the detail message (which is saved for later retrieval
    *         by the {@link #getMessage()} method).
    * @param  cause the cause (which is saved for later retrieval by the
    *         {@link #getCause()} method).  (A {@code null} value is
    *         permitted, and indicates that the cause is nonexistent or
    *         unknown.)
    *         
    */
    public CaptchaServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    /** Constructs a new CaptchaServiceException with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of
     * {@code cause}).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */

    public CaptchaServiceException(final Throwable cause) {
        super(cause);
    }
}

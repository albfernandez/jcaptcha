/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.engine;

import java.util.Locale;

import com.octo.captcha.CaptchaFactory;

/**
 * <p>CaptchaEngine is the main interface from an application point of view : it uses CapchaFactories in order to return
 * new captchas.</p> It has thes reponsibilty of creating CatpchaFactory instances and to provide an easy way to
 * retrieve captchas. Sub classes may implement container services around capcthas as, for example, Captchas pooling,
 * serialization, and so.
 *
 * @author Marc-Antoine Garrigue
 */
public interface CaptchaEngine {

    /**
     * This return a new captcha. It may be used directly.
     *
     * @return a new Captcha
     */
    com.octo.captcha.Captcha getNextCaptcha();

    /**
     * This return a new captcha. It may be used directly.
     *
     * @param locale the desired locale
     *
     * @return a new Captcha
     */
    com.octo.captcha.Captcha getNextCaptcha(Locale locale);


    /**
     * Get captcha factories used by this engines
     * 
     * @return captcha factories used by this engine
     */
    CaptchaFactory[] getFactories();

    /**
     * Set captcha factories for this engine
     * 
     * @param factories new captcha factories for this engine
     *
     * @throws CaptchaEngineException if the factories are invalid for this engine
     */
    void setFactories(CaptchaFactory[] factories) throws CaptchaEngineException;
}

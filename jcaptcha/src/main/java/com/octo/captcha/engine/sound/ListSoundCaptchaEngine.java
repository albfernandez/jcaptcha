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

package com.octo.captcha.engine.sound;

import java.util.Arrays;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.sound.SoundCaptchaFactory;

/**
 * This engine is based on a java.util.List of factories. It has a default constructor. Sub class must implements the
 * buildInitialFactories() method that should build an initial set of factories. 
 *
 * @author Benoit Doumas
 * @version 1.0
 */
public abstract class ListSoundCaptchaEngine extends SoundCaptchaEngine {

    public ListSoundCaptchaEngine() {
        buildInitialFactories();
        checkFactoriesSize();
    }

    /**
     * this method should be implemented as folow : <ul> <li>First construct all the factories you want to initialize
     * the gimpy with</li> <li>then call the this.addFactoriy method for each factory</li> </ul>
     */
    protected abstract void buildInitialFactories();

    /**
     * Add a factory to the gimpy list
     * @param factory factory
     * @return true if added false otherwise
     */
    public boolean addFactory(SoundCaptchaFactory factory) {
        return factory != null && this.factories.add(factory);
    }

    /**
     * Add an array of factories to the gimpy list
     * @param factories factories
     */
    public void addFactories(SoundCaptchaFactory[] factories) {
        checkNotNullOrEmpty(factories);
        this.factories.addAll(Arrays.asList(factories));
    }

    private void checkFactoriesSize() {
        if (factories.size() == 0)
            throw new CaptchaException("This soundEngine has no factories. Please initialize it "
                    + "properly with the buildInitialFactory() called by "
                    + "the constructor or the addFactory() mehtod later!");
    }

}
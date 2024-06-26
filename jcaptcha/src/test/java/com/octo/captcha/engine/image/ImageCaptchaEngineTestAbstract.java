/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.engine.image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.engine.CaptchaEngineException;
import com.octo.captcha.engine.MockImageCaptchaFactory;
import com.octo.captcha.image.ImageCaptchaFactory;


public abstract class ImageCaptchaEngineTestAbstract  {

    private static final MockImageCaptchaFactory MOCK_IMAGE_CAPTCHA_FACTORY_1 = new MockImageCaptchaFactory();
    private static final MockImageCaptchaFactory MOCK_IMAGE_CAPTCHA_FACTORY_2 = new MockImageCaptchaFactory();

    ImageCaptchaEngine defaultImageCaptchaEngine;
    private ImageCaptchaFactory[] factories = new ImageCaptchaFactory[]{MOCK_IMAGE_CAPTCHA_FACTORY_1};
    private ImageCaptchaFactory[] otherFactories = new ImageCaptchaFactory[]{MOCK_IMAGE_CAPTCHA_FACTORY_2};

    public void testNullOrEmptyFactoryImageCaptchaEngineConstructor() throws Exception {
        //
        try {
            buildCaptchaEngine(null);
            fail("Cannot build with null factories");
        } catch (CaptchaEngineException e) {
        	assertNotNull(e.getMessage());
        }

        try {
            buildCaptchaEngine(new ImageCaptchaFactory[]{});
            fail("Cannot build with null factories");
        } catch (CaptchaEngineException e) {
        	assertNotNull(e.getMessage());
        }

    }


    public void testNullOrEmptySetFactories() throws Exception {
        this.defaultImageCaptchaEngine = (ImageCaptchaEngine) buildCaptchaEngine(new ImageCaptchaFactory[]{MOCK_IMAGE_CAPTCHA_FACTORY_1});

        try {
            defaultImageCaptchaEngine.setFactories(null);
            fail("cannot set null factories");
        } catch (CaptchaEngineException e) {
        	assertNotNull(e.getMessage());
        }

        try {
            defaultImageCaptchaEngine.setFactories(new ImageCaptchaFactory[]{});
            fail("cannot set null factories");
        } catch (CaptchaEngineException e) {
        	assertNotNull(e.getMessage());
        }
    }




    public void testSetFactories() throws Exception {
        this.defaultImageCaptchaEngine = (ImageCaptchaEngine) buildCaptchaEngine(new MockImageCaptchaFactory[]{MOCK_IMAGE_CAPTCHA_FACTORY_1});
        assertEquals(factories[0], defaultImageCaptchaEngine.getFactories()[0]);

        defaultImageCaptchaEngine.setFactories(otherFactories);
        assertEquals(otherFactories[0], defaultImageCaptchaEngine.getFactories()[0]);
    }

    abstract CaptchaEngine buildCaptchaEngine(Object[] parameter);
}
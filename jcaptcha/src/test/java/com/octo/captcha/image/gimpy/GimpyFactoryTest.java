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

package com.octo.captcha.image.gimpy;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.image.wordtoimage.SimpleWordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;

public class GimpyFactoryTest {

    GimpyFactory tested;

    @BeforeEach
    protected void setUp() throws Exception {
        tested = new GimpyFactory(new RandomWordGenerator("a"), new SimpleWordToImage());
    }

    @Test
    public void testGetRandomLength() throws Exception {
        //be carefull values tide to SimpleWordToImage.
        for (int i = 1; i < 11; i++) {
            int j;
            do {

                j = tested.getRandomLength().intValue();
                if (j < 1 || j > 10) {
                    fail("Out of authorized range!");
                }

            } while (j != i);
        }
    }

    @Test
    public void testGimpyFactory() throws Exception {
        try {
            new GimpyFactory(null, null);
            fail("Test is not implemented");
        } catch (CaptchaException e) {
            assertNotNull(e.getMessage());
        }
        try {
            new GimpyFactory(new RandomWordGenerator("a"), null);
            fail("Test is not implemented");
        } catch (CaptchaException e) {
            assertNotNull(e.getMessage());
        }

        try {
            new GimpyFactory(null, new SimpleWordToImage());
            fail("Test is not implemented");
        } catch (CaptchaException e) {
            assertNotNull(e.getMessage());
        }
    }

}

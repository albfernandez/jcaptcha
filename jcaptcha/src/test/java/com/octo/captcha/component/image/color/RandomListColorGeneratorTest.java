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
package com.octo.captcha.component.image.color;


import java.awt.Color;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.octo.captcha.CaptchaException;

/**
 * @author Benoit
 *         
 */
public class RandomListColorGeneratorTest {
    private RandomListColorGenerator colorGenerator = null;

    @Test
    public void testNominalCase() {
        Color[] colorList = new Color[2];

        colorList[0] = new Color(1, 1, 1, 1);
        colorList[1] = new Color(2, 2, 2, 2);

        colorGenerator = new RandomListColorGenerator(colorList);

        // due to the random factor, test is repeated several times
        for (int i = 0; i < 100; i++) {
            Color color = colorGenerator.getNextColor();

            Assertions.assertTrue((color.getRed() == 1 && color.getGreen() == 1 && color.getBlue() == 1 && color
                    .getAlpha() == 1)
                    || (color.getRed() == 2 && color.getGreen() == 2 && color.getBlue() == 2 && color
                    .getAlpha() == 2));
        }
    }

    @Test
    public void testNullColor() {
        Color[] colorList = new Color[2];

        colorList[0] = new Color(1, 1, 1, 1);
        colorList[1] = null;

        try {
            colorGenerator = new RandomListColorGenerator(colorList);
            Assertions.fail();
        } catch (CaptchaException e) {
        	Assertions.assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testSingleColor() {
        Color[] colorList = new Color[1];

        colorList[0] = new Color(1, 1, 1, 1);

        for (int i = 0; i < 100; i++) {
            colorGenerator = new RandomListColorGenerator(colorList);
            Color color = colorGenerator.getNextColor();

            Assertions.assertTrue((color.getRed() == 1 && color.getGreen() == 1 && color.getBlue() == 1 && color
                    .getAlpha() == 1));
        }
    }
}

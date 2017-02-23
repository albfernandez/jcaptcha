/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.component.image.fontgenerator;

import java.awt.Font;
import java.awt.geom.AffineTransform;

/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class TwistedRandomFontGenerator extends RandomFontGenerator {

    public TwistedRandomFontGenerator(Integer minFontSize, Integer maxFontSize) {
        super(minFontSize, maxFontSize);
    }


    /**
     * Provides a way for children class to customize the generated font array
     *
     * @param font font
     * @return a customized font
     */
    protected Font applyCustomDeformationOnGeneratedFont(Font font) {
        AffineTransform at = new AffineTransform();
        float angle = myRandom.nextFloat() / 3;
        at.rotate(myRandom.nextBoolean() ? angle : -angle);
        return font.deriveFont(at);
    }
}

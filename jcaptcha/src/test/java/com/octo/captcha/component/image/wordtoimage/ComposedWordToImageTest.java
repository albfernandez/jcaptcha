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


package com.octo.captcha.component.image.wordtoimage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.GradientBackgroundGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.GlyphsPaster;

/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @version 1.0
 */
public class ComposedWordToImageTest  {

    private ComposedWordToImage composedWordToImage;
    private Integer minAcceptedWordLength = Integer.valueOf(1);
    private Integer maxAcceptedWordLength = Integer.valueOf(10);
    private Integer imageHeight = Integer.valueOf(100);
    private Integer imageWidth = Integer.valueOf(100);
    private Integer minFontSize = Integer.valueOf(10);
    private Integer maxFontSize = Integer.valueOf(12);

    /**
     * Constructor for ComposedWordToImageTest.
     */
    public ComposedWordToImageTest() {
        super();
    }

    @BeforeEach
    public void setUp() {
        BackgroundGenerator background = new GradientBackgroundGenerator(this.imageWidth, this.imageHeight, Color.black, Color.white);
        FontGenerator fontGenerator = new RandomFontGenerator(this.minFontSize, this.maxFontSize);
        GlyphsPaster textPaster = new GlyphsPaster(this.minAcceptedWordLength, this.maxAcceptedWordLength, Color.blue);
        this.composedWordToImage = new ComposedWordToImage(fontGenerator, background, textPaster);
    }

    @Test
    public void testGetFont() {
        Font test = this.composedWordToImage.getFont();
        assertNotNull(test);
    }
    @Test
    public void testGetBackround() {
        BufferedImage test = this.composedWordToImage.getBackground();
        assertNotNull(test);
    }
    @Test
    public void testGetMaxAcceptedWordLength() {
        int expected = this.maxAcceptedWordLength.intValue();
        int test = this.composedWordToImage.getMaxAcceptedWordLength();
        assertEquals(expected, test);
    }
    @Test
    public void testGetMinAcceptedWordLength() {
        int expected = this.minAcceptedWordLength.intValue();
        int test = this.composedWordToImage.getMinAcceptedWordLength();
        assertEquals(expected, test);
    }
    @Test
    public void testGetImageHeight() {
        int expected = this.imageHeight.intValue();
        int test = this.composedWordToImage.getImageHeight();
        assertEquals(expected, test);
    }
    @Test
    public void testGetImageWidth() {
        int expected = this.imageWidth.intValue();
        int test = this.composedWordToImage.getImageWidth();
        assertEquals(expected, test);
    }
    @Test
    public void testGetMinFontSize() {
        int expected = this.minFontSize.intValue();
        int test = this.composedWordToImage.getMinFontSize();
        assertEquals(expected, test);
    }

}

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

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @version 1.0
 */
public class SimpleWordToImageTest {

    private SimpleWordToImage simpleWordToImage;

    /**
     * Constructor for SimpleWordToImageTest.
     */
    public SimpleWordToImageTest() {
        super();
    }

    @BeforeEach
    public void setUp() {
        this.simpleWordToImage = new SimpleWordToImage();
    }

    @Test
    public void testGetFont() {
        Font test = this.simpleWordToImage.getFont();
        Font expected = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()[0];
        assertNotNull(test);
        assertEquals(expected, test);
    }

    @Test
    public void testGetBackround() {
        BufferedImage test = this.simpleWordToImage.getBackground();
        assertNotNull(test);
    }

    @Test
     public void testGetMaxAcceptedWordLength() {
        int test = this.simpleWordToImage.getMaxAcceptedWordLength();
        int expected = 10;
        assertEquals(expected, test);
    }

    @Test
    public void testGetMinAcceptedWordLength() {
        int test = this.simpleWordToImage.getMinAcceptedWordLength();
        int expected = 1;
        assertEquals(expected, test);
    }

    @Test
    public void testGetImageHeight() {
        int test = this.simpleWordToImage.getImageHeight();
        int expected = 50;
        assertEquals(expected, test);
    }

    @Test
    public void testGetImageWidth() {
        int test = this.simpleWordToImage.getImageWidth();
        int expected = 100;
        assertEquals(expected, test);
    }

    @Test
    public void testGetMinFontSize() {
        int test = this.simpleWordToImage.getMinFontSize();
        int expected = 10;
        assertEquals(expected, test);
    }

}

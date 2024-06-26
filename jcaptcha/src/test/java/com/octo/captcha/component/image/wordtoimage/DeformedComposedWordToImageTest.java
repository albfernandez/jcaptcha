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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;
import java.awt.image.ImageFilter;
import java.awt.image.ReplicateScaleFilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.GradientBackgroundGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.GlyphsPaster;

/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @version 1.0
 */

public class DeformedComposedWordToImageTest {

    private DeformedComposedWordToImage deformedComposedWordToImage;
    private Integer minAcceptedWordLength = Integer.valueOf(1);
    private Integer maxAcceptedWordLength = Integer.valueOf(10);
    private Integer imageHeight = Integer.valueOf(100);
    private Integer imageWidth = Integer.valueOf(100);
    private Integer minFontSize = Integer.valueOf(10);
    private Integer maxFontSize = Integer.valueOf(10);


    @BeforeEach
    public void setUp() {

        BackgroundGenerator background = new GradientBackgroundGenerator(this.imageHeight, this.imageWidth, Color.black, Color.white);
        FontGenerator fontGenerator = new RandomFontGenerator(this.minFontSize, this.maxFontSize);
        GlyphsPaster textPaster = new GlyphsPaster(this.minAcceptedWordLength, this.maxAcceptedWordLength, Color.blue);

        ImageFilter backFilter = new ReplicateScaleFilter(background.getImageWidth(),
                background.getImageHeight());

        ImageFilter textFilter = new ReplicateScaleFilter(background.getImageWidth(),
                background.getImageHeight());

        ImageFilter finalFilter = new ReplicateScaleFilter(background.getImageWidth(),
                background.getImageHeight());

        ImageFilter[] backFilters = {backFilter};

        ImageFilter[] textFilters = {textFilter};

        ImageFilter[] finalFilters = {finalFilter};

        ImageDeformation back = new ImageDeformationByFilters(backFilters);
        ImageDeformation text = new ImageDeformationByFilters(textFilters);
        ImageDeformation finalD = new ImageDeformationByFilters(finalFilters);
        this.deformedComposedWordToImage = new DeformedComposedWordToImage(fontGenerator,
                background,
                textPaster,
                back,
                text,
                finalD);
    }

    @Test
    public void testGetImage() throws CaptchaException {
        String test = "test";
        assertNotNull(this.deformedComposedWordToImage.getImage(test));
    }
    
    @Test
    public void testGetImageNull() {
        try {
            this.deformedComposedWordToImage.getImage(null);
            fail();
        } catch (CaptchaException e) {
            assertNotNull(e);
        }
    }

}

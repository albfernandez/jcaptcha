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

package com.octo.captcha.image.fisheye;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;
import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;

public class FishEyeFactoryTest  {
    FishEyeFactory fishEyeFactory;


    @BeforeEach
    protected void setUp() throws Exception {
        this.fishEyeFactory = new FishEyeFactory(new UniColorBackgroundGenerator(Integer.valueOf(300), Integer.valueOf(300),
                Color.black), new ImageDeformationByFilters(null), Integer.valueOf(10), Integer.valueOf(0));
    }

    @Test
    public void testGetImageCaptcha() throws Exception {
        for (int i = 0; i < 10; i++) {
            assertTrue(fishEyeFactory.getImageCaptcha().getChallenge() != null, "should be not null");
        }
        
        try {
            this.fishEyeFactory = new FishEyeFactory(new UniColorBackgroundGenerator(Integer.valueOf(10), Integer.valueOf(10),
                    Color.black), new ImageDeformationByFilters(null), Integer.valueOf(100), Integer.valueOf(100));
            fail("should not be able to construct");
        } catch (Exception e) {
        	assertNotNull(e.getMessage());
        }
        
        this.fishEyeFactory = new FishEyeFactory(new UniColorBackgroundGenerator(Integer.valueOf(10), Integer.valueOf(10),
                Color.black), new ImageDeformationByFilters(null), Integer.valueOf(1), Integer.valueOf(10));
        for (int i = 0; i < 10; i++) {
            assertTrue(fishEyeFactory.getImageCaptcha().validateResponse(new Point(5, 5)).booleanValue(), "should be never fail");
        }
    }
}
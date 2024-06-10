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


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.awt.Point;

import org.junit.jupiter.api.Test;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;

public class FishEyeTest {

    @Test
    public void testValidateResponse() throws Exception {
        BackgroundGenerator back = new UniColorBackgroundGenerator(Integer.valueOf(300), Integer.valueOf(300),
                Color.black);

    	FishEye fishEye = new FishEye("question", back.getBackground(), new Point(10, 10), Integer.valueOf(0));
        assertTrue(fishEye.validateResponse("10,10").booleanValue(), "string answer");
        assertTrue(fishEye.validateResponse(new Point(10, 10)).booleanValue(), "point answer");
        assertFalse(fishEye.validateResponse(new Point(11, 10)).booleanValue(), "invalid point answer");
        assertFalse(fishEye.validateResponse("toto,10").booleanValue(), "invalid string");
        assertFalse(fishEye.validateResponse(",10").booleanValue(), "invalid string");
        assertFalse(fishEye.validateResponse("10,").booleanValue(), "invalid string");
        assertFalse(fishEye.validateResponse("10;10").booleanValue(), "invalid string");
    }

    @Test
    public void testValidateResponseTolerance() throws Exception {
        BackgroundGenerator back = new UniColorBackgroundGenerator(Integer.valueOf(300), Integer.valueOf(300),
                Color.black);

    	FishEye fishEye = new FishEye("question", back.getBackground(), new Point(10, 10), Integer.valueOf(1));
        assertTrue(fishEye.validateResponse("10,11").booleanValue(), "string answer");
        assertTrue(fishEye.validateResponse("11,10").booleanValue(), "string answer");
        assertTrue(fishEye.validateResponse(new Point(11, 10)).booleanValue(), "point answer");
        assertTrue(fishEye.validateResponse(new Point(10, 11)).booleanValue(),"point answer");
        assertFalse(fishEye.validateResponse(new Point(11, 11)).booleanValue(), "invalid point answer");

    }


}
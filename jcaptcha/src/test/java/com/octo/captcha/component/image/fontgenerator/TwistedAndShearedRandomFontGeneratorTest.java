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


package com.octo.captcha.component.image.fontgenerator;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Font;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @version 1.0
 */
public class TwistedAndShearedRandomFontGeneratorTest  {

    private TwistedAndShearedRandomFontGenerator
            twistedAndShearedRandomFontGenerator;

    /**
     * Constructor for TwistedAndShearedRandomFontGeneratorTest.
     */
    public TwistedAndShearedRandomFontGeneratorTest() {
        super();
    }

    @BeforeEach
    public void setUp() {
        this.twistedAndShearedRandomFontGenerator =
                new TwistedAndShearedRandomFontGenerator(Integer.valueOf(10), null);
    }

    @Test
    public void testGetFont() {
        Font test = this.twistedAndShearedRandomFontGenerator.getFont();
        assertNotNull(test);
    }


    public void testGet10000Font() {
        for (int i = 0; i < 10000; i++) {
            Font test = this.twistedAndShearedRandomFontGenerator.getFont();
            assertNotNull(test);
        }

    }

}

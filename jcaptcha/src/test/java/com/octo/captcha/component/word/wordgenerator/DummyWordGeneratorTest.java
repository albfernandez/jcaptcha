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

package com.octo.captcha.component.word.wordgenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @version 1.0
 */
public class DummyWordGeneratorTest  {

    private DummyWordGenerator dummyWordGenerator;
    private String expectedString = "JCAPTCHA";

    @BeforeEach
    public void setUp() {
        this.dummyWordGenerator = new DummyWordGenerator(this.expectedString);
    }

    @Test
    public void testGetDefaultWord() {
        this.dummyWordGenerator = new DummyWordGenerator(null);
        String expected = this.expectedString;
        String word = this.dummyWordGenerator.getWord(Integer.valueOf(8));
        assertEquals(expected, word);
    }

    @Test
    public void testGetWordInteger() {
        String expected = this.expectedString;
        String word = this.dummyWordGenerator.getWord(Integer.valueOf(10));
        assertEquals(expected + expected.substring(0, 2), word);
    }

    @Test
    public void testGetWordIntegerLocale() {
        String expected = this.expectedString;
        String word = this.dummyWordGenerator.getWord(Integer.valueOf(10), Locale.US);
        assertEquals(expected + expected.substring(0, 2), word);
    }

}

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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @version 1.0
 */
public class RandomWordGeneratorTest {

    private RandomWordGenerator randomWordGenerator;

    /**
     * Constructor for RandomWordGeneratorTest.
     */
    public RandomWordGeneratorTest() {
        super();
    }

    @BeforeEach
    public void setUp() {
        this.randomWordGenerator = new RandomWordGenerator("azertyuiopqsdfghjklmwxcvbn");
    }

    @Test
    public void testGetWord() {
        Integer wordLength = Integer.valueOf(10);
        String pickWord = this.randomWordGenerator.getWord(wordLength, Locale.US);
        assertNotNull(pickWord);
        assertTrue(pickWord.length() > 0);
        assertEquals(wordLength.intValue(), pickWord.length());
    }

}

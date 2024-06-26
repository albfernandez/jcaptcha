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
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.word.ArrayDictionary;

/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @version 1.0
 */
public class DictionaryWordGeneratorTest { 

    private DictionaryWordGenerator dictionaryWordGenerator;
    private static String[] wordlist = {"1", "1234", "123456", "123456789", "123"};
    private static int[] lengths = {1, 4, 6, 9, 3};
    private static Integer UNKNOWN_LENGTH = Integer.valueOf(100);

    @BeforeEach
    public void setUp() {
        this.dictionaryWordGenerator = new DictionaryWordGenerator(new ArrayDictionary(wordlist));
    }

    @Test
    public void testGetWordInteger() {
        for (int i = 0; i < lengths.length; i++) {
            Integer length = Integer.valueOf(lengths[i]);
            String test = this.dictionaryWordGenerator.getWord(length);
            assertNotNull(test);
            assertTrue(test.length() > 0);
            assertEquals(length.intValue(), test.length());

        }
        try {
            this.dictionaryWordGenerator.getWord(UNKNOWN_LENGTH);
            fail("Should throw a CaptchaException");
        } catch (CaptchaException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testGetWordIntegerLocale() {
        for (int i = 0; i < lengths.length; i++) {
            Integer length = Integer.valueOf(lengths[i]);
            String test = this.dictionaryWordGenerator.getWord(length, Locale.US);
            assertNotNull(test);
            assertTrue(test.length() > 0);
            assertEquals(length.intValue(), test.length());
        }
        try {
            this.dictionaryWordGenerator.getWord(UNKNOWN_LENGTH);
            fail("Should throw a CaptchaException");
        } catch (CaptchaException e) {
            assertNotNull(e.getMessage());
        }
    }


}

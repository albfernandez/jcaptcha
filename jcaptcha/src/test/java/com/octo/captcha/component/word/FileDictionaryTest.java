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


package com.octo.captcha.component.word;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @version 1.0
 */
public class FileDictionaryTest {

    private FileDictionary fileDictionary;

    /**
     * Constructor for FileDictionaryTest.
     */
    public FileDictionaryTest() {
        super();
    }

    @BeforeEach
    public void setUp() {
        this.fileDictionary = new FileDictionary("toddlist");
    }

    @Test
    @Disabled("temporal, fails to load bundles in test")
    public void testGetWordList() {
        SizeSortedWordList test = this.fileDictionary.getWordList();
        assertNotNull(test);
        String testWord = test.getNextWord(Integer.valueOf(8));
        assertNotNull(testWord);
        assertEquals(8, testWord.length());


    }

    @Test
    public void testGetWordListLocale() {
        SizeSortedWordList test = this.fileDictionary.getWordList(Locale.US);
        Locale expected = Locale.US;
        assertNotNull(test);
        String testWord = test.getNextWord(Integer.valueOf(8));
        assertNotNull(testWord);
        assertEquals(8, testWord.length());
        assertEquals(expected, test.getLocale());
    }

}

/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.component.word;

import java.util.Locale;

/**
 *
 * @author <a href="mailto:marc.antoine.garrigue@gmail.com">Marc-Antoine Garrigue</a>
 */
public interface SizeSortedWordList {
    Locale getLocale();

    void addWord(String word);

    Integer getMinWord();

    Integer getMaxWord();

    String getNextWord(Integer length);
}

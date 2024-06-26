/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.image.gimpy;

import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.helper.CaptchaQuestionHelper;
import com.octo.captcha.image.ImageCaptcha;

/**
 * Factories for Gimpies. Built on top of WordGenerator and WordToImage. It uses thoses interfaces to build an
 * ImageCaptha answered by a String and for which the question is : Spell the word.
 */
public class GimpyFactory extends com.octo.captcha.image.ImageCaptchaFactory {

    private Random myRandom = new SecureRandom();
    private WordToImage wordToImage;
    private WordGenerator wordGenerator;
    private boolean caseSensitive=true;

    public static final String BUNDLE_QUESTION_KEY = Gimpy.class.getName();

    public GimpyFactory(WordGenerator generator, WordToImage word2image){
        this(generator, word2image,true);
    }

    public GimpyFactory(WordGenerator generator, WordToImage word2image, boolean caseSensitive) {
        if (word2image == null) {
            throw new CaptchaException("Invalid configuration" +
                    " for a GimpyFactory : WordToImage can't be null");
        }
        if (generator == null) {
            throw new CaptchaException("Invalid configuration" +
                    " for a GimpyFactory : WordGenerator can't be null");
        }
        wordToImage = word2image;
        wordGenerator = generator;
        this.caseSensitive=caseSensitive;

    }

    /**
     * gimpies are ImageCaptcha
     *
     * @return the image captcha with default locale
     */
    public ImageCaptcha getImageCaptcha() {
        return getImageCaptcha(Locale.getDefault());
    }

    public WordToImage getWordToImage() {
        return wordToImage;
    }

    public WordGenerator getWordGenerator() {
        return wordGenerator;
    }

    /**
     * gimpies are ImageCaptcha
     *
     * @return a pixCaptcha with the question :"spell the word"
     */
    public ImageCaptcha getImageCaptcha(Locale locale) {

        //length
        Integer wordLength = getRandomLength();

        String word = getWordGenerator().getWord(wordLength, locale);

        BufferedImage image = null;
        try {
            image = getWordToImage().getImage(word);
        } catch (Throwable e) {
            throw new CaptchaException(e);
        }

        ImageCaptcha captcha = new Gimpy(CaptchaQuestionHelper.getQuestion(locale, BUNDLE_QUESTION_KEY),
                image, word, caseSensitive);
        return captcha;
    }

    protected Integer getRandomLength() {
        Integer wordLength;
        int range = getWordToImage().getMaxAcceptedWordLength() -
                getWordToImage().getMinAcceptedWordLength();
        int randomRange = range != 0 ? myRandom.nextInt(range + 1) : 0;
        wordLength = Integer.valueOf(randomRange + getWordToImage().getMinAcceptedWordLength());
        return wordLength;
    }

}

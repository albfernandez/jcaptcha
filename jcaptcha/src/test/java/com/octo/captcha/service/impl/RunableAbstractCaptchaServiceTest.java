/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

import com.octo.captcha.MockCaptcha;
import com.octo.captcha.engine.MockCaptchaEngine;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.MockCaptchaStore;
import com.octo.captcha.service.captchastore.impl.MapCaptchaStore;


public class RunableAbstractCaptchaServiceTest  {

    protected Random myRandom = new SecureRandom();

    protected AbstractCaptchaService service = new MockedCaptchaService(new MapCaptchaStore(), new MockCaptchaEngine());

    public static final int SIZE = 1000;

    public void testAbstractCaptchaService() throws Exception {
        try {
            new MockedCaptchaService(null, new MockCaptchaEngine());
            fail("should have thrown an exception");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException, "IllegalArgumentException attended");
        }

        try {
            new MockedCaptchaService(new MapCaptchaStore(), null);
            fail("should have thrown an exception");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException, "IllegalArgumentException attended");
        }
    }

    public void testGetChallengeForID() throws Exception {

        for (int i = 0; i < SIZE; i++) {
            String id = String.valueOf(myRandom.nextInt());
            assertEquals(MockCaptcha.CHALLENGE + MockedCaptchaService.CLONE_CHALLENGE, service.getChallengeForID(id),
            		"Should always return a cloned challenge");
            assertEquals(MockCaptcha.CHALLENGE + MockedCaptchaService.CLONE_CHALLENGE, service.getChallengeForID(id),
            		"Should always return a cloned challenge");
        }

    }

    public void testGetQuestionForID() throws Exception {
        for (int i = 0; i < SIZE; i++) {
            String id = String.valueOf(myRandom.nextInt());
            assertEquals(MockCaptcha.QUESTION + Locale.getDefault(), service.getQuestionForID(id),
                    "Should always return The mock question and default locale");
            assertEquals(MockCaptcha.QUESTION + Locale.CHINESE, service.getQuestionForID(id, Locale.CHINESE),
            		"Should always return The mock question and specified locale");
        }

    }

    public void testValidateResponseForID() throws Exception {

        for (int i = 0; i < SIZE; i++) {
            String id = String.valueOf(myRandom.nextInt());
            try {
                service.validateResponseForID(id, "true");
                fail("The tiket is invalid, should throw an exception");
            } catch (CaptchaServiceException e) {
            	assertNotNull(e.getMessage());
            }
            //Should be ok after new question
            service.getQuestionForID(id);
            assertTrue(service.validateResponseForID(id, "true").booleanValue(), "Should be ok");

            //Should be ok after new challenge
            service.getChallengeForID(id);
            assertTrue(service.validateResponseForID(id, "true").booleanValue(), "Should be ok");

            //Should be ok after new challenge and question
            service.getChallengeForID(id);
            service.getQuestionForID(id);
            assertTrue(service.validateResponseForID(id, "true").booleanValue(), "Should be ok");

            //Should be ok after new question and challenge
            service.getChallengeForID(id);
            service.getQuestionForID(id);
            assertTrue(service.validateResponseForID(id, "true").booleanValue(), "Should be ok");
        }
    }

    public void testGenerateAndStoreCaptcha() throws Exception {
        for (int i = 0; i < SIZE; i++) {
            String id = String.valueOf(myRandom.nextInt());
            service.generateAndStoreCaptcha(Locale.getDefault(), id);
            assertTrue(service.validateResponseForID(id, "true").booleanValue(), "Should be ok");

        }
    }


    public void testCaptchaRegenerationWhenNewLocaleIsAsked() throws Exception {
        String french = service.getQuestionForID("1", Locale.FRENCH);
        String english = service.getQuestionForID("1", Locale.ENGLISH);
        assertFalse(french.equals(english));

    }
    
    public void testInitStore() {
    	MockCaptchaStore store = new MockCaptchaStore();
    	new MockedCaptchaService(store, new MockCaptchaEngine());
    	assertTrue(store.isInitCalled());
    }


}

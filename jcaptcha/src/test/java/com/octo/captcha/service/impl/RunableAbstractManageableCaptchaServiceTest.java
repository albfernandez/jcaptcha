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

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.octo.captcha.Captcha;
import com.octo.captcha.engine.MockCaptchaEngine;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.captchastore.impl.MapCaptchaStore;

@Disabled("slow")
public class RunableAbstractManageableCaptchaServiceTest extends RunableAbstractCaptchaServiceTest {
    public static int MIN_GUARANTED_STORAGE_DELAY_IN_SECONDS = 1;
    public static int CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION = 2 * SIZE;
    public static int MAX_CAPTCHA_STORE_SIZE = 3 * SIZE;


    @BeforeEach
    protected void setUp() throws Exception {
        //redefines service
        this.service = new MockedManageableCaptchaService(new MapCaptchaStore(), new MockCaptchaEngine(),
                MIN_GUARANTED_STORAGE_DELAY_IN_SECONDS,
                MAX_CAPTCHA_STORE_SIZE, CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION);
    }

    //down casts
    protected ManageableCaptchaService getMService() {
        return (ManageableCaptchaService) service;
    }

    @Test
    public void testIsCaptchaStoreFull() {
        
    	MockedManageableCaptchaService mockedService = new MockedManageableCaptchaService(
                new MapCaptchaStore(), new MockCaptchaEngine(),
                MIN_GUARANTED_STORAGE_DELAY_IN_SECONDS, 0, 0);

        assertFalse(mockedService.isCaptchaStoreFull());
    }

    @Test
    public void testAbstractManageableCaptchaService() throws Exception {
        try {
            new MockedManageableCaptchaService(new MapCaptchaStore(), new MockCaptchaEngine(), 0, 10, 100);
            fail("should have thrown an exception");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException, "IllegalArgumentException attended");
        }
    }
    @Test
    public void testGetCaptchaEngineClass() throws Exception {
        assertEquals(MockCaptchaEngine.class.getName(),
                getMService().getCaptchaEngineClass(), "Should be the mockEngine...");
    }
    
    @Test
    public void testSetCaptchaEngineClass() throws Exception {
        try {
            getMService().setCaptchaEngineClass("unknown");
            fail("should have thown an exception");
        } catch (IllegalArgumentException e) {
            assertEquals(
                    MockCaptchaEngine.class.getName(),
                    getMService().getCaptchaEngineClass(),"Should still be the mockEngine...");
        }
        
        try {
            getMService().setCaptchaEngineClass("java.lang.String");
            fail("should have thown an exception");
        } catch (IllegalArgumentException e) {
            assertEquals(
                    MockCaptchaEngine.class.getName(),
                    getMService().getCaptchaEngineClass(),"Should still be the mockEngine...");
        }
        
        assertEquals(MockCaptchaEngine.class.getName(),
                getMService().getCaptchaEngineClass(), "Should still be the mockEngine...");

        try {
            getMService().setCaptchaEngineClass(SecondMockCaptchaEngine.class.getName());
            assertEquals(SecondMockCaptchaEngine.class.getName(),
                    getMService().getCaptchaEngineClass(), "Should be the SecondmockEngine...");
        } catch (IllegalArgumentException e) {
            fail("should be ok " + e, e);
        }
    }
    @Test
    public void testEmptyCaptchaStore() throws Exception {
        for (int i = 0; i < SIZE; i++) {
            String id = String.valueOf(myRandom.nextInt());
            service.generateAndStoreCaptcha(Locale.getDefault(), id);
        }
        getMService().emptyCaptchaStore();
        assertTrue(getMService().getCaptchaStoreSize() == 0, "it shoud be emtpy");
    }
    @Test
    public void testGarbageCollectCaptchaStore() throws Exception {
        for (int i = 0; i < CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION; i++) {
            String id = String.valueOf(myRandom.nextInt());
            service.generateAndStoreCaptcha(Locale.getDefault(), id);
        }

        getMService().garbageCollectCaptchaStore();
        assertTrue(
                getMService().getCaptchaStoreSize() == CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION,
                "store size should be the same(this test may fail if time to load the store is > min guaranted...)");
        //wait,  and collect
        Thread.sleep((long) MIN_GUARANTED_STORAGE_DELAY_IN_SECONDS * 1000L + 100L);
        getMService().garbageCollectCaptchaStore();
        assertTrue(getMService().getCaptchaStoreSize() == 0, "store should be empty");
    }
    @Test
    public void testGetCaptchaStoreMaxSize() throws Exception {
        assertEquals(MAX_CAPTCHA_STORE_SIZE, getMService().getCaptchaStoreMaxSize(), "initial size");
    }
    @Test
    public void testSetCaptchaStoreMaxSize() throws Exception {
        try {
            getMService().setCaptchaStoreMaxSize(CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION - 1);
            fail("should have thrown an exception");
        } catch (Exception e) {
            assertTrue( e instanceof IllegalArgumentException,"IllegalArgumentException attended");
        }
        getMService().setCaptchaStoreMaxSize(CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION);
        assertEquals(
                CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION, getMService().getCaptchaStoreMaxSize(),
                "modified size");
    }
    @Test
    public void testSetCaptchaStoreSizeBeforeGarbageCollection() throws Exception {
        try {
            getMService().setCaptchaStoreSizeBeforeGarbageCollection(MAX_CAPTCHA_STORE_SIZE + 1);
            fail("should have thrown an exception");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException, "IllegalArgumentException attended");
        }
        getMService().setCaptchaStoreSizeBeforeGarbageCollection(MAX_CAPTCHA_STORE_SIZE);
        assertEquals(MAX_CAPTCHA_STORE_SIZE, getMService().getCaptchaStoreSizeBeforeGarbageCollection(),"modified size");
    }
    @Test
    public void testGetCaptchaStoreSizeBeforeGarbageCollection() throws Exception {
        assertEquals(CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION,
                getMService().getCaptchaStoreSizeBeforeGarbageCollection(), "initial value");
    }
    @Test
    public void testGetNumberOfGarbageCollectedCaptcha() throws Exception {
        assertEquals(0, getMService().getNumberOfGarbageCollectedCaptcha(), "inital value");
        loadAndWait();
        getMService().garbageCollectCaptchaStore();
        assertEquals(
                CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION,
                getMService().getNumberOfGarbageCollectedCaptcha(), "all should have been collected");
        //try with empty
        loadAndWait();
        getMService().emptyCaptchaStore();
        getMService().garbageCollectCaptchaStore();
        assertEquals(
                CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION,
                getMService().getNumberOfGarbageCollectedCaptcha(), "none have been collected");

        loadAndWait();
        getMService().validateResponseForID(String.valueOf(0), "true");
        getMService().garbageCollectCaptchaStore();
        assertEquals(
                2L * (long)CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION - 1L,
                getMService().getNumberOfGarbageCollectedCaptcha(),
                "all but one should have been collected");
    }

    @Test
    public void testGetNumberOfGarbageCollectableCaptchas() throws Exception {
        assertEquals(0, getMService().getNumberOfGarbageCollectableCaptchas(), "inital value");
        loadAndWait();

        assertEquals(
                CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION,
                getMService().getNumberOfGarbageCollectableCaptchas(), "all should be collectable");
        getMService().garbageCollectCaptchaStore();
        
        //try with empty
        assertEquals(0, getMService().getNumberOfGarbageCollectableCaptchas(), "none should be collectable");
        loadAndWait();
        assertEquals(CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION,
                getMService().getNumberOfGarbageCollectableCaptchas(), "all should be collectable");
        getMService().emptyCaptchaStore();
        assertEquals( 0, getMService().getNumberOfGarbageCollectableCaptchas(), "none should be collectable");
        //try with validate
        loadAndWait();
        getMService().validateResponseForID(String.valueOf(0), "true");
        assertEquals(
                CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION - 1,
                getMService().getNumberOfGarbageCollectableCaptchas(), "all but one should be collectable");

        getMService().getChallengeForID("newCaptcha");
        assertEquals(
                CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION - 1,
                getMService().getNumberOfGarbageCollectableCaptchas(), "all but one should be collectable");

        Thread.sleep((long)MIN_GUARANTED_STORAGE_DELAY_IN_SECONDS * 1000L + 100L);
        assertEquals(
                CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION,
                getMService().getNumberOfGarbageCollectableCaptchas(), "all should be collectable");

    }
    @Test
    public void testGetCaptchaStoreSize() throws Exception {
        assertEquals(0, getMService().getCaptchaStoreSize(), "initial size");
        for (int i = 0; i < SIZE; i++) {
            String id = String.valueOf(i);
            service.generateAndStoreCaptcha(Locale.getDefault(), id);
            assertEquals(i + 1, getMService().getCaptchaStoreSize());
        }
        getMService().validateResponseForID(String.valueOf(0), "true");
        assertEquals(SIZE - 1, getMService().getCaptchaStoreSize());
        getMService().getChallengeForID("newCaptcha");
        assertEquals(SIZE, getMService().getCaptchaStoreSize());
        getMService().getChallengeForID("newCaptcha");
        assertEquals( SIZE, getMService().getCaptchaStoreSize());
        getMService().getQuestionForID("newCaptcha");
        assertEquals(SIZE, getMService().getCaptchaStoreSize());
        getMService().getQuestionForID("newCaptcha", Locale.getDefault());
        assertEquals(SIZE, getMService().getCaptchaStoreSize());
        getMService().getChallengeForID("newCaptcha", Locale.getDefault());
        assertEquals(SIZE, getMService().getCaptchaStoreSize());

    }
    @Test
    public void testGetNumberOfUncorrectResponses() throws Exception {
        assertEquals(0, getMService().getNumberOfUncorrectResponses(), "initial size");

        for (int i = 0; i < CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION; i++) {
            String id = String.valueOf(i);
            service.generateAndStoreCaptcha(Locale.getDefault(), id);
            service.validateResponseForID(id, "false");
            assertEquals(i + 1, getMService().getNumberOfUncorrectResponses());
        }

        for (int i = 0; i < CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION; i++) {
            String id = String.valueOf(i);
            service.generateAndStoreCaptcha(Locale.getDefault(), id);
            service.validateResponseForID(id, "true");
            assertEquals( CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION,
                    getMService().getNumberOfUncorrectResponses(), "should not have been incremented");
        }

        for (int i = 0; i < CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION; i++) {
            String id = String.valueOf(i);
            service.generateAndStoreCaptcha(Locale.getDefault(), id);
            try {
                service.validateResponseForID("unknown", "false");
                fail("should have thrown an exception");
            } catch (CaptchaServiceException e) {
            	assertNotNull(e.getMessage());
            }
            assertEquals(CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION,
                    getMService().getNumberOfUncorrectResponses(), "should not have been incremented");
        }

    }
    
    @Test
    public void testGetNumberOfCorrectResponses() throws Exception {
        assertEquals(0, getMService().getNumberOfCorrectResponses(), "initial size");

        for (int i = 0; i < CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION; i++) {
            String id = String.valueOf(i);
            service.generateAndStoreCaptcha(Locale.getDefault(), id);
            service.validateResponseForID(id, "true");
            assertEquals(i + 1, getMService().getNumberOfCorrectResponses());
        }

        for (int i = 0; i < CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION; i++) {
            String id = String.valueOf(i);
            service.generateAndStoreCaptcha(Locale.getDefault(), id);
            service.validateResponseForID(id, "false");
            assertEquals(CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION,
                    getMService().getNumberOfCorrectResponses(), "should not have been incremented");
        }

        for (int i = 0; i < CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION; i++) {
            String id = String.valueOf(i);
            service.generateAndStoreCaptcha(Locale.getDefault(), id);
            try {
                service.validateResponseForID("unknown", "false");
                fail("should have thrown an exception");
            } catch (CaptchaServiceException e) {
            	assertNotNull(e.getMessage());
            }
            assertEquals(CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION,
                    getMService().getNumberOfCorrectResponses(), "should not have been incremented");
        }
    }
    @Test
    public void testGetNumberOfGeneratedCaptchas() throws Exception {
        assertEquals(0, getMService().getNumberOfGeneratedCaptchas(), "initial size");

        for (int i = 0; i < SIZE; i++) {
            String id = String.valueOf(i);
            service.getChallengeForID(id);
            assertEquals( 4 * i + 1, getMService().getNumberOfGeneratedCaptchas());
            service.getChallengeForID(id);
            assertEquals(4 * i + 2, getMService().getNumberOfGeneratedCaptchas());
            service.getQuestionForID(id);
            assertEquals(4 * i + 2, getMService().getNumberOfGeneratedCaptchas());
            service.getQuestionForID(id);
            assertEquals(4 * i + 2, getMService().getNumberOfGeneratedCaptchas());
            service.getChallengeForID(id);
            assertEquals(4 * i + 3, getMService().getNumberOfGeneratedCaptchas());
            service.getChallengeForID(id);
            assertEquals(4 * i + 4, getMService().getNumberOfGeneratedCaptchas());
        }
        long generatedBefore = getMService().getNumberOfGeneratedCaptchas();
        getMService().emptyCaptchaStore();
        for (int i = 0; i < SIZE; i++) {
            String id = String.valueOf(i);
            service.getQuestionForID(id);
            assertEquals(3 * i + 1 + generatedBefore, getMService().getNumberOfGeneratedCaptchas());
            service.getChallengeForID(id);
            assertEquals(3 * i + 1 + generatedBefore, getMService().getNumberOfGeneratedCaptchas());
            service.getQuestionForID(id);
            assertEquals(3 * i + 1 + generatedBefore, getMService().getNumberOfGeneratedCaptchas());
            service.getQuestionForID(id);
            assertEquals(3 * i + 1 + generatedBefore, getMService().getNumberOfGeneratedCaptchas());
            service.getChallengeForID(id);
            assertEquals(3 * i + 2 + generatedBefore, getMService().getNumberOfGeneratedCaptchas());
            service.getChallengeForID(id);
            assertEquals(3 * i + 3 + generatedBefore, getMService().getNumberOfGeneratedCaptchas());
        }
    }
    
    @Test
    public void testSetMinGuarantedStorageDelayInSeconds() throws Exception {
        assertEquals(MIN_GUARANTED_STORAGE_DELAY_IN_SECONDS, getMService().getMinGuarantedStorageDelayInSeconds());
        getMService().setMinGuarantedStorageDelayInSeconds(80);
        assertEquals(80, getMService().getMinGuarantedStorageDelayInSeconds());

        getMService().setMinGuarantedStorageDelayInSeconds(100);
        fullLoad();

        assertEquals(0, getMService().getNumberOfGarbageCollectedCaptcha(), "none should be collected");

    }
    @Test
    public void testGetMinGuarantedStorageDelayInSeconds() throws Exception {
        assertEquals(MIN_GUARANTED_STORAGE_DELAY_IN_SECONDS, getMService().getMinGuarantedStorageDelayInSeconds());
    }

    @Test
    public void testAutomaticGarbaging() throws Exception {
        loadAndWait();
        assertEquals( 0, getMService().getNumberOfGarbageCollectedCaptcha(), "none should have been collected yet");
        getMService().getChallengeForID("new");
        assertEquals(CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION,
                getMService().getNumberOfGarbageCollectedCaptcha(),
                "all should have been collected");
        getMService().emptyCaptchaStore();
        assertEquals(
                0,
                getMService().getNumberOfGarbageCollectableCaptchas(),
                "we should not been able to garbage collect");
        getMService().setMinGuarantedStorageDelayInSeconds(5);
        assertEquals(
                0,
                getMService().getNumberOfGarbageCollectableCaptchas(),
                "we should not been able to garbage collect");
        fullLoad();
        
        assertEquals(
                0,
                getMService().getNumberOfGarbageCollectableCaptchas(),
                "to be valid we should not been able to garbage collect");
        assertEquals(
                MAX_CAPTCHA_STORE_SIZE,
                getMService().getCaptchaStoreSize(),
                "store size should be full");
        Thread.sleep(5 * 1000 + 100);
        assertEquals(
                MAX_CAPTCHA_STORE_SIZE,
                getMService().getNumberOfGarbageCollectableCaptchas(),
                "all should be collectable");


    }

    @Test
    public void testMaxStoreSizeConstraint() throws Exception {
        //getMService().setMinGuarantedStorageDelayInSeconds(10000);
        fullLoad();
        for (int i = 0; i < 100; i++) {
            try {
                getMService().getChallengeForID("new" + String.valueOf(i));
                fail("should have thrown a captcha store full exception");
            } catch (CaptchaServiceException e) {
                assertEquals(MAX_CAPTCHA_STORE_SIZE, getMService().getCaptchaStoreSize(), "store should be of max size");
            }
        }

        Thread.sleep(1000);
        getMService().setMinGuarantedStorageDelayInSeconds(1);
        Thread.sleep(1000);
        try {
            fullLoad();

        } catch (CaptchaServiceException e) {
            fail("should not have thrown a captcha store full exception");
        }

    }

    private void fullLoad() {
        int i = 0;
        try {
            for (i = 0; i < MAX_CAPTCHA_STORE_SIZE; i++) {
                String id = String.valueOf(i);
                service.generateAndStoreCaptcha(Locale.getDefault(), id);
            }
        } catch (CaptchaServiceException e) {
            throw e;
        }
    }

    private void loadAndWait() throws InterruptedException {
        for (int i = 0; i < CAPTCHA_STORE_LOAD_BEFORE_GARBAGE_COLLECTION; i++) {
            String id = String.valueOf(i);
            service.generateAndStoreCaptcha(Locale.getDefault(), id);
        }
        Thread.sleep((long) MIN_GUARANTED_STORAGE_DELAY_IN_SECONDS * 1000L + 100L);
    }


    protected class MockedManageableCaptchaService extends AbstractManageableCaptchaService {

        protected MockedManageableCaptchaService(CaptchaStore captchaStore, com.octo.captcha.engine.CaptchaEngine captchaEngine,
                                                 int minGuarantedStorageDelayInSeconds,
                                                 int maxCaptchaStoreSize,
                                                 int captchaStoreLoadBeforeGarbageCollection) {
            super(captchaStore, captchaEngine, minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize,
                    captchaStoreLoadBeforeGarbageCollection);
        }

        /**
         * This method must be implemented by sublcasses and : Retrieve the challenge from the captcha Make and return a
         * clone of the challenge Return the clone It has be design in order to let the service dipose the challenge of
         * the captcha after rendering. It should be implemented for all captcha type (@see ImageCaptchaService
         * implementations for exemple)
         *
         * @return a Challenge Clone
         */
        protected Object getChallengeClone(Captcha captcha) {
            return new String(captcha.getChallenge().toString()) + MockedCaptchaService.CLONE_CHALLENGE;
        }
        
        protected boolean isCaptchaStoreFull() {
        	return super.isCaptchaStoreFull();
        }

    }


}

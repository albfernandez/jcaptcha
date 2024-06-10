/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.service.captchastore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.octo.captcha.Captcha;
import com.octo.captcha.MockCaptcha;

/**
 *
 * @author <a href="mailto:marc.antoine.garrigue@gmail.com">Marc-Antoine Garrigue</a>
 * @version 1.0
 */

public abstract class CaptchaStoreTestAbstract  {

    protected CaptchaStore store;
    private Captcha captcha;
    public static final int SIZE = 10000;
    public abstract CaptchaStore getStore();

    public CaptchaStoreTestAbstract() {
    	super();
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        store = getStore();
        store.initAndStart();
        captcha = new MockCaptcha(Locale.getDefault());
    }
    
    @AfterEach
    public void tearDown() {
    	store.cleanAndShutdown();
    }



    @Test
	public void testHasCaptcha() throws Exception {
        assertFalse(store.hasCaptcha("1"), "should not have");
        store.storeCaptcha("2", captcha, Locale.ENGLISH);
        store.storeCaptcha("1 ", captcha, Locale.ENGLISH);
        store.storeCaptcha(" 1", captcha, Locale.ENGLISH);
        assertFalse(store.hasCaptcha("1"), "should not have");
        store.storeCaptcha("1", captcha, Locale.ENGLISH);
        assertTrue(store.hasCaptcha("1"), "should");

    }

	@Test
    public void testStoreCaptcha() throws Exception {
        for (int i = 0; i < SIZE; i++) {
            store.storeCaptcha(String.valueOf(i), captcha, Locale.ENGLISH);
        }
        for (int i = 0; i < SIZE; i++) {
            assertNotNull(store.getCaptcha(String.valueOf(i)));
        }
    }

	@Test
    public void testStoreCaptchaAndLocale() throws Exception {
          for (int i = 0; i < SIZE; i++) {
              store.storeCaptcha(String.valueOf(i), captcha, Locale.FRENCH);
          }
          for (int i = 0; i < SIZE; i++) {
              assertEquals(Locale.FRENCH,store.getLocale(String.valueOf(i)));
          }
      }



    @Test
    public void testRemoveCaptcha() throws Exception {
        for (int i = 0; i < SIZE; i++) {
            store.storeCaptcha(String.valueOf(i), captcha, Locale.ENGLISH);

        }
        assertEquals(store.getSize(), SIZE, "should have a size of " + SIZE);

        for (int i = 0; i < SIZE; i++) {
            assertTrue(store.removeCaptcha(String.valueOf(i)), "Should be removed");
        }

        for (int i = 0; i < SIZE; i++) {
            assertFalse(store.removeCaptcha(String.valueOf(i)), "Should not be removed");
        }
        assertTrue(store.getSize() == 0, "should be empty now");
    }

    @Test
    public void testGetSize() throws Exception {
        for (int i = 0; i < SIZE; i++) {
            store.storeCaptcha(String.valueOf(i), captcha, Locale.ENGLISH);
            assertEquals(i + 1, store.getSize(), "Size should be : " + i);
        }
        assertEquals(store.getSize(), SIZE, "should have a size of " + SIZE);

        for (int i = 0; i < SIZE; i++) {
            store.removeCaptcha(String.valueOf(i));
            assertEquals(SIZE - i - 1, store.getSize(), "Size should be : " + (SIZE - i - 1));
        }

    }
    
    @Test
    public void testGetKeys() throws Exception {
        for (int i = 0; i < SIZE; i++) {
            store.storeCaptcha(String.valueOf(i), captcha, Locale.ENGLISH);
        }
        Collection<String> keys = store.getKeys();

        for (int i = 0; i < SIZE; i++) {
            assertTrue(keys.contains(String.valueOf(i)), "store should have key ");
        }

        for (int i = 0; i < SIZE; i++) {
            store.removeCaptcha(String.valueOf(i));
        }
        assertTrue(store.getKeys().size() == 0, "keys should be empty");
    }

    @Test
    public void testGetCaptcha() throws Exception {
        for (int i = 0; i < SIZE; i++) {
            store.storeCaptcha(String.valueOf(i), captcha, Locale.ENGLISH);

        }

        for (int i = 0; i < SIZE; i++) {
            assertEquals(captcha, store.getCaptcha(String.valueOf(i)), "store should a captcha for this key ");
        }

        assertNull(store.getCaptcha("unknown"));

    }

    @Test
    public void testEmpty() throws Exception {
        for (int i = 0; i < SIZE; i++) {
            store.storeCaptcha(String.valueOf(i), captcha, Locale.ENGLISH);
        }
        store.empty();
        assertEquals(0, store.getSize(), "Size should be 0");
        assertTrue(store.getKeys().size() == 0, "keys should be empty");

    }
}

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
package com.octo.captcha.engine.bufferedengine;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import junit.framework.TestCase;

/**
 * Unit test the ContainerConfiguration
 *
 * @author Benoit Doumas
 */
public class ContainerConfigurationTest extends TestCase {
    ContainerConfiguration config = null;

    Map<Locale, Double> localRatio = null;

    int maxMemSize = 10;

    int maxPersistentSize = 100;

    int swapSize = 10;

    int feedSize = 100;

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        localRatio = new HashMap<>();

        localRatio.put(Locale.FRANCE, Double.valueOf(0.2));
        localRatio.put(Locale.US, Double.valueOf(0.8));
        config = new ContainerConfiguration(localRatio, 0, 0, 10, 40);
    }

    public void testSetFeedSize() {
        config.setFeedSize(Integer.valueOf(feedSize));

        assertEquals(feedSize, config.getFeedSize().intValue());
    }

    public void testSetLocaleRatio() {
        HashMap<Locale, Double> localRatio2 = new HashMap<>();
        localRatio2.put(Locale.GERMAN, Double.valueOf(0.1));
        localRatio2.put(Locale.CHINA, Double.valueOf(0.8));
        localRatio2.put(Locale.ITALY, Double.valueOf(0.1));

        config.setLocaleRatio(localRatio2);

        assertEquals(3, config.getLocaleRatio().size());
    }

    public void testServeOnlyConfiguredLocales() {
        Map<Locale, Double> localRatio2 = new HashMap<>();
        localRatio2.put(Locale.GERMAN, Double.valueOf(0.1));
        localRatio2.put(Locale.CHINA, Double.valueOf(0.8));
        localRatio2.put(Locale.ITALY, Double.valueOf(0.1));

        try {
            config = new ContainerConfiguration(localRatio2, 0, 0, 10, 40, true, Locale.getDefault());
            fail("should have thown an exception concerning the default locale");
        } catch (Exception e) {

        }

        config = new ContainerConfiguration(localRatio2, 0, 0, 10, 40, false, Locale.KOREAN);
        try {
            config.setServeOnlyConfiguredLocales(true);
            fail("should have thown an exception concerning the default locale");
        } catch (Exception e) {

        }
        config = new ContainerConfiguration(localRatio2, 0, 0, 10, 40, true, Locale.GERMAN);
        config = new ContainerConfiguration(localRatio2, 0, 0, 10, 40);
        config.setDefaultLocale(Locale.GERMAN);
        config.setServeOnlyConfiguredLocales(true);

        try {
            config.setDefaultLocale(Locale.JAPAN);
            fail("should have thown an exception concerning the default locale");
        } catch (Exception e) {

        }


    }

    public void testSetMaxPersistentMemorySize() {
        config.setMaxPersistentMemorySize(Integer.valueOf(maxPersistentSize));

        assertEquals(maxPersistentSize, config.getMaxPersistentMemorySize().intValue());
    }

    public void testSetMaxVolatileMemorySize() {
        config.setMaxVolatileMemorySize(Integer.valueOf(maxMemSize));

        assertEquals(maxMemSize, config.getMaxVolatileMemorySize().intValue());
    }

    public void testSetSwapSize() {
        config.setSwapSize(Integer.valueOf(swapSize));

        assertEquals(swapSize, config.getSwapSize().intValue());
    }

}

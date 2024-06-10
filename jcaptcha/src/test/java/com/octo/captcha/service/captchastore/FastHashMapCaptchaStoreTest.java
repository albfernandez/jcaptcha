/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.service.captchastore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.octo.captcha.service.captchastore.impl.FastHashMapCaptchaStore;

public class FastHashMapCaptchaStoreTest extends CaptchaStoreTestAbstract {


	public FastHashMapCaptchaStoreTest() {
		super();
	}
	
	public CaptchaStore getStore() {
        return new FastHashMapCaptchaStore();
    }
	
	@BeforeEach
    public void setUp() throws Exception {
		super.setUp();
    }
    
    @AfterEach
    public void tearDown() {
    	super.tearDown();
    }

}

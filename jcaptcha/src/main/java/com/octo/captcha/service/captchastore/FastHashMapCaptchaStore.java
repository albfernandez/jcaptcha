/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.service.captchastore;

import org.apache.commons.collections.FastHashMap;

/**
 * @author <a href="mailto:marc.antoine.garrigue@gmail.com">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class FastHashMapCaptchaStore extends MapCaptchaStore {
    @SuppressWarnings("unchecked")
	public FastHashMapCaptchaStore() {
    	super();
        this.store = new FastHashMap();
    }
}

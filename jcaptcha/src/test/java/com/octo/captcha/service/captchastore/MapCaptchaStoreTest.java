/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.service.captchastore;

import com.octo.captcha.service.captchastore.impl.MapCaptchaStore;

public class MapCaptchaStoreTest extends CaptchaStoreTestAbstract {

    public CaptchaStore getStore() {
        return new MapCaptchaStore();
    }

}

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
package com.octo.captcha.engine;

import java.util.Locale;

import com.octo.captcha.image.ImageCaptcha;
import com.octo.captcha.image.ImageCaptchaFactory;

/**
 * @author <a href="mailto:marc.antoine.garrigue@gmail.com">Marc-Antoine Garrigue</a>
 */
public class MockImageCaptchaFactory extends ImageCaptchaFactory {
    /**
     * ImageCaptcha.
     *
     * @return a image captcha
     */
    public ImageCaptcha getImageCaptcha() {
        return new ImageCaptcha(null, null) {
            private static final long serialVersionUID = 6380875541752768415L;

			public Boolean validateResponse(Object response) {
                return Boolean.FALSE;
            }
        };
    }

    /**
     * a ImageCaptcha.
     *
     * @return a localized ImageCaptcha
     */
    public ImageCaptcha getImageCaptcha(Locale locale) {
        return getImageCaptcha();
    }


}

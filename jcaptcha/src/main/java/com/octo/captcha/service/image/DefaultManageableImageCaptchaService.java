/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.service.image;


import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.engine.image.gimpy.DefaultGimpyEngine;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.captchastore.impl.FastHashMapCaptchaStore;

/**
 * <p>Default service implementation : use a {@link FastHashMapCaptchaStore} as captcha store, and a DefaultGimpyEngine </p> It is initialized
 * with thoses default values : <ul> <li>min guaranted delay : 180s </li> <li>max store size : 100000 captchas </li>
 * <li>max store size before garbage collection : 75000 </li> </ul>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class DefaultManageableImageCaptchaService extends AbstractManageableImageCaptchaService
        implements ImageCaptchaService {
    /**
     * Construct a new ImageCaptchaService with a {@link FastHashMapCaptchaStore} and a {@link DefaultGimpyEngine}
     *  minGuarantedStorageDelayInSeconds = 180s
     *  maxCaptchaStoreSize = 100000
     *  captchaStoreLoadBeforeGarbageCollection=75000
     */
    public DefaultManageableImageCaptchaService() {
        super(new FastHashMapCaptchaStore(), new DefaultGimpyEngine(), 180,
                100000, 75000);
    }

    /**
     * Construct a new ImageCaptchaService with a {@link FastHashMapCaptchaStore} and a {@link DefaultGimpyEngine}
     * @param minGuarantedStorageDelayInSeconds minGuarantedStorageDelayInSeconds
     * @param maxCaptchaStoreSize  maxCaptchaStoreSize
     * @param captchaStoreLoadBeforeGarbageCollection captchaStoreLoadBeforeGarbageCollection
     */
    public DefaultManageableImageCaptchaService( int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize, int captchaStoreLoadBeforeGarbageCollection) {
        super(new FastHashMapCaptchaStore(), new DefaultGimpyEngine(),minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize, captchaStoreLoadBeforeGarbageCollection);
    }

    /**
     * @param captchaStore captchaStore
     * @param captchaEngine captchaEngine
     * @param minGuarantedStorageDelayInSeconds minGuarantedStorageDelayInSeconds
     * @param maxCaptchaStoreSize maxCaptchaStoreSize
     * @param captchaStoreLoadBeforeGarbageCollection captchaStoreLoadBeforeGarbageCollection
     */
    public DefaultManageableImageCaptchaService(CaptchaStore captchaStore, CaptchaEngine captchaEngine, int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize, int captchaStoreLoadBeforeGarbageCollection) {
        super(captchaStore, captchaEngine, minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize, captchaStoreLoadBeforeGarbageCollection);
    }


}

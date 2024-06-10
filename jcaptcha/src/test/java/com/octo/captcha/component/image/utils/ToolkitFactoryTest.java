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

/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.component.image.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Toolkit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.octo.captcha.CaptchaException;

/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @author <a href="antoine.veret@gmail.com">Antoine Veret</a>
 * @version 1.0
 */
public class ToolkitFactoryTest  {

	@BeforeEach
    public void setUp() {
        System.getProperties().remove(ToolkitFactory.TOOLKIT_IMPL);
    }

	@Test
    public void testGetaDefaultToolkit() {
        assertTrue(ToolkitFactory.getToolkit() instanceof Toolkit);
    }

	@Test
    public void testGetBadClassToolkit() {
        System.setProperty(ToolkitFactory.TOOLKIT_IMPL, "toto");
        try {
            ToolkitFactory.getToolkit();
            fail("should throw an exception");
        } catch (Exception expected) {
            assertEquals(CaptchaException.class, expected.getClass());
        }
    }

	@AfterEach
    public void tearDown() {
        System.getProperties().remove(ToolkitFactory.TOOLKIT_IMPL);
    }
}

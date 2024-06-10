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

package com.octo.captcha.component.image.backgroundgenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.image.BufferedImage;
import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.octo.captcha.CaptchaException;

public class FileReaderRandomBackgroundGeneratorTest {
    FileReaderRandomBackgroundGenerator fileReaderRandomBackgroundGenerator;

    @BeforeEach
    protected void setUp() throws Exception {
        fileReaderRandomBackgroundGenerator =
                new FileReaderRandomBackgroundGenerator(Integer.valueOf(2), Integer.valueOf(2), "imagedir");
    }
    
    @Test
    public void testFindDirectoryNotExisting() throws Exception {

        try {
            fileReaderRandomBackgroundGenerator.findDirectory("does not exists");
            fail("should never pass");
        } catch (CaptchaException e) {
            assertNotNull(e.getMessage());
        }
    }
    @Test
    public void testFindDirectoryClasspathDir() throws Exception {

    	File dir = fileReaderRandomBackgroundGenerator.findDirectory("imagedir");
        assertValidDir(dir, "imagedir");
        
        dir = fileReaderRandomBackgroundGenerator.findDirectory(
        	"com/octo/captcha/component/image/backgroundgenerator");
        assertValidDir(dir, "backgroundgenerator");
    }
    @Test
    public void testFindDirectoryClasspathEmptyDir() throws Exception {

    	File dir = fileReaderRandomBackgroundGenerator.findDirectory("emptyimagedir");
        assertValidDir(dir, "emptyimagedir");
    }
    @Test
    public void testCtorEmptyImageDir() throws Exception {

        try {
            new FileReaderRandomBackgroundGenerator(Integer.valueOf(2), Integer.valueOf(2), "emptyimagedir");
            fail("should never pass");
        } catch (CaptchaException e) {
        	assertNotNull(e.getMessage());
        }        
    }
    @Test
    public void testGetBackground() throws Exception {
    	FileReaderRandomBackgroundGenerator backgroundGenerator = 
    		new FileReaderRandomBackgroundGenerator(Integer.valueOf(2), Integer.valueOf(2), "imagedir");
    	BufferedImage image = backgroundGenerator.getBackground();
    	assertNotNull(image);
    }
    
    /**
     * Requires that directory be a directory, be readable, and have the right name.
     */
    private void assertValidDir(File dir, String expectedName) {
        assertTrue(dir.canRead(), "should be readable");
        assertTrue(dir.canRead(), "should be a directory");
        assertEquals(expectedName, dir.getName(), "Name of root path should match name of directory");
    }

}
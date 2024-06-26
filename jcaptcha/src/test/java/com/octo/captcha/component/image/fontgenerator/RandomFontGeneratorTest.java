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


package com.octo.captcha.component.image.fontgenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Font;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @version 1.0
 */
public class RandomFontGeneratorTest {

    private RandomFontGenerator randomFontGenerator;

    private RandomFontGenerator randomFontGeneratorWithList;
    
    private int minFontSize = 8;
    
    private int maxFontSize = 8;

    /**
     * Constructor for RandomFontGeneratorTest.
     */
    public RandomFontGeneratorTest() {
        super();
    }

    @BeforeEach
    public void setUp() {
        this.randomFontGenerator =
                new RandomFontGenerator(Integer.valueOf(minFontSize), Integer.valueOf(maxFontSize));

        Font[] fontsList = new Font[2];
        fontsList[0] = new Font("Courier", Font.BOLD, 10);
        fontsList[1] = new Font("Arial", Font.BOLD, 10);

        this.randomFontGeneratorWithList =
                new RandomFontGenerator(Integer.valueOf(minFontSize), Integer.valueOf(maxFontSize), fontsList);
    }

    @Test
    public void testGetFont() {
        Font test = this.randomFontGenerator.getFont();
        assertNotNull(test);
    }
    
    @Test
    public void testGetFontWithList() {
        Font test = this.randomFontGeneratorWithList.getFont();
        assertNotNull(test);
        assertTrue(test.getName().startsWith("Arial"));
    }

    @Test
    public void testGetFontWithEmptyList() {
        Font[] fontsList = new Font[0];
        try {
            new RandomFontGenerator(Integer.valueOf(10), Integer.valueOf(10), fontsList);

            fail("should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        	assertNotNull(e.getMessage());
        }
    }

    @Test
    public void testGetFontWithBadFontList() {
        Font[] fontsList = new Font[1];
        fontsList[0] = new Font("Courier", Font.BOLD, 10);

        try {
            new RandomFontGenerator(Integer.valueOf(10), Integer.valueOf(10), fontsList);
            fail("should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        	assertNotNull(e.getMessage());
        }
    }
    @Test
    public void testGetFontWithBadFontPrefix() {

        this.randomFontGenerator.setBadFontNamePrefixes(new String[] {"Cour"});

        Font arial = new Font("Arial", Font.BOLD, 10);

        Font[] fontsList = new Font[2];
        fontsList[0] = new Font("Courier", Font.BOLD, 10);
        fontsList[1] = arial;

        List<Font> checkedFontList = this.randomFontGenerator.cleanFontList(fontsList);
        assertEquals(1, checkedFontList.size());
        assertEquals(arial, checkedFontList.get(0));
    }                                                                                        

    @Test
    public void testGetFontWithEmptyBadFontPrefix() {

        this.randomFontGenerator.setBadFontNamePrefixes(new String[] {""});

        Font[] fontsList = new Font[2];
        fontsList[0] = new Font("Courier", Font.BOLD, 10);
        fontsList[1] = new Font("Arial", Font.BOLD, 10);

        List<Font> checkedFontList = this.randomFontGenerator.cleanFontList(fontsList);
        assertEquals(2, checkedFontList.size(), "All fonts should be preserved");
    }
    @Test
    public void testMinFontSize() {
    	Font helvetica = new Font("Helvetica", Font.BOLD, 2);
    	Font styled = this.randomFontGeneratorWithList.applyStyle(helvetica);
    	assertTrue(minFontSize <= styled.getSize());
    }
    
    @Test
    public void testMaxFontSize() {
    	Font helvetica = new Font("Helvetica", Font.BOLD, 24);
    	Font styled = this.randomFontGeneratorWithList.applyStyle(helvetica);
    	assertTrue(maxFontSize >= styled.getSize());
    }

}

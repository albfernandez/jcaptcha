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
package com.octo.captcha.image;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.octo.captcha.Captcha;
import com.octo.captcha.component.image.wordtoimage.SimpleWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.DummyWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.image.gimpy.GimpyFactory;

/**
 * <p>Description: </p>
 *
 * @author <a href="mailto:mga@octo.com">Mathieu Gandin</a>
 * @author <a href="mailto:antoine.veret@gmail.com">Antoine Véret</a>
 * @version 1.1
 */
@Disabled("temporal, fails to load bundles in test")
public class ImageCaptchaTest {

    private ImageCaptcha pixCaptcha;

    /**
     * this method is for initialisation for all the test cases
     */
    @BeforeEach
    public void setUp() throws Exception {
        WordGenerator words = new DummyWordGenerator("TESTING");
        WordToImage word2image = new SimpleWordToImage();
        ImageCaptchaFactory factory = new GimpyFactory(words, word2image);
        pixCaptcha = factory.getImageCaptcha();
    }

    /**
     * This test is for verifying if the question of the captcha is correctly instantiated.
     */
    @Test
    public void testGetQuestion() {
        assertNotNull(pixCaptcha.getQuestion());
    }

    /**
     * This test is for verifying if the challenge of the captcha is correctly instantiated.
     */
    @Test
    public void testGetChallenge() {
        assertNotNull(pixCaptcha.getChallenge());
        assertTrue(pixCaptcha.getImageChallenge() instanceof BufferedImage, "Captcha challenge is not a BufferedImage");
    }

    @Test
    public void testDisposeChallenge() {
        pixCaptcha.disposeChallenge();
        assertNull(pixCaptcha.getChallenge());
    }

    /**
     * This test is for verifying if the response of the captcha is valid.
     */
    @Test
    public void testValidateResponse() throws Exception {
        
        assertFalse(pixCaptcha.validateResponse("dummyResponse").booleanValue());

        Field responseField = pixCaptcha.getClass().getDeclaredField("response");
        responseField.setAccessible(true);
        String response = (String) responseField.get(pixCaptcha);

        assertTrue(pixCaptcha.validateResponse(response).booleanValue());
    }


    @Test
    public void testGetImageChallenge() throws Exception {
        assertFalse(pixCaptcha.hasGetChalengeBeenCalled().booleanValue());
        assertEquals(pixCaptcha.getImageChallenge(), pixCaptcha.getChallenge());
        assertTrue(pixCaptcha.hasGetChalengeBeenCalled().booleanValue());
    }

    @Test
    public void testUnMarshalling() throws Exception {

        byte[] marshalledCaptcha = marshalCaptcha(pixCaptcha);
        Captcha captchaUnserialized = unmarshalCaptcha(marshalledCaptcha);

        assertNotNull(captchaUnserialized);
        assertEquals(pixCaptcha.getQuestion(), captchaUnserialized.getQuestion());
        assertFalse(captchaUnserialized.hasGetChalengeBeenCalled().booleanValue());
        assertTrue(captchaUnserialized.getChallenge() instanceof BufferedImage);
        assertTrue(captchaUnserialized.hasGetChalengeBeenCalled().booleanValue());        
    }

    @Test
    public void testUnMarshallingWithGetChallenge() throws Exception {

        pixCaptcha.getChallenge(); // get the image challenge first

        byte[] marshalledCaptcha = marshalCaptcha(pixCaptcha);
        Captcha captchaUnserialized = unmarshalCaptcha(marshalledCaptcha);

        assertNotNull(captchaUnserialized);
        assertNotNull(captchaUnserialized.getChallenge());
        assertTrue(captchaUnserialized.hasGetChalengeBeenCalled().booleanValue());
    }

    @Test
    public void testUnMarshallingWithDisposedChallenge() throws Exception {

        pixCaptcha.getChallenge(); // get the image challenge first
        pixCaptcha.disposeChallenge();

        byte[] marshalledCaptcha = marshalCaptcha(pixCaptcha);
        Captcha captchaUnserialized = unmarshalCaptcha(marshalledCaptcha);

        assertNotNull(captchaUnserialized);
        assertNull(captchaUnserialized.getChallenge());
        assertTrue(captchaUnserialized.hasGetChalengeBeenCalled().booleanValue());
    }

    private Captcha unmarshalCaptcha(byte[] marshalledCaptcha) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new
                ByteArrayInputStream(marshalledCaptcha));
        Captcha captchaUnserialized = (Captcha) in.readObject();
        return captchaUnserialized;
    }

    private byte[] marshalCaptcha(Captcha captcha) throws IOException {
        ByteArrayOutputStream arrayOutput = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(arrayOutput);
        out.writeObject(captcha);
        out.flush();
        arrayOutput.close();
        return arrayOutput.toByteArray();
    }
}

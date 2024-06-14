package com.octo.service.image;

import java.awt.image.BufferedImage;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

public class DefaultManageableImageCaptchaServiceTest {
	
	public DefaultManageableImageCaptchaServiceTest() {
		super();
	}
	
	// DefaultManageableImageCaptchaService is the service used by SimpleImageCaptchaServlet
	// The first and default user of this library
	// Check if it's working
	
	@Test
	public void basicTest() {
		ImageCaptchaService service = new DefaultManageableImageCaptchaService();
		BufferedImage challenge = service.getImageChallengeForID("testId", Locale.US);
		Assertions.assertNotNull(challenge);
		Assertions.assertEquals(200, challenge.getWidth());
		Assertions.assertEquals(70, challenge.getHeight());
	}

}

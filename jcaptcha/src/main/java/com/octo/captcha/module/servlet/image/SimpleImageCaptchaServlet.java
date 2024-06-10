/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.module.servlet.image;

import java.io.IOException;
import java.util.Locale;

import com.octo.captcha.module.web.image.ImageToJpegHelper;
import com.octo.captcha.module.web.image.ImageToJpegHelper.ImageFormat;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author mag
 */
public class SimpleImageCaptchaServlet extends HttpServlet implements Servlet {
	
	private static final long serialVersionUID = 296035630547992751L;
	public static final ImageCaptchaService service = new DefaultManageableImageCaptchaService();


	@Override
	protected void doGet(HttpServletRequest httpServletRequest,	HttpServletResponse httpServletResponse) throws ServletException, IOException {
		
		ImageFormat imageFormat = ImageFormat.jpg;		
		if (isPng(httpServletRequest)) {
			imageFormat = ImageFormat.png;
		}
		String id = httpServletRequest.getSession(true).getId();
		ImageToJpegHelper.flushNewCaptchaToResponse(httpServletRequest, httpServletResponse, service, id, Locale.getDefault(), imageFormat);

	}

     private boolean isPng(HttpServletRequest request) {
    	 if (request == null) {
    		 return false;
    	 }
    	 String requestUri = request.getRequestURI();
    	 if (requestUri == null) {
    		 return false;
    	 }
    	 return requestUri.toLowerCase().endsWith(".png");
	}

	public static boolean validateResponse(HttpServletRequest request, String userCaptchaResponse){
         if(request.getSession(false)==null) {
        	 //if no session found
        	 return false;
         }
         try {
             return service.validateResponseForID(request.getSession().getId(), userCaptchaResponse);
         } catch (CaptchaServiceException e) {
        	 return false;
         }
     }
 }

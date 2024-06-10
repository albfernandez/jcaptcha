/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.module.servlet.image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * @author mag
 */
public class SimpleImageCaptchaServlet extends HttpServlet implements Servlet {
	
	private static final long serialVersionUID = 296035630547992751L;
	public static final ImageCaptchaService service = new DefaultManageableImageCaptchaService();


	@Override
	protected void doGet(HttpServletRequest httpServletRequest,	HttpServletResponse httpServletResponse) throws ServletException, IOException {	
		// Set to expire far in the past.
		httpServletResponse.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		httpServletResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		httpServletResponse.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		httpServletResponse.setHeader("Pragma", "no-cache");
		
		String mimetype = "image/jpeg";
		String imageFormat = "jpg";
		
		if (isPng(httpServletRequest)) {
			mimetype = "image/png";
			imageFormat = "png";
		}

		// return contentype
		httpServletResponse.setContentType(mimetype);

		// create the image with the text
		BufferedImage bi = service.getImageChallengeForID(httpServletRequest.getSession(true).getId());

		ServletOutputStream out = null; 
		try {
			out = httpServletResponse.getOutputStream();
			// write the data out
			ImageIO.write(bi, imageFormat, out);
			out.flush();
		} finally {
			if (out != null) {
				out.close();
			}
		}
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

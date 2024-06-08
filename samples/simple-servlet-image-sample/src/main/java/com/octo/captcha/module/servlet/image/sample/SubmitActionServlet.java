/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.module.servlet.image.sample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;

/**
 * @author mag
 */
public class SubmitActionServlet extends HttpServlet {

	private static final long serialVersionUID = 7342121576411594220L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userCaptchaResponse = request.getParameter("jcaptcha");
		String format = getFormat(request);
		boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, userCaptchaResponse);
		if (captchaPassed) {
			response.sendRedirect("index.jsp?message=passed&format=" + format);
		} else {
			response.sendRedirect("index.jsp?message=failed&format=" + format);
		}

	}

	private String getFormat(HttpServletRequest request) {
		if (request == null) {
			return "jpg";
		}
		String fmt = request.getParameter("format");
		if (fmt == null) {
			return "jpg";
		}
		if ("png".equals(fmt)) {
			return "png";
		}
		return "jpg";
	}
}

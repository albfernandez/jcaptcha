/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.module.servlet.sound.sample;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.octo.captcha.module.servlet.SimpleSoundCaptchaServlet;

@SuppressWarnings("serial")
public class SubmitActionServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
      String userCaptchaResponse = request.getParameter("jcaptcha");
      boolean captchaPassed = SimpleSoundCaptchaServlet.validateResponse(request, userCaptchaResponse);
      if(captchaPassed){
            response.sendRedirect("index.jsp?message=captcha%20passed");
        }else{
            response.sendRedirect("index.jsp?message=captcha%20failed");
        }

    }
}

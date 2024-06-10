/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.module.taglib;

import java.io.IOException;

import jakarta.servlet.jsp.tagext.Tag;
import jakarta.servlet.jsp.JspException;

import com.octo.captcha.module.config.CaptchaModuleConfig;
import com.octo.captcha.service.CaptchaService;

/**
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class MessageTag extends BaseCaptchaTag implements Tag {

    private String messageKey = CaptchaModuleConfig.getInstance().getMessageKey();

    public int doEndTag() throws JspException {


        String message = (String) pageContext.getRequest().getAttribute(messageKey);
        if (message != null) {
            try {
                pageContext.getOut().write(message);
            } catch (IOException e) {
                throw new JspException(e);
            }

        }
        return EVAL_PAGE;
    }


    protected CaptchaService getService() {
        return null;
    }
}

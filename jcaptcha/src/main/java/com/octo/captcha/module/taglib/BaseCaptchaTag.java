/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.module.taglib;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.Tag;


import com.octo.captcha.service.CaptchaService;

/**
 * Defines the service for the module.
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public abstract class BaseCaptchaTag implements Tag {


    protected PageContext pageContext;

    protected Tag parent;


    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;

    }

    public void setParent(Tag tag) {
        this.parent = tag;
    }

    public Tag getParent() {
        return parent;
    }


    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

    public void release() {

    }

    public abstract int doEndTag() throws JspException;

    protected abstract CaptchaService getService();
}

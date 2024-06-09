/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.module.taglib;

import java.io.IOException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.JspException;

/**
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public abstract class QuestionTag extends BaseCaptchaTag implements Tag {

    public int doEndTag() throws JspException {
        String question = getService().getQuestionForID(pageContext.getSession().getId()
                , pageContext.getRequest().getLocale());

        try {
            pageContext.getOut().write(question);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return EVAL_PAGE;
    }


}

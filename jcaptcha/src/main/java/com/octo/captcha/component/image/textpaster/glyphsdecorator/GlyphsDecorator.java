package com.octo.captcha.component.image.textpaster.glyphsdecorator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.octo.captcha.component.image.textpaster.Glyphs;

/**
 * @author mag
 */
public interface GlyphsDecorator {

    void decorate(Graphics2D g2, Glyphs glyphs, BufferedImage backround);
}

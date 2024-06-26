package com.octo.captcha.component.image.textpaster.glyphsvisitor;

import java.awt.geom.Rectangle2D;
import java.security.SecureRandom;
import java.util.Random;

import com.octo.captcha.component.image.textpaster.Glyphs;

/**
 * @author mag
 */
public class TranslateGlyphsVerticalRandomVisitor implements GlyphsVisitors {

	private Random myRandom = new SecureRandom();
	private double verticalRange = 1;

	public TranslateGlyphsVerticalRandomVisitor(double verticalRange) {
		this.verticalRange = verticalRange;
	}

	public void visit(Glyphs gv, Rectangle2D backroundBounds) {

		for (int i = 0; i < gv.size(); i++) {
			double tx = 0;
			double ty = verticalRange * myRandom.nextGaussian();
			gv.translate(i, tx, ty);
		}
	}
}

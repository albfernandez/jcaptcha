package com.octo.captcha.component.image.textpaster.glyphsvisitor;

import java.awt.geom.Rectangle2D;

import com.octo.captcha.component.image.textpaster.Glyphs;

/**
 * @author mag
 */
public class HorizontalSpaceGlyphsVisitor implements GlyphsVisitors {

    private int spaceBetweenGlyphs=0;

    public HorizontalSpaceGlyphsVisitor(int spaceBetweenGlyphs) {
        this.spaceBetweenGlyphs = spaceBetweenGlyphs;
    }

    public void visit(Glyphs glyphs, Rectangle2D backroundBounds) {

        for(int i=1;i< glyphs.size();i++){
            double tx = glyphs.getBoundsX(i-1)+ glyphs.getBoundsWidth(i-1)- glyphs.getBoundsX(i)+spaceBetweenGlyphs;
            double ty = 0;
            glyphs.translate(i,tx,ty);

        }
    }
}

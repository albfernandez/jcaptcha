package com.octo.captcha.component.image.textpaster.glyphsvisitor;

import java.awt.geom.Rectangle2D;

import com.octo.captcha.component.image.textpaster.Glyphs;

/**
 * @author mag
 */
public class MoveAllGlyphsToOriginVisitor implements GlyphsVisitors {


    public void visit(Glyphs glyphs, Rectangle2D backroundBounds) {
        for(int i=0;i< glyphs.size();i++){
        
            double tx =-glyphs.getX(i);
            double ty =-glyphs.getY(i);
            glyphs.translate(i,tx,ty);
        }
    }
}
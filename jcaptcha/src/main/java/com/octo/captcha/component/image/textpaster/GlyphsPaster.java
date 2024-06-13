package com.octo.captcha.component.image.textpaster;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.image.color.ColorGenerator;
import com.octo.captcha.component.image.textpaster.glyphsdecorator.GlyphsDecorator;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.GlyphsVisitors;

/**
 * Use Glyphs to draw, much more powerfull and efficient than using AttributedString
 *
 * @author mag
 */
public class GlyphsPaster extends AbstractTextPaster{

    private GlyphsVisitors[] glyphVisitors;
    private GlyphsDecorator[] glyphsDecorators;


    public GlyphsPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength) {
        super(minAcceptedWordLength, maxAcceptedWordLength);
    }

    public GlyphsPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, Color textColor) {
        super(minAcceptedWordLength, maxAcceptedWordLength, textColor);
    }

    public GlyphsPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, ColorGenerator colorGenerator) {
        super(minAcceptedWordLength, maxAcceptedWordLength, colorGenerator);
    }

    public GlyphsPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, ColorGenerator colorGenerator, Boolean manageColorPerGlyph) {
        super(minAcceptedWordLength, maxAcceptedWordLength, colorGenerator, manageColorPerGlyph);
    }

    public GlyphsPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, GlyphsVisitors[] glyphVisitors) {
        super(minAcceptedWordLength, maxAcceptedWordLength);
        this.glyphVisitors = glyphVisitors;
    }

    public GlyphsPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, Color textColor, GlyphsVisitors[] glyphVisitors) {
        super(minAcceptedWordLength, maxAcceptedWordLength, textColor);
        this.glyphVisitors = glyphVisitors;
    }

    public GlyphsPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, ColorGenerator colorGenerator, GlyphsVisitors[] glyphVisitors) {
        super(minAcceptedWordLength, maxAcceptedWordLength, colorGenerator);
        this.glyphVisitors = glyphVisitors;
    }

    public GlyphsPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, ColorGenerator colorGenerator, GlyphsVisitors[] glyphVisitors, GlyphsDecorator[] glyphsDecorators) {
        super(minAcceptedWordLength, maxAcceptedWordLength, colorGenerator);
        this.glyphVisitors = glyphVisitors;
        this.glyphsDecorators = glyphsDecorators;
    }

    public GlyphsPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, ColorGenerator colorGenerator, Boolean manageColorPerGlyph, GlyphsVisitors[] glyphVisitors) {
        super(minAcceptedWordLength, maxAcceptedWordLength, colorGenerator, manageColorPerGlyph);
        this.glyphVisitors = glyphVisitors;
    }


    public GlyphsPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, ColorGenerator colorGenerator, Boolean manageColorPerGlyph, GlyphsVisitors[] glyphVisitors, GlyphsDecorator[] glyphsDecorators) {
        super(minAcceptedWordLength, maxAcceptedWordLength, colorGenerator, manageColorPerGlyph);
        this.glyphVisitors = glyphVisitors;
        this.glyphsDecorators = glyphsDecorators;
    }

    public BufferedImage pasteText(BufferedImage background, AttributedString attributedWord) throws CaptchaException {
        BufferedImage out = copyBackground(background);
        Graphics2D g2 = pasteBackgroundAndSetTextColor(out, background);
        customizeGraphicsRenderingHints(g2);
        
        //build glyphs
        AttributedCharacterIterator acit = attributedWord.getIterator();
        Glyphs glyphs = new Glyphs();

        for(int i = 0 ; i < acit.getEndIndex(); i++){
            Font font = (Font) acit.getAttribute(TextAttribute.FONT);
            g2.setFont(font);
            final FontRenderContext frc = g2.getFontRenderContext();
            glyphs.addGlyphVector(font.createGlyphVector(frc,new char[]{acit.current()}));
            acit.next();
        }

        Rectangle2D backgroundBounds = new Rectangle2D.Float(0,0,background.getWidth(),background.getHeight());

        //First decorate
        if (glyphVisitors != null) {
            for (int i = 0; i < glyphVisitors.length; i++) {
                glyphVisitors[i].visit(glyphs,backgroundBounds);
            }
        }

        for(int i = 0 ; i < glyphs.size(); i++){
            g2.drawGlyphVector(glyphs.get(i),0,0);
             if(isManageColorPerGlyph())g2.setColor(getColorGenerator().getNextColor());
        }

        //and now decorate
        if (glyphsDecorators != null) {
            for (int i = 0; i < glyphsDecorators.length; i++) {
                glyphsDecorators[i].decorate(g2, glyphs, background);

            }
        }

        g2.dispose();
        return out;
    }


    

}

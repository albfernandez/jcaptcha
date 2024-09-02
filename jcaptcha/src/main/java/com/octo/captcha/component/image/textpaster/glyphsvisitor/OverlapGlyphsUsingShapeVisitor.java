package com.octo.captcha.component.image.textpaster.glyphsvisitor;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import com.octo.captcha.component.image.textpaster.Glyphs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author mag
 */
public class OverlapGlyphsUsingShapeVisitor extends OverlapGlyphsVisitor {
	
	private static Logger log = LoggerFactory.getLogger(OverlapGlyphsUsingShapeVisitor.class);
	
    private double overlapPixels ;

   

    public OverlapGlyphsUsingShapeVisitor(double overlapPixels) {
        super(0);
        this.overlapPixels = overlapPixels;
    }

    public void visit(Glyphs gv, Rectangle2D backroundBounds) {
       // super.decorate(gv, backroundBounds);

        //evaluate overlapPixel

        for (int i = 1; i < gv.size(); i++) {
            //first position the shapes near the preceding one
             gv.translate(i, getSidingPosition(gv, i), 0);

            //then evaluate if there is a real overlap
            if (mayGlyphsOverlapAtIndex(gv, i)) {
                //ok overlap is possible, try to reach
                double realPossibleOverlap = getMaximumPossibleOverlap(gv, i);
                double currentOverlapWidth = intersectAndGetOverlapWidth(gv, i);
                double currentOverlapStatus = currentOverlapWidth - realPossibleOverlap;
                double bestReacheadOverlapStatus = Math.abs(currentOverlapStatus);
               
                boolean stillOk=true;
                int iteration = 0;
                int limit = 10;
                while (Math.abs(currentOverlapStatus) >= overlapPixels / 10 && stillOk && iteration < limit) {
                	iteration++;
                    double step = currentOverlapStatus/2;
                    gv.translate(i, step, 0);
                    currentOverlapWidth = intersectAndGetOverlapWidth(gv, i);
                    currentOverlapStatus = currentOverlapWidth-realPossibleOverlap;


                    //we already reach the best overlap
                    if(Math.abs(currentOverlapStatus)>=bestReacheadOverlapStatus&&(currentOverlapWidth!=0||gv.getMaxX(i-1)-gv.getMinX(i)>gv.getBoundsWidth(i-1))){
                        //tranlsate back
                        if(currentOverlapWidth==0){
                            //back to siding
                            gv.translate(i, getSidingPosition(gv, i), 0);
                        }else{
                            //back on step
                            gv.translate(i, -step, 0);
                        }
                        stillOk=false;
                    }
                    bestReacheadOverlapStatus = Math.min(Math.abs(currentOverlapStatus),bestReacheadOverlapStatus);
                }
           } else {
                log.warn("NOT POSSIBLE");
            }
        }
    }

    private double getSidingPosition(Glyphs gv, int i) {
        return gv.getBoundsX(i-1)+gv.getBoundsWidth(i-1)-gv.getBoundsX(i)
                    -Math.abs(gv.getRSB(i-1))
                    -Math.abs(gv.getLSB(i));
    }

    private double intersectAndGetOverlapWidth(Glyphs gv, int i) {
        return getIntesection(gv, i).getBounds2D().getWidth();
    }

    private Area getIntesection(Glyphs gv, int index) {
        Area intersect = new Area(gv.getOutline(index - 1));
        intersect.intersect(new Area(gv.getOutline(index)));
        return intersect;
    }

    private double getMaximumPossibleOverlap(Glyphs gv, int index) {
        return Math.min(Math.min(overlapPixels, gv.getBoundsWidth(index)), gv.getBoundsWidth(index - 1));
    }

    private boolean mayGlyphsOverlapAtIndex(Glyphs gv, int index) {
    
       return  gv.getMinY(index-1)> gv.getMaxY(index)||gv.getMinY(index)>gv.getMaxY(index-1);
        
    }
}

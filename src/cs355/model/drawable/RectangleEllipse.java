package cs355.model.drawable;

import cs355.model.drawing.Circle;
import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Square;

import java.awt.geom.Point2D;

/**
 * Created by Joshua on 1/20/2016.
 */
public class RectangleEllipse {
    private double width;
    private double height;

    public RectangleEllipse(){

    }
    public Ellipse makeEllipse(Rectangle givenRect){
        width = givenRect.getWidth();
        height = givenRect.getHeight();
        return new Ellipse(givenRect.getColor(), new Point2D.Double(givenRect.getCenter().getX(), givenRect.getCenter().getY()), width, height);
    }
}

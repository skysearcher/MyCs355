package cs355.model.drawable;

import cs355.model.drawing.Circle;
import cs355.model.drawing.Square;

import java.awt.geom.Point2D;

/**
 * Created by Joshua on 1/19/2016.
 */
public class SquareCircle {

    public SquareCircle(){

    }

    public Circle makeCircle(Square givenSquare){
        double size = givenSquare.getSize();
        Point2D.Double center = givenSquare.getCenter();
        return new Circle(givenSquare.getColor(), new Point2D.Double(center.getX(), center.getY()), size /2 );

    }
}

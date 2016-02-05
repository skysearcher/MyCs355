package cs355.model.drawable;

import cs355.GUIFunctions;
import cs355.model.drawing.Shape;

/**
 * Created by Joshua on 1/19/2016.
 */
public class ShapeConverter {
    public DrawableShape convert(Shape toConvert){
        DrawableShape myShape;
        switch (toConvert.whatShape()) {
            case LINE:
                myShape = new DrawableLine(toConvert);
                break;
            case SQUARE:
                myShape = new DrawableSquare(toConvert);
                break;
            case RECTANGLE:
                myShape = new DrawableRectangle(toConvert);
                break;
            case CIRCLE:
                myShape = new DrawableCircle(toConvert);
                break;
            case ELLIPSE:
                myShape = new DrawableEllipse(toConvert);
                break;
            case TRIANGLE:
                myShape = new DrawableTriangle(toConvert);
                break;
            default:
                myShape = new DrawableLine(toConvert);
                break;
        }
        return myShape;
    }
}

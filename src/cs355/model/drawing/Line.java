package cs355.model.drawing;

import cs355.controller.SelectPoint;
import cs355.controller.TShapeEnum;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Add your line code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Line extends Shape {
    private double myTol;
    private double normalize;
    private double normalOne;
    private double normalTwo;
    private double distance;

    // The ending point of the line.
    private Point2D.Double end;

    /**
     * Basic constructor that sets all fields.
     *
     * @param color the color for the new shape.
     * @param start the starting point.
     * @param end   the ending point.
     */
    public Line(Color color, Point2D.Double start, Point2D.Double end) {

        // Initialize the superclass.
        super(color, start);

        // Set the field.
        this.end = end;

    }

    /**
     * Getter for this Line's ending point.
     *
     * @return the ending point as a Java point.
     */
    public Point2D.Double getEnd() {
        return end;
    }

    /**
     * Setter for this Line's ending point.
     *
     * @param end the new ending point for the Line.
     */
    public void setEnd(Point2D.Double end) {
        this.end = end;
    }

    /**
     * Add your code to do an intersection test
     * here. You <i>will</i> need the tolerance.
     *
     * @param pt        = the point to test against.
     * @param tolerance = the allowable tolerance.
     * @return true if pt is in the shape,
     * false otherwise.
     */
    @Override
    public boolean pointInShape(Point2D.Double pt, double tolerance) {
        AffineTransform worldToObj = new AffineTransform();
        worldToObj.rotate(-this.getRotation());
        worldToObj.translate(-this.getCenter().getX(), -this.getCenter().getY());
        worldToObj.transform(pt, pt);
        myTol = tolerance;
        normalize = Math.sqrt(Math.pow(end.getX() - 0, 2.0) + Math.pow(end.getY() - 0, 2.0));
        normalOne = (end.getX() - 0)/normalize;
        normalTwo = -(end.getY() - 0)/normalize; //now number one
        distance = 0;
        if ((pt.getX() * normalTwo) + (pt.getY() * normalOne) <= myTol && (pt.getX() * normalTwo) + (pt.getY() * normalOne) >= -myTol) {//within tolerance of line
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hitHandle(Point2D.Double pt) {
        return false;
    }

    @Override
    public TShapeEnum whatShape() {
        return TShapeEnum.LINE;
    }

    @Override
    public SelectPoint rotationHit(Point2D.Double pt, double tolerance) {
        if (pointInShape(pt, tolerance)) {
            return SelectPoint.Center;
        }
        if(hitHandle(pt)) {
            return SelectPoint.Rotation;
        } else {
            return SelectPoint.None;
        }
    }

}

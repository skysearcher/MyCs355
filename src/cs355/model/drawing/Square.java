package cs355.model.drawing;

import cs355.controller.SelectPoint;
import cs355.controller.TShapeEnum;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Add your square code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Square extends Shape {

    // The size of this Square.
    private double size;

    /**
     * Basic constructor that sets all fields.
     *
     * @param color  the color for the new shape.
     * @param center the center of the new shape.
     * @param size   the size of the new shape.
     */
    public Square(Color color, Point2D.Double center, double size) {

        // Initialize the superclass.
        super(color, center);

        // Set the field.
        this.size = size;
    }

    /**
     * Getter for this Square's size.
     *
     * @return the size as a double.
     */
    public double getSize() {
        return size;
    }

    /**
     * Setter for this Square's size.
     *
     * @param size the new size.
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * Add your code to do an intersection test
     * here. You shouldn't need the tolerance.
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
        return size / 2 > pt.getX() && -size / 2 < pt.getX() && size / 2 > pt.getY() && -size / 2 < pt.getY();
    }

    @Override
    public boolean hitHandle(Point2D.Double pt) {
        return pt.getY() > (-size/2) - 20 && pt.getY() < (-size/2) - 10 && pt.getX() > -5.0 && pt.getX() < 5.0;
    }

    @Override
    public TShapeEnum whatShape() {
        return TShapeEnum.SQUARE;
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

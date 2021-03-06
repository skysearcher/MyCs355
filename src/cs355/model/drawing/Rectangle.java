package cs355.model.drawing;

import cs355.controller.SelectPoint;
import cs355.controller.TShapeEnum;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Add your rectangle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Rectangle extends Shape {

    // The width of this shape.
    private double width;

    // The height of this shape.
    private double height;

    /**
     * Basic constructor that sets all fields.
     *
     * @param color  the color for the new shape.
     * @param center the center of the new shape.
     * @param width  the width of the new shape.
     * @param height the height of the new shape.
     */
    public Rectangle(Color color, Point2D.Double center, double width, double height) {

        // Initialize the superclass.
        super(color, center);

        // Set fields.
        this.width = width;
        this.height = height;
    }

    /**
     * Getter for this shape's width.
     *
     * @return this shape's width as a double.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Setter for this shape's width.
     *
     * @param width the new width.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Getter for this shape's height.
     *
     * @return this shape's height as a double.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Setter for this shape's height.
     *
     * @param height the new height.
     */
    public void setHeight(double height) {
        this.height = height;
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
        worldToObj.concatenate(new AffineTransform(new AffineTransform(Math.cos(-this.getRotation()),Math.sin(-this.getRotation()), Math.sin(this.getRotation()),Math.cos(-this.getRotation()), 0, 0)));
        worldToObj.concatenate(new AffineTransform(1, 0, 0, 1, -this.getCenter().getX(), -this.getCenter().getY()));
        worldToObj.transform(pt, pt);

        double maxX = width / 2;
        double maxY = height / 2;
        double minX = -(width / 2);
        double minY = -(height / 2);
        return maxX > pt.getX() && minX < pt.getX() && maxY > pt.getY() && minY < pt.getY();
    }

    @Override
    public boolean hitHandle(Point2D.Double pt, double zoomD) {
        return pt.getY() > (-(height)/2) - 20*(1/zoomD) && pt.getY() < (-(height)/2) - 10*(1/zoomD) && pt.getX() > -5.0 && pt.getX() < 5.0;
    }

    @Override
    public TShapeEnum whatShape() {
        return TShapeEnum.RECTANGLE;
    }

    @Override
    public SelectPoint rotationHit(Point2D.Double pt, double tolerance, double zoom) {
        if (pointInShape(pt, tolerance)) {
            return SelectPoint.Center;
        }
        if(hitHandle(pt, zoom)) {
            return SelectPoint.Rotation;
        } else {
            return SelectPoint.None;
        }
    }

}

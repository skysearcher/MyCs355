package cs355.model.drawing;

import cs355.controller.SelectPoint;
import cs355.controller.TShapeEnum;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Add your triangle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Triangle extends Shape {

    // The three points of the triangle.
    private Point2D.Double a;
    private Point2D.Double b;
    private Point2D.Double c;
    private Polygon p;

    /**
     * Basic constructor that sets all fields.
     *
     * @param color  the color for the new shape.
     * @param center the center of the new shape.
     * @param a      the first point, relative to the center.
     * @param b      the second point, relative to the center.
     * @param c      the third point, relative to the center.
     */
    public Triangle(Color color, Point2D.Double center, Point2D.Double a,
                    Point2D.Double b, Point2D.Double c) {

        // Initialize the superclass.
        super(color, center);

        // Set fields.
        this.a = a;
        this.b = b;
        this.c = c;
        p = new Polygon();
        p.addPoint((int)a.getX(), (int)a.getY());
        p.addPoint((int)b.getX(), (int)b.getY());
        p.addPoint((int)c.getX(), (int)c.getY());
    }

    /**
     * Getter for the first point.
     *
     * @return the first point as a Java point.
     */
    public Point2D.Double getA() {
        return a;
    }

    /**
     * Setter for the first point.
     *
     * @param a the new first point.
     */
    public void setA(Point2D.Double a) {
        this.a = a;
    }

    /**
     * Getter for the second point.
     *
     * @return the second point as a Java point.
     */
    public Point2D.Double getB() {
        return b;
    }

    /**
     * Setter for the second point.
     *
     * @param b the new second point.
     */
    public void setB(Point2D.Double b) {
        this.b = b;
    }

    /**
     * Getter for the third point.
     *
     * @return the third point as a Java point.
     */
    public Point2D.Double getC() {
        return c;
    }

    /**
     * Setter for the third point.
     *
     * @param c the new third point.
     */
    public void setC(Point2D.Double c) {
        this.c = c;
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
        return p.contains(pt.getX(), pt.getY());
    }

    @Override
    public boolean hitHandle(Point2D.Double pt, double zoomD) {
        double myHeight = p.getBounds().getHeight();
        myHeight = ((-myHeight/2))- 20;
        if(pt.getX() > -10 && pt.getX() < 10 && pt.getY() > myHeight && pt.getY() < myHeight + 10){
           return true;
        }else{
            return false;
        }
    }

    @Override
    public TShapeEnum whatShape() {
        return TShapeEnum.TRIANGLE;
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

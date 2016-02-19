package cs355.model.drawable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import cs355.model.drawing.Circle;
import cs355.model.drawing.Shape;

/**
 * Created by Joshua on 1/19/2016.
 */
public class DrawableCircle implements DrawableShape {
    private Shape innerCircle;
    private double radius;
    AffineTransform objToWorld;
    private double zoomD;
    private AffineTransform objToView;
    private AffineTransform zoomTrans;
    private Point2D.Double off;

    public DrawableCircle(Shape givenShape) {
        innerCircle = givenShape;
        radius = ((Circle) givenShape).getRadius();

    }

    @Override
    public void onDraw(Graphics2D g2d, AffineTransform worldToView) {
        zoomTrans = new AffineTransform(worldToView);
        zoomD = worldToView.getScaleX();
        objToWorld = new AffineTransform();
        objToWorld.concatenate(new AffineTransform(1, 0, 0, 1, innerCircle.getCenter().getX(), innerCircle.getCenter().getY()));

        objToView = new AffineTransform();

        objToView.concatenate(worldToView);
        objToView.concatenate(objToWorld);


        g2d.setTransform(objToView);
        g2d.setColor(innerCircle.getColor());
        g2d.fillOval((int) (-radius),(int) (-radius) , (int) (radius * 2), (int) (radius * 2));
    }

    @Override
    public void drawSelection(Graphics2D g2d) {
        objToWorld = new AffineTransform();
        off = new Point2D.Double(innerCircle.getCenter().getX(), innerCircle.getCenter().getY());
        zoomTrans.transform(off, off);
        objToWorld.concatenate(new AffineTransform(1, 0, 0, 1, off.getX(), off.getY()));

        g2d.setTransform(objToWorld);
        g2d.setColor(Color.RED);
        g2d.drawRect((int) (-radius * zoomD),(int) (-radius * zoomD) , (int) ((radius * zoomD) * 2), (int) ((radius * zoomD) * 2));
    }

}

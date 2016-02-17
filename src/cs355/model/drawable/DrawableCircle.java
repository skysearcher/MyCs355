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

    public DrawableCircle(Shape givenShape) {
        innerCircle = givenShape;
        radius = ((Circle) givenShape).getRadius();
        objToWorld = new AffineTransform();
        objToWorld.translate(innerCircle.getCenter().getX(), innerCircle.getCenter().getY());
    }

    @Override
    public void onDraw(Graphics2D g2d, AffineTransform zoom) {
        objToWorld.concatenate(zoom);
        g2d.setTransform(objToWorld);
        g2d.setColor(innerCircle.getColor());
        g2d.fillOval((int) (-radius),(int) (-radius) , (int) (radius * 2), (int) (radius * 2));
    }

    @Override
    public void drawSelection(Graphics2D g2d) {
        g2d.setTransform(objToWorld);
        g2d.setColor(Color.RED);
        g2d.drawRect((int) (-radius),(int) (-radius) , (int) (radius * 2), (int) (radius * 2));
    }

}

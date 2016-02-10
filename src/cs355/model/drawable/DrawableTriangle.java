package cs355.model.drawable;

import cs355.model.drawing.Shape;
import cs355.model.drawing.Triangle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by Joshua on 1/20/2016.
 */
public class DrawableTriangle implements DrawableShape{
    private Shape innerTriangle;
    private Point2D.Double a;
    private Point2D.Double b;
    private Point2D.Double c;
    private Polygon p;
    AffineTransform objToWorld = new AffineTransform();

    public DrawableTriangle(Shape givenTriangle){
        innerTriangle = givenTriangle;
        objToWorld = new AffineTransform();
        a = ((Triangle)givenTriangle).getA();
        b = ((Triangle)givenTriangle).getB();
        c = ((Triangle)givenTriangle).getC();
        p = new Polygon();
        p.addPoint((int)a.getX(), (int)a.getY());
        p.addPoint((int)b.getX(), (int)b.getY());
        p.addPoint((int)c.getX(), (int)c.getY());
        objToWorld.translate(innerTriangle.getCenter().getX(), innerTriangle.getCenter().getY());
        objToWorld.rotate(innerTriangle.getRotation());
    }

    @Override
    public void onDraw(Graphics2D g2d) {
        g2d.setTransform(objToWorld);
        g2d.setColor(innerTriangle.getColor());
        g2d.fillPolygon(p);
    }

    @Override
    public void drawSelection(Graphics2D g2d) {
        g2d.setTransform(objToWorld);
        g2d.setColor(Color.RED);
        g2d.drawOval(0, ((int)-p.getBounds().getHeight()/2), 10, 10);
        g2d.drawPolygon(p);
    }
}

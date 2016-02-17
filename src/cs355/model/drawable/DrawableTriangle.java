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
    AffineTransform objToWorld;
    private double zoomD;

    public DrawableTriangle(Shape givenTriangle){
        innerTriangle = givenTriangle;
        a = ((Triangle)givenTriangle).getA();
        b = ((Triangle)givenTriangle).getB();
        c = ((Triangle)givenTriangle).getC();
        p = new Polygon();
        p.addPoint((int)a.getX(), (int)a.getY());
        p.addPoint((int)b.getX(), (int)b.getY());
        p.addPoint((int)c.getX(), (int)c.getY());

    }

    @Override
    public void onDraw(Graphics2D g2d, AffineTransform zoom) {
        zoomD = zoom.getScaleX();
        objToWorld = new AffineTransform();
        objToWorld.translate(innerTriangle.getCenter().getX(), innerTriangle.getCenter().getY());
        objToWorld.rotate(innerTriangle.getRotation());
        objToWorld.concatenate(zoom);
        g2d.setTransform(objToWorld);
        g2d.setColor(innerTriangle.getColor());
        g2d.fillPolygon(p);
    }

    @Override
    public void drawSelection(Graphics2D g2d) {
        objToWorld = new AffineTransform();
        objToWorld.translate(innerTriangle.getCenter().getX(), innerTriangle.getCenter().getY());
        objToWorld.rotate(innerTriangle.getRotation());
        g2d.setTransform(objToWorld);
        g2d.setColor(Color.RED);
        g2d.drawOval(0, ((int)(-p.getBounds().getHeight()*zoomD)/2), 10, 10);
        g2d.drawPolygon(p);
    }
}

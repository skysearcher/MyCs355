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
    AffineTransform objToWorld = new AffineTransform();

    public DrawableTriangle(Shape givenTriangle){
        innerTriangle = givenTriangle;
        objToWorld = new AffineTransform();
        objToWorld.translate(innerTriangle.getCenter().getX(), innerTriangle.getCenter().getY());
        a = ((Triangle)givenTriangle).getA();
        b = ((Triangle)givenTriangle).getB();
        c = ((Triangle)givenTriangle).getC();
    }

    @Override
    public void onDraw(Graphics2D g2d) {
        g2d.setTransform(objToWorld);
        g2d.setColor(innerTriangle.getColor());
        Polygon p = new Polygon();
        p.addPoint((int)a.getX(), (int)a.getY());
        p.addPoint((int)b.getX(), (int)b.getY());
        p.addPoint((int)c.getX(), (int)c.getY());
        g2d.fillPolygon(p);
//        drawSelection(g2d);
    }

    @Override
    public void drawSelection(Graphics2D g2d) {
        g2d.setTransform(objToWorld);
        g2d.setColor(Color.RED);
        Polygon p = new Polygon();
        p.addPoint((int)a.getX(), (int)a.getY());
        p.addPoint((int)b.getX(), (int)b.getY());
        p.addPoint((int)c.getX(), (int)c.getY());
        g2d.drawOval((-5), ((int)(p.getBounds().getY()) - 20) , 10, 10);
        g2d.drawPolygon(p);
    }
}

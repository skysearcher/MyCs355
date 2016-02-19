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
    private Point2D.Double a2;
    private Point2D.Double b2;
    private Point2D.Double c2;
    private Polygon p;
    AffineTransform objToWorld;
    private double zoomD;
    private AffineTransform zoomTrans;
    private AffineTransform objToView;
    private Point2D.Double off;

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
    public void onDraw(Graphics2D g2d, AffineTransform worldToView) {
        zoomTrans = new AffineTransform(worldToView);
        zoomD = worldToView.getScaleX();
        objToWorld = new AffineTransform();
        objToWorld.concatenate(new AffineTransform(1, 0, 0, 1, innerTriangle.getCenter().getX(), innerTriangle.getCenter().getY()));
        objToWorld.concatenate(new AffineTransform(Math.cos(innerTriangle.getRotation()),Math.sin(innerTriangle.getRotation()), Math.sin(-innerTriangle.getRotation()),Math.cos(innerTriangle.getRotation()), 0, 0));


        objToView = new AffineTransform();

        objToView.concatenate(worldToView);
        objToView.concatenate(objToWorld);



        g2d.setTransform(objToView);
        g2d.setColor(innerTriangle.getColor());
        g2d.fillPolygon(p);
    }

    @Override
    public void drawSelection(Graphics2D g2d) {
        objToWorld = new AffineTransform();
        off = new Point2D.Double(innerTriangle.getCenter().getX(), innerTriangle.getCenter().getY());
        zoomTrans.transform(off, off);
        objToWorld.concatenate(new AffineTransform(1, 0, 0, 1, off.getX(), off.getY()));
        objToWorld.concatenate(new AffineTransform(Math.cos(innerTriangle.getRotation()),Math.sin(innerTriangle.getRotation()), Math.sin(-innerTriangle.getRotation()),Math.cos(innerTriangle.getRotation()), 0, 0));

        g2d.setTransform(objToWorld);
        g2d.setColor(Color.RED);
        g2d.drawOval(0, (int)((((-p.getBounds().getHeight())/2) * zoomD) - 20* zoomD), 10, 10);

        a2 = new Point2D.Double(a.getX() * zoomD, a.getY() * zoomD);
        b2 = new Point2D.Double(b.getX() * zoomD, b.getY() * zoomD);
        c2 = new Point2D.Double(c.getX() * zoomD, c.getY() * zoomD);
        Polygon tp = new Polygon();
        tp.addPoint((int)a2.getX(), (int)a2.getY());
        tp.addPoint((int)b2.getX(), (int)b2.getY());
        tp.addPoint((int)c2.getX(), (int)c2.getY());
        g2d.drawPolygon(tp);
    }
}

package cs355.model.drawable;

import cs355.model.drawing.Ellipse;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by Joshua on 1/20/2016.
 */
public class DrawableEllipse implements DrawableShape{
    private Shape innerEllipse;
    private double width;
    private double height;
    AffineTransform objToWorld;
    private double zoomD;
    private AffineTransform zoomTrans;
    private AffineTransform objToView;
    private Point2D.Double off;

    public DrawableEllipse(Shape givenShape){
        innerEllipse = givenShape;
        width = ((Ellipse)innerEllipse).getWidth();
        height = ((Ellipse)innerEllipse).getHeight();

    }

    @Override
    public void onDraw(Graphics2D g2d, AffineTransform worldToView) {
        zoomTrans = new AffineTransform(worldToView);
        zoomD = worldToView.getScaleX();
        objToWorld = new AffineTransform();
        objToWorld.concatenate(new AffineTransform(1, 0, 0, 1, innerEllipse.getCenter().getX(), innerEllipse.getCenter().getY()));
        objToWorld.concatenate(new AffineTransform(Math.cos(innerEllipse.getRotation()),Math.sin(innerEllipse.getRotation()), Math.sin(-innerEllipse.getRotation()),Math.cos(innerEllipse.getRotation()), 0, 0));

        objToView = new AffineTransform();

        objToView.concatenate(worldToView);
        objToView.concatenate(objToWorld);



        g2d.setTransform(objToView);
        g2d.setColor(innerEllipse.getColor());
        g2d.fillOval((int)(- width/2), (int)(- height/2), (int)(width), (int)(height));
//        drawSelection(g2d);
    }

    @Override
    public void drawSelection(Graphics2D g2d) {
        objToWorld = new AffineTransform();
        off = new Point2D.Double(innerEllipse.getCenter().getX(), innerEllipse.getCenter().getY());
        zoomTrans.transform(off, off);
        objToWorld.concatenate(new AffineTransform(1, 0, 0, 1, off.getX(), off.getY()));
        objToWorld.concatenate(new AffineTransform(Math.cos(innerEllipse.getRotation()),Math.sin(innerEllipse.getRotation()), Math.sin(-innerEllipse.getRotation()),Math.cos(innerEllipse.getRotation()), 0, 0));

        g2d.setTransform(objToWorld);
        g2d.setColor(Color.RED);
        g2d.drawOval((-5), (int)(-((height*zoomD)/2) - 20) , 10, 10);
        g2d.drawRect((int) ((-width*zoomD - 1)/2),(int) (((-height * zoomD - 1)/2)) , (int) ((width*zoomD)), (int) (height * zoomD));
    }
}

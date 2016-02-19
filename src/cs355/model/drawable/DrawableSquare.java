package cs355.model.drawable;

import cs355.model.drawing.Shape;
import cs355.model.drawing.Square;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by Joshua on 1/18/2016.
 */
public class DrawableSquare implements DrawableShape{
    private Shape innerSquare;
    private double height;
    private double width;
    AffineTransform objToWorld;
    private double zoomD;
    private AffineTransform zoomTrans;
    private AffineTransform objToView;
    private Point2D.Double off;

    public DrawableSquare(Shape givenShape){
        innerSquare = givenShape;
        height = ((Square)innerSquare).getSize();
        width = ((Square)innerSquare).getSize();
        off = new Point2D.Double();
    }

    @Override
    public void onDraw(Graphics2D g2d, AffineTransform worldToView) {
        zoomTrans = new AffineTransform(worldToView);
        zoomD = worldToView.getScaleX();
        objToWorld = new AffineTransform();
        objToWorld.concatenate(new AffineTransform(1, 0, 0, 1, innerSquare.getCenter().getX(), innerSquare.getCenter().getY()));
        objToWorld.concatenate(new AffineTransform(Math.cos(innerSquare.getRotation()),Math.sin(innerSquare.getRotation()), Math.sin(-innerSquare.getRotation()),Math.cos(innerSquare.getRotation()), 0, 0));


        objToView = new AffineTransform();

        objToView.concatenate(worldToView);
        objToView.concatenate(objToWorld);



        g2d.setTransform(objToView);

        g2d.setColor(innerSquare.getColor());
        g2d.fillRect((int)(-width/2),(int)(-height/2), (int)width, (int)height);
    }

    @Override
    public void drawSelection(Graphics2D g2d) {
        objToWorld = new AffineTransform();
        off = new Point2D.Double(innerSquare.getCenter().getX(), innerSquare.getCenter().getY());
        zoomTrans.transform(off, off);
        objToWorld.concatenate(new AffineTransform(1, 0, 0, 1, off.getX(), off.getY()));
        objToWorld.concatenate(new AffineTransform(Math.cos(innerSquare.getRotation()),Math.sin(innerSquare.getRotation()), Math.sin(-innerSquare.getRotation()),Math.cos(innerSquare.getRotation()), 0, 0));

        g2d.setTransform(objToWorld);
        g2d.setColor(Color.RED);
        g2d.drawOval((-5),(int) (((-height*zoomD)/2) - 20) , 10, 10);
        g2d.drawRect((int) ((-width*zoomD - 1)/2),(int) (((-height * zoomD - 1)/2)) , (int) ((width*zoomD)), (int) (height * zoomD));
    }
}

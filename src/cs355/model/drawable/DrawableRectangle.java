package cs355.model.drawable;

import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by Joshua on 1/19/2016.
 */
public class DrawableRectangle implements DrawableShape {
    private Shape innerRectangle;
    private int height;
    private int width;
    AffineTransform objToWorld;
    private double zoomD;
    private AffineTransform zoomTrans;
    private AffineTransform objToView;
    private Point2D.Double off;

    public DrawableRectangle(Shape givenShape) {
        innerRectangle = givenShape;
        height = (int) ((Rectangle) innerRectangle).getHeight();
        width = (int) ((Rectangle) innerRectangle).getWidth();
    }

    @Override
    public void onDraw(Graphics2D g2d, AffineTransform worldToView) {
        zoomTrans = new AffineTransform(worldToView);
        zoomD = worldToView.getScaleX();
        objToWorld = new AffineTransform();
        objToWorld.concatenate(new AffineTransform(1, 0, 0, 1, innerRectangle.getCenter().getX(), innerRectangle.getCenter().getY()));
        objToWorld.concatenate(new AffineTransform(Math.cos(innerRectangle.getRotation()),Math.sin(innerRectangle.getRotation()), Math.sin(-innerRectangle.getRotation()),Math.cos(innerRectangle.getRotation()), 0, 0));


        objToView = new AffineTransform();

        objToView.concatenate(worldToView);
        objToView.concatenate(objToWorld);



        g2d.setTransform(objToView);
        g2d.setColor(innerRectangle.getColor());
        g2d.fillRect(-width / 2, -height / 2, width, height);
//        drawSelection(g2d);
    }

    @Override
    public void drawSelection(Graphics2D g2d) {
        objToWorld = new AffineTransform();
        off = new Point2D.Double(innerRectangle.getCenter().getX(), innerRectangle.getCenter().getY());
        zoomTrans.transform(off, off);
        objToWorld.concatenate(new AffineTransform(1, 0, 0, 1, off.getX(), off.getY()));
        objToWorld.concatenate(new AffineTransform(Math.cos(innerRectangle.getRotation()),Math.sin(innerRectangle.getRotation()), Math.sin(-innerRectangle.getRotation()),Math.cos(innerRectangle.getRotation()), 0, 0));

        g2d.setTransform(objToWorld);
        g2d.setColor(Color.RED);
        g2d.drawOval((-5), (int)(-(height*zoomD / 2) - 20), 10, 10);
        g2d.drawRect((int) ((-width*zoomD - 1)/2),(int) (((-height * zoomD - 1)/2)) , (int) ((width*zoomD)), (int) (height * zoomD));
    }
}

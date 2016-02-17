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

    public DrawableEllipse(Shape givenShape){
        innerEllipse = givenShape;
        width = ((Ellipse)innerEllipse).getWidth();
        height = ((Ellipse)innerEllipse).getHeight();

    }

    @Override
    public void onDraw(Graphics2D g2d, AffineTransform zoom) {
        zoomD = zoom.getScaleX();
        objToWorld = new AffineTransform();
        objToWorld.translate(innerEllipse.getCenter().getX(), innerEllipse.getCenter().getY());
        objToWorld.rotate(innerEllipse.getRotation());
        objToWorld.concatenate(zoom);
        g2d.setTransform(objToWorld);
        g2d.setColor(innerEllipse.getColor());
        g2d.fillOval((int)(- width/2), (int)(- height/2), (int)(width), (int)(height));
//        drawSelection(g2d);
    }

    @Override
    public void drawSelection(Graphics2D g2d) {
        objToWorld = new AffineTransform();
        objToWorld.translate(innerEllipse.getCenter().getX(), innerEllipse.getCenter().getY());
        objToWorld.rotate(innerEllipse.getRotation());
        g2d.setTransform(objToWorld);
        g2d.setColor(Color.RED);
        g2d.drawOval((-5), (int)(-((height*zoomD)/2) - 20) , 10, 10);
        g2d.drawRect((int) ((-width*zoomD - 1)/2),(int) (((-height * zoomD - 1)/2)) , (int) ((width*zoomD)), (int) (height * zoomD));
    }
}

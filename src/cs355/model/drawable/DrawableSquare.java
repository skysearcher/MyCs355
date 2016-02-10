package cs355.model.drawable;

import cs355.model.drawing.Shape;
import cs355.model.drawing.Square;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by Joshua on 1/18/2016.
 */
public class DrawableSquare implements DrawableShape{
    private Shape innerSquare;
    private double height;
    private double width;
    AffineTransform objToWorld;

    public DrawableSquare(Shape givenShape){
        innerSquare = givenShape;
        height = ((Square)innerSquare).getSize();
        width = ((Square)innerSquare).getSize();
        objToWorld = new AffineTransform();
        objToWorld.translate(innerSquare.getCenter().getX(), innerSquare.getCenter().getY());
        objToWorld.rotate(innerSquare.getRotation());
    }

    @Override
    public void onDraw(Graphics2D g2d) {
        g2d.setTransform(objToWorld);
        g2d.setColor(innerSquare.getColor());
        g2d.fillRect((int)(-width/2),(int)(-height/2), (int)width, (int)height);
    }

    @Override
    public void drawSelection(Graphics2D g2d) {
        g2d.setTransform(objToWorld);
        g2d.setColor(Color.RED);
        g2d.drawOval((-5),(int) (-(height/2) - 20) , 10, 10);
        g2d.drawRect((int) (-width/2),(int) (-height/2) , (int) (width), (int) (height));
    }
}

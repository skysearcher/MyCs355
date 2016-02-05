package cs355.model.drawable;

import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by Joshua on 1/19/2016.
 */
public class DrawableRectangle implements DrawableShape {
    private Shape innerRectangle;
    private int height;
    private int width;
    AffineTransform objToWorld;

    public DrawableRectangle(Shape givenShape){
        innerRectangle = givenShape;
        height = (int)((Rectangle)innerRectangle).getHeight();
        width = (int)((Rectangle)innerRectangle).getWidth();
        objToWorld = new AffineTransform();
        objToWorld.translate(innerRectangle.getCenter().getX(), innerRectangle.getCenter().getY());
    }

    @Override
    public void onDraw(Graphics2D g2d) {

        g2d.setTransform(objToWorld);
        g2d.setColor(innerRectangle.getColor());
        g2d.fillRect(-width/2, -height/2, width, height);
//        drawSelection(g2d);
    }

    @Override
    public void drawSelection(Graphics2D g2d) {
        g2d.setTransform(objToWorld);
        g2d.setColor(Color.RED);
        g2d.drawOval((-5), (-(height/2) - 20) , 10, 10);
        g2d.drawRect((-width/2),(-height/2) , (width), (height));
    }
}

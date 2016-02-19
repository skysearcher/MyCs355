package cs355.model.drawable;

import cs355.model.drawing.Line;
import cs355.model.drawing.Shape;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by Joshua on 1/18/2016.
 */
public class DrawableLine implements DrawableShape {
    private Shape innerLine;
    private int endX;
    private int endY;
    AffineTransform objToWorld;
    private double zoomD;
    private AffineTransform zoomTrans;
    private AffineTransform objToView;
    private Point2D.Double off;

    public DrawableLine(Shape givenShape){
        innerLine = givenShape;
        endX = (int)((Line)innerLine).getEnd().getX();
        endY = (int)((Line)innerLine).getEnd().getY();


    }

    @Override
    public void onDraw(Graphics2D g2d, AffineTransform worldToView) {
        zoomTrans = new AffineTransform(worldToView);
        zoomD = worldToView.getScaleX();
        objToWorld = new AffineTransform();
        objToWorld.concatenate(new AffineTransform(1, 0, 0, 1, innerLine.getCenter().getX(), innerLine.getCenter().getY()));

        objToView = new AffineTransform();

        objToView.concatenate(worldToView);
        objToView.concatenate(objToWorld);



        g2d.setTransform(objToView);
        g2d.setColor(innerLine.getColor());
        g2d.drawLine(0, 0, endX, endY);
//        drawSelection(g2d);
    }

    @Override
    public void drawSelection(Graphics2D g2d) {
        objToWorld = new AffineTransform();
        off = new Point2D.Double(innerLine.getCenter().getX(), innerLine.getCenter().getY());
        zoomTrans.transform(off, off);
        objToWorld.concatenate(new AffineTransform(1, 0, 0, 1, off.getX(), off.getY()));

        g2d.setTransform(objToWorld);
        g2d.setColor(Color.RED);
        g2d.drawOval(-5 ,(-5) , 10, 10);
        g2d.drawOval((int)(endX*zoomD - 5),(int)(endY*zoomD - 5) , 10, 10);
    }
}

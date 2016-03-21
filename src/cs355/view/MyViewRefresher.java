package cs355.view;

import cs355.GUIFunctions;
import cs355.controller.CameraPos;
import cs355.matrixmath.MatrixMathMod;
import cs355.model.MyModel;
import cs355.model.drawable.DrawableShape;
import cs355.model.drawable.ShapeConverter;
import cs355.model.drawing.Line;
import cs355.model.drawing.Shape;
import cs355.model.scene.CS355Scene;
import cs355.model.scene.Instance;
import cs355.model.scene.Line3D;
import cs355.model.scene.WireFrame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Joshua on 1/18/2016.
 */
public class MyViewRefresher implements ViewRefresher {
    private int start;
    private MyModel myMod;
    private List<Shape> shapes;
    private List<DrawableShape> drawShapes;
    private int amDrawing;
    private DrawableShape shapeDrawing;
    private ShapeConverter shaper;
    private int selectedShape;
    private AffineTransform zoom;
    private boolean threeDing;
    private CameraPos myCamera;
    private CS355Scene myScene;
    private MatrixMathMod myMatrix;
    private MatrixMathMod conMatrix;
    private MatrixMathMod objToWorld;
    private double zoomFactor;


    public MyViewRefresher(MyModel givenModel) {
        myMod = givenModel;
        drawShapes = new ArrayList<>();
        shaper = new ShapeConverter();
        amDrawing = 0;
        start = 0;
        selectedShape = -1;
        zoom = new AffineTransform();
        myMatrix = new MatrixMathMod();
        conMatrix = new MatrixMathMod();
        objToWorld = new MatrixMathMod();
    }

    public CS355Scene getMyScene() {
        return myScene;
    }

    public void setMyScene(CS355Scene myScene) {
        this.myScene = myScene;
    }

    public CameraPos getMyCamera() {
        return myCamera;
    }

    public void setMyCamera(CameraPos myCamera) {
        this.myCamera = myCamera;
    }

    public boolean isThreeDing() {
        return threeDing;
    }

    public void setThreeDing(boolean threeDing) {
        this.threeDing = threeDing;
    }

    public void updateMatrix() {


        myMatrix.setAsTranslation(myCamera.getMyPos());
        conMatrix.setAsRotation(myCamera.getRotation());
        myMatrix.concateMatrix(conMatrix.getMatrix());
        conMatrix.setAsClipMatrix(myCamera.getFov(), myCamera.getNear(), myCamera.getFar());
        myMatrix.concateMatrix(conMatrix.getMatrix());
        zoomFactor = zoom.getScaleX();


//        myMatrix.setAsRotation(myCamera.getRotation());
//        conMatrix.setAsTranslation(myCamera.getMyPos());
//        myMatrix.concateMatrix(conMatrix.getMatrix());
//        conMatrix.setAsClipMatrix(myCamera.getFov(), myCamera.getNear(), myCamera.getFar());
//        myMatrix.concateMatrix(conMatrix.getMatrix());
    }

    public AffineTransform getZoom() {
        return zoom;
    }

    public void setZoom(AffineTransform zoom) {
        this.zoom = zoom;
    }

    public void setSelected(int given) {
        selectedShape = given;
    }

    public void unSelect() {
        selectedShape = -1;
    }

    public void setDrawing(Shape givenShape) {
        shapeDrawing = shaper.convert(givenShape);
        amDrawing = 1;
    }

    public void endDrawing() {
        amDrawing = 0;
    }

    @Override
    public void refreshView(Graphics2D g2d) {
        start++;
        for (int i = 0; i < drawShapes.size(); i++) {
            drawShapes.get(i).onDraw(g2d, zoom);
        }
        if (selectedShape > -1) {
            drawShapes.get(selectedShape).drawSelection(g2d);
        }
        //GUIFunctions.printf("Refresh " + start,0);
        //Draw list of Drawables
        if (amDrawing == 1) {   //are you drawing?
            shapeDrawing.onDraw(g2d, zoom);
        }
        g2d.setTransform(zoom);
        //TODO finish the drawing
        if (threeDing) {
            List<Instance> instances = myScene.instances();
            Instance curInstance;
            for (int i = 0; i < instances.size(); i++) {
                curInstance = instances.get(i);
                WireFrame frame = curInstance.getModel();
                List<Line3D> lines = frame.getLines();
                Line3D curLine;
                double[] myPointOne = new double[4];
                double[] myPointTwo = new double[4];
                Point2D.Double pointOne;
                Point2D.Double pointTwo;
                g2d.setPaint(curInstance.getColor());
                objToWorld.setAsRotation(Math.toRadians(curInstance.getRotAngle()));
                conMatrix.setAsTranslation(curInstance.getPosition());
                objToWorld.concateMatrix(conMatrix.getMatrix());
                for (int j = 0; j < lines.size(); j++) {
                    curLine = lines.get(j);
                    myPointOne[0] = curLine.start.x;
                    myPointOne[1] = curLine.start.y;
                    myPointOne[2] = curLine.start.z;
                    myPointOne[3] = 1;
                    myPointTwo[0] = curLine.end.x;
                    myPointTwo[1] = curLine.end.y;
                    myPointTwo[2] = curLine.end.z;
                    myPointTwo[3] = 1;
                    myPointOne = objToWorld.multiplyVec(myPointOne);
                    myPointTwo = objToWorld.multiplyVec(myPointTwo);
                    myPointOne = myMatrix.multiplyVec(myPointOne);
                    myPointTwo = myMatrix.multiplyVec(myPointTwo);
                    if (myMatrix.passClipTest(myPointOne, myPointTwo)) {
                        myPointOne = myMatrix.homogenDiv(myPointOne);
                        myPointTwo = myMatrix.homogenDiv(myPointTwo);
                        pointOne = myMatrix.toScreen(myPointOne);
                        pointTwo = myMatrix.toScreen(myPointTwo);
//                        g2d.drawLine((int) (zoom.getTranslateX() + (pointOne.x * zoomFactor)), (int) (zoom.getTranslateY() + (pointOne.y * zoomFactor)), (int) (zoom.getTranslateX() + (pointTwo.x * zoomFactor)), (int) (zoom.getTranslateY() + (pointTwo.y * zoomFactor)));
                        g2d.drawLine((int)(pointOne.x), (int) (pointOne.y), (int)(pointTwo.x), (int)(pointTwo.y));

                    }
                }
            }
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        drawShapes.clear();
        shapes = myMod.getShapes();
        for (Shape s : shapes) {
            drawShapes.add(shaper.convert(s));
        }
        GUIFunctions.refresh();
    }
}

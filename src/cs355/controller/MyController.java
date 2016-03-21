package cs355.controller;

import cs355.GUIFunctions;
import cs355.model.MyModel;
import cs355.model.drawable.RectangleEllipse;
import cs355.model.drawable.SquareCircle;
import cs355.model.drawing.*;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;
import cs355.model.scene.CS355Scene;
import cs355.view.MyViewRefresher;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.Iterator;

/**
 * Created by Joshua on 1/18/2016.
 */
public class MyController implements CS355Controller{
    private MyModel myMod;
    private TShapeEnum drawShape;
    private Shape myShape;
    private int triangleClick;
    private Color drawColor;
    private MyViewRefresher myView;
    private Point2D.Double startPress;
    private Point2D.Double squareLeft;
    private double width;
    private double height;
    private SquareCircle circConvert;
    private RectangleEllipse ellipseConvert;
    private Point2D.Double one;
    private Point2D.Double two;
    private Point2D.Double three;
    private int selectedIndex;
    private SelectPoint whatSelected;
    private double rotation;
    private double tolerance;
    private double zoom;
    private double horizontalPos;
    private double verticalPos;
    private AffineTransform viewToWorld;
    private int screenSize;
    private AffineTransform worldToView;
    private CS355Scene myScene;
    private CameraPos myCamera;
    private double moveFactor;
    private double rotateFactor;
    private boolean threeDing;


    public MyController(MyModel givenModel, MyViewRefresher theView){
        screenSize = 2048;
        myView = theView;
        myMod = givenModel;
        drawShape = TShapeEnum.NONE;
        triangleClick = 0;
        drawColor = Color.white;
        startPress = new Point2D.Double(0, 0);
        squareLeft = new Point2D.Double(0, 0);
        circConvert = new SquareCircle();
        ellipseConvert = new RectangleEllipse();
        myScene = new CS355Scene();
        selectedIndex = -1;
        whatSelected = SelectPoint.None;
        tolerance = 8;
        zoom = 100;
        horizontalPos = 0;
        verticalPos = 0;
        setAffine();
        myView.setZoom(worldToView);
        myCamera = new CameraPos();
        moveFactor = 1.0;
        rotateFactor = 0.1;
        threeDing = false;
        myView.setMyCamera(myCamera);
        myView.setMyScene(myScene);
        myCamera.setFov(Math.toRadians(60));
        myCamera.setFar(1000.0);
        myCamera.setNear(0.001);
    }

    public void setAffine(){
        viewToWorld = new AffineTransform();
        viewToWorld.concatenate(new AffineTransform(1, 0, 0, 1, horizontalPos, verticalPos));//translate
        viewToWorld.concatenate(new AffineTransform(1/(zoom/100), 0, 0, 1/(zoom/100), 0, 0));//Scale
        worldToView = new AffineTransform();
        worldToView.concatenate(new AffineTransform(zoom/100, 0, 0, zoom/100, 0, 0));//Inverse scale
        worldToView.concatenate(new AffineTransform(1, 0, 0, 1, -horizontalPos, -verticalPos));//Inverse translate
    }

    @Override
    public void colorButtonHit(Color c) {
        if(c != null){
            drawColor = c;
            GUIFunctions.changeSelectedColor(c);
        }
        if(selectedIndex != -1){
            myMod.getShape(selectedIndex).setColor(c);
            myView.setSelected(selectedIndex);
            myMod.notifyChanged();
        }
    }

    @Override
    public void lineButtonHit() {
        drawShape = TShapeEnum.LINE;
        selectedIndex = -1;
        whatSelected = SelectPoint.None;
        myView.setSelected(selectedIndex);
        myMod.notifyChanged();
        GUIFunctions.refresh();
    }

    @Override
    public void squareButtonHit() {
        drawShape = TShapeEnum.SQUARE;
        selectedIndex = -1;
        whatSelected = SelectPoint.None;
        myView.setSelected(selectedIndex);
        myMod.notifyChanged();
        GUIFunctions.refresh();
    }

    @Override
    public void rectangleButtonHit() {
        drawShape = TShapeEnum.RECTANGLE;
        selectedIndex = -1;
        whatSelected = SelectPoint.None;
        myView.setSelected(selectedIndex);
        myMod.notifyChanged();
        GUIFunctions.refresh();
    }

    @Override
    public void circleButtonHit() {
        drawShape = TShapeEnum.CIRCLE;
        selectedIndex = -1;
        whatSelected = SelectPoint.None;
        myView.setSelected(selectedIndex);
        myMod.notifyChanged();
        GUIFunctions.refresh();
    }

    @Override
    public void ellipseButtonHit() {
        drawShape = TShapeEnum.ELLIPSE;
        selectedIndex = -1;
        whatSelected = SelectPoint.None;
        myView.setSelected(selectedIndex);
        myMod.notifyChanged();
        GUIFunctions.refresh();
    }

    @Override
    public void triangleButtonHit() {
        triangleClick = 0;
        drawShape = TShapeEnum.TRIANGLE;
        selectedIndex = -1;
        whatSelected = SelectPoint.None;
        myView.setSelected(selectedIndex);
        myMod.notifyChanged();
        GUIFunctions.refresh();
    }

    @Override
    public void selectButtonHit() {
        drawShape = TShapeEnum.SELECT;
        selectedIndex = -1;
        whatSelected = SelectPoint.None;
        myView.setSelected(selectedIndex);
        myMod.notifyChanged();
        GUIFunctions.refresh();
    }

    public void setBars(int xThick, int xPos, int yThick, int yPos){
        GUIFunctions.setHScrollBarPosit(xPos);
        GUIFunctions.setHScrollBarKnob(xThick);
        GUIFunctions.setVScrollBarPosit(yPos);
        GUIFunctions.setVScrollBarKnob(yThick);
    }

    public void setBarsTwo(int xThick, int xPos, int yThick, int yPos){
        GUIFunctions.setHScrollBarKnob(xThick);
        GUIFunctions.setHScrollBarPosit(xPos);
        GUIFunctions.setVScrollBarKnob(yThick);
        GUIFunctions.setVScrollBarPosit(yPos);
    }

    @Override
    public void zoomInButtonHit() {
        boolean changed = false;
        if(zoom != 400){
            zoom = zoom * 2;
            setAffine();
            myView.setZoom(worldToView);
            GUIFunctions.setZoomText((zoom)/100);
            GUIFunctions.printf("Zoom In stuck " + zoom, 0);
            GUIFunctions.refresh();
            changed = true;
        }
        switch ((int)zoom){
            case 25:
                setBars(screenSize, 0, screenSize, 0);
                break;
            case 50:
                horizontalPos += screenSize/4;
                verticalPos += screenSize/4;
                setBarsTwo(screenSize/2, (int)horizontalPos, screenSize/2, (int)verticalPos);
                break;
            case 100:
                horizontalPos += screenSize/8;
                verticalPos += screenSize/8;
                setBars(screenSize/4, (int)horizontalPos, screenSize/4, (int)verticalPos);
                break;
            case 200:
                horizontalPos += screenSize/16;
                verticalPos += screenSize/16;
                setBars(screenSize/8, (int)horizontalPos, screenSize/8, (int)verticalPos);
                break;
            case 400:
                if(changed){
                    horizontalPos += screenSize/32;
                    verticalPos += screenSize/32;
                }
                setBars(screenSize/16, (int)horizontalPos, screenSize/16, (int)verticalPos);
                break;
        }
    }

    @Override
    public void zoomOutButtonHit() {
        boolean changed = false;
        if(zoom != 25){
            zoom = zoom / 2;
            setAffine();
            myView.setZoom(worldToView);
            GUIFunctions.setZoomText((zoom)/100);
            GUIFunctions.printf("Zoom Out stuck " + zoom, 0);
            GUIFunctions.refresh();
            changed = true;
        }
        switch ((int)zoom){
            case 25:
                setBars(screenSize, 0, screenSize, 0);
                break;
            case 50:
                horizontalPos -= screenSize/8;
                verticalPos -= screenSize/8;
                if(verticalPos + screenSize/4 > 2048){
                    verticalPos = 2048 - (screenSize/4);
                }
                if(horizontalPos + screenSize/4 > 2048){
                    horizontalPos = 2048 - (screenSize/4);
                }
                setBars(screenSize/2, (int)horizontalPos, screenSize/2, (int)verticalPos);
                break;
            case 100:
                horizontalPos -= screenSize/16;
                verticalPos -= screenSize/16;
                if(verticalPos + screenSize/8 > 2048){
                    verticalPos = 2048 - (screenSize/8);
                }
                if(horizontalPos + screenSize/8 > 2048){
                    horizontalPos = 2048 - (screenSize/8);
                }
                setBars(screenSize/4, (int)horizontalPos, screenSize/4, (int)verticalPos);
                break;
            case 200:
                horizontalPos -= screenSize/32;
                verticalPos -= screenSize/32;
                if(verticalPos + screenSize/16 > 2048){
                    verticalPos = 2048 - (screenSize/8);
                }
                if(horizontalPos + screenSize/16 > 2048){
                    horizontalPos = 2048 - (screenSize/16);
                }
                setBars(screenSize/8, (int)horizontalPos, screenSize/8, (int)verticalPos);
                break;
            case 400:
                if(changed){
                    horizontalPos -= screenSize/64;
                    verticalPos -= screenSize/64;
                }
                if(verticalPos + screenSize/32 > 2048){
                    verticalPos = 2048 - (screenSize/32);
                }
                if(horizontalPos + screenSize/32 > 2048){
                    horizontalPos = 2048 - (screenSize/32);
                }
                setBars(screenSize/16, (int)horizontalPos, screenSize/16, (int)verticalPos);
                break;
        }
    }

    @Override
    public void hScrollbarChanged(int value) {
        GUIFunctions.printf("H Changed " + value, 0);
        horizontalPos = value;
        setAffine();
        myView.setZoom(worldToView);
        GUIFunctions.refresh();
    }

    @Override
    public void vScrollbarChanged(int value) {
        GUIFunctions.printf("V Changed " + value, 0);
        verticalPos = value;
        setAffine();
        myView.setZoom(worldToView);
        GUIFunctions.refresh();
    }

    @Override
    public void openScene(File file) {
        myScene.open(file);
        myCamera.setMyPos(myScene.getCameraPosition());
        myCamera.setRotation(myScene.getCameraRotation());
        myView.updateMatrix();
        GUIFunctions.refresh();
    }

    @Override
    public void toggle3DModelDisplay() {
        myView.updateMatrix();
        if(threeDing){
            threeDing = false;
        }else{
            threeDing = true;
        }
        myView.setThreeDing(threeDing);
        myView.updateMatrix();
        GUIFunctions.refresh();
        System.out.println("Pressed 3D Model " + threeDing);
    }

    @Override
    public void keyPressed(Iterator<Integer> iterator) {
        int test = 0;
        /**TODO BUTTONS!!!
         * A = 65
         * S = 83
         * W = 87
         * D = 68
         * Q = 81
         * E = 69
         * R = 82
         * F = 70
         */
//        System.out.println(iterator.next());
        while (iterator.hasNext()){
            test = iterator.next();
            if(threeDing){
                double xAdd;
                double zAdd;
                switch (test){
                    case 65:    //A
                        xAdd = (moveFactor) * Math.cos(myCamera.getRotation());
                        zAdd = (moveFactor) * Math.sin(myCamera.getRotation());
                        myCamera.getMyPos().x += xAdd;
                        myCamera.getMyPos().z -= zAdd;
                        break;
                    case 83:    //S
                        xAdd = (moveFactor) * Math.sin(myCamera.getRotation());
                        zAdd = (moveFactor) * Math.cos(myCamera.getRotation());
                        myCamera.getMyPos().x += xAdd;
                        myCamera.getMyPos().z += zAdd;
                        break;
                    case 87:    //W
                        xAdd = (moveFactor) * Math.sin(myCamera.getRotation());
                        zAdd = (moveFactor) * Math.cos(myCamera.getRotation());
                        myCamera.getMyPos().x -= xAdd;
                        myCamera.getMyPos().z -= zAdd;
                        break;
                    case 68:    //D
                        xAdd = (moveFactor) * Math.cos(myCamera.getRotation());
                        zAdd = (moveFactor) * Math.sin(myCamera.getRotation());
                        myCamera.getMyPos().x -= xAdd;
                        myCamera.getMyPos().z += zAdd;
                        break;
                    case 81:
                        myCamera.setRotation(myCamera.getRotation() - rotateFactor);
                        break;
                    case 69:
                        myCamera.setRotation(myCamera.getRotation() + rotateFactor);
                        break;
                    case 82:
                        myCamera.getMyPos().y -= moveFactor;
                        break;
                    case 70:
                        myCamera.getMyPos().y += moveFactor;
                        break;
                    case 72:
                        myCamera.setMyPos(myScene.getCameraPosition());
                        myCamera.setRotation(myScene.getCameraRotation());
                        break;
                }
                myView.updateMatrix();
                GUIFunctions.refresh();
            }
        }

    }

    @Override
    public void openImage(File file) {

    }

    @Override
    public void saveImage(File file) {

    }

    @Override
    public void toggleBackgroundDisplay() {

    }

    @Override
    public void saveDrawing(File file) {
        myMod.save(file);
    }

    @Override
    public void openDrawing(File file) {
        myMod.open(file);
    }

    @Override
    public void doDeleteShape() {
        if(selectedIndex != -1){
            int mySelect = selectedIndex;
            selectedIndex = -1;
            myView.setSelected(selectedIndex);
            myMod.deleteShape(mySelect);
            GUIFunctions.refresh();

        }
    }

    @Override
    public void doEdgeDetection() {

    }

    @Override
    public void doSharpen() {

    }

    @Override
    public void doMedianBlur() {

    }

    @Override
    public void doUniformBlur() {

    }

    @Override
    public void doGrayscale() {

    }

    @Override
    public void doChangeContrast(int contrastAmountNum) {

    }

    @Override
    public void doChangeBrightness(int brightnessAmountNum) {

    }

    @Override
    public void doMoveForward() {
        if(selectedIndex != -1){
            if(selectedIndex >= 0 && selectedIndex < myMod.getSize() - 1){
                myMod.moveForward(selectedIndex);
                selectedIndex++;
                myView.setSelected(selectedIndex);
                GUIFunctions.refresh();
            }
        }
    }

    @Override
    public void doMoveBackward() {
        if(selectedIndex != -1){
            if(selectedIndex > 0){
                myMod.moveBackward(selectedIndex);
                selectedIndex--;
                myView.setSelected(selectedIndex);
                GUIFunctions.refresh();
            }
        }
    }

    @Override
    public void doSendToFront() {
        if(selectedIndex != -1){
            if(selectedIndex >= 0){
                myMod.moveToFront(selectedIndex);
                selectedIndex = myMod.getSize() - 1;
                myView.setSelected(selectedIndex);
                GUIFunctions.refresh();
            }
        }
    }

    @Override
    public void doSendtoBack() {
        if(selectedIndex != -1){
            if(selectedIndex > 0){
                myMod.movetoBack(selectedIndex);
                selectedIndex = 0;
                myView.setSelected(selectedIndex);
                GUIFunctions.refresh();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (drawShape) {
            case LINE:
                break;
            case SQUARE:
                break;
            case RECTANGLE:
                break;
            case CIRCLE:
                break;
            case ELLIPSE:
                break;
            case TRIANGLE:
                break;
            case NONE:
                myView.endDrawing();
                break;
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        Point2D.Double myClick = new Point2D.Double(e.getX(), e.getY());
        viewToWorld.transform(myClick, myClick);
        switch (drawShape) {
            case LINE:
                myShape = new Line(drawColor, myClick, new Point2D.Double(0,0));
                myView.setDrawing(myShape);
                break;
            case SQUARE:
                startPress.setLocation(myClick.getX(), myClick.getY());
                squareLeft.setLocation(myClick.getX(), myClick.getY());
                myShape = new Square(drawColor, new Point2D.Double(startPress.getX(), startPress.getY()), 0);
                myView.setDrawing(myShape);
                break;
            case RECTANGLE:
                startPress.setLocation(myClick.getX(), myClick.getY());
                squareLeft.setLocation(myClick.getX(), myClick.getY());
                myShape = new Rectangle(drawColor, new Point2D.Double(startPress.getX(), startPress.getY()), 0, 0);
                myView.setDrawing(myShape);
                break;
            case CIRCLE:
                startPress.setLocation(myClick.getX(), myClick.getY());
                squareLeft.setLocation(myClick.getX(), myClick.getY());
                myShape = new Square(drawColor, new Point2D.Double(startPress.getX(), startPress.getY()), 0);
                myView.setDrawing(myShape);
                break;
            case ELLIPSE:
                startPress.setLocation(myClick.getX(), myClick.getY());
                squareLeft.setLocation(myClick.getX(), myClick.getY());
                myShape = new Rectangle(drawColor, new Point2D.Double(startPress.getX(), startPress.getY()), 0, 0);
                myView.setDrawing(myShape);
                break;
            case TRIANGLE:
                switch (triangleClick){
                    case 0:
                        one = new Point2D.Double(myClick.getX(), myClick.getY());
                        break;
                    case 1:
                        two = new Point2D.Double(myClick.getX(), myClick.getY());
                        break;
                    case 2:
                        three = new Point2D.Double(myClick.getX(), myClick.getY());
                        Point2D.Double center = new Point2D.Double((one.getX() + two.getX() + three.getX())/3, (one.getY() + two.getY() + three.getY())/3 );
                        one.setLocation(one.getX() - center.getX(), one.getY() - center.getY());
                        two.setLocation(two.getX() - center.getX(), two.getY() - center.getY());
                        three.setLocation(three.getX() - center.getX(), three.getY() - center.getY());
                        myMod.addShape(new Triangle(drawColor, center, one, two, three));
                        GUIFunctions.refresh();
                        break;
                }
                triangleClick++;
                if(triangleClick >= 3){
                    triangleClick = 0;
                }
                break;
            case SELECT:
                if(selectedIndex == -1){
                    for(int i = myMod.getSize() - 1; i > -1; i--){
                        if(myMod.getShape(i).pointInShape(new Point2D.Double(myClick.getX(), myClick.getY()), tolerance*(1/(zoom/100)))){
                            selectedIndex = i;
                            myView.setSelected(selectedIndex);
                            GUIFunctions.changeSelectedColor(myMod.getShape(selectedIndex).getColor());
                            GUIFunctions.refresh();
                            i = -1;
                        }
                    }
                    if(selectedIndex != -1){
                        whatSelected = myMod.getShape(selectedIndex).rotationHit(new Point2D.Double(myClick.getX(), myClick.getY()), tolerance*(1/(zoom/100)), worldToView.getScaleY());
                        if (whatSelected.name().equals(SelectPoint.Center.name())) {
                            startPress.setLocation(myClick.getX(), myClick.getY());
                        }
                    }
                }else{
                    whatSelected = myMod.getShape(selectedIndex).rotationHit(new Point2D.Double(myClick.getX(), myClick.getY()), tolerance*(1/(zoom/100)), worldToView.getScaleY());
                    switch (whatSelected){
                        case None:
                            int i;
                            for(i = myMod.getSize() - 1; i > -1; i--){
                                if(myMod.getShape(i).pointInShape(new Point2D.Double(myClick.getX(), myClick.getY()), tolerance*(1/(zoom/100)))){
                                    selectedIndex = i;
                                    myView.setSelected(selectedIndex);
                                    GUIFunctions.changeSelectedColor(myMod.getShape(selectedIndex).getColor());
                                    GUIFunctions.refresh();
                                    whatSelected = SelectPoint.Center;
                                    startPress.setLocation(myClick.getX(), myClick.getY());
                                    i = -2;
                                }
                            }
                            if(i == -1){
                                selectedIndex = -1;
                                myView.setSelected(selectedIndex);
                                GUIFunctions.refresh();
                                whatSelected = SelectPoint.None;
                            }
                            break;
                        case Center:
                            startPress.setLocation(myClick.getX(), myClick.getY());
                            break;
                        case LinePoint:
                            break;
                        case Rotation:
                            Point2D.Double myCenter = myMod.getShape(selectedIndex).getCenter();
                            rotation = Math.atan2(myClick.getY() - myCenter.getY(), myClick.getX() - myCenter.getX());
                            GUIFunctions.printf("rotation " + rotation, 0);
                            rotation += 1.57;
                            myMod.getShape(selectedIndex).setRotation(rotation);
                            myMod.notifyChanged();
                            GUIFunctions.refresh();
                            break;
                    }
                }
                break;
            case NONE:
                myView.endDrawing();
                break;
        }
        GUIFunctions.refresh();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point2D.Double myClick = new Point2D.Double(e.getX(), e.getY());
        viewToWorld.transform(myClick, myClick);
        switch (drawShape) {
            case LINE:
                ((Line)myShape).setEnd(new Point2D.Double(myClick.getX() - myShape.getCenter().getX(), myClick.getY() - myShape.getCenter().getY()));
                myMod.addShape(myShape);
                break;
            case SQUARE: //commit square
                myMod.addShape(myShape);
                break;
            case RECTANGLE:
                myMod.addShape(myShape);
                break;
            case CIRCLE:
                myShape = circConvert.makeCircle((Square)myShape);
                myMod.addShape(myShape);
                break;
            case ELLIPSE:
                myShape = ellipseConvert.makeEllipse((Rectangle)myShape);
                myMod.addShape(myShape);
                break;
            case TRIANGLE:
                break;
            case NONE:
                myView.endDrawing();
                break;
        }
        myView.endDrawing();
        GUIFunctions.refresh();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void quadOneS(){
        height = -height;
        if(width > height){//add height
            (myShape).setCenter(new Point2D.Double(startPress.getX() + (height/2), startPress.getY() - (height/2)));
            ((Square)myShape).setSize(height);
        }else{//add width
            (myShape).setCenter(new Point2D.Double(startPress.getX() + (width/2), startPress.getY() - width/2));
            ((Square)myShape).setSize(width);
        }
    }
    public void quadTwoS(){
        if(width < height){//add height
            (myShape).setCenter(new Point2D.Double(startPress.getX() + (height/2), startPress.getY() + (height/2) ));
            ((Square)myShape).setSize(-height);
        }else{//add width
            (myShape).setCenter(new Point2D.Double(startPress.getX() + (width/2), startPress.getY() + (width/2) ));
            ((Square)myShape).setSize(-width);
        }
    }
    public void quadThreeS(){
        width = -width;
        if(width > height){//add height
            (myShape).setCenter(new Point2D.Double(startPress.getX() - (height/2), startPress.getY() + (height/2) ));
            ((Square)myShape).setSize(height);
        }else{//add width
            (myShape).setCenter(new Point2D.Double(startPress.getX() - (width/2), startPress.getY() + (width/2) ));
            ((Square)myShape).setSize(width);
        }
    }
    public void quadFourS(){
        if(width > height){//add height
            (myShape).setCenter(new Point2D.Double(startPress.getX() + (height/2), startPress.getY() + (height/2)));
            ((Square)myShape).setSize(height);
        }else{//add width
            (myShape).setCenter(new Point2D.Double(startPress.getX() + (width/2), startPress.getY() + (width/2)));
            ((Square)myShape).setSize(width);
        }
    }

    public void quadOneR(){
        height = -height;
        (myShape).setCenter(new Point2D.Double(startPress.getX() + width/2, startPress.getY() - height/2));
        ((Rectangle)myShape).setHeight(height);
        ((Rectangle)myShape).setWidth(width);
    }
    public void quadTwoR(){
        (myShape).setCenter(new Point2D.Double(startPress.getX() + width/2, startPress.getY() + height/2));
        ((Rectangle)myShape).setHeight(-height);
        ((Rectangle)myShape).setWidth(-width);
    }
    public void quadThreeR(){
        width = -width;
        (myShape).setCenter(new Point2D.Double(startPress.getX() - width/2, startPress.getY() + height/2));
        ((Rectangle)myShape).setHeight(height);
        ((Rectangle)myShape).setWidth(width);
    }
    public void quadFourR(){
        (myShape).setCenter(new Point2D.Double(startPress.getX() + width/2, startPress.getY() + height/2));
        ((Rectangle)myShape).setHeight(height);
        ((Rectangle)myShape).setWidth(width);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point2D.Double myClick = new Point2D.Double(e.getX(), e.getY());
        viewToWorld.transform(myClick, myClick);
        switch (drawShape) {
            case LINE:
                ((Line)myShape).setEnd(new Point2D.Double(myClick.getX() - myShape.getCenter().getX(), myClick.getY() - myShape.getCenter().getY()));
                myView.setDrawing(myShape);
                break;
            case SQUARE:
                width = myClick.getX() - startPress.getX();
                height = myClick.getY() - startPress.getY();
                if(width < 0){
                    if(height < 0){//2
                        quadTwoS();
                    } else{//3
                        quadThreeS();
                    }
                }else{
                    if(height < 0){//1
                        quadOneS();
                    } else{//4
                        quadFourS();
                    }
                }
                myView.setDrawing(myShape);
                break;
            case RECTANGLE:
                width = myClick.getX() - startPress.getX();
                height = myClick.getY() - startPress.getY();
                if(width < 0){
                    if(height < 0){//2
                        quadTwoR();
                    } else{//3
                        quadThreeR();
                    }
                }else{
                    if(height < 0){//1
                        quadOneR();
                    } else{//4
                        quadFourR();
                    }
                }
                myView.setDrawing(myShape);
                break;
            case CIRCLE:
                width = myClick.getX() - startPress.getX();
                height = myClick.getY() - startPress.getY();
                if(width < 0){
                    if(height < 0){//2
                        quadTwoS();
                    } else{//3
                        quadThreeS();
                    }
                }else{
                    if(height < 0){//1
                        quadOneS();
                    } else{//4
                        quadFourS();
                    }
                }
                myView.setDrawing(circConvert.makeCircle((Square) myShape));
                break;
            case ELLIPSE:
                width = myClick.getX() - startPress.getX();
                height = myClick.getY() - startPress.getY();
                if(width < 0){
                    if(height < 0){//2
                        quadTwoR();
                    } else{//3
                        quadThreeR();
                    }
                }else{
                    if(height < 0){//1
                        quadOneR();
                    } else{//4
                        quadFourR();
                    }
                }
                myView.setDrawing(ellipseConvert.makeEllipse((Rectangle) myShape));
                break;
            case TRIANGLE:
                break;
            case SELECT:
                switch (whatSelected){
                    case None:
                        return;
                    case Rotation:
                        Point2D.Double myCenter = myMod.getShape(selectedIndex).getCenter();
                        rotation = Math.atan2(myClick.getY() - myCenter.getY(), myClick.getX() - myCenter.getX());
                        GUIFunctions.printf("rotation " + rotation, 0);
                        rotation += 1.57;
                        myMod.getShape(selectedIndex).setRotation(rotation);
                        myMod.notifyChanged();
                        GUIFunctions.refresh();
                        break;
                    case LinePoint:
                        ((Line)myMod.getShape(selectedIndex)).changeCenter(myClick);
                        myMod.notifyChanged();
                        GUIFunctions.refresh();
                        break;
                    case LinePointTwo:
                        ((Line)myMod.getShape(selectedIndex)).setAffEnd(myClick);
                        myMod.notifyChanged();
                        GUIFunctions.refresh();
                        break;
                    case Center:
                        double xOff = startPress.getX() - myClick.getX();
                        double yOff = startPress.getY() - myClick.getY();
                        startPress.setLocation(myClick.getX(), myClick.getY());
                        GUIFunctions.printf("Should be moving",0);
                        Point2D.Double myDouble = myMod.getShape(selectedIndex).getCenter();
                        myDouble.setLocation(myDouble.getX() - xOff, myDouble.getY() - yOff);
                        myMod.notifyChanged();
                        GUIFunctions.refresh();
                        break;
                }
                break;
            case NONE:
                myView.endDrawing();
                break;
        }
        GUIFunctions.refresh();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}

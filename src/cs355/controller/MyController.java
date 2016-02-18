package cs355.controller;

import cs355.GUIFunctions;
import cs355.model.MyModel;
import cs355.model.drawable.RectangleEllipse;
import cs355.model.drawable.SquareCircle;
import cs355.model.drawing.*;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;
import cs355.view.MyViewRefresh;

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
    private MyViewRefresh myView;
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
    private AffineTransform viewToWorldClick;
    private int screenSize;
    private AffineTransform viewToWorld;


    public MyController(MyModel givenModel, MyViewRefresh theView){
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
        selectedIndex = -1;
        whatSelected = SelectPoint.None;
        tolerance = 8;
        zoom = 100;
        horizontalPos = 0;
        verticalPos = 0;
        viewToWorldClick = new AffineTransform();
        viewToWorldClick.setTransform(zoom/100, 0, 0, zoom/100, -screenSize*(horizontalPos/400), -screenSize*(verticalPos/400));
        viewToWorld = new AffineTransform();
        viewToWorld.setTransform(zoom/100, 0, 0, zoom/100, screenSize*(horizontalPos/400), screenSize*(verticalPos/400));
        myView.setZoom(viewToWorld);
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

    @Override
    public void zoomInButtonHit() {
        double thickness;
        if(zoom != 400){
            zoom = zoom * 2;
            viewToWorldClick.setTransform(zoom/100, 0, 0, zoom/100, -screenSize*(horizontalPos/400), -screenSize*(verticalPos/400));
            viewToWorld.setTransform(zoom/100, 0, 0, zoom/100, screenSize*(horizontalPos/400), screenSize*(verticalPos/400));
            myView.setZoom(viewToWorld);
            GUIFunctions.setZoomText((zoom)/100);
            GUIFunctions.printf("Zoom In stuck " + zoom, 0);
            GUIFunctions.refresh();
        }
        thickness = 400 - zoom + 25;
        GUIFunctions.setHScrollBarKnob((int)thickness);
        GUIFunctions.setHScrollBarPosit((int)horizontalPos);
        GUIFunctions.setVScrollBarKnob((int)thickness);
        GUIFunctions.setVScrollBarPosit((int)verticalPos);

    }

    @Override
    public void zoomOutButtonHit() {
        double thickness;
        if(zoom != 25){
            zoom = zoom / 2;
            viewToWorldClick.setTransform(zoom/100, 0, 0, zoom/100, -screenSize*(horizontalPos/400), -screenSize*(verticalPos/400));
            viewToWorld.setTransform(zoom/100, 0, 0, zoom/100, screenSize*(horizontalPos/400), screenSize*(verticalPos/400));
            myView.setZoom(viewToWorld);
            GUIFunctions.setZoomText((zoom)/100);
            GUIFunctions.printf("Zoom Out stuck " + zoom, 0);
            GUIFunctions.refresh();
        }
        thickness = 400 - zoom + 25;
        GUIFunctions.setHScrollBarKnob((int)thickness);
        GUIFunctions.setHScrollBarPosit((int)horizontalPos);
        GUIFunctions.setVScrollBarKnob((int)thickness);
        GUIFunctions.setVScrollBarPosit((int)verticalPos);
    }

    @Override
    public void hScrollbarChanged(int value) {
        GUIFunctions.printf("H Changed " + value, 0);
        horizontalPos = 400 - value;
        viewToWorldClick.setTransform(zoom/100, 0, 0, zoom/100, -screenSize*(horizontalPos/400), -screenSize*(verticalPos/400));
        viewToWorld.setTransform(zoom/100, 0, 0, zoom/100, screenSize*(horizontalPos/400), screenSize*(verticalPos/400));
        myView.setZoom(viewToWorld);
        GUIFunctions.refresh();
    }

    @Override
    public void vScrollbarChanged(int value) {
        GUIFunctions.printf("V Changed " + value, 0);
        verticalPos = 400 - value;
        viewToWorldClick.setTransform(zoom/100, 0, 0, zoom/100, -screenSize*(horizontalPos/400), -screenSize*(verticalPos/400));
        viewToWorld.setTransform(zoom/100, 0, 0, zoom/100, screenSize*(horizontalPos/400), screenSize*(verticalPos/400));
        myView.setZoom(viewToWorld);
        GUIFunctions.refresh();
    }

    @Override
    public void openScene(File file) {

    }

    @Override
    public void toggle3DModelDisplay() {

    }

    @Override
    public void keyPressed(Iterator<Integer> iterator) {

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
        Point2D.Double myClickStart = new Point2D.Double(e.getX(), e.getY());
        Point2D.Double myClick = new Point2D.Double();
        viewToWorldClick.transform(myClickStart, myClick);
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
                    for(int i = 0; i < myMod.getSize(); i++){
                        if(myMod.getShape(i).pointInShape(myClick, tolerance)){
                            selectedIndex = i;
                            myView.setSelected(selectedIndex);
                            GUIFunctions.changeSelectedColor(myMod.getShape(selectedIndex).getColor());
                            GUIFunctions.refresh();
                            i = myMod.getSize();
                        }
                    }
                    if(selectedIndex != -1){
                        whatSelected = myMod.getShape(selectedIndex).rotationHit(myClick, tolerance);
                        if (whatSelected.name().equals(SelectPoint.Center.name())) {
                            startPress.setLocation(myClick.getX(), myClick.getY());
                        }
                    }
                }else{
                    whatSelected = myMod.getShape(selectedIndex).rotationHit(myClick, tolerance);
                    switch (whatSelected){
                        case None:
                            int i;
                            for(i = 0; i < myMod.getSize(); i++){
                                if(myMod.getShape(i).pointInShape(myClick, tolerance)){
                                    selectedIndex = i;
                                    myView.setSelected(selectedIndex);
                                    GUIFunctions.changeSelectedColor(myMod.getShape(selectedIndex).getColor());
                                    GUIFunctions.refresh();
                                    whatSelected = SelectPoint.Center;
                                    startPress.setLocation(myClick.getX(), myClick.getY());
                                    i = myMod.getSize() + 1;
                                }
                            }
                            if(i == myMod.getSize()){
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
        viewToWorldClick.transform(myClick, myClick);
        switch (drawShape) {
            case LINE:
                ((Line)myShape).setEnd(new Point2D.Double(e.getX() - myShape.getCenter().getX(), e.getY() - myShape.getCenter().getY()));
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
        Point2D.Double myClickStart = new Point2D.Double(e.getX(), e.getY());
        Point2D.Double myClick = new Point2D.Double();
        viewToWorldClick.transform(myClickStart, myClick);
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

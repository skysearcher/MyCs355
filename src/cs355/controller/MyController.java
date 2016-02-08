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


    public MyController(MyModel givenModel, MyViewRefresh theView){
        myView = theView;
        myMod = givenModel;
        drawShape = TShapeEnum.NONE;
        triangleClick = 0;
        drawColor = Color.white;
        startPress = new Point2D.Double(0.0, 0.0);
        squareLeft = new Point2D.Double(0.0, 0.0);
        circConvert = new SquareCircle();
        ellipseConvert = new RectangleEllipse();
        selectedIndex = -1;
    }
    @Override
    public void colorButtonHit(Color c) {
        if(c != null){
            drawColor = c;
            GUIFunctions.changeSelectedColor(c);
        }
        if(selectedIndex != -1){
            myMod.getShape(selectedIndex).setColor(c);
            myMod.notifyChanged();
        }
    }

    @Override
    public void lineButtonHit() {
        drawShape = TShapeEnum.LINE;
        selectedIndex = -1;
        myMod.notifyChanged();
        GUIFunctions.refresh();
    }

    @Override
    public void squareButtonHit() {
        drawShape = TShapeEnum.SQUARE;
        selectedIndex = -1;
        myMod.notifyChanged();
        GUIFunctions.refresh();
    }

    @Override
    public void rectangleButtonHit() {
        drawShape = TShapeEnum.RECTANGLE;
        selectedIndex = -1;
        myMod.notifyChanged();
        GUIFunctions.refresh();
    }

    @Override
    public void circleButtonHit() {
        drawShape = TShapeEnum.CIRCLE;
        selectedIndex = -1;
        myMod.notifyChanged();
        GUIFunctions.refresh();
    }

    @Override
    public void ellipseButtonHit() {
        drawShape = TShapeEnum.ELLIPSE;
        selectedIndex = -1;
        myMod.notifyChanged();
        GUIFunctions.refresh();
    }

    @Override
    public void triangleButtonHit() {
        triangleClick = 0;
        drawShape = TShapeEnum.TRIANGLE;
        selectedIndex = -1;
        myMod.notifyChanged();
        GUIFunctions.refresh();
    }

    @Override
    public void selectButtonHit() {
        drawShape = TShapeEnum.NONE;
        selectedIndex = -1;
        myMod.notifyChanged();
        GUIFunctions.refresh();
    }

    @Override
    public void zoomInButtonHit() {
        GUIFunctions.printf("Zoom In stuck", 0);
        drawShape = TShapeEnum.NONE;
    }

    @Override
    public void zoomOutButtonHit() {
        GUIFunctions.printf("Zoom Out stuck", 0);
        drawShape = TShapeEnum.NONE;
    }

    @Override
    public void hScrollbarChanged(int value) {
        GUIFunctions.printf("Hroizontal Scroll Bar stuck", 0);
    }

    @Override
    public void vScrollbarChanged(int value) {
        GUIFunctions.printf("Vertical Scroll Bar stuck", 0);
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

    }

    @Override
    public void doMoveBackward() {

    }

    @Override
    public void doSendToFront() {

    }

    @Override
    public void doSendtoBack() {

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
                return;
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        switch (drawShape) {
            case LINE:
                myShape = new Line(drawColor, new Point2D.Double(e.getX(), e.getY()), new Point2D.Double(e.getX(), e.getY()));
                myView.setDrawing(myShape);
                break;
            case SQUARE:
                startPress.setLocation(e.getX(), e.getY());
                squareLeft.setLocation(e.getX(), e.getY());
                myShape = new Square(drawColor, new Point2D.Double(startPress.getX(), startPress.getY()), 0);
                myView.setDrawing(myShape);
                break;
            case RECTANGLE:
                startPress.setLocation(e.getX(), e.getY());
                squareLeft.setLocation(e.getX(), e.getY());
                myShape = new Rectangle(drawColor, new Point2D.Double(startPress.getX(), startPress.getY()), 0, 0);
                myView.setDrawing(myShape);
                break;
            case CIRCLE:
                startPress.setLocation(e.getX(), e.getY());
                squareLeft.setLocation(e.getX(), e.getY());
                myShape = new Square(drawColor, new Point2D.Double(startPress.getX(), startPress.getY()), 0);
                myView.setDrawing(myShape);
                break;
            case ELLIPSE:
                startPress.setLocation(e.getX(), e.getY());
                squareLeft.setLocation(e.getX(), e.getY());
                myShape = new Rectangle(drawColor, new Point2D.Double(startPress.getX(), startPress.getY()), 0, 0);
                myView.setDrawing(myShape);
                break;
            case TRIANGLE:
                switch (triangleClick){
                    case 0:
                        one = new Point2D.Double(e.getX(), e.getY());
                        break;
                    case 1:
                        two = new Point2D.Double(e.getX(), e.getY());
                        break;
                    case 2:
                        three = new Point2D.Double(e.getX(), e.getY());
                        Point2D.Double center = new Point2D.Double((one.getX() + two.getX() + three.getX())/2, (one.getY() + two.getY() + three.getY())/2 );
                        one.setLocation(center.getX() - one.getX(), center.getY() - one.getY());
                        two.setLocation(center.getX() - two.getX(), center.getY() - two.getY());
                        three.setLocation(center.getX() - three.getX(), center.getY() - three.getY());
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
                for(int i = 0; i < myMod.getSize(); i++){
                    if(myMod.getShape(i).pointInShape(new Point2D.Double(e.getX(), e.getY()), 4)){
                        selectedIndex = i;

                        i = myMod.getSize();
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
        switch (drawShape) {
            case LINE:
                ((Line)myShape).setEnd(new Point2D.Double(e.getX(), e.getY()));
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
                return;
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

    public void quadOneS(MouseEvent e){
        height = -height;
        if(width > height){//add height
            (myShape).setCenter(new Point2D.Double(startPress.getX() - width, startPress.getY() - height));
            ((Square)myShape).setSize(height);
        }else{//add width
            (myShape).setCenter(new Point2D.Double(startPress.getX() - height, startPress.getY() - width));
            ((Square)myShape).setSize(width);
        }
    }
    public void quadTwoS(MouseEvent e){
        if(width < height){//add height
            (myShape).setCenter(new Point2D.Double(startPress.getX() + height, startPress.getY() + height));
            ((Square)myShape).setSize(-height);
        }else{//add width
            (myShape).setCenter(new Point2D.Double(startPress.getX() + width, startPress.getY() + width));
            ((Square)myShape).setSize(-width);
        }
    }
    public void quadThreeS(MouseEvent e){
        width = -width;
        if(width > height){//add height
            (myShape).setCenter(new Point2D.Double(startPress.getX() - height, startPress.getY()));
            ((Square)myShape).setSize(height);
        }else{//add width
            (myShape).setCenter(new Point2D.Double(startPress.getX() - width, startPress.getY()));
            ((Square)myShape).setSize(width);
        }
    }
    public void quadFourS(MouseEvent e){
        if(width > height){//add height
            (myShape).setCenter(new Point2D.Double(startPress.getX() - width, startPress.getY()));
            ((Square)myShape).setSize(height);
        }else{//add width
            (myShape).setCenter(new Point2D.Double(startPress.getX() - width, startPress.getY()));
            ((Square)myShape).setSize(width);
        }
    }

    public void quadOneR(MouseEvent e){
        height = -height;
        ((Rectangle)myShape).setCenter(new Point2D.Double(startPress.getX(), startPress.getY() - height));
        ((Rectangle)myShape).setHeight(height);
        ((Rectangle)myShape).setWidth(width);
    }
    public void quadTwoR(MouseEvent e){
        ((Rectangle)myShape).setCenter(new Point2D.Double(startPress.getX() + width, startPress.getY() + height));
        ((Rectangle)myShape).setHeight(-height);
        ((Rectangle)myShape).setWidth(-width);
    }
    public void quadThreeR(MouseEvent e){
        width = -width;
        ((Rectangle)myShape).setCenter(new Point2D.Double(startPress.getX() - width, startPress.getY()));
        ((Rectangle)myShape).setHeight(height);
        ((Rectangle)myShape).setWidth(width);
    }
    public void quadFourR(MouseEvent e){
        ((Rectangle)myShape).setHeight(height);
        ((Rectangle)myShape).setWidth(width);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (drawShape) {
            case LINE:
                ((Line)myShape).setEnd(new Point2D.Double(e.getX(), e.getY()));
                myView.setDrawing(myShape);
                break;
            case SQUARE:
                width = e.getX() - startPress.getX();
                height = e.getY() - startPress.getY();
                if(width < 0){
                    if(height < 0){//2
                        quadTwoS(e);
                    } else{//3
                        quadThreeS(e);
                    }
                }else{
                    if(height < 0){//1
                        quadOneS(e);
                    } else{//4
                        quadFourS(e);
                    }
                }
                myView.setDrawing(myShape);
                break;
            case RECTANGLE:
                width = e.getX() - startPress.getX();
                height = e.getY() - startPress.getY();
                if(width < 0){
                    if(height < 0){//2
                        quadTwoR(e);
                    } else{//3
                        quadThreeR(e);
                    }
                }else{
                    if(height < 0){//1
                        quadOneR(e);
                    } else{//4
                        quadFourR(e);
                    }
                }
                myView.setDrawing(myShape);
                break;
            case CIRCLE:
                width = e.getX() - startPress.getX();
                height = e.getY() - startPress.getY();
                if(width < 0){
                    if(height < 0){//2
                        quadTwoS(e);
                    } else{//3
                        quadThreeS(e);
                    }
                }else{
                    if(height < 0){//1
                        quadOneS(e);
                    } else{//4
                        quadFourS(e);
                    }
                }
                myView.setDrawing(circConvert.makeCircle((Square) myShape));
                break;
            case ELLIPSE:
                width = e.getX() - startPress.getX();
                height = e.getY() - startPress.getY();
                if(width < 0){
                    if(height < 0){//2
                        quadTwoR(e);
                    } else{//3
                        quadThreeR(e);
                    }
                }else{
                    if(height < 0){//1
                        quadOneR(e);
                    } else{//4
                        quadFourR(e);
                    }
                }
                myView.setDrawing(ellipseConvert.makeEllipse((Rectangle) myShape));
                break;
            case TRIANGLE:
                break;
            case NONE:
                myView.endDrawing();
                return;
        }
        GUIFunctions.refresh();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}

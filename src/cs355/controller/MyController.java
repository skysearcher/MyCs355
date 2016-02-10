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
    private Point2D.Double rotationStart;
    private int selectedIndex;
    private int selectPoint;
    private SelectPoint whatSelected;
    private double rotation;


    public MyController(MyModel givenModel, MyViewRefresh theView){
        myView = theView;
        myMod = givenModel;
        drawShape = TShapeEnum.NONE;
        triangleClick = 0;
        drawColor = Color.white;
        startPress = new Point2D.Double(0, 0);
        squareLeft = new Point2D.Double(0, 0);
        rotationStart = new Point2D.Double(0,0);
        circConvert = new SquareCircle();
        ellipseConvert = new RectangleEllipse();
        selectedIndex = -1;
        selectPoint = -1;
        whatSelected = SelectPoint.None;
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
                break;
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        switch (drawShape) {
            case LINE:
                myShape = new Line(drawColor, new Point2D.Double(e.getX(), e.getY()), new Point2D.Double(0,0));
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
                        if(myMod.getShape(i).pointInShape(new Point2D.Double(e.getX(), e.getY()), 4)){
                            selectedIndex = i;
                            myView.setSelected(selectedIndex);
                            GUIFunctions.changeSelectedColor(myMod.getShape(selectedIndex).getColor());
                            GUIFunctions.refresh();
                            i = myMod.getSize();
                        }
                    }if(selectedIndex != -1){
                        whatSelected = myMod.getShape(selectedIndex).rotationHit(new Point2D.Double(e.getX(), e.getY()), 4);
                        if (whatSelected.name().equals(SelectPoint.Center.name())) {
                            startPress.setLocation(e.getX(), e.getY());
                        }
                    }
                }else{
                    whatSelected = myMod.getShape(selectedIndex).rotationHit(new Point2D.Double(e.getX(), e.getY()), 4);
                    switch (whatSelected){
                        case None:
                            for(int i = 0; i < myMod.getSize(); i++){
                                if(myMod.getShape(i).pointInShape(new Point2D.Double(e.getX(), e.getY()), 4)){
                                    selectedIndex = i;
                                    myView.setSelected(selectedIndex);
                                    GUIFunctions.changeSelectedColor(myMod.getShape(selectedIndex).getColor());
                                    GUIFunctions.refresh();
                                    whatSelected = SelectPoint.Center;
                                    startPress.setLocation(e.getX(), e.getY());
                                    i = myMod.getSize();
                                }
                            }
                            break;
                        case Center:
                            startPress.setLocation(e.getX(), e.getY());
                            break;
                        case LinePoint:
                            break;
                        case Rotation:
                            Point2D.Double myCenter = myMod.getShape(selectedIndex).getCenter();
                            rotation = Math.atan2(e.getY() - myCenter.getY(), e.getX() - myCenter.getX());
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
        switch (drawShape) {
            case LINE:
                ((Line)myShape).setEnd(new Point2D.Double(e.getX() - myShape.getCenter().getX(), e.getY() - myShape.getCenter().getY()));
                myView.setDrawing(myShape);
                break;
            case SQUARE:
                width = e.getX() - startPress.getX();
                height = e.getY() - startPress.getY();
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
                width = e.getX() - startPress.getX();
                height = e.getY() - startPress.getY();
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
                width = e.getX() - startPress.getX();
                height = e.getY() - startPress.getY();
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
                width = e.getX() - startPress.getX();
                height = e.getY() - startPress.getY();
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
                        rotation = Math.atan2(e.getY() - myCenter.getY(), e.getX() - myCenter.getX());
                        GUIFunctions.printf("rotation " + rotation, 0);
                        rotation += 1.57;
                        myMod.getShape(selectedIndex).setRotation(rotation);
                        myMod.notifyChanged();
                        GUIFunctions.refresh();
                        break;
                    case LinePoint:
                        break;
                    case Center:
                        double xOff = startPress.getX() - e.getX();
                        double yOff = startPress.getY() - e.getY();
                        startPress.setLocation(e.getX(), e.getY());
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

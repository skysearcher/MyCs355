package cs355.model;

import cs355.model.drawing.CS355Drawing;
import cs355.model.drawing.Shape;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Joshua on 1/18/2016.
 */
public class  MyModel extends CS355Drawing {
    private List<Shape> myShapes;
    public MyModel(){
        myShapes = new LinkedList<>();
    }

    @Override
    public Shape getShape(int index) {
        return myShapes.get(index);
    }

    @Override
    public int addShape(Shape s) {
        myShapes.add(s);
        this.setChanged();
        this.notifyObservers();
        return 0;
    }

    @Override
    public void deleteShape(int index) {
        myShapes.remove(index);
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void moveToFront(int index) {
        Shape moveShape = myShapes.remove(index);
        myShapes.add(myShapes.size(), moveShape);
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void movetoBack(int index) {
        Shape moveShape = myShapes.remove(index);
        myShapes.add(0, moveShape);
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void moveForward(int index) {
        Shape moveShape = myShapes.remove(index + 1);
        myShapes.add(index, moveShape);
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void moveBackward(int index) {
        Shape moveShape = myShapes.remove(index);
        myShapes.add(index - 1, moveShape);
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public List<Shape> getShapes() {
        return myShapes;
    }

    @Override
    public List<Shape> getShapesReversed() {
        return myShapes;
    }

    @Override
    public void setShapes(List<Shape> shapes) {
        myShapes = shapes;
        this.setChanged();
        this.notifyObservers();
    }
    public int getSize(){
        return myShapes.size();
    }

    public void notifyChanged(){
        this.setChanged();
        this.notifyObservers();
    }
}

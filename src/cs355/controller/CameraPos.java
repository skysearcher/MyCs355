package cs355.controller;

import cs355.model.scene.Point3D;

/**
 * Created by Josh on 3/20/2016.
 */
public class CameraPos {
    private Point3D myPos;
    private double rotation;
    public CameraPos(){
        myPos = new Point3D();
        rotation = 0;
    }

    public Point3D getMyPos() {
        return myPos;
    }

    public void setMyPos(Point3D myPos) {
        this.myPos = myPos;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
}

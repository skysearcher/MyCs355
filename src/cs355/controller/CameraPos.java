package cs355.controller;

import cs355.model.scene.Point3D;

/**
 * Created by Josh on 3/20/2016.
 */
public class CameraPos {
    private Point3D myPos;
    private double rotation;
    private double fov;
    private double near;
    private double far;

    public CameraPos(){
        myPos = new Point3D();
        rotation = 0;
        fov = 0;
        near = 0;
        far = 0;
    }

    public double getFov() {
        return fov;
    }

    public void setFov(double fov) {
        this.fov = fov;
    }

    public double getNear() {
        return near;
    }

    public void setNear(double near) {
        this.near = near;
    }

    public double getFar() {
        return far;
    }

    public void setFar(double far) {
        this.far = far;
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

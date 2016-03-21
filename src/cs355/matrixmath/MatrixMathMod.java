package cs355.matrixmath;

import cs355.model.scene.Point3D;

import java.awt.geom.Point2D;

/**
 * Created by Josh on 3/20/2016.
 */
public class MatrixMathMod {
    double[][] matrix;
    double width;
    double height;

    public MatrixMathMod() {
        matrix = new double[4][4];
        width = 2048;
        height = 2048;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public void clearMatrix(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                matrix[i][j] = 0;
            }
        }
    }

    public void setAsRotation(double rotation) {
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                matrix[i][j] = 0;
            }
        }
        matrix[0][0] = Math.cos(rotation);
        matrix[0][2] = -Math.sin(rotation);
        matrix[1][1] = 1;
        matrix[2][0] = Math.sin(rotation);
        matrix[2][2] = Math.cos(rotation);
        matrix[3][3] = 1;
    }

    public void setAsTranslation(Point3D myPoint) {
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                matrix[i][j] = 0;
            }
        }
        for(int i = 0; i < 4; i++){
            matrix[i][i] = 1;
        }
        matrix[0][3] = myPoint.x;
        matrix[1][3] = myPoint.y;
        matrix[2][3] = myPoint.z;
    }

    public void setAsClipMatrix(double fov, double near, double far){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                matrix[i][j] = 0;
            }
        }
        matrix[0][0] = 1/Math.tan(fov/2);
        matrix[1][1] = 1/Math.tan(fov/2);
        matrix[2][2] = (far + near)/(far - near);
        matrix[2][3] = (-2 * near * far)/(far - near);
        matrix[3][2] = 1;
    }


    public double[] multiplyVec(double[] vec1) {
        double[] changeVec = new double[4];
        for (int i = 0; i < 4; i++)
            for (int k = 0; k < 4; k++)
                changeVec[i] += matrix[i][k] * vec1[k];
        return changeVec;
    }

    public void concateMatrix(double[][] givenMatrix) {
        double[][] matrixOne = new double[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                for (int k = 0; k < 4; k++)
                    matrixOne[i][j] += givenMatrix[i][k] * matrix[k][j];
        matrix = matrixOne;
    }
    public double[] homogenDiv(double[] givenVec){
        double[] divide = new double[4];
        divide[0] = givenVec[0]/givenVec[3];
        divide[1] = givenVec[1]/givenVec[3];
        divide[2] = 1;
        return divide;
    }
    public Point2D.Double toScreen(double[] givenVec){
        Point2D.Double myPoint = new Point2D.Double();
        myPoint.x = (givenVec[0] * (width/2)) + (width/2);
        myPoint.y = (givenVec[1] * (-height/2)) + (height/2);
        return myPoint;
    }

    public boolean passClipTest(double[] givenVec, double[] givenVec2){
        if(givenVec[0] < givenVec[3] || givenVec2[0] < givenVec2[3]){
            if(givenVec[1] < givenVec[3] || givenVec2[1] < givenVec2[3]){
                if(givenVec[2] < givenVec[3] || givenVec2[2] < givenVec2[3]){
                    return passClipTestTwo(givenVec , givenVec2);
                }
            }
        }
        return false;
    }
    private boolean passClipTestTwo(double[] givenVec, double[] givenVec2){
        if(givenVec[0] > -givenVec[3] || givenVec2[0] > -givenVec2[3]){
            if(givenVec[1] > -givenVec[3] || givenVec2[1] > -givenVec2[3]){
                if(givenVec[2] > -givenVec[3] && givenVec2[2] > -givenVec2[3]){
                    return true;
                }
            }
        }
        return false;
    }
}

package cs355.matrixmath;

import cs355.model.scene.Point3D;

/**
 * Created by Josh on 3/20/2016.
 */
public class FourDMatrix {
    double[][] matrix;

    public FourDMatrix() {
        matrix = new double[4][4];
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMulti(double[][] multi) {
        this.matrix = multi;
    }

    public void setAsRotation(double rotation) {
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                matrix[i][j] = 0;
            }
        }
        matrix[0][0] = Math.cos(rotation);
        matrix[0][2] = Math.sin(rotation);
        matrix[1][1] = 1;
        matrix[2][0] = -Math.sin(rotation);
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

}

package cs355.model.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Joshua on 4/12/2016.
 */
public class MyImage extends CS355Image{

    public MyImage(){
    }

    @Override
    public BufferedImage getImage() {
        BufferedImage myImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                myImage.setRGB(i, j, ((getRed(i, j)<<16) + (getGreen(i, j)<<8) + getBlue(i, j)));
            }
        }
        return myImage;
    }

    //TODO: work on the Edge Detection
    @Override
    public void edgeDetection() {

    }

    @Override
    public void sharpen() {
        CS355Image mySecond = new MyImage();
        mySecond.setPixels(this);
        int[] my1 = new int[3];
        int[] my2 = new int[3];
        int[] my3 = new int[3];
        int[] my4 = new int[3];
        int[] my5 = new int[3];
        int[] my6 = new int[3];
        int[] my7 = new int[3];
        int[] my8 = new int[3];
        int[] my9 = new int[3];
        int[] myPixelAdd = new int[3];
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                getPixel(i, j, my1);
                mySecond.setPixel(i, j , my1);
            }
        }
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                if(i > 0 && i < getWidth() - 1 && j > 0 && j < getHeight() - 1){ //middle
//                    getPixel(i - 1, j - 1, my1);
                    getPixel(i, j - 1, my2);
//                    getPixel(i + 1, j - 1, my3);
                    getPixel(i - 1, j, my4);
                    getPixel(i, j, my5);
                    getPixel(i + 1, j, my6);
//                    getPixel(i - 1, j + 1, my7);
                    getPixel(i, j + 1, my8);
//                    getPixel(i + 1, j + 1, my9);
                }
                myPixelAdd[0] = ((my2[0] * -1) - my4[0] + (my5[0] * 6) - my6[0] - my8[0])/2;
                if(myPixelAdd[0] < 0){
                    myPixelAdd[0] = 0;
                }
                if(myPixelAdd[0] > 255){
                    myPixelAdd[0] = 255;
                }
                myPixelAdd[1] = ((my2[0] * -1) - my4[0] + (my5[0] * 6) - my6[0] - my8[0])/2;
                if(myPixelAdd[1] < 0){
                    myPixelAdd[1] = 0;
                }
                if(myPixelAdd[1] > 255){
                    myPixelAdd[1] = 255;
                }
                myPixelAdd[2] = ((my2[0] * -1) - my4[0] + (my5[0] * 6) - my6[0] - my8[0])/2;
                if(myPixelAdd[2] < 0){
                    myPixelAdd[2] = 0;
                }
                if(myPixelAdd[2] > 255){
                    myPixelAdd[2] = 255;
                }
                mySecond.setPixel(i, j, myPixelAdd);
            }
        }
        setPixels(mySecond);
    }
    //TODO: work on the median blur
    @Override
    public void medianBlur() {
        CS355Image mySecond = new MyImage();
        mySecond.setPixels(this);
        int[] transfer = new int[3];
        int[] myChart = new int[9];
        int[][] myChartPixel = new int[9][3];
        int[] myPixelMedian = new int[3];
        int changeIndex = 0;
        double distance = 0;
        double testD = 0;
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                getPixel(i, j, transfer);
                mySecond.setPixel(i, j , transfer);
            }
        }
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                if(i > 0 && i < getWidth() - 1 && j > 0 && j < getHeight() - 1){ //middle
                    getPixel(i - 1, j - 1, myChartPixel[0]);
                    getPixel(i, j - 1, myChartPixel[1]);
                    getPixel(i + 1, j - 1, myChartPixel[2]);
                    getPixel(i - 1, j, myChartPixel[3]);
                    getPixel(i, j, myChartPixel[4]);
                    getPixel(i + 1, j, myChartPixel[5]);
                    getPixel(i - 1, j + 1, myChartPixel[6]);
                    getPixel(i, j + 1, myChartPixel[7]);
                    getPixel(i + 1, j + 1, myChartPixel[8]);

                    myChart[0] = myChartPixel[0][0];
                    myChart[1] = myChartPixel[1][0];
                    myChart[2] = myChartPixel[2][0];
                    myChart[3] = myChartPixel[3][0];
                    myChart[4] = myChartPixel[4][0];
                    myChart[5] = myChartPixel[5][0];
                    myChart[6] = myChartPixel[6][0];
                    myChart[7] = myChartPixel[7][0];
                    myChart[8] = myChartPixel[8][0];

                    Arrays.sort(myChart);

                    myPixelMedian[0] = myChart[4];

                    myChart[0] = myChartPixel[0][1];
                    myChart[1] = myChartPixel[1][1];
                    myChart[2] = myChartPixel[2][1];
                    myChart[3] = myChartPixel[3][1];
                    myChart[4] = myChartPixel[4][1];
                    myChart[5] = myChartPixel[5][1];
                    myChart[6] = myChartPixel[6][1];
                    myChart[7] = myChartPixel[7][1];
                    myChart[8] = myChartPixel[8][1];

                    Arrays.sort(myChart);

                    myPixelMedian[1] = myChart[4];

                    myChart[0] = myChartPixel[0][2];
                    myChart[1] = myChartPixel[1][2];
                    myChart[2] = myChartPixel[2][2];
                    myChart[3] = myChartPixel[3][2];
                    myChart[4] = myChartPixel[4][2];
                    myChart[5] = myChartPixel[5][2];
                    myChart[6] = myChartPixel[6][2];
                    myChart[7] = myChartPixel[7][2];
                    myChart[8] = myChartPixel[8][2];

                    Arrays.sort(myChart);

                    myPixelMedian[2] = myChart[4];

                    distance = Double.MAX_VALUE;
                    for(int k = 0; k < 9; k++){
                        testD = Math.sqrt(Math.pow(myPixelMedian[0] - myChartPixel[k][0], 2) + Math.pow(myPixelMedian[0] - myChartPixel[k][0], 2) + Math.pow(myPixelMedian[0] - myChartPixel[k][0], 2));
                        if(testD < distance){
                            distance = testD;
                            changeIndex = k;
                        }
                    }

                    mySecond.setPixel(i, j, myChartPixel[changeIndex]);
                }
            }
        }
        setPixels(mySecond);
    }

    @Override
    public void uniformBlur() {
        CS355Image mySecond = new MyImage();
        mySecond.setPixels(this);
        int[] my1 = new int[3];
        int[] my2 = new int[3];
        int[] my3 = new int[3];
        int[] my4 = new int[3];
        int[] my5 = new int[3];
        int[] my6 = new int[3];
        int[] my7 = new int[3];
        int[] my8 = new int[3];
        int[] my9 = new int[3];
        int[] myPixelAdd = new int[3];
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                getPixel(i, j, my1);
                mySecond.setPixel(i, j , my1);
            }
        }
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                if(i > 0 && i < getWidth() - 1 && j > 0 && j < getHeight() - 1){ //middle
                    getPixel(i - 1, j - 1, my1);
                    getPixel(i, j - 1, my2);
                    getPixel(i + 1, j - 1, my3);
                    getPixel(i - 1, j, my4);
                    getPixel(i, j, my5);
                    getPixel(i + 1, j, my6);
                    getPixel(i - 1, j + 1, my7);
                    getPixel(i, j + 1, my8);
                    getPixel(i + 1, j + 1, my9);
                }
                if(i == 0){

                }
                if(i == getWidth() - 1){

                }
                if(j == 0){

                }
                if(j == getHeight() - 1){

                }
                myPixelAdd[0] = (my1[0] + my2[0] + my3[0] + my4[0] + my5[0] + my6[0] + my7[0] + my8[0] + my9[0])/9;
                myPixelAdd[1] = (my1[1] + my2[1] + my3[1] + my4[1] + my5[1] + my6[1] + my7[1] + my8[1] + my9[1])/9;
                myPixelAdd[2] = (my1[2] + my2[2] + my3[2] + my4[2] + my5[2] + my6[2] + my7[2] + my8[2] + my9[2])/9;
                mySecond.setPixel(i, j, myPixelAdd);
            }
        }
        setPixels(mySecond);
    }

    @Override
    public void grayscale() {
        int[] myPixel = new int[3];
        float[] myFloats = new float[3];
        Color myColor;
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                getPixel(i, j, myPixel);
                Color.RGBtoHSB(myPixel[0], myPixel[1], myPixel[2], myFloats);
                myFloats[1] = 0;

                myColor = Color.getHSBColor(myFloats[0], myFloats[1], myFloats[2]);
                myPixel[0] = myColor.getRed();
                myPixel[1] = myColor.getGreen();
                myPixel[2] = myColor.getBlue();
                setPixel(i, j , myPixel);
            }
        }
    }

    @Override
    public void contrast(int amount) {
        int[] myPixel = new int[3];
        float[] myFloats = new float[3];
        Color myColor;
        float colorChange = (float)Math.pow((((double)(amount - 100))/100.0), 4.0);
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                getPixel(i, j, myPixel);
                Color.RGBtoHSB(myPixel[0], myPixel[1], myPixel[2], myFloats);
                myFloats[2] = (colorChange*(myFloats[2] - 0.5f)) + 0.5f;

                myColor = Color.getHSBColor(myFloats[0], myFloats[1], myFloats[2]);
                myPixel[0] = myColor.getRed();
                myPixel[1] = myColor.getGreen();
                myPixel[2] = myColor.getBlue();
                setPixel(i, j , myPixel);
            }
        }
    }

    @Override
    public void brightness(int amount) {
        int[] myPixel = new int[3];
        float[] myFloats = new float[3];
        Color myColor;
        float colorChange = ((float)amount)/100.0f;
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                getPixel(i, j, myPixel);
                Color.RGBtoHSB(myPixel[0], myPixel[1], myPixel[2], myFloats);
                myFloats[2] += colorChange;
                if(myFloats[2] < 0){
                    myFloats[2] = 0;
                }
                if(myFloats[2] > 1){
                    myFloats[2] = 1;
                }

                myColor = Color.getHSBColor(myFloats[0], myFloats[1], myFloats[2]);
                myPixel[0] = myColor.getRed();
                myPixel[1] = myColor.getGreen();
                myPixel[2] = myColor.getBlue();
                setPixel(i, j , myPixel);
            }
        }
    }
}

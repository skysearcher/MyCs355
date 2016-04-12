package cs355.model.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
                if(i == 0){

                }
                if(i == getWidth() - 1){

                }
                if(j == 0){

                }
                if(j == getHeight() - 1){

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

    @Override
    public void medianBlur() {
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

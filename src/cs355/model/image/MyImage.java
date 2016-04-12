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

    }

    @Override
    public void medianBlur() {

    }

    @Override
    public void uniformBlur() {
        CS355Image mySecond = new MyImage();
        int[] myPixel = new int[3];
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                getPixel(i, j, myPixel);
                mySecond.setPixel(i, j , myPixel);
            }
        }
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){

            }
        }
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

                myColor = Color.getHSBColor(myFloats[0], myFloats[1], myFloats[2]);
                myPixel[0] = myColor.getRed();
                myPixel[1] = myColor.getGreen();
                myPixel[2] = myColor.getBlue();
                setPixel(i, j , myPixel);
            }
        }
    }
}

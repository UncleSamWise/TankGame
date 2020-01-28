package com.cs240.tankgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/*
 *
 */

public class CharacterSprite {

    private Bitmap image;

    //image location
    private int x, y;

    //image speed
    private int xVelocity = 10;
    private int yVelocity = 5;

    //screen display width and height
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    //constructor. Determines starting position of image
    public CharacterSprite (Bitmap bmp) {
        image = bmp;
        x = 100;
        y = 100;
    }

    //draws the image on screen
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);


    }

    //Updates the position of the image on screen
    public void update() {

        //moves the image like a screen saver
        x += xVelocity;
        y += yVelocity;
        if ((x > screenWidth - image.getWidth()) || (x < 0)) {
            xVelocity = xVelocity*-1;
        }
        if ((y > screenHeight - image.getHeight()) || (y < 0)) {
            yVelocity = yVelocity*-1;
        }

    }


}

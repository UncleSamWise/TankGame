package com.cs240.tankgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/*
 *
 */

public class CharacterSprite {

    private Bitmap image;

    private int facing;

    //image location
    private int x, y;


    //screen display width and height
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    //image speed
    private int xVelocity = screenWidth / 10;
    private int yVelocity = screenHeight / 17;

    //constructor. Determines starting position of image
    public CharacterSprite (Bitmap bmp, int width, int height) {
        image = bmp.createScaledBitmap(bmp, width, height, true);
        x = 300;
        y = 300;
        facing = 1;
    }

    //draws the image on screen
    public void drawSprite( Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
//        canvas.drawColor(Color.WHITE);
//        Paint paint = new Paint();
//        paint.setColor(Color.rgb(250, 0, 0));
//        canvas.drawRect(100, 100, 200, 200, paint);

    }

    //Updates the position of the image on screen
    public void update(int input) {
//        x = (int)inputX;
//        y = (int)inputY;
        if(input == 0) {

        }else if(input == 1 ) {

                y += yVelocity;

        }
//        else if(input == 2) {
//            //if ((y > screenWidth - image.getHeight()) || (y < 0)) {&& (y > screenWidth - image.getHeight())
//                y -= yVelocity;
//            //}
//        }else if(input == 3) {
//            //if ((x > screenWidth - image.getWidth()) || (x < 0)) {
//                x -= xVelocity;
//            //}
//        }else if(input == 4) {
//            //if ((x > screenWidth - image.getWidth()) || (x < 0)) {
//                x += xVelocity;
//            //}
//        }

        if ((x > screenWidth - image.getWidth()) || (x < 0)) {

        }
        if ((y > screenHeight - image.getHeight()) || (y < 0)) {
            yVelocity = yVelocity*-1;
        } else {
            if(input == 0) {

            }else if(input == 1 ) {

                y += yVelocity;

            }
        }

        //if ((x > screenWidth - image.getWidth()) || (x < 0) || y > screenHeight - image.getHeight() || (y < 0)) {
//            if(input == 0) {
//
//            }else if(input == 1 ) {
//
//                y += yVelocity;
//
//            }
//        } else {
//
//        }

//        //moves the image like a screen saver
//        x += xVelocity;
//        y += yVelocity;

    }


}
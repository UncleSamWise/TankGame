package com.cs240.tankgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class LineTank extends Enemy {

    private boolean fireTicker = true;

    Matrix matrix = new Matrix();

    //Constructor
    //Moves in direction of initial moveFacing, and fires to its right
    public LineTank(TankMap map, int col, int row, int facing, Bitmap bmp, int width, int height) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.moveFacing = facing;
        if(facing == 3){
            this.fireFacing = 0;
        } else fireFacing = facing+1;
        this.health = 3;
        tookTurn = true;
        isBullet = false;

        matrix.postRotate(90);
        this.image = bmp.createScaledBitmap(bmp, width, height, true);
        for(int i = 0; i < facing + 1; i++) {
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
        }
//        matrix = new Matrix();
//        matrix.postRotate(0);
    }

    public void doTurn(){
        if(fireTicker){
            doShoot();
        } else doMove();
        tookTurn = true;
    }

    public Bitmap drawEnemy(Canvas canvas){
        return image;
        //canvas.drawBitmap(image, screenWidth/row, screenHeight/col, null);
    }

    public void doMove(){

        if(!map.move(this)) {
            if(moveFacing == 0){
                moveFacing = 2;
            } else if (moveFacing == 1){
                moveFacing = 3;
            } else if (moveFacing == 2){
                moveFacing = 0;
            } else moveFacing = 1;
            map.move(this);
        }
        fireTicker = true;
        image = Bitmap.createBitmap(image);
    }

    public void doShoot() {
        map.shoot(this, 1, 1);
        fireTicker = false;
    }
}
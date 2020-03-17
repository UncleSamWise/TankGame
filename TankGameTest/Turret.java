package com.cs240.tankgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class Turret extends Enemy {

    private boolean tickerFire = true;
    Matrix matrix = new Matrix();

    //Constructor
    public Turret(TankMap map, int col, int row, int facing, Bitmap bmp, int width, int height) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.moveFacing = facing;
        this.fireFacing = facing;
        this.health = 1;
        tookTurn = true;
        //icon = 'T';
        isBullet = false;
        matrix.postRotate(90);
        this.image = bmp.createScaledBitmap(bmp, width, height, true);
        for(int i = 0; i < facing; i++) {
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
        }
    }

    public Bitmap drawEnemy(Canvas canvas){
        return image;
        //canvas.drawBitmap(image, screenWidth/row, screenHeight/col, null);
    }

    public void doTurn(){
        if(tickerFire){
            doShoot();
            tickerFire = false;
        } else{
            tickerFire = true;
        }
        tookTurn = true;
        image = Bitmap.createBitmap(image);
    }

    public void doMove(){
    }

    public void doShoot() {
        map.shoot(this, 1, 1);
    }
}
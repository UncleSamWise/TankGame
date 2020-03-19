package com.cs240.tankgame;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class PlayerSprite extends Enemy{

    private int currentRotation;

    private boolean isFirstTurn;

    private Matrix matrix = new Matrix();

    public PlayerSprite(TankMap map, int col, int row, int facing, Bitmap bmp, int width, int height) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.moveFacing = facing;
        this.fireFacing = facing;
        this.health = 1;
        tookTurn = true;
        isBullet = false;
        isPlayer = true;
        isFirstTurn = true;
        currentRotation = 0;
        matrix.postRotate(90);
        this.image = bmp.createScaledBitmap(bmp, width, height, true);
        for(int i = 0; i < fireFacing; i++) {
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
        }
    }

    public void doPlayerTurn(int dir){
        if(dir == 0){
            doShoot();
        } else {
            moveFacing = dir - 1;
            doMove();
        }
        tookTurn = true;
    }

    //Next two methods should be called externally
    public void doMove(){

        map.move(this);
        while(currentRotation != moveFacing) {
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
            if (currentRotation != 3) currentRotation++;
            else currentRotation = 0;
        }
    }

    public void doShoot(){
        fireFacing = moveFacing;
        if(isFirstTurn){
            fireFacing = Math.abs(fireFacing - 2);
            isFirstTurn = false;
        }
        map.shoot(this, 1, 1);
//        while(currentRotation != fireFacing) {
//            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
//            if (currentRotation != 3) currentRotation++;
//            else currentRotation = 0;
//        }
    }

}
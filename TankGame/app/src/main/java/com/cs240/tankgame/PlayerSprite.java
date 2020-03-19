package com.cs240.tankgame;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class PlayerSprite extends Enemy{

    int currentRotation;

    private Matrix matrix = new Matrix();

    public PlayerSprite(TankMap map, int col, int row, int facing, Bitmap bmp, int width, int height) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.moveFacing = facing;
        this.fireFacing = facing;
        this.health = 5;
        tookTurn = true;
        isBullet = false;
        isPlayer = true;
        currentRotation = 0;
        matrix.postRotate(90);
        this.image = bmp.createScaledBitmap(bmp, width, height, true);
        for(int i = 0; i < fireFacing; i++) {
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
        }
    }

    public void doPlayerTurn(int dir){
        if(dir == 0){
            //fireFacing = dir;
            doPlayerShoot(dir - 1);
        } else doPlayerMove(dir - 1);
        tookTurn = true;
    }

    //Next two methods should be called externally
    public void doPlayerMove(int dir){

        moveFacing = dir;
        if(!map.move(this)){
            moveFacing = dir;
            map.move(this);
        }
        while(currentRotation != moveFacing) {
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
            if (currentRotation != 3) currentRotation++;
            else currentRotation = 0;
        }
    }

    public void doPlayerShoot(int dir){

        //fireFacing = dir;
        if(!map.playerShoot(this, 1, 1, dir)){
            //fireFacing = dir;
            map.playerShoot(this, 1, 1, dir);
        }        //currentRotation = 0;
        while(currentRotation != dir) {
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
            if (currentRotation != 3) currentRotation++;
            else currentRotation = 0;
        }
    }

//    public void doHit(int damage){
//        health -= damage;
//        if(health <= 0) doDie();
//    }

//    public void doDie(){
//        map.setAtLoc(null, col, row);
//        //Handle losing the game
//    }
//

}
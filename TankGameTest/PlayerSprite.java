package com.cs240.tankgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class PlayerSprite extends Enemy{

    int currentRotation;

    Matrix matrix = new Matrix();

    public PlayerSprite(TankMap map, int col, int row, int facing, Bitmap bmp, int width, int height) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.moveFacing = facing;
        this.fireFacing = facing;
        this.health = 1;
        tookTurn = true;
        isBullet = false;
        currentRotation = 0;
        matrix.setRotate(90);
        this.image = bmp.createScaledBitmap(bmp, width, height, true);
    }

    public void doTurn(){
        tookTurn = true;
    }

    //Next two methods should be called externally
    public void doMove(int facing){

        moveFacing = facing;
        if(map.move(this)){
           map.doTurns();
        } else System.out.println("Illegal move attempted"); //Handle this some other way later;
        while(currentRotation != moveFacing) {
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
            if (currentRotation != 3) currentRotation++;
            else currentRotation = 0;
        }
    }

    public void doShoot(int facing){

        fireFacing = facing;
        if(map.shoot(this, 2, 2)){
            map.doTurns();
        } else System.out.println("Illegal move attempted"); //Handle this some other way later;
        while(currentRotation != fireFacing) {
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
            if (currentRotation != 3) currentRotation++;
            else currentRotation = 0;
        }
    }

    public void doHit(int damage){
        health -= damage;
        if(health <= 0) doDie();
    }

    public void doDie(){
        map.setAtLoc(null, col, row);
        //Handle losing the game
    }

    public Bitmap drawEnemy(Canvas canvas){
        return image;
        //canvas.drawBitmap(image, screenWidth/row, screenHeight/col, null);
    }

    public String toString() {
        return icon + "";
    }

}
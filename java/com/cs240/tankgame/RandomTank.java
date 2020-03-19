package com.cs240.tankgame;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import java.util.Random;

public class RandomTank extends Enemy {

    private Random randomNum = new Random();
    Matrix matrix = new Matrix();
    int currentRotation;

    //Constructor
    public RandomTank(TankMap map, int col, int row, int facing, Bitmap bmp, int width, int height) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.moveFacing = facing;
        this.fireFacing = facing;
        this.health = 5;
        currentRotation = facing;
        tookTurn = true;
        isBullet = false;

        matrix.postRotate(90);
        this.image = bmp.createScaledBitmap(bmp, width, height, true);
        for(int i = 0; i < facing; i++) {
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
        }
    }

    public void doTurn(){
        if(randomNum.nextBoolean()) doMove();
        else doShoot();
        tookTurn = true;
    }

    public void doMove(){
        moveFacing = randomNum.nextInt(4);
        if(!map.move(this)){
            moveFacing = randomNum.nextInt(4);
            map.move(this);
        }
        while(currentRotation != moveFacing) {
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
            if (currentRotation != 3) currentRotation++;
            else currentRotation = 0;
        }
    }

    public void doShoot() {
        fireFacing = randomNum.nextInt(4);
        if(!map.shoot(this, 1, 1)){
            fireFacing = randomNum.nextInt(4);
            map.shoot(this, 1, 1);
        }
        while(currentRotation != fireFacing) {
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
            if (currentRotation != 3) currentRotation++;
            else currentRotation = 0;
        }
    }
}
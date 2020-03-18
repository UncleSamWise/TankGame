package com.cs240.tankgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class SpinningTurret extends Enemy {

    private boolean tickerFire = true;

    Matrix matrix = new Matrix();

    //Constructor
    public SpinningTurret(TankMap map, int col, int row, int facing, Bitmap bmp, int width, int height) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.moveFacing = facing;
        this.fireFacing = facing;
        this.health = 1;
        tookTurn = true;
        isBullet = false;
        isPlayer = false;

        matrix.postRotate(90);
        this.image = bmp.createScaledBitmap(bmp, width, height, true);
        for(int i = 0; i < facing; i++) {
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
        }
    }

    //Shoot 1 turn, rotate the next, ad infinitium
    public void doTurn(){
        if(tickerFire){
            tickerFire = false;
            doShoot();
        } else{
            doMove();
            tickerFire = true;
        }
        tookTurn = true;
    }

    //Rotate clockwise
    public void doMove(){
        if(fireFacing < 3){
            fireFacing++;
        } else fireFacing = 0;

        image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
    }

    //Fire
    public void doShoot() {
        if (!map.shoot(this, 1, 1)) {
            doMove();
            tickerFire = true;
        }
    }
}
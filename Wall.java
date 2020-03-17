package com.cs240.tankgame;

import android.graphics.Bitmap;

public class Wall extends Enemy {
    //Simply does nothing

    public Wall(TankMap map, int col, int row, Bitmap bmp) {
        this.map = map;
        this.col = col;
        this.row = row;
//        this.moveFacing = facing;
//        this.fireFacing = facing;
        this.health = 99999;
        this.icon = '1';
        this.image = bmp;
        tookTurn = true;
        isBullet = false;
    }

    public void doTurn() {
        health = 99999;
    }
}

package com.cs240.tankgame;

import android.graphics.Bitmap;

public class Wall extends Enemy {
    //Simply does nothing
    Bitmap[] bmp;

    public Wall(TankMap map, int col, int row, Bitmap[] bmp) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.health = 2;
        this.bmp = bmp;
        this.image = bmp[1];
        tookTurn = true;
        isBullet = false;
    }

    public void doTurn() {
        if(health == 1) image = bmp[7];
    }
}

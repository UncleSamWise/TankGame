package com.cs240.tankgame;

import android.graphics.Bitmap;

public class PlayerSprite extends Enemy{

    public PlayerSprite(TankMap map, int col, int row, int facing, Bitmap bmp, int width, int height) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.moveFacing = facing;
        this.fireFacing = facing;
        this.health = 1;
        tookTurn = true;
        isBullet = false;

        this.image = bmp.createScaledBitmap(bmp, width, height, true);
    }
}

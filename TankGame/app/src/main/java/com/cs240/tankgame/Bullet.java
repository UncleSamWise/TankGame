package com.cs240.tankgame;

import android.graphics.Bitmap;
import android.graphics.Matrix;

//Bullet can be used for both enemy shots and player shots
public class Bullet extends Enemy{

    private int speed; // Tiles per tick
    private int damage; // Healthpoints damage
    private Matrix matrix = new Matrix();

    public Bullet(TankMap map, int col, int row, int facing, int damage, int speed, Bitmap bmp, int width, int height){
        this.map = map;
        this.col = col;
        this.row = row;
        this.moveFacing = facing;
        this.fireFacing = facing;
        this.speed = speed;
        this.damage = damage;
        this.health = 99;
        isBullet = true;
        tookTurn = true;
        isPlayer = false;
        matrix.postRotate(90);
        this.image = bmp.createScaledBitmap(bmp, width, height, true);
        for(int i = 0; i < facing; i++) {
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
        }
    }

    public void doTurn(){
        doMove();
        tookTurn = true;
    }

    public void doMove(){
        for(int i = 0; i < speed; i++){
            if(!map.move(this)){
                doShoot();
            }
        }
    }

    public void doShoot(){
        if(moveFacing == 0){
            if(col > 0){
                map.getAtLoc(col-1, row).doHit(damage);
            }
        }
        if(moveFacing == 1){
            if(row < map.columns-1 ){
                map.getAtLoc(col, row+1).doHit(damage);
            }
        }
        if(moveFacing == 2){
            if(col < map.rows-1){
                map.getAtLoc(col+1, row).doHit(damage);
            }
        }
        if(moveFacing == 3){
            if(row > 0){
                map.getAtLoc(col, row-1).doHit(damage);
            }
        }
        doDie();
    }
}
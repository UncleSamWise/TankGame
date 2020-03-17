package com.cs240.tankgame;

import android.content.res.Resources;
import android.graphics.Bitmap;

public class Enemy{
    TankMap map; //pass map object as reference
    int col; //keep track of coordinates
    int row;
    int health; // <= 0 = dead, else alive
    int moveFacing; //0 = north, 1 = east, 2 = south, 3 = west
    int fireFacing;
    boolean tookTurn;
    boolean isBullet;
    Bitmap image;
    char icon; //For printing to console during testing; swap this out with whatever we use to render (sprites?)

    //screen display width and height
//    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//    int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    //Should run every tick
    public void doTurn(){}

    //Generally, only one of these two functions should run on each turn
    void doMove(){}
    void doShoot(){}

    //Run when getting hit
    public void doHit(int damage){
        health -= damage;
        if(health <= 0) doDie();
    }

    //Run when health <= 0
    public void doDie(){
        map.setAtLoc(null, col, row);
    }

    //draws to screen
//    public Bitmap drawEnemy(){
//        return drawEnemy();
//    }

    public String toString() {
        return icon + "";
    }
}

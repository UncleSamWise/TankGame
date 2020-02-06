package com.cs240.tankgame;
//Interface for enemies; I'll write some classes for this, feel free to write some of your own, too
public interface Enemy {

    private Enemy[][] grid; //pass 2d array as reference
    private int col;
    private int row;
    int health; // <= 0 = dead, else alive
    int facing; //0 = north, 1 = east, 2 = south, 3 = west

    //Simple return
    int getHealth();

    //Should run every tick
    void doTurn();

    //Generally, only one of these two functions should run on each turn
    void doMove();
    void doShoot();

    //Run when getting hit
    void doHit(int damage);

    //Run when health <= 0
    void doDie();
}
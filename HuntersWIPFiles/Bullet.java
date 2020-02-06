package com.cs240.tankgame;
//Class for bullet object; can be used for both player attacks and enemy attacks
//Will be finished after map code
public class Bullet implements Enemy{

    private Enemy[][] grid;
    int col;
    int row;
    private int movespeed; // Tiles per tick
    private int health; //Health
    private int facing; // //0 = north, 1 = east, 2 = south, 3 = west
    private int damage; // Healthpoints damage

    public Bullet(int movespeed, int facing, int damage){
        this.movespeed = movespeed;
        this.facing = facing;
        this.damage = damage;
    }

    private void doTurn(){
        doMove();
    }

    private void doMove(){
        int i = 0;
        while(i < movespeed){
            if(!Movement.move(this, grid, facing, col, row)){
                doShoot();
            }
        }
    }

    private void doShoot(){
        if(facing == 0){
            if(row > 0){
                grid[col][row-1].doHit(damage);
            }
        }
        if(facing == 1){
            if(col < grid.length-1){
                grid[col+1][row].doHit(damage);
            }
        }
        if(facing == 2){
            if(row < grid[0].length){
                grid[col][row+1].doHit(damage);
            }
        }
        if(facing == 3){
            if(col > 0){
                grid[col-1][row].doHit(damage);
            }
        }
        doDie();
    }

    private void doDie(){
        grid[col][row] = null;
    }

    public void doHit(int damage){
        health -= damage;
        if(health <= 0) doDie();
    }

    int getHealth() return health;

}
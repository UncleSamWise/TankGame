package com.cs240.tankgame;
public class SpinningTurret implements Enemy {
    private Enemy[][] grid; //pass reference to overarching 2d array to each enemy
    int col;
    int row;
    private int health = 1; // <= 0 = dead, else alive
    private int facing; //0 = north, 1 = east, 2 = south, 3 = west
    private boolean tickerFire = true;

    //Constructor
    public SpinningTurret(Enemy[][] grid, int col, int row, int facing) {
        this.grid = grid;
        this.col = col;
        this.row = row;
        this.facing = facing;
    }

    //Return health
    public int getHealth() {
    return health;
    }

    //Shoot 1 turn, rotate the next, ad infinitium
    public void doTurn(){
        if(tickerFire){
            doShoot();
            tickerFire = false;
        } else{
            doMove();
            tickerFire = true;
        }
    }

    //Rotate clockwise
    private void doMove(){
        if(facing < 3){
            facing++;
        } else facing = 0;
    }

    //Fire
    private void doShoot(){
        if(facing == 0){
            if(row > 0 && grid[col][row-1] == null){
                grid[col][row-1] = new Bullet(1, facing, 1);
            } else doMove();
        }
        if(facing == 1){
            if(col < grid.length-1 && grid[col+1][row] == null){
                grid[col+1][row] = new Bullet(1, facing, 1);
            } else doMove();
        }
        if(facing == 2){
            if(row < grid[0].length && grid[col][row+1] == null){
                grid[col][row+1] = new Bullet(1, facing, 1);
            } else doMove();
        }
        if(facing == 3){
            if(col > 0 && grid[col-1][row] == null){
                grid[col-1][row] = new Bullet(1, facing, 1);
            } else doMove();
        }
    }

    //Get hit
    public void doHit(int damage){
        health -= damage;
        if(health <= 0) doDie();
    }

    //Die
    private void doDie(){
        grid[col][row] = null;
    }
}
package com.cs240.tankgame;
public class Movement{

    public boolean move(Enemy enemy, Enemy[][] grid, int facing, int col, int row){
        if(facing == 0){
            if(row > 0 && grid[col][row-1] == null){
                grid[col][row-1] = enemy;
                grid[col][row] = null;
                enemy.row = enemy.row-1;
            } else return false;
        }
        if(facing == 1){
            if(col < grid.length-1 && grid[col+1][row] == null){
                grid[col+1][row] = enemy;
                grid[col][row] = null;
                enemy.row = enemy.col+1;
            } else return false;
        }
        if(facing == 2){
            if(row < grid[0].length && grid[col][row+1] == null){
                grid[col][row+1] = enemy;
                grid[col][row] = null;
                enemy.row = enemy.row+1;
            } else return false;
        }
        if(facing == 3){
            if(col > 0 && grid[col-1][row] == null){
                grid[col-1][row] = enemy;
                grid[col][row] = null;
                enemy.row = enemy.col-1;
            } else return false;
        }
        return true;
    }

}
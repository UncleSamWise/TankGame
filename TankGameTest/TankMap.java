package com.cs240.tankgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class TankMap {
    int columns;
    int rows;
    private int[][] maze;
    Enemy[][] grid;

    Bitmap bulletbmp;

    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private int cellWidth;
    private int cellHeight;


    public TankMap(Enemy[][] maze,/* int width, int height,*/ Bitmap[] bmp){
        columns = maze[0].length;
        rows = maze.length;
        //grid = maze;
        bulletbmp = bmp[4];
        //this.maze = maze;
        PerlinNoise n = new PerlinNoise(null, 1.0f, rows, columns);
        n.initialise();
        grid = n.returnGrid(this, bmp);
//        cellWidth = width;
//        cellHeight = height;
        //populate the grid with walls

//        for(int i = 0; i < maze.length; i++){
//            for(int j = 0; j < maze[0].length; j++){
//                {if(this.maze[i][j] == 1)
//                    this.addEnemy(new Wall(this, i, j, bmp[1]));
//                }
//            }
//        }

    }

    public Enemy getAtLoc(int col, int row){
        return grid[col][row];
    }

    public void addEnemy(Enemy enemy){
        grid[enemy.col][enemy.row] = enemy;
    }

    public void setAtLoc(Enemy enemy, int col, int row){
        grid[col][row] = enemy;
    }

    public void doTurns(){
        for(Enemy[] column : grid){
            for(Enemy current : column){
                if(current != null){
                    current.tookTurn = false;
                }
            }
        }
        for(Enemy[] column : grid){
            for(Enemy current : column){
                if(current != null && !current.tookTurn && current.isBullet){
                    current.doTurn();
                    //current.drawEnemy();
                }
            }
        }
        for(Enemy[] column : grid){
            for(Enemy current : column){
                if(current != null && !current.tookTurn){
                    current.doTurn();
                }
            }
        }
    }

    //Moves 1 tile forward, returns true if move is valid, otherwise false
    public boolean move(Enemy enemy){
        int facing = enemy.moveFacing;
        int row = enemy.row;
        int col = enemy.col;
        if(facing == 0){
            if(col > 0 && grid[col-1][row] == null){
                grid[col-1][row] = enemy;
                grid[col][row] = null;
                enemy.col = enemy.col-1;
                //enemy.drawEnemy();
            } else return false;
        }
        if(facing == 1){
            if(row < rows-1 && grid[col][row+1] == null){
                grid[col][row+1] = enemy;
                grid[col][row] = null;
                enemy.row = enemy.row+1;
            } else return false;
        }
        if(facing == 2){
            if(col < columns - 1 && grid[col+1][row] == null){
                grid[col+1][row] = enemy;
                grid[col][row] = null;
                enemy.col = enemy.col+1;
            } else return false;
        }
        if(facing == 3){
            if(row > 0 && grid[col][row-1] == null){
                grid[col][row-1] = enemy;
                grid[col][row] = null;
                enemy.row = enemy.row-1;
            } else return false;
        }
        return true;
    }

    //Spawn bullet 1 tile in front, returns true if valid, otherwise false
    public boolean shoot(Enemy enemy, int damage, int speed){
        int facing = enemy.fireFacing;
        int row = enemy.row;
        int col = enemy.col;
        if(facing == 0){
            if(col > 0 && grid[col-1][row] == null){
                addEnemy(new Bullet(this, col-1, row, facing, damage, speed, bulletbmp, cellWidth, cellHeight));
            } else return false;
        }
        if(facing == 1){
            if(row < rows-1 && grid[col][row+1] == null){
                addEnemy(new Bullet(this, col, row+1, facing, damage, speed, bulletbmp, cellWidth, cellHeight));
            } else return false;
        }
        if(facing == 2){
            if(col < columns-1 && grid[col+1][row] == null){
                addEnemy(new Bullet(this, col+1, row, facing, damage, speed, bulletbmp, cellWidth, cellHeight));
            } else return false;
        }
        if(facing == 3){
            if(row > 0 && grid[col][row-1] == null){
                addEnemy(new Bullet(this, col, row-1, facing, damage, speed, bulletbmp, cellWidth, cellHeight));
            } else return false;
        }
        return true;
    }

    public Enemy[][] render(){
//        System.out.println("____________________________");
        //Enemy[][] temp = new Enemy[grid.length][grid[0].length];
//        for(int i = 0; i < maze.length; i++){
//            for(int j = 0; j < maze[0].length; j++){
//                if(maze[i][j] == 0){
//                    this.addEnemy(new Wall(this, i, j, bmp[1]));
//                }
//            }
//        }
//        for(int i = 0; i < maze.length; i++){
//            for(int ii = 0; ii < maze[0].length; ii++){
//                if(grid[i][ii].toString().equals("s")){
//                   maze[i][ii] = 3;
//                } else if(grid[i][ii].toString().equals("b")){
//                    maze[i][ii] = 4;
//                    //canvas.drawBitmap(grid[i][ii].image, screenHeight/grid[i][ii].row, screenWidth/grid[i][ii].col, null);
//                }
//            }
//        }

//        System.out.println("____________________________");
        return grid;
    }
}
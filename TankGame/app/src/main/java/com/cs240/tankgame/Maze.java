package com.cs240.tankgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Maze {
    private RectF drawRect = new RectF();
    private Bitmap[] bitmaps;
    private int[][] tileType;
    private float screenWidth, screenHeight;
    
    public Maze(Bitmap[] bitmaps, int[][] tileType, float xCellCountOnScreen, float yCellCountOnScreen, float screenWidth, float screenHeight){
        this.bitmaps = bitmaps;
        this.tileType = tileType;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        drawRect.set(0, 0, screenWidth / xCellCountOnScreen, screenHeight / yCellCountOnScreen);
    }
    
    public int getType(int x, int y){
        if(y < tileType.length && x < tileType[y].length) return tileType[y][x];
        return 0;
    }

    public float getCellWidth(){ 
      return drawRect.width(); 
    }
    
    public float getCellHeight(){ 
      return drawRect.height(); 
    }
    
    public void drawMaze(Canvas canvas, float viewX, float viewY){
        int tileX = 0;
        int tileY = 0;
        float xCoord = -viewX;
        float yCoord = -viewY;

        while(tileY < tileType.length && yCoord <= screenHeight){
            tileX = 0;
            xCoord = -viewX;

            while(tileX < tileType[tileY].length && xCoord <= screenWidth){
                if(bitmaps[tileType[tileY][tileX]] != null){

                    if(xCoord + drawRect.width() >= 0 && yCoord + drawRect.height() >= 0){
                        drawRect.offsetTo(xCoord, yCoord);
                        canvas.drawBitmap(bitmaps[tileType[tileY][tileX]], null, drawRect, null);
                    }
                }

                tileX++;
                xCoord += drawRect.width();
            }

            tileY++;
            yCoord += drawRect.height();
        }
    }
}

/*
OnCreate 
int[][] maze = {
        {0, 0, 0, 0, 0},
        {0, 1, 2, 1, 0},
        {0, 0, 0, 1, 0},
        {1, 1, 2, 1, 0},
        {0, 0, 0, 0, 0}
};

Bitmap[] bitmaps = {
        BitmapFactory.decodeResource(getResources(), R.drawable.floor),
        BitmapFactory.decodeResource(getResources(), R.drawable.firstwall)
        BitmapFactory.decodeResource(getResources(), R.drawable.secondwall)
};

maze = new Maze(bitmaps, maze, 5, 5, 480, 320);

OnDraw
maze.draw(canvas, viewX, viewY);

*/

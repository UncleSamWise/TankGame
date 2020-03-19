package com.cs240.tankgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.content.res.Resources;

public class Maze {
    private RectF drawRect = new RectF();
    private Bitmap[] bitmaps;
    private Enemy[][] tileType;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    
    public Maze(Bitmap[] bitmaps, Enemy[][] tileType, float xCellCountOnScreen, float yCellCountOnScreen){
        this.bitmaps = bitmaps;
        this.tileType = tileType;
        drawRect.set(0, 0, screenWidth / xCellCountOnScreen, screenHeight / (yCellCountOnScreen));
    }
    
    public Enemy getType(int x, int y){
        if(y < tileType.length && x < tileType[y].length) return tileType[y][x];
        return null;
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
                if(tileType[tileY][tileX] != null){

                    if(xCoord + drawRect.width() >= 0 && yCoord + drawRect.height() >= 0){
                        drawRect.offsetTo(xCoord +17, yCoord + 11);
                        canvas.drawBitmap(bitmaps[0], null, drawRect, null);
                        canvas.drawBitmap(tileType[tileY][tileX].image, null, drawRect, null);
                    }
                } else{
                    if(xCoord + drawRect.width() >= 0 && yCoord + drawRect.height() >= 0){
                        drawRect.offsetTo(xCoord +17, yCoord + 11);
                        canvas.drawBitmap(bitmaps[0], null, drawRect, null);
                    }
                }

                tileX++;
                xCoord += drawRect.width();
            }

            tileY++;
            yCoord += drawRect.height();
        }
    }

//    private Bitmap[] bitmaps = {
//            BitmapFactory.decodeResource(getResources(), R.drawable.metal),
//            BitmapFactory.decodeResource(getResources(), R.drawable.best),
//            BitmapFactory.decodeResource(getResources(), R.drawable.metal)
//    };
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

package com.cs240.tankgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import androidx.core.view.GestureDetectorCompat;

/*
 *
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private GestureDetectorCompat gestureDetectorCompat = null;
    private DetectSwipeGestureListener gestureListener;

    private Maze mazeFinal;
    private TankMap theMap;

    private int mazeWidth = 17;
    private int mazeHeight = 10;

    public static Canvas canvas;

    //up = 2. down = 1. right = 3. left = 4. tap = 0.
    private int direction;
    private boolean turnDone;

    public GameView(Context context) {
        super(context);

        thread = new MainThread(getHolder(), this);
        gestureListener = new DetectSwipeGestureListener();
        gestureDetectorCompat = new GestureDetectorCompat(getContext(), gestureListener);
        surfaceCreated(getHolder());
        direction = -1;
        turnDone = true;
        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // MotionEvent reports input details from the touch screen
        // and other input controls.
        if(turnDone) {
            gestureDetectorCompat.onTouchEvent(event);
            direction = gestureListener.getDirection();
        } //else direction = -1;

        return true;
    }

    //bitmap directory
    Bitmap[] bitmaps = {
            BitmapFactory.decodeResource(getResources(), R.drawable.floor),
            BitmapFactory.decodeResource(getResources(), R.drawable.wall),
            BitmapFactory.decodeResource(getResources(), R.drawable.playertank),
            BitmapFactory.decodeResource(getResources(), R.drawable.enemytank),
            BitmapFactory.decodeResource(getResources(), R.drawable.linetank),
            BitmapFactory.decodeResource(getResources(), R.drawable.turret),
            BitmapFactory.decodeResource(getResources(), R.drawable.bullet),
            BitmapFactory.decodeResource(getResources(), R.drawable.wall2),
    };

    //calls images and starts the main thread
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Enemy[][] maze = new Enemy[mazeWidth][mazeHeight];
        mazeFinal = new Maze(bitmaps, maze, mazeHeight, mazeWidth);

        int cellHeight = (int)mazeFinal.getCellHeight();
        int cellWidth = (int)mazeFinal.getCellWidth();

        theMap = new TankMap(maze, cellWidth, cellHeight, bitmaps);
        theMap.addEnemy(new LineTank(theMap, 2, 5, 3, bitmaps[4], cellWidth, cellHeight));
        theMap.addEnemy(new SpinningTurret(theMap, 2, 7, 3, bitmaps[5], cellWidth, cellHeight));
        theMap.addEnemy(new RandomTank(theMap, 5, 5, 2, bitmaps[3], cellWidth, cellHeight));

        //theMap.addEnemy(new PlayerSprite(theMap, 7, 7, 2, bitmaps[2], cellWidth, cellHeight));

        //theMap.populate();
        maze = theMap.grid;
        mazeFinal = new Maze(bitmaps, maze, mazeHeight, mazeWidth);

        //draw();
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch(InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        turnDone = false;
        if(direction != -1){
            theMap.doTurns(direction);
        }
        direction = -1;
        for(int i = -1000000000; i <= 0; i++);
        turnDone = true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            //canvas.drawColor(Color.WHITE);
            mazeFinal.drawMaze(canvas, mazeWidth, mazeHeight);
        }
    }


}
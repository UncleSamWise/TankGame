package com.cs240.tankgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.app.Activity;

import androidx.core.view.GestureDetectorCompat;

import java.util.Calendar;

/*
 *
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private GestureDetectorCompat gestureDetectorCompat = null;
    private DetectSwipeGestureListener gestureListener;

    private CharacterSprite characterSprite;
    Maze mazeFinal;

    private int mazeWidth = 17;
    private int mazeHeight = 10;

    //up = 2. down = 1. right = 3. left = 4. tap = 0.
    private int direction;
    private boolean touched;

    volatile float x1, y1;
    volatile float x2, y2;
    private long startClickTime;
    static final int MIN_DISTANCE = 300;
    static final int MAX_SWIPE_TIME = 200;

    public GameView(Context context) {
        super(context);

        thread = new MainThread(getHolder(), this);
        gestureListener = new DetectSwipeGestureListener();
        gestureDetectorCompat = new GestureDetectorCompat(getContext(), gestureListener);
        surfaceCreated(getHolder());
        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        gestureDetectorCompat.onTouchEvent(event);
        direction = gestureListener.getDirection();
        return true;
    }

    Bitmap[] bitmaps = {
            BitmapFactory.decodeResource(getResources(), R.drawable.grass1),
            BitmapFactory.decodeResource(getResources(), R.drawable.best),
            BitmapFactory.decodeResource(getResources(), R.drawable.metal)
    };

    //calls images and starts the main thread
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        PerlinNoise n = new PerlinNoise(null, 1.0f, mazeWidth, mazeHeight);
        n.initialise();
        int[][] maze = n.returnGrid();
        mazeFinal = new Maze(bitmaps, maze, mazeHeight, mazeWidth);

        int cellHeight = (int)mazeFinal.getCellHeight();
        int cellWidth = (int)mazeFinal.getCellWidth();
        //use this to access a (.png) from main/res/drawable
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.playertank),
                                              cellWidth, cellHeight);
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
        characterSprite.update(direction);
        direction = -1;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas!=null) {
            //canvas.drawColor(Color.WHITE);
            mazeFinal.drawMaze(canvas, mazeWidth, mazeHeight);
            characterSprite.draw(canvas);

            //draws a red square in the top left of the screen
            //dont

//            Paint paint = new Paint();
//            paint.setColor(Color.rgb(250, 0, 0));
//            canvas.drawRect(100, 100, 200, 200, paint);
        }
    }


}
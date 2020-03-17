package com.cs240.tankgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.app.Activity;
import java.util.Timer;
import java.util.TimerTask;
import androidx.core.view.GestureDetectorCompat;

/*
 *
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private GestureDetectorCompat gestureDetectorCompat = null;
    private DetectSwipeGestureListener gestureListener;

    private CharacterSprite characterSprite;
    private Maze mazeFinal;
    private TankMap theMap;

    private int mazeWidth = 17;
    private int mazeHeight = 10;

    public static Canvas canvas;


    //up = 2. down = 1. right = 3. left = 4. tap = 0.
    private int direction;

    private long startTime;

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
        // and other input controls.
        //startTime = SystemClock.elapsedRealtime();
//        if(event.getPointerCount() > 1) {
//            //System.out.println("Multitouch detected!");
//            return true;
//        }
        gestureDetectorCompat.onTouchEvent(event);
        direction = gestureListener.getDirection();
//        long difference = 0;
//        while(difference < 2000) {
//            difference = SystemClock.elapsedRealtime() - startTime;
//        }
        //update();

        return true;
    }

    Bitmap[] bitmaps = {
            BitmapFactory.decodeResource(getResources(), R.drawable.grass1),
            BitmapFactory.decodeResource(getResources(), R.drawable.best),
            BitmapFactory.decodeResource(getResources(), R.drawable.playertank),
            BitmapFactory.decodeResource(getResources(), R.drawable.enemytank),
            BitmapFactory.decodeResource(getResources(), R.drawable.bullet),
            //BitmapFactory.decodeResource(getResources(), R.drawable.best),
            BitmapFactory.decodeResource(getResources(), R.drawable.metal)
    };

    //calls images and starts the main thread
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Enemy[][] maze = new Enemy[mazeWidth][mazeHeight];
        mazeFinal = new Maze(bitmaps, maze, mazeHeight, mazeWidth);
//        PerlinNoise n = new PerlinNoise(null, 1.0f, mazeWidth, mazeHeight);
//        n.initialise();
//        maze = n.returnGrid();


        int cellHeight = (int)mazeFinal.getCellHeight();
        int cellWidth = (int)mazeFinal.getCellWidth();

        theMap = new TankMap(maze, cellWidth, cellHeight, bitmaps);
        theMap.addEnemy(new SpinningTurret(theMap, 2, 5, 3, bitmaps[3], cellWidth, cellHeight));
        theMap.addEnemy(new SpinningTurret(theMap, 2, 7, 3, bitmaps[3], cellWidth, cellHeight));
        theMap.addEnemy(new Bullet(theMap, 1, 6, 4, 1, 1, bitmaps[4], cellWidth, cellHeight));

        //theMap.populate();

        //maze = theMap.render();

        //use this to access a (.png) from main/res/drawable
        characterSprite = new CharacterSprite(bitmaps[2], cellWidth, cellHeight);
        maze = theMap.grid;
        mazeFinal = new Maze(bitmaps, maze, mazeHeight, mazeWidth);


        //draw();
        thread.setRunning(true);
        thread.start();

    }

    public Maze returnMaze(){
        return mazeFinal;
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
        if(direction != -1){
            theMap.doTurns();
        }
        direction = -1;
    }

    @Override
    public void draw(Canvas canvas) {
        if(canvas!=null) {
            canvas.drawColor(Color.WHITE);
            mazeFinal.drawMaze(canvas, mazeWidth, mazeHeight);
            theMap.render();
            characterSprite.drawSprite(canvas);
        }
    }


}
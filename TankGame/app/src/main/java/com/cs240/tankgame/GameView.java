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

import java.util.Calendar;

/*
 *
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private CharacterSprite characterSprite;
    Maze mazeFinal;
    //up = 2. down = 1. right = 3. left = 4. tap = 0.
    private int direction;

    volatile float x1, y1;
    private float x2, y2;
    private long startClickTime;
    static final int MIN_DISTANCE = 150;
    static final int MAX_SWIPE_TIME = 200;

    public GameView(Context context) {
        super(context);

        thread = new MainThread(getHolder(), this);
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

//        float x = e.getX();
//        float y = e.getY();

        x1 = event.getX();
        y1 = event.getY();

        switch (event.getAction()) {
            //case MotionEvent.ACTION_BUTTON_PRESS:

            case MotionEvent.ACTION_DOWN:
                //startClickTime = Calendar.getInstance().getTimeInMillis();
//                x2 = event.getX();
//                y2 = event.getY();
                float deltaY = event.getX() - y1;
                float deltaX = event.getY() - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE /*&& clickDuration < MAX_SWIPE_TIME*/){
                    //left to right
                    direction = 3;
                }else if(deltaX > MIN_DISTANCE /*&& clickDuration < MAX_SWIPE_TIME*/){
                    //right to left
                    direction = 4;
                }else if(deltaY > MIN_DISTANCE){
                    //down
                    direction = 1;
                }else if(Math.abs(deltaY) > MIN_DISTANCE){
                    //up
                    direction = 2;
                }else{
                    //button press
                    direction = 0;
                }
                break;
            case MotionEvent.ACTION_UP:
                //long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;


                break;
            case MotionEvent.ACTION_MOVE:

                break;
//            case MotionEvent.ACTION_CANCEL:
//                //touched = false;
//                break;
//            case MotionEvent.ACTION_OUTSIDE:
//                //touched = false;
//                break;
            default:
        }
        //direction = 0;
        return true;
        //return super.onTouchEvent(e);
    }

    Bitmap[] bitmaps = {
            BitmapFactory.decodeResource(getResources(), R.drawable.metal),
            BitmapFactory.decodeResource(getResources(), R.drawable.best),
            BitmapFactory.decodeResource(getResources(), R.drawable.metal)
    };

    //calls images and starts the main thread
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        int mazeWidth = 10;
        int mazeHeight = 10;

        PerlinNoise n = new PerlinNoise(null, 1.0f, mazeWidth, mazeHeight);
        n.initialise();
        int[][] maze = n.returnGrid();
        mazeFinal = new Maze(bitmaps, maze, mazeWidth, mazeHeight);
        //use this to access a (.png) from main/res/drawable
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.best));

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
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas!=null) {
            //canvas.drawColor(Color.WHITE);
            mazeFinal.drawMaze(canvas, 10, 10);
            //characterSprite.draw(canvas);

            //draws a red square in the top left of the screen
            //dont

//            Paint paint = new Paint();
//            paint.setColor(Color.rgb(250, 0, 0));
//            canvas.drawRect(100, 100, 200, 200, paint);
        }
    }


}

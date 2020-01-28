package com.cs240.tankgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;


/*
 *
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private CharacterSprite characterSprite;

    public GameView(Context context) {
        super(context);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //use this to access a (.png) from main/res/drawable
        //characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable./*example*/));


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
        characterSprite.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(canvas!=null) {
            characterSprite.draw(canvas);

            //draws a red square in the top left of the screen
            //dont
//            canvas.drawColor(Color.WHITE);
//            Paint paint = new Paint();
//            paint.setColor(Color.rgb(250, 0, 0));
//            canvas.drawRect(100, 100, 200, 200, paint);
        }
    }


}

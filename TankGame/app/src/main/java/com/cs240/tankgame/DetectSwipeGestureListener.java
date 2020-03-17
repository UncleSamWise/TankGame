package com.cs240.tankgame;

import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Jerry on 4/18/2018.
 */

public class DetectSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

    private int direction;

    // Minimal x and y axis swipe distance.
    private static int MIN_SWIPE_DISTANCE_X = 50;
    private static int MIN_SWIPE_DISTANCE_Y = 50;

    // Maximal x and y axis swipe distance.
    private static int MAX_SWIPE_DISTANCE_X = 1000;
    private static int MAX_SWIPE_DISTANCE_Y = 1000;

    private long lastClickTime;
    static final int MAX_SWIPE_TIME = 200;

    /* This method is invoked when a swipe gesture happened. */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

//        if (SystemClock.elapsedRealtime() - lastClickTime < 9000) {
//            return true;
//        }
//        lastClickTime = SystemClock.elapsedRealtime();

        // Get swipe delta value in x axis.
        float deltaX = e1.getX() - e2.getX();

        // Get swipe delta value in y axis.
        float deltaY = e1.getY() - e2.getY();

        // Get absolute value.
        float deltaXAbs = Math.abs(deltaX);
        float deltaYAbs = Math.abs(deltaY);

        // Only when swipe distance between minimal and maximal distance value then we treat it as effective swipe
        if((deltaXAbs >= MIN_SWIPE_DISTANCE_X) && (deltaXAbs <= MAX_SWIPE_DISTANCE_X))
        {
            if(deltaX > 0)
            {
                //left
                direction = 3;
            }else
            {
                //right
                direction = 4;
            }
        }

        if((deltaYAbs >= MIN_SWIPE_DISTANCE_Y) && (deltaYAbs <= MAX_SWIPE_DISTANCE_Y))
        {
            if(deltaY > 0)
            {
                //up
                direction = 2;
            }else
            {
                //down
                direction = 1;
            }
        }


        return true;
    }

    // Invoked when single tap screen.
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        direction = 0;
        return true;
    }

    // Invoked when double tap screen.
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        //this.activity.displayMessage("Double tap occurred.");
        return true;
    }

    public int getDirection() {
        return direction;
    }

}

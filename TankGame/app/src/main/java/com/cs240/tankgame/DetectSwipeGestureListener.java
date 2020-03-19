package com.cs240.tankgame;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 *
 */

public class DetectSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

    private int direction;

    // Minimal x and y axis swipe distance.
    private static final int MIN_SWIPE_DISTANCE_X = 500;
    private static final int MIN_SWIPE_DISTANCE_Y = 500;

    // Maximal x and y axis swipe distance.
    private static final int MAX_SWIPE_DISTANCE_X = 1000;
    private static final int MAX_SWIPE_DISTANCE_Y = 1000;

    /* This method is invoked when a swipe gesture happened. */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        // Get first point of contact for swipe
        float x1 = e1.getX();
        float y1 = e1.getY();

        //wait a bit
        for(int i = -1000000000; i <= 0; i++);

        // Get final point of contact for swipe
        float x2 = e2.getX();
        float y2 = e2.getY();

        //find the length and direction of swipe
        float deltaX = x1 - x2;
        float deltaY = y1 - y2;

        // Get absolute value.
        float deltaXAbs = Math.abs(deltaX);
        float deltaYAbs = Math.abs(deltaY);

        // Only when swipe distance between minimal and maximal distance value then we treat it as effective swipe
        if((deltaXAbs >= MIN_SWIPE_DISTANCE_X) && (deltaXAbs <= MAX_SWIPE_DISTANCE_X)) {
            if(deltaX > 0) {//left
                direction = 4;
            }else{//right
                direction = 2;
            }
        }else if((deltaYAbs >= MIN_SWIPE_DISTANCE_Y) && (deltaYAbs <= MAX_SWIPE_DISTANCE_Y)) {
            if(deltaY > 0){//up
                direction = 1;
            }else{//down
                direction = 3;
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
//    @Override
//    public boolean onDoubleTap(MotionEvent e) {
//        //this.activity.displayMessage("Double tap occurred.");
//        return true;
//    }

    public int getDirection() {
        return direction;
    }

}

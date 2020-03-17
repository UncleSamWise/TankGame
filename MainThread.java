package com.cs240.tankgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

/*
 *
 */

public class MainThread extends Thread {
    private GameView gameView;
    private SurfaceHolder surfaceHolder;
    Bitmap tempCanvasBitmap;
    Canvas tempCanvas;
    private boolean running;
    public static Canvas canvas;
    private int targetFPS = 1;
    private double averageFPS;

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels - 1;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

    }

    @Override
    public void run() {

        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount =0;
        long targetTime = 1000/targetFPS;

//        tempCanvasBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
//        tempCanvas = new Canvas();
//        tempCanvas.setBitmap(tempCanvasBitmap);

        while(running) {
            startTime = System.nanoTime();
            canvas = null;
            //tempCanvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.update();
                    //this.gameView.draw(tempCanvas);
                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {}

            finally{
                if(canvas!=null) {
                    try {
                        //canvas.drawBitmap(tempCanvasBitmap, 0, 0, null);
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime-timeMillis;

            try{
                this.sleep(waitTime);
            }catch(Exception e){}

            totalTime += System.nanoTime()-startTime;
            frameCount++;
            if(frameCount == targetFPS) {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount =0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }
    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }
}
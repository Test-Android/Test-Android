package com.nicodangelo.gametest1;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{
    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder holder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder holder, GamePanel gamePanel)
    {
        super();
        this.holder = holder;
        this.gamePanel = gamePanel;
    }

    public void run()
    {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000/FPS;

        while(running)
        {
            startTime = System.nanoTime();
            canvas = null;

            //try locking the canvas for pixel editing
            try
            {
                canvas = this.holder.lockCanvas();
                synchronized (holder)
                {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }catch(Exception e){

            }
            finally
            {
                if(canvas!=null)
                {
                    try
                    {
                        holder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }

            //this will equal how much time it took the game to uodate and draw once:)
            timeMillis = System.nanoTime() - startTime / 1000000;
            waitTime = targetTime - timeMillis;

            try
            {
                this.sleep(waitTime);
            }catch(Exception e){}

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if(frameCount == FPS)
            {
                averageFPS = 1000 / ((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println("AVERAGE FPS -------- " + averageFPS);
            }
        }
    }

    public void setRunning(boolean r)
    {
        running = r;
    }
}

package com.nicodangelo.changecolor;

import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity  implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener
{
    private TextView swipeText;
    private GestureDetectorCompat gestureDectector;
    private int colour = Color.argb(255,255,255,255);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeText = (TextView) findViewById(R.id.swipeText);
        this.gestureDectector = new GestureDetectorCompat(this,this);
        gestureDectector.setOnDoubleTapListener(this);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
    //begin gesture that we are using
///////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
    {
        System.out.println("Swiping");
        int r = 255;
        int g = 255;
        int b = 255;
        for(int x = 0; x <100; x--)
        {
            r = r - 1;
            g = g - 1;
            b = b - 1;
            colour = Color.argb(255,r,g,b);
            swipeText.setBackgroundColor(colour);
        }


        return true;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////
    //End gesture that we are using
///////////////////////////////////////////////////////////////////////////////////////////////////



    ///////////////////////Begin Gestures///////////////////////

    public boolean onSingleTapConfirmed(MotionEvent e)
    {
        return false;
    }

    public boolean onDoubleTap(MotionEvent e)
    {
        return false;
    }

    public boolean onDoubleTapEvent(MotionEvent e)
    {
        return false;
    }

    public boolean onDown(MotionEvent e)
    {
        return false;
    }

    public void onShowPress(MotionEvent e)
    {
    }

    public boolean onSingleTapUp(MotionEvent e)
    {
        return false;
    }

    public void onLongPress(MotionEvent e)
    {
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    {
        return false;
    }

    ///////////////////////End Gestures///////////////////////


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //now it will look for ours first
        this.gestureDectector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


/*
100% — FF
95% — F2
90% — E6
85% — D9
80% — CC
75% — BF
70% — B3
65% — A6
60% — 99
55% — 8C
50% — 80
45% — 73
40% — 66
35% — 59
30% — 4D
25% — 40
20% — 33
15% — 26
10% — 1A
5% — 0D
0% — 00
 */

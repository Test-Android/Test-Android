package com.nicodangelo.swipeme;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;


public class MainActivity extends ActionBarActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener
{

    //this is just a reference to the TextView
    private TextView nicoMessage;
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nicoMessage = (TextView) findViewById(R.id.nicoMessage);
        this.gestureDetector = new GestureDetectorCompat(this,this);
        gestureDetector.setOnDoubleTapListener(this);
    }

    ///////////////////////Begin Gestures///////////////////////

    public boolean onSingleTapConfirmed(MotionEvent e)
    {
        nicoMessage.setText("onSingleTapConfirmed");
        return true;
    }

    public boolean onDoubleTap(MotionEvent e)
    {
        nicoMessage.setText("onDoubleTap");
        return true;
    }

    public boolean onDoubleTapEvent(MotionEvent e)
    {
        nicoMessage.setText("onDoubleTapEvent");
        return true;
    }

    public boolean onDown(MotionEvent e)
    {
        nicoMessage.setText("onDown");
        return true;
    }

    public void onShowPress(MotionEvent e)
    {
        nicoMessage.setText("onShowPress");
    }

    public boolean onSingleTapUp(MotionEvent e)
    {
        nicoMessage.setText("onSingleTapUp");
        return true;
    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
    {
        nicoMessage.setText("onScroll");
        return true;
    }

    public void onLongPress(MotionEvent e)
    {
        nicoMessage.setText("onLongPress");
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
    {
        nicoMessage.setText("onFling");
        return true;
    }



    ///////////////////////End Gestures///////////////////////


    //needs to override the default touch event for the device.
    //if it was a touch gesture we made then handle it the way we want to
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //now it will look for ours first
        this.gestureDetector.onTouchEvent(event);
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

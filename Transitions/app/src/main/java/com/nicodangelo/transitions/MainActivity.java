package com.nicodangelo.transitions;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.RelativeLayout;
import android.transition.TransitionManager;


public class MainActivity extends ActionBarActivity
{

    ViewGroup nico_Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nico_Layout = (ViewGroup) findViewById(R.id.nico_layout);

        nico_Layout.setOnTouchListener(
                new RelativeLayout.OnTouchListener()
                {
                    public boolean onTouch(View v, MotionEvent event)
                    {
                        moveButton();
                        return true;
                    }
                }
        );
    }

    public void moveButton()
    {
        View nico_button = findViewById(R.id.nico_button);

        TransitionManager.beginDelayedTransition(nico_Layout);

        //Change the position of the button
        RelativeLayout.LayoutParams positionRules = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);

        positionRules.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        positionRules.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        nico_button.setLayoutParams(positionRules);

        //change the size of the button
        ViewGroup.LayoutParams sizeRules = nico_button.getLayoutParams();
        sizeRules.width = 450;
        sizeRules.height = 300;
        nico_button.setLayoutParams(sizeRules);
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

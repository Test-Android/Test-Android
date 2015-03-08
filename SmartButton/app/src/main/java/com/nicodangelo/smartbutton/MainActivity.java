package com.nicodangelo.smartbutton;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;


public class MainActivity extends ActionBarActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this creates a object in reference to the button on the screen
        Button nicoButton = (Button) findViewById(R.id.nicoButton);

        nicoButton.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        TextView nicoText = (TextView) findViewById(R.id.nicoText);
                        nicoText.setText("YAY");
                    }
                }
        );

        nicoButton.setOnLongClickListener(
                new Button.OnLongClickListener()
                {
                    public boolean onLongClick(View v)
                    {
                        TextView nicoText = (TextView) findViewById(R.id.nicoText);
                        nicoText.setText("THIS A LONG ONE");
                        return true;
                    }
                }
        );
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

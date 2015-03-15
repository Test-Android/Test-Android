package com.nicodangelo.threads;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;


public class MainActivity extends ActionBarActivity
{

    //Create a handler to change the text outside of the thread!! we created
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            TextView nicosText = (TextView) findViewById(R.id.nicosText);
            nicosText.setText("Nice Job Hoss!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickNicosButton(View view)
    {
        //there is a rule for using threads though...
        //you never want to update the interface inside a thread that is not the main thread... (or a handler)
        //I dont know what will happen I just know that that is very very bad and bad things will come of it:)
        //To do that item do it in the main thread or you can use the things called HANDLERS!!!!!!! thos will be perfect:)
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                Long futureTime = System.currentTimeMillis() + 10000;
                while(System.currentTimeMillis() < futureTime)
                {
                    synchronized (this)
                    {
                        try
                        {
                            wait(futureTime - System.currentTimeMillis());
                        }
                        catch(Exception e){}
                    }
                }
                //calls the handler and takes an int (this is empty so i put 0..)
                handler.sendEmptyMessage(0);
            }
        };

        //now we just create the thread and add our runnable code to it
        Thread nicosThread = new Thread(r);

        //This will start the new thread!
        nicosThread.start();

        TextView nicosText = (TextView) findViewById(R.id.nicosText);
        //this will now happen instaintly and it will then change again after 10 secconds
        nicosText.setText("Wait 10 seconds...");
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

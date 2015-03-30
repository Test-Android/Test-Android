package com.nicodangelo.conversioncalc;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity
{
    private EditText input;
    private TextView output;
    private Button itemSelect1;
    private Button itemSelect2;
    private Button convert;
    private AlertDialog.Builder a;
    private AlertDialog ad;
    private AlertDialog.Builder b;
    private AlertDialog bd;
    private double inputNum;
    private double outputNum;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private String index1;
    private String index2;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void makeConversion(View view)
    {
        layout1.removeAllViews();
        layout2.removeAllViews();

        input = (EditText) findViewById(R.id.input);
        output = (TextView) findViewById(R.id.output);

        inputNum = Double.parseDouble(input.getText().toString());

        //ALL THE GOD FORSAKEN IF STATEMENTS!
        if(index1.equals(index2)) {outputNum = inputNum;}
        if(index1.equals("ounces") && index2.equals("grams")) {outputNum = inputNum * 28.3;}
        if(index1.equals("grams") && index2.equals("ounces")) {outputNum = inputNum * .0353;}

        output.setText(Double.toString(outputNum));
    }

    public void firstConversion(View view)
    {
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        //THE FIRST DIALOG BOX AND ITS ATTRIBUTES

        itemSelect1 = (Button) findViewById(R.id.itemSelect1);

        //create the Alert Dialog 1
        a = new AlertDialog.Builder(this)
                .setTitle("Measurements")
                .setMessage("Pick a measurement to convert from");
        a.setCancelable(false);

        //Create the button
        final Button ounces1 = new Button(MainActivity.this);
        final Button grams1 = new Button(MainActivity.this);

        //set the name to the buttons
        ounces1.setText("ounces");
        grams1.setText("grams");

        //all the on click listeners...
        ounces1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "ounces";
                itemSelect1.setText("ounces");
            }
        });

        grams1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ad.dismiss();
                index1 = "grams";
                itemSelect1.setText("grams");

            }
        });

        //NOW ADD THE STUFF CREATED ABOVE TO THE VIEW WITH A NEW LAYOUT

        layout1 = new LinearLayout(MainActivity.this);
        layout1.setOrientation(LinearLayout.VERTICAL);
        //Add the buttons to the layout
        layout1.addView(ounces1);
        layout1.addView(grams1);
        //now set the view and create it
        a.setView(layout1);
        ad = a.create();
        ad = a.show();
    }

    public void secondConversion(View view)
    {
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        //THE SECOND DIALOG BOX AND ITS ATTRIBUTES

        itemSelect2 = (Button) findViewById(R.id.itemSelect2);

        b = new AlertDialog.Builder(this)
                .setTitle("Measurements")
                .setMessage("Pick a measurement to convert from");
        b.setCancelable(false);

        //Create the button
        final Button ounces2 = new Button(MainActivity.this);
        final Button grams2 = new Button(MainActivity.this);

        //set the name to the buttons
        ounces2.setText("ounces");
        grams2.setText("grams");

        //all the on click listeners...
        ounces2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "ounces";
                itemSelect2.setText("ounces");
            }
        });

        grams2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                bd.dismiss();
                index2 = "grams";
                itemSelect2.setText("grams");
            }
        });

        layout2 = new LinearLayout(MainActivity.this);
        layout2.setOrientation(LinearLayout.VERTICAL);
        //Add the buttons to the layout
        layout2.addView(ounces2);
        layout2.addView(grams2);
        //now set the view and create it
        b.setView(layout2);
        bd = b.create();
        bd = b.show();
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

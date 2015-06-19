package com.nicodangelo.tablemyexampleanimation;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity
{
    ListView l;
    int[] images = {R.drawable.ic_action_refresh, R.drawable.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l = (ListView) findViewById(R.id.listview);
        CustomAdapter adapter = new CustomAdapter(this, Cheeses.sCheeseStrings, images);
        l.setAdapter(adapter);
    }
}

class CustomAdapter extends ArrayAdapter<String>
{

    Context context;
    int images[];
    String names[];
    boolean swapImages = false;

    CustomAdapter(Context c, String[] names, int images[])
    {
        super(c,R.layout.custom_row, R.id.textView, names);
        this.context = c;
        this.images = images;
        this.names = names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_row, parent, false);

        ImageView image = (ImageView) row.findViewById(R.id.imageView);
        TextView text = (TextView) row.findViewById(R.id.textView);

        //all this does is swap the used image back and forth
        if(swapImages)
        {
            image.setImageResource(images[0]);
            swapImages = false;
        }
        else
        {
            image.setImageResource(images[1]);
            swapImages = true;
        }
        ///////////////////////////////////////////////////

        text.setText(names[position]);

        return row;
    }
}



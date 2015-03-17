package com.nicodangelo.listexample;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] foods = {"Bacon", "Ham", "Tuna", "Candy", "Meatball", "Potato", "grape", "apple", "sandwich", "pear", "chicken", "water", "nuts", "popcorn", "peanut Butter"};
        ListAdapter nicosAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,foods);
        ListView nicosListView = (ListView) findViewById(R.id.nicosListView);
        nicosListView.setAdapter(nicosAdapter);

        nicosListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        String food = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, food, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}

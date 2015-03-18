package com.nicodangelo.sqlitesample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity
{
    EditText nicosInput;
    TextView nicosText;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nicosInput = (EditText) findViewById(R.id.nicosInput);
        nicosText = (TextView) findViewById(R.id.nicosText);
        dbHandler = new MyDBHandler(this,null,null,1);
        printDataBase();
    }

    //add a product to the database
    public void addButtonOnClick(View view)
    {
        Products product = new Products(nicosInput.getText().toString());
        dbHandler.addProduct(product);
        printDataBase();
    }

    //delete a product
    public void deleteButtonClicked(View view)
    {
        String inputText = nicosInput.getText().toString();
        dbHandler.deleteProduct(inputText);
        printDataBase();
    }

    public void printDataBase()
    {
        String dbString = dbHandler.databaseToString();
        nicosText.setText(dbString);
        nicosInput.setText("");
    }
}

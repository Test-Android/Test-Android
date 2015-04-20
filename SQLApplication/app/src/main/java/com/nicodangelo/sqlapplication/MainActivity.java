package com.nicodangelo.sqlapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity
{
    EditText input;
    TextView nicosText;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.input);
        nicosText = (TextView) findViewById(R.id.nicosText);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }

    //Add a Item to the database
    public void AddButtonClick(View view)
    {
        Products product = new Products(input.getText().toString());
        dbHandler.addProduct(product);
        printDatabase();
    }

    //Delete items
    public void deleteButtonClick(View view)
    {
        String inputText = input.getText().toString();
        System.out.println("ITEM TO DELETE = " + inputText);
        dbHandler.deleteProduct(inputText);
        printDatabase();
    }

    private void printDatabase()
    {
        nicosText.setText("");
        String dbString = dbHandler.databaseToString();
        nicosText.setText(dbString);
        input.setText("");
    }
}

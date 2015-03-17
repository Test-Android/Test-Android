/*
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Keep this class for a long time it is so useful all you have to do to make a new one like it is possibly change a few variables!!
Created by Jake Cox and free to copy any and all code from this example:)
SQLite is the way of storing data from your app so it can be then reread when the user runs your app again. ANDROID FILE SAVING.
somewhat similar to file read and write in JAVA SWING but a little more complicated.
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */

package com.nicodangelo.sqlitesample;

///////////////////////////////////////////////////////////////////////////////////////////////////
    //imports needed for this to work!
///////////////////////////////////////////////////////////////////////////////////////////////////

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //The class itself and all the instructions and examples to get this going
    //THis class's whole purpose entirely and only purpose is to work directly with the database.
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class MyDBHandler extends SQLiteOpenHelper
{
    //the version that you have, if you edit the data change this.
    private static final int DATABASE_VERSION = 1;
    //make sure you have the db extension it shows android this is a database file.
    private static final String DATABASE_NAME = "products.db";
    //have to name the table.
    public static final String TABLE_PRODUCTS = "products";
    //name the column after what is in it? seems like a good idea...
    public static final String COLUMN_ID = "_id";
    //name this column like the info in it?? yes my good sir.
    public static final String Column_PRODUCTNAME = "productname";

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //the constructor and onCreate() and onUpgrade() methods examples and usage
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////
    //HouseKeeping to pass some of the data on to the superclass "SQLiteOpenHelper".
///////////////////////////////////////////////////////////////////////////////////////////////////
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////
    //when the database is created....
    //when you create this table for the first time what do you do?
    //could possibly be a good idea in here to create the table? maybe, possibly, kinda?
///////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Create a new query... doesn't have to be called that
        //in this String you create the name of the table and inside ( you put all the column data ). inside the " you put all the properties "
        //for that column
        String query = "Create Table" + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + //AUTOINCREMENT makes it so it auto adds one to each item so 0, 1, 2, 3, 4.. get it?
                Column_PRODUCTNAME + " TEXT " + //for this we are just storing text
                ")";
        //this will execute the query
        db.execSQL(query);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////
    //if you ever upgrade the version what do you want to do so it all still works??
    //This is onl;y necessary if you change the code in your onCreate() method!
///////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //first thing to do is delete the current table
        db.execSQL("DROP_TABLE_IF_EXISTS" + TABLE_PRODUCTS);
        //after the current table has been deleted you want to use the new code
        //you changed so call your onCreate() again!!
        onCreate(db);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DataBase editor methods like adding a product or deleting a product
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //add a new row to the Database
    public void addProduct(Products product)
    {
        //makes inserting rows into your table really easy
        ContentValues values = new ContentValues();
        values.put(Column_PRODUCTNAME, product.get_productName());
        //this db is now equal to the database we are going to write to.
        SQLiteDatabase db = getWritableDatabase();
        //this will insert a new row into the table
        db.insert(TABLE_PRODUCTS, null, values);
        //tell java it can have its memory back adn you are done.
        db.close();
    }

    //Delete a product from the database
    public void deleteProduct(String productName)
    {
        SQLiteDatabase db = getWritableDatabase();
        //this one may be confusing but it deletes the product from the table that you choose.
        db.execSQL("DELETE FROM" + TABLE_PRODUCTS + " WHERE " + Column_PRODUCTNAME + " =\"" + productName +  "\";");
        //this might not be good at all but i put it here because it makes sense...
        db.close();
    }

    //Print out the database as a String (not necessary for any of this to work its just for a visualization of what is going on)
    public String databaseToString()
    {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM" + TABLE_PRODUCTS + " WHERE 1";

        //Courser point to a location results
        Cursor c = db.rawQuery(query, null);
        //move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("productname")) != null)
            {
                dbString += c.getString(c.getColumnIndex("productname"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;

    }
}

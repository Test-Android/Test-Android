package com.nicodangelo.sqlapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper
{
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "productDB.db";
        public static final String TABLE_PRODUCTS = "products";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_PRODUCTNAME = "productname";

        //We need to pass database information along to superclass
        public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PRODUCTNAME + " TEXT " +
                    ");";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
            onCreate(db);
        }

        //Add a new row to the database
        public void addProduct(Products product){
            ContentValues values = new ContentValues();
            values.put(COLUMN_PRODUCTNAME, product.get_productname());
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_PRODUCTS, null, values);
            db.close();
        }

        //Delete a product from the database
        public void deleteProduct(String productName)
        {
            SQLiteDatabase db = getWritableDatabase();
            String query = ("DELETE FROM "+ TABLE_PRODUCTS + " WHERE " + " productname='" + productName + "';");
            db.execSQL(query);
            db.close();
          /*  for(int x = 1; x < db.getMaximumSize(); x++)
            {
                String y = Integer.toString(x);
                db.execSQL("SELECT * FROM " + TABLE_PRODUCTS + "WERE _id=" + y);
            }

            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1;";

            //Cursor points to a location in your results
            Cursor c = db.rawQuery(query, null);
            //Move to the first row in your results
            c.moveToFirst();

            //Position after the last row means the end of the results
            while (!c.isAfterLast()) {
                System.out.println("Cursor Name: " + c.getString(c.getColumnIndex("productname")));
                System.out.println("Cursor Pos: " +  c.getPosition());
                //String keyid = c.getString(c.getColumnIndex("KEY_ID"));
                if (c.getString(c.getColumnIndex("productname")).equalsIgnoreCase(productName)) {
                    int tempID = c.getPosition() + 2;
                    db.execSQL("SELECT * FROM " + TABLE_PRODUCTS + "WHERE _id=" + tempID);
                }
                c.moveToNext();
            }
            db.close();

            /*String query = ("DELETE FROM "+ TABLE_PRODUCTS + " WHERE " + TABLE_PRODUCTS + "=\"" + productName + "\";");
            db.execSQL(query);
            System.out.println(query);*/
            System.out.println("this is the product " + COLUMN_PRODUCTNAME + "  " + productName);
        }

        public String databaseToString(){
            String dbString = "";
            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1;";

            //Cursor points to a location in your results
            Cursor c = db.rawQuery(query, null);
            //Move to the first row in your results
            c.moveToFirst();

            //Position after the last row means the end of the results
            while (!c.isAfterLast()) {
                if (c.getString(c.getColumnIndex("productname")) != null) {
                    dbString += c.getString(c.getColumnIndex("productname"));
                    dbString += "\n";
                }
                c.moveToNext();
            }
            db.close();
            return dbString;
        }
    }


/*
Basic SQL NOTES KIND OF GOOD LUCK UNDERSTANDING:)

-- Database-Level
DROP DATABASE databaseName                 -- Delete the database (irrecoverable!)
DROP DATABASE IF EXISTS databaseName       -- Delete if it exists
CREATE DATABASE databaseName               -- Create a new database
CREATE DATABASE IF NOT EXISTS databaseName -- Create only if it does not exists
SHOW DATABASES                             -- Show all the databases in this server
USE databaseName                           -- Set the default (current) database
SELECT DATABASE()                          -- Show the default database
SHOW CREATE DATABASE databaseName          -- Show the CREATE DATABASE statement

-- Table-Level
DROP TABLE [IF EXISTS] tableName, ...
CREATE TABLE [IF NOT EXISTS] tableName (
   columnName columnType columnAttribute, ...
   PRIMARY KEY(columnName),
   FOREIGN KEY (columnNmae) REFERENCES tableName (columnNmae)
)
SHOW TABLES                -- Show all the tables in the default database
DESCRIBE|DESC tableName    -- Describe the details for a table
ALTER TABLE tableName ...  -- Modify a table, e.g., ADD COLUMN and DROP COLUMN
ALTER TABLE tableName ADD columnDefinition
ALTER TABLE tableName DROP columnName
ALTER TABLE tableName ADD FOREIGN KEY (columnNmae) REFERENCES tableName (columnNmae)
ALTER TABLE tableName DROP FOREIGN KEY constraintName
SHOW CREATE TABLE tableName        -- Show the CREATE TABLE statement for this tableName

-- Row-Level
INSERT INTO tableName
   VALUES (column1Value, column2Value,...)               -- Insert on all Columns
INSERT INTO tableName
   VALUES (column1Value, column2Value,...), ...          -- Insert multiple rows
INSERT INTO tableName (column1Name, ..., columnNName)
   VALUES (column1Value, ..., columnNValue)              -- Insert on selected Columns
DELETE FROM tableName WHERE criteria
UPDATE tableName SET columnName = expr, ... WHERE criteria
SELECT * | column1Name AS alias1, ..., columnNName AS aliasN
   FROM tableName
   WHERE criteria
   GROUP BY columnName
   ORDER BY columnName ASC|DESC, ...
   HAVING groupConstraints
   LIMIT count | offset count

-- Others
SHOW WARNINGS;   -- Show the warnings of the previous statement


-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Remove the database "southwind", if it exists.
-- Beware that DROP (and DELETE) actions are irreversible and not recoverable!
mysql> DROP DATABASE IF EXISTS southwind;
Query OK, 1 rows affected (0.31 sec)

-- Create the database "southwind"
mysql> CREATE DATABASE southwind;
Query OK, 1 row affected (0.01 sec)

-- Show all the databases in the server
--   to confirm that "southwind" database has been created.
mysql> SHOW DATABASES;
+--------------------+
| Database           |
+--------------------+
| southwind          |
| ......             |

-- Set "southwind" as the default database so as to reference its table directly.
mysql> USE southwind;
Database changed

-- Show the current (default) database
mysql> SELECT DATABASE();
+------------+
| DATABASE() |
+------------+
| southwind  |
+------------+

-- Show all the tables in the current database.
-- "southwind" has no table (empty set).
mysql> SHOW TABLES;
Empty set (0.00 sec)

-- Create the table "products". Read "explanations" below for the column defintions
mysql> CREATE TABLE IF NOT EXISTS products (
         productID    INT UNSIGNED  NOT NULL AUTO_INCREMENT,
         productCode  CHAR(3)       NOT NULL DEFAULT '',
         name         VARCHAR(30)   NOT NULL DEFAULT '',
         quantity     INT UNSIGNED  NOT NULL DEFAULT 0,
         price        DECIMAL(7,2)  NOT NULL DEFAULT 99999.99,
         PRIMARY KEY  (productID)
       );
Query OK, 0 rows affected (0.08 sec)

-- Show all the tables to confirm that the "products" table has been created
mysql> SHOW TABLES;
+---------------------+
| Tables_in_southwind |
+---------------------+
| products            |
+---------------------+

-- Describe the fields (columns) of the "products" table
mysql> DESCRIBE products;
+-------------+------------------+------+-----+------------+----------------+
| Field       | Type             | Null | Key | Default    | Extra          |
+-------------+------------------+------+-----+------------+----------------+
| productID   | int(10) unsigned | NO   | PRI | NULL       | auto_increment |
| productCode | char(3)          | NO   |     |            |                |
| name        | varchar(30)      | NO   |     |            |                |
| quantity    | int(10) unsigned | NO   |     | 0          |                |
| price       | decimal(7,2)     | NO   |     | 99999.99   |                |
+-------------+------------------+------+-----+------------+----------------+

-- Show the complete CREATE TABLE statement used by MySQL to create this table
mysql> SHOW CREATE TABLE products \G
*************************** 1. row ***************************
       Table: products
Create Table:
CREATE TABLE `products` (
  `productID`    int(10) unsigned  NOT NULL AUTO_INCREMENT,
  `productCode`  char(3)           NOT NULL DEFAULT '',
  `name`         varchar(30)       NOT NULL DEFAULT '',
  `quantity`     int(10) unsigned  NOT NULL DEFAULT '0',
  `price`        decimal(7,2)      NOT NULL DEFAULT '99999.99',
  PRIMARY KEY (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Each record has a unique identifier or primary key. SQL, which stands for Structured Query Language, is used to communicate with a database. Through SQL one can create and delete tables. Here are some commands:

CREATE TABLE - creates a new database table
ALTER TABLE - alters a database table
DROP TABLE - deletes a database table
CREATE INDEX - creates an index (search key)
DROP INDEX - deletes an index
SQL also has syntax to update, insert, and delete records.

SELECT - get data from a database table
UPDATE - change data in a database table
DELETE - remove data from a database table
INSERT INTO - insert new data in a database table
SELECT

The SELECT is used to query the database and retrieve selected data that match the specific criteria that you specify:

SELECT column1 [, column2, ...]
FROM tablename
WHERE condition

The conditional clause can include these operators

= Equal
> Greater than
< Less than
>= Greater than or equal
<= Less than or equal
<> Not equal to
LIKE pattern matching operator
SELECT * FROM tablename

returns all the data from the table.
Use single quotes around text values (most database systems will also accept double quotes). Numerical values should not be enclosed in quotes.

LIKE matches a pattern. The wildcard % is used to denote 0 or more characters.

'A%' : matches all strings that start with A
'%a' : matches all strings that end with a
'%a%' : matches all strings that contain an a
CREATE TABLE

The CREATE TABLE statement is used to create a new table. The format is:

CREATE TABLE tablename
(column1 data type,
column2 data type,
column3 data type);
char(size): Fixed length character string.
varchar(size): Variable-length character string. Max size is specified in parenthesis.
number(size): Number value with a max number of columns specified in parenthesis
date: Date value
number(size,d): A number with a maximum number of digits of "size" and a maximum number of "d" digits to the right of the decimal
INSERT VALUES

Once a table has been created data can be inserted using INSERT INTO command.

INSERT INTO tablename
(col1, ... , coln)
VALUES (val1, ... , valn)
UPDATE

To change the data values in a pre existing table, the UPDATE command can be used.

UPDATE tablename
SET colX = valX [, colY = valY, ...]
WHERE condition
DELETE

The DELETE command can be used to remove a record(s) from a table.

DELETE FROM tablename
WHERE condition

To delete all the records from a table without deleting the table do

DELETE * FROM tablename

DROP

To remove an entire table from the database use the DROP command.

DROP TABLE tablename
ORDER BY

ORDER BY clause can order column name in either ascending (ASC) or descending (DESC) order.

ORDER BY col_name ASC

AND / OR

AND and OR can join two or more conditions in a WHERE clause. AND will return data when all the conditions are true. OR will return data when any one of the conditions is true.

IN

IN operator is used when you know the exact value you want to return for at least one of the columns

SELECT * FROM table_name WHERE col_name IN (val1, val2, ...)

BETWEEN / AND

The BETWEEN ... AND operator selects a range of data between two values. These values can be numbers, text, or dates.

SELECT * FROM table_name WHERE col_name BETWEEN val1 AND val2

JOIN

There are times when we need to collate data from two or more tables. That is called a join. Table in a database are related to each other through their keys. We can associate data in various tables without repeating them. For example we could have a table called Customers which could have information about customers like their name, address, phone numbers. We could have another table called Products that has information regarding the products like part number, product name, manufacturer, number in stock, unit price. A third table called Orders could have information regarding what product was ordered, by whom, the date the order was placed, and quantity. Here are the tables:

ALTER TABLE

With ALTER TABLE you can add or delete columns in an existing table. When you add a column you must specify a data type.
ALTER TABLE table_name
ADD col_name datatype

ALTER TABLE table_name
DROP COLUMN col_name
UNION

The UNION command is used to select data from two tables very similar to the JOIN command. But the UNION command can be used only with columns having the same datatype. With UNION only distinct values are selected, i.e. if there are common data in the two tables only one instance of that data is returned.

SELECT Name FROM Customers_USA
UNION
SELECT Name FROM Customers_Asia

This will select all the customers from USA and Asia but if there is a name that occurs in both the tables it will return only one such name. To get all the names use UNION ALL instead.

SQL Functions

There are several built-in functins in SQL. The basic function types are:

Aggregate Functions: These are functions that operate against a collection of values, but return a single value.
Scalar Functions: These functions operate against a single value, and return a single value.
To use a built-in function the syntax is:

SELECT function (col_name) FROM table_name
GROUP BY

The GROUP BY was added to SQL so that aggregate functions could return a result grouped by column values.

SELECT col_name, function (col_name) FROM table_name GROUP BY col_name
HAVING keyword was introduced because the WHERE keyword could not be used. HAVING states a condition.

SELECT clo_name, function (col_name) FROM table_name
GROUP BY col_name
HAVING function (col_name) condition value
CREATE VIEW

A view is a virtual table that is a result of SQL SELECT statement. A view contains fields from one or more real tables in the database. This virtual table can then be queried as if it were a real table.

CREATE VIEW view_name AS
SELECT col_name(s)
FROM table_name
WHERE condition

A view could be used from inside a query, a stored procedure, or from inside another view. You can add functions and joins to a view and present the data you want to the user.
 */
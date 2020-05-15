package com.example.lab7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "productDB";
    private static final String TABLE_NAME = "Products";
    private static final int DB_VERSION = 1;

    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";
    private static final String KEY_COUNT = "count";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME +" ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, "
                + KEY_PRICE + " INTEGER, "
                + KEY_COUNT + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }

    public static String getTableName() {return TABLE_NAME;}

    public static String getKeyID(){return KEY_ID;}

    public static String getKeyName() {return KEY_NAME;}

    public static String getKeyPrice() {return KEY_PRICE;}

    public static String getKeyCount() {return KEY_COUNT;}
};
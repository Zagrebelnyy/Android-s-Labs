package com.example.lab3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mainDB";
    private static final String TABLE_NAME = "Students";
    private static final int DB_VERSION = 1;
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "FIO";
    private static final String KEY_DATE = "DATE";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +" ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, "
                + KEY_DATE + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getKeyID(){
        return KEY_ID;
    }

    public static String getKeyName() {
        return KEY_NAME;
    }

    public static String getKeyDate() {
        return KEY_DATE;
    }
};

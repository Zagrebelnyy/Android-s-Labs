package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FrontActivity extends AppCompatActivity {
    private TextView nameProduct;
    private TextView priceProduct;
    private TextView countProduct;
    private Button btnBuy;
    private ViewPager2 viewPager2;
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private static ArrayList<Product> products;
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        viewPager2 = findViewById(R.id.view_pager);
        products = new ArrayList<>();
        dbHelper =  new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        String columns[] = {DBHelper.getKeyID(), DBHelper.getKeyName(),DBHelper.getKeyPrice(),DBHelper.getKeyCount()};
        Cursor cursor = database.query(DBHelper.getTableName(), columns, null,
                null, null, null, null);
        int nameColumnIndex = cursor.getColumnIndex(DBHelper.getKeyName());
        int priceColumnIndex = cursor.getColumnIndex(DBHelper.getKeyPrice());
        int countColumnIndex = cursor.getColumnIndex(DBHelper.getKeyCount());
        String currentName;
        int currentPrice;
        int currentCount;
        while (cursor.moveToNext()) {
            currentName = cursor.getString(nameColumnIndex);
            currentPrice = cursor.getInt(priceColumnIndex);
            currentCount = cursor.getInt(countColumnIndex);
            Product product = new Product(currentName, currentPrice, currentCount);
            products.add(product);
        }
        if(products.size()!=0) {
            DataAdapter dataAdapter = new DataAdapter(this, products);
            viewPager2.setAdapter(dataAdapter);
        }
        else{
            Toast.makeText(this,"Товаров нет",Toast.LENGTH_SHORT).show();
        }
    }



    public void buyTheProduct(View view){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //MyAsyncTask myAsyncTask = new MyAsyncTask();
                //myAsyncTask.execute();
            }},SPLASH_DISPLAY_LENGTH);
    }


}

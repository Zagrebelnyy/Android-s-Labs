package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

public class BackActivity extends AppCompatActivity {
    private EditText nameInput;
    private EditText priceInput;
    private EditText countInput;
    private Button btnAddNewProduct;
    private static DBHelper dbHelper;
    private static SQLiteDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        btnAddNewProduct = findViewById(R.id.btnAdd);
        nameInput = findViewById(R.id.nameInput);
        priceInput = findViewById(R.id.priceInput);
        countInput = findViewById(R.id.countInput);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
    }

    public void addNewProduct(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.getKeyName(), nameInput.getText().toString());
        contentValues.put(DBHelper.getKeyPrice(), priceInput.getText().toString());
        contentValues.put(DBHelper.getKeyCount(), countInput.getText().toString());
        database.insert(DBHelper.getTableName(), null, contentValues);
        setNullInput();
    }

    private void setNullInput() {
        nameInput.setText("");
        priceInput.setText("");
        countInput.setText("");
    }

}

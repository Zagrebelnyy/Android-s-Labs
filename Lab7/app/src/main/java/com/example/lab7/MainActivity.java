package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    private Button btnFront;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFront = findViewById(R.id.btnFront);
        btnBack = findViewById(R.id.btnBack);
    }

    public void goToActFront(View view){
        Intent intent = new Intent(this, FrontActivity.class);
        startActivity(intent);
    }

    public void goToActBack(View view){
        Intent intent = new Intent(this, BackActivity.class);
        startActivity(intent);
    }
}

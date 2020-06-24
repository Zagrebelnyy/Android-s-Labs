package com.example.testmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button buttonFrom;
    private EditText editTextFrom;
    private Button buttonTo;
    private EditText editTextTo;
    private double departureLongitude;
    private double departureLatitude;
    private double arrivalLongitude;
    private double arrivalLatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonFrom = findViewById(R.id.btnSearchFrom);
        editTextFrom = findViewById(R.id.searchInputFrom);
        buttonTo = findViewById(R.id.btnSearchTo);
        editTextTo = findViewById(R.id.searchInputTo);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");

        tabSpec.setContent(R.id.linearLayout1);
        tabSpec.setIndicator("Откуда");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.linearLayout2);
        tabSpec.setIndicator("Куда");
        tabHost.addTab(tabSpec);
        tabHost.setCurrentTab(0);


    }


    public void searchAdrFrom(View view) {
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        Toast.makeText(getApplicationContext(), "Клавиша нажата", Toast.LENGTH_SHORT).show();
        try {
            List<Address> addresses = geocoder.getFromLocationName(editTextFrom.getText().toString(), 1);
            if (addresses.size() > 0) {
                Address fetchedAddress = addresses.get(0);
                departureLongitude = fetchedAddress.getLongitude();
                departureLatitude = fetchedAddress.getLatitude();
                Toast.makeText(getApplicationContext(),  departureLongitude + "," + departureLatitude, Toast.LENGTH_SHORT).show();
            } else {
                editTextTo.setText("Невозможно определить координаты");
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
        }
    }

    public void searchAdrTo(View view) {
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        try {
            List<Address> addresses = geocoder.getFromLocationName(editTextTo.getText().toString(), 1);
            if (addresses.size() > 0) {
                Address fetchedAddress = addresses.get(0);
                arrivalLongitude = fetchedAddress.getLongitude();
                arrivalLatitude = fetchedAddress.getLatitude();
                Toast.makeText(getApplicationContext(),  arrivalLongitude + "," + arrivalLatitude , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("DepartureLongitude", departureLongitude);
                intent.putExtra("DepartureLatitude", departureLatitude);
                intent.putExtra("ArrivalLatitude", arrivalLatitude);
                intent.putExtra("ArrivalLongitude", arrivalLongitude);
                startActivity(intent);
            } else {
                editTextTo.setText("Невозможно определить координаты");
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
        }
    }
}

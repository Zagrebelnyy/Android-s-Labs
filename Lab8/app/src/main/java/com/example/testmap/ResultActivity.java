package com.example.testmap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;

public class ResultActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    String url;
    private EditText textView;
    private double departureLongitude;
    private double departureLatitude;
    private double arrivalLongitude;
    private double arrivalLatitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle arguments = getIntent().getExtras();
        departureLatitude = arguments.getDouble("DepartureLatitude");
        departureLongitude = arguments.getDouble("DepartureLongitude");
        arrivalLatitude = arguments.getDouble("ArrivalLatitude");
        arrivalLongitude = arguments.getDouble("ArrivalLongitude");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        url = "https://maps.googleapis.com/maps/api/directions/json?" +
                "origin=" + departureLatitude + ",%" +  departureLongitude + "&" +
                "destination=" + arrivalLatitude + ",%" +  arrivalLongitude + "&" +
                "&key=";
        JSONDownload jsonDownload = new JSONDownload();
        jsonDownload.execute(url);

    }
    public class JSONDownload extends AsyncTask<String, Void, Void> {
        private String jsonString = "";

        @Override
        protected Void doInBackground(String... urls) {
            try {
                textView = findViewById(R.id.test);
                String pageURL = urls[0];
                Document doc = Jsoup.connect(pageURL)
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")//Идентификатор того кто запрашивает(Можно оставить как есть)
                        .header("Accept", "application/json")//Для парсинга JSON
                        .referrer("http://www.google.com")//Источник запроса(Можно оставить как есть)
                        .get();
                jsonString = doc.body().text();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            PolylineOptions line = new PolylineOptions();
            mMap = googleMap;
            LatLng moscow = new LatLng(55.754724, 37.621380);
            line.add(new LatLng(departureLatitude, departureLongitude));
            line.add(new LatLng(arrivalLatitude, arrivalLongitude));
            mMap.addMarker(new MarkerOptions().position(moscow).title("Moscow"));
            googleMap.addPolyline(line);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(moscow));
        }
    }

}

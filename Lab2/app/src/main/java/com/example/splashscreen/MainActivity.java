package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.AsyncTask;
import android.os.Bundle;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static List<ListItem> listItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        JSONDownload jsonDownload = new JSONDownload();
        jsonDownload.execute("https://raw.githubusercontent.com/wesleywerner/" +
                "ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/" +
                "src/data/techs.ruleset.json");
    }

    public static List<ListItem> getList(){
        return listItems;
    }



    public class JSONDownload extends AsyncTask<String, Void, Void> {
        private String jsonString = "";
        private String imageURL =
                "https://raw.githubusercontent.com/wesleywerner" +
                        "/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/";

        @Override
        protected Void doInBackground(String... urls) {
            try {
                String pageURL = urls[0];
                Document doc = Jsoup.connect(pageURL)
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")//Идентификатор того кто запрашивает(Можно оставить как есть)
                        .header("Accept", "application/json")//Для парсинга JSON
                        .referrer("http://www.google.com")//Источник запроса(Можно оставить как есть)
                        .get();
                jsonString = doc.body().text();
                try {
                    Object obj = new JSONParser().parse(jsonString);
                    JSONArray jo = (JSONArray) obj;
                    for (int i = 1; i < jo.size(); i++) {
                        String graphic = "";
                        String name = "";
                        String helpText = "";
                        JSONObject jsonObject = (JSONObject) jo.get(i);
                        if (jsonObject.get("graphic") != null) {
                            graphic += jsonObject.get("graphic").toString();
                            graphic = imageURL + graphic;
                        }
                        if (jsonObject.get("name") != null) {
                            name += jsonObject.get("name").toString();
                        }
                        if (jsonObject.get("helptext") != null) {
                            helpText += jsonObject.get("helptext").toString();
                            listItems.add(new ListItem(name, graphic, helpText));
                        }
                        if(jsonObject.get("helptext") == null){
                            listItems.add(new ListItem(name, graphic));
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment(MainActivity.this, getList());
            fragmentTransaction.add(R.id.recycler_view_fragment, recyclerViewFragment);
            fragmentTransaction.commit();
        }
    }
}

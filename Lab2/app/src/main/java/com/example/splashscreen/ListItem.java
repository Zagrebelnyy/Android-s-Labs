package com.example.splashscreen;

import android.graphics.Bitmap;
import android.icu.text.StringSearch;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ListItem {
    private String name;
    private String image;
    private String helptext;

    public ListItem(String name, String image) {
        this.name = name;
        this.image = image;
        this.helptext = "";
    }

    public ListItem(String name, String image, String helptext) {
        this.name = name;
        this.image = image;
        this.helptext = helptext;
    }

    public String getImage() {
        return this.image;
    }

    public String getName() {
        return this.name;
    }

    public String getHelptext(){
        return this.helptext;
    }

}

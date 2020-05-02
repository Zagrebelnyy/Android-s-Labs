package com.example.lab4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WidgetConfig extends Activity {
    private EditText editText;
    private AppWidgetManager awManager;
    private Context thisContext;
    private int awID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_widget_config);
        editText = (EditText) findViewById(R.id.configWidgetText);
        Intent intent = getIntent();
        Bundle bundleExtras = intent.getExtras();
        if (bundleExtras != null) {
            awID = bundleExtras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        } else
            finish();
        thisContext = this.getApplicationContext();
        awManager = AppWidgetManager.getInstance(thisContext);
        final RemoteViews awRV = new RemoteViews(thisContext.getPackageName(), R.layout.app_widget);
        findViewById(R.id.configWidgetBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String end = editText.getText().toString();
                AppWidget.day = end;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                try {
                    Date nextDay = simpleDateFormat.parse(end);
                    Integer day = (int) ((nextDay.getTime() - (new Date()).getTime()) / (24 * 60 * 60 * 1000));
                    awRV.setTextViewText(R.id.appwidget_text, day.toString());
                    Intent intentBtnPwr = new Intent(thisContext, WidgetConfig.class);
                    intentBtnPwr.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, awID);
                    PendingIntent pi = PendingIntent.getActivity(thisContext, awID, intentBtnPwr, PendingIntent.FLAG_UPDATE_CURRENT);
                    awRV.setOnClickPendingIntent(R.id.btn, pi);
                    awManager.updateAppWidget(awID, awRV);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, awID);
                    setResult(RESULT_OK, resultIntent);
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

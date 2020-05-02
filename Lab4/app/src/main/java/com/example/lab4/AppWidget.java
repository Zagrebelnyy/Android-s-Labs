package com.example.lab4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppWidget extends AppWidgetProvider {
    static String day = null;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId : appWidgetIds) {
            RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.app_widget);
            if(day != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                try {
                    Date nextDay = simpleDateFormat.parse(day);
                    Integer dayCount = (int) ((nextDay.getTime() - (new Date()).getTime()) / (24 * 60 * 60 * 1000));
                    if(dayCount == 0 && new Date().getHours() == 9){
                        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                        NotificationCompat.Builder builder =
                                new NotificationCompat.Builder(context, "CHANNEL_ID")
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setContentTitle("Напоминание")
                                        .setContentText("Дань настастал")
                                        .setPriority(NotificationCompat.PRIORITY_HIGH);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel notificationChannel = new NotificationChannel("CHANNEL_ID", "CHANNEL_ID", NotificationManager.IMPORTANCE_DEFAULT);
                            notificationManager.createNotificationChannel(notificationChannel);
                            notificationManager.notify(1, builder.build());
                        }
                    }
                    view.setTextViewText(R.id.appwidget_text, dayCount.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                view.setTextViewText(R.id.appwidget_text, "0");
            }
            appWidgetManager.updateAppWidget(appWidgetId, view);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        AppWidgetManager awm = AppWidgetManager.getInstance(context);
        ComponentName compName = new ComponentName(context, AppWidget.class);
        int[] widgetIds = awm.getAppWidgetIds(compName);
        for (int widgetId : widgetIds) {
            Intent intentBtnPwr = new Intent(context, WidgetConfig.class);
            intentBtnPwr.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
            PendingIntent pi = PendingIntent.getActivity(context, widgetId, intentBtnPwr, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.btn, pi);
            awm.updateAppWidget(widgetId, remoteViews);
        }
    }
}


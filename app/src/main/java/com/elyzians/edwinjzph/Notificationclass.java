package com.elyzians.edwinjzph;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notificationclass extends Application {
    public static final String NOTIFICATION_ID = "notification_id";

    public void onCreate() {
        super.onCreate();
        createnotification();
    }

    private void createnotification() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_ID, "ddemo_noyification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Demo_description");
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }
}

package com.elyzians.edwinjzph;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class fcmmessaing extends FirebaseMessagingService {
    DatabaseReference databaseReference;
    NotificationManagerCompat managerCompat;

    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            this.managerCompat = NotificationManagerCompat.from(this);
            shownoutification(title, body);
        }
    }

    private void shownoutification(String str, String str2) {
        this.managerCompat.notify((int) System.currentTimeMillis(), new NotificationCompat.Builder(this, Notificationclass.NOTIFICATION_ID).setContentTitle(str).setContentText(str2).setPriority(1).setCategory(NotificationCompat.CATEGORY_MESSAGE).setSmallIcon(R.drawable.ic_account).build());
    }

    public  void  onNewToken(String str) {
        Log.d("NEW_TOKEN", str);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            sendRegistrationToServer(str);
        }
        super.onNewToken(str);
    }

    public void sendRegistrationToServer(String str) {
        Log.v("FirebaseService", "Token " + str);
        DatabaseReference child = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("token");
        this.databaseReference = child;
        child.setValue(new token(str));
    }
}

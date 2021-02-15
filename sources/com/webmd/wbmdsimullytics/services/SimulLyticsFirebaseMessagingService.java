package com.webmd.wbmdsimullytics.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.appboy.Appboy;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.webmd.wbmdsimullytics.R;
import com.webmd.wbmdsimullytics.constants.NotificationConstants;

public class SimulLyticsFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = SimulLyticsFirebaseMessagingService.class.getSimpleName();

    public void onNewToken(String str) {
        super.onNewToken(str);
        Log.d(TAG, String.format("Refreshed Token: %s", new Object[]{str}));
        Appboy.getInstance(this).registerAppboyPushMessages(str);
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "Firebase message received");
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            String str = null;
            if (remoteMessage.getData() != null) {
                str = remoteMessage.getData().get(NotificationConstants.PUSH_DEEP_LINK_QUERY_STRING_KEY);
            }
            sendNotification(title, body, str);
        }
    }

    private void sendNotification(String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.setClassName(this, getResources().getString(R.string.push_handler_activity_name));
        intent.putExtra(NotificationConstants.PUSH_DEEP_LINK_KEY, str3);
        intent.addFlags(67108864);
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder((Context) this, "fcmid").setSmallIcon(R.drawable.push_small_icon).setColor(getResources().getColor(R.color.notification_color)).setContentTitle(str).setContentText(str2).setAutoCancel(true).setSound(RingtoneManager.getDefaultUri(2)).setContentIntent(PendingIntent.getActivity(this, 0, intent, 1073741824));
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel("fcmid", "Channel human readable title", 3));
        }
        notificationManager.notify(0, contentIntent.build());
    }
}

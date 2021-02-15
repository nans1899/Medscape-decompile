package com.appboy;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.core.app.NotificationManagerCompat;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.push.AppboyNotificationActionUtils;
import com.appboy.push.AppboyNotificationUtils;
import com.appboy.support.AppboyLogger;

public final class AppboyFcmReceiver extends BroadcastReceiver {
    private static final String FCM_DELETED_MESSAGES_KEY = "deleted_messages";
    private static final String FCM_MESSAGE_TYPE_KEY = "message_type";
    private static final String FCM_NUMBER_OF_MESSAGES_DELETED_KEY = "total_deleted";
    private static final String FCM_RECEIVE_INTENT_ACTION = "com.google.android.c2dm.intent.RECEIVE";
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(AppboyFcmReceiver.class);

    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        AppboyLogger.i(str, "Received broadcast message. Message: " + intent.toString());
        String action = intent.getAction();
        if (FCM_RECEIVE_INTENT_ACTION.equals(action)) {
            handleAppboyFcmReceiveIntent(context, intent);
        } else if (Constants.APPBOY_CANCEL_NOTIFICATION_ACTION.equals(action)) {
            AppboyNotificationUtils.handleCancelNotificationAction(context, intent);
        } else if (Constants.APPBOY_ACTION_CLICKED_ACTION.equals(action)) {
            AppboyNotificationActionUtils.handleNotificationActionClicked(context, intent);
        } else if (Constants.APPBOY_STORY_TRAVERSE_CLICKED_ACTION.equals(action)) {
            handleAppboyFcmReceiveIntent(context, intent);
        } else if (Constants.APPBOY_STORY_CLICKED_ACTION.equals(action)) {
            AppboyNotificationUtils.handlePushStoryPageClicked(context, intent);
        } else if (Constants.APPBOY_PUSH_CLICKED_ACTION.equals(action)) {
            AppboyNotificationUtils.handleNotificationOpened(context, intent);
        } else if (Constants.APPBOY_PUSH_DELETED_ACTION.equals(action)) {
            AppboyNotificationUtils.handleNotificationDeleted(context, intent);
        } else {
            AppboyLogger.w(TAG, "The FCM receiver received a message not sent from Appboy. Ignoring the message.");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean handleAppboyFcmMessage(Context context, Intent intent) {
        NotificationManagerCompat from = NotificationManagerCompat.from(context);
        if ("deleted_messages".equals(intent.getStringExtra("message_type"))) {
            int intExtra = intent.getIntExtra(FCM_NUMBER_OF_MESSAGES_DELETED_KEY, -1);
            if (intExtra == -1) {
                String str = TAG;
                AppboyLogger.e(str, "Unable to parse FCM message. Intent: " + intent.toString());
            } else {
                String str2 = TAG;
                AppboyLogger.i(str2, "FCM deleted " + intExtra + " messages. Fetch them from Appboy.");
            }
            return false;
        }
        Bundle extras = intent.getExtras();
        String str3 = TAG;
        AppboyLogger.i(str3, "Push message payload received: " + extras);
        Bundle appboyExtrasWithoutPreprocessing = AppboyNotificationUtils.getAppboyExtrasWithoutPreprocessing(extras);
        extras.putBundle(Constants.APPBOY_PUSH_EXTRAS_KEY, appboyExtrasWithoutPreprocessing);
        if (!extras.containsKey(Constants.APPBOY_PUSH_RECEIVED_TIMESTAMP_MILLIS)) {
            extras.putLong(Constants.APPBOY_PUSH_RECEIVED_TIMESTAMP_MILLIS, System.currentTimeMillis());
        }
        AppboyNotificationUtils.handleContentCardsSerializedCardIfPresent(context, extras);
        if (AppboyNotificationUtils.isNotificationMessage(intent)) {
            AppboyLogger.d(TAG, "Received notification push");
            int notificationId = AppboyNotificationUtils.getNotificationId(extras);
            extras.putInt(Constants.APPBOY_PUSH_NOTIFICATION_ID, notificationId);
            AppboyConfigurationProvider appboyConfigurationProvider = new AppboyConfigurationProvider(context);
            IAppboyNotificationFactory activeNotificationFactory = AppboyNotificationUtils.getActiveNotificationFactory();
            if (!extras.containsKey(Constants.APPBOY_PUSH_STORY_KEY)) {
                AppboyNotificationUtils.logPushDeliveryEvent(context, extras);
            } else if (!extras.containsKey(Constants.APPBOY_PUSH_STORY_IS_NEWLY_RECEIVED)) {
                AppboyLogger.d(TAG, "Received the initial push story notification.");
                extras.putBoolean(Constants.APPBOY_PUSH_STORY_IS_NEWLY_RECEIVED, true);
                AppboyNotificationUtils.logPushDeliveryEvent(context, extras);
            }
            Notification createNotification = activeNotificationFactory.createNotification(appboyConfigurationProvider, context, extras, appboyExtrasWithoutPreprocessing);
            if (createNotification == null) {
                AppboyLogger.d(TAG, "Notification created by notification factory was null. Not displaying notification.");
                return false;
            }
            from.notify(Constants.APPBOY_PUSH_NOTIFICATION_TAG, notificationId, createNotification);
            AppboyNotificationUtils.sendPushMessageReceivedBroadcast(context, extras);
            AppboyNotificationUtils.wakeScreenIfHasPermission(context, extras);
            if (extras != null && extras.containsKey(Constants.APPBOY_PUSH_NOTIFICATION_DURATION_KEY)) {
                AppboyNotificationUtils.setNotificationDurationAlarm(context, getClass(), notificationId, Integer.parseInt(extras.getString(Constants.APPBOY_PUSH_NOTIFICATION_DURATION_KEY)));
            }
            return true;
        }
        AppboyLogger.d(TAG, "Received data push");
        AppboyNotificationUtils.logPushDeliveryEvent(context, extras);
        AppboyNotificationUtils.sendPushMessageReceivedBroadcast(context, extras);
        AppboyNotificationUtils.requestGeofenceRefreshIfAppropriate(context, extras);
        return false;
    }

    public class HandleAppboyFcmMessageTask extends AsyncTask<Void, Void, Void> {
        private final Context mContext;
        private final Intent mIntent;

        public HandleAppboyFcmMessageTask(Context context, Intent intent) {
            this.mContext = context;
            this.mIntent = intent;
            execute(new Void[0]);
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            try {
                AppboyFcmReceiver.this.handleAppboyFcmMessage(this.mContext, this.mIntent);
                return null;
            } catch (Exception e) {
                AppboyLogger.e(AppboyFcmReceiver.TAG, "Failed to create and display notification.", e);
                return null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void handleAppboyFcmReceiveIntent(Context context, Intent intent) {
        if (AppboyNotificationUtils.isAppboyPushMessage(intent)) {
            new HandleAppboyFcmMessageTask(context, intent);
        }
    }
}

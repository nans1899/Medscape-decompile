package com.appboy;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.push.AppboyNotificationActionUtils;
import com.appboy.push.AppboyNotificationUtils;
import com.appboy.support.AppboyLogger;

public final class AppboyAdmReceiver extends BroadcastReceiver {
    private static final String ADM_DELETED_MESSAGES_KEY = "deleted_messages";
    private static final String ADM_ERROR_KEY = "error";
    private static final String ADM_MESSAGE_TYPE_KEY = "message_type";
    private static final String ADM_NUMBER_OF_MESSAGES_DELETED_KEY = "total_deleted";
    private static final String ADM_RECEIVE_INTENT_ACTION = "com.amazon.device.messaging.intent.RECEIVE";
    private static final String ADM_REGISTRATION_ID_KEY = "registration_id";
    private static final String ADM_REGISTRATION_INTENT_ACTION = "com.amazon.device.messaging.intent.REGISTRATION";
    private static final String ADM_UNREGISTERED_KEY = "unregistered";
    public static final String CAMPAIGN_ID_KEY = "cid";
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(AppboyAdmReceiver.class);

    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        AppboyLogger.i(str, "Received broadcast message. Message: " + intent.toString());
        String action = intent.getAction();
        if (ADM_REGISTRATION_INTENT_ACTION.equals(action)) {
            handleRegistrationEventIfEnabled(new AppboyConfigurationProvider(context), context, intent);
        } else if (ADM_RECEIVE_INTENT_ACTION.equals(action)) {
            handleAppboyAdmReceiveIntent(context, intent);
        } else if (Constants.APPBOY_CANCEL_NOTIFICATION_ACTION.equals(action)) {
            AppboyNotificationUtils.handleCancelNotificationAction(context, intent);
        } else if (Constants.APPBOY_ACTION_CLICKED_ACTION.equals(action)) {
            AppboyNotificationActionUtils.handleNotificationActionClicked(context, intent);
        } else if (Constants.APPBOY_PUSH_CLICKED_ACTION.equals(action)) {
            AppboyNotificationUtils.handleNotificationOpened(context, intent);
        } else if (Constants.APPBOY_PUSH_DELETED_ACTION.equals(action)) {
            AppboyNotificationUtils.handleNotificationDeleted(context, intent);
        } else {
            AppboyLogger.w(TAG, "The ADM receiver received a message not sent from Appboy. Ignoring the message.");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean handleRegistrationIntent(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("error");
        String stringExtra2 = intent.getStringExtra(ADM_REGISTRATION_ID_KEY);
        String stringExtra3 = intent.getStringExtra(ADM_UNREGISTERED_KEY);
        if (stringExtra != null) {
            String str = TAG;
            AppboyLogger.e(str, "Error during ADM registration: " + stringExtra);
            return true;
        } else if (stringExtra2 != null) {
            String str2 = TAG;
            AppboyLogger.i(str2, "Registering for ADM messages with registrationId: " + stringExtra2);
            Appboy.getInstance(context).registerAppboyPushMessages(stringExtra2);
            return true;
        } else if (stringExtra3 != null) {
            String str3 = TAG;
            AppboyLogger.w(str3, "The device was un-registered from ADM: " + stringExtra3);
            return true;
        } else {
            AppboyLogger.w(TAG, "The ADM registration intent is missing error information, registration id, and unregistration confirmation. Ignoring.");
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean handleAppboyAdmMessage(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if ("deleted_messages".equals(intent.getStringExtra("message_type"))) {
            int intExtra = intent.getIntExtra(ADM_NUMBER_OF_MESSAGES_DELETED_KEY, -1);
            if (intExtra == -1) {
                String str = TAG;
                AppboyLogger.e(str, "Unable to parse ADM message. Intent: " + intent.toString());
            } else {
                String str2 = TAG;
                AppboyLogger.i(str2, "ADM deleted " + intExtra + " messages. Fetch them from Appboy.");
            }
            return false;
        }
        Bundle extras = intent.getExtras();
        String str3 = TAG;
        AppboyLogger.d(str3, "Push message payload received: " + extras);
        if (!extras.containsKey(Constants.APPBOY_PUSH_RECEIVED_TIMESTAMP_MILLIS)) {
            extras.putLong(Constants.APPBOY_PUSH_RECEIVED_TIMESTAMP_MILLIS, System.currentTimeMillis());
        }
        AppboyNotificationUtils.logPushDeliveryEvent(context, extras);
        Bundle appboyExtrasWithoutPreprocessing = AppboyNotificationUtils.getAppboyExtrasWithoutPreprocessing(extras);
        extras.putBundle(Constants.APPBOY_PUSH_EXTRAS_KEY, appboyExtrasWithoutPreprocessing);
        if (AppboyNotificationUtils.isNotificationMessage(intent)) {
            int notificationId = AppboyNotificationUtils.getNotificationId(extras);
            extras.putInt(Constants.APPBOY_PUSH_NOTIFICATION_ID, notificationId);
            Notification createNotification = AppboyNotificationUtils.getActiveNotificationFactory().createNotification(new AppboyConfigurationProvider(context), context, extras, appboyExtrasWithoutPreprocessing);
            if (createNotification == null) {
                AppboyLogger.d(TAG, "Notification created by notification factory was null. Not displaying notification.");
                return false;
            }
            notificationManager.notify(Constants.APPBOY_PUSH_NOTIFICATION_TAG, notificationId, createNotification);
            AppboyNotificationUtils.sendPushMessageReceivedBroadcast(context, extras);
            AppboyNotificationUtils.wakeScreenIfHasPermission(context, extras);
            if (!extras.containsKey(Constants.APPBOY_PUSH_NOTIFICATION_DURATION_KEY)) {
                return true;
            }
            AppboyNotificationUtils.setNotificationDurationAlarm(context, getClass(), notificationId, Integer.parseInt(extras.getString(Constants.APPBOY_PUSH_NOTIFICATION_DURATION_KEY)));
            return true;
        }
        AppboyNotificationUtils.sendPushMessageReceivedBroadcast(context, extras);
        AppboyNotificationUtils.requestGeofenceRefreshIfAppropriate(context, extras);
        return false;
    }

    public class HandleAppboyAdmMessageTask extends AsyncTask<Void, Void, Void> {
        private final Context mContext;
        private final Intent mIntent;

        public HandleAppboyAdmMessageTask(Context context, Intent intent) {
            this.mContext = context;
            this.mIntent = intent;
            execute(new Void[0]);
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            try {
                AppboyAdmReceiver.this.handleAppboyAdmMessage(this.mContext, this.mIntent);
                return null;
            } catch (Exception e) {
                AppboyLogger.e(AppboyAdmReceiver.TAG, "Failed to create and display notification.", e);
                return null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void handleAppboyAdmReceiveIntent(Context context, Intent intent) {
        if (AppboyNotificationUtils.isAppboyPushMessage(intent)) {
            new HandleAppboyAdmMessageTask(context, intent);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean handleRegistrationEventIfEnabled(AppboyConfigurationProvider appboyConfigurationProvider, Context context, Intent intent) {
        String str = TAG;
        AppboyLogger.i(str, "Received ADM registration. Message: " + intent.toString());
        if (appboyConfigurationProvider.isAdmMessagingRegistrationEnabled()) {
            AppboyLogger.d(TAG, "ADM enabled in appboy.xml. Continuing to process ADM registration intent.");
            handleRegistrationIntent(context, intent);
            return true;
        }
        AppboyLogger.w(TAG, "ADM not enabled in appboy.xml. Ignoring ADM registration intent. Note: you must set com_appboy_push_adm_messaging_registration_enabled to true in your appboy.xml to enable ADM.");
        return false;
    }
}

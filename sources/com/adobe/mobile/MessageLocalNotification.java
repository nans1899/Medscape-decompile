package com.adobe.mobile;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.adobe.mobile.StaticMethods;
import java.security.SecureRandom;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

final class MessageLocalNotification extends Message {
    private static final String JSON_CONFIG_CONTENT = "content";
    private static final String JSON_CONFIG_WAIT = "wait";
    protected String content;
    protected String deeplink;
    protected Integer localNotificationDelay;
    protected String userInfo;

    MessageLocalNotification() {
    }

    /* access modifiers changed from: protected */
    public boolean initWithPayloadObject(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0 || !super.initWithPayloadObject(jSONObject)) {
            return false;
        }
        StaticMethods.logDebugFormat("Messages -  Building Local Notification message.", new Object[0]);
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("payload");
            if (jSONObject2.length() <= 0) {
                StaticMethods.logDebugFormat("Messages - Unable to create local notification message \"%s\", payload is empty", this.messageId);
                return false;
            }
            try {
                String string = jSONObject2.getString("content");
                this.content = string;
                if (string.length() <= 0) {
                    StaticMethods.logDebugFormat("Messages - Unable to create local notification message \"%s\", content is empty", this.messageId);
                    return false;
                }
                try {
                    this.localNotificationDelay = Integer.valueOf(jSONObject2.getInt(JSON_CONFIG_WAIT));
                    try {
                        this.deeplink = jSONObject2.getString(Config.ADB_MESSAGE_DEEPLINK_KEY);
                    } catch (JSONException unused) {
                        StaticMethods.logDebugFormat("Messages - Tried to read deeplink for local notification message but found none.  This is not a required field", new Object[0]);
                    }
                    try {
                        this.userInfo = jSONObject2.getJSONObject("userData").toString();
                    } catch (JSONException unused2) {
                        StaticMethods.logDebugFormat("Messages - Tried to read userData for local notification message but found none.  This is not a required field", new Object[0]);
                    } catch (NullPointerException unused3) {
                        StaticMethods.logDebugFormat("Messages - Tried to read userData for local notification message but found none.  This is not a required field", new Object[0]);
                    }
                    StaticMethods.logDebugFormat("Message created with: \n MessageID: \"%s\" \n Content: \"%s\" \n Delay: \"%d\" \n Deeplink: \"%s\" \n User Data: \"%s\"", this.messageId, this.content, this.localNotificationDelay, this.deeplink, this.userInfo);
                    return true;
                } catch (JSONException unused4) {
                    StaticMethods.logDebugFormat("Messages - Unable to create local notification message \"%s\", localNotificationDelay is required", this.messageId);
                    return false;
                }
            } catch (JSONException unused5) {
                StaticMethods.logDebugFormat("Messages - Unable to create local notification message \"%s\", content is required", this.messageId);
                return false;
            }
        } catch (JSONException unused6) {
            StaticMethods.logDebugFormat("Messages - Unable to create local notification message \"%s\", payload is required", this.messageId);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void show() {
        super.show();
        messageTriggered();
        try {
            Activity currentActivity = StaticMethods.getCurrentActivity();
            int nextInt = new SecureRandom().nextInt();
            Calendar instance = Calendar.getInstance();
            instance.add(13, this.localNotificationDelay.intValue());
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClass(currentActivity, MessageNotificationHandler.class);
            intent.putExtra("adbMessageCode", Messages.MESSAGE_LOCAL_IDENTIFIER);
            intent.putExtra("adb_m_l_id", this.messageId);
            intent.putExtra("requestCode", nextInt);
            intent.putExtra("userData", this.userInfo);
            intent.putExtra(Config.ADB_MESSAGE_DEEPLINK_KEY, this.deeplink);
            intent.putExtra("alarm_message", this.content);
            try {
                ((AlarmManager) StaticMethods.getSharedContext().getSystemService(NotificationCompat.CATEGORY_ALARM)).set(0, instance.getTimeInMillis(), PendingIntent.getBroadcast(StaticMethods.getSharedContext(), nextInt, intent, 134217728));
            } catch (StaticMethods.NullContextException e) {
                StaticMethods.logErrorFormat("Messaging - Error scheduling local notification (%s)", e.getMessage());
            }
        } catch (StaticMethods.NullActivityException e2) {
            StaticMethods.logErrorFormat(e2.getMessage(), new Object[0]);
        }
    }
}

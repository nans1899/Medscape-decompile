package com.appboy.push;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.appboy.support.IntentUtils;
import com.appboy.support.StringUtils;

public class AppboyNotificationActionUtils {
    private static final String TAG = AppboyLogger.getAppboyLogTag(AppboyNotificationActionUtils.class);

    public static void addNotificationActions(Context context, NotificationCompat.Builder builder, Bundle bundle) {
        if (bundle == null) {
            try {
                AppboyLogger.w(TAG, "Notification extras were null. Doing nothing.");
            } catch (Exception e) {
                AppboyLogger.e(TAG, "Caught exception while adding notification action buttons.", e);
            }
        } else {
            for (int i = 0; !StringUtils.isNullOrBlank(getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_ACTION_TYPE_KEY_TEMPLATE)); i++) {
                addNotificationAction(context, builder, bundle, i);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0093 A[Catch:{ Exception -> 0x0097 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void handleNotificationActionClicked(android.content.Context r6, android.content.Intent r7) {
        /*
            java.lang.String r0 = "appboy_action_use_webview"
            java.lang.String r1 = "appboy_action_uri"
            java.lang.String r2 = "ab_uri"
            java.lang.String r3 = "appboy_action_type"
            java.lang.String r3 = r7.getStringExtra(r3)     // Catch:{ Exception -> 0x0097 }
            boolean r4 = com.appboy.support.StringUtils.isNullOrBlank(r3)     // Catch:{ Exception -> 0x0097 }
            if (r4 == 0) goto L_0x001a
            java.lang.String r6 = TAG     // Catch:{ Exception -> 0x0097 }
            java.lang.String r7 = "Notification action button type was blank or null. Doing nothing."
            com.appboy.support.AppboyLogger.w((java.lang.String) r6, (java.lang.String) r7)     // Catch:{ Exception -> 0x0097 }
            return
        L_0x001a:
            java.lang.String r4 = "nid"
            r5 = -1
            int r4 = r7.getIntExtra(r4, r5)     // Catch:{ Exception -> 0x0097 }
            logNotificationActionClicked(r6, r7)     // Catch:{ Exception -> 0x0097 }
            boolean r5 = r3.equals(r2)     // Catch:{ Exception -> 0x0097 }
            if (r5 != 0) goto L_0x0047
            java.lang.String r5 = "ab_open"
            boolean r5 = r3.equals(r5)     // Catch:{ Exception -> 0x0097 }
            if (r5 == 0) goto L_0x0033
            goto L_0x0047
        L_0x0033:
            java.lang.String r7 = "ab_none"
            boolean r7 = r3.equals(r7)     // Catch:{ Exception -> 0x0097 }
            if (r7 == 0) goto L_0x003f
            com.appboy.push.AppboyNotificationUtils.cancelNotification(r6, r4)     // Catch:{ Exception -> 0x0097 }
            goto L_0x009f
        L_0x003f:
            java.lang.String r6 = TAG     // Catch:{ Exception -> 0x0097 }
            java.lang.String r7 = "Unknown notification action button clicked. Doing nothing."
            com.appboy.support.AppboyLogger.w((java.lang.String) r6, (java.lang.String) r7)     // Catch:{ Exception -> 0x0097 }
            goto L_0x009f
        L_0x0047:
            com.appboy.push.AppboyNotificationUtils.cancelNotification(r6, r4)     // Catch:{ Exception -> 0x0097 }
            android.content.Intent r4 = new android.content.Intent     // Catch:{ Exception -> 0x0097 }
            java.lang.String r5 = "android.intent.action.CLOSE_SYSTEM_DIALOGS"
            r4.<init>(r5)     // Catch:{ Exception -> 0x0097 }
            r6.sendBroadcast(r4)     // Catch:{ Exception -> 0x0097 }
            boolean r2 = r3.equals(r2)     // Catch:{ Exception -> 0x0097 }
            java.lang.String r3 = "uri"
            if (r2 == 0) goto L_0x0082
            android.os.Bundle r2 = r7.getExtras()     // Catch:{ Exception -> 0x0097 }
            boolean r2 = r2.containsKey(r1)     // Catch:{ Exception -> 0x0097 }
            if (r2 == 0) goto L_0x0082
            java.lang.String r1 = r7.getStringExtra(r1)     // Catch:{ Exception -> 0x0097 }
            r7.putExtra(r3, r1)     // Catch:{ Exception -> 0x0097 }
            android.os.Bundle r1 = r7.getExtras()     // Catch:{ Exception -> 0x0097 }
            boolean r1 = r1.containsKey(r0)     // Catch:{ Exception -> 0x0097 }
            if (r1 == 0) goto L_0x0085
            java.lang.String r1 = "ab_use_webview"
            java.lang.String r0 = r7.getStringExtra(r0)     // Catch:{ Exception -> 0x0097 }
            r7.putExtra(r1, r0)     // Catch:{ Exception -> 0x0097 }
            goto L_0x0085
        L_0x0082:
            r7.removeExtra(r3)     // Catch:{ Exception -> 0x0097 }
        L_0x0085:
            com.appboy.push.AppboyNotificationUtils.sendNotificationOpenedBroadcast(r6, r7)     // Catch:{ Exception -> 0x0097 }
            com.appboy.configuration.AppboyConfigurationProvider r0 = new com.appboy.configuration.AppboyConfigurationProvider     // Catch:{ Exception -> 0x0097 }
            r0.<init>(r6)     // Catch:{ Exception -> 0x0097 }
            boolean r0 = r0.getHandlePushDeepLinksAutomatically()     // Catch:{ Exception -> 0x0097 }
            if (r0 == 0) goto L_0x009f
            com.appboy.push.AppboyNotificationUtils.routeUserWithNotificationOpenedIntent(r6, r7)     // Catch:{ Exception -> 0x0097 }
            goto L_0x009f
        L_0x0097:
            r6 = move-exception
            java.lang.String r7 = TAG
            java.lang.String r0 = "Caught exception while handling notification action button click."
            com.appboy.support.AppboyLogger.e(r7, r0, r6)
        L_0x009f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.push.AppboyNotificationActionUtils.handleNotificationActionClicked(android.content.Context, android.content.Intent):void");
    }

    private static void addNotificationAction(Context context, NotificationCompat.Builder builder, Bundle bundle, int i) {
        Bundle bundle2 = new Bundle(bundle);
        String actionFieldAtIndex = getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_ACTION_TYPE_KEY_TEMPLATE);
        bundle2.putInt(Constants.APPBOY_ACTION_INDEX_KEY, i);
        bundle2.putString(Constants.APPBOY_ACTION_TYPE_KEY, actionFieldAtIndex);
        bundle2.putString(Constants.APPBOY_ACTION_ID_KEY, getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_ACTION_ID_KEY_TEMPLATE));
        bundle2.putString(Constants.APPBOY_ACTION_URI_KEY, getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_ACTION_URI_KEY_TEMPLATE));
        bundle2.putString(Constants.APPBOY_ACTION_USE_WEBVIEW_KEY, getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_ACTION_USE_WEBVIEW_KEY_TEMPLATE));
        Intent intent = new Intent(Constants.APPBOY_ACTION_CLICKED_ACTION).setClass(context, AppboyNotificationRoutingActivity.class);
        intent.putExtras(bundle2);
        NotificationCompat.Action.Builder builder2 = new NotificationCompat.Action.Builder(0, (CharSequence) getActionFieldAtIndex(i, bundle, Constants.APPBOY_PUSH_ACTION_TEXT_KEY_TEMPLATE), PendingIntent.getActivity(context, IntentUtils.getRequestCode(), intent, 134217728));
        builder2.addExtras(new Bundle(bundle2));
        builder.addAction(builder2.build());
    }

    private static void logNotificationActionClicked(Context context, Intent intent) {
        Appboy.getInstance(context).logPushNotificationActionClicked(intent.getStringExtra("cid"), intent.getStringExtra(Constants.APPBOY_ACTION_ID_KEY));
    }

    public static String getActionFieldAtIndex(int i, Bundle bundle, String str) {
        return getActionFieldAtIndex(i, bundle, str, "");
    }

    public static String getActionFieldAtIndex(int i, Bundle bundle, String str, String str2) {
        String string = bundle.getString(str.replace("*", String.valueOf(i)));
        return string == null ? str2 : string;
    }
}

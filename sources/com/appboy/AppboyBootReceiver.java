package com.appboy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import bo.app.dy;
import com.appboy.support.AppboyLogger;

public class AppboyBootReceiver extends BroadcastReceiver {
    private static final String a = AppboyLogger.getAppboyLogTag(AppboyBootReceiver.class);

    public void onReceive(Context context, Intent intent) {
        a(context, intent);
    }

    /* access modifiers changed from: package-private */
    public boolean a(Context context, Intent intent) {
        if (intent == null) {
            AppboyLogger.w(a, "Null intent received. Doing nothing.");
            return false;
        } else if (context == null) {
            String str = a;
            AppboyLogger.w(str, "Null context received for intent " + intent.toString() + ". Doing nothing.");
            return false;
        } else {
            String str2 = a;
            AppboyLogger.i(str2, "Received broadcast message. Message: " + intent.toString());
            if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
                AppboyLogger.i(a, "Boot complete intent received. Initializing.");
                dy.a(context);
                Appboy.getInstance(context);
                return true;
            }
            String str3 = a;
            AppboyLogger.w(str3, "Unknown intent " + intent.toString() + " received. Doing nothing.");
            return false;
        }
    }
}

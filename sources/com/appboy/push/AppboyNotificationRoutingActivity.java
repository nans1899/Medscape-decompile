package com.appboy.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.appboy.support.AppboyLogger;

public class AppboyNotificationRoutingActivity extends Activity {
    private static final String TAG = AppboyLogger.getAppboyLogTag(AppboyNotificationRoutingActivity.class);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            AppboyLogger.d(TAG, "Notification routing activity received null intent. Doing nothing.");
            finish();
            return;
        }
        String action = intent.getAction();
        if (action == null) {
            AppboyLogger.d(TAG, "Notification routing activity received intent with null action. Doing nothing.");
            finish();
            return;
        }
        String str = TAG;
        AppboyLogger.i(str, "Notification routing activity received intent: " + intent.toString());
        Context applicationContext = getApplicationContext();
        Intent intent2 = new Intent(action).setClass(applicationContext, AppboyNotificationUtils.getNotificationReceiverClass());
        intent2.putExtras(intent.getExtras());
        applicationContext.sendBroadcast(intent2);
        finish();
    }
}

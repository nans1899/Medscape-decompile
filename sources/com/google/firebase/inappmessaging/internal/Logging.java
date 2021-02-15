package com.google.firebase.inappmessaging.internal;

import android.util.Log;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class Logging {
    public static final String TAG = "FIAM.Headless";

    public static void logd(String str) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, str);
        }
    }

    public static void logi(String str) {
        if (Log.isLoggable(TAG, 4)) {
            Log.i(TAG, str);
        }
    }

    public static void loge(String str) {
        Log.e(TAG, str);
    }

    public static void logw(String str) {
        Log.w(TAG, str);
    }
}

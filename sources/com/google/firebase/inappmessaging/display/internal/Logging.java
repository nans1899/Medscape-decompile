package com.google.firebase.inappmessaging.display.internal;

import android.util.Log;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class Logging {
    private static final String TAG = "FIAM.Display";

    public static void logdNumber(String str, float f) {
        logd(str + ": " + f);
    }

    public static void logdPair(String str, float f, float f2) {
        logd(str + ": (" + f + ", " + f2 + ")");
    }

    public static void logdHeader(String str) {
        logd("============ " + str + " ============");
    }

    public static void logd(String str) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, str);
        }
    }

    public static void loge(String str) {
        Log.e(TAG, str);
    }

    public static void logi(String str) {
        if (Log.isLoggable(TAG, 4)) {
            Log.i(TAG, str);
        }
    }
}

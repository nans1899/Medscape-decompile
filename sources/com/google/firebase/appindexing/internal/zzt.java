package com.google.firebase.appindexing.internal;

import android.util.Log;
import com.google.firebase.appindexing.FirebaseAppIndex;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzt {
    public static int zzn(String str) {
        if (isLoggable(3)) {
            return Log.d(FirebaseAppIndex.APP_INDEXING_API_TAG, str);
        }
        return 0;
    }

    public static boolean isLoggable(int i) {
        if (Log.isLoggable(FirebaseAppIndex.APP_INDEXING_API_TAG, i)) {
            return true;
        }
        return Log.isLoggable(FirebaseAppIndex.APP_INDEXING_API_TAG, i);
    }
}

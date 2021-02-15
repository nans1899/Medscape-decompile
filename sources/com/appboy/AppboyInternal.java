package com.appboy;

import android.content.Context;
import bo.app.ca;
import bo.app.x;

public class AppboyInternal {
    public static void requestGeofenceRefresh(Context context, boolean z) {
        Appboy.getInstance(context).a(z);
    }

    public static void requestGeofenceRefresh(Context context, ca caVar) {
        Appboy.getInstance(context).a(caVar);
    }

    public static void recordGeofenceTransition(Context context, String str, x xVar) {
        Appboy.getInstance(context).a(str, xVar);
    }

    public static void addSerializedContentCardToStorage(Context context, String str, String str2) {
        Appboy.getInstance(context).a(str, str2);
    }

    public static void requestGeofencesInitialization(Context context) {
        Appboy.getInstance(context).a();
    }
}

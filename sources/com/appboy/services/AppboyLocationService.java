package com.appboy.services;

import android.content.Context;
import com.appboy.AppboyInternal;
import com.appboy.support.AppboyLogger;

public class AppboyLocationService {
    private static final String a = AppboyLogger.getAppboyLogTag(AppboyLocationService.class);

    public static void requestInitialization(Context context) {
        AppboyLogger.d(a, "Location permissions were granted. Requesting geofence initialization.");
        AppboyInternal.requestGeofencesInitialization(context);
    }
}

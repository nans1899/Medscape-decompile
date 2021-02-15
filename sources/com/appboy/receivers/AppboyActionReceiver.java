package com.appboy.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import bo.app.ca;
import bo.app.cg;
import bo.app.x;
import com.appboy.Appboy;
import com.appboy.AppboyInternal;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.LocationResult;
import java.util.List;

public class AppboyActionReceiver extends BroadcastReceiver {
    /* access modifiers changed from: private */
    public static final String a = AppboyLogger.getAppboyLogTag(AppboyActionReceiver.class);

    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            AppboyLogger.w(a, "AppboyActionReceiver received null intent. Doing nothing.");
        } else {
            new Thread(new a(context.getApplicationContext(), intent, goAsync())).start();
        }
    }

    static class a implements Runnable {
        private final String a;
        private final Context b;
        private final BroadcastReceiver.PendingResult c;
        private final Intent d;

        a(Context context, Intent intent, BroadcastReceiver.PendingResult pendingResult) {
            this.b = context;
            this.d = intent;
            this.a = intent.getAction();
            this.c = pendingResult;
        }

        public void run() {
            try {
                a();
            } catch (Exception e) {
                String a2 = AppboyActionReceiver.a;
                AppboyLogger.e(a2, "Caught exception while performing the AppboyActionReceiver work. Action: " + this.a + " Intent: " + this.d, e);
            }
            this.c.finish();
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            if (this.a == null) {
                AppboyLogger.d(AppboyActionReceiver.a, "Received intent with null action. Doing nothing.");
                return false;
            }
            String a2 = AppboyActionReceiver.a;
            AppboyLogger.d(a2, "Received intent with action " + this.a);
            if (this.a.equals(Constants.APPBOY_ACTION_RECEIVER_DATA_SYNC_INTENT_ACTION)) {
                AppboyLogger.d(AppboyActionReceiver.a, "Requesting immediate data flush from AppboyActionReceiver.", false);
                Appboy.getInstance(this.b).requestImmediateDataFlush();
                return true;
            } else if (this.a.equals(Constants.APPBOY_ACTION_RECEIVER_GEOFENCE_LOCATION_UPDATE_INTENT_ACTION)) {
                if (LocationResult.hasResult(this.d)) {
                    String a3 = AppboyActionReceiver.a;
                    AppboyLogger.d(a3, "AppboyActionReceiver received intent with location result: " + this.a);
                    return a(this.b, LocationResult.extractResult(this.d));
                }
                String a4 = AppboyActionReceiver.a;
                AppboyLogger.w(a4, "AppboyActionReceiver received intent without location result: " + this.a);
                return false;
            } else if (this.a.equals(Constants.APPBOY_ACTION_RECEIVER_GEOFENCE_UPDATE_INTENT_ACTION)) {
                String a5 = AppboyActionReceiver.a;
                AppboyLogger.d(a5, "AppboyActionReceiver received intent with geofence transition: " + this.a);
                return a(this.b, GeofencingEvent.fromIntent(this.d));
            } else {
                String a6 = AppboyActionReceiver.a;
                AppboyLogger.w(a6, "Unknown intent received in AppboyActionReceiver with action: " + this.a);
                return false;
            }
        }

        static boolean a(Context context, LocationResult locationResult) {
            try {
                Location lastLocation = locationResult.getLastLocation();
                AppboyInternal.requestGeofenceRefresh(context, (ca) new cg(lastLocation.getLatitude(), lastLocation.getLongitude(), Double.valueOf(lastLocation.getAltitude()), Double.valueOf((double) lastLocation.getAccuracy())));
                return true;
            } catch (Exception e) {
                AppboyLogger.e(AppboyActionReceiver.a, "Exception while processing location result", e);
                return false;
            }
        }

        static boolean a(Context context, GeofencingEvent geofencingEvent) {
            if (geofencingEvent.hasError()) {
                int errorCode = geofencingEvent.getErrorCode();
                String a2 = AppboyActionReceiver.a;
                AppboyLogger.e(a2, "AppboyLocation Services error: " + errorCode);
                return false;
            }
            int geofenceTransition = geofencingEvent.getGeofenceTransition();
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();
            if (1 == geofenceTransition) {
                for (Geofence requestId : triggeringGeofences) {
                    AppboyInternal.recordGeofenceTransition(context, requestId.getRequestId(), x.ENTER);
                }
                return true;
            } else if (2 == geofenceTransition) {
                for (Geofence requestId2 : triggeringGeofences) {
                    AppboyInternal.recordGeofenceTransition(context, requestId2.getRequestId(), x.EXIT);
                }
                return true;
            } else {
                String a3 = AppboyActionReceiver.a;
                AppboyLogger.w(a3, "Unsupported transition type received: " + geofenceTransition);
                return false;
            }
        }
    }
}

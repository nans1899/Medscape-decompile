package bo.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import com.appboy.models.AppboyGeofence;
import com.appboy.support.AppboyLogger;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public final class dy {
    /* access modifiers changed from: private */
    public static final String a = AppboyLogger.getAppboyLogTag(dy.class);

    public static void a(Context context) {
        AppboyLogger.d(a, "Deleting registered geofence cache.");
        SharedPreferences.Editor edit = context.getSharedPreferences("com.appboy.support.geofences", 0).edit();
        edit.clear();
        edit.apply();
    }

    public static void a(Context context, List<AppboyGeofence> list, PendingIntent pendingIntent) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("com.appboy.support.geofences", 0);
            List<AppboyGeofence> a2 = dx.a(sharedPreferences);
            if (list.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (AppboyGeofence next : a2) {
                    arrayList.add(next.getId());
                    String str = a;
                    AppboyLogger.d(str, "Obsolete geofence will be un-registered: " + next.getId());
                }
                if (!arrayList.isEmpty()) {
                    LocationServices.getGeofencingClient(context).removeGeofences((List<String>) arrayList);
                    sharedPreferences.edit().clear().apply();
                    String str2 = a;
                    AppboyLogger.d(str2, "No new geofences to register. Cleared " + a2.size() + " previously registered geofences.");
                    return;
                }
                AppboyLogger.d(a, "No new geofences to register. No geofences are currently registered.");
                return;
            }
            ArrayList<AppboyGeofence> arrayList2 = new ArrayList<>();
            HashSet hashSet = new HashSet();
            for (AppboyGeofence next2 : list) {
                hashSet.add(next2.getId());
                boolean z = true;
                for (AppboyGeofence next3 : a2) {
                    if (next2.getId().equals(next3.getId()) && next2.equivalentServerData(next3)) {
                        z = false;
                    }
                }
                if (z) {
                    String str3 = a;
                    AppboyLogger.d(str3, "New geofence will be registered: " + next2.getId());
                    arrayList2.add(next2);
                }
            }
            ArrayList<String> arrayList3 = new ArrayList<>();
            for (AppboyGeofence next4 : a2) {
                if (!hashSet.contains(next4.getId())) {
                    arrayList3.add(next4.getId());
                    String str4 = a;
                    AppboyLogger.d(str4, "Obsolete geofence will be un-registered: " + next4.getId());
                }
            }
            if (!arrayList3.isEmpty()) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                for (String remove : arrayList3) {
                    edit.remove(remove);
                }
                edit.apply();
                String str5 = a;
                AppboyLogger.d(str5, "Un-registering " + arrayList3.size() + " obsolete geofences from Google Play Services.");
                LocationServices.getGeofencingClient(context).removeGeofences((List<String>) arrayList3);
            } else {
                AppboyLogger.d(a, "No obsolete geofences need to be unregistered from Google Play Services.");
            }
            if (!arrayList2.isEmpty()) {
                ArrayList arrayList4 = new ArrayList();
                SharedPreferences.Editor edit2 = sharedPreferences.edit();
                for (AppboyGeofence appboyGeofence : arrayList2) {
                    arrayList4.add(appboyGeofence.toGeofence());
                    edit2.putString(appboyGeofence.getId(), appboyGeofence.forJsonPut().toString());
                }
                edit2.apply();
                String str6 = a;
                AppboyLogger.d(str6, "Registering " + arrayList2.size() + " new geofences with Google Play Services.");
                b(context, arrayList4, pendingIntent);
                return;
            }
            AppboyLogger.d(a, "No new geofences need to be registered with Google Play Services.");
        } catch (SecurityException e) {
            AppboyLogger.e(a, "Security exception while adding geofences.", e);
        } catch (Exception e2) {
            AppboyLogger.e(a, "Exception while adding geofences.", e2);
        }
    }

    public static void a(Context context, PendingIntent pendingIntent) {
        try {
            AppboyLogger.d(a, "Requesting single location update from Google Play Services.");
            LocationRequest create = LocationRequest.create();
            create.setPriority(100);
            create.setNumUpdates(1);
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            LocationServices.getFusedLocationProviderClient(context).requestLocationUpdates(create, pendingIntent);
        } catch (SecurityException e) {
            AppboyLogger.w(a, "Failed to request location update due to security exception from insufficient permissions.", e);
        } catch (Exception e2) {
            AppboyLogger.w(a, "Failed to request location update due to exception.", e2);
        }
    }

    private static void b(Context context, List<Geofence> list, PendingIntent pendingIntent) {
        LocationServices.getGeofencingClient(context).addGeofences(new GeofencingRequest.Builder().addGeofences(list).setInitialTrigger(0).build(), pendingIntent).addOnSuccessListener(new OnSuccessListener<Void>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                AppboyLogger.d(dy.a, "Geofences successfully registered with Google Play Services.");
            }
        }).addOnFailureListener(new OnFailureListener() {
            public void onFailure(Exception exc) {
                if (exc instanceof ApiException) {
                    int statusCode = ((ApiException) exc).getStatusCode();
                    if (statusCode != 0) {
                        switch (statusCode) {
                            case 1000:
                                String a = dy.a;
                                AppboyLogger.w(a, "Geofences not registered with Google Play Services due to GEOFENCE_NOT_AVAILABLE: " + statusCode);
                                return;
                            case 1001:
                                String a2 = dy.a;
                                AppboyLogger.w(a2, "Geofences not registered with Google Play Services due to GEOFENCE_TOO_MANY_GEOFENCES: " + statusCode);
                                return;
                            case 1002:
                                String a3 = dy.a;
                                AppboyLogger.w(a3, "Geofences not registered with Google Play Services due to GEOFENCE_TOO_MANY_PENDING_INTENTS: " + statusCode);
                                return;
                            default:
                                String a4 = dy.a;
                                AppboyLogger.w(a4, "Geofence pending result returned unknown status code: " + statusCode);
                                return;
                        }
                    } else {
                        AppboyLogger.d(dy.a, "Received Geofence registration success code in failure block with Google Play Services.");
                    }
                } else {
                    AppboyLogger.e(dy.a, "Geofence exception encountered while adding geofences.", exc);
                }
            }
        });
    }
}

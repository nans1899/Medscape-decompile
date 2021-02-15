package bo.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.models.AppboyGeofence;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PermissionUtils;
import com.google.android.gms.location.LocationServices;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class bi {
    private static final String j = AppboyLogger.getAppboyLogTag(bi.class);
    final bq a;
    final SharedPreferences b;
    final List<AppboyGeofence> c;
    final PendingIntent d;
    final PendingIntent e;
    bj f;
    ca g;
    boolean h;
    int i;
    private final Context k;
    private final AppboyConfigurationProvider l;
    private final dq m;
    private final Object n = new Object();

    public bi(Context context, String str, bq bqVar, AppboyConfigurationProvider appboyConfigurationProvider, dq dqVar) {
        boolean z = false;
        this.h = false;
        this.k = context.getApplicationContext();
        this.a = bqVar;
        this.b = context.getSharedPreferences(b(str), 0);
        this.l = appboyConfigurationProvider;
        this.m = dqVar;
        if (dx.a(dqVar) && a(context)) {
            z = true;
        }
        this.h = z;
        this.i = dx.b(this.m);
        this.c = dx.a(this.b);
        this.d = dx.a(context);
        this.e = dx.b(context);
        this.f = new bj(context, str, dqVar);
        a(true);
    }

    public void a() {
        AppboyLogger.d(j, "Request to set up geofences received.");
        this.h = dx.a(this.m) && a(this.k);
        a(false);
        b(true);
    }

    /* access modifiers changed from: protected */
    public boolean a(Context context) {
        if (!bk.a(this.l)) {
            AppboyLogger.d(j, "Location collection not available. Geofences not enabled.");
            return false;
        } else if (!PermissionUtils.hasPermission(context, "android.permission.ACCESS_FINE_LOCATION")) {
            AppboyLogger.i(j, "Fine grained location permissions not found. Geofences not enabled.");
            return false;
        } else if (!dz.a(context)) {
            AppboyLogger.d(j, "Google Play Services not available. Geofences not enabled.");
            return false;
        } else {
            try {
                if (Class.forName("com.google.android.gms.location.LocationServices", false, bi.class.getClassLoader()) != null) {
                    return true;
                }
                throw new RuntimeException("com.google.android.gms.location.LocationServices not found.");
            } catch (Exception unused) {
                AppboyLogger.d(j, "Google Play Services Location API not found. Geofences not enabled.");
                return false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        if (!this.h) {
            AppboyLogger.d(j, "Appboy geofences not enabled. Geofences not set up.");
            return;
        }
        AppboyLogger.d(j, "Location permissions and Google Play Services available. Location collection and Geofencing enabled via config.");
        if (z) {
            synchronized (this.n) {
                a(this.c, this.d);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(PendingIntent pendingIntent) {
        AppboyLogger.d(j, "Tearing down geofences.");
        if (pendingIntent != null) {
            AppboyLogger.d(j, "Unregistering any Braze geofences from Google Play Services.");
            LocationServices.getGeofencingClient(this.k).removeGeofences(pendingIntent);
        }
        synchronized (this.n) {
            AppboyLogger.d(j, "Deleting locally stored geofences.");
            SharedPreferences.Editor edit = this.b.edit();
            edit.clear();
            this.c.clear();
            edit.apply();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str, x xVar) {
        synchronized (this.n) {
            AppboyGeofence a2 = a(str);
            if (a2 != null) {
                if (xVar.equals(x.ENTER)) {
                    boolean analyticsEnabledEnter = a2.getAnalyticsEnabledEnter();
                    return analyticsEnabledEnter;
                } else if (xVar.equals(x.EXIT)) {
                    boolean analyticsEnabledExit = a2.getAnalyticsEnabledExit();
                    return analyticsEnabledExit;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public AppboyGeofence a(String str) {
        synchronized (this.n) {
            for (AppboyGeofence next : this.c) {
                if (next.getId().equals(str)) {
                    return next;
                }
            }
            return null;
        }
    }

    public void b(String str, x xVar) {
        if (!this.h) {
            AppboyLogger.w(j, "Appboy geofences not enabled. Not posting geofence report.");
            return;
        }
        try {
            cf d2 = cf.d(str, xVar.toString().toLowerCase(Locale.US));
            if (a(str, xVar)) {
                this.a.a((bz) d2);
            }
            if (this.f.a(du.a(), a(str), xVar)) {
                this.a.b(d2);
            }
        } catch (Exception e2) {
            AppboyLogger.w(j, "Failed to record geofence transition.", e2);
        }
    }

    public void a(ca caVar) {
        if (!this.h) {
            AppboyLogger.d(j, "Appboy geofences not enabled. Not requesting geofences.");
        } else if (caVar != null) {
            cg cgVar = new cg(caVar.a(), caVar.b(), caVar.c(), caVar.d());
            this.g = cgVar;
            this.a.a((ca) cgVar);
        }
    }

    public void b(boolean z) {
        if (!this.h) {
            AppboyLogger.d(j, "Appboy geofences not enabled. Not requesting geofences.");
        } else if (this.f.a(z, du.a())) {
            b(this.e);
        }
    }

    public void a(cn cnVar) {
        if (cnVar == null) {
            AppboyLogger.w(j, "Could not configure geofence manager from server config. Server config was null.");
            return;
        }
        boolean i2 = cnVar.i();
        String str = j;
        AppboyLogger.d(str, "Geofences enabled server config value " + i2 + " received.");
        boolean z = i2 && a(this.k);
        if (z != this.h) {
            this.h = z;
            String str2 = j;
            AppboyLogger.i(str2, "Geofences enabled status newly set to " + this.h + " during server config update.");
            if (this.h) {
                a(false);
                b(true);
            } else {
                a(this.d);
            }
        } else {
            String str3 = j;
            AppboyLogger.d(str3, "Geofences enabled status " + this.h + " unchanged during server config update.");
        }
        int h2 = cnVar.h();
        if (h2 >= 0) {
            this.i = h2;
            String str4 = j;
            AppboyLogger.i(str4, "Max number to register newly set to " + this.i + " via server config.");
        }
        this.f.a(cnVar);
    }

    public void a(List<AppboyGeofence> list) {
        if (list == null) {
            AppboyLogger.w(j, "Appboy geofence list was null. Not adding new geofences to local storage.");
        } else if (!this.h) {
            AppboyLogger.w(j, "Appboy geofences not enabled. Not adding new geofences to local storage.");
        } else {
            if (this.g != null) {
                for (AppboyGeofence next : list) {
                    next.setDistanceFromGeofenceRefresh(ee.a(this.g.a(), this.g.b(), next.getLatitude(), next.getLongitude()));
                }
                Collections.sort(list);
            }
            synchronized (this.n) {
                String str = j;
                AppboyLogger.d(str, "Received new geofence list of size: " + list.size());
                SharedPreferences.Editor edit = this.b.edit();
                edit.clear();
                this.c.clear();
                int i2 = 0;
                Iterator<AppboyGeofence> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    AppboyGeofence next2 = it.next();
                    if (i2 == this.i) {
                        String str2 = j;
                        AppboyLogger.d(str2, "Reached maximum number of new geofences: " + this.i);
                        break;
                    }
                    this.c.add(next2);
                    String str3 = j;
                    AppboyLogger.d(str3, "Adding new geofence to local storage: " + next2.toString());
                    edit.putString(next2.getId(), next2.forJsonPut().toString());
                    i2++;
                }
                edit.apply();
                String str4 = j;
                AppboyLogger.d(str4, "Added " + this.c.size() + " new geofences to local storage.");
            }
            this.f.a(list);
            a(true);
        }
    }

    public void b() {
        if (!this.h) {
            AppboyLogger.d(j, "Appboy geofences not enabled. Not un-registering geofences.");
            return;
        }
        AppboyLogger.d(j, "Tearing down all geofences.");
        a(this.d);
    }

    /* access modifiers changed from: protected */
    public void b(PendingIntent pendingIntent) {
        dy.a(this.k, pendingIntent);
    }

    /* access modifiers changed from: protected */
    public void a(List<AppboyGeofence> list, PendingIntent pendingIntent) {
        dy.a(this.k, list, pendingIntent);
    }

    static String b(String str) {
        return "com.appboy.managers.geofences.storage." + str;
    }
}

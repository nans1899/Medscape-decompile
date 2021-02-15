package bo.app;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import com.appboy.Constants;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PermissionUtils;
import com.appboy.support.StringUtils;

public class bk implements bt {
    /* access modifiers changed from: private */
    public static final String a = AppboyLogger.getAppboyLogTag(bk.class);
    private final Context b;
    private final String c;
    private final LocationManager d;
    private final bq e;
    private final boolean f;
    private String g;
    private BroadcastReceiver h = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                AppboyLogger.e(bk.a, "Location broadcast receiver received null intent.");
            } else if (intent.getAction().endsWith(Constants.APPBOY_SINGLE_LOCATION_UPDATE_INTENT_SUFFIX)) {
                bk.this.a(intent);
            }
        }
    };

    public bk(Context context, bq bqVar, AppboyConfigurationProvider appboyConfigurationProvider, dq dqVar) {
        this.b = context;
        this.c = context.getPackageName();
        this.e = bqVar;
        this.d = (LocationManager) context.getSystemService("location");
        this.f = a(appboyConfigurationProvider);
        this.b.registerReceiver(this.h, new IntentFilter(this.c + Constants.APPBOY_SINGLE_LOCATION_UPDATE_INTENT_SUFFIX));
    }

    /* access modifiers changed from: private */
    public void a(Intent intent) {
        try {
            String str = a;
            AppboyLogger.i(str, "Single location update received from Appboy location manager: " + intent.getAction());
            Location location = (Location) intent.getExtras().get("location");
            if (location != null) {
                a((ca) new cg(location.getLatitude(), location.getLongitude(), Double.valueOf(location.getAltitude()), Double.valueOf((double) location.getAccuracy())));
            } else {
                AppboyLogger.w(a, "Failed to process location update. Received location was null.");
            }
        } catch (Exception e2) {
            AppboyLogger.e(a, "Failed to process location update.", e2);
        }
    }

    public boolean a(ca caVar) {
        try {
            this.e.a((bz) cf.a(caVar));
            return true;
        } catch (Exception e2) {
            AppboyLogger.w(a, "Failed to log location recorded event.", e2);
            return false;
        }
    }

    public boolean a() {
        String str;
        if (!this.f) {
            AppboyLogger.i(a, "Did not request single location update. Location collection is disabled.");
            return false;
        } else if (PermissionUtils.hasPermission(this.b, "android.permission.ACCESS_FINE_LOCATION") || PermissionUtils.hasPermission(this.b, "android.permission.ACCESS_COARSE_LOCATION")) {
            if (PermissionUtils.hasPermission(this.b, "android.permission.ACCESS_FINE_LOCATION")) {
                str = "passive";
            } else {
                str = c();
            }
            if (StringUtils.isNullOrBlank(str)) {
                AppboyLogger.d(a, "Could not request single location update. Android location provider not found.");
                return false;
            }
            try {
                AppboyLogger.d(a, "Requesting single location update.");
                this.d.requestSingleUpdate(str, PendingIntent.getBroadcast(this.b, 0, new Intent(this.c + Constants.APPBOY_SINGLE_LOCATION_UPDATE_INTENT_SUFFIX), 134217728));
                return true;
            } catch (SecurityException e2) {
                AppboyLogger.w(a, "Failed to request single location update due to security exception from insufficient permissions.", e2);
                return false;
            } catch (Exception e3) {
                AppboyLogger.w(a, "Failed to request single location update due to exception.", e3);
                return false;
            }
        } else {
            AppboyLogger.i(a, "Did not request single location update. Fine grained location permissions not found.");
            return false;
        }
    }

    private String c() {
        String str = this.g;
        if (str != null) {
            return str;
        }
        Criteria criteria = new Criteria();
        criteria.setAccuracy(2);
        criteria.setPowerRequirement(1);
        String bestProvider = this.d.getBestProvider(criteria, true);
        this.g = bestProvider;
        return bestProvider;
    }

    public static boolean a(AppboyConfigurationProvider appboyConfigurationProvider) {
        if (appboyConfigurationProvider.isLocationCollectionEnabled()) {
            AppboyLogger.i(a, "Location collection enabled via sdk configuration.");
            return true;
        }
        AppboyLogger.i(a, "Location collection disabled via sdk configuration.");
        return false;
    }
}

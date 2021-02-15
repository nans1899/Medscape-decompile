package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.models.AppboyGeofence;
import com.appboy.support.AppboyLogger;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class bj {
    private static final String h = AppboyLogger.getAppboyLogTag(bj.class);
    final SharedPreferences a;
    final SharedPreferences b;
    Map<String, Long> c;
    long d = this.a.getLong("last_request_global", 0);
    long e = this.a.getLong("last_report_global", 0);
    int f;
    int g;

    bj(Context context, String str, dq dqVar) {
        this.a = context.getSharedPreferences("com.appboy.managers.geofences.eligibility.global." + str, 0);
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.appboy.managers.geofences.eligibility.individual." + str, 0);
        this.b = sharedPreferences;
        this.c = a(sharedPreferences);
        this.f = dqVar.c();
        this.g = dqVar.d();
    }

    /* access modifiers changed from: package-private */
    public void a(List<AppboyGeofence> list) {
        HashSet hashSet = new HashSet();
        for (AppboyGeofence id : list) {
            hashSet.add(id.getId());
        }
        HashSet<String> hashSet2 = new HashSet<>(this.c.keySet());
        SharedPreferences.Editor edit = this.b.edit();
        for (String str : hashSet2) {
            if (!hashSet.contains(a(str))) {
                String str2 = h;
                AppboyLogger.d(str2, "Deleting outdated re-eligibility id " + str + " from re-eligibility list.");
                this.c.remove(str);
                edit.remove(str);
            } else {
                String str3 = h;
                AppboyLogger.d(str3, "Retaining re-eligibility id " + str + " in re-eligibility list.");
            }
        }
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public boolean a(long j, AppboyGeofence appboyGeofence, x xVar) {
        long j2 = j;
        x xVar2 = xVar;
        if (appboyGeofence == null) {
            AppboyLogger.w(h, "Geofence passed into getReportEligible() was null.");
            return false;
        }
        String id = appboyGeofence.getId();
        String a2 = a(id, xVar2);
        int cooldownEnterSeconds = xVar2.equals(x.ENTER) ? appboyGeofence.getCooldownEnterSeconds() : appboyGeofence.getCooldownExitSeconds();
        long j3 = j2 - this.e;
        if (((long) this.g) > j3) {
            String str = h;
            AppboyLogger.d(str, "Geofence report suppressed since only " + j3 + " seconds have passed since the last time geofences were reported globally (minimum interval: " + this.g + "). id:" + id);
            return false;
        }
        if (this.c.containsKey(a2)) {
            long longValue = j2 - this.c.get(a2).longValue();
            String str2 = id;
            if (((long) cooldownEnterSeconds) > longValue) {
                String str3 = h;
                AppboyLogger.d(str3, "Geofence report suppressed since only " + longValue + " seconds have passed since the last time this geofence/transition combination was reported (minimum interval: " + cooldownEnterSeconds + "). id:" + str2 + " transition:" + xVar2);
                return false;
            }
            id = str2;
            String str4 = h;
            AppboyLogger.d(str4, longValue + " seconds have passed since the last time this geofence/transition combination was reported (minimum interval: " + cooldownEnterSeconds + "). id:" + id + " transition:" + xVar2);
        } else {
            String str5 = h;
            AppboyLogger.d(str5, "Geofence report eligible since this geofence/transition combination has never reported. id:" + id + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + xVar2);
        }
        String str6 = h;
        AppboyLogger.d(str6, "Geofence report eligible since " + j3 + " seconds have passed since the last time geofences were reported globally (minimum interval: " + this.g + "). id:" + id);
        this.c.put(a2, Long.valueOf(j));
        SharedPreferences.Editor edit = this.b.edit();
        long j4 = j;
        edit.putLong(a2, j4);
        edit.apply();
        this.e = j4;
        SharedPreferences.Editor edit2 = this.a.edit();
        edit2.putLong("last_report_global", j4);
        edit2.apply();
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(boolean z, long j) {
        long j2 = j - this.d;
        if (z || ((long) this.f) <= j2) {
            if (z) {
                String str = h;
                AppboyLogger.d(str, "Geofence request eligible. Ignoring rate limit for this geofence request. Elapsed time since last request:" + j2);
            } else {
                String str2 = h;
                AppboyLogger.d(str2, "Geofence request eligible since " + j2 + " seconds have passed since the last time geofences were requested (minimum interval: " + this.f + ").");
            }
            this.d = j;
            SharedPreferences.Editor edit = this.a.edit();
            edit.putLong("last_request_global", this.d);
            edit.apply();
            return true;
        }
        String str3 = h;
        AppboyLogger.d(str3, "Geofence request suppressed since only " + j2 + " seconds have passed since the last time geofences were requested (minimum interval: " + this.f + ").");
        return false;
    }

    /* access modifiers changed from: package-private */
    public String a(String str) {
        try {
            return str.split("_", 2)[1];
        } catch (Exception e2) {
            String str2 = h;
            AppboyLogger.i(str2, "Exception trying to parse re-eligibility id: " + str, (Throwable) e2);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public String a(String str, x xVar) {
        return xVar.toString().toLowerCase(Locale.US) + "_" + str;
    }

    /* access modifiers changed from: package-private */
    public void a(cn cnVar) {
        int f2 = cnVar.f();
        if (f2 >= 0) {
            this.f = f2;
            String str = h;
            AppboyLogger.i(str, "Min time since last geofence request reset via server configuration: " + f2 + "s.");
        }
        int g2 = cnVar.g();
        if (g2 >= 0) {
            this.g = g2;
            String str2 = h;
            AppboyLogger.i(str2, "Min time since last geofence report reset via server configuration: " + g2 + "s.");
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, Long> a(SharedPreferences sharedPreferences) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Map<String, ?> all = sharedPreferences.getAll();
        if (!(all == null || all.size() == 0)) {
            Set<String> keySet = all.keySet();
            if (keySet.size() == 0) {
                return concurrentHashMap;
            }
            for (String next : keySet) {
                long j = sharedPreferences.getLong(next, 0);
                String str = h;
                AppboyLogger.d(str, "Retrieving geofence id " + a(next) + " eligibility information from local storage.");
                concurrentHashMap.put(next, Long.valueOf(j));
            }
        }
        return concurrentHashMap;
    }
}

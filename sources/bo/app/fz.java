package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class fz implements fw {
    private static final String a = AppboyLogger.getAppboyLogTag(fz.class);
    private final SharedPreferences b;
    private Map<String, Long> c = a();

    public fz(Context context, String str, String str2) {
        this.b = context.getSharedPreferences("com.appboy.storage.triggers.re_eligibility" + StringUtils.getCacheFileSuffix(context, str, str2), 0);
    }

    public void a(List<ek> list) {
        HashSet hashSet = new HashSet();
        for (ek b2 : list) {
            hashSet.add(b2.b());
        }
        HashSet<String> hashSet2 = new HashSet<>(this.c.keySet());
        SharedPreferences.Editor edit = this.b.edit();
        for (String str : hashSet2) {
            if (!hashSet.contains(str)) {
                String str2 = a;
                AppboyLogger.d(str2, "Deleting outdated triggered action id " + str + " from re-eligibility list.");
                this.c.remove(str);
                edit.remove(str);
            } else {
                String str3 = a;
                AppboyLogger.d(str3, "Retaining triggered action " + str + " in re-eligibility list.");
            }
        }
        edit.apply();
    }

    public boolean a(ek ekVar) {
        fd f = ekVar.c().f();
        if (f.a()) {
            String str = a;
            AppboyLogger.d(str, "Triggered action id " + ekVar.b() + " always eligible via configuration. Returning true for eligibility status");
            return true;
        } else if (!this.c.containsKey(ekVar.b())) {
            String str2 = a;
            AppboyLogger.d(str2, "Triggered action id " + ekVar.b() + " always eligible via never having been triggered. Returning true for eligibility status");
            return true;
        } else if (f.b()) {
            String str3 = a;
            AppboyLogger.d(str3, "Triggered action id " + ekVar.b() + " no longer eligible due to having been triggered in the past and is only eligible once.");
            return false;
        } else {
            long longValue = this.c.get(ekVar.b()).longValue();
            if (du.a() + ((long) ekVar.c().d()) >= ((long) f.c().intValue()) + longValue) {
                String str4 = a;
                AppboyLogger.d(str4, "Trigger action is re-eligible for display since " + (du.a() - longValue) + " seconds have passed since the last time it was triggered (minimum interval: " + f.c() + ").");
                return true;
            }
            String str5 = a;
            AppboyLogger.d(str5, "Trigger action is not re-eligible for display since only " + (du.a() - longValue) + " seconds have passed since the last time it was triggered (minimum interval: " + f.c() + ").");
            return false;
        }
    }

    public void a(ek ekVar, long j) {
        String str = a;
        AppboyLogger.d(str, "Updating re-eligibility for action Id " + ekVar.b() + " to time " + j + ".");
        this.c.put(ekVar.b(), Long.valueOf(j));
        SharedPreferences.Editor edit = this.b.edit();
        edit.putLong(ekVar.b(), j);
        edit.apply();
    }

    private Map<String, Long> a() {
        Set<String> keySet;
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Map<String, ?> all = this.b.getAll();
        if (!(all == null || all.size() == 0 || (keySet = all.keySet()) == null || keySet.size() == 0)) {
            try {
                for (String next : keySet) {
                    long j = this.b.getLong(next, 0);
                    String str = a;
                    AppboyLogger.d(str, "Retrieving triggered action id " + next + " eligibility information from local storage.");
                    concurrentHashMap.put(next, Long.valueOf(j));
                }
            } catch (Exception e) {
                AppboyLogger.e(a, "Encountered unexpected exception while parsing stored re-eligibility information.", e);
            }
        }
        return concurrentHashMap;
    }
}

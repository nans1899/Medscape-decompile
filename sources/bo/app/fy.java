package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONException;
import org.json.JSONObject;

public class fy implements fu {
    private static final String a = AppboyLogger.getAppboyLogTag(fy.class);
    /* access modifiers changed from: private */
    public final Context b;
    private final bq c;
    /* access modifiers changed from: private */
    public final ad d;
    private final long e;
    private final SharedPreferences f;
    private final ft g;
    private final fw h;
    private Map<String, ek> i;
    private volatile long j = 0;
    private final Object k = new Object();

    public fy(Context context, bq bqVar, ThreadPoolExecutor threadPoolExecutor, ad adVar, AppboyConfigurationProvider appboyConfigurationProvider, String str, String str2) {
        this.b = context.getApplicationContext();
        this.c = bqVar;
        this.d = adVar;
        this.e = appboyConfigurationProvider.getTriggerActionMinimumTimeIntervalInSeconds();
        this.f = context.getSharedPreferences("com.appboy.storage.triggers.actions" + StringUtils.getCacheFileSuffix(context, str, str2), 0);
        this.g = new fx(context, threadPoolExecutor, str2);
        this.h = new fz(context, str, str2);
        this.i = b();
    }

    public void a(List<ek> list) {
        boolean z;
        fq fqVar = new fq();
        if (list == null) {
            AppboyLogger.w(a, "Received a null list of triggers in registerTriggeredActions(). Doing nothing.");
            return;
        }
        synchronized (this.k) {
            this.i.clear();
            SharedPreferences.Editor edit = this.f.edit();
            edit.clear();
            String str = a;
            AppboyLogger.d(str, "Registering " + list.size() + " new triggered actions.");
            z = false;
            for (ek next : list) {
                String str2 = a;
                AppboyLogger.d(str2, "Registering triggered action id " + next.b());
                this.i.put(next.b(), next);
                edit.putString(next.b(), ((JSONObject) next.forJsonPut()).toString());
                if (next.a((fk) fqVar)) {
                    z = true;
                }
            }
            edit.apply();
        }
        this.h.a(list);
        this.g.a(list);
        if (z) {
            AppboyLogger.i(a, "Test triggered actions found, triggering test event.");
            a((fk) fqVar);
            return;
        }
        AppboyLogger.d(a, "No test triggered actions found.");
    }

    public void a(fk fkVar) {
        String str = a;
        AppboyLogger.d(str, "New incoming <" + fkVar.b() + ">. Searching for matching triggers.");
        ek b2 = b(fkVar);
        if (b2 != null) {
            a(fkVar, b2);
        }
    }

    public fw a() {
        return this.h;
    }

    public void a(long j2) {
        this.j = j2;
    }

    /* access modifiers changed from: package-private */
    public ek b(fk fkVar) {
        synchronized (this.k) {
            int i2 = Integer.MIN_VALUE;
            ek ekVar = null;
            for (ek next : this.i.values()) {
                if (next.a(fkVar) && this.h.a(next)) {
                    if (a(fkVar, next, this.j, this.e)) {
                        AppboyLogger.d(a, "Found potential triggered action for incoming trigger event. Action id " + next.b() + ".");
                        int c2 = next.c().c();
                        if (c2 > i2) {
                            ekVar = next;
                            i2 = c2;
                        }
                    }
                }
            }
            if (ekVar == null) {
                AppboyLogger.d(a, "Failed to match triggered action for incoming <" + fkVar.b() + ">.");
                return null;
            }
            String str = a;
            StringBuilder sb = new StringBuilder();
            sb.append("Found best triggered action for incoming trigger event ");
            sb.append(fkVar.e() != null ? ed.a((JSONObject) fkVar.e().forJsonPut()) : "");
            sb.append(".\nMatched Action id: ");
            sb.append(ekVar.b());
            sb.append(".");
            AppboyLogger.d(str, sb.toString());
            return ekVar;
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, ek> b() {
        Set<String> keySet;
        HashMap hashMap = new HashMap();
        Map<String, ?> all = this.f.getAll();
        if (!(all == null || all.size() == 0 || (keySet = all.keySet()) == null || keySet.size() == 0)) {
            try {
                for (String next : keySet) {
                    String string = this.f.getString(next, (String) null);
                    if (StringUtils.isNullOrBlank(string)) {
                        String str = a;
                        AppboyLogger.w(str, "Received null or blank serialized triggered action string for action id " + next + " from shared preferences. Not parsing.");
                    } else {
                        ek b2 = gb.b(new JSONObject(string), this.c);
                        if (b2 != null) {
                            hashMap.put(b2.b(), b2);
                            String str2 = a;
                            AppboyLogger.d(str2, "Retrieving templated triggered action id " + b2.b() + " from local storage.");
                        }
                    }
                }
            } catch (JSONException e2) {
                AppboyLogger.e(a, "Encountered Json exception while parsing stored triggered actions.", e2);
            } catch (Exception e3) {
                AppboyLogger.e(a, "Encountered unexpected exception while parsing stored triggered actions.", e3);
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public void a(fk fkVar, ek ekVar) {
        ekVar.a(this.g.a(ekVar));
        fe c2 = ekVar.c();
        final long d2 = c2.e() != -1 ? fkVar.d() + ((long) c2.e()) : -1;
        Handler handler = new Handler(Looper.getMainLooper());
        int d3 = c2.d();
        String str = a;
        AppboyLogger.d(str, "Performing triggered action after a delay of " + d3 + " seconds.");
        final ek ekVar2 = ekVar;
        final fk fkVar2 = fkVar;
        handler.postDelayed(new Runnable() {
            public void run() {
                ekVar2.a(fy.this.b, fy.this.d, fkVar2, d2);
            }
        }, (long) (d3 * 1000));
    }

    static boolean a(fk fkVar, ek ekVar, long j2, long j3) {
        long j4;
        if (fkVar instanceof fq) {
            AppboyLogger.d(a, "Ignoring minimum time interval between triggered actions because the trigger event is a test.");
            return true;
        }
        long a2 = du.a() + ((long) ekVar.c().d());
        int g2 = ekVar.c().g();
        if (g2 != -1) {
            String str = a;
            AppboyLogger.d(str, "Using override minimum display interval: " + g2);
            j4 = j2 + ((long) g2);
        } else {
            j4 = j2 + j3;
        }
        if (a2 >= j4) {
            String str2 = a;
            AppboyLogger.i(str2, "Minimum time interval requirement met for matched trigger. Action display time: " + a2 + " . Next viable display time: " + j4);
            return true;
        }
        String str3 = a;
        AppboyLogger.i(str3, "Minimum time interval requirement and triggered action override time interval requirement of " + j3 + " not met for matched trigger. Returning null. Next viable display time: " + j4 + ". Action display time: " + a2);
        return false;
    }
}

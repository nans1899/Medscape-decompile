package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

public class dq {
    private static final String a = AppboyLogger.getAppboyLogTag(dq.class);
    private final SharedPreferences b;
    /* access modifiers changed from: private */
    public final Object c = new Object();
    private AtomicBoolean d = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public cn e;

    public dq(Context context, String str) {
        String str2;
        if (str == null) {
            AppboyLogger.e(a, "ServerConfigStorageProvider received null api key.");
            str2 = "";
        } else {
            str2 = "." + str;
        }
        this.b = context.getSharedPreferences("com.appboy.storage.serverconfigstorageprovider" + str2, 0);
        new a().execute(new Void[0]);
    }

    class a extends AsyncTask<Void, Void, Void> {
        private a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            cn cnVar = new cn();
            cnVar.b(dq.this.i());
            cnVar.a(dq.this.h());
            cnVar.c(dq.this.j());
            cnVar.a(dq.this.g());
            cnVar.b(dq.this.f());
            cnVar.a(dq.this.c());
            cnVar.b(dq.this.d());
            cnVar.c(dq.this.e());
            cnVar.a(dq.this.b());
            cnVar.b(dq.this.a());
            cnVar.c(dq.this.k());
            synchronized (dq.this.c) {
                cn unused = dq.this.e = cnVar;
            }
            return null;
        }
    }

    public void a(cn cnVar) {
        synchronized (this.c) {
            this.e = cnVar;
        }
        try {
            SharedPreferences.Editor edit = this.b.edit();
            if (cnVar.b() != null) {
                edit.putString("blacklisted_events", new JSONArray(cnVar.b()).toString());
            }
            if (cnVar.c() != null) {
                edit.putString("blacklisted_attributes", new JSONArray(cnVar.c()).toString());
            }
            if (cnVar.d() != null) {
                edit.putString("blacklisted_purchases", new JSONArray(cnVar.d()).toString());
            }
            edit.putLong("config_time", cnVar.a());
            edit.putInt("geofences_min_time_since_last_request", cnVar.f());
            edit.putInt("geofences_min_time_since_last_report", cnVar.g());
            edit.putInt("geofences_max_num_to_register", cnVar.h());
            edit.putBoolean("geofences_enabled", cnVar.i());
            edit.putBoolean("geofences_enabled_set", cnVar.j());
            edit.putLong("messaging_session_timeout", cnVar.e());
            edit.putBoolean("test_user_device_logging_enabled", cnVar.k());
            edit.apply();
        } catch (Exception e2) {
            AppboyLogger.w(a, "Could not persist server config to shared preferences.", e2);
        }
    }

    public boolean a() {
        synchronized (this.c) {
            if (this.e != null) {
                boolean j = this.e.j();
                return j;
            }
            boolean z = this.b.getBoolean("geofences_enabled_set", false);
            return z;
        }
    }

    public boolean b() {
        synchronized (this.c) {
            if (this.e != null) {
                boolean i = this.e.i();
                return i;
            }
            boolean z = this.b.getBoolean("geofences_enabled", false);
            return z;
        }
    }

    public int c() {
        synchronized (this.c) {
            if (this.e != null) {
                int f = this.e.f();
                return f;
            }
            int i = this.b.getInt("geofences_min_time_since_last_request", -1);
            return i;
        }
    }

    public int d() {
        synchronized (this.c) {
            if (this.e != null) {
                int g = this.e.g();
                return g;
            }
            int i = this.b.getInt("geofences_min_time_since_last_report", -1);
            return i;
        }
    }

    public int e() {
        synchronized (this.c) {
            if (this.e != null) {
                int h = this.e.h();
                return h;
            }
            int i = this.b.getInt("geofences_max_num_to_register", -1);
            return i;
        }
    }

    public long f() {
        synchronized (this.c) {
            if (this.e != null) {
                long e2 = this.e.e();
                return e2;
            }
            long j = this.b.getLong("messaging_session_timeout", -1);
            return j;
        }
    }

    public long g() {
        synchronized (this.c) {
            if (this.e != null) {
                long a2 = this.e.a();
                return a2;
            }
            long j = this.b.getLong("config_time", 0);
            return j;
        }
    }

    public Set<String> h() {
        Set<String> set;
        synchronized (this.c) {
            if (this.e != null) {
                set = this.e.b();
            } else {
                set = a("blacklisted_events");
            }
            if (set != null) {
                return set;
            }
            HashSet hashSet = new HashSet();
            return hashSet;
        }
    }

    public Set<String> i() {
        Set<String> set;
        synchronized (this.c) {
            if (this.e != null) {
                set = this.e.c();
            } else {
                set = a("blacklisted_attributes");
            }
            if (set != null) {
                return set;
            }
            HashSet hashSet = new HashSet();
            return hashSet;
        }
    }

    public Set<String> j() {
        Set<String> set;
        synchronized (this.c) {
            if (this.e != null) {
                set = this.e.d();
            } else {
                set = a("blacklisted_purchases");
            }
            if (set != null) {
                return set;
            }
            HashSet hashSet = new HashSet();
            return hashSet;
        }
    }

    public boolean k() {
        synchronized (this.c) {
            if (this.e != null) {
                boolean k = this.e.k();
                return k;
            }
            boolean z = this.b.getBoolean("test_user_device_logging_enabled", false);
            return z;
        }
    }

    public boolean l() {
        return this.d.get();
    }

    public void a(boolean z) {
        this.d.set(z);
    }

    private Set<String> a(String str) {
        try {
            String string = this.b.getString(str, "");
            if (StringUtils.isNullOrBlank(string)) {
                return null;
            }
            JSONArray jSONArray = new JSONArray(string);
            HashSet hashSet = new HashSet();
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.getString(i));
            }
            return hashSet;
        } catch (Exception e2) {
            AppboyLogger.w(a, "Experienced exception retrieving blacklisted strings from local storage. Returning null.", e2);
            return null;
        }
    }
}

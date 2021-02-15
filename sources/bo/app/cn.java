package bo.app;

import com.appboy.support.AppboyLogger;
import com.medscape.android.cache.Cache;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class cn {
    private static final String a = AppboyLogger.getAppboyLogTag(cn.class);
    private long b;
    private Set<String> c;
    private Set<String> d;
    private Set<String> e;
    private int f = -1;
    private int g = -1;
    private int h = -1;
    private boolean i = false;
    private boolean j = false;
    private long k = -1;
    private boolean l = false;

    public cn() {
    }

    public cn(JSONObject jSONObject) {
        this.c = a(jSONObject, "events_blacklist");
        this.d = a(jSONObject, "attributes_blacklist");
        this.e = a(jSONObject, "purchases_blacklist");
        this.b = jSONObject.optLong(Cache.Caches.TIME, 0);
        this.k = jSONObject.optLong("messaging_session_timeout", -1);
        JSONObject optJSONObject = jSONObject.optJSONObject("geofences");
        if (optJSONObject != null) {
            try {
                this.f = optJSONObject.getInt("min_time_since_last_request");
                this.g = optJSONObject.getInt("min_time_since_last_report");
                this.j = optJSONObject.getBoolean("enabled");
                this.i = true;
                this.h = optJSONObject.optInt("max_num_to_register", 20);
            } catch (JSONException e2) {
                AppboyLogger.e(a, "Required geofence fields were null. Using defaults.", e2);
                this.f = -1;
                this.g = -1;
                this.h = -1;
                this.j = false;
                this.i = false;
            }
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("test_user");
        if (optJSONObject2 != null) {
            try {
                this.l = optJSONObject2.getBoolean("device_logging_enabled");
            } catch (JSONException e3) {
                AppboyLogger.e(a, "Required test user fields were null. Using defaults", e3);
                this.l = false;
            }
        }
    }

    public long a() {
        return this.b;
    }

    public Set<String> b() {
        return this.c;
    }

    public Set<String> c() {
        return this.d;
    }

    public Set<String> d() {
        return this.e;
    }

    public long e() {
        return this.k;
    }

    public int f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    public int h() {
        return this.h;
    }

    public boolean i() {
        return this.j;
    }

    public boolean j() {
        return this.i;
    }

    public boolean k() {
        return this.l;
    }

    public void a(long j2) {
        this.b = j2;
    }

    public void a(Set<String> set) {
        this.c = set;
    }

    public void b(Set<String> set) {
        this.d = set;
    }

    public void c(Set<String> set) {
        this.e = set;
    }

    public void b(long j2) {
        this.k = j2;
    }

    public void a(int i2) {
        this.f = i2;
    }

    public void b(int i2) {
        this.g = i2;
    }

    public void c(int i2) {
        this.h = i2;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public void c(boolean z) {
        this.l = z;
    }

    private Set<String> a(JSONObject jSONObject, String str) {
        if (jSONObject.optJSONArray(str) == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            hashSet.add(optJSONArray.getString(i2));
        }
        return hashSet;
    }
}

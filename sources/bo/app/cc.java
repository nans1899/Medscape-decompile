package bo.app;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import org.json.JSONException;
import org.json.JSONObject;

public class cc implements IPutIntoJson<JSONObject> {
    private static final String a = AppboyLogger.getAppboyLogTag(cc.class);
    private final cd b;
    private final double c;
    private volatile Double d;
    private volatile boolean e;

    public cc(cd cdVar, double d2) {
        this(cdVar, d2, (Double) null, false);
    }

    public cc(cd cdVar, double d2, Double d3, boolean z) {
        this.e = false;
        this.b = cdVar;
        this.c = d2;
        this.e = z;
        this.d = d3;
    }

    public cc(JSONObject jSONObject) {
        this.e = false;
        this.b = cd.a(jSONObject.getString("session_id"));
        this.c = jSONObject.getDouble("start_time");
        this.e = jSONObject.getBoolean("is_sealed");
        if (jSONObject.has("end_time")) {
            this.d = Double.valueOf(jSONObject.getDouble("end_time"));
        }
    }

    public cd a() {
        return this.b;
    }

    public double b() {
        return this.c;
    }

    public Double c() {
        return this.d;
    }

    public void a(Double d2) {
        this.d = d2;
    }

    public boolean d() {
        return this.e;
    }

    public void e() {
        this.e = true;
        a(Double.valueOf(du.b()));
    }

    public long f() {
        if (this.d == null) {
            return -1;
        }
        long doubleValue = (long) (this.d.doubleValue() - this.c);
        if (doubleValue < 0) {
            String str = a;
            AppboyLogger.w(str, "End time '" + this.d + "' for session is less than the start time '" + this.c + "' for this session.");
        }
        return doubleValue;
    }

    /* renamed from: g */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("session_id", this.b);
            jSONObject.put("start_time", this.c);
            jSONObject.put("is_sealed", this.e);
            if (this.d != null) {
                jSONObject.put("end_time", this.d);
            }
        } catch (JSONException e2) {
            AppboyLogger.e(a, "Caught exception creating Session Json.", e2);
        }
        return jSONObject;
    }
}

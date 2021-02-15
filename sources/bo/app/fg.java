package bo.app;

import com.google.firebase.messaging.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class fg implements fe {
    private final long a;
    private final long b;
    private final int c;
    private final int d;
    private final int e;
    private final fd f;
    private final int g;

    public fg(JSONObject jSONObject) {
        this.a = jSONObject.optLong("start_time", -1);
        this.b = jSONObject.optLong("end_time", -1);
        this.c = jSONObject.optInt(Constants.FirelogAnalytics.PARAM_PRIORITY, 0);
        this.g = jSONObject.optInt("min_seconds_since_last_trigger", -1);
        this.d = jSONObject.optInt("delay", 0);
        this.e = jSONObject.optInt("timeout", -1);
        this.f = new ff(jSONObject);
    }

    public long a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public fd f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    /* renamed from: h */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = (JSONObject) this.f.forJsonPut();
            jSONObject.put("start_time", this.a);
            jSONObject.put("end_time", this.b);
            jSONObject.put(Constants.FirelogAnalytics.PARAM_PRIORITY, this.c);
            jSONObject.put("min_seconds_since_last_trigger", this.g);
            jSONObject.put("timeout", this.e);
            jSONObject.put("delay", this.d);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}

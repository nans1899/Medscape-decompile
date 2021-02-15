package bo.app;

import com.appboy.support.AppboyLogger;
import com.appboy.support.ValidationUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class cg implements ca {
    private static final String a = AppboyLogger.getAppboyLogTag(cg.class);
    private final double b;
    private final double c;
    private final Double d;
    private final Double e;

    public cg(double d2, double d3, Double d4, Double d5) {
        if (ValidationUtils.isValidLocation(d2, d3)) {
            this.b = d2;
            this.c = d3;
            this.d = d4;
            this.e = d5;
            return;
        }
        throw new IllegalArgumentException("Unable to create AppboyLocation. Latitude and longitude values are bounded by ±90 and ±180 respectively");
    }

    public double a() {
        return this.b;
    }

    public double b() {
        return this.c;
    }

    public boolean e() {
        return this.d != null;
    }

    public Double c() {
        return this.d;
    }

    public boolean f() {
        return this.e != null;
    }

    public Double d() {
        return this.e;
    }

    /* renamed from: g */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("latitude", this.b);
            jSONObject.put("longitude", this.c);
            if (e()) {
                jSONObject.put("altitude", this.d);
            }
            if (f()) {
                jSONObject.put("ll_accuracy", this.e);
            }
        } catch (JSONException e2) {
            AppboyLogger.e(a, "Caught exception creating location Json.", e2);
        }
        return jSONObject;
    }
}

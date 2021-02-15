package bo.app;

import org.json.JSONException;
import org.json.JSONObject;

public class ff implements fd {
    private final int a;

    public ff(JSONObject jSONObject) {
        this.a = jSONObject.optInt("re_eligibility", -1);
    }

    public boolean a() {
        return this.a == 0;
    }

    public boolean b() {
        return this.a == -1;
    }

    public Integer c() {
        int i = this.a;
        if (i > 0) {
            return Integer.valueOf(i);
        }
        return null;
    }

    /* renamed from: d */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("re_eligibility", this.a);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}

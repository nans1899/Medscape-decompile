package bo.app;

import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class en implements ek {
    private static final String a = AppboyLogger.getAppboyLogTag(en.class);
    private final String b;
    private final fe c;
    private final List<er> d = new ArrayList();
    private boolean e;

    protected en(JSONObject jSONObject) {
        this.b = jSONObject.getString("id");
        this.c = new fg(jSONObject);
        JSONArray jSONArray = jSONObject.getJSONArray("trigger_condition");
        if (jSONArray != null && jSONArray.length() > 0) {
            this.d.addAll(gb.a(jSONArray));
        }
        this.e = jSONObject.optBoolean("prefetch", true);
    }

    public boolean a(fk fkVar) {
        if (!i()) {
            String str = a;
            AppboyLogger.d(str, "Triggered action " + this.b + "not eligible to be triggered by " + fkVar.b() + " event. Current device time outside triggered action time window.");
            return false;
        }
        for (er a2 : this.d) {
            if (a2.a(fkVar)) {
                return true;
            }
        }
        return false;
    }

    public boolean a() {
        return this.e;
    }

    public fe c() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    /* renamed from: e */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = (JSONObject) this.c.forJsonPut();
            jSONObject.put("id", this.b);
            if (this.d != null) {
                JSONArray jSONArray = new JSONArray();
                for (er forJsonPut : this.d) {
                    jSONArray.put(forJsonPut.forJsonPut());
                }
                jSONObject.put("trigger_condition", jSONArray);
                jSONObject.put("prefetch", this.e);
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean i() {
        return j() && k();
    }

    /* access modifiers changed from: package-private */
    public boolean j() {
        return this.c.a() == -1 || du.a() > this.c.a();
    }

    /* access modifiers changed from: package-private */
    public boolean k() {
        return this.c.b() == -1 || du.a() < this.c.b();
    }
}

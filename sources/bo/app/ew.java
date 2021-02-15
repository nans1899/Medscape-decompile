package bo.app;

import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class ew extends ez {
    private static final String b = AppboyLogger.getAppboyLogTag(ew.class);
    private String c;

    public ew(JSONObject jSONObject) {
        super(jSONObject);
        this.c = jSONObject.getJSONObject("data").getString("product_id");
    }

    public boolean a(fk fkVar) {
        if (!(fkVar instanceof fo) || StringUtils.isNullOrBlank(this.c)) {
            return false;
        }
        fo foVar = (fo) fkVar;
        if (!StringUtils.isNullOrBlank(foVar.a()) && foVar.a().equals(this.c)) {
            return super.a(fkVar);
        }
        return false;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        JSONObject a = super.forJsonPut();
        try {
            a.put("type", "purchase_property");
            JSONObject jSONObject = a.getJSONObject("data");
            jSONObject.put("product_id", this.c);
            a.put("data", jSONObject);
        } catch (JSONException e) {
            AppboyLogger.e(b, "Caught exception creating Json.", e);
        }
        return a;
    }
}

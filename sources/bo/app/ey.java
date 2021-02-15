package bo.app;

import org.json.JSONException;
import org.json.JSONObject;

public final class ey implements er {
    public boolean a(fk fkVar) {
        return fkVar instanceof fq;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "test");
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}

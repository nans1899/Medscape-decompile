package bo.app;

import com.appboy.support.StringUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.json.JSONException;
import org.json.JSONObject;

public final class ev implements er {
    String a;

    ev() {
    }

    public ev(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        if (optJSONObject != null && !optJSONObject.isNull("product_id")) {
            this.a = optJSONObject.optString("product_id", (String) null);
        }
    }

    public boolean a(fk fkVar) {
        if (!(fkVar instanceof fo)) {
            return false;
        }
        if (StringUtils.isNullOrBlank(this.a)) {
            return true;
        }
        fo foVar = (fo) fkVar;
        if (StringUtils.isNullOrBlank(foVar.a()) || !foVar.a().equals(this.a)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", FirebaseAnalytics.Event.PURCHASE);
            if (this.a != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.putOpt("product_id", this.a);
                jSONObject.putOpt("data", jSONObject2);
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}

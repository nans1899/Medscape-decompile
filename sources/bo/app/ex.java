package bo.app;

import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class ex implements er {
    private String a;

    ex() {
    }

    public ex(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        if (optJSONObject != null && !optJSONObject.isNull("campaign_id")) {
            this.a = optJSONObject.optString("campaign_id", (String) null);
        }
    }

    public boolean a(fk fkVar) {
        if (!(fkVar instanceof fp)) {
            return false;
        }
        if (StringUtils.isNullOrBlank(this.a)) {
            return true;
        }
        fp fpVar = (fp) fkVar;
        if (StringUtils.isNullOrBlank(fpVar.a()) || !fpVar.a().equals(this.a)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "push_click");
            if (this.a != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.putOpt("campaign_id", this.a);
                jSONObject.putOpt("data", jSONObject2);
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}

package bo.app;

import com.appboy.support.StringUtils;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class es implements er {
    private String a;
    private Set<String> b = new HashSet();

    public es(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        this.a = jSONObject2.getString("id");
        JSONArray optJSONArray = jSONObject2.optJSONArray(MessengerShareContentUtility.BUTTONS);
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                this.b.add(optJSONArray.getString(i));
            }
        }
    }

    public boolean a(fk fkVar) {
        if (fkVar instanceof fm) {
            fm fmVar = (fm) fkVar;
            if (!StringUtils.isNullOrBlank(fmVar.a()) && fmVar.a().equals(this.a)) {
                if (this.b.size() <= 0) {
                    return StringUtils.isNullOrBlank(fmVar.f());
                }
                if (StringUtils.isNullOrBlank(fmVar.f()) || !this.b.contains(fmVar.f())) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "iam_click");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("id", this.a);
            if (this.b.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (String put : this.b) {
                    jSONArray.put(put);
                }
                jSONObject2.put(MessengerShareContentUtility.BUTTONS, jSONArray);
            }
            jSONObject.put("data", jSONObject2);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}

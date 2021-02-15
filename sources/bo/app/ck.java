package bo.app;

import com.appboy.models.IPutIntoJson;
import com.facebook.AccessToken;
import org.json.JSONArray;
import org.json.JSONObject;

public class ck implements cb, IPutIntoJson<JSONArray> {
    private final JSONObject a;
    private final JSONArray b;

    public ck(JSONObject jSONObject) {
        this.a = jSONObject;
        JSONArray jSONArray = new JSONArray();
        this.b = jSONArray;
        jSONArray.put(this.a);
    }

    public JSONObject a() {
        return this.a;
    }

    /* renamed from: c */
    public JSONArray forJsonPut() {
        return this.b;
    }

    public boolean b() {
        JSONObject jSONObject = this.a;
        if (jSONObject == null || jSONObject.length() == 0) {
            return true;
        }
        if (this.a.length() != 1 || !this.a.has(AccessToken.USER_ID_KEY)) {
            return false;
        }
        return true;
    }
}

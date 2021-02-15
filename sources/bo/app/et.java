package bo.app;

import com.medscape.android.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public final class et implements er {
    public boolean a(fk fkVar) {
        return fkVar instanceof fn;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", Constants.OMNITURE_MLINK_OPEN);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}

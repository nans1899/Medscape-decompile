package bo.app;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import org.json.JSONException;
import org.json.JSONObject;

public class ci implements IPutIntoJson<JSONObject> {
    private static final String a = AppboyLogger.getAppboyLogTag(ci.class);
    private final long b;

    public ci(long j) {
        this.b = j;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("config_time", this.b);
            return jSONObject;
        } catch (JSONException e) {
            AppboyLogger.d(a, "Caught exception creating config params json.", (Throwable) e);
            return null;
        }
    }
}

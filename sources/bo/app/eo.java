package bo.app;

import bolts.MeasurementEvent;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class eo implements er {
    private String a;

    public eo(JSONObject jSONObject) {
        this.a = jSONObject.getJSONObject("data").getString(MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY);
    }

    public boolean a(fk fkVar) {
        if (!(fkVar instanceof fj)) {
            return false;
        }
        fj fjVar = (fj) fkVar;
        return !StringUtils.isNullOrBlank(fjVar.a()) && fjVar.a().equals(this.a);
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "custom_event");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY, this.a);
            jSONObject.put("data", jSONObject2);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}

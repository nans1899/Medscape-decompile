package bo.app;

import bolts.MeasurementEvent;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class ep extends ez {
    private static final String b = AppboyLogger.getAppboyLogTag(ep.class);
    private String c;

    public ep(JSONObject jSONObject) {
        super(jSONObject);
        this.c = jSONObject.getJSONObject("data").getString(MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY);
    }

    public boolean a(fk fkVar) {
        if (!(fkVar instanceof fj)) {
            return false;
        }
        fj fjVar = (fj) fkVar;
        if (StringUtils.isNullOrBlank(fjVar.a()) || !fjVar.a().equals(this.c)) {
            return false;
        }
        return super.a(fkVar);
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        JSONObject a = super.forJsonPut();
        try {
            a.put("type", "custom_event_property");
            JSONObject jSONObject = a.getJSONObject("data");
            jSONObject.put(MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY, this.c);
            a.put("data", jSONObject);
        } catch (JSONException e) {
            AppboyLogger.e(b, "Caught exception creating CustomEventWithPropertiesTriggerCondition Json.", e);
        }
        return a;
    }
}

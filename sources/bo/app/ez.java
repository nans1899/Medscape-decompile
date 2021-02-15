package bo.app;

import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ez implements er {
    private static final String b = AppboyLogger.getAppboyLogTag(ez.class);
    fa a;

    public ez(JSONObject jSONObject) {
        JSONArray jSONArray = jSONObject.getJSONObject("data").getJSONArray("property_filters");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONArray jSONArray2 = jSONArray.getJSONArray(i);
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                arrayList2.add(new eu(jSONArray2.getJSONObject(i2)));
            }
            arrayList.add(new fc(arrayList2));
        }
        this.a = new fa(arrayList);
    }

    public boolean a(fk fkVar) {
        return this.a.a(fkVar);
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("property_filters", this.a.a());
            jSONObject.put("data", jSONObject2);
        } catch (JSONException e) {
            AppboyLogger.e(b, "Caught exception creating Json.", e);
        }
        return jSONObject;
    }
}

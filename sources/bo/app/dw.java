package bo.app;

import com.appboy.models.AppboyGeofence;
import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class dw {
    private static final String a = AppboyLogger.getAppboyLogTag(dw.class);

    public static List<AppboyGeofence> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject == null) {
                try {
                    AppboyLogger.w(a, "Received null or blank geofence Json. Not parsing.");
                } catch (JSONException e) {
                    String str = a;
                    AppboyLogger.w(str, "Failed to deserialize geofence Json due to JSONException: " + optJSONObject, e);
                } catch (Exception e2) {
                    String str2 = a;
                    AppboyLogger.e(str2, "Failed to deserialize geofence Json:" + optJSONObject, e2);
                }
            } else {
                arrayList.add(new AppboyGeofence(optJSONObject));
            }
        }
        return arrayList;
    }
}

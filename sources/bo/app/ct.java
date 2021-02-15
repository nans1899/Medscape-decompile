package bo.app;

import android.net.Uri;
import com.appboy.support.AppboyLogger;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class ct extends co {
    private static final String b = AppboyLogger.getAppboyLogTag(ct.class);
    private final bz c;

    public boolean h() {
        return false;
    }

    public ct(String str, ca caVar) {
        super(Uri.parse(str + "geofence/request"), (Map<String, String>) null);
        this.c = cf.a(caVar);
    }

    public y i() {
        return y.POST;
    }

    public void a(ad adVar, cl clVar) {
        AppboyLogger.d(b, "GeofenceRefreshRequest executed successfully.");
    }

    public JSONObject g() {
        JSONObject g = super.g();
        if (g == null) {
            return null;
        }
        try {
            if (this.c != null) {
                g.put("location_event", this.c.forJsonPut());
            }
            return g;
        } catch (JSONException e) {
            AppboyLogger.w(b, "Experienced JSONException while creating geofence refresh request. Returning null.", e);
            return null;
        }
    }
}

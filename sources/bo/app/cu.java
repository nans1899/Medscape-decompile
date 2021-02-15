package bo.app;

import android.net.Uri;
import com.appboy.support.AppboyLogger;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class cu extends co {
    private static final String b = AppboyLogger.getAppboyLogTag(cu.class);
    private final bz c;

    public boolean h() {
        return false;
    }

    public cu(String str, bz bzVar) {
        super(Uri.parse(str + "geofence/report"), (Map<String, String>) null);
        this.c = bzVar;
    }

    public y i() {
        return y.POST;
    }

    public void a(ad adVar, cl clVar) {
        AppboyLogger.d(b, "GeofenceReportRequest executed successfully.");
    }

    public JSONObject g() {
        JSONObject g = super.g();
        if (g == null) {
            return null;
        }
        try {
            if (this.c != null) {
                g.put("geofence_event", this.c.forJsonPut());
            }
            return g;
        } catch (JSONException e) {
            AppboyLogger.w(b, "Experienced JSONException while creating geofence report request. Returning null.", e);
            return null;
        }
    }
}

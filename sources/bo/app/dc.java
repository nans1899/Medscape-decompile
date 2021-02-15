package bo.app;

import android.net.Uri;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.facebook.AccessToken;
import com.medscape.android.cache.Cache;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dc extends co {
    private static final String b = AppboyLogger.getAppboyLogTag(dc.class);
    private final long c;
    private final List<String> d;
    private final String e;

    public void a(ad adVar, cl clVar) {
    }

    public dc(String str, List<String> list, long j, String str2) {
        super(Uri.parse(str + "data"), (Map<String, String>) null);
        this.c = j;
        this.d = list;
        this.e = str2;
    }

    public y i() {
        return y.POST;
    }

    public boolean h() {
        return this.d.isEmpty() && super.h();
    }

    public JSONObject g() {
        JSONObject g = super.g();
        if (g == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Cache.Caches.TIME, this.c);
            if (!StringUtils.isNullOrBlank(this.e)) {
                jSONObject.put(AccessToken.USER_ID_KEY, this.e);
            }
            if (!this.d.isEmpty()) {
                jSONObject.put("device_logs", new JSONArray(this.d));
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            g.put("test_user_data", jSONArray);
            return g;
        } catch (JSONException e2) {
            AppboyLogger.e(b, "Experienced JSONException while retrieving parameters. Returning null.", e2);
            return null;
        }
    }
}

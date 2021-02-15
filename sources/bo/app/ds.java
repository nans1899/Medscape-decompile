package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ds implements dn {
    private static final String a = AppboyLogger.getAppboyLogTag(ds.class);
    private final SharedPreferences b;

    public ds(Context context, String str, String str2) {
        this.b = context.getSharedPreferences("com.appboy.storage.session_storage" + StringUtils.getCacheFileSuffix(context, str, str2), 0);
    }

    public void a(cc ccVar) {
        String cdVar = ccVar.a().toString();
        JSONObject g = ccVar.forJsonPut();
        SharedPreferences.Editor edit = this.b.edit();
        a(g);
        edit.putString(cdVar, g.toString());
        if (!ccVar.d()) {
            edit.putString("current_open_session", cdVar);
        } else if (this.b.getString("current_open_session", "").equals(cdVar)) {
            edit.remove("current_open_session");
        }
        edit.apply();
    }

    public cc a() {
        JSONObject jSONObject;
        String str;
        if (!this.b.contains("current_open_session")) {
            AppboyLogger.d(a, "No stored open session in storage.");
            return null;
        }
        try {
            str = this.b.getString("current_open_session", "");
            try {
                jSONObject = new JSONObject(this.b.getString(str, ""));
            } catch (JSONException e) {
                e = e;
                jSONObject = null;
                AppboyLogger.e(a, "Could not create new mutable session for open session with id: " + str + " and json data: " + jSONObject, e);
                return null;
            }
            try {
                return new cc(jSONObject);
            } catch (JSONException e2) {
                e = e2;
                AppboyLogger.e(a, "Could not create new mutable session for open session with id: " + str + " and json data: " + jSONObject, e);
                return null;
            }
        } catch (JSONException e3) {
            e = e3;
            str = null;
            jSONObject = null;
            AppboyLogger.e(a, "Could not create new mutable session for open session with id: " + str + " and json data: " + jSONObject, e);
            return null;
        }
    }

    public void b(cc ccVar) {
        String string = this.b.getString("current_open_session", (String) null);
        String cdVar = ccVar.a().toString();
        SharedPreferences.Editor edit = this.b.edit();
        edit.remove(cdVar);
        if (cdVar.equals(string)) {
            edit.remove("current_open_session");
        }
        edit.apply();
    }

    private void a(JSONObject jSONObject) {
        if (!jSONObject.has("end_time")) {
            try {
                jSONObject.put("end_time", du.b());
            } catch (JSONException unused) {
                AppboyLogger.e(a, "Failed to set end time to now for session json data");
            }
        }
    }
}

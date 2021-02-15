package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dh extends dd<ch> {
    private static final String b = AppboyLogger.getAppboyLogTag(dh.class);
    final SharedPreferences a;
    private final AppboyConfigurationProvider c;
    private ch d;

    public dh(Context context) {
        this(context, (String) null, (String) null);
    }

    public dh(Context context, String str, String str2) {
        this.d = null;
        this.a = context.getSharedPreferences("com.appboy.storage.device_cache.v3" + StringUtils.getCacheFileSuffix(context, str, str2), 0);
        this.c = new AppboyConfigurationProvider(context);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public ch a() {
        JSONObject a2 = this.d.forJsonPut();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject = new JSONObject(this.a.getString("cached_device", "{}"));
        } catch (JSONException e) {
            AppboyLogger.e(b, "Caught exception confirming and unlocking Json objects.", e);
        }
        JSONObject jSONObject2 = new JSONObject();
        Iterator<String> keys = a2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object opt = a2.opt(next);
            Object opt2 = jSONObject.opt(next);
            if ((opt instanceof JSONObject) || (opt instanceof JSONArray)) {
                if (opt2 != null) {
                    try {
                        if (!gf.a(String.valueOf(opt), String.valueOf(opt2), gg.NON_EXTENSIBLE).b()) {
                        }
                    } catch (JSONException e2) {
                        AppboyLogger.d(b, "Caught json exception creating dirty outbound device on a jsonObject value. Returning the whole device.", (Throwable) e2);
                        return this.d;
                    }
                }
                jSONObject2.put(next, opt);
            } else if (!opt.equals(opt2)) {
                try {
                    jSONObject2.put(next, opt);
                } catch (JSONException e3) {
                    AppboyLogger.e(b, "Caught json exception creating dirty outbound device. Returning the whole device.", e3);
                    return this.d;
                }
            }
        }
        return ch.a(this.c, jSONObject2);
    }

    public void a(ch chVar) {
        this.d = chVar;
    }

    /* access modifiers changed from: package-private */
    public void a(ch chVar, boolean z) {
        if (z && chVar != null) {
            try {
                JSONObject jSONObject = new JSONObject(this.a.getString("cached_device", "{}"));
                JSONObject a2 = chVar.forJsonPut();
                SharedPreferences.Editor edit = this.a.edit();
                edit.putString("cached_device", ed.a(jSONObject, a2).toString());
                edit.apply();
            } catch (JSONException e) {
                AppboyLogger.d(b, "Caught exception confirming and unlocking device cache.", (Throwable) e);
            }
        }
    }
}

package bo.app;

import android.content.Context;
import com.appboy.models.IInAppMessage;
import com.appboy.models.IInAppMessageHtml;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class el extends en implements ek {
    private static final String a = AppboyLogger.getAppboyLogTag(el.class);
    private IInAppMessage b;
    private bq c;
    private String d;

    public el(JSONObject jSONObject, bq bqVar) {
        super(jSONObject);
        String str = a;
        AppboyLogger.d(str, "Parsing in-app message triggered action with JSON: " + ed.a(jSONObject));
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        if (jSONObject2 != null) {
            this.c = bqVar;
            this.b = ec.a(jSONObject2, bqVar);
            return;
        }
        AppboyLogger.w(a, "InAppMessageTriggeredAction Json did not contain in-app message.");
    }

    public ga d() {
        if (StringUtils.isNullOrBlank(this.b.getRemoteAssetPathForPrefetch())) {
            return null;
        }
        if (this.b instanceof IInAppMessageHtml) {
            return new ga(fi.ZIP, this.b.getRemoteAssetPathForPrefetch());
        }
        return new ga(fi.IMAGE, this.b.getRemoteAssetPathForPrefetch());
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(Context context, ad adVar, fk fkVar, long j) {
        try {
            String str = a;
            AppboyLogger.d(str, "Attempting to publish in-app message after delay of " + c().d() + " seconds.");
            if (!StringUtils.isNullOrBlank(this.d)) {
                this.b.setLocalAssetPathForPrefetch(this.d);
            }
            this.b.setExpirationTimestamp(j);
            adVar.a(new ak(this, this.b, this.c.e()), ak.class);
        } catch (Exception e) {
            AppboyLogger.w(a, "Caught exception while performing triggered action.", e);
        }
    }

    /* renamed from: e */
    public JSONObject forJsonPut() {
        try {
            JSONObject e = super.forJsonPut();
            e.put("data", this.b.forJsonPut());
            e.put("type", "inapp");
            return e;
        } catch (JSONException unused) {
            return null;
        }
    }
}

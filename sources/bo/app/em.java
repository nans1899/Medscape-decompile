package bo.app;

import android.content.Context;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class em extends en implements ek {
    private static final String a = AppboyLogger.getAppboyLogTag(em.class);
    private bq b;
    private String c;
    private String d;
    private String e;
    private String f;
    private long g = -1;

    public em(JSONObject jSONObject, bq bqVar) {
        super(jSONObject);
        String str = a;
        AppboyLogger.d(str, "Parsing templated triggered action with JSON: " + ed.a(jSONObject));
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        this.c = jSONObject2.getString("trigger_id");
        JSONArray optJSONArray = jSONObject2.optJSONArray("prefetch_image_urls");
        if (optJSONArray != null) {
            this.d = optJSONArray.getString(0);
        }
        JSONArray optJSONArray2 = jSONObject2.optJSONArray("prefetch_zip_urls");
        if (optJSONArray2 != null) {
            this.e = optJSONArray2.getString(0);
        }
        this.b = bqVar;
    }

    public ga d() {
        if (!StringUtils.isNullOrBlank(this.d)) {
            return new ga(fi.IMAGE, this.d);
        }
        if (!StringUtils.isNullOrBlank(this.e)) {
            return new ga(fi.ZIP, this.e);
        }
        return null;
    }

    public void a(String str) {
        this.f = str;
    }

    public void a(Context context, ad adVar, fk fkVar, long j) {
        if (this.b != null) {
            this.g = j;
            String str = a;
            AppboyLogger.d(str, "Posting templating request after delay of " + c().d() + " seconds.");
            this.b.a(this, fkVar);
        }
    }

    public long f() {
        return this.g;
    }

    public String g() {
        return this.c;
    }

    public String h() {
        return this.f;
    }

    /* renamed from: e */
    public JSONObject forJsonPut() {
        try {
            JSONObject e2 = super.forJsonPut();
            e2.put("type", "templated_iam");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("trigger_id", this.c);
            JSONArray jSONArray = new JSONArray();
            if (!StringUtils.isNullOrBlank(this.d)) {
                jSONArray.put(this.d);
                jSONObject.put("prefetch_image_urls", jSONArray);
            }
            JSONArray jSONArray2 = new JSONArray();
            if (!StringUtils.isNullOrBlank(this.e)) {
                jSONArray2.put(this.e);
                jSONObject.put("prefetch_zip_urls", jSONArray2);
            }
            e2.put("data", jSONObject);
            return e2;
        } catch (JSONException unused) {
            return null;
        }
    }
}

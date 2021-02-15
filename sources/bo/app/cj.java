package bo.app;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.StringUtils;
import com.facebook.AccessToken;
import net.media.android.bidder.base.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class cj implements cb, IPutIntoJson<JSONObject> {
    private final String a;
    private final Boolean b;
    private final Boolean c;
    private final ci d;

    private cj(String str, Boolean bool, Boolean bool2, ci ciVar) {
        this.a = str;
        this.b = bool;
        this.c = bool2;
        this.d = ciVar;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!StringUtils.isNullOrEmpty(this.a)) {
                jSONObject.put(AccessToken.USER_ID_KEY, this.a);
            }
            if (this.b != null) {
                jSONObject.put("feed", this.b);
            }
            if (this.c != null) {
                jSONObject.put("triggers", this.c);
            }
            if (this.d != null) {
                jSONObject.put(Constants.CONFIG_FILE_NAME, this.d.forJsonPut());
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public boolean c() {
        return this.d != null;
    }

    public boolean d() {
        return this.c != null;
    }

    public boolean e() {
        return this.b != null;
    }

    public boolean f() {
        return !StringUtils.isNullOrEmpty(this.a);
    }

    public boolean b() {
        JSONObject a2 = forJsonPut();
        if (a2.length() == 0) {
            return true;
        }
        if (a2.length() == 1) {
            return a2.has(AccessToken.USER_ID_KEY);
        }
        return false;
    }

    public static class a {
        private String a;
        private Boolean b;
        private Boolean c;
        private ci d;

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a a() {
            this.b = true;
            return this;
        }

        public a b() {
            this.c = true;
            return this;
        }

        public a a(ci ciVar) {
            this.d = ciVar;
            return this;
        }

        public cj c() {
            return new cj(this.a, this.b, this.c, this.d);
        }
    }
}

package bo.app;

import android.net.Uri;
import bo.app.cj;
import com.appboy.enums.inappmessage.InAppMessageFailureType;
import com.appboy.models.response.ResponseError;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class db extends co {
    private static final String b = AppboyLogger.getAppboyLogTag(db.class);
    private final String c;
    private final long d;
    private final String e;
    private final fk f;
    private final ek g;
    private final cj h;
    private final bq i;

    public boolean h() {
        return false;
    }

    public db(String str, em emVar, fk fkVar, bq bqVar, String str2) {
        super(Uri.parse(str + MessengerShareContentUtility.ATTACHMENT_TEMPLATE_TYPE), (Map<String, String>) null);
        this.c = emVar.g();
        this.d = emVar.f();
        this.e = emVar.h();
        this.f = fkVar;
        this.h = new cj.a().a(str2).c();
        this.i = bqVar;
        this.g = emVar;
    }

    public y i() {
        return y.POST;
    }

    public void a(ad adVar, cl clVar) {
        if (clVar == null || !clVar.b()) {
            m();
        } else if (!StringUtils.isNullOrBlank(this.e)) {
            clVar.i().setLocalAssetPathForPrefetch(this.e);
        }
    }

    public void a(ad adVar, ResponseError responseError) {
        super.a(adVar, responseError);
        m();
    }

    public long k() {
        return this.d;
    }

    public JSONObject g() {
        JSONObject g2 = super.g();
        if (g2 == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trigger_id", this.c);
            jSONObject.put("trigger_event_type", this.f.b());
            if (this.f.e() != null) {
                jSONObject.put("data", this.f.e().forJsonPut());
            }
            g2.put(MessengerShareContentUtility.ATTACHMENT_TEMPLATE_TYPE, jSONObject);
            if (this.h.f()) {
                g2.put("respond_with", this.h.forJsonPut());
            }
            return g2;
        } catch (JSONException e2) {
            AppboyLogger.w(b, "Experienced JSONException while retrieving parameters. Returning null.", e2);
            return null;
        }
    }

    public ek l() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public void m() {
        AppboyLogger.i(b, "Template request failed. Attempting to log in-app message template request failure.");
        if (StringUtils.isNullOrBlank(this.c)) {
            AppboyLogger.d(b, "Trigger ID not found. Not logging in-app message template request failure.");
        } else if (this.i == null) {
            AppboyLogger.e(b, "Cannot log an in-app message template request failure because the IAppboyManager is null.");
        } else {
            try {
                this.i.a((bz) cf.a((String) null, (String) null, this.c, InAppMessageFailureType.TEMPLATE_REQUEST));
            } catch (JSONException e2) {
                this.i.a((Throwable) e2);
            }
        }
    }
}

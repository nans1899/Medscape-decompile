package bo.app;

import android.net.Uri;
import bo.app.cj;
import com.appboy.support.AppboyLogger;
import com.facebook.internal.ServerProtocol;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class cr extends co {
    private static final String b = AppboyLogger.getAppboyLogTag(cr.class);
    private final cj c;

    public void a(ad adVar, cl clVar) {
    }

    public cr(String str) {
        this(str, new cj.a().c());
    }

    public cr(String str, cj cjVar) {
        super(Uri.parse(str + "data"), (Map<String, String>) null);
        this.c = cjVar;
        a(cjVar);
    }

    public y i() {
        return y.POST;
    }

    public boolean h() {
        return this.c.b() && super.h();
    }

    public void a(Map<String, String> map) {
        super.a(map);
        if (!this.c.b()) {
            map.put("X-Braze-DataRequest", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            if (this.c.e()) {
                map.put("X-Braze-FeedRequest", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            }
            if (this.c.d()) {
                map.put("X-Braze-TriggersRequest", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            }
        }
    }

    public JSONObject g() {
        JSONObject g = super.g();
        if (g == null) {
            return null;
        }
        try {
            if (!this.c.b()) {
                g.put("respond_with", this.c.forJsonPut());
            }
            return g;
        } catch (JSONException e) {
            AppboyLogger.w(b, "Experienced JSONException while retrieving parameters. Returning null.", e);
            return null;
        }
    }
}

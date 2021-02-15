package bo.app;

import android.net.Uri;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.facebook.AccessToken;
import com.facebook.internal.ServerProtocol;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class cq extends co {
    private static final String b = AppboyLogger.getAppboyLogTag(cq.class);
    private final long c;
    private final long d;
    private final String e;

    public boolean h() {
        return false;
    }

    public cq(String str, long j, long j2, String str2) {
        super(Uri.parse(str + "content_cards/sync"), (Map<String, String>) null);
        this.c = j;
        this.d = j2;
        this.e = str2;
    }

    public y i() {
        return y.POST;
    }

    public void a(ad adVar, cl clVar) {
        AppboyLogger.d(b, "ContentCardsSyncRequest executed successfully.");
    }

    public void a(Map<String, String> map) {
        super.a(map);
        map.put("X-Braze-DataRequest", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        map.put("X-Braze-ContentCardsRequest", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
    }

    public JSONObject g() {
        JSONObject g = super.g();
        if (g == null) {
            return null;
        }
        try {
            g.put("last_full_sync_at", this.d);
            g.put("last_card_updated_at", this.c);
            if (!StringUtils.isNullOrBlank(this.e)) {
                g.put(AccessToken.USER_ID_KEY, this.e);
            }
            return g;
        } catch (JSONException e2) {
            AppboyLogger.w(b, "Experienced JSONException while creating Content Cards request. Returning null.", e2);
            return null;
        }
    }
}

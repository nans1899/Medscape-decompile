package bo.app;

import android.net.Uri;
import com.appboy.events.SubmitFeedbackFailed;
import com.appboy.events.SubmitFeedbackSucceeded;
import com.appboy.models.outgoing.Feedback;
import com.appboy.models.response.ResponseError;
import com.appboy.support.AppboyLogger;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class cs extends co {
    private static final String b = AppboyLogger.getAppboyLogTag(cs.class);
    private final Feedback c;

    public boolean h() {
        return false;
    }

    public cs(String str, Feedback feedback) {
        super(Uri.parse(str + "data"), (Map<String, String>) null);
        this.c = feedback;
    }

    public y i() {
        return y.POST;
    }

    public void a(ad adVar, cl clVar) {
        adVar.a(new SubmitFeedbackSucceeded(this.c), SubmitFeedbackSucceeded.class);
    }

    public void a(ad adVar, ResponseError responseError) {
        super.a(adVar, responseError);
        adVar.a(new SubmitFeedbackFailed(this.c, responseError), SubmitFeedbackFailed.class);
    }

    public JSONObject g() {
        JSONObject g = super.g();
        if (g == null) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(this.c.forJsonPut());
            g.put(Constants.FEEDBACK_VERSION, jSONArray);
            return g;
        } catch (JSONException e) {
            AppboyLogger.w(b, "Experienced JSONException while retrieving parameters. Returning null.", e);
            return null;
        }
    }
}

package com.appboy.models;

import bo.app.bq;
import bo.app.bz;
import bo.app.cf;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class InAppMessageControl extends InAppMessageBase {
    private boolean j = false;

    public InAppMessageControl(JSONObject jSONObject, bq bqVar) {
        super(jSONObject, bqVar);
    }

    public boolean logImpression() {
        if (this.j) {
            AppboyLogger.i(a, "Control impression already logged for this in-app message. Ignoring.");
            return false;
        } else if (StringUtils.isNullOrEmpty(this.d)) {
            AppboyLogger.w(a, "Trigger Id not found. Not logging in-app message control impression.");
            return false;
        } else if (this.i == null) {
            AppboyLogger.e(a, "Cannot log an in-app message control impression because the AppboyManager is null.");
            return false;
        } else {
            try {
                AppboyLogger.v(a, "Logging control in-app message impression event");
                this.i.a((bz) cf.a(this.b, this.c, this.d));
                this.j = true;
                return true;
            } catch (JSONException e) {
                this.i.a((Throwable) e);
                return false;
            }
        }
    }
}

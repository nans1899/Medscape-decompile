package com.webmd.webmdrx.parsers;

import com.facebook.appevents.AppEventsConstants;
import org.json.JSONObject;

public class MemberIdResultsParser {
    public static String parse(JSONObject jSONObject) {
        if (jSONObject != null) {
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("data");
            String optString3 = jSONObject.optString("status");
            if (!optString.equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO) || optString2.isEmpty() || !optString3.equalsIgnoreCase("ok")) {
                return null;
            }
            return optString2;
        }
        return null;
    }
}

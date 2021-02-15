package io.branch.referral;

import android.text.TextUtils;
import com.crashlytics.android.answers.shim.AnswersOptionalLogger;
import com.crashlytics.android.answers.shim.KitEvent;
import io.branch.referral.Defines;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ExtendedAnswerProvider {
    private static final String CTRL_PARAM_NOTATION = "~";
    private static final String EXTRA_PARAM_NOTATION = "+";
    private static final String INNER_PARAM_NOTATION = ".";
    public static final String KIT_EVENT_INSTALL = "Branch Install";
    public static final String KIT_EVENT_OPEN = "Branch Open";
    public static final String KIT_EVENT_SHARE = "Branch Share";

    ExtendedAnswerProvider() {
    }

    public void provideData(String str, JSONObject jSONObject, String str2) {
        try {
            KitEvent kitEvent = new KitEvent(str);
            if (jSONObject != null) {
                addJsonObjectToKitEvent(kitEvent, jSONObject, "");
                kitEvent.putAttribute(Defines.Jsonkey.BranchIdentity.getKey(), str2);
                AnswersOptionalLogger.get().logKitEvent(kitEvent);
            }
        } catch (Throwable unused) {
        }
    }

    private void addJsonObjectToKitEvent(KitEvent kitEvent, JSONObject jSONObject, String str) throws JSONException {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (!next.startsWith(EXTRA_PARAM_NOTATION)) {
                if (obj instanceof JSONObject) {
                    addJsonObjectToKitEvent(kitEvent, (JSONObject) obj, str + next + INNER_PARAM_NOTATION);
                } else if (obj instanceof JSONArray) {
                    addJsonArrayToKitEvent(kitEvent, (JSONArray) obj, next + INNER_PARAM_NOTATION);
                } else {
                    addBranchAttributes(kitEvent, str, next, jSONObject.getString(next));
                }
            }
        }
    }

    private void addJsonArrayToKitEvent(KitEvent kitEvent, JSONArray jSONArray, String str) throws JSONException {
        for (int i = 0; i < jSONArray.length(); i++) {
            addBranchAttributes(kitEvent, str, CTRL_PARAM_NOTATION + Integer.toString(i), jSONArray.getString(i));
        }
    }

    private void addBranchAttributes(KitEvent kitEvent, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        if (str2.startsWith(CTRL_PARAM_NOTATION)) {
            kitEvent.putAttribute(str.replaceFirst(CTRL_PARAM_NOTATION, "") + str2.replaceFirst(CTRL_PARAM_NOTATION, ""), str3);
            return;
        }
        if (str2.equals("$" + Defines.Jsonkey.IdentityID.getKey())) {
            kitEvent.putAttribute(Defines.Jsonkey.ReferringBranchIdentity.getKey(), str3);
        }
    }
}

package com.medscape.android.consult.managers;

import android.content.Context;
import android.net.Uri;
import com.medscape.android.Settings;
import com.medscape.android.consult.ConsultUrls;
import com.medscape.android.consult.util.ConsultConstants;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdhttpclient.VolleyRestClient;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ConsultMedStudentFlagsApiManager {
    private static final String MED_STUDENTS_NOTIFICATION = "consultMedStudentNotification";
    private static final String SHOW_MED_STUDENTS_POSTS = "consultMedStudentShow";

    public static void saveMedStudentFlag(Context context, JSONObject jSONObject) {
        if (context != null && UserProfileProvider.INSTANCE.getUserProfile(context) != null) {
            String registeredId = UserProfileProvider.INSTANCE.getUserProfile(context).getRegisteredId();
            saveFlagToLocalUserProfile(jSONObject, context);
            String encode = Uri.encode(registeredId + "");
            HashMap hashMap = new HashMap();
            hashMap.put("isEncrypted", "false");
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            String updateUserAttributesUrl = ConsultUrls.getUpdateUserAttributesUrl(context);
            VolleyRestClient instance = VolleyRestClient.getInstance(context);
            instance.post(updateUserAttributesUrl + encode, jSONObject, (Map<String, String>) hashMap, (ICallbackEvent) new ICallbackEvent() {
                public void onCompleted(Object obj) {
                }

                public void onError(Object obj) {
                }
            });
        }
    }

    public static void saveFlagToLocalUserProfile(JSONObject jSONObject, Context context) {
        if (jSONObject != null && jSONObject.length() > 0 && context != null) {
            String optString = jSONObject.optString("consultMedStudentShow");
            String optString2 = jSONObject.optString("consultMedStudentNotification");
            String optString3 = jSONObject.optString(ConsultConstants.GLOBAL_POSTS_SHOW);
            if (optString != null && !optString.isEmpty()) {
                Settings.singleton(context).saveSetting("consultMedStudentShow", optString);
            }
            if (optString2 != null && !optString2.isEmpty()) {
                Settings.singleton(context).saveSetting("consultMedStudentNotification", optString2);
            }
            if (optString3 != null && !optString3.isEmpty()) {
                Settings.singleton(context).saveSetting(ConsultConstants.GLOBAL_POSTS_SHOW, optString3);
            }
        }
    }
}

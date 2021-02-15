package com.medscape.android.consult.parsers;

import com.medscape.android.consult.models.NotificationPreference;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationPreferencesParser {
    public static ArrayList<NotificationPreference> getNotificationsFromCommunityResponse(JSONArray jSONArray) {
        return getPushPreferencesFromJSON(jSONArray);
    }

    public static boolean getUpdatedStatusFromCommunityResponse(JSONObject jSONObject) {
        return jSONObject != null && jSONObject.optInt("StatusCode") == 200;
    }

    public static JSONArray getPreferencesJSONArrayfromObj(ArrayList<NotificationPreference> arrayList) {
        JSONArray jSONArray = new JSONArray();
        if (arrayList != null) {
            try {
                Iterator<NotificationPreference> it = arrayList.iterator();
                while (it.hasNext()) {
                    NotificationPreference next = it.next();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("Enabled", next.mEnabled);
                    jSONObject.put(NotificationPreference.KEY_NOTIFICATION_TYPE, next.mType);
                    jSONObject.put(NotificationPreference.KEY_DISTRIBUTION_TYPE, next.mDistribution);
                    jSONArray.put(jSONObject);
                }
            } catch (JSONException unused) {
            }
        }
        return jSONArray;
    }

    static ArrayList getPreferencesFromJSON(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                NotificationPreference notificationPreference = new NotificationPreference();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                notificationPreference.mDistribution = jSONObject.optInt(NotificationPreference.KEY_DISTRIBUTION_TYPE);
                notificationPreference.mType = jSONObject.optInt(NotificationPreference.KEY_NOTIFICATION_TYPE);
                notificationPreference.mEnabled = jSONObject.optBoolean("Enabled");
                arrayList.add(notificationPreference);
            }
        }
        return arrayList;
    }

    static ArrayList getPushPreferencesFromJSON(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                NotificationPreference notificationPreference = new NotificationPreference();
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    notificationPreference.mDistribution = optJSONObject.optInt(NotificationPreference.KEY_DISTRIBUTION_TYPE);
                    notificationPreference.mType = optJSONObject.optInt(NotificationPreference.KEY_NOTIFICATION_TYPE);
                    notificationPreference.mEnabled = optJSONObject.optBoolean("Enabled");
                    if (notificationPreference.mDistribution == 2) {
                        arrayList.add(notificationPreference);
                    }
                }
            }
        }
        return arrayList;
    }

    static ArrayList getEmailPreferencesFromJSON(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                NotificationPreference notificationPreference = new NotificationPreference();
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    notificationPreference.mDistribution = optJSONObject.optInt(NotificationPreference.KEY_DISTRIBUTION_TYPE);
                    notificationPreference.mType = optJSONObject.optInt(NotificationPreference.KEY_NOTIFICATION_TYPE);
                    notificationPreference.mEnabled = optJSONObject.optBoolean("Enabled");
                    if (notificationPreference.mDistribution == 1) {
                        arrayList.add(notificationPreference);
                    }
                }
            }
        }
        return arrayList;
    }
}

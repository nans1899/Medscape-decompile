package com.medscape.android.CP;

import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.Settings;
import com.medscape.android.util.JSONUtils;
import com.wbmd.ads.model.AdContentData;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012&\u0010\b\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tj\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u001a\u001a\u0004\u0018\u00010\u0003J\u0006\u0010\u001b\u001a\u00020\u001cJ\"\u0010\u001d\u001a\u00020\u001c2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t2\u0006\u0010\u001f\u001a\u00020\u001cR\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR1\u0010\b\u001a\"\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tj\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000f¨\u0006 "}, d2 = {"Lcom/medscape/android/CP/CPEventJsonCreator;", "", "appName", "", "activityName", "activityId", "url", "cpJsonToSend", "screenMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/HashMap;)V", "getActivityId", "()Ljava/lang/String;", "setActivityId", "(Ljava/lang/String;)V", "getActivityName", "setActivityName", "getAppName", "setAppName", "getCpJsonToSend", "setCpJsonToSend", "getScreenMap", "()Ljava/util/HashMap;", "getUrl", "setUrl", "createCpJson", "createDefaultJson", "Lorg/json/JSONObject;", "updateJsonWithParams", "adParams", "cpJson", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CPEventJsonCreator.kt */
public final class CPEventJsonCreator {
    private String activityId;
    private String activityName;
    private String appName;
    private String cpJsonToSend;
    private final HashMap<String, String> screenMap;
    private String url;

    public CPEventJsonCreator(String str, String str2, String str3, String str4, String str5, HashMap<String, String> hashMap) {
        Intrinsics.checkNotNullParameter(str, "appName");
        Intrinsics.checkNotNullParameter(str2, "activityName");
        Intrinsics.checkNotNullParameter(str3, "activityId");
        Intrinsics.checkNotNullParameter(str4, "url");
        this.appName = str;
        this.activityName = str2;
        this.activityId = str3;
        this.url = str4;
        this.cpJsonToSend = str5;
        this.screenMap = hashMap;
    }

    public final String getActivityId() {
        return this.activityId;
    }

    public final String getActivityName() {
        return this.activityName;
    }

    public final String getAppName() {
        return this.appName;
    }

    public final void setActivityId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.activityId = str;
    }

    public final void setActivityName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.activityName = str;
    }

    public final void setAppName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.appName = str;
    }

    public final String getCpJsonToSend() {
        return this.cpJsonToSend;
    }

    public final HashMap<String, String> getScreenMap() {
        return this.screenMap;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setCpJsonToSend(String str) {
        this.cpJsonToSend = str;
    }

    public final void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    public final String createCpJson() {
        JSONObject createDefaultJson = createDefaultJson();
        HashMap<String, String> hashMap = this.screenMap;
        if (hashMap != null && hashMap.size() > 0) {
            createDefaultJson = updateJsonWithParams(this.screenMap, createDefaultJson);
        }
        CharSequence charSequence = this.cpJsonToSend;
        if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
            try {
                JSONObject jSONObject = new JSONObject(this.cpJsonToSend);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (next != null) {
                        String str = next;
                        if (StringsKt.equals(str, "impr", true)) {
                            createDefaultJson.put(str, jSONObject.getJSONArray(str));
                        } else {
                            createDefaultJson.put(str, jSONObject.getString(str));
                        }
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return createDefaultJson.toString();
    }

    public final JSONObject createDefaultJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(OmnitureConstants.OMNITURE_FILTER_DATE, System.currentTimeMillis());
            jSONObject.put("uid", Settings.singleton(MedscapeApplication.get()).getSetting(Constants.REGISTERED_ID, AppEventsConstants.EVENT_PARAM_VALUE_NO));
            jSONObject.put("url", this.url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final JSONObject updateJsonWithParams(HashMap<String, String> hashMap, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(hashMap, "adParams");
        Intrinsics.checkNotNullParameter(jSONObject, "cpJson");
        try {
            jSONObject.put("appname", this.appName);
            jSONObject.put("activityName", this.activityName);
            jSONObject.put("activityId", this.activityId);
            jSONObject.put("blockCode", JSONUtils.optJSONArrayFromDSV(hashMap.get("bc"), "_"));
            jSONObject.put("allSpecialties", JSONUtils.optJSONArrayFromDSV(hashMap.get(AdContentData.RELATED_SPECIALITY), ","));
            jSONObject.put("allConcepts", JSONUtils.optJSONArrayFromDSV(hashMap.get("ac"), ","));
            jSONObject.put("leadSpec", hashMap.get(AdContentData.LEAD_SPECIALITY));
            jSONObject.put("leadConcept", hashMap.get(AdContentData.LEAD_CONCEPT));
            jSONObject.put("contentGroup", hashMap.get("cg"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}

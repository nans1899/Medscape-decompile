package com.medscape.android.CP;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.CookieSyncManager;
import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.Settings;
import com.medscape.android.util.JSONUtils;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.wbmd.ads.model.AdContentData;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class FireCPEventWithAdsSegvarAsyncTask extends AsyncTask<HashMap<String, String>, Void, Void> {
    private String cpSource;
    private String mActivityId;
    private String mActivityName;
    private String mAdditionalJsonSting;
    private String mAppName;
    private String mCookie;

    public FireCPEventWithAdsSegvarAsyncTask(Context context, String str, String str2, String str3, String str4, String str5) {
        CookieSyncManager.createInstance(context);
        this.mCookie = Settings.singleton(context).getSetting(Constants.PREF_COOKIE_STRING, "");
        this.cpSource = str4;
        this.mActivityName = str2;
        this.mAppName = str;
        this.mActivityId = str3;
        this.mAdditionalJsonSting = str5;
    }

    /* access modifiers changed from: protected */
    @SafeVarargs
    public final Void doInBackground(HashMap<String, String>... hashMapArr) {
        JSONObject createDefaultJson = createDefaultJson();
        if (hashMapArr != null && hashMapArr.length > 0 && hashMapArr[0] != null && hashMapArr[0].size() > 0) {
            createDefaultJson = updateJsonWithParams(hashMapArr[0], createDefaultJson);
        }
        if (StringUtil.isNotEmpty(this.mAdditionalJsonSting)) {
            try {
                JSONObject jSONObject = new JSONObject(this.mAdditionalJsonSting);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (next.equalsIgnoreCase("impr")) {
                        createDefaultJson.put(next, jSONObject.getJSONArray(next));
                    } else {
                        createDefaultJson.put(next, jSONObject.getString(next));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String jSONObject2 = createDefaultJson.toString();
        Log.d("CP Json", jSONObject2);
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://api.medscape.com/cp/events.json?event=" + Uri.encode(jSONObject2)).openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Cookie", this.mCookie);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type", AbstractSpiCall.ACCEPT_JSON_VALUE);
            httpURLConnection.setRequestProperty("Accept", AbstractSpiCall.ACCEPT_JSON_VALUE);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setReadTimeout(Util.TIMEOUT);
            httpURLConnection.connect();
            httpURLConnection.getInputStream().close();
            httpURLConnection.disconnect();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private JSONObject updateJsonWithParams(HashMap<String, String> hashMap, JSONObject jSONObject) {
        try {
            jSONObject.put("appname", this.mAppName);
            jSONObject.put("activityName", this.mActivityName);
            jSONObject.put("activityId", this.mActivityId);
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

    private JSONObject createDefaultJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(OmnitureConstants.OMNITURE_FILTER_DATE, System.currentTimeMillis());
            jSONObject.put("uid", Settings.singleton(MedscapeApplication.get()).getSetting(Constants.REGISTERED_ID, AppEventsConstants.EVENT_PARAM_VALUE_NO));
            jSONObject.put("url", this.cpSource);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}

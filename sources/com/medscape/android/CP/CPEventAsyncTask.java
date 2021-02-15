package com.medscape.android.CP;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.CookieSyncManager;
import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.medscape.android.Constants;
import com.medscape.android.Settings;
import com.medscape.android.util.Util;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class CPEventAsyncTask extends AsyncTask<Void, Void, Void> {
    private final String CP_URL = "https://api.medscape.com/cp/events.json?event=";
    private String mActivityId;
    private String mActivityName;
    private String mAppName;
    private String mCPSource;
    private Context mContext;
    private String mCookie;
    private String mParId;
    private String mTacticId;

    public CPEventAsyncTask(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        CookieSyncManager.createInstance(context);
        this.mCookie = Settings.singleton(context).getSetting(Constants.PREF_COOKIE_STRING, "");
        this.mContext = context;
        this.mAppName = str;
        this.mActivityName = str3;
        this.mActivityId = str2;
        this.mParId = str4;
        this.mTacticId = str5;
        this.mCPSource = str6;
    }

    /* access modifiers changed from: protected */
    public Void doInBackground(Void... voidArr) {
        String json = getJSON();
        if (json != null && !json.equals("")) {
            Log.d("CP Json", json);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://api.medscape.com/cp/events.json?event=" + Uri.encode(json)).openConnection();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getJSON() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(OmnitureConstants.OMNITURE_FILTER_DATE, Long.valueOf(System.currentTimeMillis()).toString());
            jSONObject.put("appname", this.mAppName);
            jSONObject.put("activityName", this.mActivityName);
            jSONObject.put("uid", Settings.singleton(this.mContext).getSetting(Constants.REGISTERED_ID, AppEventsConstants.EVENT_PARAM_VALUE_NO));
            jSONObject.put("url", this.mCPSource);
            jSONObject.put("activityId", this.mActivityId);
            jSONObject.put("tacticId", this.mTacticId);
            jSONObject.put("parId", this.mParId);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}

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
import com.medscape.android.parser.model.Article;
import com.medscape.android.util.Util;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FireCPAsyncTask extends AsyncTask<Void, Void, Void> {
    private final String ACTIVITY_NAME = "headlineimpr";
    private final String APP_NAME = "alert";
    private final String CP_URL = "https://api.medscape.com/cp/events.json?event=";
    private List<Article> articles;
    private String cpSource;
    private Context mContext;
    private String mCookie;

    public FireCPAsyncTask(Context context, String str) {
        CookieSyncManager.createInstance(context);
        this.mCookie = Settings.singleton(context).getSetting(Constants.PREF_COOKIE_STRING, "");
        this.cpSource = str;
        this.mContext = context;
    }

    public void setArticleArray(List<Article> list) {
        this.articles = list;
    }

    /* access modifiers changed from: protected */
    public Void doInBackground(Void... voidArr) {
        String json = getJSON(this.articles, this.cpSource);
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

    public String getJSON(List<Article> list, String str) {
        boolean z;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(OmnitureConstants.OMNITURE_FILTER_DATE, System.currentTimeMillis());
            jSONObject.put("appname", "alert");
            jSONObject.put("activityName", "headlineimpr");
            jSONObject.put("uid", Settings.singleton(this.mContext).getSetting(Constants.REGISTERED_ID, AppEventsConstants.EVENT_PARAM_VALUE_NO));
            jSONObject.put("url", str);
            JSONArray jSONArray = new JSONArray();
            boolean z2 = false;
            for (Article next : list) {
                JSONObject jSONObject2 = new JSONObject();
                boolean z3 = true;
                if (next.mTcId == null || next.mTcId.trim().equals("")) {
                    z = false;
                } else {
                    jSONObject2.put("tcid", next.mTcId);
                    z2 = true;
                    z = true;
                }
                if (next.mActivityId == null || next.mActivityId.trim().equals("")) {
                    z3 = z;
                } else {
                    jSONObject2.put("activityId", next.mActivityId);
                    z2 = true;
                }
                if (z3) {
                    jSONArray.put(jSONObject2);
                }
            }
            if (!z2) {
                return "";
            }
            jSONObject.put("impr", jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}

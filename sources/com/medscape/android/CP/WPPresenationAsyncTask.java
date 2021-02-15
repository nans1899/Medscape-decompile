package com.medscape.android.CP;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.CookieSyncManager;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.medscape.android.Constants;
import com.medscape.android.Settings;
import com.medscape.android.util.Util;
import java.net.HttpURLConnection;
import java.net.URL;

public class WPPresenationAsyncTask extends AsyncTask<Void, Void, Void> {
    private final String ENDPOINT_URL = "http://wp.medscape.com/activity/viewpresentation";
    private String mActivityId;
    private String mActivityName;
    private Context mContext;
    private String mCookie;
    private String mParId;

    public WPPresenationAsyncTask(Context context, String str, String str2, String str3) {
        CookieSyncManager.createInstance(context);
        this.mCookie = Settings.singleton(context).getSetting(Constants.PREF_COOKIE_STRING, "");
        this.mContext = context;
        this.mActivityName = str2;
        this.mActivityId = str;
        this.mParId = str3;
    }

    /* access modifiers changed from: protected */
    public Void doInBackground(Void... voidArr) {
        String urlWithParams = getUrlWithParams();
        if (urlWithParams != null && !urlWithParams.equals("")) {
            Log.d("WP params", urlWithParams);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(urlWithParams).openConnection();
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

    public String getUrlWithParams() {
        return "http://wp.medscape.com/activity/viewpresentation" + "?action=" + Uri.encode(this.mActivityName) + "&activityId=" + Uri.encode(this.mActivityId) + "&parId=" + Uri.encode(this.mParId);
    }
}

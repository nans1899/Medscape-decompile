package com.medscape.android.ads;

import android.content.Context;
import android.os.AsyncTask;
import android.webkit.CookieSyncManager;
import com.medscape.android.Constants;
import com.medscape.android.Settings;
import com.medscape.android.util.Util;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdServerPingTask extends AsyncTask<String, String, Boolean> {
    private String TAG = "AdServerPingTask";
    private Context context;

    public AdServerPingTask(Context context2) {
        this.context = context2;
    }

    /* access modifiers changed from: protected */
    public Boolean doInBackground(String... strArr) {
        try {
            CookieSyncManager.createInstance(this.context);
            String setting = Settings.singleton(this.context).getSetting(Constants.PREF_COOKIE_STRING, "");
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
            httpURLConnection.setRequestProperty("Cookie", setting);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setReadTimeout(Util.TIMEOUT);
            httpURLConnection.connect();
            httpURLConnection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}

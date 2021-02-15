package com.medscape.android.task;

import android.content.Context;
import android.os.AsyncTask;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.Settings;
import com.medscape.android.util.Util;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import org.json.JSONObject;

public class SearchExternalTask extends AsyncTask<Void, Integer, JSONObject> {
    private String SEARCH_API_URL = "";
    String mCollection;
    SearchExternalCompleteListener mListener;
    int mPage;
    int mSize;
    String mTerm;

    public interface SearchExternalCompleteListener {
        void onNoInternet();

        void onSearchComplete(JSONObject jSONObject);
    }

    public SearchExternalTask(Context context, String str, String str2, int i, int i2, SearchExternalCompleteListener searchExternalCompleteListener) {
        this.mCollection = str;
        try {
            this.mTerm = URLEncoder.encode(str2, Charset.defaultCharset().name());
        } catch (Exception unused) {
            this.mTerm = str2;
        }
        this.mSize = i;
        this.mPage = i2;
        this.mListener = searchExternalCompleteListener;
        this.SEARCH_API_URL = Utilities.generateEnvironment(context, "https://api%s.medscape.com/") + "contentsearchservice/%s/search?size=%d&spellcheck=true&client=mscpsrch&q=%s&page=%d&out=id,title,clientUrl,contentGroup,origContentType,description,pubDisplay,publicationDate";
    }

    /* access modifiers changed from: protected */
    public JSONObject doInBackground(Void... voidArr) {
        JSONObject jSONObject = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(String.format(this.SEARCH_API_URL, new Object[]{this.mCollection, Integer.valueOf(this.mSize), this.mTerm, Integer.valueOf(this.mPage)})).openConnection();
            httpURLConnection.setReadTimeout(Util.TIMEOUT);
            httpURLConnection.setRequestProperty("Cookie", Settings.singleton(MedscapeApplication.get()).getSetting(Constants.PREF_COOKIE_STRING, ""));
            InputStream inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = inputStream.read();
                if (read != -1) {
                    byteArrayOutputStream.write(read);
                } else {
                    String str = new String(byteArrayOutputStream.toByteArray());
                    JSONObject jSONObject2 = new JSONObject(str.substring(str.indexOf("{"), str.lastIndexOf("}") + 1));
                    try {
                        byteArrayOutputStream.flush();
                        inputStream.close();
                        byteArrayOutputStream.close();
                        return jSONObject2;
                    } catch (Throwable th) {
                        th = th;
                        jSONObject = jSONObject2;
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            th.printStackTrace();
            return jSONObject;
        }
    }

    public void onPostExecute(JSONObject jSONObject) {
        try {
            if (this.mListener == null) {
                return;
            }
            if (Util.isOnline(MedscapeApplication.get()) || jSONObject != null) {
                this.mListener.onSearchComplete(jSONObject);
            } else {
                this.mListener.onNoInternet();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}

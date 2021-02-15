package com.medscape.android.reference.task;

import android.content.Context;
import android.os.AsyncTask;
import com.medscape.android.Constants;
import com.medscape.android.Settings;
import com.medscape.android.helper.FileHelper;
import com.medscape.android.reference.interfaces.ArticleDownloadListener;
import com.medscape.android.util.Util;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

public class ArticleDownloadTask extends AsyncTask<String, String, String> {
    private Context mContext;
    private ArticleDownloadListener mListener;

    public ArticleDownloadTask(Context context, ArticleDownloadListener articleDownloadListener) {
        this.mContext = context;
        this.mListener = articleDownloadListener;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(String... strArr) {
        try {
            String str = strArr[0];
            String str2 = strArr[1];
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
            httpURLConnection.setReadTimeout(Util.TIMEOUT);
            httpURLConnection.setRequestProperty("Cookie", Settings.singleton(this.mContext).getSetting(Constants.PREF_COOKIE_STRING, ""));
            String str3 = Constants.DIRECTORY_MAIN_CR;
            File file = new File(str3);
            if (!file.canWrite()) {
                file.mkdir();
            }
            String str4 = str2 + ".xml";
            InputStream inputStream = httpURLConnection.getInputStream();
            FileHelper.saveToFile(inputStream, file, str4);
            inputStream.close();
            return str3 + str4;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (SocketException e2) {
            e2.printStackTrace();
            return null;
        } catch (SocketTimeoutException e3) {
            e3.printStackTrace();
            return null;
        } catch (UnknownHostException e4) {
            e4.printStackTrace();
            return null;
        } catch (Exception e5) {
            e5.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        if (str != null) {
            this.mListener.articleDownloadComplete(str);
        } else {
            this.mListener.articlDownloadUnsuccessful();
        }
    }
}

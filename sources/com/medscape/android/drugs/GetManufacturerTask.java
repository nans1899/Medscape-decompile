package com.medscape.android.drugs;

import android.content.Context;
import android.os.AsyncTask;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.Constants;
import com.medscape.android.Settings;
import com.medscape.android.task.FetchPListTask;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.Util;
import com.wbmd.wbmdcommons.utils.Extensions;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

public class GetManufacturerTask extends AsyncTask<String, Double, String> {
    private String TAG = "GetManufacturerTask";
    private FetchPListTask.FetchPListListener getURLContentsListener;
    private Context mContext;
    private int size;

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
    }

    public GetManufacturerTask(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(String... strArr) {
        String str;
        String str2 = "";
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
            httpURLConnection.setReadTimeout(Util.TIMEOUT);
            httpURLConnection.setRequestProperty("Cookie", Settings.singleton(this.mContext).getSetting(Constants.PREF_COOKIE_STRING, str2));
            InputStream inputStream = httpURLConnection.getInputStream();
            this.size = httpURLConnection.getContentLength();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            while (true) {
                int read = inputStream.read();
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(read);
            }
            str = new String(byteArrayOutputStream.toByteArray());
            try {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                inputStream.close();
            } catch (SocketException e) {
                e = e;
                str2 = str;
            } catch (SocketTimeoutException e2) {
                e = e2;
                str2 = str;
                e.printStackTrace();
                str = str2;
                this.getURLContentsListener.onContentsDownloaded(str);
                return str;
            } catch (UnknownHostException e3) {
                e = e3;
                str2 = str;
                e.printStackTrace();
                str = str2;
                this.getURLContentsListener.onContentsDownloaded(str);
                return str;
            } catch (FileNotFoundException e4) {
                e = e4;
                str2 = str;
                e.printStackTrace();
                str = str2;
                this.getURLContentsListener.onContentsDownloaded(str);
                return str;
            } catch (Exception e5) {
                e = e5;
                str2 = str;
                LogUtil.e(this.TAG, " getMessage=%s", e.getMessage().toString());
                str = str2;
                this.getURLContentsListener.onContentsDownloaded(str);
                return str;
            }
        } catch (SocketException e6) {
            e = e6;
            e.printStackTrace();
            str = str2;
            this.getURLContentsListener.onContentsDownloaded(str);
            return str;
        } catch (SocketTimeoutException e7) {
            e = e7;
            e.printStackTrace();
            str = str2;
            this.getURLContentsListener.onContentsDownloaded(str);
            return str;
        } catch (UnknownHostException e8) {
            e = e8;
            e.printStackTrace();
            str = str2;
            this.getURLContentsListener.onContentsDownloaded(str);
            return str;
        } catch (FileNotFoundException e9) {
            e = e9;
            e.printStackTrace();
            str = str2;
            this.getURLContentsListener.onContentsDownloaded(str);
            return str;
        } catch (Exception e10) {
            e = e10;
            if (e.getMessage() != null && !Extensions.IsNullOrEmpty(e.getMessage().toString())) {
                LogUtil.e(this.TAG, " getMessage=%s", e.getMessage().toString());
            }
            str = str2;
            this.getURLContentsListener.onContentsDownloaded(str);
            return str;
        }
        this.getURLContentsListener.onContentsDownloaded(str);
        return str;
    }

    public void setGetURLContentsListener(FetchPListTask.FetchPListListener fetchPListListener) {
        this.getURLContentsListener = fetchPListListener;
    }

    public FetchPListTask.FetchPListListener getGetURLContentsListener() {
        return this.getURLContentsListener;
    }
}

package com.medscape.android.task;

import android.os.AsyncTask;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

public class FetchPListTask extends AsyncTask<String, Double, String> {
    private static final String TAG = "GetURLContentsTask";
    private int error = -1;
    private FetchPListListener mListener;
    private int size = 0;

    public interface FetchPListListener {
        void onContentsDownloaded(String str);

        void setContentsMaxProgress(int i);

        void setonError(int i);

        void showContentsDownloadedProgress(int i);
    }

    public FetchPListTask() {
    }

    public FetchPListTask(FetchPListListener fetchPListListener) {
        this.mListener = fetchPListListener;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(String... strArr) {
        String str = "";
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
            httpURLConnection.setReadTimeout(Util.TIMEOUT);
            InputStream inputStream = httpURLConnection.getInputStream();
            int contentLength = httpURLConnection.getContentLength();
            this.size = contentLength;
            this.mListener.setContentsMaxProgress(contentLength);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = inputStream.read();
                if (read != -1) {
                    byteArrayOutputStream.write(read);
                    publishProgress(new Double[]{Double.valueOf(((double) byteArrayOutputStream.size()) + FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)});
                } else {
                    String str2 = new String(byteArrayOutputStream.toByteArray());
                    try {
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                        inputStream.close();
                        return str2;
                    } catch (SocketException e) {
                        e = e;
                        str = str2;
                    } catch (SocketTimeoutException e2) {
                        e = e2;
                        str = str2;
                        this.error = 1;
                        e.printStackTrace();
                        return str;
                    } catch (UnknownHostException e3) {
                        e = e3;
                        str = str2;
                        e.printStackTrace();
                        this.error = 1;
                        return str;
                    } catch (FileNotFoundException e4) {
                        e = e4;
                        str = str2;
                        this.error = 10;
                        e.printStackTrace();
                        return str;
                    } catch (Exception e5) {
                        e = e5;
                        str = str2;
                        this.error = 8;
                        if (!(e.getMessage() == null || e.getMessage().toString() == null)) {
                            LogUtil.e(getClass().getName(), "message = %s", e.getMessage().toString());
                        }
                        return str;
                    }
                }
            }
        } catch (SocketException e6) {
            e = e6;
            e.printStackTrace();
            this.error = 1;
            return str;
        } catch (SocketTimeoutException e7) {
            e = e7;
            this.error = 1;
            e.printStackTrace();
            return str;
        } catch (UnknownHostException e8) {
            e = e8;
            e.printStackTrace();
            this.error = 1;
            return str;
        } catch (FileNotFoundException e9) {
            e = e9;
            this.error = 10;
            e.printStackTrace();
            return str;
        } catch (Exception e10) {
            e = e10;
            this.error = 8;
            LogUtil.e(getClass().getName(), "message = %s", e.getMessage().toString());
            return str;
        }
    }

    /* access modifiers changed from: protected */
    public void onProgressUpdate(Double... dArr) {
        this.mListener.showContentsDownloadedProgress(dArr[0].intValue());
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        FetchPListListener fetchPListListener = this.mListener;
        if (fetchPListListener != null) {
            int i = this.error;
            if (i != -1) {
                fetchPListListener.setonError(i);
                return;
            }
            fetchPListListener.showContentsDownloadedProgress(this.size);
            this.mListener.onContentsDownloaded(str);
        }
    }

    public void setGetURLContentsListener(FetchPListListener fetchPListListener) {
        this.mListener = fetchPListListener;
    }
}

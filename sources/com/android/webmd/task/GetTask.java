package com.android.webmd.task;

import android.content.Context;
import android.os.AsyncTask;
import com.android.webmd.model.Device;
import com.android.webmd.util.Util;
import java.net.URL;
import java.util.List;

public class GetTask extends AsyncTask<URL, String, List<Device>> {
    private GetParsedContentListener getURLContentsListener;
    private Context mContext;
    private String url;

    public GetTask(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0043, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0044, code lost:
        r3.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003f, code lost:
        r3.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0043 A[ExcHandler: IOException (r3v2 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.android.webmd.model.Device> doInBackground(java.net.URL... r3) {
        /*
            r2 = this;
            r0 = 0
            r3 = r3[r0]     // Catch:{ IOException -> 0x0043, Exception -> 0x003e }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0043, Exception -> 0x003e }
            r2.url = r3     // Catch:{ IOException -> 0x0043, Exception -> 0x003e }
            java.net.URL r3 = new java.net.URL     // Catch:{ IOException -> 0x0043, Exception -> 0x003e }
            java.lang.String r0 = r2.url     // Catch:{ IOException -> 0x0043, Exception -> 0x003e }
            java.lang.String r0 = r0.trim()     // Catch:{ IOException -> 0x0043, Exception -> 0x003e }
            r3.<init>(r0)     // Catch:{ IOException -> 0x0043, Exception -> 0x003e }
            java.net.URLConnection r3 = r3.openConnection()     // Catch:{ IOException -> 0x0043, Exception -> 0x003e }
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ IOException -> 0x0043, Exception -> 0x003e }
            r0 = 60000(0xea60, float:8.4078E-41)
            r3.setReadTimeout(r0)     // Catch:{ IOException -> 0x0043, Exception -> 0x003e }
            javax.xml.parsers.SAXParserFactory r0 = javax.xml.parsers.SAXParserFactory.newInstance()     // Catch:{ IOException -> 0x0043, Exception -> 0x003e }
            javax.xml.parsers.SAXParser r0 = r0.newSAXParser()     // Catch:{ Exception -> 0x0039, IOException -> 0x0043 }
            com.android.webmd.parser.DeviceHandler r1 = new com.android.webmd.parser.DeviceHandler     // Catch:{ Exception -> 0x0039, IOException -> 0x0043 }
            r1.<init>()     // Catch:{ Exception -> 0x0039, IOException -> 0x0043 }
            java.io.InputStream r3 = r3.getInputStream()     // Catch:{ Exception -> 0x0039, IOException -> 0x0043 }
            r0.parse(r3, r1)     // Catch:{ Exception -> 0x0039, IOException -> 0x0043 }
            java.util.List r3 = r1.getDevice()     // Catch:{ Exception -> 0x0039, IOException -> 0x0043 }
            goto L_0x0048
        L_0x0039:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ IOException -> 0x0043, Exception -> 0x003e }
            goto L_0x0047
        L_0x003e:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x0047
        L_0x0043:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0047:
            r3 = 0
        L_0x0048:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.webmd.task.GetTask.doInBackground(java.net.URL[]):java.util.List");
    }

    public static boolean compareDevice(Context context, List<Device> list) {
        String deviceId = Util.getDeviceId(context);
        if (list == null || list.size() == 0) {
            return false;
        }
        for (Device deviceId2 : list) {
            if (deviceId2.getDeviceId().equalsIgnoreCase(deviceId)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        super.onCancelled();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(List<Device> list) {
        this.getURLContentsListener.onContentsDownloaded(list);
    }

    public void setGetURLContentsListener(GetParsedContentListener getParsedContentListener) {
        this.getURLContentsListener = getParsedContentListener;
    }
}

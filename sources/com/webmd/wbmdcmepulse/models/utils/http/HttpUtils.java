package com.webmd.wbmdcmepulse.models.utils.http;

import com.wbmd.wbmdcommons.logging.Trace;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class HttpUtils {
    static final String TAG = HttpUtils.class.getSimpleName();

    public static HttpURLConnection getNewHttpConnection(String str) {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            KeyManagerFactory instance2 = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            instance2.init(instance, (char[]) null);
            SSLContext instance3 = SSLContext.getInstance("TLS");
            instance3.init(instance2.getKeyManagers(), (TrustManager[]) null, (SecureRandom) null);
            URL url = new URL(str);
            url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if (httpURLConnection instanceof HttpsURLConnection) {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(instance3.getSocketFactory());
            }
            httpURLConnection.setInstanceFollowRedirects(true);
            HttpURLConnection.setFollowRedirects(true);
            return httpURLConnection;
        } catch (Exception e) {
            Trace.e(TAG, "Unexpected error", e);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00db, code lost:
        if (r2.equalsIgnoreCase("DELETE") != false) goto L_0x00dd;
     */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0242 A[SYNTHETIC, Splitter:B:108:0x0242] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x024e A[SYNTHETIC, Splitter:B:115:0x024e] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01e7 A[Catch:{ Exception -> 0x01ff }] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01f1 A[Catch:{ Exception -> 0x01ff }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0204 A[SYNTHETIC, Splitter:B:95:0x0204] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.webmd.wbmdcmepulse.models.utils.http.HttpResponseObject sendHttpRequest(com.webmd.wbmdcmepulse.models.utils.http.HttpRequestObject r10, android.content.Context r11, boolean r12) {
        /*
            java.lang.String r12 = "POST"
            java.lang.String r0 = "HttpUtils"
            java.lang.String r1 = "="
            java.lang.String r2 = ""
            r3 = 0
            if (r10 == 0) goto L_0x0263
            com.webmd.wbmdcmepulse.models.utils.http.HttpResponseObject r4 = new com.webmd.wbmdcmepulse.models.utils.http.HttpResponseObject
            r4.<init>()
            boolean r5 = com.webmd.wbmdcmepulse.models.utils.Utilities.isNetworkAvailable(r11)
            if (r5 != 0) goto L_0x0024
            android.content.res.Resources r10 = r11.getResources()
            int r11 = com.webmd.wbmdcmepulse.R.string.error_connection_required
            java.lang.String r10 = r10.getString(r11)
            r4.setResponseErrorMsg(r10)
            return r4
        L_0x0024:
            int r5 = r10.getRequestType()
            r4.setRequestType(r5)
            java.lang.String r5 = r10.getUrl()     // Catch:{ Exception -> 0x021c, all -> 0x0219 }
            if (r5 == 0) goto L_0x0201
            java.lang.String r5 = r10.getUrl()     // Catch:{ Exception -> 0x021c, all -> 0x0219 }
            java.lang.String r5 = r5.trim()     // Catch:{ Exception -> 0x021c, all -> 0x0219 }
            boolean r5 = r5.equals(r2)     // Catch:{ Exception -> 0x021c, all -> 0x0219 }
            if (r5 != 0) goto L_0x0201
            java.lang.String r5 = r10.getRequestMethod()     // Catch:{ Exception -> 0x021c, all -> 0x0219 }
            if (r5 == 0) goto L_0x0201
            java.lang.String r5 = r10.getUrl()     // Catch:{ Exception -> 0x021c, all -> 0x0219 }
            java.net.HttpURLConnection r5 = getNewHttpConnection(r5)     // Catch:{ Exception -> 0x021c, all -> 0x0219 }
            if (r5 == 0) goto L_0x0202
            java.lang.String r6 = r10.getAuthorization()     // Catch:{ Exception -> 0x01ff }
            if (r6 == 0) goto L_0x0079
            java.lang.String r6 = r10.getAuthorization()     // Catch:{ Exception -> 0x01ff }
            boolean r6 = r6.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x01ff }
            if (r6 != 0) goto L_0x0079
            java.lang.String r6 = "Authorization"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01ff }
            r7.<init>()     // Catch:{ Exception -> 0x01ff }
            java.lang.String r8 = "bearer "
            r7.append(r8)     // Catch:{ Exception -> 0x01ff }
            java.lang.String r8 = r10.getAuthorization()     // Catch:{ Exception -> 0x01ff }
            r7.append(r8)     // Catch:{ Exception -> 0x01ff }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x01ff }
            r5.setRequestProperty(r6, r7)     // Catch:{ Exception -> 0x01ff }
        L_0x0079:
            java.util.HashMap r6 = r10.getHeadersMap()     // Catch:{ Exception -> 0x01ff }
            if (r6 == 0) goto L_0x00a9
            int r7 = r6.size()     // Catch:{ Exception -> 0x01ff }
            if (r7 <= 0) goto L_0x00a9
            java.util.Set r7 = r6.keySet()     // Catch:{ Exception -> 0x01ff }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ Exception -> 0x01ff }
        L_0x008d:
            boolean r8 = r7.hasNext()     // Catch:{ Exception -> 0x01ff }
            if (r8 == 0) goto L_0x00a9
            java.lang.Object r8 = r7.next()     // Catch:{ Exception -> 0x01ff }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Exception -> 0x01ff }
            java.lang.Object r9 = r6.get(r8)     // Catch:{ Exception -> 0x01ff }
            if (r9 == 0) goto L_0x008d
            java.lang.Object r9 = r6.get(r8)     // Catch:{ Exception -> 0x01ff }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x01ff }
            r5.setRequestProperty(r8, r9)     // Catch:{ Exception -> 0x01ff }
            goto L_0x008d
        L_0x00a9:
            java.lang.String r6 = r10.getContentType()     // Catch:{ Exception -> 0x01ff }
            if (r6 == 0) goto L_0x00be
            java.lang.String r7 = r6.trim()     // Catch:{ Exception -> 0x01ff }
            boolean r2 = r7.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x01ff }
            if (r2 != 0) goto L_0x00be
            java.lang.String r2 = "Content-Type"
            r5.setRequestProperty(r2, r6)     // Catch:{ Exception -> 0x01ff }
        L_0x00be:
            r2 = 30000(0x7530, float:4.2039E-41)
            r5.setReadTimeout(r2)     // Catch:{ Exception -> 0x01ff }
            r5.setConnectTimeout(r2)     // Catch:{ Exception -> 0x01ff }
            java.lang.String r2 = r10.getRequestMethod()     // Catch:{ Exception -> 0x01ff }
            java.lang.String r2 = r2.trim()     // Catch:{ Exception -> 0x01ff }
            boolean r6 = r2.equalsIgnoreCase(r12)     // Catch:{ Exception -> 0x01ff }
            java.lang.String r7 = "DELETE"
            r8 = 1
            if (r6 != 0) goto L_0x00dd
            boolean r6 = r2.equalsIgnoreCase(r7)     // Catch:{ Exception -> 0x01ff }
            if (r6 == 0) goto L_0x0118
        L_0x00dd:
            r5.setRequestMethod(r2)     // Catch:{ Exception -> 0x01ff }
            boolean r12 = r2.equalsIgnoreCase(r12)     // Catch:{ Exception -> 0x01ff }
            if (r12 != 0) goto L_0x00ec
            boolean r12 = r2.equalsIgnoreCase(r7)     // Catch:{ Exception -> 0x01ff }
            if (r12 == 0) goto L_0x0118
        L_0x00ec:
            java.lang.String r12 = r10.getRequestBody()     // Catch:{ Exception -> 0x01ff }
            if (r12 == 0) goto L_0x0118
            r5.setDoInput(r8)     // Catch:{ Exception -> 0x01ff }
            r5.setDoOutput(r8)     // Catch:{ Exception -> 0x01ff }
            java.io.OutputStream r12 = r5.getOutputStream()     // Catch:{ Exception -> 0x01ff }
            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch:{ Exception -> 0x01ff }
            java.io.OutputStreamWriter r6 = new java.io.OutputStreamWriter     // Catch:{ Exception -> 0x01ff }
            java.lang.String r7 = "UTF-8"
            r6.<init>(r12, r7)     // Catch:{ Exception -> 0x01ff }
            r2.<init>(r6)     // Catch:{ Exception -> 0x01ff }
            java.lang.String r6 = r10.getRequestBody()     // Catch:{ Exception -> 0x01ff }
            r2.write(r6)     // Catch:{ Exception -> 0x01ff }
            r2.flush()     // Catch:{ Exception -> 0x01ff }
            r2.close()     // Catch:{ Exception -> 0x01ff }
            r12.close()     // Catch:{ Exception -> 0x01ff }
        L_0x0118:
            java.io.BufferedReader r12 = new java.io.BufferedReader     // Catch:{ all -> 0x01dd }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x01dd }
            java.io.InputStream r6 = r5.getInputStream()     // Catch:{ all -> 0x01dd }
            r2.<init>(r6)     // Catch:{ all -> 0x01dd }
            r12.<init>(r2)     // Catch:{ all -> 0x01dd }
            int r2 = r5.getResponseCode()     // Catch:{ all -> 0x01da }
            r4.setResponseCode(r2)     // Catch:{ all -> 0x01da }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ all -> 0x01da }
            r2.<init>()     // Catch:{ all -> 0x01da }
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x01da }
            r3.<init>(r2)     // Catch:{ all -> 0x01da }
        L_0x0137:
            java.lang.String r6 = r12.readLine()     // Catch:{ all -> 0x01da }
            if (r6 == 0) goto L_0x0149
            java.lang.ref.WeakReference r7 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x01da }
            r7.<init>(r6)     // Catch:{ all -> 0x01da }
            r2.append(r6)     // Catch:{ all -> 0x01da }
            r7.clear()     // Catch:{ all -> 0x01da }
            goto L_0x0137
        L_0x0149:
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01da }
            r4.setResponseData(r2)     // Catch:{ all -> 0x01da }
            java.lang.String r2 = TAG     // Catch:{ all -> 0x01da }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x01da }
            r6.<init>()     // Catch:{ all -> 0x01da }
            java.lang.String r7 = "Response data for request to "
            r6.append(r7)     // Catch:{ all -> 0x01da }
            java.lang.String r10 = r10.getUrl()     // Catch:{ all -> 0x01da }
            r6.append(r10)     // Catch:{ all -> 0x01da }
            java.lang.String r10 = r6.toString()     // Catch:{ all -> 0x01da }
            com.wbmd.wbmdcommons.logging.Trace.i(r2, r10)     // Catch:{ all -> 0x01da }
            java.lang.String r10 = TAG     // Catch:{ all -> 0x01da }
            java.lang.String r2 = r4.getResponseData()     // Catch:{ all -> 0x01da }
            com.wbmd.wbmdcommons.logging.Trace.i(r10, r2)     // Catch:{ all -> 0x01da }
            r3.clear()     // Catch:{ all -> 0x01da }
            java.lang.System.gc()     // Catch:{ Exception -> 0x017a }
            goto L_0x017e
        L_0x017a:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ all -> 0x01da }
        L_0x017e:
            java.util.Map r10 = r5.getHeaderFields()     // Catch:{ all -> 0x01da }
            if (r10 == 0) goto L_0x01d8
            int r2 = r10.size()     // Catch:{ all -> 0x01da }
            if (r2 <= 0) goto L_0x01d8
            java.lang.String r2 = "Set-Cookie"
            java.lang.Object r10 = r10.get(r2)     // Catch:{ all -> 0x01da }
            java.util.List r10 = (java.util.List) r10     // Catch:{ all -> 0x01da }
            if (r10 == 0) goto L_0x01d8
            int r2 = r10.size()     // Catch:{ all -> 0x01da }
            if (r2 <= 0) goto L_0x01d8
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x01da }
            r2.<init>()     // Catch:{ all -> 0x01da }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ all -> 0x01da }
        L_0x01a3:
            boolean r3 = r10.hasNext()     // Catch:{ all -> 0x01da }
            if (r3 == 0) goto L_0x01d5
            java.lang.Object r3 = r10.next()     // Catch:{ all -> 0x01da }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x01da }
            boolean r6 = com.webmd.wbmdcmepulse.models.utils.Extensions.isStringNullOrEmpty(r3)     // Catch:{ all -> 0x01da }
            if (r6 == 0) goto L_0x01a3
            boolean r6 = r3.contains(r1)     // Catch:{ all -> 0x01da }
            if (r6 == 0) goto L_0x01a3
            r6 = 0
            int r7 = r3.indexOf(r1)     // Catch:{ all -> 0x01da }
            java.lang.String r6 = r3.substring(r6, r7)     // Catch:{ all -> 0x01da }
            int r7 = r3.indexOf(r1)     // Catch:{ all -> 0x01da }
            int r7 = r7 + r8
            int r9 = r3.length()     // Catch:{ all -> 0x01da }
            java.lang.String r3 = r3.substring(r7, r9)     // Catch:{ all -> 0x01da }
            r2.put(r6, r3)     // Catch:{ all -> 0x01da }
            goto L_0x01a3
        L_0x01d5:
            r4.setResponseCookies(r2)     // Catch:{ all -> 0x01da }
        L_0x01d8:
            r3 = r12
            goto L_0x0202
        L_0x01da:
            r10 = move-exception
            r3 = r12
            goto L_0x01de
        L_0x01dd:
            r10 = move-exception
        L_0x01de:
            java.lang.String r12 = "Failed to parse network response"
            com.wbmd.wbmdcommons.logging.Trace.w(r0, r12)     // Catch:{ Exception -> 0x01ff }
            boolean r12 = r10 instanceof java.net.UnknownHostException     // Catch:{ Exception -> 0x01ff }
            if (r12 == 0) goto L_0x01f1
            int r10 = com.webmd.wbmdcmepulse.R.string.error_connection_required     // Catch:{ Exception -> 0x01ff }
            java.lang.String r10 = r11.getString(r10)     // Catch:{ Exception -> 0x01ff }
            r4.setResponseErrorMsg(r10)     // Catch:{ Exception -> 0x01ff }
            goto L_0x0202
        L_0x01f1:
            boolean r10 = r10 instanceof java.net.SocketTimeoutException     // Catch:{ Exception -> 0x01ff }
            if (r10 == 0) goto L_0x0202
            int r10 = com.webmd.wbmdcmepulse.R.string.error_service_unavailable     // Catch:{ Exception -> 0x01ff }
            java.lang.String r10 = r11.getString(r10)     // Catch:{ Exception -> 0x01ff }
            r4.setResponseErrorMsg(r10)     // Catch:{ Exception -> 0x01ff }
            goto L_0x0202
        L_0x01ff:
            r10 = move-exception
            goto L_0x021e
        L_0x0201:
            r5 = r3
        L_0x0202:
            if (r3 == 0) goto L_0x024a
            r3.close()     // Catch:{ IOException -> 0x020d }
            if (r5 == 0) goto L_0x024a
            r5.disconnect()     // Catch:{ IOException -> 0x020d }
            goto L_0x024a
        L_0x020d:
            r10 = move-exception
            r10.printStackTrace()
            java.lang.String r10 = r10.getMessage()
            r4.setResponseErrorMsg(r10)
            goto L_0x024a
        L_0x0219:
            r10 = move-exception
            r5 = r3
            goto L_0x024c
        L_0x021c:
            r10 = move-exception
            r5 = r3
        L_0x021e:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x024b }
            r11.<init>()     // Catch:{ all -> 0x024b }
            java.lang.String r12 = "sendHttpRequest::"
            r11.append(r12)     // Catch:{ all -> 0x024b }
            java.lang.String r12 = r10.getMessage()     // Catch:{ all -> 0x024b }
            r11.append(r12)     // Catch:{ all -> 0x024b }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x024b }
            com.wbmd.wbmdcommons.logging.Trace.d(r0, r11)     // Catch:{ all -> 0x024b }
            r10.printStackTrace()     // Catch:{ all -> 0x024b }
            java.lang.String r10 = r10.getMessage()     // Catch:{ all -> 0x024b }
            r4.setResponseErrorMsg(r10)     // Catch:{ all -> 0x024b }
            if (r3 == 0) goto L_0x024a
            r3.close()     // Catch:{ IOException -> 0x020d }
            if (r5 == 0) goto L_0x024a
            r5.disconnect()     // Catch:{ IOException -> 0x020d }
        L_0x024a:
            return r4
        L_0x024b:
            r10 = move-exception
        L_0x024c:
            if (r3 == 0) goto L_0x0262
            r3.close()     // Catch:{ IOException -> 0x0257 }
            if (r5 == 0) goto L_0x0262
            r5.disconnect()     // Catch:{ IOException -> 0x0257 }
            goto L_0x0262
        L_0x0257:
            r11 = move-exception
            r11.printStackTrace()
            java.lang.String r11 = r11.getMessage()
            r4.setResponseErrorMsg(r11)
        L_0x0262:
            throw r10
        L_0x0263:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.models.utils.http.HttpUtils.sendHttpRequest(com.webmd.wbmdcmepulse.models.utils.http.HttpRequestObject, android.content.Context, boolean):com.webmd.wbmdcmepulse.models.utils.http.HttpResponseObject");
    }
}

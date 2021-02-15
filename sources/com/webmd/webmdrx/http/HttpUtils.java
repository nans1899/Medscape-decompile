package com.webmd.webmdrx.http;

import com.webmd.webmdrx.util.Trace;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class HttpUtils {
    private static final String TAG = HttpUtils.class.getSimpleName();

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

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d7, code lost:
        if (r2.equalsIgnoreCase("DELETE") != false) goto L_0x00d9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x023c A[SYNTHETIC, Splitter:B:106:0x023c] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0246 A[SYNTHETIC, Splitter:B:111:0x0246] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01e3 A[Catch:{ Exception -> 0x01fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01ed A[Catch:{ Exception -> 0x01fb }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0200 A[SYNTHETIC, Splitter:B:95:0x0200] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.webmd.webmdrx.http.HttpResponseObject sendHttpRequest(com.webmd.webmdrx.http.HttpRequestObject r10, android.content.Context r11, boolean r12) {
        /*
            java.lang.String r12 = "POST"
            java.lang.String r0 = "HttpUtils"
            java.lang.String r1 = "="
            java.lang.String r2 = ""
            r3 = 0
            if (r10 == 0) goto L_0x0259
            com.webmd.webmdrx.http.HttpResponseObject r4 = new com.webmd.webmdrx.http.HttpResponseObject
            r4.<init>()
            boolean r5 = com.webmd.webmdrx.util.NetworkUtil.isOnline(r11)
            if (r5 != 0) goto L_0x0020
            int r10 = com.webmd.webmdrx.R.string.error_connection_required
            java.lang.String r10 = r11.getString(r10)
            r4.setResponseErrorMsg(r10)
            return r4
        L_0x0020:
            int r5 = r10.getRequestType()
            r4.setRequestType(r5)
            java.lang.String r5 = r10.getUrl()     // Catch:{ Exception -> 0x0216, all -> 0x0213 }
            if (r5 == 0) goto L_0x01fd
            java.lang.String r5 = r10.getUrl()     // Catch:{ Exception -> 0x0216, all -> 0x0213 }
            java.lang.String r5 = r5.trim()     // Catch:{ Exception -> 0x0216, all -> 0x0213 }
            boolean r5 = r5.equals(r2)     // Catch:{ Exception -> 0x0216, all -> 0x0213 }
            if (r5 != 0) goto L_0x01fd
            java.lang.String r5 = r10.getRequestMethod()     // Catch:{ Exception -> 0x0216, all -> 0x0213 }
            if (r5 == 0) goto L_0x01fd
            java.lang.String r5 = r10.getUrl()     // Catch:{ Exception -> 0x0216, all -> 0x0213 }
            java.net.HttpURLConnection r5 = getNewHttpConnection(r5)     // Catch:{ Exception -> 0x0216, all -> 0x0213 }
            if (r5 == 0) goto L_0x01fe
            java.lang.String r6 = r10.getAuthorization()     // Catch:{ Exception -> 0x01fb }
            if (r6 == 0) goto L_0x0075
            java.lang.String r6 = r10.getAuthorization()     // Catch:{ Exception -> 0x01fb }
            boolean r6 = r6.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x01fb }
            if (r6 != 0) goto L_0x0075
            java.lang.String r6 = "Authorization"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01fb }
            r7.<init>()     // Catch:{ Exception -> 0x01fb }
            java.lang.String r8 = "bearer "
            r7.append(r8)     // Catch:{ Exception -> 0x01fb }
            java.lang.String r8 = r10.getAuthorization()     // Catch:{ Exception -> 0x01fb }
            r7.append(r8)     // Catch:{ Exception -> 0x01fb }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x01fb }
            r5.setRequestProperty(r6, r7)     // Catch:{ Exception -> 0x01fb }
        L_0x0075:
            java.util.HashMap r6 = r10.getHeadersMap()     // Catch:{ Exception -> 0x01fb }
            if (r6 == 0) goto L_0x00a5
            int r7 = r6.size()     // Catch:{ Exception -> 0x01fb }
            if (r7 <= 0) goto L_0x00a5
            java.util.Set r7 = r6.keySet()     // Catch:{ Exception -> 0x01fb }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ Exception -> 0x01fb }
        L_0x0089:
            boolean r8 = r7.hasNext()     // Catch:{ Exception -> 0x01fb }
            if (r8 == 0) goto L_0x00a5
            java.lang.Object r8 = r7.next()     // Catch:{ Exception -> 0x01fb }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Exception -> 0x01fb }
            java.lang.Object r9 = r6.get(r8)     // Catch:{ Exception -> 0x01fb }
            if (r9 == 0) goto L_0x0089
            java.lang.Object r9 = r6.get(r8)     // Catch:{ Exception -> 0x01fb }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x01fb }
            r5.setRequestProperty(r8, r9)     // Catch:{ Exception -> 0x01fb }
            goto L_0x0089
        L_0x00a5:
            java.lang.String r6 = r10.getContentType()     // Catch:{ Exception -> 0x01fb }
            if (r6 == 0) goto L_0x00ba
            java.lang.String r7 = r6.trim()     // Catch:{ Exception -> 0x01fb }
            boolean r2 = r7.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x01fb }
            if (r2 != 0) goto L_0x00ba
            java.lang.String r2 = "Content-Type"
            r5.setRequestProperty(r2, r6)     // Catch:{ Exception -> 0x01fb }
        L_0x00ba:
            r2 = 30000(0x7530, float:4.2039E-41)
            r5.setReadTimeout(r2)     // Catch:{ Exception -> 0x01fb }
            r5.setConnectTimeout(r2)     // Catch:{ Exception -> 0x01fb }
            java.lang.String r2 = r10.getRequestMethod()     // Catch:{ Exception -> 0x01fb }
            java.lang.String r2 = r2.trim()     // Catch:{ Exception -> 0x01fb }
            boolean r6 = r2.equalsIgnoreCase(r12)     // Catch:{ Exception -> 0x01fb }
            java.lang.String r7 = "DELETE"
            r8 = 1
            if (r6 != 0) goto L_0x00d9
            boolean r6 = r2.equalsIgnoreCase(r7)     // Catch:{ Exception -> 0x01fb }
            if (r6 == 0) goto L_0x0114
        L_0x00d9:
            r5.setRequestMethod(r2)     // Catch:{ Exception -> 0x01fb }
            boolean r12 = r2.equalsIgnoreCase(r12)     // Catch:{ Exception -> 0x01fb }
            if (r12 != 0) goto L_0x00e8
            boolean r12 = r2.equalsIgnoreCase(r7)     // Catch:{ Exception -> 0x01fb }
            if (r12 == 0) goto L_0x0114
        L_0x00e8:
            java.lang.String r12 = r10.getRequestBody()     // Catch:{ Exception -> 0x01fb }
            if (r12 == 0) goto L_0x0114
            r5.setDoInput(r8)     // Catch:{ Exception -> 0x01fb }
            r5.setDoOutput(r8)     // Catch:{ Exception -> 0x01fb }
            java.io.OutputStream r12 = r5.getOutputStream()     // Catch:{ Exception -> 0x01fb }
            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch:{ Exception -> 0x01fb }
            java.io.OutputStreamWriter r6 = new java.io.OutputStreamWriter     // Catch:{ Exception -> 0x01fb }
            java.lang.String r7 = "UTF-8"
            r6.<init>(r12, r7)     // Catch:{ Exception -> 0x01fb }
            r2.<init>(r6)     // Catch:{ Exception -> 0x01fb }
            java.lang.String r6 = r10.getRequestBody()     // Catch:{ Exception -> 0x01fb }
            r2.write(r6)     // Catch:{ Exception -> 0x01fb }
            r2.flush()     // Catch:{ Exception -> 0x01fb }
            r2.close()     // Catch:{ Exception -> 0x01fb }
            r12.close()     // Catch:{ Exception -> 0x01fb }
        L_0x0114:
            java.io.BufferedReader r12 = new java.io.BufferedReader     // Catch:{ all -> 0x01d9 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x01d9 }
            java.io.InputStream r6 = r5.getInputStream()     // Catch:{ all -> 0x01d9 }
            r2.<init>(r6)     // Catch:{ all -> 0x01d9 }
            r12.<init>(r2)     // Catch:{ all -> 0x01d9 }
            int r2 = r5.getResponseCode()     // Catch:{ all -> 0x01d6 }
            r4.setResponseCode(r2)     // Catch:{ all -> 0x01d6 }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ all -> 0x01d6 }
            r2.<init>()     // Catch:{ all -> 0x01d6 }
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x01d6 }
            r3.<init>(r2)     // Catch:{ all -> 0x01d6 }
        L_0x0133:
            java.lang.String r6 = r12.readLine()     // Catch:{ all -> 0x01d6 }
            if (r6 == 0) goto L_0x0145
            java.lang.ref.WeakReference r7 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x01d6 }
            r7.<init>(r6)     // Catch:{ all -> 0x01d6 }
            r2.append(r6)     // Catch:{ all -> 0x01d6 }
            r7.clear()     // Catch:{ all -> 0x01d6 }
            goto L_0x0133
        L_0x0145:
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01d6 }
            r4.setResponseData(r2)     // Catch:{ all -> 0x01d6 }
            java.lang.String r2 = TAG     // Catch:{ all -> 0x01d6 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d6 }
            r6.<init>()     // Catch:{ all -> 0x01d6 }
            java.lang.String r7 = "Response data for request to "
            r6.append(r7)     // Catch:{ all -> 0x01d6 }
            java.lang.String r10 = r10.getUrl()     // Catch:{ all -> 0x01d6 }
            r6.append(r10)     // Catch:{ all -> 0x01d6 }
            java.lang.String r10 = r6.toString()     // Catch:{ all -> 0x01d6 }
            com.webmd.webmdrx.util.Trace.i(r2, r10)     // Catch:{ all -> 0x01d6 }
            java.lang.String r10 = TAG     // Catch:{ all -> 0x01d6 }
            java.lang.String r2 = r4.getResponseData()     // Catch:{ all -> 0x01d6 }
            com.webmd.webmdrx.util.Trace.i(r10, r2)     // Catch:{ all -> 0x01d6 }
            r3.clear()     // Catch:{ all -> 0x01d6 }
            java.lang.System.gc()     // Catch:{ Exception -> 0x0176 }
            goto L_0x017a
        L_0x0176:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ all -> 0x01d6 }
        L_0x017a:
            java.util.Map r10 = r5.getHeaderFields()     // Catch:{ all -> 0x01d6 }
            if (r10 == 0) goto L_0x01d4
            int r2 = r10.size()     // Catch:{ all -> 0x01d6 }
            if (r2 <= 0) goto L_0x01d4
            java.lang.String r2 = "Set-Cookie"
            java.lang.Object r10 = r10.get(r2)     // Catch:{ all -> 0x01d6 }
            java.util.List r10 = (java.util.List) r10     // Catch:{ all -> 0x01d6 }
            if (r10 == 0) goto L_0x01d4
            int r2 = r10.size()     // Catch:{ all -> 0x01d6 }
            if (r2 <= 0) goto L_0x01d4
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x01d6 }
            r2.<init>()     // Catch:{ all -> 0x01d6 }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ all -> 0x01d6 }
        L_0x019f:
            boolean r3 = r10.hasNext()     // Catch:{ all -> 0x01d6 }
            if (r3 == 0) goto L_0x01d1
            java.lang.Object r3 = r10.next()     // Catch:{ all -> 0x01d6 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x01d6 }
            boolean r6 = com.webmd.webmdrx.util.StringUtil.isNotEmpty(r3)     // Catch:{ all -> 0x01d6 }
            if (r6 == 0) goto L_0x019f
            boolean r6 = r3.contains(r1)     // Catch:{ all -> 0x01d6 }
            if (r6 == 0) goto L_0x019f
            r6 = 0
            int r7 = r3.indexOf(r1)     // Catch:{ all -> 0x01d6 }
            java.lang.String r6 = r3.substring(r6, r7)     // Catch:{ all -> 0x01d6 }
            int r7 = r3.indexOf(r1)     // Catch:{ all -> 0x01d6 }
            int r7 = r7 + r8
            int r9 = r3.length()     // Catch:{ all -> 0x01d6 }
            java.lang.String r3 = r3.substring(r7, r9)     // Catch:{ all -> 0x01d6 }
            r2.put(r6, r3)     // Catch:{ all -> 0x01d6 }
            goto L_0x019f
        L_0x01d1:
            r4.setResponseCookies(r2)     // Catch:{ all -> 0x01d6 }
        L_0x01d4:
            r3 = r12
            goto L_0x01fe
        L_0x01d6:
            r10 = move-exception
            r3 = r12
            goto L_0x01da
        L_0x01d9:
            r10 = move-exception
        L_0x01da:
            java.lang.String r12 = "Failed to parse network response"
            com.webmd.webmdrx.util.Trace.w(r0, r12)     // Catch:{ Exception -> 0x01fb }
            boolean r12 = r10 instanceof java.net.UnknownHostException     // Catch:{ Exception -> 0x01fb }
            if (r12 == 0) goto L_0x01ed
            int r10 = com.webmd.webmdrx.R.string.error_connection_required     // Catch:{ Exception -> 0x01fb }
            java.lang.String r10 = r11.getString(r10)     // Catch:{ Exception -> 0x01fb }
            r4.setResponseErrorMsg(r10)     // Catch:{ Exception -> 0x01fb }
            goto L_0x01fe
        L_0x01ed:
            boolean r10 = r10 instanceof java.net.SocketTimeoutException     // Catch:{ Exception -> 0x01fb }
            if (r10 == 0) goto L_0x01fe
            int r10 = com.webmd.webmdrx.R.string.error_service_unavailable     // Catch:{ Exception -> 0x01fb }
            java.lang.String r10 = r11.getString(r10)     // Catch:{ Exception -> 0x01fb }
            r4.setResponseErrorMsg(r10)     // Catch:{ Exception -> 0x01fb }
            goto L_0x01fe
        L_0x01fb:
            r10 = move-exception
            goto L_0x0218
        L_0x01fd:
            r5 = r3
        L_0x01fe:
            if (r3 == 0) goto L_0x0242
            r3.close()     // Catch:{ IOException -> 0x0207 }
            r5.disconnect()     // Catch:{ IOException -> 0x0207 }
            goto L_0x0242
        L_0x0207:
            r10 = move-exception
            r10.printStackTrace()
            java.lang.String r10 = r10.getMessage()
            r4.setResponseErrorMsg(r10)
            goto L_0x0242
        L_0x0213:
            r10 = move-exception
            r5 = r3
            goto L_0x0244
        L_0x0216:
            r10 = move-exception
            r5 = r3
        L_0x0218:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0243 }
            r11.<init>()     // Catch:{ all -> 0x0243 }
            java.lang.String r12 = "sendHttpRequest::"
            r11.append(r12)     // Catch:{ all -> 0x0243 }
            java.lang.String r12 = r10.getMessage()     // Catch:{ all -> 0x0243 }
            r11.append(r12)     // Catch:{ all -> 0x0243 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0243 }
            com.webmd.webmdrx.util.Trace.d(r0, r11)     // Catch:{ all -> 0x0243 }
            r10.printStackTrace()     // Catch:{ all -> 0x0243 }
            java.lang.String r10 = r10.getMessage()     // Catch:{ all -> 0x0243 }
            r4.setResponseErrorMsg(r10)     // Catch:{ all -> 0x0243 }
            if (r3 == 0) goto L_0x0242
            r3.close()     // Catch:{ IOException -> 0x0207 }
            r5.disconnect()     // Catch:{ IOException -> 0x0207 }
        L_0x0242:
            return r4
        L_0x0243:
            r10 = move-exception
        L_0x0244:
            if (r3 == 0) goto L_0x0258
            r3.close()     // Catch:{ IOException -> 0x024d }
            r5.disconnect()     // Catch:{ IOException -> 0x024d }
            goto L_0x0258
        L_0x024d:
            r11 = move-exception
            r11.printStackTrace()
            java.lang.String r11 = r11.getMessage()
            r4.setResponseErrorMsg(r11)
        L_0x0258:
            throw r10
        L_0x0259:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.webmdrx.http.HttpUtils.sendHttpRequest(com.webmd.webmdrx.http.HttpRequestObject, android.content.Context, boolean):com.webmd.webmdrx.http.HttpResponseObject");
    }
}

package com.medscape.android.util;

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

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f2, code lost:
        if (r13.equalsIgnoreCase("DELETE") != false) goto L_0x00f4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x026c A[SYNTHETIC, Splitter:B:114:0x026c] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0278 A[SYNTHETIC, Splitter:B:121:0x0278] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.medscape.android.http.HttpResponseObject sendHttpRequest(com.medscape.android.http.HttpRequestObject r11, android.content.Context r12, boolean r13) {
        /*
            java.lang.String r0 = "="
            java.lang.String r1 = "POST"
            java.lang.String r2 = "HttpUtils"
            java.lang.String r3 = ""
            r4 = 0
            if (r11 == 0) goto L_0x028d
            com.medscape.android.http.HttpResponseObject r5 = new com.medscape.android.http.HttpResponseObject
            r5.<init>()
            boolean r6 = com.medscape.android.util.Util.isOnline(r12)
            r7 = 2131952444(0x7f13033c, float:1.954133E38)
            if (r6 != 0) goto L_0x0021
            java.lang.String r11 = r12.getString(r7)
            r5.setResponseErrorMsg(r11)
            return r5
        L_0x0021:
            int r6 = r11.getRequestType()
            r5.setRequestType(r6)
            java.lang.String r6 = r11.getUrl()     // Catch:{ Exception -> 0x0246, all -> 0x0243 }
            if (r6 == 0) goto L_0x022b
            java.lang.String r6 = r11.getUrl()     // Catch:{ Exception -> 0x0246, all -> 0x0243 }
            java.lang.String r6 = r6.trim()     // Catch:{ Exception -> 0x0246, all -> 0x0243 }
            boolean r6 = r6.equals(r3)     // Catch:{ Exception -> 0x0246, all -> 0x0243 }
            if (r6 != 0) goto L_0x022b
            java.lang.String r6 = r11.getRequestMethod()     // Catch:{ Exception -> 0x0246, all -> 0x0243 }
            if (r6 == 0) goto L_0x022b
            java.lang.String r6 = r11.getUrl()     // Catch:{ Exception -> 0x0246, all -> 0x0243 }
            java.net.HttpURLConnection r6 = getNewHttpConnection(r6)     // Catch:{ Exception -> 0x0246, all -> 0x0243 }
            if (r6 == 0) goto L_0x022c
            com.medscape.android.Settings r8 = com.medscape.android.Settings.singleton(r12)     // Catch:{ Exception -> 0x0229 }
            java.lang.String r9 = "pref_cookie_string"
            java.lang.String r8 = r8.getSetting((java.lang.String) r9, (java.lang.String) r3)     // Catch:{ Exception -> 0x0229 }
            if (r8 == 0) goto L_0x0064
            boolean r9 = r8.equalsIgnoreCase(r3)     // Catch:{ Exception -> 0x0229 }
            if (r9 != 0) goto L_0x0064
            java.lang.String r13 = "Cookie"
            r6.setRequestProperty(r13, r8)     // Catch:{ Exception -> 0x0229 }
            goto L_0x0067
        L_0x0064:
            if (r13 == 0) goto L_0x0067
            return r4
        L_0x0067:
            java.lang.String r13 = r11.getAuthorization()     // Catch:{ Exception -> 0x0229 }
            if (r13 == 0) goto L_0x0091
            java.lang.String r13 = r11.getAuthorization()     // Catch:{ Exception -> 0x0229 }
            boolean r13 = r13.equalsIgnoreCase(r3)     // Catch:{ Exception -> 0x0229 }
            if (r13 != 0) goto L_0x0091
            java.lang.String r13 = "Authorization"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0229 }
            r8.<init>()     // Catch:{ Exception -> 0x0229 }
            java.lang.String r9 = "bearer "
            r8.append(r9)     // Catch:{ Exception -> 0x0229 }
            java.lang.String r9 = r11.getAuthorization()     // Catch:{ Exception -> 0x0229 }
            r8.append(r9)     // Catch:{ Exception -> 0x0229 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0229 }
            r6.setRequestProperty(r13, r8)     // Catch:{ Exception -> 0x0229 }
        L_0x0091:
            java.util.HashMap r13 = r11.getHeadersMap()     // Catch:{ Exception -> 0x0229 }
            if (r13 == 0) goto L_0x00c1
            int r8 = r13.size()     // Catch:{ Exception -> 0x0229 }
            if (r8 <= 0) goto L_0x00c1
            java.util.Set r8 = r13.keySet()     // Catch:{ Exception -> 0x0229 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ Exception -> 0x0229 }
        L_0x00a5:
            boolean r9 = r8.hasNext()     // Catch:{ Exception -> 0x0229 }
            if (r9 == 0) goto L_0x00c1
            java.lang.Object r9 = r8.next()     // Catch:{ Exception -> 0x0229 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x0229 }
            java.lang.Object r10 = r13.get(r9)     // Catch:{ Exception -> 0x0229 }
            if (r10 == 0) goto L_0x00a5
            java.lang.Object r10 = r13.get(r9)     // Catch:{ Exception -> 0x0229 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Exception -> 0x0229 }
            r6.setRequestProperty(r9, r10)     // Catch:{ Exception -> 0x0229 }
            goto L_0x00a5
        L_0x00c1:
            java.lang.String r13 = r11.getContentType()     // Catch:{ Exception -> 0x0229 }
            if (r13 == 0) goto L_0x00d6
            java.lang.String r8 = r13.trim()     // Catch:{ Exception -> 0x0229 }
            boolean r3 = r8.equalsIgnoreCase(r3)     // Catch:{ Exception -> 0x0229 }
            if (r3 != 0) goto L_0x00d6
            java.lang.String r3 = "Content-Type"
            r6.setRequestProperty(r3, r13)     // Catch:{ Exception -> 0x0229 }
        L_0x00d6:
            r13 = 30000(0x7530, float:4.2039E-41)
            r6.setReadTimeout(r13)     // Catch:{ Exception -> 0x0229 }
            r6.setConnectTimeout(r13)     // Catch:{ Exception -> 0x0229 }
            java.lang.String r13 = r11.getRequestMethod()     // Catch:{ Exception -> 0x0229 }
            java.lang.String r13 = r13.trim()     // Catch:{ Exception -> 0x0229 }
            boolean r3 = r13.equalsIgnoreCase(r1)     // Catch:{ Exception -> 0x0229 }
            java.lang.String r8 = "DELETE"
            if (r3 != 0) goto L_0x00f4
            boolean r3 = r13.equalsIgnoreCase(r8)     // Catch:{ Exception -> 0x0229 }
            if (r3 == 0) goto L_0x0130
        L_0x00f4:
            r6.setRequestMethod(r13)     // Catch:{ Exception -> 0x0229 }
            boolean r1 = r13.equalsIgnoreCase(r1)     // Catch:{ Exception -> 0x0229 }
            if (r1 != 0) goto L_0x0103
            boolean r13 = r13.equalsIgnoreCase(r8)     // Catch:{ Exception -> 0x0229 }
            if (r13 == 0) goto L_0x0130
        L_0x0103:
            java.lang.String r13 = r11.getRequestBody()     // Catch:{ Exception -> 0x0229 }
            if (r13 == 0) goto L_0x0130
            r13 = 1
            r6.setDoInput(r13)     // Catch:{ Exception -> 0x0229 }
            r6.setDoOutput(r13)     // Catch:{ Exception -> 0x0229 }
            java.io.OutputStream r13 = r6.getOutputStream()     // Catch:{ Exception -> 0x0229 }
            java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch:{ Exception -> 0x0229 }
            java.io.OutputStreamWriter r3 = new java.io.OutputStreamWriter     // Catch:{ Exception -> 0x0229 }
            java.lang.String r8 = "UTF-8"
            r3.<init>(r13, r8)     // Catch:{ Exception -> 0x0229 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x0229 }
            java.lang.String r3 = r11.getRequestBody()     // Catch:{ Exception -> 0x0229 }
            r1.write(r3)     // Catch:{ Exception -> 0x0229 }
            r1.flush()     // Catch:{ Exception -> 0x0229 }
            r1.close()     // Catch:{ Exception -> 0x0229 }
            r13.close()     // Catch:{ Exception -> 0x0229 }
        L_0x0130:
            int r13 = r6.getResponseCode()     // Catch:{ all -> 0x0208 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r13 != r1) goto L_0x0147
            java.io.BufferedReader r13 = new java.io.BufferedReader     // Catch:{ all -> 0x0208 }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ all -> 0x0208 }
            java.io.InputStream r3 = r6.getInputStream()     // Catch:{ all -> 0x0208 }
            r1.<init>(r3)     // Catch:{ all -> 0x0208 }
            r13.<init>(r1)     // Catch:{ all -> 0x0208 }
            goto L_0x0155
        L_0x0147:
            java.io.BufferedReader r13 = new java.io.BufferedReader     // Catch:{ all -> 0x0208 }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ all -> 0x0208 }
            java.io.InputStream r3 = r6.getErrorStream()     // Catch:{ all -> 0x0208 }
            r1.<init>(r3)     // Catch:{ all -> 0x0208 }
            r13.<init>(r1)     // Catch:{ all -> 0x0208 }
        L_0x0155:
            r4 = r13
            int r13 = r6.getResponseCode()     // Catch:{ all -> 0x0208 }
            r5.setResponseCode(r13)     // Catch:{ all -> 0x0208 }
            java.lang.StringBuffer r13 = new java.lang.StringBuffer     // Catch:{ all -> 0x0208 }
            r13.<init>()     // Catch:{ all -> 0x0208 }
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0208 }
            r1.<init>(r13)     // Catch:{ all -> 0x0208 }
        L_0x0167:
            java.lang.String r3 = r4.readLine()     // Catch:{ all -> 0x0208 }
            if (r3 == 0) goto L_0x0179
            java.lang.ref.WeakReference r8 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x0208 }
            r8.<init>(r3)     // Catch:{ all -> 0x0208 }
            r13.append(r3)     // Catch:{ all -> 0x0208 }
            r8.clear()     // Catch:{ all -> 0x0208 }
            goto L_0x0167
        L_0x0179:
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x0208 }
            r5.setResponseData(r13)     // Catch:{ all -> 0x0208 }
            java.lang.String r13 = TAG     // Catch:{ all -> 0x0208 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0208 }
            r3.<init>()     // Catch:{ all -> 0x0208 }
            java.lang.String r8 = "Response content for request to "
            r3.append(r8)     // Catch:{ all -> 0x0208 }
            java.lang.String r11 = r11.getUrl()     // Catch:{ all -> 0x0208 }
            r3.append(r11)     // Catch:{ all -> 0x0208 }
            java.lang.String r11 = r3.toString()     // Catch:{ all -> 0x0208 }
            com.wbmd.wbmdcommons.logging.Trace.i(r13, r11)     // Catch:{ all -> 0x0208 }
            java.lang.String r11 = TAG     // Catch:{ all -> 0x0208 }
            java.lang.String r13 = r5.getResponseData()     // Catch:{ all -> 0x0208 }
            com.wbmd.wbmdcommons.logging.Trace.i(r11, r13)     // Catch:{ all -> 0x0208 }
            r1.clear()     // Catch:{ all -> 0x0208 }
            java.lang.System.gc()     // Catch:{ Exception -> 0x01aa }
            goto L_0x01ae
        L_0x01aa:
            r11 = move-exception
            r11.printStackTrace()     // Catch:{ all -> 0x0208 }
        L_0x01ae:
            java.util.Map r11 = r6.getHeaderFields()     // Catch:{ all -> 0x0208 }
            if (r11 == 0) goto L_0x022c
            int r13 = r11.size()     // Catch:{ all -> 0x0208 }
            if (r13 <= 0) goto L_0x022c
            java.lang.String r13 = "Set-Cookie"
            java.lang.Object r11 = r11.get(r13)     // Catch:{ all -> 0x0208 }
            java.util.List r11 = (java.util.List) r11     // Catch:{ all -> 0x0208 }
            if (r11 == 0) goto L_0x022c
            int r13 = r11.size()     // Catch:{ all -> 0x0208 }
            if (r13 <= 0) goto L_0x022c
            java.util.HashMap r13 = new java.util.HashMap     // Catch:{ all -> 0x0208 }
            r13.<init>()     // Catch:{ all -> 0x0208 }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ all -> 0x0208 }
        L_0x01d3:
            boolean r1 = r11.hasNext()     // Catch:{ all -> 0x0208 }
            if (r1 == 0) goto L_0x0204
            java.lang.Object r1 = r11.next()     // Catch:{ all -> 0x0208 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0208 }
            boolean r3 = com.medscape.android.util.StringUtil.isNotEmpty(r1)     // Catch:{ all -> 0x0208 }
            if (r3 == 0) goto L_0x01d3
            boolean r3 = r1.contains(r0)     // Catch:{ all -> 0x0208 }
            if (r3 == 0) goto L_0x01d3
            int r3 = r1.indexOf(r0)     // Catch:{ all -> 0x0208 }
            java.lang.String r8 = ";"
            int r8 = r1.indexOf(r8)     // Catch:{ all -> 0x0208 }
            r9 = 0
            java.lang.String r9 = r1.substring(r9, r3)     // Catch:{ all -> 0x0208 }
            int r3 = r3 + 1
            java.lang.String r1 = r1.substring(r3, r8)     // Catch:{ all -> 0x0208 }
            r13.put(r9, r1)     // Catch:{ all -> 0x0208 }
            goto L_0x01d3
        L_0x0204:
            r5.setResponseCookies(r13)     // Catch:{ all -> 0x0208 }
            goto L_0x022c
        L_0x0208:
            r11 = move-exception
            java.lang.String r13 = "Failed to parse network response"
            com.wbmd.wbmdcommons.logging.Trace.w(r2, r13)     // Catch:{ Exception -> 0x0229 }
            boolean r13 = r11 instanceof java.net.UnknownHostException     // Catch:{ Exception -> 0x0229 }
            if (r13 == 0) goto L_0x021a
            java.lang.String r11 = r12.getString(r7)     // Catch:{ Exception -> 0x0229 }
            r5.setResponseErrorMsg(r11)     // Catch:{ Exception -> 0x0229 }
            goto L_0x022c
        L_0x021a:
            boolean r11 = r11 instanceof java.net.SocketTimeoutException     // Catch:{ Exception -> 0x0229 }
            if (r11 == 0) goto L_0x022c
            r11 = 2131952474(0x7f13035a, float:1.9541392E38)
            java.lang.String r11 = r12.getString(r11)     // Catch:{ Exception -> 0x0229 }
            r5.setResponseErrorMsg(r11)     // Catch:{ Exception -> 0x0229 }
            goto L_0x022c
        L_0x0229:
            r11 = move-exception
            goto L_0x0248
        L_0x022b:
            r6 = r4
        L_0x022c:
            if (r4 == 0) goto L_0x0274
            r4.close()     // Catch:{ IOException -> 0x0237 }
            if (r6 == 0) goto L_0x0274
            r6.disconnect()     // Catch:{ IOException -> 0x0237 }
            goto L_0x0274
        L_0x0237:
            r11 = move-exception
            r11.printStackTrace()
            java.lang.String r11 = r11.getMessage()
            r5.setResponseErrorMsg(r11)
            goto L_0x0274
        L_0x0243:
            r11 = move-exception
            r6 = r4
            goto L_0x0276
        L_0x0246:
            r11 = move-exception
            r6 = r4
        L_0x0248:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0275 }
            r12.<init>()     // Catch:{ all -> 0x0275 }
            java.lang.String r13 = "sendHttpRequest::"
            r12.append(r13)     // Catch:{ all -> 0x0275 }
            java.lang.String r13 = r11.getMessage()     // Catch:{ all -> 0x0275 }
            r12.append(r13)     // Catch:{ all -> 0x0275 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0275 }
            com.wbmd.wbmdcommons.logging.Trace.d(r2, r12)     // Catch:{ all -> 0x0275 }
            r11.printStackTrace()     // Catch:{ all -> 0x0275 }
            java.lang.String r11 = r11.getMessage()     // Catch:{ all -> 0x0275 }
            r5.setResponseErrorMsg(r11)     // Catch:{ all -> 0x0275 }
            if (r4 == 0) goto L_0x0274
            r4.close()     // Catch:{ IOException -> 0x0237 }
            if (r6 == 0) goto L_0x0274
            r6.disconnect()     // Catch:{ IOException -> 0x0237 }
        L_0x0274:
            return r5
        L_0x0275:
            r11 = move-exception
        L_0x0276:
            if (r4 == 0) goto L_0x028c
            r4.close()     // Catch:{ IOException -> 0x0281 }
            if (r6 == 0) goto L_0x028c
            r6.disconnect()     // Catch:{ IOException -> 0x0281 }
            goto L_0x028c
        L_0x0281:
            r12 = move-exception
            r12.printStackTrace()
            java.lang.String r12 = r12.getMessage()
            r5.setResponseErrorMsg(r12)
        L_0x028c:
            throw r11
        L_0x028d:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.util.HttpUtils.sendHttpRequest(com.medscape.android.http.HttpRequestObject, android.content.Context, boolean):com.medscape.android.http.HttpResponseObject");
    }
}

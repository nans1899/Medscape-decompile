package bo.app;

import com.appboy.support.AppboyLogger;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import org.json.JSONObject;

public final class f implements g {
    private static final String a = AppboyLogger.getAppboyLogTag(f.class);
    private final int b;

    public f(int i) {
        this.b = i;
    }

    public JSONObject a(URI uri, Map<String, String> map) {
        return a(uri, (JSONObject) null, map, y.GET);
    }

    public JSONObject a(URI uri, Map<String, String> map, JSONObject jSONObject) {
        return a(uri, jSONObject, map, y.POST);
    }

    private JSONObject a(URI uri, JSONObject jSONObject, Map<String, String> map, y yVar) {
        URL a2 = eb.a(uri);
        if (a2 == null) {
            return null;
        }
        try {
            return a(a2, jSONObject, map, yVar);
        } catch (IOException e) {
            throw new aw("Experienced IOException during request to [" + a2.toString() + "], failing: [" + e.getMessage() + "]", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0084 A[SYNTHETIC, Splitter:B:34:0x0084] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONObject a(java.net.URL r7, org.json.JSONObject r8, java.util.Map<java.lang.String, java.lang.String> r9, bo.app.y r10) {
        /*
            r6 = this;
            java.lang.String r0 = "]"
            java.lang.String r1 = "Caught an error trying to close the inputStream in getJsonResultFromUrl"
            r2 = 0
            if (r7 == 0) goto L_0x0010
            java.net.HttpURLConnection r8 = r6.b(r7, r8, r9, r10)     // Catch:{ all -> 0x000c }
            goto L_0x0011
        L_0x000c:
            r7 = move-exception
            r9 = r2
            goto L_0x007d
        L_0x0010:
            r8 = r2
        L_0x0011:
            if (r8 == 0) goto L_0x008f
            java.io.InputStream r9 = r6.a((java.net.HttpURLConnection) r8)     // Catch:{ all -> 0x007a }
            java.io.BufferedReader r10 = new java.io.BufferedReader     // Catch:{ all -> 0x0078 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x0078 }
            java.lang.String r4 = "UTF-8"
            r3.<init>(r9, r4)     // Catch:{ all -> 0x0078 }
            r10.<init>(r3)     // Catch:{ all -> 0x0078 }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ IOException -> 0x0059, JSONException -> 0x003e }
            java.lang.String r10 = r6.a((java.io.BufferedReader) r10)     // Catch:{ IOException -> 0x0059, JSONException -> 0x003e }
            r3.<init>(r10)     // Catch:{ IOException -> 0x0059, JSONException -> 0x003e }
            if (r8 == 0) goto L_0x0031
            r8.disconnect()
        L_0x0031:
            if (r9 == 0) goto L_0x003d
            r9.close()     // Catch:{ Exception -> 0x0037 }
            goto L_0x003d
        L_0x0037:
            r7 = move-exception
            java.lang.String r8 = a
            com.appboy.support.AppboyLogger.e(r8, r1, r7)
        L_0x003d:
            return r3
        L_0x003e:
            r10 = move-exception
            java.lang.String r3 = a     // Catch:{ all -> 0x0078 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0078 }
            r4.<init>()     // Catch:{ all -> 0x0078 }
            java.lang.String r5 = "Unable to parse response ["
            r4.append(r5)     // Catch:{ all -> 0x0078 }
            r4.append(r10)     // Catch:{ all -> 0x0078 }
            r4.append(r0)     // Catch:{ all -> 0x0078 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0078 }
            com.appboy.support.AppboyLogger.e(r3, r0, r10)     // Catch:{ all -> 0x0078 }
            goto L_0x0090
        L_0x0059:
            r10 = move-exception
            java.lang.String r3 = a     // Catch:{ all -> 0x0078 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0078 }
            r4.<init>()     // Catch:{ all -> 0x0078 }
            java.lang.String r5 = "Could not read from response stream ["
            r4.append(r5)     // Catch:{ all -> 0x0078 }
            java.lang.String r5 = r10.getMessage()     // Catch:{ all -> 0x0078 }
            r4.append(r5)     // Catch:{ all -> 0x0078 }
            r4.append(r0)     // Catch:{ all -> 0x0078 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0078 }
            com.appboy.support.AppboyLogger.e(r3, r0, r10)     // Catch:{ all -> 0x0078 }
            goto L_0x0090
        L_0x0078:
            r7 = move-exception
            goto L_0x007c
        L_0x007a:
            r7 = move-exception
            r9 = r2
        L_0x007c:
            r2 = r8
        L_0x007d:
            if (r2 == 0) goto L_0x0082
            r2.disconnect()
        L_0x0082:
            if (r9 == 0) goto L_0x008e
            r9.close()     // Catch:{ Exception -> 0x0088 }
            goto L_0x008e
        L_0x0088:
            r8 = move-exception
            java.lang.String r9 = a
            com.appboy.support.AppboyLogger.e(r9, r1, r8)
        L_0x008e:
            throw r7
        L_0x008f:
            r9 = r2
        L_0x0090:
            if (r8 == 0) goto L_0x0095
            r8.disconnect()
        L_0x0095:
            if (r9 == 0) goto L_0x00a1
            r9.close()     // Catch:{ Exception -> 0x009b }
            goto L_0x00a1
        L_0x009b:
            r8 = move-exception
            java.lang.String r9 = a
            com.appboy.support.AppboyLogger.e(r9, r1, r8)
        L_0x00a1:
            java.lang.String r8 = a
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Failed to get result from ["
            r9.append(r10)
            r9.append(r7)
            java.lang.String r7 = "]. Returning null."
            r9.append(r7)
            java.lang.String r7 = r9.toString()
            com.appboy.support.AppboyLogger.w((java.lang.String) r8, (java.lang.String) r7)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.f.a(java.net.URL, org.json.JSONObject, java.util.Map, bo.app.y):org.json.JSONObject");
    }

    private InputStream a(HttpURLConnection httpURLConnection) {
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode / 100 != 2) {
            throw new aw("Bad Http response code from Appboy: [" + responseCode + "]");
        } else if ("gzip".equalsIgnoreCase(httpURLConnection.getContentEncoding())) {
            return new GZIPInputStream(httpURLConnection.getInputStream());
        } else {
            return new BufferedInputStream(httpURLConnection.getInputStream());
        }
    }

    private HttpURLConnection b(URL url, JSONObject jSONObject, Map<String, String> map, y yVar) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) k.a(url);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(this.b);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod(yVar.toString());
            a(httpURLConnection, map);
            if (yVar == y.POST) {
                a(httpURLConnection, jSONObject);
            }
            return httpURLConnection;
        } catch (IOException e) {
            throw new aw("Could not set up connection [" + url.toString() + "] [" + e.getMessage() + "].  Appboy will try to reconnect periodically.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(HttpURLConnection httpURLConnection, Map<String, String> map) {
        for (Map.Entry next : map.entrySet()) {
            httpURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
        }
    }

    private void a(HttpURLConnection httpURLConnection, JSONObject jSONObject) {
        httpURLConnection.setDoOutput(true);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
        bufferedOutputStream.write(jSONObject.toString().getBytes("UTF-8"));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    private String a(BufferedReader bufferedReader) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return sb.toString();
            }
            sb.append(readLine);
        }
    }
}

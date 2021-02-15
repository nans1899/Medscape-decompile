package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

public class BasicNetwork implements Network {
    protected static final boolean DEBUG = VolleyLog.DEBUG;
    private static int DEFAULT_POOL_SIZE = 4096;
    private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool byteArrayPool) {
        this.mHttpStack = httpStack;
        this.mPool = byteArrayPool;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0076, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0077, code lost:
        r16 = null;
        r17 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00cf, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d0, code lost:
        r1 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d2, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d3, code lost:
        r17 = r1;
        r16 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d9, code lost:
        r17 = r1;
        r13 = null;
        r16 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e0, code lost:
        r0 = r13.getStatusLine().getStatusCode();
        com.android.volley.VolleyLog.e("Unexpected response code %d for %s", java.lang.Integer.valueOf(r0), r25.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00fd, code lost:
        if (r16 != null) goto L_0x00ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ff, code lost:
        if (r13 != null) goto L_0x0101;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0101, code lost:
        r14 = new com.android.volley.NetworkResponse(r0, r16, r17, false, android.os.SystemClock.elapsedRealtime() - r9, r13.getAllHeaders());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0115, code lost:
        r14 = new com.android.volley.NetworkResponse(r0, r16, r17, false, android.os.SystemClock.elapsedRealtime() - r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0126, code lost:
        if (r0 == 401) goto L_0x0133;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0132, code lost:
        throw new com.android.volley.ServerError(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0133, code lost:
        attemptRetryOnException("auth", r8, new com.android.volley.AuthFailureError(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0144, code lost:
        throw new com.android.volley.NetworkError((com.android.volley.NetworkResponse) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x014a, code lost:
        throw new com.android.volley.NoConnectionError(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x014b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0166, code lost:
        throw new java.lang.RuntimeException("Bad URL " + r25.getUrl(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0167, code lost:
        attemptRetryOnException("connection", r8, new com.android.volley.TimeoutError());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0173, code lost:
        attemptRetryOnException("socket", r8, new com.android.volley.TimeoutError());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x014b A[ExcHandler: MalformedURLException (r0v2 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:2:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:68:? A[ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException), SYNTHETIC, Splitter:B:2:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:70:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:2:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0145 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.volley.NetworkResponse performRequest(com.android.volley.Request<?> r25) throws com.android.volley.VolleyError {
        /*
            r24 = this;
            r7 = r24
            r8 = r25
            long r9 = android.os.SystemClock.elapsedRealtime()
        L_0x0008:
            java.util.Map r1 = java.util.Collections.emptyMap()
            r11 = 0
            r12 = 0
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00d8 }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00d8 }
            com.android.volley.Cache$Entry r2 = r25.getCacheEntry()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00d8 }
            r7.addCacheHeaders(r0, r2)     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00d8 }
            com.android.volley.toolbox.HttpStack r2 = r7.mHttpStack     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00d8 }
            org.apache.http.HttpResponse r13 = r2.performRequest(r8, r0)     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00d8 }
            org.apache.http.StatusLine r6 = r13.getStatusLine()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00d2 }
            int r15 = r6.getStatusCode()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00d2 }
            org.apache.http.Header[] r0 = r13.getAllHeaders()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00d2 }
            java.util.Map r14 = convertHeaders(r0)     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00d2 }
            r0 = 304(0x130, float:4.26E-43)
            if (r15 != r0) goto L_0x007d
            com.android.volley.Cache$Entry r0 = r25.getCacheEntry()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            if (r0 != 0) goto L_0x0054
            com.android.volley.NetworkResponse r0 = new com.android.volley.NetworkResponse     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            r17 = 304(0x130, float:4.26E-43)
            r18 = 0
            r20 = 1
            long r1 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            long r21 = r1 - r9
            org.apache.http.Header[] r23 = r13.getAllHeaders()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            r16 = r0
            r19 = r14
            r16.<init>(r17, r18, r19, r20, r21, r23)     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            return r0
        L_0x0054:
            java.util.Map<java.lang.String, java.lang.String> r1 = r0.responseHeaders     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            r1.putAll(r14)     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            com.android.volley.NetworkResponse r1 = new com.android.volley.NetworkResponse     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            r16 = 304(0x130, float:4.26E-43)
            byte[] r2 = r0.data     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            java.util.Map<java.lang.String, java.lang.String> r0 = r0.responseHeaders     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            r19 = 1
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            long r20 = r3 - r9
            org.apache.http.Header[] r22 = r13.getAllHeaders()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            r15 = r1
            r17 = r2
            r18 = r0
            r15.<init>(r16, r17, r18, r19, r20, r22)     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            return r1
        L_0x0076:
            r0 = move-exception
            r16 = r12
            r17 = r14
            goto L_0x00de
        L_0x007d:
            org.apache.http.HttpEntity r0 = r13.getEntity()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00cf }
            if (r0 == 0) goto L_0x008c
            org.apache.http.HttpEntity r0 = r13.getEntity()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            byte[] r0 = r7.entityToBytes(r0)     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x0076 }
            goto L_0x008e
        L_0x008c:
            byte[] r0 = new byte[r11]     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00cf }
        L_0x008e:
            r22 = r0
            long r0 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00c8 }
            long r2 = r0 - r9
            r1 = r24
            r4 = r25
            r5 = r22
            r1.logSlowRequests(r2, r4, r5, r6)     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00c8 }
            r0 = 200(0xc8, float:2.8E-43)
            if (r15 < r0) goto L_0x00bf
            r0 = 299(0x12b, float:4.19E-43)
            if (r15 > r0) goto L_0x00bf
            com.android.volley.NetworkResponse r0 = new com.android.volley.NetworkResponse     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00c8 }
            r18 = 0
            long r1 = android.os.SystemClock.elapsedRealtime()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00c8 }
            long r19 = r1 - r9
            org.apache.http.Header[] r21 = r13.getAllHeaders()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00c8 }
            r1 = r14
            r14 = r0
            r16 = r22
            r17 = r1
            r14.<init>(r15, r16, r17, r18, r19, r21)     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00c6 }
            return r0
        L_0x00bf:
            r1 = r14
            java.io.IOException r0 = new java.io.IOException     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00c6 }
            r0.<init>()     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00c6 }
            throw r0     // Catch:{ SocketTimeoutException -> 0x0173, ConnectTimeoutException -> 0x0167, MalformedURLException -> 0x014b, IOException -> 0x00c6 }
        L_0x00c6:
            r0 = move-exception
            goto L_0x00ca
        L_0x00c8:
            r0 = move-exception
            r1 = r14
        L_0x00ca:
            r17 = r1
            r16 = r22
            goto L_0x00de
        L_0x00cf:
            r0 = move-exception
            r1 = r14
            goto L_0x00d3
        L_0x00d2:
            r0 = move-exception
        L_0x00d3:
            r17 = r1
            r16 = r12
            goto L_0x00de
        L_0x00d8:
            r0 = move-exception
            r17 = r1
            r13 = r12
            r16 = r13
        L_0x00de:
            if (r13 == 0) goto L_0x0145
            org.apache.http.StatusLine r0 = r13.getStatusLine()
            int r0 = r0.getStatusCode()
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            r1[r11] = r2
            r2 = 1
            java.lang.String r3 = r25.getUrl()
            r1[r2] = r3
            java.lang.String r2 = "Unexpected response code %d for %s"
            com.android.volley.VolleyLog.e(r2, r1)
            if (r16 == 0) goto L_0x013f
            if (r13 == 0) goto L_0x0115
            com.android.volley.NetworkResponse r1 = new com.android.volley.NetworkResponse
            r18 = 0
            long r2 = android.os.SystemClock.elapsedRealtime()
            long r19 = r2 - r9
            org.apache.http.Header[] r21 = r13.getAllHeaders()
            r14 = r1
            r15 = r0
            r14.<init>(r15, r16, r17, r18, r19, r21)
            goto L_0x0124
        L_0x0115:
            com.android.volley.NetworkResponse r1 = new com.android.volley.NetworkResponse
            r18 = 0
            long r2 = android.os.SystemClock.elapsedRealtime()
            long r19 = r2 - r9
            r14 = r1
            r15 = r0
            r14.<init>(r15, r16, r17, r18, r19)
        L_0x0124:
            r2 = 401(0x191, float:5.62E-43)
            if (r0 == r2) goto L_0x0133
            r2 = 403(0x193, float:5.65E-43)
            if (r0 != r2) goto L_0x012d
            goto L_0x0133
        L_0x012d:
            com.android.volley.ServerError r0 = new com.android.volley.ServerError
            r0.<init>(r1)
            throw r0
        L_0x0133:
            com.android.volley.AuthFailureError r0 = new com.android.volley.AuthFailureError
            r0.<init>((com.android.volley.NetworkResponse) r1)
            java.lang.String r1 = "auth"
            attemptRetryOnException(r1, r8, r0)
            goto L_0x0008
        L_0x013f:
            com.android.volley.NetworkError r0 = new com.android.volley.NetworkError
            r0.<init>((com.android.volley.NetworkResponse) r12)
            throw r0
        L_0x0145:
            com.android.volley.NoConnectionError r1 = new com.android.volley.NoConnectionError
            r1.<init>(r0)
            throw r1
        L_0x014b:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Bad URL "
            r2.append(r3)
            java.lang.String r3 = r25.getUrl()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x0167:
            com.android.volley.TimeoutError r0 = new com.android.volley.TimeoutError
            r0.<init>()
            java.lang.String r1 = "connection"
            attemptRetryOnException(r1, r8, r0)
            goto L_0x0008
        L_0x0173:
            com.android.volley.TimeoutError r0 = new com.android.volley.TimeoutError
            r0.<init>()
            java.lang.String r1 = "socket"
            attemptRetryOnException(r1, r8, r0)
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.BasicNetwork.performRequest(com.android.volley.Request):com.android.volley.NetworkResponse");
    }

    private void logSlowRequests(long j, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (DEBUG || j > ((long) SLOW_REQUEST_THRESHOLD_MS)) {
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    private static void attemptRetryOnException(String str, Request<?> request, VolleyError volleyError) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[]{str, Integer.valueOf(timeoutMs)}));
            throw e;
        }
    }

    private void addCacheHeaders(Map<String, String> map, Cache.Entry entry) {
        if (entry != null) {
            if (entry.etag != null) {
                map.put(HttpHeaders.IF_NONE_MATCH, entry.etag);
            }
            if (entry.lastModified > 0) {
                map.put(HttpHeaders.IF_MODIFIED_SINCE, DateUtils.formatDate(new Date(entry.lastModified)));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void logError(String str, String str2, long j) {
        VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", str, Long.valueOf(SystemClock.elapsedRealtime() - j), str2);
    }

    private byte[] entityToBytes(HttpEntity httpEntity) throws IOException, ServerError {
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.mPool, (int) httpEntity.getContentLength());
        byte[] bArr = null;
        try {
            InputStream content = httpEntity.getContent();
            if (content != null) {
                bArr = this.mPool.getBuf(1024);
                while (true) {
                    int read = content.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    poolingByteArrayOutputStream.write(bArr, 0, read);
                }
                return poolingByteArrayOutputStream.toByteArray();
            }
            throw new ServerError();
        } finally {
            try {
                httpEntity.consumeContent();
            } catch (IOException unused) {
                VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
            }
            this.mPool.returnBuf(bArr);
            poolingByteArrayOutputStream.close();
        }
    }

    protected static Map<String, String> convertHeaders(Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            if (!treeMap.containsKey(headerArr[i].getName())) {
                treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
            } else {
                treeMap.put(headerArr[i].getName(), ((String) treeMap.get(headerArr[i].getName())) + ";" + headerArr[i].getValue());
            }
        }
        return treeMap;
    }
}

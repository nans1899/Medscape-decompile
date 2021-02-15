package com.tapstream.sdk.http;

import java.io.IOException;

public class StdLibHttpClient implements HttpClient {
    public static final int DEFAULT_CONNECT_TIMEOUT = 5000;
    public static final int DEFAULT_READ_TIMEOUT = 5000;

    public void close() throws IOException {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r0 = r0.getErrorStream();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0090, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0096, code lost:
        throw new java.io.IOException(r5);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0076 */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0090 A[ExcHandler: Exception (r5v2 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:16:0x0069] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.tapstream.sdk.http.HttpResponse sendRequest(com.tapstream.sdk.http.HttpRequest r5) throws java.io.IOException {
        /*
            r4 = this;
            java.net.URL r0 = r5.getURL()
            java.net.URLConnection r0 = r0.openConnection()
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0
            r1 = 5000(0x1388, float:7.006E-42)
            r0.setConnectTimeout(r1)
            r0.setReadTimeout(r1)
            r1 = 0
            r0.setUseCaches(r1)
            int[] r1 = com.tapstream.sdk.http.StdLibHttpClient.AnonymousClass1.$SwitchMap$com$tapstream$sdk$http$HttpMethod
            com.tapstream.sdk.http.HttpMethod r2 = r5.getMethod()
            int r2 = r2.ordinal()
            r1 = r1[r2]
            r2 = 1
            if (r1 == r2) goto L_0x0064
            r3 = 2
            if (r1 == r3) goto L_0x0029
            goto L_0x0069
        L_0x0029:
            java.lang.String r1 = "POST"
            r0.setRequestMethod(r1)
            com.tapstream.sdk.http.RequestBody r1 = r5.getBody()
            if (r1 == 0) goto L_0x0069
            com.tapstream.sdk.http.RequestBody r1 = r5.getBody()
            java.lang.String r1 = r1.contentType()
            com.tapstream.sdk.http.RequestBody r5 = r5.getBody()
            byte[] r5 = r5.toBytes()
            int r3 = r5.length
            r0.setFixedLengthStreamingMode(r3)
            java.lang.String r3 = "Content-Type"
            r0.setRequestProperty(r3, r1)
            r0.setDoOutput(r2)
            java.io.OutputStream r1 = r0.getOutputStream()
            r1.write(r5)     // Catch:{ all -> 0x005d }
            if (r1 == 0) goto L_0x0069
            r1.close()
            goto L_0x0069
        L_0x005d:
            r5 = move-exception
            if (r1 == 0) goto L_0x0063
            r1.close()
        L_0x0063:
            throw r5
        L_0x0064:
            java.lang.String r5 = "GET"
            r0.setRequestMethod(r5)
        L_0x0069:
            int r5 = r0.getResponseCode()     // Catch:{ IOException -> 0x0097, Exception -> 0x0090 }
            java.lang.String r1 = r0.getResponseMessage()     // Catch:{ IOException -> 0x0097, Exception -> 0x0090 }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ IOException -> 0x0076, Exception -> 0x0090 }
            goto L_0x007a
        L_0x0076:
            java.io.InputStream r0 = r0.getErrorStream()     // Catch:{ IOException -> 0x0097, Exception -> 0x0090 }
        L_0x007a:
            byte[] r2 = com.tapstream.sdk.Utils.readFully(r0)     // Catch:{ all -> 0x0089 }
            if (r0 == 0) goto L_0x0083
            r0.close()     // Catch:{ IOException -> 0x0097, Exception -> 0x0090 }
        L_0x0083:
            com.tapstream.sdk.http.HttpResponse r0 = new com.tapstream.sdk.http.HttpResponse     // Catch:{ IOException -> 0x0097, Exception -> 0x0090 }
            r0.<init>(r5, r1, r2)     // Catch:{ IOException -> 0x0097, Exception -> 0x0090 }
            return r0
        L_0x0089:
            r5 = move-exception
            if (r0 == 0) goto L_0x008f
            r0.close()     // Catch:{ IOException -> 0x0097, Exception -> 0x0090 }
        L_0x008f:
            throw r5     // Catch:{ IOException -> 0x0097, Exception -> 0x0090 }
        L_0x0090:
            r5 = move-exception
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r5)
            throw r0
        L_0x0097:
            r5 = move-exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapstream.sdk.http.StdLibHttpClient.sendRequest(com.tapstream.sdk.http.HttpRequest):com.tapstream.sdk.http.HttpResponse");
    }

    /* renamed from: com.tapstream.sdk.http.StdLibHttpClient$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$tapstream$sdk$http$HttpMethod;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.tapstream.sdk.http.HttpMethod[] r0 = com.tapstream.sdk.http.HttpMethod.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$tapstream$sdk$http$HttpMethod = r0
                com.tapstream.sdk.http.HttpMethod r1 = com.tapstream.sdk.http.HttpMethod.GET     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$tapstream$sdk$http$HttpMethod     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tapstream.sdk.http.HttpMethod r1 = com.tapstream.sdk.http.HttpMethod.POST     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tapstream.sdk.http.StdLibHttpClient.AnonymousClass1.<clinit>():void");
        }
    }
}

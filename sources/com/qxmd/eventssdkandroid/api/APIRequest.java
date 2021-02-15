package com.qxmd.eventssdkandroid.api;

import com.qxmd.eventssdkandroid.managers.HttpClientManager;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;

public class APIRequest {
    private static final String ACCOUNTS_ENDPOINT = "https://events.service.qxmd.com/";
    private static final String ACCOUNTS_HEADER_API_KEY = "x-qxmd-api-key";
    private static final String ACCOUNTS_HEADER_API_VALUE = "iaobgWotkvgg6Rc1e1iEWk6hEAAY4V1y";
    protected static final int API_REQUEST_TIMEOUT = 30000;
    private static final String OK_HTTP_TAG = "OK_HTTP_TAG";
    private static final String TAG = APIRequest.class.getSimpleName();
    private static final String kApiActionBatch = "batch";
    public String accountsAction;
    private String accountsBody;
    private final OkHttpClient client = HttpClientManager.getInstance().getHttpClient();
    private HashMap<String, String> headerParams = new HashMap<>();
    private HttpType httpType;
    private Map<String, String> parameters;

    private enum HttpType {
        POST,
        GET,
        PUT
    }

    public static APIRequest sendEventsRequest(String str) {
        APIRequest aPIRequest = new APIRequest();
        aPIRequest.httpType = HttpType.PUT;
        aPIRequest.accountsAction = kApiActionBatch;
        aPIRequest.accountsBody = str;
        return aPIRequest;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v23, resolved type: okhttp3.RequestBody} */
    /* JADX WARNING: type inference failed for: r5v10, types: [okhttp3.RequestBody] */
    /* JADX WARNING: type inference failed for: r5v19 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.qxmd.eventssdkandroid.api.APIResponse send() {
        /*
            r9 = this;
            com.qxmd.eventssdkandroid.api.APIResponse r0 = new com.qxmd.eventssdkandroid.api.APIResponse
            r0.<init>()
            java.net.URL r1 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0186 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x0186 }
            r2.<init>()     // Catch:{ MalformedURLException -> 0x0186 }
            java.lang.String r3 = "https://events.service.qxmd.com/"
            r2.append(r3)     // Catch:{ MalformedURLException -> 0x0186 }
            java.lang.String r3 = r9.accountsAction     // Catch:{ MalformedURLException -> 0x0186 }
            r2.append(r3)     // Catch:{ MalformedURLException -> 0x0186 }
            java.lang.String r2 = r2.toString()     // Catch:{ MalformedURLException -> 0x0186 }
            r1.<init>(r2)     // Catch:{ MalformedURLException -> 0x0186 }
            r2 = 0
            r3 = 258(0x102, float:3.62E-43)
            okhttp3.Request$Builder r4 = new okhttp3.Request$Builder     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r4.<init>()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r5 = "x-qxmd-api-key"
            java.lang.String r6 = "iaobgWotkvgg6Rc1e1iEWk6hEAAY4V1y"
            r4.addHeader(r5, r6)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.util.HashMap<java.lang.String, java.lang.String> r5 = r9.headerParams     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.util.Set r5 = r5.entrySet()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
        L_0x0036:
            boolean r6 = r5.hasNext()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            if (r6 == 0) goto L_0x0052
            java.lang.Object r6 = r5.next()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.Object r7 = r6.getKey()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.Object r6 = r6.getValue()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r4.addHeader(r7, r6)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            goto L_0x0036
        L_0x0052:
            java.lang.String r5 = r9.accountsBody     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            if (r5 == 0) goto L_0x0063
            java.lang.String r5 = "application/json; charset=utf-8"
            okhttp3.MediaType r5 = okhttp3.MediaType.parse(r5)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r6 = r9.accountsBody     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            okhttp3.RequestBody r5 = okhttp3.RequestBody.create((okhttp3.MediaType) r5, (java.lang.String) r6)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            goto L_0x0064
        L_0x0063:
            r5 = r2
        L_0x0064:
            java.lang.String r6 = TAG     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r7.<init>()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r8 = "Request: "
            r7.append(r8)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r8 = r1.toString()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r7.append(r8)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r7 = r7.toString()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            com.qxmd.eventssdkandroid.util.Log.d((java.lang.String) r6, (java.lang.String) r7)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r4.url((java.net.URL) r1)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r6 = "OK_HTTP_TAG"
            r4.tag(r6)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            if (r5 == 0) goto L_0x009b
            com.qxmd.eventssdkandroid.api.APIRequest$HttpType r6 = r9.httpType     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            com.qxmd.eventssdkandroid.api.APIRequest$HttpType r7 = com.qxmd.eventssdkandroid.api.APIRequest.HttpType.POST     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            if (r6 != r7) goto L_0x0092
            r4.post(r5)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            goto L_0x009b
        L_0x0092:
            com.qxmd.eventssdkandroid.api.APIRequest$HttpType r6 = r9.httpType     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            com.qxmd.eventssdkandroid.api.APIRequest$HttpType r7 = com.qxmd.eventssdkandroid.api.APIRequest.HttpType.PUT     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            if (r6 != r7) goto L_0x009b
            r4.put(r5)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
        L_0x009b:
            java.lang.String r5 = TAG     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r6.<init>()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r7 = "requestBuilder url "
            r6.append(r7)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r7 = r4.toString()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r6.append(r7)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r6 = r6.toString()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            com.qxmd.eventssdkandroid.util.Log.d((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            okhttp3.Request r4 = r4.build()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            okhttp3.OkHttpClient r5 = r9.client     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            okhttp3.OkHttpClient$Builder r5 = r5.newBuilder()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r6 = 30000(0x7530, double:1.4822E-319)
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            okhttp3.OkHttpClient$Builder r5 = r5.connectTimeout(r6, r8)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            okhttp3.OkHttpClient r5 = r5.build()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            okhttp3.Call r4 = r5.newCall(r4)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            okhttp3.Response r2 = r4.execute()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            okhttp3.ResponseBody r4 = r2.body()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            if (r4 == 0) goto L_0x00e3
            okhttp3.ResponseBody r4 = r2.body()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r4 = r4.string()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r0.responseString = r4     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
        L_0x00e3:
            int r4 = r2.code()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r0.httpStatusCode = r4     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            int r4 = r0.httpStatusCode     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            int r4 = r4 / 100
            r5 = 2
            if (r4 == r5) goto L_0x00fe
            com.qxmd.eventssdkandroid.model.QxError r4 = new com.qxmd.eventssdkandroid.model.QxError     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            com.qxmd.eventssdkandroid.model.QxError$ErrorType r5 = com.qxmd.eventssdkandroid.model.QxError.ErrorType.HTML     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            int r6 = r0.httpStatusCode     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r7 = r0.responseString     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r4.<init>(r5, r6, r7)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r0.addError(r4)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
        L_0x00fe:
            java.lang.String r4 = TAG     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r5.<init>()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r6 = "Read Response: "
            r5.append(r6)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r1 = r1.toString()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r5.append(r1)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r1 = "; responseCode "
            r5.append(r1)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            int r1 = r0.httpStatusCode     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            r5.append(r1)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            java.lang.String r1 = r5.toString()     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            com.qxmd.eventssdkandroid.util.Log.d((java.lang.String) r4, (java.lang.String) r1)     // Catch:{ SocketTimeoutException -> 0x0147, IOException -> 0x012e }
            if (r2 == 0) goto L_0x0199
            okhttp3.ResponseBody r1 = r2.body()     // Catch:{ MalformedURLException -> 0x0186 }
        L_0x0128:
            r1.close()     // Catch:{ MalformedURLException -> 0x0186 }
            goto L_0x0199
        L_0x012c:
            r1 = move-exception
            goto L_0x017c
        L_0x012e:
            r1 = move-exception
            com.qxmd.eventssdkandroid.model.QxError r4 = new com.qxmd.eventssdkandroid.model.QxError     // Catch:{ all -> 0x012c }
            com.qxmd.eventssdkandroid.model.QxError$ErrorType r5 = com.qxmd.eventssdkandroid.model.QxError.ErrorType.NOT_SET     // Catch:{ all -> 0x012c }
            java.lang.String r6 = r1.getLocalizedMessage()     // Catch:{ all -> 0x012c }
            r4.<init>(r5, r3, r6)     // Catch:{ all -> 0x012c }
            r0.addError(r4)     // Catch:{ all -> 0x012c }
            r1.printStackTrace()     // Catch:{ all -> 0x012c }
            if (r2 == 0) goto L_0x0199
            okhttp3.ResponseBody r1 = r2.body()     // Catch:{ MalformedURLException -> 0x0186 }
            goto L_0x0128
        L_0x0147:
            r1 = move-exception
            java.lang.String r4 = TAG     // Catch:{ all -> 0x012c }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x012c }
            r5.<init>()     // Catch:{ all -> 0x012c }
            java.lang.String r6 = "Response parsed: timeout: "
            r5.append(r6)     // Catch:{ all -> 0x012c }
            java.lang.String r6 = r1.getMessage()     // Catch:{ all -> 0x012c }
            r5.append(r6)     // Catch:{ all -> 0x012c }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x012c }
            android.util.Log.d(r4, r5)     // Catch:{ all -> 0x012c }
            r0.httpStatusCode = r3     // Catch:{ all -> 0x012c }
            com.qxmd.eventssdkandroid.model.QxError r4 = new com.qxmd.eventssdkandroid.model.QxError     // Catch:{ all -> 0x012c }
            com.qxmd.eventssdkandroid.model.QxError$ErrorType r5 = com.qxmd.eventssdkandroid.model.QxError.ErrorType.HTML     // Catch:{ all -> 0x012c }
            java.lang.String r6 = r1.getLocalizedMessage()     // Catch:{ all -> 0x012c }
            r4.<init>(r5, r3, r6)     // Catch:{ all -> 0x012c }
            r0.addError(r4)     // Catch:{ all -> 0x012c }
            r1.printStackTrace()     // Catch:{ all -> 0x012c }
            if (r2 == 0) goto L_0x0199
            okhttp3.ResponseBody r1 = r2.body()     // Catch:{ MalformedURLException -> 0x0186 }
            goto L_0x0128
        L_0x017c:
            if (r2 == 0) goto L_0x0185
            okhttp3.ResponseBody r2 = r2.body()     // Catch:{ MalformedURLException -> 0x0186 }
            r2.close()     // Catch:{ MalformedURLException -> 0x0186 }
        L_0x0185:
            throw r1     // Catch:{ MalformedURLException -> 0x0186 }
        L_0x0186:
            r1 = move-exception
            com.qxmd.eventssdkandroid.model.QxError r2 = new com.qxmd.eventssdkandroid.model.QxError
            com.qxmd.eventssdkandroid.model.QxError$ErrorType r3 = com.qxmd.eventssdkandroid.model.QxError.ErrorType.NOT_SET
            r4 = 0
            java.lang.String r5 = r1.getLocalizedMessage()
            r2.<init>(r3, r4, r5)
            r0.addError(r2)
            r1.printStackTrace()
        L_0x0199:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qxmd.eventssdkandroid.api.APIRequest.send():com.qxmd.eventssdkandroid.api.APIResponse");
    }

    public int hashCode() {
        Map<String, String> map = this.parameters;
        return 31 + (map == null ? 0 : map.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        APIRequest aPIRequest = (APIRequest) obj;
        Map<String, String> map = this.parameters;
        if (map == null) {
            if (aPIRequest.parameters != null) {
                return false;
            }
        } else if (!map.equals(aPIRequest.parameters)) {
            return false;
        }
        return true;
    }
}

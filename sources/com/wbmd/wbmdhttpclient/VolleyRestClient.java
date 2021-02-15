package com.wbmd.wbmdhttpclient;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.ExecutorDelivery;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import java.io.File;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

public class VolleyRestClient implements IRestClient {
    /* access modifiers changed from: private */
    public static final String TAG = VolleyRestClient.class.getSimpleName();
    private static Context mContext;
    private static VolleyRestClient mInstance;
    /* access modifiers changed from: private */
    public static int mRedirectAttempts = 6;
    private static RequestQueue mRequestQueue;
    public HashMap<String, String> mStubbedNetworkResponses = new HashMap<>();

    static /* synthetic */ int access$010() {
        int i = mRedirectAttempts;
        mRedirectAttempts = i - 1;
        return i;
    }

    private VolleyRestClient(Context context) {
        mContext = context;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public static synchronized com.wbmd.wbmdhttpclient.VolleyRestClient getInstance(android.content.Context r2) {
        /*
            java.lang.Class<com.wbmd.wbmdhttpclient.VolleyRestClient> r0 = com.wbmd.wbmdhttpclient.VolleyRestClient.class
            monitor-enter(r0)
            r1 = 6
            mRedirectAttempts = r1     // Catch:{ all -> 0x0029 }
            android.content.Context r1 = mContext     // Catch:{ all -> 0x0029 }
            if (r1 != 0) goto L_0x000c
            mContext = r2     // Catch:{ all -> 0x0029 }
        L_0x000c:
            com.wbmd.wbmdhttpclient.VolleyRestClient r2 = mInstance     // Catch:{ all -> 0x0029 }
            if (r2 != 0) goto L_0x001f
            monitor-enter(r0)     // Catch:{ all -> 0x0029 }
            com.wbmd.wbmdhttpclient.VolleyRestClient r2 = new com.wbmd.wbmdhttpclient.VolleyRestClient     // Catch:{ all -> 0x001c }
            android.content.Context r1 = mContext     // Catch:{ all -> 0x001c }
            r2.<init>(r1)     // Catch:{ all -> 0x001c }
            mInstance = r2     // Catch:{ all -> 0x001c }
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            goto L_0x001f
        L_0x001c:
            r2 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            throw r2     // Catch:{ all -> 0x0029 }
        L_0x001f:
            com.android.volley.RequestQueue r2 = getRequestQueue()     // Catch:{ all -> 0x0029 }
            mRequestQueue = r2     // Catch:{ all -> 0x0029 }
            com.wbmd.wbmdhttpclient.VolleyRestClient r2 = mInstance     // Catch:{ all -> 0x0029 }
            monitor-exit(r0)
            return r2
        L_0x0029:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.wbmdhttpclient.VolleyRestClient.getInstance(android.content.Context):com.wbmd.wbmdhttpclient.VolleyRestClient");
    }

    public void post(String str, Map<String, Object> map, ICallbackEvent iCallbackEvent) {
        post(str, map, (Map<String, String>) null, iCallbackEvent);
    }

    public void post(String str, Map<String, Object> map, Map<String, String> map2, ICallbackEvent iCallbackEvent) {
        JSONObject jSONObject = new JSONObject();
        for (String next : map.keySet()) {
            try {
                jSONObject.put(next.toString(), map.get(next));
            } catch (JSONException e) {
                iCallbackEvent.onError(e);
            }
        }
        makeJsonObjectRequest(str, jSONObject, (Map<String, String>) null, iCallbackEvent);
    }

    public void post(String str, JSONObject jSONObject, ICallbackEvent iCallbackEvent) {
        post(str, jSONObject, (Map<String, String>) null, iCallbackEvent);
    }

    public void post(String str, JSONObject jSONObject, Map<String, String> map, ICallbackEvent iCallbackEvent) {
        makeJsonObjectRequest(str, jSONObject, map, iCallbackEvent);
    }

    public void get(String str, int i, ICallbackEvent iCallbackEvent) {
        get(str, i, (Map<String, String>) null, iCallbackEvent);
    }

    public void get(String str, int i, Map<String, String> map, ICallbackEvent iCallbackEvent) {
        if (i == 1) {
            makeJsonObjectRequest(str, (JSONObject) null, map, iCallbackEvent);
        } else if (i == 0) {
            makeStringRequest(str, iCallbackEvent, map);
        }
    }

    public JSONObject get(String str, Map<String, String> map) throws InterruptedException, ExecutionException, TimeoutException {
        RequestFuture newFuture = RequestFuture.newFuture();
        final Map<String, String> map2 = map;
        mRequestQueue.add(new JsonObjectRequest(str, (JSONObject) null, newFuture, newFuture) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                return map2;
            }
        });
        return (JSONObject) newFuture.get(60, TimeUnit.SECONDS);
    }

    public void flushQueue() {
        RequestQueue requestQueue = mRequestQueue;
        if (requestQueue != null) {
            requestQueue.cancelAll((Object) TAG);
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [com.android.volley.Request] */
    /* JADX WARNING: type inference failed for: r1v3, types: [com.wbmd.wbmdhttpclient.VolleyRestClient$7] */
    /* JADX WARNING: type inference failed for: r1v4, types: [com.wbmd.wbmdhttpclient.VolleyRestClient$4] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void makeJsonObjectRequest(java.lang.String r9, org.json.JSONObject r10, java.util.Map<java.lang.String, java.lang.String> r11, final com.wbmd.wbmdcommons.callbacks.ICallbackEvent<org.json.JSONObject, java.lang.Exception> r12) {
        /*
            r8 = this;
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.mStubbedNetworkResponses
            if (r0 == 0) goto L_0x003a
            boolean r0 = r0.containsKey(r9)
            if (r0 == 0) goto L_0x003a
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.mStubbedNetworkResponses     // Catch:{ JSONException -> 0x0036 }
            java.lang.Object r0 = r0.get(r9)     // Catch:{ JSONException -> 0x0036 }
            if (r0 == 0) goto L_0x003a
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.mStubbedNetworkResponses     // Catch:{ JSONException -> 0x0036 }
            java.lang.Object r0 = r0.get(r9)     // Catch:{ JSONException -> 0x0036 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ JSONException -> 0x0036 }
            boolean r0 = r0.isEmpty()     // Catch:{ JSONException -> 0x0036 }
            if (r0 != 0) goto L_0x003a
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0036 }
            java.util.HashMap<java.lang.String, java.lang.String> r2 = r8.mStubbedNetworkResponses     // Catch:{ JSONException -> 0x0036 }
            java.lang.Object r2 = r2.get(r9)     // Catch:{ JSONException -> 0x0036 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ JSONException -> 0x0036 }
            r0.<init>(r2)     // Catch:{ JSONException -> 0x0036 }
            r12.onCompleted(r0)     // Catch:{ JSONException -> 0x0036 }
            return
        L_0x0036:
            r0 = move-exception
            r0.printStackTrace()
        L_0x003a:
            if (r11 == 0) goto L_0x0057
            int r0 = r11.size()
            if (r0 <= 0) goto L_0x0057
            com.wbmd.wbmdhttpclient.VolleyRestClient$4 r0 = new com.wbmd.wbmdhttpclient.VolleyRestClient$4
            com.wbmd.wbmdhttpclient.VolleyRestClient$2 r5 = new com.wbmd.wbmdhttpclient.VolleyRestClient$2
            r5.<init>(r12)
            com.wbmd.wbmdhttpclient.VolleyRestClient$3 r6 = new com.wbmd.wbmdhttpclient.VolleyRestClient$3
            r6.<init>(r12)
            r1 = r0
            r2 = r8
            r3 = r9
            r4 = r10
            r7 = r11
            r1.<init>(r3, r4, r5, r6, r7)
            goto L_0x006a
        L_0x0057:
            com.wbmd.wbmdhttpclient.VolleyRestClient$7 r0 = new com.wbmd.wbmdhttpclient.VolleyRestClient$7
            com.wbmd.wbmdhttpclient.VolleyRestClient$5 r5 = new com.wbmd.wbmdhttpclient.VolleyRestClient$5
            r5.<init>(r12)
            com.wbmd.wbmdhttpclient.VolleyRestClient$6 r6 = new com.wbmd.wbmdhttpclient.VolleyRestClient$6
            r6.<init>(r12)
            r1 = r0
            r2 = r8
            r3 = r9
            r4 = r10
            r1.<init>(r3, r4, r5, r6)
        L_0x006a:
            com.android.volley.RequestQueue r1 = mRequestQueue
            r1.add(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.wbmdhttpclient.VolleyRestClient.makeJsonObjectRequest(java.lang.String, org.json.JSONObject, java.util.Map, com.wbmd.wbmdcommons.callbacks.ICallbackEvent):void");
    }

    private void makeStringRequest(String str, final ICallbackEvent iCallbackEvent) {
        HashMap<String, String> hashMap = this.mStubbedNetworkResponses;
        if (hashMap != null && hashMap.containsKey(str)) {
            new JSONObject();
            try {
                if (this.mStubbedNetworkResponses.get(str) != null && !this.mStubbedNetworkResponses.get(str).isEmpty()) {
                    iCallbackEvent.onCompleted(new JSONObject(this.mStubbedNetworkResponses.get(str)));
                    return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        new StringRequest(str, new Response.Listener<String>() {
            public void onResponse(String str) {
                iCallbackEvent.onCompleted(str);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError == null || volleyError.networkResponse == null) {
                    iCallbackEvent.onError(volleyError);
                } else if (volleyError.networkResponse.statusCode != 302 || VolleyRestClient.mRedirectAttempts <= 0) {
                    iCallbackEvent.onError(volleyError);
                } else {
                    VolleyRestClient.this.get(volleyError.networkResponse.headers.get("location"), 0, iCallbackEvent);
                    VolleyRestClient.access$010();
                }
            }
        });
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: com.wbmd.wbmdhttpclient.VolleyRestClient$12} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.android.volley.toolbox.StringRequest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: com.wbmd.wbmdhttpclient.VolleyRestClient$12} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.wbmd.wbmdhttpclient.VolleyRestClient$12} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void makeStringRequest(java.lang.String r8, final com.wbmd.wbmdcommons.callbacks.ICallbackEvent r9, java.util.Map<java.lang.String, java.lang.String> r10) {
        /*
            r7 = this;
            if (r10 == 0) goto L_0x001c
            int r0 = r10.size()
            if (r0 <= 0) goto L_0x001c
            com.wbmd.wbmdhttpclient.VolleyRestClient$12 r0 = new com.wbmd.wbmdhttpclient.VolleyRestClient$12
            com.wbmd.wbmdhttpclient.VolleyRestClient$10 r4 = new com.wbmd.wbmdhttpclient.VolleyRestClient$10
            r4.<init>(r9)
            com.wbmd.wbmdhttpclient.VolleyRestClient$11 r5 = new com.wbmd.wbmdhttpclient.VolleyRestClient$11
            r5.<init>(r9)
            r1 = r0
            r2 = r7
            r3 = r8
            r6 = r10
            r1.<init>(r3, r4, r5, r6)
            goto L_0x002b
        L_0x001c:
            com.android.volley.toolbox.StringRequest r0 = new com.android.volley.toolbox.StringRequest
            com.wbmd.wbmdhttpclient.VolleyRestClient$13 r10 = new com.wbmd.wbmdhttpclient.VolleyRestClient$13
            r10.<init>(r9)
            com.wbmd.wbmdhttpclient.VolleyRestClient$14 r1 = new com.wbmd.wbmdhttpclient.VolleyRestClient$14
            r1.<init>(r9)
            r0.<init>(r8, r10, r1)
        L_0x002b:
            com.android.volley.RequestQueue r8 = mRequestQueue
            r8.add(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.wbmdhttpclient.VolleyRestClient.makeStringRequest(java.lang.String, com.wbmd.wbmdcommons.callbacks.ICallbackEvent, java.util.Map):void");
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    private static synchronized com.android.volley.RequestQueue getRequestQueue() {
        /*
            java.lang.Class<com.wbmd.wbmdhttpclient.VolleyRestClient> r0 = com.wbmd.wbmdhttpclient.VolleyRestClient.class
            monitor-enter(r0)
            com.android.volley.RequestQueue r1 = mRequestQueue     // Catch:{ all -> 0x0017 }
            if (r1 != 0) goto L_0x0013
            monitor-enter(r0)     // Catch:{ all -> 0x0017 }
            com.android.volley.RequestQueue r1 = createRequestQueue()     // Catch:{ all -> 0x0010 }
            mRequestQueue = r1     // Catch:{ all -> 0x0010 }
            monitor-exit(r0)     // Catch:{ all -> 0x0010 }
            goto L_0x0013
        L_0x0010:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0010 }
            throw r1     // Catch:{ all -> 0x0017 }
        L_0x0013:
            com.android.volley.RequestQueue r1 = mRequestQueue     // Catch:{ all -> 0x0017 }
            monitor-exit(r0)
            return r1
        L_0x0017:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.wbmdhttpclient.VolleyRestClient.getRequestQueue():com.android.volley.RequestQueue");
    }

    private static RequestQueue createRequestQueue() {
        CookieHandler.setDefault(new CookieManager());
        File file = new File(mContext.getCacheDir(), "cache/volley");
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(file), new BasicNetwork(new HurlStack()), 4, new ExecutorDelivery((Executor) Executors.newSingleThreadExecutor()));
        requestQueue.start();
        return requestQueue;
    }
}

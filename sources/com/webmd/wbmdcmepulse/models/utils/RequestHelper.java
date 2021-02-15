package com.webmd.wbmdcmepulse.models.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.GsonRequest;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NoCache;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdproffesionalauthentication.providers.SharedPreferenceProvider;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestHelper {
    /* access modifiers changed from: private */
    public static final String TAG = RequestHelper.class.getSimpleName();
    /* access modifiers changed from: private */
    public static Context mContext;
    private static RequestHelper mInstance;
    private RequestQueue mRequestQueue = getRequestQueue();

    private RequestHelper(Context context) {
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
    public static synchronized com.webmd.wbmdcmepulse.models.utils.RequestHelper getInstance(android.content.Context r2) {
        /*
            java.lang.Class<com.webmd.wbmdcmepulse.models.utils.RequestHelper> r0 = com.webmd.wbmdcmepulse.models.utils.RequestHelper.class
            monitor-enter(r0)
            com.webmd.wbmdcmepulse.models.utils.RequestHelper r1 = mInstance     // Catch:{ all -> 0x0018 }
            if (r1 != 0) goto L_0x0014
            monitor-enter(r0)     // Catch:{ all -> 0x0018 }
            com.webmd.wbmdcmepulse.models.utils.RequestHelper r1 = new com.webmd.wbmdcmepulse.models.utils.RequestHelper     // Catch:{ all -> 0x0011 }
            r1.<init>(r2)     // Catch:{ all -> 0x0011 }
            mInstance = r1     // Catch:{ all -> 0x0011 }
            monitor-exit(r0)     // Catch:{ all -> 0x0011 }
            goto L_0x0014
        L_0x0011:
            r2 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0011 }
            throw r2     // Catch:{ all -> 0x0018 }
        L_0x0014:
            com.webmd.wbmdcmepulse.models.utils.RequestHelper r2 = mInstance     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)
            return r2
        L_0x0018:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.models.utils.RequestHelper.getInstance(android.content.Context):com.webmd.wbmdcmepulse.models.utils.RequestHelper");
    }

    public RequestQueue getRequestQueue() {
        if (this.mRequestQueue == null) {
            synchronized (RequestHelper.class) {
                this.mRequestQueue = newRequestQueue(mContext);
            }
        }
        return this.mRequestQueue;
    }

    public static RequestQueue newRequestQueue(Context context) {
        RequestQueue requestQueue = new RequestQueue(new NoCache(), new BasicNetwork(new HurlStack()));
        requestQueue.start();
        return requestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.mRequestQueue = requestQueue;
    }

    public void addJSONObjectRequest(String str, JSONObject jSONObject, final ICallbackEvent<JSONObject, String> iCallbackEvent) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(str, jSONObject, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject jSONObject) {
                ICallbackEvent iCallbackEvent = iCallbackEvent;
                if (iCallbackEvent != null) {
                    iCallbackEvent.onCompleted(jSONObject);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                ICallbackEvent iCallbackEvent = iCallbackEvent;
                if (iCallbackEvent != null) {
                    iCallbackEvent.onError(volleyError.toString());
                }
            }
        });
        jsonObjectRequest.setShouldCache(false);
        this.mRequestQueue.add(jsonObjectRequest);
    }

    public void addJSONObjectRequest(String str, JSONObject jSONObject, RetryPolicy retryPolicy, final ICallbackEvent<JSONObject, String> iCallbackEvent) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(str, jSONObject, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject jSONObject) {
                iCallbackEvent.onCompleted(jSONObject);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                iCallbackEvent.onError(volleyError.toString());
            }
        });
        jsonObjectRequest.setRetryPolicy(retryPolicy);
        jsonObjectRequest.setShouldCache(false);
        this.mRequestQueue.add(jsonObjectRequest);
    }

    public void addJSONArrayRequest(int i, String str, JSONArray jSONArray, final ICallbackEvent<JSONArray, String> iCallbackEvent) {
        AnonymousClass7 r0 = new JsonArrayRequest(i, str, jSONArray, new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray jSONArray) {
                iCallbackEvent.onCompleted(jSONArray);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                iCallbackEvent.onError(volleyError.toString());
            }
        }) {
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0015 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.util.Map<java.lang.String, java.lang.String> getHeaders() throws com.android.volley.AuthFailureError {
                /*
                    r4 = this;
                    java.util.Map r0 = super.getHeaders()
                    java.util.TreeMap r1 = new java.util.TreeMap
                    r1.<init>()
                    r1.putAll(r0)
                    com.webmd.wbmdcmepulse.models.utils.RequestHelper r0 = com.webmd.wbmdcmepulse.models.utils.RequestHelper.this     // Catch:{ Exception -> 0x0015 }
                    android.content.Context r2 = com.webmd.wbmdcmepulse.models.utils.RequestHelper.mContext     // Catch:{ Exception -> 0x0015 }
                    r0.addSessionCookie(r1, r2)     // Catch:{ Exception -> 0x0015 }
                L_0x0015:
                    android.content.ComponentName r0 = new android.content.ComponentName     // Catch:{ Exception -> 0x0034 }
                    android.content.Context r2 = com.webmd.wbmdcmepulse.models.utils.RequestHelper.mContext     // Catch:{ Exception -> 0x0034 }
                    java.lang.Class<com.webmd.wbmdcmepulse.models.utils.RequestHelper> r3 = com.webmd.wbmdcmepulse.models.utils.RequestHelper.class
                    r0.<init>(r2, r3)     // Catch:{ Exception -> 0x0034 }
                    android.content.Context r2 = com.webmd.wbmdcmepulse.models.utils.RequestHelper.mContext     // Catch:{ Exception -> 0x0034 }
                    android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ Exception -> 0x0034 }
                    java.lang.String r0 = r0.getPackageName()     // Catch:{ Exception -> 0x0034 }
                    r3 = 0
                    android.content.pm.PackageInfo r0 = r2.getPackageInfo(r0, r3)     // Catch:{ Exception -> 0x0034 }
                    java.lang.String r0 = r0.versionName     // Catch:{ Exception -> 0x0034 }
                    goto L_0x003a
                L_0x0034:
                    r0 = move-exception
                    r0.printStackTrace()
                    java.lang.String r0 = "1.0"
                L_0x003a:
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "CMEPulse/"
                    r2.append(r3)
                    r2.append(r0)
                    java.lang.String r0 = " "
                    r2.append(r0)
                    java.lang.String r0 = "http.agent"
                    java.lang.String r0 = java.lang.System.getProperty(r0)
                    r2.append(r0)
                    java.lang.String r0 = r2.toString()
                    java.lang.String r2 = "User-Agent"
                    r1.put(r2, r0)
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.models.utils.RequestHelper.AnonymousClass7.getHeaders():java.util.Map");
            }
        };
        r0.setShouldCache(false);
        r0.setTag(TAG);
        this.mRequestQueue.add(r0);
    }

    public void addRegistrationRequest(int i, String str, JSONObject jSONObject, final ICallbackEvent<JSONObject, String> iCallbackEvent) {
        AnonymousClass10 r0 = new JsonObjectRequest(i, str, jSONObject, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject jSONObject) {
                String str;
                if (jSONObject.optInt("status") == 1) {
                    iCallbackEvent.onCompleted(jSONObject);
                    return;
                }
                int optInt = jSONObject.optInt("errorCode", 0);
                if (optInt == 101) {
                    str = "Email address already registered.";
                } else if (optInt == 107 || optInt == 111) {
                    str = RequestHelper.mContext.getString(R.string.login_forgot_password_emailnotfound);
                } else {
                    str = jSONObject.optString("errorDescription");
                    if (Extensions.isStringNullOrEmpty(str)) {
                        str = "Error registering user";
                    }
                }
                iCallbackEvent.onError(str);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                iCallbackEvent.onError(volleyError.toString());
            }
        }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                String str;
                Map<String, String> headers = super.getHeaders();
                TreeMap treeMap = new TreeMap();
                treeMap.putAll(headers);
                treeMap.put("isEncrypted", "false");
                try {
                    str = RequestHelper.mContext.getPackageManager().getPackageInfo(new ComponentName(RequestHelper.mContext, RequestHelper.class).getPackageName(), 0).versionName;
                } catch (Exception e) {
                    e.printStackTrace();
                    str = "1.0";
                }
                treeMap.put("User-Agent", "CMEPulse/" + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + System.getProperty("http.agent"));
                return treeMap;
            }
        };
        r0.setShouldCache(false);
        r0.setTag(TAG);
        this.mRequestQueue.add(r0);
    }

    public void addJSONObjectRequest(int i, String str, JSONObject jSONObject, ICallbackEvent<JSONObject, String> iCallbackEvent) {
        final WeakReference weakReference = new WeakReference(iCallbackEvent);
        final ICallbackEvent<JSONObject, String> iCallbackEvent2 = iCallbackEvent;
        AnonymousClass13 r1 = new JsonObjectRequest(i, str, jSONObject, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject jSONObject) {
                if (weakReference.get() != null) {
                    ((ICallbackEvent) weakReference.get()).onCompleted(jSONObject);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String str;
                Resources resources;
                if (weakReference.get() != null) {
                    if (volleyError != null) {
                        str = volleyError.toString();
                        if (!volleyError.getClass().equals(NoConnectionError.class)) {
                            Resources resources2 = RequestHelper.mContext.getResources();
                            if (resources2 != null) {
                                str = resources2.getString(R.string.error_service_unavailable);
                            }
                        } else if (!(RequestHelper.mContext == null || (resources = RequestHelper.mContext.getResources()) == null)) {
                            str = resources.getString(R.string.error_connection_required);
                        }
                    } else {
                        str = "";
                    }
                    ((ICallbackEvent) weakReference.get()).onError(str);
                }
            }
        }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                String str;
                Map<String, String> headers = super.getHeaders();
                TreeMap treeMap = new TreeMap();
                treeMap.putAll(headers);
                try {
                    str = RequestHelper.mContext.getPackageManager().getPackageInfo(new ComponentName(RequestHelper.mContext, RequestHelper.class).getPackageName(), 0).versionName;
                } catch (Exception e) {
                    e.printStackTrace();
                    str = "1.0";
                }
                treeMap.put("User-Agent", "CMEPulse/" + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + System.getProperty("http.agent"));
                try {
                    RequestHelper.this.addSessionCookie(treeMap, RequestHelper.mContext);
                } catch (Exception e2) {
                    iCallbackEvent2.onError(e2.getMessage());
                }
                return treeMap;
            }
        };
        r1.setShouldCache(false);
        r1.setTag(TAG);
        this.mRequestQueue.add(r1);
    }

    public void addJSONObjectRequestNoCookie(int i, String str, JSONObject jSONObject, final ICallbackEvent<JSONObject, String> iCallbackEvent) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(i, str, jSONObject, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject jSONObject) {
                ICallbackEvent iCallbackEvent = iCallbackEvent;
                if (iCallbackEvent != null) {
                    iCallbackEvent.onCompleted(jSONObject);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                ICallbackEvent iCallbackEvent = iCallbackEvent;
                if (iCallbackEvent != null) {
                    iCallbackEvent.onError(volleyError.toString());
                }
            }
        });
        jsonObjectRequest.setShouldCache(false);
        jsonObjectRequest.setTag(TAG);
        this.mRequestQueue.add(jsonObjectRequest);
    }

    public <T> void addGSONObjectRequest(String str, Map<String, String> map, final ICallbackEvent iCallbackEvent, Class<T> cls) {
        final Map<String, String> map2 = map;
        AnonymousClass18 r0 = new GsonRequest<T>(str, cls, (Map) null, new Response.Listener<T>() {
            public void onResponse(T t) {
                ICallbackEvent iCallbackEvent = iCallbackEvent;
                if (iCallbackEvent != null) {
                    iCallbackEvent.onCompleted(t);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String str;
                Resources resources;
                if (iCallbackEvent != null) {
                    if (volleyError != null) {
                        str = volleyError.toString();
                        if (!volleyError.getClass().equals(NoConnectionError.class)) {
                            Resources resources2 = RequestHelper.mContext.getResources();
                            if (resources2 != null) {
                                str = resources2.getString(R.string.error_service_unavailable);
                            }
                        } else if (!(RequestHelper.mContext == null || (resources = RequestHelper.mContext.getResources()) == null)) {
                            str = resources.getString(R.string.error_connection_required);
                        }
                    } else {
                        str = "";
                    }
                    iCallbackEvent.onError(str);
                }
            }
        }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                String str;
                Map<String, String> headers = super.getHeaders();
                TreeMap treeMap = new TreeMap();
                treeMap.putAll(headers);
                try {
                    RequestHelper.this.addSessionCookie(treeMap, RequestHelper.mContext);
                    if (map2 != null) {
                        treeMap.putAll(map2);
                    }
                } catch (Exception e) {
                    Trace.e(RequestHelper.TAG, e.getMessage());
                }
                try {
                    str = RequestHelper.mContext.getPackageManager().getPackageInfo(new ComponentName(RequestHelper.mContext, RequestHelper.class).getPackageName(), 0).versionName;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    str = "1.0";
                }
                treeMap.put("User-Agent", "CMEPulse/" + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + System.getProperty("http.agent"));
                treeMap.put("content-contribType", "application/json; charset=utf-8");
                return treeMap;
            }
        };
        r0.setShouldCache(false);
        r0.setRetryPolicy(new DefaultRetryPolicy(5000, 1, 1.0f));
        r0.setTag(TAG);
        this.mRequestQueue.add(r0);
    }

    public void addStringRequest(int i, String str, Map<String, String> map, Map<String, String> map2, ICallbackEvent iCallbackEvent) {
        addStringRequest(i, str, map, map2, false, iCallbackEvent);
    }

    public void addStringRequest(int i, String str, Map<String, String> map, ICallbackEvent iCallbackEvent) {
        addStringRequest(i, str, (Map<String, String>) null, map, false, iCallbackEvent);
    }

    public synchronized void addStringRequest(int i, String str, boolean z, ICallbackEvent iCallbackEvent) {
        addStringRequest(i, str, (Map<String, String>) null, (Map<String, String>) null, z, iCallbackEvent);
    }

    public synchronized void addStringRequest(int i, String str, Map<String, String> map, Map<String, String> map2, boolean z, final ICallbackEvent iCallbackEvent) {
        final Map<String, String> map3 = map;
        final boolean z2 = z;
        AnonymousClass21 r0 = new StringRequest(i, str, new Response.Listener<String>() {
            public void onResponse(String str) {
                ICallbackEvent iCallbackEvent = iCallbackEvent;
                if (iCallbackEvent != null) {
                    iCallbackEvent.onCompleted(str);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String str;
                Resources resources;
                if (iCallbackEvent != null) {
                    if (volleyError != null) {
                        str = volleyError.toString();
                        if (!volleyError.getClass().equals(NoConnectionError.class)) {
                            Resources resources2 = RequestHelper.mContext.getResources();
                            if (resources2 != null) {
                                str = resources2.getString(R.string.error_service_unavailable);
                            }
                        } else if (!(RequestHelper.mContext == null || (resources = RequestHelper.mContext.getResources()) == null)) {
                            str = resources.getString(R.string.error_connection_required);
                        }
                    } else {
                        str = "";
                    }
                    iCallbackEvent.onError(str);
                }
            }
        }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                String str;
                Map<String, String> headers = super.getHeaders();
                TreeMap treeMap = new TreeMap();
                treeMap.putAll(headers);
                try {
                    str = RequestHelper.mContext.getPackageManager().getPackageInfo(new ComponentName(RequestHelper.mContext, RequestHelper.class).getPackageName(), 0).versionName;
                } catch (Exception e) {
                    e.printStackTrace();
                    str = "1.0";
                }
                treeMap.put("User-Agent", "CMEPulse/" + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + System.getProperty("http.agent"));
                try {
                    RequestHelper.this.addSessionCookie(treeMap, RequestHelper.mContext);
                    if (headers != null) {
                        treeMap.putAll(headers);
                    }
                } catch (Exception e2) {
                    Trace.e(RequestHelper.TAG, e2.getMessage());
                }
                return treeMap;
            }

            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                return map3;
            }

            /* access modifiers changed from: protected */
            public Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
                if (z2 && networkResponse.responseHeaders != null && networkResponse.responseHeaders.length > 0) {
                    RequestHelper.this.checkSessionCookie(networkResponse.responseHeaders);
                }
                return super.parseNetworkResponse(networkResponse);
            }
        };
        r0.setShouldCache(false);
        r0.setRetryPolicy(new DefaultRetryPolicy(5000, 1, 1.0f));
        r0.setTag(TAG);
        this.mRequestQueue.add(r0);
    }

    /* access modifiers changed from: private */
    public final void checkSessionCookie(Header[] headerArr) {
        try {
            StringBuilder sb = new StringBuilder();
            for (Header header : headerArr) {
                if (header.getName().equalsIgnoreCase("Set-Cookie")) {
                    sb.append(header.getValue() + ";");
                }
            }
            saveSessionCookie(sb.toString(), mContext);
        } catch (Exception unused) {
        }
    }

    public final void saveSessionCookie(String str, Context context) throws Exception {
        if (str.length() > 0) {
            SharedPreferenceProvider.get().saveCryptoEncrypted(Constants.PREF_COOKIE, str, context);
        }
    }

    /* access modifiers changed from: private */
    public final void addSessionCookie(Map<String, String> map, Context context) throws Exception {
        String cookieString = Utilities.getCookieString(context);
        if (!Extensions.isStringNullOrEmpty(cookieString) && !map.containsKey("Cookie")) {
            map.put("Cookie", cookieString);
        }
    }

    public void cancelPending() {
        this.mRequestQueue.cancelAll((Object) TAG);
    }
}

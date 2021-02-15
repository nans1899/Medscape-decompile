package com.facebook.applinks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppLinkData {
    private static final String APPLINK_BRIDGE_ARGS_KEY = "bridge_args";
    private static final String APPLINK_METHOD_ARGS_KEY = "method_args";
    private static final String APPLINK_VERSION_KEY = "version";
    public static final String ARGUMENTS_EXTRAS_KEY = "extras";
    public static final String ARGUMENTS_NATIVE_CLASS_KEY = "com.facebook.platform.APPLINK_NATIVE_CLASS";
    public static final String ARGUMENTS_NATIVE_URL = "com.facebook.platform.APPLINK_NATIVE_URL";
    public static final String ARGUMENTS_REFERER_DATA_KEY = "referer_data";
    public static final String ARGUMENTS_TAPTIME_KEY = "com.facebook.platform.APPLINK_TAP_TIME_UTC";
    private static final String BRIDGE_ARGS_METHOD_KEY = "method";
    private static final String BUNDLE_AL_APPLINK_DATA_KEY = "al_applink_data";
    static final String BUNDLE_APPLINK_ARGS_KEY = "com.facebook.platform.APPLINK_ARGS";
    private static final String DEFERRED_APP_LINK_ARGS_FIELD = "applink_args";
    private static final String DEFERRED_APP_LINK_CLASS_FIELD = "applink_class";
    private static final String DEFERRED_APP_LINK_CLICK_TIME_FIELD = "click_time";
    private static final String DEFERRED_APP_LINK_EVENT = "DEFERRED_APP_LINK";
    private static final String DEFERRED_APP_LINK_PATH = "%s/activities";
    private static final String DEFERRED_APP_LINK_URL_FIELD = "applink_url";
    private static final String EXTRAS_DEEPLINK_CONTEXT_KEY = "deeplink_context";
    private static final String METHOD_ARGS_REF_KEY = "ref";
    private static final String METHOD_ARGS_TARGET_URL_KEY = "target_url";
    private static final String PROMOTION_CODE_KEY = "promo_code";
    private static final String REFERER_DATA_REF_KEY = "fb_ref";
    private static final String TAG = AppLinkData.class.getCanonicalName();
    private Bundle argumentBundle;
    private JSONObject arguments;
    private String promotionCode;
    private String ref;
    private Uri targetUri;

    public interface CompletionHandler {
        void onDeferredAppLinkDataFetched(AppLinkData appLinkData);
    }

    public static void fetchDeferredAppLinkData(Context context, CompletionHandler completionHandler) {
        fetchDeferredAppLinkData(context, (String) null, completionHandler);
    }

    public static void fetchDeferredAppLinkData(Context context, final String str, final CompletionHandler completionHandler) {
        Validate.notNull(context, "context");
        Validate.notNull(completionHandler, "completionHandler");
        if (str == null) {
            str = Utility.getMetadataApplicationId(context);
        }
        Validate.notNull(str, "applicationId");
        final Context applicationContext = context.getApplicationContext();
        FacebookSdk.getExecutor().execute(new Runnable() {
            public void run() {
                AppLinkData.fetchDeferredAppLinkFromServer(applicationContext, str, completionHandler);
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        com.facebook.internal.Utility.logd(TAG, "Unable to put tap time in AppLinkData.arguments");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        com.facebook.internal.Utility.logd(TAG, "Unable to put tap time in AppLinkData.arguments");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        com.facebook.internal.Utility.logd(TAG, "Unable to put tap time in AppLinkData.arguments");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c7, code lost:
        com.facebook.internal.Utility.logd(TAG, "Unable to fetch deferred applink from server");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0089 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00a5 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00c1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void fetchDeferredAppLinkFromServer(android.content.Context r7, java.lang.String r8, com.facebook.applinks.AppLinkData.CompletionHandler r9) {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "event"
            java.lang.String r2 = "DEFERRED_APP_LINK"
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00d2 }
            com.facebook.internal.AttributionIdentifiers r1 = com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(r7)     // Catch:{ JSONException -> 0x00d2 }
            java.lang.String r2 = com.facebook.appevents.AppEventsLogger.getAnonymousAppDeviceGUID(r7)     // Catch:{ JSONException -> 0x00d2 }
            boolean r3 = com.facebook.FacebookSdk.getLimitEventAndDataUsage(r7)     // Catch:{ JSONException -> 0x00d2 }
            com.facebook.internal.Utility.setAppEventAttributionParameters(r0, r1, r2, r3)     // Catch:{ JSONException -> 0x00d2 }
            android.content.Context r1 = com.facebook.FacebookSdk.getApplicationContext()     // Catch:{ JSONException -> 0x00d2 }
            com.facebook.internal.Utility.setAppEventExtendedDeviceInfoParameters(r0, r1)     // Catch:{ JSONException -> 0x00d2 }
            java.lang.String r1 = "application_package_name"
            java.lang.String r7 = r7.getPackageName()     // Catch:{ JSONException -> 0x00d2 }
            r0.put(r1, r7)     // Catch:{ JSONException -> 0x00d2 }
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r1 = 0
            r7[r1] = r8
            java.lang.String r8 = "%s/activities"
            java.lang.String r7 = java.lang.String.format(r8, r7)
            r8 = 0
            com.facebook.GraphRequest r7 = com.facebook.GraphRequest.newPostRequest(r8, r7, r0, r8)     // Catch:{ Exception -> 0x00c7 }
            com.facebook.GraphResponse r7 = r7.executeAndWait()     // Catch:{ Exception -> 0x00c7 }
            org.json.JSONObject r7 = r7.getJSONObject()     // Catch:{ Exception -> 0x00c7 }
            if (r7 == 0) goto L_0x00ce
            java.lang.String r0 = "applink_args"
            java.lang.String r0 = r7.optString(r0)     // Catch:{ Exception -> 0x00c7 }
            java.lang.String r1 = "click_time"
            r2 = -1
            long r4 = r7.optLong(r1, r2)     // Catch:{ Exception -> 0x00c7 }
            java.lang.String r1 = "applink_class"
            java.lang.String r1 = r7.optString(r1)     // Catch:{ Exception -> 0x00c7 }
            java.lang.String r6 = "applink_url"
            java.lang.String r7 = r7.optString(r6)     // Catch:{ Exception -> 0x00c7 }
            boolean r6 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x00c7 }
            if (r6 != 0) goto L_0x00ce
            com.facebook.applinks.AppLinkData r8 = createFromJson(r0)     // Catch:{ Exception -> 0x00c7 }
            java.lang.String r0 = "Unable to put tap time in AppLinkData.arguments"
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x008e
            org.json.JSONObject r2 = r8.arguments     // Catch:{ JSONException -> 0x0089 }
            java.lang.String r3 = "com.facebook.platform.APPLINK_TAP_TIME_UTC"
            if (r2 == 0) goto L_0x007b
            org.json.JSONObject r2 = r8.arguments     // Catch:{ JSONException -> 0x0089 }
            r2.put(r3, r4)     // Catch:{ JSONException -> 0x0089 }
        L_0x007b:
            android.os.Bundle r2 = r8.argumentBundle     // Catch:{ JSONException -> 0x0089 }
            if (r2 == 0) goto L_0x008e
            android.os.Bundle r2 = r8.argumentBundle     // Catch:{ JSONException -> 0x0089 }
            java.lang.String r4 = java.lang.Long.toString(r4)     // Catch:{ JSONException -> 0x0089 }
            r2.putString(r3, r4)     // Catch:{ JSONException -> 0x0089 }
            goto L_0x008e
        L_0x0089:
            java.lang.String r2 = TAG     // Catch:{ Exception -> 0x00c7 }
            com.facebook.internal.Utility.logd((java.lang.String) r2, (java.lang.String) r0)     // Catch:{ Exception -> 0x00c7 }
        L_0x008e:
            if (r1 == 0) goto L_0x00aa
            org.json.JSONObject r2 = r8.arguments     // Catch:{ JSONException -> 0x00a5 }
            java.lang.String r3 = "com.facebook.platform.APPLINK_NATIVE_CLASS"
            if (r2 == 0) goto L_0x009b
            org.json.JSONObject r2 = r8.arguments     // Catch:{ JSONException -> 0x00a5 }
            r2.put(r3, r1)     // Catch:{ JSONException -> 0x00a5 }
        L_0x009b:
            android.os.Bundle r2 = r8.argumentBundle     // Catch:{ JSONException -> 0x00a5 }
            if (r2 == 0) goto L_0x00aa
            android.os.Bundle r2 = r8.argumentBundle     // Catch:{ JSONException -> 0x00a5 }
            r2.putString(r3, r1)     // Catch:{ JSONException -> 0x00a5 }
            goto L_0x00aa
        L_0x00a5:
            java.lang.String r1 = TAG     // Catch:{ Exception -> 0x00c7 }
            com.facebook.internal.Utility.logd((java.lang.String) r1, (java.lang.String) r0)     // Catch:{ Exception -> 0x00c7 }
        L_0x00aa:
            if (r7 == 0) goto L_0x00ce
            org.json.JSONObject r1 = r8.arguments     // Catch:{ JSONException -> 0x00c1 }
            java.lang.String r2 = "com.facebook.platform.APPLINK_NATIVE_URL"
            if (r1 == 0) goto L_0x00b7
            org.json.JSONObject r1 = r8.arguments     // Catch:{ JSONException -> 0x00c1 }
            r1.put(r2, r7)     // Catch:{ JSONException -> 0x00c1 }
        L_0x00b7:
            android.os.Bundle r1 = r8.argumentBundle     // Catch:{ JSONException -> 0x00c1 }
            if (r1 == 0) goto L_0x00ce
            android.os.Bundle r1 = r8.argumentBundle     // Catch:{ JSONException -> 0x00c1 }
            r1.putString(r2, r7)     // Catch:{ JSONException -> 0x00c1 }
            goto L_0x00ce
        L_0x00c1:
            java.lang.String r7 = TAG     // Catch:{ Exception -> 0x00c7 }
            com.facebook.internal.Utility.logd((java.lang.String) r7, (java.lang.String) r0)     // Catch:{ Exception -> 0x00c7 }
            goto L_0x00ce
        L_0x00c7:
            java.lang.String r7 = TAG
            java.lang.String r0 = "Unable to fetch deferred applink from server"
            com.facebook.internal.Utility.logd((java.lang.String) r7, (java.lang.String) r0)
        L_0x00ce:
            r9.onDeferredAppLinkDataFetched(r8)
            return
        L_0x00d2:
            r7 = move-exception
            com.facebook.FacebookException r8 = new com.facebook.FacebookException
            java.lang.String r9 = "An error occurred while preparing deferred app link"
            r8.<init>((java.lang.String) r9, (java.lang.Throwable) r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.applinks.AppLinkData.fetchDeferredAppLinkFromServer(android.content.Context, java.lang.String, com.facebook.applinks.AppLinkData$CompletionHandler):void");
    }

    public static AppLinkData createFromActivity(Activity activity) {
        Validate.notNull(activity, "activity");
        Intent intent = activity.getIntent();
        if (intent == null) {
            return null;
        }
        AppLinkData createFromAlApplinkData = createFromAlApplinkData(intent);
        if (createFromAlApplinkData == null) {
            createFromAlApplinkData = createFromJson(intent.getStringExtra(BUNDLE_APPLINK_ARGS_KEY));
        }
        return createFromAlApplinkData == null ? createFromUri(intent.getData()) : createFromAlApplinkData;
    }

    public static AppLinkData createFromAlApplinkData(Intent intent) {
        Bundle bundleExtra;
        String string;
        String string2;
        if (intent == null || (bundleExtra = intent.getBundleExtra(BUNDLE_AL_APPLINK_DATA_KEY)) == null) {
            return null;
        }
        AppLinkData appLinkData = new AppLinkData();
        Uri data = intent.getData();
        appLinkData.targetUri = data;
        if (data == null && (string2 = bundleExtra.getString(METHOD_ARGS_TARGET_URL_KEY)) != null) {
            appLinkData.targetUri = Uri.parse(string2);
        }
        appLinkData.argumentBundle = bundleExtra;
        appLinkData.arguments = null;
        Bundle bundle = bundleExtra.getBundle(ARGUMENTS_REFERER_DATA_KEY);
        if (bundle != null) {
            appLinkData.ref = bundle.getString(REFERER_DATA_REF_KEY);
        }
        Bundle bundle2 = bundleExtra.getBundle(ARGUMENTS_EXTRAS_KEY);
        if (!(bundle2 == null || (string = bundle2.getString("deeplink_context")) == null)) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                if (jSONObject.has("promo_code")) {
                    appLinkData.promotionCode = jSONObject.getString("promo_code");
                }
            } catch (JSONException e) {
                Utility.logd(TAG, "Unable to parse deeplink_context JSON", e);
            }
        }
        return appLinkData;
    }

    private static AppLinkData createFromJson(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("version");
            if (jSONObject.getJSONObject("bridge_args").getString("method").equals("applink") && string.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                AppLinkData appLinkData = new AppLinkData();
                JSONObject jSONObject2 = jSONObject.getJSONObject("method_args");
                appLinkData.arguments = jSONObject2;
                if (jSONObject2.has(METHOD_ARGS_REF_KEY)) {
                    appLinkData.ref = appLinkData.arguments.getString(METHOD_ARGS_REF_KEY);
                } else if (appLinkData.arguments.has(ARGUMENTS_REFERER_DATA_KEY)) {
                    JSONObject jSONObject3 = appLinkData.arguments.getJSONObject(ARGUMENTS_REFERER_DATA_KEY);
                    if (jSONObject3.has(REFERER_DATA_REF_KEY)) {
                        appLinkData.ref = jSONObject3.getString(REFERER_DATA_REF_KEY);
                    }
                }
                if (appLinkData.arguments.has(METHOD_ARGS_TARGET_URL_KEY)) {
                    appLinkData.targetUri = Uri.parse(appLinkData.arguments.getString(METHOD_ARGS_TARGET_URL_KEY));
                }
                if (appLinkData.arguments.has(ARGUMENTS_EXTRAS_KEY)) {
                    JSONObject jSONObject4 = appLinkData.arguments.getJSONObject(ARGUMENTS_EXTRAS_KEY);
                    if (jSONObject4.has("deeplink_context")) {
                        JSONObject jSONObject5 = jSONObject4.getJSONObject("deeplink_context");
                        if (jSONObject5.has("promo_code")) {
                            appLinkData.promotionCode = jSONObject5.getString("promo_code");
                        }
                    }
                }
                appLinkData.argumentBundle = toBundle(appLinkData.arguments);
                return appLinkData;
            }
        } catch (JSONException e) {
            Utility.logd(TAG, "Unable to parse AppLink JSON", e);
        } catch (FacebookException e2) {
            Utility.logd(TAG, "Unable to parse AppLink JSON", e2);
        }
        return null;
    }

    private static AppLinkData createFromUri(Uri uri) {
        if (uri == null) {
            return null;
        }
        AppLinkData appLinkData = new AppLinkData();
        appLinkData.targetUri = uri;
        return appLinkData;
    }

    private static Bundle toBundle(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONObject) {
                bundle.putBundle(next, toBundle((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj;
                int i = 0;
                if (jSONArray.length() == 0) {
                    bundle.putStringArray(next, new String[0]);
                } else {
                    Object obj2 = jSONArray.get(0);
                    if (obj2 instanceof JSONObject) {
                        Bundle[] bundleArr = new Bundle[jSONArray.length()];
                        while (i < jSONArray.length()) {
                            bundleArr[i] = toBundle(jSONArray.getJSONObject(i));
                            i++;
                        }
                        bundle.putParcelableArray(next, bundleArr);
                    } else if (!(obj2 instanceof JSONArray)) {
                        String[] strArr = new String[jSONArray.length()];
                        while (i < jSONArray.length()) {
                            strArr[i] = jSONArray.get(i).toString();
                            i++;
                        }
                        bundle.putStringArray(next, strArr);
                    } else {
                        throw new FacebookException("Nested arrays are not supported.");
                    }
                }
            } else {
                bundle.putString(next, obj.toString());
            }
        }
        return bundle;
    }

    private AppLinkData() {
    }

    public Uri getTargetUri() {
        return this.targetUri;
    }

    public String getRef() {
        return this.ref;
    }

    public String getPromotionCode() {
        return this.promotionCode;
    }

    public Bundle getArgumentBundle() {
        return this.argumentBundle;
    }

    public Bundle getRefererData() {
        Bundle bundle = this.argumentBundle;
        if (bundle != null) {
            return bundle.getBundle(ARGUMENTS_REFERER_DATA_KEY);
        }
        return null;
    }
}

package com.facebook.marketing.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.internal.AttributionIdentifiers;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class RemoteConfigManager {
    private static final String SAMPLING_ENDPOINT_PATH = "%s/button_auto_detection_device_selection";
    private static final String SAMPLING_RESULT_FIELD = "is_selected";
    private static final String TAG = RemoteConfigManager.class.getCanonicalName();
    private static final Map<String, RemoteConfig> remoteConfigs = new ConcurrentHashMap();

    public static void loadRemoteConfigAsync() {
        FacebookSdk.getExecutor().execute(new Runnable() {
            public void run() {
                JSONObject access$000;
                Context applicationContext = FacebookSdk.getApplicationContext();
                String applicationId = FacebookSdk.getApplicationId();
                AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(applicationContext);
                if ((attributionIdentifiers != null ? attributionIdentifiers.getAndroidAdvertiserId() : null) != null && (access$000 = RemoteConfigManager.getRemoteConfigQueryResponse(applicationId)) != null) {
                    RemoteConfigManager.parseRemoteConfigFromJSON(applicationId, access$000);
                }
            }
        });
    }

    public static RemoteConfig getRemoteConfigWithoutQuery(String str) {
        if (str != null) {
            return remoteConfigs.get(str);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static JSONObject getRemoteConfigQueryResponse(String str) {
        try {
            String format = String.format(Locale.US, SAMPLING_ENDPOINT_PATH, new Object[]{str});
            Bundle bundle = new Bundle();
            bundle.putString(GraphRequest.FIELDS_PARAM, SAMPLING_RESULT_FIELD);
            GraphRequest graphRequest = new GraphRequest((AccessToken) null, format, bundle, HttpMethod.GET, (GraphRequest.Callback) null);
            graphRequest.setSkipClientToken(true);
            return graphRequest.executeAndWait().getJSONObject();
        } catch (Exception e) {
            Log.e(TAG, "fail to request button sampling api", e);
            return new JSONObject();
        }
    }

    /* access modifiers changed from: private */
    public static void parseRemoteConfigFromJSON(String str, JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        if (optJSONArray != null && optJSONArray.length() > 0 && (optJSONObject = optJSONArray.optJSONObject(0)) != null) {
            remoteConfigs.put(str, new RemoteConfig(optJSONObject.optBoolean(SAMPLING_RESULT_FIELD, false)));
        }
    }
}

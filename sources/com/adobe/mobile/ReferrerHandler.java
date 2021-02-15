package com.adobe.mobile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import com.adobe.mobile.Config;
import com.adobe.mobile.StaticMethods;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import net.bytebuddy.description.type.TypeDescription;
import org.json.JSONException;
import org.json.JSONObject;

final class ReferrerHandler {
    static final String ACQUISITION_ADOBE_DATA_PREFIX = "adb";
    static final String ACQUISITION_GOOGLE_REFERRER_NAMESPACE = "a.referrer.campaign.";
    static final String ACQUISITION_REFERRER_DATA_NAMESPACE = "a.acquisition.custom.";
    static final String ACQUISITION_V3_TOKEN = "adb_acq_v3";
    static final HashSet<String> ADOBE_DATA_BLACKLISTED_NAMESPACED_KEYS_SET = new HashSet<String>() {
        {
            add("a.acquisition.custom.unique_id");
            add("a.acquisition.custom.deeplinkid");
            add("a.acquisition.custom.link_deferred");
        }
    };
    static final String ADOBE_DATA_KEY = "adobeData";
    static final String CONTEXT_DATA_KEY = "contextData";
    static final String DEEP_LINK_KEY = "link_deferred";
    static final String GOOGLE_REFERRER_DATA_KEY = "googleReferrerData";
    static final String OTHER_REFERRER_DATA_KEY = "otherReferrerData";
    private static boolean _referrerProcessed = true;

    ReferrerHandler() {
    }

    protected static void setReferrerProcessed(boolean z) {
        _referrerProcessed = z;
    }

    protected static boolean getReferrerProcessed() {
        return _referrerProcessed;
    }

    public static void processIntent(Intent intent) {
        String referrerURLFromIntent = getReferrerURLFromIntent(intent);
        if (referrerURLFromIntent == null || referrerURLFromIntent.length() == 0) {
            StaticMethods.logDebugFormat("Analytics - Ignoring referrer due to the intent's referrer string being empty", new Object[0]);
            return;
        }
        StaticMethods.logDebugFormat("Analytics - Received referrer information(%s)", referrerURLFromIntent);
        HashMap<String, Object> parseReferrerURLToMap = parseReferrerURLToMap(referrerURLFromIntent);
        if (isV3Response(parseReferrerURLToMap)) {
            handleV3Acquisition(parseReferrerURLToMap);
        } else {
            handleGooglePlayAcquisition(parseReferrerURLToMap);
        }
    }

    protected static boolean isBlackListed(String str) {
        return ADOBE_DATA_BLACKLISTED_NAMESPACED_KEYS_SET.contains(str);
    }

    protected static String getReferrerURLFromIntent(Intent intent) {
        if (intent == null) {
            StaticMethods.logWarningFormat("Analytics - Unable to process referrer due to an invalid intent parameter", new Object[0]);
            return null;
        } else if (!intent.getAction().equals("com.android.vending.INSTALL_REFERRER")) {
            StaticMethods.logDebugFormat("Analytics - Ignoring referrer due to the intent's action not being handled by analytics", new Object[0]);
            return null;
        } else {
            try {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    extras.containsKey((String) null);
                }
                String stringExtra = intent.getStringExtra("referrer");
                if (stringExtra == null) {
                    return null;
                }
                try {
                    return URLDecoder.decode(stringExtra, "UTF-8");
                } catch (Exception unused) {
                    return stringExtra;
                }
            } catch (Exception unused2) {
                return null;
            }
        }
    }

    protected static HashMap<String, Object> parseReferrerURLToMap(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        for (String split : str.split("&")) {
            String[] split2 = split.split("=");
            if (split2.length == 2) {
                hashMap.put(split2[0], split2[1]);
            }
        }
        return hashMap;
    }

    protected static void handleGooglePlayAcquisition(HashMap<String, Object> hashMap) {
        boolean z;
        try {
            z = StaticMethods.getSharedPreferences().contains("ADMS_Referrer_ContextData_Json_String");
        } catch (StaticMethods.NullContextException unused) {
            StaticMethods.logDebugFormat("Analytics - Error reading referrer info from preferences (%s)", new Object[0]);
            z = false;
        }
        if (!z) {
            final HashMap<String, Object> appendNamespaceToGoogleReferrerFields = appendNamespaceToGoogleReferrerFields(hashMap);
            if (Lifecycle.lifecycleHasRun && appendNamespaceToGoogleReferrerFields.containsKey("a.referrer.campaign.source") && appendNamespaceToGoogleReferrerFields.containsKey("a.referrer.campaign.name")) {
                StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
                    public void run() {
                        Lifecycle.updateContextData(appendNamespaceToGoogleReferrerFields);
                    }
                });
                MobileConfig.getInstance().invokeAdobeDataCallback(Config.MobileDataEvent.MOBILE_EVENT_ACQUISITION_INSTALL, appendNamespaceToGoogleReferrerFields);
                AnalyticsWorker.sharedInstance().kickWithReferrerData(appendNamespaceToGoogleReferrerFields);
            }
            try {
                SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(GOOGLE_REFERRER_DATA_KEY, new JSONObject(appendNamespaceToGoogleReferrerFields));
                sharedPreferencesEditor.putString("ADMS_Referrer_ContextData_Json_String", jSONObject.toString());
                sharedPreferencesEditor.commit();
            } catch (StaticMethods.NullContextException e) {
                StaticMethods.logErrorFormat("Analytics - Error persisting referrer data (%s)", e.getMessage());
            } catch (JSONException e2) {
                StaticMethods.logErrorFormat("Analytics - Error persisting referrer data (%s)", e2.getMessage());
            }
            _referrerProcessed = true;
        }
    }

    protected static HashMap<String, Object> appendNamespaceToGoogleReferrerFields(HashMap<String, Object> hashMap) {
        HashMap<String, Object> hashMap2 = new HashMap<>();
        if (!(hashMap == null || hashMap.size() == 0)) {
            HashMap hashMap3 = new HashMap(hashMap);
            if (hashMap3.containsKey("utm_campaign")) {
                hashMap2.put("a.referrer.campaign.name", hashMap3.remove("utm_campaign"));
            }
            if (hashMap3.containsKey("utm_source")) {
                hashMap2.put("a.referrer.campaign.source", hashMap3.remove("utm_source"));
            }
            if (hashMap3.containsKey("utm_medium")) {
                hashMap2.put("a.referrer.campaign.medium", hashMap3.remove("utm_medium"));
            }
            if (hashMap3.containsKey("utm_term")) {
                hashMap2.put("a.referrer.campaign.term", hashMap3.remove("utm_term"));
            }
            if (hashMap3.containsKey("utm_content")) {
                hashMap2.put("a.referrer.campaign.content", hashMap3.remove("utm_content"));
            }
            if (hashMap3.containsKey("trackingcode")) {
                hashMap2.put("a.referrer.campaign.trackingcode", hashMap3.remove("trackingcode"));
            }
            for (Map.Entry entry : hashMap3.entrySet()) {
                String str = (String) entry.getKey();
                if (!str.startsWith(ACQUISITION_REFERRER_DATA_NAMESPACE) && !str.startsWith(ACQUISITION_GOOGLE_REFERRER_NAMESPACE)) {
                    str = ACQUISITION_REFERRER_DATA_NAMESPACE + str;
                }
                hashMap2.put(str, entry.getValue());
            }
        }
        return hashMap2;
    }

    protected static HashMap<String, Object> parseGoogleReferrerFields(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (str == null) {
            return hashMap;
        }
        try {
            hashMap.putAll(getDataFromJSON(new JSONObject(str), GOOGLE_REFERRER_DATA_KEY));
        } catch (JSONException e) {
            StaticMethods.logDebugFormat("Could not retrieve Google referrer data (%s)", e.getMessage());
            hashMap.clear();
        }
        return hashMap;
    }

    protected static void saveReferrerDataFromOtherSource(Map<String, Object> map) {
        if (map != null && map.size() != 0) {
            try {
                SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(OTHER_REFERRER_DATA_KEY, new JSONObject(map));
                sharedPreferencesEditor.putString("ADMS_Referrer_ContextData_Json_String", jSONObject.toString());
                sharedPreferencesEditor.commit();
            } catch (StaticMethods.NullContextException e) {
                StaticMethods.logErrorFormat("Analytics - Error persisting referrer data (%s)", e.getMessage());
            } catch (JSONException e2) {
                StaticMethods.logErrorFormat("Analytics - Error persisting referrer data (%s)", e2.getMessage());
            }
        }
    }

    protected static HashMap<String, Object> parseOtherReferrerFields(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (str == null) {
            return hashMap;
        }
        try {
            hashMap.putAll(getDataFromJSON(new JSONObject(str), OTHER_REFERRER_DATA_KEY));
        } catch (JSONException e) {
            StaticMethods.logDebugFormat("Could not retrieve referrer data (%s)", e.getMessage());
            hashMap.clear();
        }
        return hashMap;
    }

    protected static String processReferrerDataFromV3Server(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            HashMap<String, Object> dataFromJSON = getDataFromJSON(jSONObject, ADOBE_DATA_KEY);
            HashMap hashMap = new HashMap();
            if (dataFromJSON == null || dataFromJSON.size() <= 0) {
                return str;
            }
            for (Map.Entry next : dataFromJSON.entrySet()) {
                String str2 = (String) next.getKey();
                if (!str2.startsWith(ACQUISITION_REFERRER_DATA_NAMESPACE)) {
                    str2 = ACQUISITION_REFERRER_DATA_NAMESPACE + str2;
                }
                hashMap.put(str2, next.getValue());
            }
            jSONObject.remove(ADOBE_DATA_KEY);
            jSONObject.put(ADOBE_DATA_KEY, new JSONObject(hashMap));
            return jSONObject.toString();
        } catch (JSONException e) {
            StaticMethods.logDebugFormat("Could not parse adobeData from the response (%s)", e.getMessage());
            return str;
        }
    }

    protected static HashMap<String, Object> processV3ResponseAndReturnAdobeData(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            HashMap<String, Object> dataFromJSON = getDataFromJSON(new JSONObject(str), ADOBE_DATA_KEY);
            if (dataFromJSON != null && dataFromJSON.size() > 0) {
                hashMap.putAll(filterForBlacklistedKeys(dataFromJSON));
            }
        } catch (JSONException e) {
            StaticMethods.logDebugFormat("Could not parse adobeData from the response (%s)", e.getMessage());
        }
        return hashMap;
    }

    protected static HashMap<String, Object> filterForBlacklistedKeys(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            return null;
        }
        HashMap<String, Object> hashMap2 = new HashMap<>(hashMap);
        Iterator<Map.Entry<String, Object>> it = hashMap2.entrySet().iterator();
        while (it.hasNext()) {
            if (isBlackListed((String) it.next().getKey())) {
                it.remove();
            }
        }
        return hashMap2;
    }

    protected static void handleV3Acquisition(HashMap<String, Object> hashMap) {
        String processReferrerDataFromV3Server = processReferrerDataFromV3Server(getReferrerDataFromV3Server((String) hashMap.get("utm_content"), StaticMethods.getAdvertisingIdentifier()));
        if (Lifecycle.lifecycleHasRun) {
            if (_referrerProcessed) {
                StaticMethods.logDebugFormat("Analytics - Acquisition referrer timed out", new Object[0]);
                return;
            }
            final HashMap hashMap2 = new HashMap();
            JSONObject translateV3StringResponseToJSONObject = translateV3StringResponseToJSONObject(processReferrerDataFromV3Server);
            triggerDeepLink(getDeepLinkFromJSON(translateV3StringResponseToJSONObject));
            HashMap<String, Object> dataFromJSON = getDataFromJSON(translateV3StringResponseToJSONObject, CONTEXT_DATA_KEY);
            hashMap2.putAll(dataFromJSON);
            if (dataFromJSON != null && dataFromJSON.size() > 0) {
                hashMap2.putAll(processV3ResponseAndReturnAdobeData(processReferrerDataFromV3Server));
            }
            MobileConfig.getInstance().invokeAdobeDataCallback(Config.MobileDataEvent.MOBILE_EVENT_ACQUISITION_INSTALL, hashMap2);
            StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
                public void run() {
                    Lifecycle.updateContextData(hashMap2);
                }
            });
            AnalyticsWorker.sharedInstance().kickWithReferrerData(hashMap2);
        }
        try {
            SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
            sharedPreferencesEditor.putString("ADMS_Referrer_ContextData_Json_String", processReferrerDataFromV3Server);
            sharedPreferencesEditor.commit();
        } catch (StaticMethods.NullContextException e) {
            StaticMethods.logErrorFormat("Analytics - Error persisting referrer data (%s)", e.getMessage());
        }
        _referrerProcessed = true;
    }

    protected static boolean isV3Response(HashMap<String, Object> hashMap) {
        return ACQUISITION_V3_TOKEN.equals(hashMap.get("utm_source")) && ACQUISITION_V3_TOKEN.equals(hashMap.get("utm_campaign"));
    }

    protected static HashMap<String, Object> processV3ResponseAndReturnContextData(String str) {
        return getDataFromJSON(translateV3StringResponseToJSONObject(str), CONTEXT_DATA_KEY);
    }

    protected static void triggerDeepLink(String str) {
        if (str != null) {
            try {
                Activity currentActivity = StaticMethods.getCurrentActivity();
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                currentActivity.startActivity(intent);
            } catch (StaticMethods.NullActivityException e) {
                StaticMethods.logWarningFormat(e.getMessage(), new Object[0]);
            } catch (Exception e2) {
                StaticMethods.logWarningFormat("Acquisition - Could not load deep link intent for Acquisition (%s)", e2.toString());
            }
        }
    }

    protected static HashMap<String, Object> parseV3ContextDataFromResponse(String str) {
        return getDataFromJSON(translateV3StringResponseToJSONObject(str), CONTEXT_DATA_KEY);
    }

    protected static JSONObject translateV3StringResponseToJSONObject(String str) {
        if (!(str == null || str.length() == 0)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                try {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(CONTEXT_DATA_KEY);
                    if (jSONObject2 == null) {
                        StaticMethods.logDebugFormat("Analytics - Unable to parse acquisition service response (no contextData parameter in response)", new Object[0]);
                        return null;
                    } else if (!jSONObject2.has("a.referrer.campaign.name")) {
                        StaticMethods.logDebugFormat("Analytics - Acquisition referrer data was not complete (no a.referrer.campaign.name in context data), ignoring", new Object[0]);
                        return null;
                    } else {
                        StaticMethods.logDebugFormat("Analytics - Received Referrer Data(%s)", str);
                        return jSONObject;
                    }
                } catch (JSONException unused) {
                    StaticMethods.logDebugFormat("Analytics - Unable to parse acquisition service response (no contextData parameter in response)", new Object[0]);
                    return null;
                }
            } catch (JSONException e) {
                StaticMethods.logDebugFormat("Analytics - Unable to parse response(%s)", e.getLocalizedMessage());
            }
        }
        return null;
    }

    protected static HashMap<String, Object> getDataFromJSON(JSONObject jSONObject, String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (jSONObject == null) {
            return hashMap;
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            if (jSONObject2 == null) {
                return hashMap;
            }
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String obj = keys.next().toString();
                try {
                    hashMap.put(obj, jSONObject2.getString(obj));
                } catch (JSONException unused) {
                    StaticMethods.logDebugFormat("Analytics - Unable to parse acquisition service response (the value for %s is not a string)", obj);
                }
            }
            return hashMap;
        } catch (JSONException unused2) {
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.lang.String getDeepLinkFromJSON(org.json.JSONObject r2) {
        /*
            r0 = 0
            if (r2 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "adobeData"
            org.json.JSONObject r2 = r2.getJSONObject(r1)     // Catch:{ JSONException -> 0x0014 }
            if (r2 != 0) goto L_0x000d
            return r0
        L_0x000d:
            java.lang.String r1 = "a.acquisition.custom.link_deferred"
            java.lang.String r2 = r2.getString(r1)     // Catch:{  }
            return r2
        L_0x0014:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.ReferrerHandler.getDeepLinkFromJSON(org.json.JSONObject):java.lang.String");
    }

    protected static String getReferrerDataFromV3Server(String str, String str2) {
        if (!MobileConfig.getInstance().mobileReferrerConfigured()) {
            return null;
        }
        String generateURLForV3 = generateURLForV3(str, str2);
        StaticMethods.logDebugFormat("Analytics - Trying to fetch referrer data from (%s)", generateURLForV3);
        byte[] retrieveData = RequestHandler.retrieveData(generateURLForV3, (Map<String, String>) null, MobileConfig.getInstance().getReferrerTimeout(), "Analytics");
        if (retrieveData == null) {
            return null;
        }
        try {
            return new String(retrieveData, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            StaticMethods.logErrorFormat("Analytics - Unable to decode response(%s)", e.getLocalizedMessage());
            return null;
        }
    }

    protected static String generateURLForV3(String str, String str2) {
        StringBuilder sb = new StringBuilder(64);
        MobileConfig instance = MobileConfig.getInstance();
        sb.append(String.format("https://%s/v3/%s/end", new Object[]{instance.getAcquisitionServer(), instance.getAcquisitionAppId()}));
        StringBuilder sb2 = new StringBuilder(64);
        if (str != null && str.length() > 0) {
            sb2.append(String.format("?a_ugid=%s", new Object[]{StaticMethods.URLEncode(str)}));
        }
        if (str2 != null && str2.length() > 0) {
            sb2.append(sb2.length() > 0 ? "&" : TypeDescription.Generic.OfWildcardType.SYMBOL);
            sb2.append(String.format("a_cid=%s", new Object[]{StaticMethods.URLEncode(str2)}));
        }
        sb.append(sb2);
        return sb.toString();
    }
}

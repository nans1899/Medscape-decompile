package com.adobe.mobile;

import com.appboy.Constants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

final class RequestBuilder {
    private static final String APP_STATE_BACKGROUND = "background";
    private static final String APP_STATE_FOREGROUND = "foreground";
    private static final String CUSTOMER_PERSPECTIVE_VAR = "cp";
    private static final String PRIVACY_MODE_KEY = "a.privacy.mode";
    private static final String VAR_ESCAPE_PREFIX = "&&";

    RequestBuilder() {
    }

    protected static void buildAndSendRequest(Map<String, Object> map, Map<String, Object> map2, long j) {
        HashMap hashMap;
        if (WearableFunctionBridge.shouldSendHit()) {
            HashMap hashMap2 = new HashMap();
            hashMap2.putAll(StaticMethods.getDefaultData());
            long timeSinceLaunch = StaticMethods.getTimeSinceLaunch();
            if (timeSinceLaunch > 0) {
                hashMap2.put("a.TimeSinceLaunch", String.valueOf(timeSinceLaunch));
            }
            if (map != null) {
                hashMap2.putAll(map);
            }
            if (MobileConfig.getInstance().getPrivacyStatus() == MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_UNKNOWN) {
                hashMap2.put(PRIVACY_MODE_KEY, "unknown");
            }
            if (map2 == null) {
                hashMap = new HashMap();
            }
            if (StaticMethods.getAID() != null) {
                hashMap.put("aid", StaticMethods.getAID());
            }
            if (StaticMethods.getVisitorID() != null) {
                hashMap.put("vid", StaticMethods.getVisitorID());
            }
            hashMap.put("ce", MobileConfig.getInstance().getCharacterSet());
            if (MobileConfig.getInstance().getOfflineTrackingEnabled()) {
                hashMap.put("ts", Long.toString(j));
            }
            hashMap.put(Constants.APPBOY_PUSH_TITLE_KEY, StaticMethods.getTimestampString());
            hashMap.put(CUSTOMER_PERSPECTIVE_VAR, Lifecycle.applicationIsInBackground() ? APP_STATE_BACKGROUND : APP_STATE_FOREGROUND);
            Iterator it = hashMap2.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String str = (String) entry.getKey();
                if (str == null) {
                    it.remove();
                } else if (str.startsWith(VAR_ESCAPE_PREFIX)) {
                    hashMap.put(str.substring(2), entry.getValue());
                    it.remove();
                }
            }
            if (MobileConfig.getInstance().getVisitorIdServiceEnabled()) {
                hashMap.putAll(VisitorIDService.sharedInstance().getAnalyticsIdVisitorParameters());
            }
            Messages.checkForInAppMessage(new HashMap(hashMap), new HashMap(hashMap2), new HashMap(Lifecycle.getContextDataLowercase()));
            Messages.checkFor3rdPartyCallbacks(new HashMap(hashMap), new HashMap(hashMap2));
            hashMap.put("c", StaticMethods.translateContextData(hashMap2));
            if (MobileConfig.getInstance().getMarketingCloudCoopUnsafe() && MobileConfig.getInstance().getAamAnalyticsForwardingEnabled()) {
                ContextData contextData = new ContextData();
                ContextData contextData2 = new ContextData();
                contextData2.value = 1;
                contextData.put("coop_unsafe", contextData2);
                hashMap.put(Constants.APPBOY_PUSH_NOTIFICATION_SOUND_DEFAULT_VALUE, contextData);
            }
            StringBuilder sb = new StringBuilder(2048);
            sb.append("ndh=1");
            if (MobileConfig.getInstance().getVisitorIdServiceEnabled()) {
                sb.append(VisitorIDService.sharedInstance().getAnalyticsIdString());
            }
            StaticMethods.serializeToQueryString(hashMap, sb);
            StaticMethods.logDebugFormat("Analytics - Request Queued (%s)", sb);
            AnalyticsWorker.sharedInstance().queue(sb.toString(), j);
        }
    }
}

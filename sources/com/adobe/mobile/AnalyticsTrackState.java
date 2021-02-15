package com.adobe.mobile;

import java.util.HashMap;
import java.util.Map;

final class AnalyticsTrackState {
    AnalyticsTrackState() {
    }

    protected static void trackState(String str, Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        if (str == null || str.length() <= 0) {
            str = StaticMethods.getApplicationID();
        }
        hashMap.put("pageName", str);
        RequestBuilder.buildAndSendRequest(map, hashMap, StaticMethods.getTimeSince1970());
    }
}

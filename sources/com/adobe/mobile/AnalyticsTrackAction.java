package com.adobe.mobile;

import java.util.HashMap;
import java.util.Map;

final class AnalyticsTrackAction {
    AnalyticsTrackAction() {
    }

    protected static void trackAction(String str, Map<String, Object> map) {
        HashMap hashMap;
        if (map == null) {
            hashMap = new HashMap();
        }
        if (str == null) {
            str = "";
        }
        hashMap.put("a.action", str);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("pe", "lnk_o");
        hashMap2.put("pev2", "AMACTION:" + str);
        hashMap2.put("pageName", StaticMethods.getApplicationID());
        RequestBuilder.buildAndSendRequest(hashMap, hashMap2, StaticMethods.getTimeSince1970());
    }
}

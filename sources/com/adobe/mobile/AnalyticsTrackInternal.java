package com.adobe.mobile;

import java.util.HashMap;
import java.util.Map;

final class AnalyticsTrackInternal {
    AnalyticsTrackInternal() {
    }

    protected static void trackInternal(String str, Map<String, Object> map) {
        trackInternal(str, map, StaticMethods.getTimeSince1970());
    }

    protected static void trackInternal(String str, Map<String, Object> map, long j) {
        HashMap hashMap;
        if (map == null) {
            hashMap = new HashMap();
        }
        hashMap.put("a.internalaction", str != null ? str : "None");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("pe", "lnk_o");
        StringBuilder sb = new StringBuilder();
        sb.append("ADBINTERNAL:");
        if (str == null) {
            str = "None";
        }
        sb.append(str);
        hashMap2.put("pev2", sb.toString());
        hashMap2.put("pageName", StaticMethods.getApplicationID());
        RequestBuilder.buildAndSendRequest(hashMap, hashMap2, j);
    }
}

package com.medscape.android.capabilities.parser;

import com.medscape.android.Constants;
import com.medscape.android.capabilities.models.CapabilitiesFeature;
import com.medscape.android.util.JSONUtils;
import java.util.HashMap;
import java.util.Map;

public class CapabilitiesParser {
    public static Map<String, CapabilitiesFeature> parseCapabilitiesResponse(String str) {
        Map<String, Object> mapFromJson;
        if (str == null || str.trim().equalsIgnoreCase("") || (mapFromJson = JSONUtils.getMapFromJson(str)) == null || mapFromJson.size() <= 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String next : mapFromJson.keySet()) {
            Object obj = mapFromJson.get(next);
            if (obj != null && (obj instanceof Map)) {
                hashMap.put(next, createCapabilitiesFeatureFromJson((Map) obj));
            }
        }
        return hashMap;
    }

    private static CapabilitiesFeature createCapabilitiesFeatureFromJson(Map<String, Object> map) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        CapabilitiesFeature capabilitiesFeature = new CapabilitiesFeature();
        if (map.containsKey(Constants.CAPABILITIES_EULA_URL) && (obj4 = map.get(Constants.CAPABILITIES_EULA_URL)) != null && (obj4 instanceof String)) {
            capabilitiesFeature.setEulaUrl((String) obj4);
        }
        if (map.containsKey(Constants.CAPABILITIES_EULA_VERSION) && (obj3 = map.get(Constants.CAPABILITIES_EULA_VERSION)) != null && (obj3 instanceof Integer)) {
            capabilitiesFeature.setEulaVersion(((Integer) obj3).intValue());
        }
        if (map.containsKey(Constants.CAPABILITIES_FEATURE_STATUS) && (obj2 = map.get(Constants.CAPABILITIES_FEATURE_STATUS)) != null && (obj2 instanceof String)) {
            String str = (String) obj2;
            if (str.equalsIgnoreCase("Enabled")) {
                capabilitiesFeature.setCapabilitiesStatus(2001);
            } else if (str.equalsIgnoreCase(Constants.CAPABILITIES_FEATURE_DISABLED)) {
                capabilitiesFeature.setCapabilitiesStatus(2002);
            }
        }
        Object obj5 = map.get(Constants.CAPABILITIES_INNER_CAPABILITIES_KEY);
        if (obj5 != null && (obj5 instanceof HashMap)) {
            HashMap hashMap = (HashMap) obj5;
            for (Object next : hashMap.keySet()) {
                if (next != null && (next instanceof String) && (obj = hashMap.get(next)) != null && (obj instanceof String)) {
                    String str2 = (String) obj;
                    if (str2.equalsIgnoreCase(Constants.CAPABILITIES_FEATURE_DISABLED)) {
                        capabilitiesFeature.addToCapabilities((String) next, Constants.CAPABILITIES_FEATURE_DISABLED);
                    } else if (str2.equalsIgnoreCase("Enabled")) {
                        capabilitiesFeature.addToCapabilities((String) next, "Enabled");
                    } else {
                        capabilitiesFeature.addToCapabilities((String) next, "Unknown");
                    }
                }
            }
        }
        return capabilitiesFeature;
    }
}

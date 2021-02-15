package com.adobe.mobile;

import java.util.Map;

final class AcquisitionHandler {
    static final String ACQUISITION_CONTEXT_DATA_PREFIX = "ctx";
    private static String ACQUISITION_SERVER = "c00.adobe.com";
    private static final int CONNECTION_TIMEOUT_MSEC = 5000;

    AcquisitionHandler() {
    }

    protected static void campaignStartForApp(String str, Map<String, Object> map) {
        String constructURLForCampaignStartRequest = constructURLForCampaignStartRequest(str, StaticMethods.getAdvertisingIdentifier(), map);
        StaticMethods.logDebugFormat("Acquisition - Sending acquisition request  (%s)", constructURLForCampaignStartRequest);
        RequestHandler.sendGenericRequest(constructURLForCampaignStartRequest, (Map<String, String>) null, 5000, "Acquisition");
    }

    protected static String constructURLForCampaignStartRequest(String str, String str2, Map<String, Object> map) {
        if (str == null || str.length() <= 0) {
            StaticMethods.logDebugFormat("Acquisition - Acquisition application identifier is blank", new Object[0]);
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("https://" + ACQUISITION_SERVER + "/v3/");
        sb.append(str);
        sb.append("/start?");
        StringBuilder sb2 = new StringBuilder();
        if (str2 != null) {
            sb2.append(sb2.length() > 0 ? "&" : "");
            sb2.append("a_cid=");
            sb2.append(StaticMethods.URLEncode(str2));
        }
        if (map != null && map.size() > 0) {
            for (Map.Entry next : map.entrySet()) {
                String str3 = (String) next.getKey();
                Object value = next.getValue();
                if (!(str3 == null || str3.length() == 0 || value == null || value.toString().length() == 0)) {
                    sb2.append(sb2.length() > 0 ? "&" : "");
                    sb2.append(ACQUISITION_CONTEXT_DATA_PREFIX);
                    sb2.append(StaticMethods.URLEncode(str3));
                    sb2.append("=");
                    sb2.append(StaticMethods.URLEncode(value.toString()));
                }
            }
        }
        sb.append(sb2);
        return sb.toString();
    }
}

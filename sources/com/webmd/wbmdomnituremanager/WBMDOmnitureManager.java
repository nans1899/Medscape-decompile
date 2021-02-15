package com.webmd.wbmdomnituremanager;

import com.adobe.mobile.Analytics;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.HashMap;
import java.util.Map;

public class WBMDOmnitureManager {
    private static final String APP_WBMD = "app-wbmd";
    public static final String ASSET_ID = "wapp.asset";
    static String appName = WBMDOmnitureData.getAppName();
    public static HashMap<String, String> mDefferedData = new HashMap<>();
    public static String mPageNamePrefix = "webmd.com";
    public static boolean noExtraSlash = false;
    public static WBMDOmnitureManager shared = new WBMDOmnitureManager();
    private String lastSentPage = "";
    public HashMap<String, String> mData = new HashMap<>();
    private String mLink = "";
    private String mModule = "";

    private WBMDOmnitureManager() {
    }

    public static void sendPageView(String str, Map<String, String> map, WBMDOmnitureModule wBMDOmnitureModule) {
        HashMap hashMap = new HashMap();
        if (WBMDOmnitureData.getAppName().equalsIgnoreCase(APP_WBMD)) {
            hashMap.putAll(WBMDOmnitureData.getDefaulData());
            hashMap.putAll(shared.mData);
            if (map != null) {
                hashMap.putAll(map);
            }
        } else {
            if (map != null) {
                hashMap.putAll(map);
            }
            hashMap.putAll(WBMDOmnitureData.getDefaulData());
            hashMap.putAll(shared.mData);
        }
        HashMap<String, String> hashMap2 = mDefferedData;
        if (hashMap2 != null && !hashMap2.isEmpty()) {
            hashMap.putAll(mDefferedData);
            mDefferedData.clear();
        } else if (wBMDOmnitureModule != null) {
            hashMap.putAll(mapFromModule(wBMDOmnitureModule));
        }
        String fullPageNameForPage = fullPageNameForPage(str);
        shared.lastSentPage = str;
        Analytics.trackState(fullPageNameForPage, hashMap);
    }

    public static void sendModuleAction(WBMDOmnitureModule wBMDOmnitureModule) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.putAll(mapFromModule(wBMDOmnitureModule));
        hashMap.putAll(WBMDOmnitureData.getDefaulData());
        hashMap.putAll(hashMap2);
        hashMap.putAll(shared.mData);
        String str = "" + ((String) hashMap2.get("wapp.mpage"));
        if (hashMap2.containsKey("wapp.mmodule") && hashMap2.get("wapp.mmodule") != null && !((String) hashMap2.get("wapp.mmodule")).isEmpty()) {
            str = str + "_" + ((String) hashMap2.get("wapp.mmodule"));
        }
        if (hashMap2.containsKey("wapp.mlink") && hashMap2.get("wapp.mlink") != null && !((String) hashMap2.get("wapp.mlink")).isEmpty()) {
            str = str + "_" + ((String) hashMap2.get("wapp.mlink"));
        }
        Analytics.trackAction(str, hashMap);
    }

    public static String fullPageNameForPage(String str) {
        String str2;
        String cleanString = cleanString(str);
        if (WBMDOmnitureData.getAppName().equalsIgnoreCase(APP_WBMD)) {
            return "webmd.com/" + cleanString + "/";
        }
        if (noExtraSlash) {
            str2 = "";
        } else {
            str2 = "/";
        }
        if (WBMDOmnitureData.getAppName().equals("msp-app")) {
            return mPageNamePrefix + cleanString + str2;
        }
        return mPageNamePrefix + WBMDOmnitureData.getAppName() + "/" + cleanString + str2;
    }

    public static Map<String, String> mapFromModule(WBMDOmnitureModule wBMDOmnitureModule) {
        HashMap hashMap = new HashMap();
        if (wBMDOmnitureModule.getRefferer() != null) {
            hashMap.put("wapp.mpage", cleanString(fullPageNameForPage(wBMDOmnitureModule.getRefferer())));
        }
        String str = shared.mModule;
        if (str == null || str.isEmpty()) {
            hashMap.put("wapp.mmodule", cleanString(wBMDOmnitureModule.getModuleId()));
        } else {
            hashMap.put("wapp.mmodule", cleanString(shared.mModule));
            shared.mModule = "";
        }
        String str2 = shared.mLink;
        if (str2 == null || str2.isEmpty()) {
            hashMap.put("wapp.mlink", cleanString(wBMDOmnitureModule.getLinkId()));
        } else {
            hashMap.put("wapp.mlink", cleanString(shared.mLink));
            shared.mLink = "";
        }
        hashMap.putAll(wBMDOmnitureModule.getProperties());
        return hashMap;
    }

    public static void setDefferedModule(WBMDOmnitureModule wBMDOmnitureModule) {
        if (mDefferedData == null) {
            mDefferedData = new HashMap<>();
        }
        if (wBMDOmnitureModule.getRefferer() != null) {
            mDefferedData.put("wapp.mpage", cleanString(fullPageNameForPage(wBMDOmnitureModule.getRefferer())));
        }
        String str = shared.mModule;
        if (str == null || str.isEmpty()) {
            mDefferedData.put("wapp.mmodule", cleanString(wBMDOmnitureModule.getModuleId()));
        } else {
            mDefferedData.put("wapp.mmodule", cleanString(shared.mModule));
            shared.mModule = "";
        }
        String str2 = shared.mLink;
        if (str2 == null || str2.isEmpty()) {
            mDefferedData.put("wapp.mlink", cleanString(wBMDOmnitureModule.getLinkId()));
        } else {
            mDefferedData.put("wapp.mlink", cleanString(shared.mLink));
            shared.mLink = "";
        }
        mDefferedData.putAll(wBMDOmnitureModule.getProperties());
    }

    public String getLastSentPage() {
        return this.lastSentPage;
    }

    public void setLastSentPage(String str) {
        this.lastSentPage = str;
    }

    public String getModule() {
        return this.mModule;
    }

    public void setModule(String str) {
        this.mModule = str;
    }

    public String getLink() {
        return this.mLink;
    }

    public void setLink(String str) {
        this.mLink = str;
    }

    public static String normalizeNameForOmniturePageName(String str) {
        return str != null ? str.toLowerCase().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "-").replaceAll("[^A-Za-z0-9-]", "") : str;
    }

    public static String cleanString(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "-");
    }

    public static void sendPageViewWithSharedModule(String str, Map<String, String> map) {
        String str2;
        String str3 = shared.mLink;
        if ((str3 == null || str3.isEmpty()) && ((str2 = shared.mModule) == null || str2.isEmpty())) {
            sendPageView(str, map, (WBMDOmnitureModule) null);
        } else {
            sendPageView(str, map, new WBMDOmnitureModule((String) null, (String) null, shared.getLastSentPage()));
        }
    }
}

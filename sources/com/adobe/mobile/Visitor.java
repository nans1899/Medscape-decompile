package com.adobe.mobile;

import com.adobe.mobile.VisitorID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Visitor {

    public interface VisitorCallback {
        void call(String str);
    }

    public static String getMarketingCloudId() {
        return VisitorIDService.sharedInstance().getMarketingCloudID();
    }

    public static void getUrlVariablesAsync(final VisitorCallback visitorCallback) {
        if (visitorCallback == null) {
            StaticMethods.logDebugFormat("ID Service - Provided callback to \"getUrlVariablesAsync\" was empty.", new Object[0]);
        } else {
            StaticMethods.getSharedExecutor().execute(new Runnable() {
                public void run() {
                    visitorCallback.call(VisitorIDService.sharedInstance().getVisitorInfoUrlString());
                }
            });
        }
    }

    public static void syncIdentifier(String str, String str2, VisitorID.VisitorIDAuthenticationState visitorIDAuthenticationState) {
        if (str == null || str.length() == 0) {
            StaticMethods.logWarningFormat("ID Service - Unable to sync VisitorID with id:%s, idType was nil/empty.", str2);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(str, str2);
        VisitorIDService.sharedInstance().idSync((Map<String, String>) hashMap, visitorIDAuthenticationState);
    }

    public static void syncIdentifiers(Map<String, String> map) {
        VisitorIDService.sharedInstance().idSync(map);
    }

    public static void syncIdentifiers(Map<String, String> map, VisitorID.VisitorIDAuthenticationState visitorIDAuthenticationState) {
        VisitorIDService.sharedInstance().idSync(map, visitorIDAuthenticationState);
    }

    public static List<VisitorID> getIdentifiers() {
        return VisitorIDService.sharedInstance().getIdentifiers();
    }

    public static String appendToURL(String str) {
        return VisitorIDService.sharedInstance().appendVisitorInfoForURL(str);
    }
}

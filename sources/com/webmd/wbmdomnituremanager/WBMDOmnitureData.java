package com.webmd.wbmdomnituremanager;

import java.util.HashMap;
import java.util.Map;

public abstract class WBMDOmnitureData {
    private static String appName = "";
    private static Map<String, String> defaulData = new HashMap();
    private static Map<String, String> moduleNames = new HashMap();
    private static Map<String, String> moduleNamesExceptions = new HashMap();
    private static Map<String, String> pageNames = new HashMap();
    private static Map<String, String> pageNamesExceptions = new HashMap();

    public abstract String addAppName();

    public abstract Map<String, String> addDefaultData();

    public abstract Map<String, String> addModuleNameExceptions();

    public abstract Map<String, String> addModuleNames();

    public abstract Map<String, String> addPageNameExceptions();

    public abstract Map<String, String> addPageNames();

    public WBMDOmnitureData() {
        refreshInitialData();
    }

    public String getPageName(String str) {
        return pageNames.get(str);
    }

    public static Map<String, String> getPageNamesMap() {
        return pageNames;
    }

    public static Map<String, String> getPageNamesExceptionsMap() {
        return pageNamesExceptions;
    }

    public static Map<String, String> getModuleNamesMap() {
        return moduleNames;
    }

    public static Map<String, String> getModuleNamesExceptionsMap() {
        return moduleNamesExceptions;
    }

    public static String getAppName() {
        return appName;
    }

    public static String getShortAppName() {
        String str = appName;
        if (str == null || str.isEmpty()) {
            return "";
        }
        return appName.substring(0, appName.indexOf("-"));
    }

    public static Map<String, String> getDefaulData() {
        return defaulData;
    }

    public void refreshInitialData() {
        pageNames.putAll(addPageNames());
        pageNamesExceptions.putAll(addPageNameExceptions());
        defaulData.putAll(addDefaultData());
        appName = addAppName();
        moduleNames.putAll(addModuleNames());
        moduleNamesExceptions.putAll(addModuleNameExceptions());
    }
}

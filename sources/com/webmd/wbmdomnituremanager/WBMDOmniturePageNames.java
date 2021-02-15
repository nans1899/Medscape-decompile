package com.webmd.wbmdomnituremanager;

public abstract class WBMDOmniturePageNames {
    private static final int OMNITURE_OBJECT_PAGE_MODULE = 1;
    private static final int OMNITURE_OBJECT_PAGE_NAME = 0;

    public abstract WBMDOmnitureModule getModuleForException(Object obj);

    public abstract String getPageNameForException(Object obj);

    public String getPageNameForClass(Object obj) {
        if (obj == null) {
            return null;
        }
        String simpleName = obj.getClass().getSimpleName();
        if (isInExceptionMap(simpleName, 0)) {
            return getPageNameForException(obj);
        }
        return WBMDOmnitureData.getPageNamesMap().get(simpleName);
    }

    public String getPageNameForClass(String str) {
        return WBMDOmnitureData.getPageNamesMap().get(str);
    }

    public WBMDOmnitureModule getPageModuleForClass(Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        if (isInExceptionMap(simpleName, 1)) {
            return getModuleForException(obj);
        }
        if (WBMDOmnitureData.getModuleNamesMap().get(simpleName) != null) {
            return new WBMDOmnitureModule(WBMDOmnitureData.getModuleNamesMap().get(simpleName), (String) null, WBMDOmnitureManager.shared.getLastSentPage());
        }
        return null;
    }

    public static boolean isInExceptionMap(String str, int i) {
        if (i == 0) {
            return WBMDOmnitureData.getPageNamesExceptionsMap().containsKey(str);
        }
        if (i != 1) {
            return false;
        }
        return WBMDOmnitureData.getModuleNamesExceptionsMap().containsKey(str);
    }
}

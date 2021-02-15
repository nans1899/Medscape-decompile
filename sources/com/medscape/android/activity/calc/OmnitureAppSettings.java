package com.medscape.android.activity.calc;

import com.wbmd.omniture.IOmnitureAppSettings;

public class OmnitureAppSettings implements IOmnitureAppSettings {
    public String getAppId() {
        return "";
    }

    public String getBaseUrl() {
        return "medscape.com/app";
    }

    public String getDefaultActionName() {
        return "";
    }

    public String getDefaultSection() {
        return "";
    }

    public boolean getEnableTrailingForwardSlash() {
        return false;
    }

    public boolean getNormalizePageNames() {
        return false;
    }

    public boolean getPageNameToLowerCase() {
        return true;
    }

    public boolean getReplaceSpaceWithDash() {
        return true;
    }
}

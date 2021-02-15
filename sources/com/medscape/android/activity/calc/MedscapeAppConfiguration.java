package com.medscape.android.activity.calc;

import com.medscape.android.BuildConfig;
import com.wbmd.qxcalculator.AppConfigurationProvider;
import com.webmd.medscape.live.explorelivevents.util.Constants;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/medscape/android/activity/calc/MedscapeAppConfiguration;", "Lcom/wbmd/qxcalculator/AppConfigurationProvider;", "()V", "getAppBuildVersion", "", "getPlatformOsForContentItem", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MedscapeAppConfiguration.kt */
public final class MedscapeAppConfiguration implements AppConfigurationProvider {
    public String getAppBuildVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public String getPlatformOsForContentItem() {
        return Constants.USERNAME;
    }
}

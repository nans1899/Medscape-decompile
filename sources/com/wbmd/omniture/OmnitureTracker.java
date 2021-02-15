package com.wbmd.omniture;

import android.content.Context;
import com.adobe.mobile.Analytics;
import com.adobe.mobile.Config;
import com.wbmd.omniture.utils.PvidGenerator;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001¢\u0006\u0002\b\u0007J\r\u0010\b\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0011\u001a\u00020\u000bH\u0002¨\u0006\u0013"}, d2 = {"Lcom/wbmd/omniture/OmnitureTracker;", "", "()V", "getActionName", "", "action", "Lcom/wbmd/omniture/Action;", "getActionName$wbmd_omniture_release", "getReferringPageName", "getReferringPageName$wbmd_omniture_release", "sendAction", "", "sendPageView", "pageView", "Lcom/wbmd/omniture/PageView;", "generatedPvid", "setDefferedAction", "useDefferedAction", "Companion", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: OmnitureTracker.kt */
public final class OmnitureTracker {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    private final void useDefferedAction() {
    }

    public final void setDefferedAction(Action action) {
        Intrinsics.checkNotNullParameter(action, "action");
    }

    public static /* synthetic */ String sendPageView$default(OmnitureTracker omnitureTracker, PageView pageView, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        return omnitureTracker.sendPageView(pageView, str);
    }

    public final String sendPageView(PageView pageView, String str) {
        Intrinsics.checkNotNullParameter(pageView, "pageView");
        Intrinsics.checkNotNullParameter(str, "generatedPvid");
        String formatPageName = new PageNameFormatter(pageView.getPage()).formatPageName();
        pageView.getDataMap().put("wapp.mpage", OmnitureState.Companion.getInstance().getReferringPage());
        if (!(str.length() > 0)) {
            str = PvidGenerator.INSTANCE.generatePVID();
        }
        pageView.getDataMap().put("wapp.pvid", str);
        new OmnitureDataSaver().saveUserData(pageView.getDataMap());
        Analytics.trackState(formatPageName, pageView.getDataMap());
        OmnitureState.Companion.getInstance().setReferringPage(formatPageName);
        return str;
    }

    public final void sendAction(Action action) {
        Intrinsics.checkNotNullParameter(action, "action");
        action.getDataMap().put("wapp.mpage", getReferringPageName$wbmd_omniture_release());
        new OmnitureDataSaver().saveUserData(action.getDataMap());
        Analytics.trackAction(getActionName$wbmd_omniture_release(action), action.getDataMap());
    }

    public final String getReferringPageName$wbmd_omniture_release() {
        String str;
        String referringPage = OmnitureState.Companion.getInstance().getReferringPage();
        boolean z = true;
        if (!(referringPage.length() == 0)) {
            return referringPage;
        }
        IOmnitureAppSettings appSettings = OmnitureState.Companion.getInstance().getAppSettings();
        if (appSettings == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(appSettings.getBaseUrl());
        if (appSettings.getDefaultActionName().length() <= 0) {
            z = false;
        }
        if (z) {
            str = '/' + appSettings.getDefaultActionName();
        } else {
            str = "";
        }
        sb.append(str);
        return sb.toString();
    }

    public final String getActionName$wbmd_omniture_release(Action action) {
        String appId;
        Intrinsics.checkNotNullParameter(action, "action");
        String str = getReferringPageName$wbmd_omniture_release() + "_";
        IOmnitureAppSettings appSettings = OmnitureState.Companion.getInstance().getAppSettings();
        if (!(appSettings == null || (appId = appSettings.getAppId()) == null)) {
            if (appId.length() > 0) {
                str = str + appId + '-';
            }
        }
        return str + action.getMmodule() + '_' + action.getMlink();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lcom/wbmd/omniture/OmnitureTracker$Companion;", "", "()V", "configure", "", "context", "Landroid/content/Context;", "configInputStream", "Ljava/io/InputStream;", "setDebugLogging", "debugLogging", "", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: OmnitureTracker.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void configure$default(Companion companion, Context context, InputStream inputStream, int i, Object obj) {
            if ((i & 2) != 0) {
                inputStream = null;
            }
            companion.configure(context, inputStream);
        }

        public final void configure(Context context, InputStream inputStream) {
            Intrinsics.checkNotNullParameter(context, "context");
            Config.setContext(context);
            Config.overrideConfigStream(inputStream);
        }

        public final void setDebugLogging(boolean z) {
            Config.setDebugLogging(Boolean.valueOf(z));
        }
    }
}

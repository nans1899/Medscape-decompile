package com.webmd.webmdrx.omnitureextensions;

import android.content.Context;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.medscape.live.explorelivevents.util.Constants;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.webmdrx.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004J&\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004J&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006¨\u0006\u001b"}, d2 = {"Lcom/webmd/webmdrx/omnitureextensions/RxOmnitureSender;", "", "()V", "pageNamePrefix", "", "getPageNamePrefix", "()Ljava/lang/String;", "prof_site", "getProf_site", "section", "getSection", "site", "getSite", "buildIcd", "drugName", "drugId", "buildIcdFromActivity", "fromActivity", "", "packageName", "formatDrugName", "sendPageView", "", "pageName", "module", "sendProfPageView", "Companion", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: RxOmnitureSender.kt */
public final class RxOmnitureSender {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static boolean isProfessional;
    private final String pageNamePrefix = "rx/";
    private final String prof_site = "app-msp";
    private final String section = "rx";
    private final String site = "app-wbmd";

    public final String getSite() {
        return this.site;
    }

    public final String getProf_site() {
        return this.prof_site;
    }

    public final String getSection() {
        return this.section;
    }

    public final String getPageNamePrefix() {
        return this.pageNamePrefix;
    }

    public final void sendPageView(String str) {
        Intrinsics.checkNotNullParameter(str, "pageName");
        String lastSentPage = WBMDOmnitureManager.shared.getLastSentPage();
        HashMap hashMapOf = MapsKt.hashMapOf(TuplesKt.to("wapp.section", this.section), TuplesKt.to("wapp.site", this.site));
        if (!isProfessional) {
            WBMDOmnitureManager.sendPageView(this.pageNamePrefix + str, hashMapOf, new WBMDOmnitureModule((String) null, (String) null, lastSentPage));
        }
    }

    public final void sendProfPageView(String str) {
        Intrinsics.checkNotNullParameter(str, "pageName");
        if (isProfessional) {
            WBMDOmnitureManager.sendPageView(str, MapsKt.hashMapOf(TuplesKt.to("wapp.section", this.section), TuplesKt.to("wapp.site", this.prof_site)), (WBMDOmnitureModule) null);
        }
    }

    public final void sendPageView(String str, String str2, String str3, String str4) {
        Intrinsics.checkNotNullParameter(str, "pageName");
        Intrinsics.checkNotNullParameter(str2, "module");
        Intrinsics.checkNotNullParameter(str3, "drugName");
        Intrinsics.checkNotNullParameter(str4, "drugId");
        String lastSentPage = WBMDOmnitureManager.shared.getLastSentPage();
        HashMap hashMapOf = MapsKt.hashMapOf(TuplesKt.to("wapp.icd", buildIcd(str3, str4)), TuplesKt.to("wapp.section", this.section), TuplesKt.to("wapp.site", this.site));
        if (!isProfessional) {
            WBMDOmnitureManager.sendPageView(this.pageNamePrefix + str, hashMapOf, new WBMDOmnitureModule(str2, formatDrugName(str3), lastSentPage));
        }
    }

    public final String buildIcd(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "drugName");
        Intrinsics.checkNotNullParameter(str2, "drugId");
        String replace$default = StringsKt.replace$default(str2, "FDB_", "", false, 4, (Object) null);
        return "appwbmd_wrx-drugs-monotop_" + formatDrugName(str) + '_' + replace$default + "_none_none";
    }

    public final String buildIcdFromActivity(String str, String str2, int i, String str3) {
        String str4;
        Intrinsics.checkNotNullParameter(str, "drugName");
        Intrinsics.checkNotNullParameter(str2, "drugId");
        Intrinsics.checkNotNullParameter(str3, "packageName");
        String replace$default = StringsKt.replace$default(str2, "FDB_", "", false, 4, (Object) null);
        CharSequence charSequence = str3;
        String str5 = "";
        if (StringsKt.contains$default(charSequence, (CharSequence) Constants.USERNAME, false, 2, (Object) null)) {
            str4 = "appwbmda";
        } else {
            str4 = StringsKt.contains$default(charSequence, (CharSequence) "medscape", false, 2, (Object) null) ? "appmscpa" : str5;
        }
        if (i == 1) {
            str5 = "search";
            str = "rx";
        } else if (i == 2) {
            str5 = "wrx-drugs-monotop";
        } else {
            str = str5;
        }
        return str4 + '_' + str5 + '_' + str + '_' + replace$default + "_none_none";
    }

    private final String formatDrugName(String str) {
        String replace$default;
        if (str == null || (replace$default = StringsKt.replace$default(str, (String) MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "-", false, 4, (Object) null)) == null) {
            return null;
        }
        if (replace$default != null) {
            String lowerCase = replace$default.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
            return lowerCase;
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/webmd/webmdrx/omnitureextensions/RxOmnitureSender$Companion;", "", "()V", "isProfessional", "", "()Z", "setProfessional", "(Z)V", "context", "Landroid/content/Context;", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: RxOmnitureSender.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isProfessional() {
            return RxOmnitureSender.isProfessional;
        }

        public final void setProfessional(boolean z) {
            RxOmnitureSender.isProfessional = z;
        }

        public final boolean isProfessional(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Companion companion = this;
            companion.setProfessional(context.getResources().getBoolean(R.bool.rx_is_professional));
            return companion.isProfessional();
        }
    }
}

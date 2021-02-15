package com.wbmd.omniture;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010%\n\u0002\b\u0006\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR(\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Lcom/wbmd/omniture/OmnitureState;", "", "()V", "appSettings", "Lcom/wbmd/omniture/IOmnitureAppSettings;", "getAppSettings", "()Lcom/wbmd/omniture/IOmnitureAppSettings;", "setAppSettings", "(Lcom/wbmd/omniture/IOmnitureAppSettings;)V", "currentSection", "", "getCurrentSection", "()Ljava/lang/String;", "setCurrentSection", "(Ljava/lang/String;)V", "referringPage", "getReferringPage", "setReferringPage", "userData", "", "getUserData", "()Ljava/util/Map;", "setUserData", "(Ljava/util/Map;)V", "Companion", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: OmnitureState.kt */
public final class OmnitureState {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Lazy instance$delegate = LazyKt.lazy(OmnitureState$Companion$instance$2.INSTANCE);
    public IOmnitureAppSettings appSettings;
    private String currentSection;
    private String referringPage;
    private Map<String, Object> userData;

    private OmnitureState() {
        this.referringPage = "";
        this.currentSection = "";
        this.userData = new LinkedHashMap();
    }

    public /* synthetic */ OmnitureState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final String getReferringPage() {
        return this.referringPage;
    }

    public final void setReferringPage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.referringPage = str;
    }

    public final String getCurrentSection() {
        return this.currentSection;
    }

    public final void setCurrentSection(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.currentSection = str;
    }

    public final Map<String, Object> getUserData() {
        return this.userData;
    }

    public final void setUserData(Map<String, Object> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.userData = map;
    }

    public final IOmnitureAppSettings getAppSettings() {
        IOmnitureAppSettings iOmnitureAppSettings = this.appSettings;
        if (iOmnitureAppSettings == null) {
            Intrinsics.throwUninitializedPropertyAccessException("appSettings");
        }
        return iOmnitureAppSettings;
    }

    public final void setAppSettings(IOmnitureAppSettings iOmnitureAppSettings) {
        Intrinsics.checkNotNullParameter(iOmnitureAppSettings, "<set-?>");
        this.appSettings = iOmnitureAppSettings;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/wbmd/omniture/OmnitureState$Companion;", "", "()V", "instance", "Lcom/wbmd/omniture/OmnitureState;", "getInstance", "()Lcom/wbmd/omniture/OmnitureState;", "instance$delegate", "Lkotlin/Lazy;", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: OmnitureState.kt */
    public static final class Companion {
        public final OmnitureState getInstance() {
            Lazy access$getInstance$cp = OmnitureState.instance$delegate;
            Companion companion = OmnitureState.Companion;
            return (OmnitureState) access$getInstance$cp.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

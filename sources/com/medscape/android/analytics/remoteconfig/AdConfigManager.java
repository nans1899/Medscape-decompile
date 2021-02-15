package com.medscape.android.analytics.remoteconfig;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0010R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/AdConfigManager;", "", "remoteConfig", "Lcom/medscape/android/analytics/remoteconfig/RemoteConfig;", "parser", "Lcom/medscape/android/analytics/remoteconfig/AdConfigParser;", "(Lcom/medscape/android/analytics/remoteconfig/RemoteConfig;Lcom/medscape/android/analytics/remoteconfig/AdConfigParser;)V", "getParser", "()Lcom/medscape/android/analytics/remoteconfig/AdConfigParser;", "setParser", "(Lcom/medscape/android/analytics/remoteconfig/AdConfigParser;)V", "getRemoteConfig", "()Lcom/medscape/android/analytics/remoteconfig/RemoteConfig;", "setRemoteConfig", "(Lcom/medscape/android/analytics/remoteconfig/RemoteConfig;)V", "getAdConfigData", "Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel;", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdConfigManager.kt */
public final class AdConfigManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static AdRemoteConfigModel adConfig;
    private AdConfigParser parser;
    private RemoteConfig remoteConfig;

    public AdConfigManager() {
        this((RemoteConfig) null, (AdConfigParser) null, 3, (DefaultConstructorMarker) null);
    }

    public AdConfigManager(RemoteConfig remoteConfig2, AdConfigParser adConfigParser) {
        this.remoteConfig = remoteConfig2;
        this.parser = adConfigParser;
        if (remoteConfig2 == null) {
            this.remoteConfig = new RemoteConfig();
        }
        if (this.parser == null) {
            this.parser = new AdConfigParser();
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AdConfigManager(RemoteConfig remoteConfig2, AdConfigParser adConfigParser, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : remoteConfig2, (i & 2) != 0 ? null : adConfigParser);
    }

    public final AdConfigParser getParser() {
        return this.parser;
    }

    public final RemoteConfig getRemoteConfig() {
        return this.remoteConfig;
    }

    public final void setParser(AdConfigParser adConfigParser) {
        this.parser = adConfigParser;
    }

    public final void setRemoteConfig(RemoteConfig remoteConfig2) {
        this.remoteConfig = remoteConfig2;
    }

    public final AdRemoteConfigModel getAdConfigData() {
        String str;
        AdRemoteConfigModel adRemoteConfigModel;
        FirebaseRemoteConfig mFirebaseRemoteConfig;
        RemoteConfig remoteConfig2 = this.remoteConfig;
        if (remoteConfig2 == null || (mFirebaseRemoteConfig = remoteConfig2.getMFirebaseRemoteConfig()) == null || (str = mFirebaseRemoteConfig.getString(Constants.AD_REMOTE_CONFIG)) == null) {
            str = "";
        }
        Intrinsics.checkNotNullExpressionValue(str, "remoteConfig?.mFirebaseR…ts.AD_REMOTE_CONFIG) ?:\"\"");
        AdConfigParser adConfigParser = this.parser;
        if (adConfigParser == null || (adRemoteConfigModel = adConfigParser.parseAdRemoteConfig(str)) == null) {
            adRemoteConfigModel = new AdRemoteConfigModel();
        }
        adConfig = adRemoteConfigModel;
        Intrinsics.checkNotNull(adRemoteConfigModel);
        return adRemoteConfigModel;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/AdConfigManager$Companion;", "", "()V", "adConfig", "Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel;", "getAdConfig", "()Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel;", "setAdConfig", "(Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel;)V", "getADConfig", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: AdConfigManager.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AdRemoteConfigModel getAdConfig() {
            return AdConfigManager.adConfig;
        }

        public final void setAdConfig(AdRemoteConfigModel adRemoteConfigModel) {
            AdConfigManager.adConfig = adRemoteConfigModel;
        }

        public final AdRemoteConfigModel getADConfig() {
            Companion companion = this;
            if (companion.getAdConfig() == null) {
                companion.setAdConfig(new AdConfigManager((RemoteConfig) null, (AdConfigParser) null, 3, (DefaultConstructorMarker) null).getAdConfigData());
            }
            AdRemoteConfigModel adConfig = companion.getAdConfig();
            Intrinsics.checkNotNull(adConfig);
            return adConfig;
        }
    }
}

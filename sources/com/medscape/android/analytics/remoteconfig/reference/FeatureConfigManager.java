package com.medscape.android.analytics.remoteconfig.reference;

import com.medscape.android.Constants;
import com.medscape.android.analytics.remoteconfig.RemoteConfig;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\"\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012`\u0013R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigManager;", "", "remoteConfig", "Lcom/medscape/android/analytics/remoteconfig/RemoteConfig;", "parser", "Lcom/medscape/android/analytics/remoteconfig/reference/ParseFeatureConfig;", "(Lcom/medscape/android/analytics/remoteconfig/RemoteConfig;Lcom/medscape/android/analytics/remoteconfig/reference/ParseFeatureConfig;)V", "getParser", "()Lcom/medscape/android/analytics/remoteconfig/reference/ParseFeatureConfig;", "setParser", "(Lcom/medscape/android/analytics/remoteconfig/reference/ParseFeatureConfig;)V", "getRemoteConfig", "()Lcom/medscape/android/analytics/remoteconfig/RemoteConfig;", "setRemoteConfig", "(Lcom/medscape/android/analytics/remoteconfig/RemoteConfig;)V", "getFeatureConfigData", "Ljava/util/HashMap;", "", "Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigModel;", "Lkotlin/collections/HashMap;", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeatureConfigManager.kt */
public final class FeatureConfigManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static HashMap<String, FeatureConfigModel> referenceModel;
    private ParseFeatureConfig parser;
    private RemoteConfig remoteConfig;

    public FeatureConfigManager() {
        this((RemoteConfig) null, (ParseFeatureConfig) null, 3, (DefaultConstructorMarker) null);
    }

    public FeatureConfigManager(RemoteConfig remoteConfig2, ParseFeatureConfig parseFeatureConfig) {
        Intrinsics.checkNotNullParameter(remoteConfig2, "remoteConfig");
        Intrinsics.checkNotNullParameter(parseFeatureConfig, "parser");
        this.remoteConfig = remoteConfig2;
        this.parser = parseFeatureConfig;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FeatureConfigManager(RemoteConfig remoteConfig2, ParseFeatureConfig parseFeatureConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new RemoteConfig() : remoteConfig2, (i & 2) != 0 ? new ParseFeatureConfig() : parseFeatureConfig);
    }

    public final RemoteConfig getRemoteConfig() {
        return this.remoteConfig;
    }

    public final void setRemoteConfig(RemoteConfig remoteConfig2) {
        Intrinsics.checkNotNullParameter(remoteConfig2, "<set-?>");
        this.remoteConfig = remoteConfig2;
    }

    public final ParseFeatureConfig getParser() {
        return this.parser;
    }

    public final void setParser(ParseFeatureConfig parseFeatureConfig) {
        Intrinsics.checkNotNullParameter(parseFeatureConfig, "<set-?>");
        this.parser = parseFeatureConfig;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007R:\u0010\u0003\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigManager$Companion;", "", "()V", "referenceModel", "Ljava/util/HashMap;", "", "Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigModel;", "Lkotlin/collections/HashMap;", "getReferenceModel", "()Ljava/util/HashMap;", "setReferenceModel", "(Ljava/util/HashMap;)V", "getReferenceConfig", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: FeatureConfigManager.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HashMap<String, FeatureConfigModel> getReferenceModel() {
            return FeatureConfigManager.referenceModel;
        }

        public final void setReferenceModel(HashMap<String, FeatureConfigModel> hashMap) {
            FeatureConfigManager.referenceModel = hashMap;
        }

        public final HashMap<String, FeatureConfigModel> getReferenceConfig() {
            Companion companion = this;
            if (companion.getReferenceModel() == null) {
                companion.setReferenceModel(new FeatureConfigManager((RemoteConfig) null, (ParseFeatureConfig) null, 3, (DefaultConstructorMarker) null).getFeatureConfigData());
            }
            HashMap<String, FeatureConfigModel> referenceModel = companion.getReferenceModel();
            Intrinsics.checkNotNull(referenceModel);
            return referenceModel;
        }
    }

    public final HashMap<String, FeatureConfigModel> getFeatureConfigData() {
        String string = this.remoteConfig.getMFirebaseRemoteConfig().getString(Constants.REFERENCE_TRAY_REMOTE_CONFIG);
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.mFirebaseRe…RENCE_TRAY_REMOTE_CONFIG)");
        return this.parser.parseFeatureConfig(string);
    }
}

package com.medscape.android.analytics.remoteconfig;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/ClinicalAdvancesConfigManager;", "", "remoteConfig", "Lcom/medscape/android/analytics/remoteconfig/RemoteConfig;", "parser", "Lcom/medscape/android/analytics/remoteconfig/ClinicalAdvancesConfigParser;", "(Lcom/medscape/android/analytics/remoteconfig/RemoteConfig;Lcom/medscape/android/analytics/remoteconfig/ClinicalAdvancesConfigParser;)V", "getParser", "()Lcom/medscape/android/analytics/remoteconfig/ClinicalAdvancesConfigParser;", "setParser", "(Lcom/medscape/android/analytics/remoteconfig/ClinicalAdvancesConfigParser;)V", "getRemoteConfig", "()Lcom/medscape/android/analytics/remoteconfig/RemoteConfig;", "setRemoteConfig", "(Lcom/medscape/android/analytics/remoteconfig/RemoteConfig;)V", "getClinicalAdvancesConfigData", "", "Lcom/medscape/android/analytics/remoteconfig/ClinicalAdvancesConfigModel;", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClinicalAdvancesConfigManager.kt */
public final class ClinicalAdvancesConfigManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static List<ClinicalAdvancesConfigModel> clinicalAdvancesConfigModel;
    private ClinicalAdvancesConfigParser parser;
    private RemoteConfig remoteConfig;

    public ClinicalAdvancesConfigManager() {
        this((RemoteConfig) null, (ClinicalAdvancesConfigParser) null, 3, (DefaultConstructorMarker) null);
    }

    public ClinicalAdvancesConfigManager(RemoteConfig remoteConfig2, ClinicalAdvancesConfigParser clinicalAdvancesConfigParser) {
        this.remoteConfig = remoteConfig2;
        this.parser = clinicalAdvancesConfigParser;
        if (remoteConfig2 == null) {
            this.remoteConfig = new RemoteConfig();
        }
        if (this.parser == null) {
            this.parser = new ClinicalAdvancesConfigParser();
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ClinicalAdvancesConfigManager(RemoteConfig remoteConfig2, ClinicalAdvancesConfigParser clinicalAdvancesConfigParser, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : remoteConfig2, (i & 2) != 0 ? null : clinicalAdvancesConfigParser);
    }

    public final ClinicalAdvancesConfigParser getParser() {
        return this.parser;
    }

    public final RemoteConfig getRemoteConfig() {
        return this.remoteConfig;
    }

    public final void setParser(ClinicalAdvancesConfigParser clinicalAdvancesConfigParser) {
        this.parser = clinicalAdvancesConfigParser;
    }

    public final void setRemoteConfig(RemoteConfig remoteConfig2) {
        this.remoteConfig = remoteConfig2;
    }

    public final List<ClinicalAdvancesConfigModel> getClinicalAdvancesConfigData() {
        String str;
        Object obj;
        FirebaseRemoteConfig mFirebaseRemoteConfig;
        RemoteConfig remoteConfig2 = this.remoteConfig;
        if (remoteConfig2 == null || (mFirebaseRemoteConfig = remoteConfig2.getMFirebaseRemoteConfig()) == null || (str = mFirebaseRemoteConfig.getString(Constants.CLINICAL_ADVANCES_CONFIG)) == null) {
            str = "";
        }
        Intrinsics.checkNotNullExpressionValue(str, "remoteConfig?.mFirebaseR…AL_ADVANCES_CONFIG) ?: \"\"");
        ClinicalAdvancesConfigParser clinicalAdvancesConfigParser = this.parser;
        if (clinicalAdvancesConfigParser == null || (obj = clinicalAdvancesConfigParser.parseClinicalAdvancesConfig(str)) == null) {
            obj = new ClinicalAdvancesConfigModel();
        }
        List<ClinicalAdvancesConfigModel> list = (List) obj;
        clinicalAdvancesConfigModel = list;
        Intrinsics.checkNotNull(list);
        return list;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/ClinicalAdvancesConfigManager$Companion;", "", "()V", "clinicalAdvancesConfigModel", "", "Lcom/medscape/android/analytics/remoteconfig/ClinicalAdvancesConfigModel;", "getClinicalAdvancesConfigModel", "()Ljava/util/List;", "setClinicalAdvancesConfigModel", "(Ljava/util/List;)V", "getClinicalAdvancesConfig", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ClinicalAdvancesConfigManager.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<ClinicalAdvancesConfigModel> getClinicalAdvancesConfigModel() {
            return ClinicalAdvancesConfigManager.clinicalAdvancesConfigModel;
        }

        public final void setClinicalAdvancesConfigModel(List<ClinicalAdvancesConfigModel> list) {
            ClinicalAdvancesConfigManager.clinicalAdvancesConfigModel = list;
        }

        public final List<ClinicalAdvancesConfigModel> getClinicalAdvancesConfig() {
            Companion companion = this;
            if (companion.getClinicalAdvancesConfigModel() == null) {
                companion.setClinicalAdvancesConfigModel(new ClinicalAdvancesConfigManager((RemoteConfig) null, (ClinicalAdvancesConfigParser) null, 3, (DefaultConstructorMarker) null).getClinicalAdvancesConfigData());
            }
            List<ClinicalAdvancesConfigModel> clinicalAdvancesConfigModel = companion.getClinicalAdvancesConfigModel();
            Intrinsics.checkNotNull(clinicalAdvancesConfigModel);
            return clinicalAdvancesConfigModel;
        }
    }
}

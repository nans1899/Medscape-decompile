package com.wbmd.decisionpoint.providers;

import com.wbmd.decisionpoint.domain.enums.DecisionPointType;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fJ\u0006\u0010\u0010\u001a\u00020\u0006J\u0016\u0010\u0011\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006J\b\u0010\u0013\u001a\u00020\u0006H\u0002J\b\u0010\u0014\u001a\u00020\u0006H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0004¨\u0006\u0015"}, d2 = {"Lcom/wbmd/decisionpoint/providers/TypesUrlBuilder;", "", "descionPointType", "Lcom/wbmd/decisionpoint/domain/enums/DecisionPointType;", "(Lcom/wbmd/decisionpoint/domain/enums/DecisionPointType;)V", "contentMetaDataBaseUrl", "", "decisionPointApiBaseURL", "getDescionPointType", "()Lcom/wbmd/decisionpoint/domain/enums/DecisionPointType;", "setDescionPointType", "buildArticleFilterUrl", "type", "subType", "filter", "", "buildDecisionPointBaseUrl", "buildSubTypeUrl", "buildTypeUrl", "getDecisionPointApiPath", "getDecisionPointContentMetaDataPath", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: TypesUrlBuilder.kt */
public final class TypesUrlBuilder {
    private final String contentMetaDataBaseUrl = "https://api.medscape.com/contentmetadataservice/decisionpoint/v1/";
    private final String decisionPointApiBaseURL = "https://decisionpoint.medscape.com/";
    private DecisionPointType descionPointType;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[DecisionPointType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[DecisionPointType.DERMATOLOGY.ordinal()] = 1;
            $EnumSwitchMapping$0[DecisionPointType.ONCOLOGY.ordinal()] = 2;
            $EnumSwitchMapping$0[DecisionPointType.PULMONARY_MEDICINE.ordinal()] = 3;
            int[] iArr2 = new int[DecisionPointType.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[DecisionPointType.DERMATOLOGY.ordinal()] = 1;
            $EnumSwitchMapping$1[DecisionPointType.ONCOLOGY.ordinal()] = 2;
            $EnumSwitchMapping$1[DecisionPointType.PULMONARY_MEDICINE.ordinal()] = 3;
        }
    }

    public TypesUrlBuilder(DecisionPointType decisionPointType) {
        Intrinsics.checkNotNullParameter(decisionPointType, "descionPointType");
        this.descionPointType = decisionPointType;
    }

    public final DecisionPointType getDescionPointType() {
        return this.descionPointType;
    }

    public final void setDescionPointType(DecisionPointType decisionPointType) {
        Intrinsics.checkNotNullParameter(decisionPointType, "<set-?>");
        this.descionPointType = decisionPointType;
    }

    public final String buildDecisionPointBaseUrl() {
        String decisionPointContentMetaDataPath = getDecisionPointContentMetaDataPath();
        return this.contentMetaDataBaseUrl + decisionPointContentMetaDataPath + "/types";
    }

    public final String buildTypeUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "type");
        return this.contentMetaDataBaseUrl + getDecisionPointContentMetaDataPath() + "/type/" + str;
    }

    public final String buildSubTypeUrl(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(str2, "subType");
        return this.decisionPointApiBaseURL + getDecisionPointApiPath() + "?type=" + str + "&subtype=" + str2;
    }

    public final String buildArticleFilterUrl(String str, String str2, List<String> list) {
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(str2, "subType");
        Intrinsics.checkNotNullParameter(list, OmnitureConstants.OMNITURE_MODULE_FILTER);
        list.add(0, str);
        list.add(1, str2);
        return this.contentMetaDataBaseUrl + getDecisionPointContentMetaDataPath() + "/type/" + str + "/subtype/" + str2 + "/articles?path=" + CollectionsKt.joinToString$default(list, ";", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    private final String getDecisionPointApiPath() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.descionPointType.ordinal()];
        if (i == 1) {
            return "dermatology";
        }
        if (i == 2) {
            return "oncology";
        }
        if (i == 3) {
            return "pulmonarymedicine";
        }
        throw new NoWhenBranchMatchedException();
    }

    private final String getDecisionPointContentMetaDataPath() {
        int i = WhenMappings.$EnumSwitchMapping$1[this.descionPointType.ordinal()];
        if (i == 1) {
            return "DermDP";
        }
        if (i == 2) {
            return "ODP";
        }
        if (i == 3) {
            return "PulmDP";
        }
        throw new NoWhenBranchMatchedException();
    }
}

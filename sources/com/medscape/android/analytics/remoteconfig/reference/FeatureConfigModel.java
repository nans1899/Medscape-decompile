package com.medscape.android.analytics.remoteconfig.reference;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J-\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006 "}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigModel;", "", "type", "", "indicator", "Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigIndicatorModel;", "criteria", "", "Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigCriteriaModel;", "(Ljava/lang/String;Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigIndicatorModel;Ljava/util/List;)V", "getCriteria", "()Ljava/util/List;", "setCriteria", "(Ljava/util/List;)V", "getIndicator", "()Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigIndicatorModel;", "setIndicator", "(Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigIndicatorModel;)V", "getType", "()Ljava/lang/String;", "setType", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeatureConfigModel.kt */
public final class FeatureConfigModel {
    private List<FeatureConfigCriteriaModel> criteria;
    private FeatureConfigIndicatorModel indicator;
    private String type;

    public FeatureConfigModel() {
        this((String) null, (FeatureConfigIndicatorModel) null, (List) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ FeatureConfigModel copy$default(FeatureConfigModel featureConfigModel, String str, FeatureConfigIndicatorModel featureConfigIndicatorModel, List<FeatureConfigCriteriaModel> list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = featureConfigModel.type;
        }
        if ((i & 2) != 0) {
            featureConfigIndicatorModel = featureConfigModel.indicator;
        }
        if ((i & 4) != 0) {
            list = featureConfigModel.criteria;
        }
        return featureConfigModel.copy(str, featureConfigIndicatorModel, list);
    }

    public final String component1() {
        return this.type;
    }

    public final FeatureConfigIndicatorModel component2() {
        return this.indicator;
    }

    public final List<FeatureConfigCriteriaModel> component3() {
        return this.criteria;
    }

    public final FeatureConfigModel copy(String str, FeatureConfigIndicatorModel featureConfigIndicatorModel, List<FeatureConfigCriteriaModel> list) {
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(featureConfigIndicatorModel, "indicator");
        Intrinsics.checkNotNullParameter(list, "criteria");
        return new FeatureConfigModel(str, featureConfigIndicatorModel, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FeatureConfigModel)) {
            return false;
        }
        FeatureConfigModel featureConfigModel = (FeatureConfigModel) obj;
        return Intrinsics.areEqual((Object) this.type, (Object) featureConfigModel.type) && Intrinsics.areEqual((Object) this.indicator, (Object) featureConfigModel.indicator) && Intrinsics.areEqual((Object) this.criteria, (Object) featureConfigModel.criteria);
    }

    public int hashCode() {
        String str = this.type;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        FeatureConfigIndicatorModel featureConfigIndicatorModel = this.indicator;
        int hashCode2 = (hashCode + (featureConfigIndicatorModel != null ? featureConfigIndicatorModel.hashCode() : 0)) * 31;
        List<FeatureConfigCriteriaModel> list = this.criteria;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "FeatureConfigModel(type=" + this.type + ", indicator=" + this.indicator + ", criteria=" + this.criteria + ")";
    }

    public FeatureConfigModel(String str, FeatureConfigIndicatorModel featureConfigIndicatorModel, List<FeatureConfigCriteriaModel> list) {
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(featureConfigIndicatorModel, "indicator");
        Intrinsics.checkNotNullParameter(list, "criteria");
        this.type = str;
        this.indicator = featureConfigIndicatorModel;
        this.criteria = list;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.type = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FeatureConfigModel(String str, FeatureConfigIndicatorModel featureConfigIndicatorModel, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? new FeatureConfigIndicatorModel(0, 0, 3, (DefaultConstructorMarker) null) : featureConfigIndicatorModel, (i & 4) != 0 ? new ArrayList() : list);
    }

    public final FeatureConfigIndicatorModel getIndicator() {
        return this.indicator;
    }

    public final void setIndicator(FeatureConfigIndicatorModel featureConfigIndicatorModel) {
        Intrinsics.checkNotNullParameter(featureConfigIndicatorModel, "<set-?>");
        this.indicator = featureConfigIndicatorModel;
    }

    public final List<FeatureConfigCriteriaModel> getCriteria() {
        return this.criteria;
    }

    public final void setCriteria(List<FeatureConfigCriteriaModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.criteria = list;
    }
}

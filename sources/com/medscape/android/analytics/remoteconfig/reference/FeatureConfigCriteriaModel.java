package com.medscape.android.analytics.remoteconfig.reference;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R*\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigCriteriaModel;", "", "target", "", "conditions", "Ljava/util/ArrayList;", "Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigConditionModel;", "Lkotlin/collections/ArrayList;", "(Ljava/lang/String;Ljava/util/ArrayList;)V", "getConditions", "()Ljava/util/ArrayList;", "setConditions", "(Ljava/util/ArrayList;)V", "getTarget", "()Ljava/lang/String;", "setTarget", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeatureConfigCriteriaModel.kt */
public final class FeatureConfigCriteriaModel {
    private ArrayList<FeatureConfigConditionModel> conditions;
    private String target;

    public FeatureConfigCriteriaModel() {
        this((String) null, (ArrayList) null, 3, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ FeatureConfigCriteriaModel copy$default(FeatureConfigCriteriaModel featureConfigCriteriaModel, String str, ArrayList<FeatureConfigConditionModel> arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = featureConfigCriteriaModel.target;
        }
        if ((i & 2) != 0) {
            arrayList = featureConfigCriteriaModel.conditions;
        }
        return featureConfigCriteriaModel.copy(str, arrayList);
    }

    public final String component1() {
        return this.target;
    }

    public final ArrayList<FeatureConfigConditionModel> component2() {
        return this.conditions;
    }

    public final FeatureConfigCriteriaModel copy(String str, ArrayList<FeatureConfigConditionModel> arrayList) {
        Intrinsics.checkNotNullParameter(str, "target");
        Intrinsics.checkNotNullParameter(arrayList, "conditions");
        return new FeatureConfigCriteriaModel(str, arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FeatureConfigCriteriaModel)) {
            return false;
        }
        FeatureConfigCriteriaModel featureConfigCriteriaModel = (FeatureConfigCriteriaModel) obj;
        return Intrinsics.areEqual((Object) this.target, (Object) featureConfigCriteriaModel.target) && Intrinsics.areEqual((Object) this.conditions, (Object) featureConfigCriteriaModel.conditions);
    }

    public int hashCode() {
        String str = this.target;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        ArrayList<FeatureConfigConditionModel> arrayList = this.conditions;
        if (arrayList != null) {
            i = arrayList.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "FeatureConfigCriteriaModel(target=" + this.target + ", conditions=" + this.conditions + ")";
    }

    public FeatureConfigCriteriaModel(String str, ArrayList<FeatureConfigConditionModel> arrayList) {
        Intrinsics.checkNotNullParameter(str, "target");
        Intrinsics.checkNotNullParameter(arrayList, "conditions");
        this.target = str;
        this.conditions = arrayList;
    }

    public final String getTarget() {
        return this.target;
    }

    public final void setTarget(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.target = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FeatureConfigCriteriaModel(String str, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? new ArrayList() : arrayList);
    }

    public final ArrayList<FeatureConfigConditionModel> getConditions() {
        return this.conditions;
    }

    public final void setConditions(ArrayList<FeatureConfigConditionModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.conditions = arrayList;
    }
}

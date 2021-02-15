package com.medscape.android.analytics.remoteconfig.reference;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B3\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005j\b\u0012\u0004\u0012\u00020\u0003`\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005j\b\u0012\u0004\u0012\u00020\u0003`\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J7\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005j\b\u0012\u0004\u0012\u00020\u0003`\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R*\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005j\b\u0012\u0004\u0012\u00020\u0003`\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001f"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigConditionModel;", "", "key", "", "value", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "inverse", "", "(Ljava/lang/String;Ljava/util/ArrayList;Z)V", "getInverse", "()Z", "setInverse", "(Z)V", "getKey", "()Ljava/lang/String;", "setKey", "(Ljava/lang/String;)V", "getValue", "()Ljava/util/ArrayList;", "setValue", "(Ljava/util/ArrayList;)V", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeatureConfigConditionModel.kt */
public final class FeatureConfigConditionModel {
    private boolean inverse;
    private String key;
    private ArrayList<String> value;

    public FeatureConfigConditionModel() {
        this((String) null, (ArrayList) null, false, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ FeatureConfigConditionModel copy$default(FeatureConfigConditionModel featureConfigConditionModel, String str, ArrayList<String> arrayList, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = featureConfigConditionModel.key;
        }
        if ((i & 2) != 0) {
            arrayList = featureConfigConditionModel.value;
        }
        if ((i & 4) != 0) {
            z = featureConfigConditionModel.inverse;
        }
        return featureConfigConditionModel.copy(str, arrayList, z);
    }

    public final String component1() {
        return this.key;
    }

    public final ArrayList<String> component2() {
        return this.value;
    }

    public final boolean component3() {
        return this.inverse;
    }

    public final FeatureConfigConditionModel copy(String str, ArrayList<String> arrayList, boolean z) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(arrayList, "value");
        return new FeatureConfigConditionModel(str, arrayList, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FeatureConfigConditionModel)) {
            return false;
        }
        FeatureConfigConditionModel featureConfigConditionModel = (FeatureConfigConditionModel) obj;
        return Intrinsics.areEqual((Object) this.key, (Object) featureConfigConditionModel.key) && Intrinsics.areEqual((Object) this.value, (Object) featureConfigConditionModel.value) && this.inverse == featureConfigConditionModel.inverse;
    }

    public int hashCode() {
        String str = this.key;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        ArrayList<String> arrayList = this.value;
        if (arrayList != null) {
            i = arrayList.hashCode();
        }
        int i2 = (hashCode + i) * 31;
        boolean z = this.inverse;
        if (z) {
            z = true;
        }
        return i2 + (z ? 1 : 0);
    }

    public String toString() {
        return "FeatureConfigConditionModel(key=" + this.key + ", value=" + this.value + ", inverse=" + this.inverse + ")";
    }

    public FeatureConfigConditionModel(String str, ArrayList<String> arrayList, boolean z) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(arrayList, "value");
        this.key = str;
        this.value = arrayList;
        this.inverse = z;
    }

    public final String getKey() {
        return this.key;
    }

    public final void setKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.key = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FeatureConfigConditionModel(String str, ArrayList arrayList, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? new ArrayList() : arrayList, (i & 4) != 0 ? false : z);
    }

    public final ArrayList<String> getValue() {
        return this.value;
    }

    public final void setValue(ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.value = arrayList;
    }

    public final boolean getInverse() {
        return this.inverse;
    }

    public final void setInverse(boolean z) {
        this.inverse = z;
    }
}

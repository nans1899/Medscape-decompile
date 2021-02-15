package com.medscape.android.analytics.remoteconfig.reference;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigIndicatorModel;", "", "ttl", "", "endDate", "", "(IJ)V", "getEndDate", "()J", "setEndDate", "(J)V", "getTtl", "()I", "setTtl", "(I)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeatureConfigIndicatorModel.kt */
public final class FeatureConfigIndicatorModel {
    private long endDate;
    private int ttl;

    public FeatureConfigIndicatorModel() {
        this(0, 0, 3, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ FeatureConfigIndicatorModel copy$default(FeatureConfigIndicatorModel featureConfigIndicatorModel, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = featureConfigIndicatorModel.ttl;
        }
        if ((i2 & 2) != 0) {
            j = featureConfigIndicatorModel.endDate;
        }
        return featureConfigIndicatorModel.copy(i, j);
    }

    public final int component1() {
        return this.ttl;
    }

    public final long component2() {
        return this.endDate;
    }

    public final FeatureConfigIndicatorModel copy(int i, long j) {
        return new FeatureConfigIndicatorModel(i, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FeatureConfigIndicatorModel)) {
            return false;
        }
        FeatureConfigIndicatorModel featureConfigIndicatorModel = (FeatureConfigIndicatorModel) obj;
        return this.ttl == featureConfigIndicatorModel.ttl && this.endDate == featureConfigIndicatorModel.endDate;
    }

    public int hashCode() {
        long j = this.endDate;
        return (this.ttl * 31) + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "FeatureConfigIndicatorModel(ttl=" + this.ttl + ", endDate=" + this.endDate + ")";
    }

    public FeatureConfigIndicatorModel(int i, long j) {
        this.ttl = i;
        this.endDate = j;
    }

    public final int getTtl() {
        return this.ttl;
    }

    public final void setTtl(int i) {
        this.ttl = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FeatureConfigIndicatorModel(int i, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? 0 : j);
    }

    public final long getEndDate() {
        return this.endDate;
    }

    public final void setEndDate(long j) {
        this.endDate = j;
    }
}

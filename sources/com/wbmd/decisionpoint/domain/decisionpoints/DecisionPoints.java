package com.wbmd.decisionpoint.domain.decisionpoints;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\b\u0002\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003HÆ\u0003J\u001d\u0010\t\u001a\u00020\u00002\u0012\b\u0002\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R \u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/wbmd/decisionpoint/domain/decisionpoints/DecisionPoints;", "", "data", "", "Lcom/wbmd/decisionpoint/domain/decisionpoints/DecisionPoint;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DecisionPoints.kt */
public final class DecisionPoints {
    @SerializedName("decisionPoints")
    private final List<DecisionPoint> data;

    public DecisionPoints() {
        this((List) null, 1, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ DecisionPoints copy$default(DecisionPoints decisionPoints, List<DecisionPoint> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = decisionPoints.data;
        }
        return decisionPoints.copy(list);
    }

    public final List<DecisionPoint> component1() {
        return this.data;
    }

    public final DecisionPoints copy(List<DecisionPoint> list) {
        return new DecisionPoints(list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof DecisionPoints) && Intrinsics.areEqual((Object) this.data, (Object) ((DecisionPoints) obj).data);
        }
        return true;
    }

    public int hashCode() {
        List<DecisionPoint> list = this.data;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "DecisionPoints(data=" + this.data + ")";
    }

    public DecisionPoints(List<DecisionPoint> list) {
        this.data = list;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DecisionPoints(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }

    public final List<DecisionPoint> getData() {
        return this.data;
    }
}

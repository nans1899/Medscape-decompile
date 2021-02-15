package com.wbmd.decisionpoint.domain.decisionpoints;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0012\b\u0002\u0010\u0004\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0013\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0005HÂ\u0003J)\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0012\b\u0002\u0010\u0004\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0004\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/wbmd/decisionpoint/domain/decisionpoints/DecisionPoint;", "", "title", "", "data", "", "Lcom/wbmd/decisionpoint/domain/decisionpoints/DataItem;", "(Ljava/lang/String;Ljava/util/List;)V", "detail", "getDetail", "()Ljava/lang/String;", "imageUrl", "getImageUrl", "getTitle", "url", "getUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DecisionPoints.kt */
public final class DecisionPoint {
    private final List<DataItem> data;
    @SerializedName("title")
    private final String title;

    public DecisionPoint() {
        this((String) null, (List) null, 3, (DefaultConstructorMarker) null);
    }

    private final List<DataItem> component2() {
        return this.data;
    }

    public static /* synthetic */ DecisionPoint copy$default(DecisionPoint decisionPoint, String str, List<DataItem> list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = decisionPoint.title;
        }
        if ((i & 2) != 0) {
            list = decisionPoint.data;
        }
        return decisionPoint.copy(str, list);
    }

    public final String component1() {
        return this.title;
    }

    public final DecisionPoint copy(String str, List<DataItem> list) {
        return new DecisionPoint(str, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DecisionPoint)) {
            return false;
        }
        DecisionPoint decisionPoint = (DecisionPoint) obj;
        return Intrinsics.areEqual((Object) this.title, (Object) decisionPoint.title) && Intrinsics.areEqual((Object) this.data, (Object) decisionPoint.data);
    }

    public int hashCode() {
        String str = this.title;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List<DataItem> list = this.data;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "DecisionPoint(title=" + this.title + ", data=" + this.data + ")";
    }

    public DecisionPoint(String str, List<DataItem> list) {
        this.title = str;
        this.data = list;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DecisionPoint(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : list);
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getUrl() {
        DataItem dataItem;
        List<DataItem> list = this.data;
        if (list == null || (dataItem = list.get(0)) == null) {
            return null;
        }
        return dataItem.getRootPath();
    }

    public final String getDetail() {
        DataItem dataItem;
        List<DataItem> list = this.data;
        if (list == null || (dataItem = list.get(0)) == null) {
            return null;
        }
        return dataItem.getDescriptiveText();
    }

    public final String getImageUrl() {
        DataItem dataItem;
        List<DataItem> list = this.data;
        if (list == null || (dataItem = list.get(0)) == null) {
            return null;
        }
        return dataItem.getClipArt();
    }
}

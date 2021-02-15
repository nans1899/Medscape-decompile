package com.wbmd.decisionpoint.domain.decisionpoints;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/wbmd/decisionpoint/domain/decisionpoints/DataItem;", "", "rootPath", "", "descriptiveText", "clipArt", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getClipArt", "()Ljava/lang/String;", "getDescriptiveText", "getRootPath", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DecisionPoints.kt */
public final class DataItem {
    private final String clipArt;
    private final String descriptiveText;
    private final String rootPath;

    public DataItem() {
        this((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ DataItem copy$default(DataItem dataItem, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = dataItem.rootPath;
        }
        if ((i & 2) != 0) {
            str2 = dataItem.descriptiveText;
        }
        if ((i & 4) != 0) {
            str3 = dataItem.clipArt;
        }
        return dataItem.copy(str, str2, str3);
    }

    public final String component1() {
        return this.rootPath;
    }

    public final String component2() {
        return this.descriptiveText;
    }

    public final String component3() {
        return this.clipArt;
    }

    public final DataItem copy(String str, String str2, String str3) {
        return new DataItem(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataItem)) {
            return false;
        }
        DataItem dataItem = (DataItem) obj;
        return Intrinsics.areEqual((Object) this.rootPath, (Object) dataItem.rootPath) && Intrinsics.areEqual((Object) this.descriptiveText, (Object) dataItem.descriptiveText) && Intrinsics.areEqual((Object) this.clipArt, (Object) dataItem.clipArt);
    }

    public int hashCode() {
        String str = this.rootPath;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.descriptiveText;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.clipArt;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "DataItem(rootPath=" + this.rootPath + ", descriptiveText=" + this.descriptiveText + ", clipArt=" + this.clipArt + ")";
    }

    public DataItem(String str, String str2, String str3) {
        this.rootPath = str;
        this.descriptiveText = str2;
        this.clipArt = str3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DataItem(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
    }

    public final String getRootPath() {
        return this.rootPath;
    }

    public final String getDescriptiveText() {
        return this.descriptiveText;
    }

    public final String getClipArt() {
        return this.clipArt;
    }
}

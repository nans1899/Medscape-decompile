package com.wbmd.decisionpoint.domain.contributors;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001d\u0010\u0012\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007HÆ\u0003J3\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R2\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u00078\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/wbmd/decisionpoint/domain/contributors/ContributorsType;", "", "title", "", "data", "Ljava/util/ArrayList;", "Lcom/wbmd/decisionpoint/domain/contributors/Contributor;", "Lkotlin/collections/ArrayList;", "(Ljava/lang/String;Ljava/util/ArrayList;)V", "getData", "()Ljava/util/ArrayList;", "setData", "(Ljava/util/ArrayList;)V", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ContributorsType.kt */
public final class ContributorsType {
    @SerializedName("data")
    private ArrayList<Contributor> data;
    @SerializedName("title")
    private String title;

    public static /* synthetic */ ContributorsType copy$default(ContributorsType contributorsType, String str, ArrayList<Contributor> arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = contributorsType.title;
        }
        if ((i & 2) != 0) {
            arrayList = contributorsType.data;
        }
        return contributorsType.copy(str, arrayList);
    }

    public final String component1() {
        return this.title;
    }

    public final ArrayList<Contributor> component2() {
        return this.data;
    }

    public final ContributorsType copy(String str, ArrayList<Contributor> arrayList) {
        return new ContributorsType(str, arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContributorsType)) {
            return false;
        }
        ContributorsType contributorsType = (ContributorsType) obj;
        return Intrinsics.areEqual((Object) this.title, (Object) contributorsType.title) && Intrinsics.areEqual((Object) this.data, (Object) contributorsType.data);
    }

    public int hashCode() {
        String str = this.title;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        ArrayList<Contributor> arrayList = this.data;
        if (arrayList != null) {
            i = arrayList.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "ContributorsType(title=" + this.title + ", data=" + this.data + ")";
    }

    public ContributorsType(String str, ArrayList<Contributor> arrayList) {
        this.title = str;
        this.data = arrayList;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final ArrayList<Contributor> getData() {
        return this.data;
    }

    public final void setData(ArrayList<Contributor> arrayList) {
        this.data = arrayList;
    }
}

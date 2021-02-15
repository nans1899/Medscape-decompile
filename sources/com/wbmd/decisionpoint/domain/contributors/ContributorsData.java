package com.wbmd.decisionpoint.domain.contributors;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B!\u0012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0003J'\u0010\n\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R*\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/wbmd/decisionpoint/domain/contributors/ContributorsData;", "", "contributorsType", "Ljava/util/ArrayList;", "Lcom/wbmd/decisionpoint/domain/contributors/ContributorsType;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "getContributorsType", "()Ljava/util/ArrayList;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ContributorsData.kt */
public final class ContributorsData {
    @SerializedName("contributerData")
    private final ArrayList<ContributorsType> contributorsType;

    public static /* synthetic */ ContributorsData copy$default(ContributorsData contributorsData, ArrayList<ContributorsType> arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = contributorsData.contributorsType;
        }
        return contributorsData.copy(arrayList);
    }

    public final ArrayList<ContributorsType> component1() {
        return this.contributorsType;
    }

    public final ContributorsData copy(ArrayList<ContributorsType> arrayList) {
        return new ContributorsData(arrayList);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof ContributorsData) && Intrinsics.areEqual((Object) this.contributorsType, (Object) ((ContributorsData) obj).contributorsType);
        }
        return true;
    }

    public int hashCode() {
        ArrayList<ContributorsType> arrayList = this.contributorsType;
        if (arrayList != null) {
            return arrayList.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "ContributorsData(contributorsType=" + this.contributorsType + ")";
    }

    public ContributorsData(ArrayList<ContributorsType> arrayList) {
        this.contributorsType = arrayList;
    }

    public final ArrayList<ContributorsType> getContributorsType() {
        return this.contributorsType;
    }
}

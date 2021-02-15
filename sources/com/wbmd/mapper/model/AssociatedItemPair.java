package com.wbmd.mapper.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B)\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R&\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR&\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lcom/wbmd/mapper/model/AssociatedItemPair;", "", "webmdGroups", "", "Lcom/wbmd/mapper/model/GroupItem;", "affiliateGroups", "(Ljava/util/List;Ljava/util/List;)V", "getAffiliateGroups", "()Ljava/util/List;", "setAffiliateGroups", "(Ljava/util/List;)V", "getWebmdGroups", "setWebmdGroups", "wbmdmapper_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AssociatedItemPair.kt */
public final class AssociatedItemPair {
    @SerializedName("affiliate")
    private List<GroupItem> affiliateGroups;
    @SerializedName("webmd")
    private List<GroupItem> webmdGroups;

    public AssociatedItemPair() {
        this((List) null, (List) null, 3, (DefaultConstructorMarker) null);
    }

    public AssociatedItemPair(List<GroupItem> list, List<GroupItem> list2) {
        this.webmdGroups = list;
        this.affiliateGroups = list2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AssociatedItemPair(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : list2);
    }

    public final List<GroupItem> getWebmdGroups() {
        return this.webmdGroups;
    }

    public final void setWebmdGroups(List<GroupItem> list) {
        this.webmdGroups = list;
    }

    public final List<GroupItem> getAffiliateGroups() {
        return this.affiliateGroups;
    }

    public final void setAffiliateGroups(List<GroupItem> list) {
        this.affiliateGroups = list;
    }
}

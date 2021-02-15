package com.wbmd.mapper.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0011\u0018\u00002\u00020\u0001B?\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\u0010\tR&\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR&\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/wbmd/mapper/model/GroupItem;", "", "id", "", "description", "", "associatedIds", "", "allIds", "(ILjava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getAllIds", "()Ljava/util/List;", "setAllIds", "(Ljava/util/List;)V", "getAssociatedIds", "setAssociatedIds", "getDescription", "()Ljava/lang/String;", "setDescription", "(Ljava/lang/String;)V", "getId", "()I", "setId", "(I)V", "wbmdmapper_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: GroupItem.kt */
public final class GroupItem {
    @SerializedName("all_ids")
    private List<String> allIds;
    @SerializedName("associated_ids")
    private List<String> associatedIds;
    @SerializedName("descr")
    private String description;
    @SerializedName("id")
    private int id;

    public GroupItem() {
        this(0, (String) null, (List) null, (List) null, 15, (DefaultConstructorMarker) null);
    }

    public GroupItem(int i, String str, List<String> list, List<String> list2) {
        this.id = i;
        this.description = str;
        this.associatedIds = list;
        this.allIds = list2;
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int i) {
        this.id = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GroupItem(int i, String str, List list, List list2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? null : str, (i2 & 4) != 0 ? null : list, (i2 & 8) != 0 ? null : list2);
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        this.description = str;
    }

    public final List<String> getAssociatedIds() {
        return this.associatedIds;
    }

    public final void setAssociatedIds(List<String> list) {
        this.associatedIds = list;
    }

    public final List<String> getAllIds() {
        return this.allIds;
    }

    public final void setAllIds(List<String> list) {
        this.allIds = list;
    }
}

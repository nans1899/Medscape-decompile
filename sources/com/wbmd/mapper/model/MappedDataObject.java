package com.wbmd.mapper.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\bR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR,\u0010\u0004\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/wbmd/mapper/model/MappedDataObject;", "", "associatedItemPair", "Lcom/wbmd/mapper/model/AssociatedItemPair;", "directMappings", "", "Lcom/wbmd/mapper/model/MappedItemPair;", "Lcom/wbmd/mapper/model/MappedItem;", "(Lcom/wbmd/mapper/model/AssociatedItemPair;Ljava/util/List;)V", "getAssociatedItemPair", "()Lcom/wbmd/mapper/model/AssociatedItemPair;", "setAssociatedItemPair", "(Lcom/wbmd/mapper/model/AssociatedItemPair;)V", "getDirectMappings", "()Ljava/util/List;", "setDirectMappings", "(Ljava/util/List;)V", "wbmdmapper_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MappedDataObject.kt */
public final class MappedDataObject {
    @SerializedName("associated_groups")
    private AssociatedItemPair associatedItemPair;
    @SerializedName("direct_mappings")
    private List<MappedItemPair<MappedItem>> directMappings;

    public MappedDataObject() {
        this((AssociatedItemPair) null, (List) null, 3, (DefaultConstructorMarker) null);
    }

    public MappedDataObject(AssociatedItemPair associatedItemPair2, List<MappedItemPair<MappedItem>> list) {
        this.associatedItemPair = associatedItemPair2;
        this.directMappings = list;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MappedDataObject(AssociatedItemPair associatedItemPair2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : associatedItemPair2, (i & 2) != 0 ? null : list);
    }

    public final AssociatedItemPair getAssociatedItemPair() {
        return this.associatedItemPair;
    }

    public final void setAssociatedItemPair(AssociatedItemPair associatedItemPair2) {
        this.associatedItemPair = associatedItemPair2;
    }

    public final List<MappedItemPair<MappedItem>> getDirectMappings() {
        return this.directMappings;
    }

    public final void setDirectMappings(List<MappedItemPair<MappedItem>> list) {
        this.directMappings = list;
    }
}

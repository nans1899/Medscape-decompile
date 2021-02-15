package com.wbmd.mapper.model;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u000e\u0010\n\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0007J\u000e\u0010\u000b\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0007J(\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0018\u0010\u0003\u001a\u00028\u00008\u0006X\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\u0004\u001a\u00028\u00008\u0006X\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcom/wbmd/mapper/model/MappedItemPair;", "T", "", "affiliateItem", "webmdItem", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getAffiliateItem", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getWebmdItem", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Object;)Lcom/wbmd/mapper/model/MappedItemPair;", "equals", "", "other", "hashCode", "", "toString", "", "wbmdmapper_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MappedItemPair.kt */
public final class MappedItemPair<T> {
    @SerializedName("affiliate_item")
    private final T affiliateItem;
    @SerializedName("webmd_item")
    private final T webmdItem;

    public static /* synthetic */ MappedItemPair copy$default(MappedItemPair mappedItemPair, T t, T t2, int i, Object obj) {
        if ((i & 1) != 0) {
            t = mappedItemPair.affiliateItem;
        }
        if ((i & 2) != 0) {
            t2 = mappedItemPair.webmdItem;
        }
        return mappedItemPair.copy(t, t2);
    }

    public final T component1() {
        return this.affiliateItem;
    }

    public final T component2() {
        return this.webmdItem;
    }

    public final MappedItemPair<T> copy(T t, T t2) {
        return new MappedItemPair<>(t, t2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedItemPair)) {
            return false;
        }
        MappedItemPair mappedItemPair = (MappedItemPair) obj;
        return Intrinsics.areEqual((Object) this.affiliateItem, (Object) mappedItemPair.affiliateItem) && Intrinsics.areEqual((Object) this.webmdItem, (Object) mappedItemPair.webmdItem);
    }

    public int hashCode() {
        T t = this.affiliateItem;
        int i = 0;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        T t2 = this.webmdItem;
        if (t2 != null) {
            i = t2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "MappedItemPair(affiliateItem=" + this.affiliateItem + ", webmdItem=" + this.webmdItem + ")";
    }

    public MappedItemPair(T t, T t2) {
        this.affiliateItem = t;
        this.webmdItem = t2;
    }

    public final T getAffiliateItem() {
        return this.affiliateItem;
    }

    public final T getWebmdItem() {
        return this.webmdItem;
    }
}

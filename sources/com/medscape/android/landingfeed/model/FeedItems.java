package com.medscape.android.landingfeed.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\b\u0002\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003HÆ\u0003J\u001d\u0010\t\u001a\u00020\u00002\u0012\b\u0002\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001b\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/medscape/android/landingfeed/model/FeedItems;", "", "data", "", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedItems.kt */
public final class FeedItems {
    private final List<FeedDataItem> data;

    public FeedItems() {
        this((List) null, 1, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ FeedItems copy$default(FeedItems feedItems, List<FeedDataItem> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = feedItems.data;
        }
        return feedItems.copy(list);
    }

    public final List<FeedDataItem> component1() {
        return this.data;
    }

    public final FeedItems copy(List<FeedDataItem> list) {
        return new FeedItems(list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof FeedItems) && Intrinsics.areEqual((Object) this.data, (Object) ((FeedItems) obj).data);
        }
        return true;
    }

    public int hashCode() {
        List<FeedDataItem> list = this.data;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "FeedItems(data=" + this.data + ")";
    }

    public FeedItems(List<FeedDataItem> list) {
        this.data = list;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FeedItems(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }

    public final List<FeedDataItem> getData() {
        return this.data;
    }
}

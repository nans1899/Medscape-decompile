package com.medscape.android.landingfeed.views;

import androidx.recyclerview.widget.DiffUtil;
import com.medscape.android.landingfeed.model.FeedAdItem;
import com.medscape.android.landingfeed.model.FeedDataItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0017J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u001a\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/medscape/android/landingfeed/views/FeedPagedListAdapter$Companion$ITEM_COMPARATOR$1", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "getChangePayload", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedPagedListAdapter.kt */
public final class FeedPagedListAdapter$Companion$ITEM_COMPARATOR$1 extends DiffUtil.ItemCallback<FeedDataItem> {
    public Object getChangePayload(FeedDataItem feedDataItem, FeedDataItem feedDataItem2) {
        Intrinsics.checkNotNullParameter(feedDataItem, "oldItem");
        Intrinsics.checkNotNullParameter(feedDataItem2, "newItem");
        return null;
    }

    FeedPagedListAdapter$Companion$ITEM_COMPARATOR$1() {
    }

    public boolean areContentsTheSame(FeedDataItem feedDataItem, FeedDataItem feedDataItem2) {
        Intrinsics.checkNotNullParameter(feedDataItem, "oldItem");
        Intrinsics.checkNotNullParameter(feedDataItem2, "newItem");
        return Intrinsics.areEqual((Object) feedDataItem, (Object) feedDataItem2) && !(feedDataItem2 instanceof FeedAdItem);
    }

    public boolean areItemsTheSame(FeedDataItem feedDataItem, FeedDataItem feedDataItem2) {
        Intrinsics.checkNotNullParameter(feedDataItem, "oldItem");
        Intrinsics.checkNotNullParameter(feedDataItem2, "newItem");
        return Intrinsics.areEqual((Object) feedDataItem.getTitle(), (Object) feedDataItem2.getTitle()) && Intrinsics.areEqual((Object) feedDataItem.getContentId(), (Object) feedDataItem2.getContentId()) && !(feedDataItem2 instanceof FeedAdItem);
    }
}

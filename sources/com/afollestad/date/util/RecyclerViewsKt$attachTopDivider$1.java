package com.afollestad.date.util;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/afollestad/date/util/RecyclerViewsKt$attachTopDivider$1", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "onScrolled", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "dx", "", "dy", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: RecyclerViews.kt */
public final class RecyclerViewsKt$attachTopDivider$1 extends RecyclerView.OnScrollListener {
    final /* synthetic */ View $divider;
    final /* synthetic */ RecyclerView $this_attachTopDivider;

    RecyclerViewsKt$attachTopDivider$1(RecyclerView recyclerView, View view) {
        this.$this_attachTopDivider = recyclerView;
        this.$divider = view;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        super.onScrolled(recyclerView, i, i2);
        RecyclerViewsKt.invalidateTopDividerNow(this.$this_attachTopDivider, this.$divider);
    }
}

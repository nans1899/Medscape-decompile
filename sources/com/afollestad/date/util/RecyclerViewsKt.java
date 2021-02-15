package com.afollestad.date.util;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0006"}, d2 = {"attachTopDivider", "", "Landroidx/recyclerview/widget/RecyclerView;", "divider", "Landroid/view/View;", "invalidateTopDividerNow", "com.afollestad.date-picker"}, k = 2, mv = {1, 1, 15})
/* compiled from: RecyclerViews.kt */
public final class RecyclerViewsKt {
    public static final void attachTopDivider(RecyclerView recyclerView, View view) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "$this$attachTopDivider");
        Intrinsics.checkParameterIsNotNull(view, "divider");
        invalidateTopDividerNow(recyclerView, view);
        recyclerView.addOnScrollListener(new RecyclerViewsKt$attachTopDivider$1(recyclerView, view));
    }

    public static final void invalidateTopDividerNow(RecyclerView recyclerView, View view) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "$this$invalidateTopDividerNow");
        Intrinsics.checkParameterIsNotNull(view, "divider");
        if (!ViewsKt.isVisible(recyclerView)) {
            ViewsKt.hide(view);
        } else {
            ViewsKt.showOrHide(view, recyclerView.computeVerticalScrollOffset() > view.getMeasuredHeight() * 2);
        }
    }
}

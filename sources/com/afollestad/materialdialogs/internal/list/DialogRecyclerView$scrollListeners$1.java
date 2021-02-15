package com.afollestad.materialdialogs.internal.list;

import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"com/afollestad/materialdialogs/internal/list/DialogRecyclerView$scrollListeners$1", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "onScrolled", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "dx", "", "dy", "core"}, k = 1, mv = {1, 1, 16})
/* compiled from: DialogRecyclerView.kt */
public final class DialogRecyclerView$scrollListeners$1 extends RecyclerView.OnScrollListener {
    final /* synthetic */ DialogRecyclerView this$0;

    DialogRecyclerView$scrollListeners$1(DialogRecyclerView dialogRecyclerView) {
        this.this$0 = dialogRecyclerView;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        super.onScrolled(recyclerView, i, i2);
        this.this$0.invalidateDividers();
    }
}

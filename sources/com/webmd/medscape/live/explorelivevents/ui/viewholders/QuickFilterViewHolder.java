package com.webmd.medscape.live.explorelivevents.ui.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import com.webmd.medscape.live.explorelivevents.databinding.QuickFilterItemView2Binding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/viewholders/QuickFilterViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/webmd/medscape/live/explorelivevents/databinding/QuickFilterItemView2Binding;", "(Lcom/webmd/medscape/live/explorelivevents/databinding/QuickFilterItemView2Binding;)V", "getBinding", "()Lcom/webmd/medscape/live/explorelivevents/databinding/QuickFilterItemView2Binding;", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: QuickFilterViewHolder.kt */
public final class QuickFilterViewHolder extends RecyclerView.ViewHolder {
    private final QuickFilterItemView2Binding binding;

    public final QuickFilterItemView2Binding getBinding() {
        return this.binding;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public QuickFilterViewHolder(QuickFilterItemView2Binding quickFilterItemView2Binding) {
        super(quickFilterItemView2Binding.getRoot());
        Intrinsics.checkNotNullParameter(quickFilterItemView2Binding, "binding");
        this.binding = quickFilterItemView2Binding;
    }
}

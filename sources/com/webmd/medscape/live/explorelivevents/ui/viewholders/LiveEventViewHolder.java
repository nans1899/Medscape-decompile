package com.webmd.medscape.live.explorelivevents.ui.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import com.webmd.medscape.live.explorelivevents.databinding.EventItemBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/viewholders/LiveEventViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/webmd/medscape/live/explorelivevents/databinding/EventItemBinding;", "(Lcom/webmd/medscape/live/explorelivevents/databinding/EventItemBinding;)V", "getBinding", "()Lcom/webmd/medscape/live/explorelivevents/databinding/EventItemBinding;", "setBinding", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventViewHolder.kt */
public final class LiveEventViewHolder extends RecyclerView.ViewHolder {
    private EventItemBinding binding;

    public final EventItemBinding getBinding() {
        return this.binding;
    }

    public final void setBinding(EventItemBinding eventItemBinding) {
        Intrinsics.checkNotNullParameter(eventItemBinding, "<set-?>");
        this.binding = eventItemBinding;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LiveEventViewHolder(EventItemBinding eventItemBinding) {
        super(eventItemBinding.getRoot());
        Intrinsics.checkNotNullParameter(eventItemBinding, "binding");
        this.binding = eventItemBinding;
    }
}

package com.webmd.medscape.live.explorelivevents.ui.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import com.webmd.medscape.live.explorelivevents.databinding.ButtonItemBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/viewholders/ButtonViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/webmd/medscape/live/explorelivevents/databinding/ButtonItemBinding;", "(Lcom/webmd/medscape/live/explorelivevents/databinding/ButtonItemBinding;)V", "getBinding", "()Lcom/webmd/medscape/live/explorelivevents/databinding/ButtonItemBinding;", "setBinding", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ButtonViewHolder.kt */
public final class ButtonViewHolder extends RecyclerView.ViewHolder {
    private ButtonItemBinding binding;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ButtonViewHolder(ButtonItemBinding buttonItemBinding) {
        super(buttonItemBinding.getRoot());
        Intrinsics.checkNotNullParameter(buttonItemBinding, "binding");
        this.binding = buttonItemBinding;
    }

    public final ButtonItemBinding getBinding() {
        return this.binding;
    }

    public final void setBinding(ButtonItemBinding buttonItemBinding) {
        Intrinsics.checkNotNullParameter(buttonItemBinding, "<set-?>");
        this.binding = buttonItemBinding;
    }
}

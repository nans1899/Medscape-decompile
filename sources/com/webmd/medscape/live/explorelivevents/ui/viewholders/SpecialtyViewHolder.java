package com.webmd.medscape.live.explorelivevents.ui.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import com.webmd.medscape.live.explorelivevents.databinding.SpecialtyItemBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/viewholders/SpecialtyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/webmd/medscape/live/explorelivevents/databinding/SpecialtyItemBinding;", "(Lcom/webmd/medscape/live/explorelivevents/databinding/SpecialtyItemBinding;)V", "getBinding", "()Lcom/webmd/medscape/live/explorelivevents/databinding/SpecialtyItemBinding;", "setBinding", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SpecialtyViewHolder.kt */
public final class SpecialtyViewHolder extends RecyclerView.ViewHolder {
    private SpecialtyItemBinding binding;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SpecialtyViewHolder(SpecialtyItemBinding specialtyItemBinding) {
        super(specialtyItemBinding.getRoot());
        Intrinsics.checkNotNullParameter(specialtyItemBinding, "binding");
        this.binding = specialtyItemBinding;
    }

    public final SpecialtyItemBinding getBinding() {
        return this.binding;
    }

    public final void setBinding(SpecialtyItemBinding specialtyItemBinding) {
        Intrinsics.checkNotNullParameter(specialtyItemBinding, "<set-?>");
        this.binding = specialtyItemBinding;
    }
}

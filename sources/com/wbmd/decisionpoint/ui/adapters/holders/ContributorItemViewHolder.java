package com.wbmd.decisionpoint.ui.adapters.holders;

import androidx.recyclerview.widget.RecyclerView;
import com.wbmd.decisionpoint.databinding.RowContributorItemBinding;
import com.wbmd.decisionpoint.domain.contributors.Contributor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/wbmd/decisionpoint/ui/adapters/holders/ContributorItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/wbmd/decisionpoint/databinding/RowContributorItemBinding;", "(Lcom/wbmd/decisionpoint/databinding/RowContributorItemBinding;)V", "bindData", "", "contributor", "Lcom/wbmd/decisionpoint/domain/contributors/Contributor;", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ContributorItemViewHolder.kt */
public final class ContributorItemViewHolder extends RecyclerView.ViewHolder {
    private final RowContributorItemBinding binding;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContributorItemViewHolder(RowContributorItemBinding rowContributorItemBinding) {
        super(rowContributorItemBinding.getRoot());
        Intrinsics.checkNotNullParameter(rowContributorItemBinding, "binding");
        this.binding = rowContributorItemBinding;
    }

    public final void bindData(Contributor contributor) {
        Intrinsics.checkNotNullParameter(contributor, "contributor");
        this.binding.setContributor(contributor);
    }
}

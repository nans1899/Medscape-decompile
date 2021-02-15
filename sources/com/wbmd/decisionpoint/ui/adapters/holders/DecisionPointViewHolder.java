package com.wbmd.decisionpoint.ui.adapters.holders;

import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wbmd.decisionpoint.databinding.RowDecisionPointBinding;
import com.wbmd.decisionpoint.domain.decisionpoints.DecisionPoint;
import com.wbmd.decisionpoint.domain.interfaces.HubListener;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/wbmd/decisionpoint/ui/adapters/holders/DecisionPointViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/wbmd/decisionpoint/databinding/RowDecisionPointBinding;", "listener", "Lcom/wbmd/decisionpoint/domain/interfaces/HubListener;", "(Lcom/wbmd/decisionpoint/databinding/RowDecisionPointBinding;Lcom/wbmd/decisionpoint/domain/interfaces/HubListener;)V", "getBinding", "()Lcom/wbmd/decisionpoint/databinding/RowDecisionPointBinding;", "setBinding", "(Lcom/wbmd/decisionpoint/databinding/RowDecisionPointBinding;)V", "getListener", "()Lcom/wbmd/decisionpoint/domain/interfaces/HubListener;", "setListener", "(Lcom/wbmd/decisionpoint/domain/interfaces/HubListener;)V", "bindData", "", "item", "Lcom/wbmd/decisionpoint/domain/decisionpoints/DecisionPoint;", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DecisionPointViewHolder.kt */
public final class DecisionPointViewHolder extends RecyclerView.ViewHolder {
    private RowDecisionPointBinding binding;
    private HubListener listener;

    public final RowDecisionPointBinding getBinding() {
        return this.binding;
    }

    public final void setBinding(RowDecisionPointBinding rowDecisionPointBinding) {
        Intrinsics.checkNotNullParameter(rowDecisionPointBinding, "<set-?>");
        this.binding = rowDecisionPointBinding;
    }

    public final HubListener getListener() {
        return this.listener;
    }

    public final void setListener(HubListener hubListener) {
        Intrinsics.checkNotNullParameter(hubListener, "<set-?>");
        this.listener = hubListener;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DecisionPointViewHolder(RowDecisionPointBinding rowDecisionPointBinding, HubListener hubListener) {
        super(rowDecisionPointBinding.getRoot());
        Intrinsics.checkNotNullParameter(rowDecisionPointBinding, "binding");
        Intrinsics.checkNotNullParameter(hubListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.binding = rowDecisionPointBinding;
        this.listener = hubListener;
    }

    public final void bindData(DecisionPoint decisionPoint) {
        Intrinsics.checkNotNullParameter(decisionPoint, ContentParser.ITEM);
        this.binding.setItem(decisionPoint);
        this.binding.setListener(this.listener);
    }
}

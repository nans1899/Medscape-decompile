package com.medscape.android.consult.postupdates.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.consult.models.BodyUpdates;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.postupdates.interfaces.PostUpdateSelectedListener;
import com.medscape.android.databinding.ItemConsultPostUpdateBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/medscape/android/consult/postupdates/viewholders/ConsultUpdateViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/medscape/android/databinding/ItemConsultPostUpdateBinding;", "updateSelectedListener", "Lcom/medscape/android/consult/postupdates/interfaces/PostUpdateSelectedListener;", "(Lcom/medscape/android/databinding/ItemConsultPostUpdateBinding;Lcom/medscape/android/consult/postupdates/interfaces/PostUpdateSelectedListener;)V", "getBinding", "()Lcom/medscape/android/databinding/ItemConsultPostUpdateBinding;", "onBind", "", "consultPost", "Lcom/medscape/android/consult/models/ConsultPost;", "bodyUpdates", "Lcom/medscape/android/consult/models/BodyUpdates;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ConsultUpdateViewHolder.kt */
public final class ConsultUpdateViewHolder extends RecyclerView.ViewHolder {
    private final ItemConsultPostUpdateBinding binding;
    /* access modifiers changed from: private */
    public final PostUpdateSelectedListener updateSelectedListener;

    public final ItemConsultPostUpdateBinding getBinding() {
        return this.binding;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConsultUpdateViewHolder(ItemConsultPostUpdateBinding itemConsultPostUpdateBinding, PostUpdateSelectedListener postUpdateSelectedListener) {
        super(itemConsultPostUpdateBinding.getRoot());
        Intrinsics.checkNotNullParameter(itemConsultPostUpdateBinding, "binding");
        Intrinsics.checkNotNullParameter(postUpdateSelectedListener, "updateSelectedListener");
        this.binding = itemConsultPostUpdateBinding;
        this.updateSelectedListener = postUpdateSelectedListener;
    }

    public final void onBind(ConsultPost consultPost, BodyUpdates bodyUpdates) {
        Intrinsics.checkNotNullParameter(bodyUpdates, "bodyUpdates");
        this.binding.setBodyupdate(bodyUpdates);
        this.binding.consultEdit.setOnClickListener(new ConsultUpdateViewHolder$onBind$1(this, consultPost, bodyUpdates));
        this.binding.executePendingBindings();
    }
}

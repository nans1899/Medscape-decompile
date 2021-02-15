package com.medscape.android.consult.postupdates.viewholders;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.postupdates.interfaces.PostUpdateSelectedListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u000e\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/medscape/android/consult/postupdates/viewholders/ConsultAddUpdateButtonViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "updateSelectedListener", "Lcom/medscape/android/consult/postupdates/interfaces/PostUpdateSelectedListener;", "(Landroid/view/View;Lcom/medscape/android/consult/postupdates/interfaces/PostUpdateSelectedListener;)V", "addUpdateButton", "onBind", "", "consultPost", "Lcom/medscape/android/consult/models/ConsultPost;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ConsultAddUpdateButtonViewHolder.kt */
public final class ConsultAddUpdateButtonViewHolder extends RecyclerView.ViewHolder {
    private View addUpdateButton;
    /* access modifiers changed from: private */
    public final PostUpdateSelectedListener updateSelectedListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConsultAddUpdateButtonViewHolder(View view, PostUpdateSelectedListener postUpdateSelectedListener) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "itemView");
        Intrinsics.checkNotNullParameter(postUpdateSelectedListener, "updateSelectedListener");
        this.updateSelectedListener = postUpdateSelectedListener;
        View findViewById = view.findViewById(R.id.button_add_an_update);
        Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.button_add_an_update)");
        this.addUpdateButton = findViewById;
    }

    public final void onBind(ConsultPost consultPost) {
        this.addUpdateButton.setOnClickListener(new ConsultAddUpdateButtonViewHolder$onBind$1(this, consultPost));
    }
}

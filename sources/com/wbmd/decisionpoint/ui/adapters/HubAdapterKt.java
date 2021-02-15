package com.wbmd.decisionpoint.ui.adapters;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001¨\u0006\u0006"}, d2 = {"setHubAdapter", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "adapter", "Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter;", "wbmd.decisionpoint_release"}, k = 2, mv = {1, 4, 0})
/* compiled from: HubAdapter.kt */
public final class HubAdapterKt {
    @BindingAdapter({"hubAdapter"})
    public static final void setHubAdapter(RecyclerView recyclerView, HubAdapter hubAdapter) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(hubAdapter, "adapter");
        recyclerView.setAdapter(hubAdapter);
    }
}

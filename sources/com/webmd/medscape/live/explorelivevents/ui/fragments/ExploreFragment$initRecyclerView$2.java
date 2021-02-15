package com.webmd.medscape.live.explorelivevents.ui.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.medscape.live.explorelivevents.data.Error;
import com.webmd.medscape.live.explorelivevents.data.UiState;
import com.webmd.medscape.live.explorelivevents.util.ExtensionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/webmd/medscape/live/explorelivevents/ui/fragments/ExploreFragment$initRecyclerView$2", "Landroidx/recyclerview/widget/RecyclerView$AdapterDataObserver;", "onChanged", "", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ExploreFragment.kt */
public final class ExploreFragment$initRecyclerView$2 extends RecyclerView.AdapterDataObserver {
    final /* synthetic */ ExploreFragment this$0;

    ExploreFragment$initRecyclerView$2(ExploreFragment exploreFragment) {
        this.this$0 = exploreFragment;
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [com.webmd.medscape.live.explorelivevents.data.UiState] */
    /* JADX WARNING: type inference failed for: r3v3, types: [com.webmd.medscape.live.explorelivevents.data.UiState] */
    public void onChanged() {
        super.onChanged();
        UiState uiState = null;
        if (this.this$0.eventsAdapter.getItemCount() == 0) {
            MutableLiveData<Error> errorObserver = this.this$0.getViewModel().getErrorObserver();
            Context requireContext = this.this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            errorObserver.setValue(new Error(true, "No events for the specified criteria", ExtensionsKt.getErrorImageRes(0, requireContext)));
            MutableLiveData<UiState> uiState2 = this.this$0.getViewModel().getUiState();
            ? uiState3 = this.this$0.getViewModel().getUiState();
            if (uiState3 != 0) {
                uiState = UiState.copy$default(uiState3, false, true, false, false, 8, (Object) null);
            }
            uiState2.setValue(uiState);
            return;
        }
        this.this$0.getViewModel().getErrorObserver().setValue(new Error(false, (String) null, (Drawable) null));
        MutableLiveData<UiState> uiState4 = this.this$0.getViewModel().getUiState();
        ? uiState5 = this.this$0.getViewModel().getUiState();
        if (uiState5 != 0) {
            uiState = UiState.copy$default(uiState5, false, false, true, false, 8, (Object) null);
        }
        uiState4.setValue(uiState);
    }
}

package com.webmd.medscape.live.explorelivevents.ui.fragments;

import com.webmd.medscape.live.explorelivevents.common.MenuUiState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: FilterSelectionFragment.kt */
final class FilterSelectionFragment$unselectAllCallback$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ FilterSelectionFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilterSelectionFragment$unselectAllCallback$1(FilterSelectionFragment filterSelectionFragment) {
        super(0);
        this.this$0 = filterSelectionFragment;
    }

    public final void invoke() {
        this.this$0.unselect = false;
        this.this$0.getViewModel().setMenuUiState(MenuUiState.copy$default(this.this$0.getViewModel().getMenuUiState(), false, false, false, 6, (Object) null));
    }
}

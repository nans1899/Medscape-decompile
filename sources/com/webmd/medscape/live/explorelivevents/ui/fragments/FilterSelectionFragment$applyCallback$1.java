package com.webmd.medscape.live.explorelivevents.ui.fragments;

import com.webmd.medscape.live.explorelivevents.common.MenuUiState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: FilterSelectionFragment.kt */
final class FilterSelectionFragment$applyCallback$1 extends Lambda implements Function1<Boolean, Unit> {
    final /* synthetic */ FilterSelectionFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilterSelectionFragment$applyCallback$1(FilterSelectionFragment filterSelectionFragment) {
        super(1);
        this.this$0 = filterSelectionFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Boolean) obj).booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(boolean z) {
        this.this$0.getViewModel().setMenuUiState(MenuUiState.copy$default(this.this$0.getViewModel().getMenuUiState(), false, z, false, 5, (Object) null));
    }
}
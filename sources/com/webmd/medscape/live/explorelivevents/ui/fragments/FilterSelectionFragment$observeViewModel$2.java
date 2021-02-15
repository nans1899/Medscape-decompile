package com.webmd.medscape.live.explorelivevents.ui.fragments;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.MenuUiState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/webmd/medscape/live/explorelivevents/common/MenuUiState;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: FilterSelectionFragment.kt */
final class FilterSelectionFragment$observeViewModel$2<T> implements Observer<MenuUiState> {
    final /* synthetic */ FilterSelectionFragment this$0;

    FilterSelectionFragment$observeViewModel$2(FilterSelectionFragment filterSelectionFragment) {
        this.this$0 = filterSelectionFragment;
    }

    public final void onChanged(MenuUiState menuUiState) {
        Toolbar toolbar = this.this$0.getBinding().toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar, "binding.toolbar");
        MenuItem findItem = toolbar.getMenu().findItem(R.id.action_done);
        Toolbar toolbar2 = this.this$0.getBinding().toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar2, "binding.toolbar");
        toolbar2.getMenu().findItem(R.id.action_select_all);
        Intrinsics.checkNotNullExpressionValue(findItem, "applyItem");
        findItem.setVisible(menuUiState.getApplyIconStatus());
    }
}

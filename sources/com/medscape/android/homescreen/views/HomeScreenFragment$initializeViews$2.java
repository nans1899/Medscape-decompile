package com.medscape.android.homescreen.views;

import android.view.ViewGroup;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.medscape.android.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: HomeScreenFragment.kt */
final class HomeScreenFragment$initializeViews$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ HomeScreenFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HomeScreenFragment$initializeViews$2(HomeScreenFragment homeScreenFragment) {
        super(0);
        this.this$0 = homeScreenFragment;
    }

    public final void invoke() {
        SpecialRecyclerView.Companion.setFabButtonTouchY(-1.0f);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.this$0._$_findCachedViewById(R.id.collapsing_toolbar_layout);
        Intrinsics.checkNotNullExpressionValue(collapsingToolbarLayout, "collapsing_toolbar_layout");
        ViewGroup.LayoutParams layoutParams = collapsingToolbarLayout.getLayoutParams();
        if (layoutParams != null) {
            ((AppBarLayout.LayoutParams) layoutParams).setScrollFlags(19);
            CollapsingToolbarLayout collapsingToolbarLayout2 = (CollapsingToolbarLayout) this.this$0._$_findCachedViewById(R.id.collapsing_toolbar_layout);
            Intrinsics.checkNotNullExpressionValue(collapsingToolbarLayout2, "collapsing_toolbar_layout");
            collapsingToolbarLayout2.setLayoutParams(layoutParams);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.google.android.material.appbar.AppBarLayout.LayoutParams");
    }
}

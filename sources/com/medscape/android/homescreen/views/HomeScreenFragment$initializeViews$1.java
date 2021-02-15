package com.medscape.android.homescreen.views;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.medscape.android.R;
import com.medscape.android.homescreen.views.SpecialRecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "event", "Landroid/view/MotionEvent;", "onTouch"}, k = 3, mv = {1, 4, 0})
/* compiled from: HomeScreenFragment.kt */
final class HomeScreenFragment$initializeViews$1 implements View.OnTouchListener {
    final /* synthetic */ HomeScreenFragment this$0;

    HomeScreenFragment$initializeViews$1(HomeScreenFragment homeScreenFragment) {
        this.this$0 = homeScreenFragment;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        SpecialRecyclerView.Companion companion = SpecialRecyclerView.Companion;
        Intrinsics.checkNotNullExpressionValue(motionEvent, "event");
        companion.setFabButtonTouchY(motionEvent.getRawY());
        SpecialRecyclerView.Companion.setFabButtonSwipeReleased(true);
        if (motionEvent.getAction() != 0) {
            return false;
        }
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.this$0._$_findCachedViewById(R.id.collapsing_toolbar_layout);
        Intrinsics.checkNotNullExpressionValue(collapsingToolbarLayout, "collapsing_toolbar_layout");
        ViewGroup.LayoutParams layoutParams = collapsingToolbarLayout.getLayoutParams();
        if (layoutParams != null) {
            ((AppBarLayout.LayoutParams) layoutParams).setScrollFlags(23);
            CollapsingToolbarLayout collapsingToolbarLayout2 = (CollapsingToolbarLayout) this.this$0._$_findCachedViewById(R.id.collapsing_toolbar_layout);
            Intrinsics.checkNotNullExpressionValue(collapsingToolbarLayout2, "collapsing_toolbar_layout");
            collapsingToolbarLayout2.setLayoutParams(layoutParams);
            return false;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.google.android.material.appbar.AppBarLayout.LayoutParams");
    }
}

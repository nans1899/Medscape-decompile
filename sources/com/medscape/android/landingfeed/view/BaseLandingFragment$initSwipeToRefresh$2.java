package com.medscape.android.landingfeed.view;

import androidx.fragment.app.FragmentActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "onRefresh"}, k = 3, mv = {1, 4, 0})
/* compiled from: BaseLandingFragment.kt */
final class BaseLandingFragment$initSwipeToRefresh$2 implements SwipeRefreshLayout.OnRefreshListener {
    final /* synthetic */ BaseLandingFragment this$0;

    BaseLandingFragment$initSwipeToRefresh$2(BaseLandingFragment baseLandingFragment) {
        this.this$0 = baseLandingFragment;
    }

    public final void onRefresh() {
        LandingFeedViewModel access$getFeedViewModel$p;
        this.this$0.forceRefresh = true;
        if (this.this$0.getFeedType() == 3) {
            FragmentActivity activity = this.this$0.getActivity();
            if (activity != null) {
                BaseLandingFragment baseLandingFragment = this.this$0;
                Intrinsics.checkNotNullExpressionValue(activity, "it");
                baseLandingFragment.loadCMEFeeds(activity);
            }
        } else {
            FragmentActivity activity2 = this.this$0.getActivity();
            if (!(activity2 == null || (access$getFeedViewModel$p = this.this$0.feedViewModel) == null)) {
                Intrinsics.checkNotNullExpressionValue(activity2, "it");
                access$getFeedViewModel$p.loadSpecificInvitations(activity2, true);
            }
            LandingFeedViewModel access$getFeedViewModel$p2 = this.this$0.feedViewModel;
            if (access$getFeedViewModel$p2 != null) {
                access$getFeedViewModel$p2.refresh();
            }
        }
        LandingFeedViewModel access$getFeedViewModel$p3 = this.this$0.feedViewModel;
        if (access$getFeedViewModel$p3 != null) {
            access$getFeedViewModel$p3.setMPvid("");
        }
        this.this$0.sendPageView();
    }
}

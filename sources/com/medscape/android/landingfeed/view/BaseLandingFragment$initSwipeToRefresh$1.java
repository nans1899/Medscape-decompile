package com.medscape.android.landingfeed.view;

import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.medscape.android.R;
import com.medscape.android.landingfeed.repository.NetworkState;
import com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel;
import com.medscape.android.landingfeed.views.FeedPagedListAdapter;
import com.webmd.wbmdcmepulse.live_events.model.LiveEventItem;
import com.webmd.wbmdcmepulse.live_events.util.LiveEventsCacheManager;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/medscape/android/landingfeed/repository/NetworkState;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: BaseLandingFragment.kt */
final class BaseLandingFragment$initSwipeToRefresh$1<T> implements Observer<NetworkState> {
    final /* synthetic */ BaseLandingFragment this$0;

    BaseLandingFragment$initSwipeToRefresh$1(BaseLandingFragment baseLandingFragment) {
        this.this$0 = baseLandingFragment;
    }

    public final void onChanged(NetworkState networkState) {
        LandingFeedViewModel access$getFeedViewModel$p;
        LandingFeedViewModel access$getFeedViewModel$p2;
        FeedPagedListAdapter feedAdapter;
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) this.this$0._$_findCachedViewById(R.id.swipe_refresh);
        Intrinsics.checkNotNullExpressionValue(swipeRefreshLayout, "swipe_refresh");
        swipeRefreshLayout.setRefreshing(Intrinsics.areEqual((Object) networkState, (Object) NetworkState.Companion.getLOADING()));
        if (this.this$0.getFeedType() == 3 && Intrinsics.areEqual((Object) networkState, (Object) NetworkState.Companion.getLOADED()) && (access$getFeedViewModel$p = this.this$0.feedViewModel) != null && access$getFeedViewModel$p.getLiveEventsLoaded()) {
            LiveEventsCacheManager access$getLiveEventsCacheManager$p = this.this$0.liveEventsCacheManager;
            ArrayList<LiveEventItem> liveEvents = access$getLiveEventsCacheManager$p != null ? access$getLiveEventsCacheManager$p.getLiveEvents() : null;
            if (liveEvents != null && (access$getFeedViewModel$p2 = this.this$0.feedViewModel) != null && (feedAdapter = access$getFeedViewModel$p2.getFeedAdapter()) != null) {
                feedAdapter.addLiveEvents(liveEvents);
            }
        }
    }
}

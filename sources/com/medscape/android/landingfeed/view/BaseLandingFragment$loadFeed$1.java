package com.medscape.android.landingfeed.view;

import android.os.Handler;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import com.medscape.android.landingfeed.model.ActiveTime;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.landingfeed.model.FeedListing;
import com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel;
import com.medscape.android.landingfeed.views.FeedPagedListAdapter;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Landroidx/paging/PagedList;", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: BaseLandingFragment.kt */
final class BaseLandingFragment$loadFeed$1<T> implements Observer<PagedList<FeedDataItem>> {
    final /* synthetic */ BaseLandingFragment this$0;

    BaseLandingFragment$loadFeed$1(BaseLandingFragment baseLandingFragment) {
        this.this$0 = baseLandingFragment;
    }

    public final void onChanged(PagedList<FeedDataItem> pagedList) {
        LandingFeedViewModel access$getFeedViewModel$p;
        LandingFeedViewModel access$getFeedViewModel$p2;
        FeedPagedListAdapter feedAdapter;
        FeedPagedListAdapter feedAdapter2;
        LandingFeedViewModel access$getFeedViewModel$p3;
        MutableLiveData<FeedListing<FeedDataItem>> feedResult;
        FeedListing value;
        LandingFeedViewModel access$getFeedViewModel$p4 = this.this$0.feedViewModel;
        boolean z = false;
        if (!(access$getFeedViewModel$p4 == null || (feedAdapter = access$getFeedViewModel$p4.getFeedAdapter()) == null)) {
            int itemCount = feedAdapter.getItemCount();
            if ((pagedList != null && pagedList.size() > 1) || itemCount < 2) {
                if (this.this$0.forceRefresh && (!this.this$0.mActiveTimeMap.isEmpty())) {
                    for (Map.Entry entry : this.this$0.mActiveTimeMap.entrySet()) {
                        this.this$0.mActiveTimeMap.put(entry.getKey(), this.this$0.updateActiveItemsTime((ActiveTime) entry.getValue()));
                    }
                }
                this.this$0.forceRefresh = false;
                LandingFeedViewModel access$getFeedViewModel$p5 = this.this$0.feedViewModel;
                if (access$getFeedViewModel$p5 != null) {
                    access$getFeedViewModel$p5.initPreloadAds(pagedList);
                }
                LandingFeedViewModel access$getFeedViewModel$p6 = this.this$0.feedViewModel;
                if (!(access$getFeedViewModel$p6 == null || (feedAdapter2 = access$getFeedViewModel$p6.getFeedAdapter()) == null)) {
                    feedAdapter2.submitList(pagedList);
                }
                new Handler().postDelayed(new BaseLandingFragment$loadFeed$1$$special$$inlined$let$lambda$1(this, pagedList), 300);
            } else if (!((itemCount >= 2 && !this.this$0.forceRefresh) || (access$getFeedViewModel$p3 = this.this$0.feedViewModel) == null || (feedResult = access$getFeedViewModel$p3.getFeedResult()) == null || (value = feedResult.getValue()) == null)) {
                value.setRetry(new BaseLandingFragment$loadFeed$1$$special$$inlined$let$lambda$2(this, pagedList));
            }
        }
        this.this$0.initSwipeToRefresh();
        if (!(pagedList == null || (access$getFeedViewModel$p = this.this$0.feedViewModel) == null || access$getFeedViewModel$p.getItemsLoaded() || (access$getFeedViewModel$p2 = this.this$0.feedViewModel) == null)) {
            if (pagedList.size() > 2) {
                z = true;
            }
            access$getFeedViewModel$p2.setItemsLoaded(z);
        }
        BaseLandingFragment baseLandingFragment = this.this$0;
        LandingFeedViewModel access$getFeedViewModel$p7 = baseLandingFragment.feedViewModel;
        Boolean valueOf = access$getFeedViewModel$p7 != null ? Boolean.valueOf(access$getFeedViewModel$p7.getItemsLoaded()) : null;
        Intrinsics.checkNotNull(valueOf);
        baseLandingFragment.isScrollTop = !valueOf.booleanValue();
    }
}

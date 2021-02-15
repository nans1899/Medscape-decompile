package com.medscape.android.landingfeed.view;

import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "run", "com/medscape/android/landingfeed/view/BaseLandingFragment$loadFeed$1$1$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: BaseLandingFragment.kt */
final class BaseLandingFragment$loadFeed$1$$special$$inlined$let$lambda$1 implements Runnable {
    final /* synthetic */ PagedList $it$inlined;
    final /* synthetic */ BaseLandingFragment$loadFeed$1 this$0;

    BaseLandingFragment$loadFeed$1$$special$$inlined$let$lambda$1(BaseLandingFragment$loadFeed$1 baseLandingFragment$loadFeed$1, PagedList pagedList) {
        this.this$0 = baseLandingFragment$loadFeed$1;
        this.$it$inlined = pagedList;
    }

    public final void run() {
        RecyclerView feedView = this.this$0.this$0.getFeedView();
        if (feedView != null) {
            feedView.scrollToPosition(0);
        }
        this.this$0.this$0.addItemsToActiveTimeMap();
    }
}

package com.medscape.android.landingfeed.view;

import androidx.paging.PagedList;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.medscape.android.R;
import com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "invoke", "com/medscape/android/landingfeed/view/BaseLandingFragment$loadFeed$1$1$2"}, k = 3, mv = {1, 4, 0})
/* compiled from: BaseLandingFragment.kt */
final class BaseLandingFragment$loadFeed$1$$special$$inlined$let$lambda$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ PagedList $it$inlined;
    final /* synthetic */ BaseLandingFragment$loadFeed$1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseLandingFragment$loadFeed$1$$special$$inlined$let$lambda$2(BaseLandingFragment$loadFeed$1 baseLandingFragment$loadFeed$1, PagedList pagedList) {
        super(0);
        this.this$0 = baseLandingFragment$loadFeed$1;
        this.$it$inlined = pagedList;
    }

    public final void invoke() {
        LandingFeedViewModel access$getFeedViewModel$p = this.this$0.this$0.feedViewModel;
        if (access$getFeedViewModel$p != null) {
            access$getFeedViewModel$p.refresh();
        }
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) this.this$0.this$0._$_findCachedViewById(R.id.swipe_refresh);
        Intrinsics.checkNotNullExpressionValue(swipeRefreshLayout, "swipe_refresh");
        swipeRefreshLayout.setRefreshing(false);
    }
}

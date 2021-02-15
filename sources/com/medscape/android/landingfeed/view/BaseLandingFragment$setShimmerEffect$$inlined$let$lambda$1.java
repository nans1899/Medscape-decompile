package com.medscape.android.landingfeed.view;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.medscape.android.R;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "run", "com/medscape/android/landingfeed/view/BaseLandingFragment$setShimmerEffect$1$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: BaseLandingFragment.kt */
final class BaseLandingFragment$setShimmerEffect$$inlined$let$lambda$1 implements Runnable {
    final /* synthetic */ BaseLandingFragment this$0;

    BaseLandingFragment$setShimmerEffect$$inlined$let$lambda$1(BaseLandingFragment baseLandingFragment) {
        this.this$0 = baseLandingFragment;
    }

    public final void run() {
        if (((ShimmerFrameLayout) this.this$0._$_findCachedViewById(R.id.shimmerLayout)) != null) {
            ((ShimmerFrameLayout) this.this$0._$_findCachedViewById(R.id.shimmerLayout)).stopShimmer();
        }
    }
}

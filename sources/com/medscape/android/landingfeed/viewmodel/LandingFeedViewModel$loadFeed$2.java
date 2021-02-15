package com.medscape.android.landingfeed.viewmodel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.landingfeed.model.FeedListing;
import com.medscape.android.landingfeed.repository.NetworkState;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006 \u0003*\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Landroidx/lifecycle/LiveData;", "Lcom/medscape/android/landingfeed/repository/NetworkState;", "kotlin.jvm.PlatformType", "it", "Lcom/medscape/android/landingfeed/model/FeedListing;", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "apply"}, k = 3, mv = {1, 4, 0})
/* compiled from: LandingFeedViewModel.kt */
final class LandingFeedViewModel$loadFeed$2<I, O> implements Function<FeedListing<FeedDataItem>, LiveData<NetworkState>> {
    public static final LandingFeedViewModel$loadFeed$2 INSTANCE = new LandingFeedViewModel$loadFeed$2();

    LandingFeedViewModel$loadFeed$2() {
    }

    public final LiveData<NetworkState> apply(FeedListing<FeedDataItem> feedListing) {
        return feedListing.getRefreshState();
    }
}

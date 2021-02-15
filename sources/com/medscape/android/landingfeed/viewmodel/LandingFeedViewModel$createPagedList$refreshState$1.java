package com.medscape.android.landingfeed.viewmodel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import com.medscape.android.landingfeed.repository.FeedDataSource;
import com.medscape.android.landingfeed.repository.NetworkState;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u000e\u0010\u0004\u001a\n \u0003*\u0004\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Landroidx/lifecycle/LiveData;", "Lcom/medscape/android/landingfeed/repository/NetworkState;", "kotlin.jvm.PlatformType", "it", "Lcom/medscape/android/landingfeed/repository/FeedDataSource;", "apply"}, k = 3, mv = {1, 4, 0})
/* compiled from: LandingFeedViewModel.kt */
final class LandingFeedViewModel$createPagedList$refreshState$1<I, O> implements Function<FeedDataSource, LiveData<NetworkState>> {
    public static final LandingFeedViewModel$createPagedList$refreshState$1 INSTANCE = new LandingFeedViewModel$createPagedList$refreshState$1();

    LandingFeedViewModel$createPagedList$refreshState$1() {
    }

    public final LiveData<NetworkState> apply(FeedDataSource feedDataSource) {
        return feedDataSource.getInitialLoad();
    }
}

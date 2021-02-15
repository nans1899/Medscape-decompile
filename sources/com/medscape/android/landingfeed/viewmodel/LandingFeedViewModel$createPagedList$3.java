package com.medscape.android.landingfeed.viewmodel;

import com.medscape.android.landingfeed.repository.FeedDataSource;
import com.medscape.android.landingfeed.repository.FeedDataSourceFactory;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: LandingFeedViewModel.kt */
final class LandingFeedViewModel$createPagedList$3 extends Lambda implements Function0<Unit> {
    final /* synthetic */ FeedDataSourceFactory $factory;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LandingFeedViewModel$createPagedList$3(FeedDataSourceFactory feedDataSourceFactory) {
        super(0);
        this.$factory = feedDataSourceFactory;
    }

    public final void invoke() {
        FeedDataSource value = this.$factory.getSourceLiveData().getValue();
        if (value != null) {
            value.invalidate();
        }
    }
}

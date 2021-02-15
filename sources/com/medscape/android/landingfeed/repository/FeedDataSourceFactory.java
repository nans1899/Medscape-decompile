package com.medscape.android.landingfeed.repository;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel;
import com.medscape.android.landingfeed.api.LandingFeedApi;
import com.medscape.android.landingfeed.model.FeedDataItem;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B/\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0002¢\u0006\u0002\u0010\rJ\u0014\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0014H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/medscape/android/landingfeed/repository/FeedDataSourceFactory;", "Landroidx/paging/DataSource$Factory;", "", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "feedApi", "Lcom/medscape/android/landingfeed/api/LandingFeedApi;", "retryExecutor", "Ljava/util/concurrent/Executor;", "adConfig", "Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel;", "context", "Landroidx/fragment/app/FragmentActivity;", "feedPath", "(Lcom/medscape/android/landingfeed/api/LandingFeedApi;Ljava/util/concurrent/Executor;Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel;Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;)V", "sourceLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/medscape/android/landingfeed/repository/FeedDataSource;", "getSourceLiveData", "()Landroidx/lifecycle/MutableLiveData;", "create", "Landroidx/paging/DataSource;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedDataSourceFactory.kt */
public final class FeedDataSourceFactory extends DataSource.Factory<String, FeedDataItem> {
    private final AdRemoteConfigModel adConfig;
    private final FragmentActivity context;
    private final LandingFeedApi feedApi;
    private final String feedPath;
    private final Executor retryExecutor;
    private final MutableLiveData<FeedDataSource> sourceLiveData = new MutableLiveData<>();

    public FeedDataSourceFactory(LandingFeedApi landingFeedApi, Executor executor, AdRemoteConfigModel adRemoteConfigModel, FragmentActivity fragmentActivity, String str) {
        Intrinsics.checkNotNullParameter(executor, "retryExecutor");
        Intrinsics.checkNotNullParameter(adRemoteConfigModel, "adConfig");
        Intrinsics.checkNotNullParameter(fragmentActivity, "context");
        Intrinsics.checkNotNullParameter(str, "feedPath");
        this.feedApi = landingFeedApi;
        this.retryExecutor = executor;
        this.adConfig = adRemoteConfigModel;
        this.context = fragmentActivity;
        this.feedPath = str;
    }

    public final MutableLiveData<FeedDataSource> getSourceLiveData() {
        return this.sourceLiveData;
    }

    public DataSource<String, FeedDataItem> create() {
        FeedDataSource feedDataSource = new FeedDataSource(this.feedApi, this.retryExecutor, this.adConfig, this.context, this.feedPath);
        this.sourceLiveData.postValue(feedDataSource);
        return feedDataSource;
    }
}

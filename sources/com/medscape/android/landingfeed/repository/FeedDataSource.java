package com.medscape.android.landingfeed.repository;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;
import com.facebook.internal.NativeProtocol;
import com.medscape.android.R;
import com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel;
import com.medscape.android.landingfeed.api.LandingFeedApi;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.util.Util;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B/\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0002¢\u0006\u0002\u0010\rJ\b\u0010\u001f\u001a\u00020\u0002H\u0002J*\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020#2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030%H\u0016J*\u0010&\u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020#2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030%H\u0016J*\u0010'\u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020(2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030)H\u0016J\u0006\u0010*\u001a\u00020!R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u000e\u0010\u001a\u001a\u00020\u001bXD¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/medscape/android/landingfeed/repository/FeedDataSource;", "Landroidx/paging/PageKeyedDataSource;", "", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "feedApi", "Lcom/medscape/android/landingfeed/api/LandingFeedApi;", "retryExecutor", "Ljava/util/concurrent/Executor;", "adConfig", "Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel;", "context", "Landroidx/fragment/app/FragmentActivity;", "feedPath", "(Lcom/medscape/android/landingfeed/api/LandingFeedApi;Ljava/util/concurrent/Executor;Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel;Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;)V", "initialLoad", "Landroidx/lifecycle/MutableLiveData;", "Lcom/medscape/android/landingfeed/repository/NetworkState;", "getInitialLoad", "()Landroidx/lifecycle/MutableLiveData;", "isLastItemLoaded", "", "()Z", "setLastItemLoaded", "(Z)V", "networkState", "getNetworkState", "pageSize", "", "retry", "Lkotlin/Function0;", "", "getNetworkErrorMsg", "loadAfter", "", "params", "Landroidx/paging/PageKeyedDataSource$LoadParams;", "callback", "Landroidx/paging/PageKeyedDataSource$LoadCallback;", "loadBefore", "loadInitial", "Landroidx/paging/PageKeyedDataSource$LoadInitialParams;", "Landroidx/paging/PageKeyedDataSource$LoadInitialCallback;", "retryAllFailed", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedDataSource.kt */
public final class FeedDataSource extends PageKeyedDataSource<String, FeedDataItem> {
    private final AdRemoteConfigModel adConfig;
    private final FragmentActivity context;
    private final LandingFeedApi feedApi;
    private final String feedPath;
    private final MutableLiveData<NetworkState> initialLoad = new MutableLiveData<>();
    private boolean isLastItemLoaded;
    private final MutableLiveData<NetworkState> networkState = new MutableLiveData<>();
    private final int pageSize = 20;
    private Function0<? extends Object> retry;
    private final Executor retryExecutor;

    public void loadBefore(PageKeyedDataSource.LoadParams<String> loadParams, PageKeyedDataSource.LoadCallback<String, FeedDataItem> loadCallback) {
        Intrinsics.checkNotNullParameter(loadParams, NativeProtocol.WEB_DIALOG_PARAMS);
        Intrinsics.checkNotNullParameter(loadCallback, "callback");
    }

    public FeedDataSource(LandingFeedApi landingFeedApi, Executor executor, AdRemoteConfigModel adRemoteConfigModel, FragmentActivity fragmentActivity, String str) {
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

    public final MutableLiveData<NetworkState> getNetworkState() {
        return this.networkState;
    }

    public final MutableLiveData<NetworkState> getInitialLoad() {
        return this.initialLoad;
    }

    public final boolean isLastItemLoaded() {
        return this.isLastItemLoaded;
    }

    public final void setLastItemLoaded(boolean z) {
        this.isLastItemLoaded = z;
    }

    public final void retryAllFailed() {
        Function0<? extends Object> function0 = this.retry;
        this.retry = null;
        if (function0 != null) {
            this.retryExecutor.execute(new FeedDataSource$retryAllFailed$1$1(function0));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0095, code lost:
        r2 = kotlin.collections.CollectionsKt.filterNotNull((r2 = r2.getData()));
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004f A[Catch:{ all -> 0x00c5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadInitial(androidx.paging.PageKeyedDataSource.LoadInitialParams<java.lang.String> r12, androidx.paging.PageKeyedDataSource.LoadInitialCallback<java.lang.String, com.medscape.android.landingfeed.model.FeedDataItem> r13) {
        /*
            r11 = this;
            java.lang.String r0 = "params"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "callback"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            r0 = 0
            r11.isLastItemLoaded = r0
            androidx.fragment.app.FragmentActivity r1 = r11.context
            androidx.lifecycle.ViewModelProvider r1 = androidx.lifecycle.ViewModelProviders.of((androidx.fragment.app.FragmentActivity) r1)
            java.lang.Class<com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel> r2 = com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel.class
            androidx.lifecycle.ViewModel r1 = r1.get(r2)
            java.lang.String r2 = "ViewModelProviders.of(co…eedViewModel::class.java)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r7 = r1
            com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel r7 = (com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel) r7
            androidx.lifecycle.MutableLiveData<com.medscape.android.landingfeed.repository.NetworkState> r1 = r11.networkState
            com.medscape.android.landingfeed.repository.NetworkState$Companion r2 = com.medscape.android.landingfeed.repository.NetworkState.Companion
            com.medscape.android.landingfeed.repository.NetworkState r2 = r2.getLOADING()
            r1.postValue(r2)
            androidx.lifecycle.MutableLiveData<com.medscape.android.landingfeed.repository.NetworkState> r1 = r11.initialLoad
            com.medscape.android.landingfeed.repository.NetworkState$Companion r2 = com.medscape.android.landingfeed.repository.NetworkState.Companion
            com.medscape.android.landingfeed.repository.NetworkState r2 = r2.getLOADING()
            r1.postValue(r2)
            r1 = 0
            com.medscape.android.landingfeed.api.LandingFeedApi r2 = r11.feedApi     // Catch:{ all -> 0x00c5 }
            if (r2 == 0) goto L_0x00c9
            java.lang.String r2 = r11.feedPath     // Catch:{ all -> 0x00c5 }
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ all -> 0x00c5 }
            r9 = 1
            if (r2 == 0) goto L_0x004c
            int r2 = r2.length()     // Catch:{ all -> 0x00c5 }
            if (r2 != 0) goto L_0x004a
            goto L_0x004c
        L_0x004a:
            r2 = 0
            goto L_0x004d
        L_0x004c:
            r2 = 1
        L_0x004d:
            if (r2 != 0) goto L_0x00c9
            com.medscape.android.landingfeed.api.LandingFeedApi r2 = r11.feedApi     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = r11.feedPath     // Catch:{ all -> 0x00c5 }
            androidx.fragment.app.FragmentActivity r4 = r11.context     // Catch:{ all -> 0x00c5 }
            android.content.Context r4 = (android.content.Context) r4     // Catch:{ all -> 0x00c5 }
            com.medscape.android.Settings r4 = com.medscape.android.Settings.singleton(r4)     // Catch:{ all -> 0x00c5 }
            java.lang.String r5 = "pref_cookie_string"
            java.lang.String r6 = ""
            java.lang.String r4 = r4.getSetting((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ all -> 0x00c5 }
            java.lang.String r5 = "Settings.singleton(conte…s.PREF_COOKIE_STRING, \"\")"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch:{ all -> 0x00c5 }
            com.medscape.android.landingfeed.model.LandingFeedApiRequest r5 = new com.medscape.android.landingfeed.model.LandingFeedApiRequest     // Catch:{ all -> 0x00c5 }
            androidx.fragment.app.FragmentActivity r6 = r11.context     // Catch:{ all -> 0x00c5 }
            android.content.Context r6 = (android.content.Context) r6     // Catch:{ all -> 0x00c5 }
            java.lang.String r6 = r7.getCurrentSpecialityIDwithFallBack(r6)     // Catch:{ all -> 0x00c5 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ all -> 0x00c5 }
            int r8 = r11.pageSize     // Catch:{ all -> 0x00c5 }
            java.lang.String r10 = r7.getApiFeedType()     // Catch:{ all -> 0x00c5 }
            r5.<init>(r6, r9, r8, r10)     // Catch:{ all -> 0x00c5 }
            retrofit2.Call r2 = r2.getLandingContent(r3, r4, r5)     // Catch:{ all -> 0x00c5 }
            retrofit2.Response r2 = r2.execute()     // Catch:{ all -> 0x00c5 }
            java.lang.Object r2 = r2.body()     // Catch:{ all -> 0x00c5 }
            com.medscape.android.landingfeed.model.FeedItems r2 = (com.medscape.android.landingfeed.model.FeedItems) r2     // Catch:{ all -> 0x00c5 }
            if (r2 == 0) goto L_0x00a5
            java.util.List r2 = r2.getData()     // Catch:{ all -> 0x00c5 }
            if (r2 == 0) goto L_0x00a5
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch:{ all -> 0x00c5 }
            java.util.List r2 = kotlin.collections.CollectionsKt.filterNotNull(r2)     // Catch:{ all -> 0x00c5 }
            if (r2 == 0) goto L_0x00a5
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x00c5 }
            java.util.List r2 = kotlin.collections.CollectionsKt.toMutableList(r2)     // Catch:{ all -> 0x00c5 }
            r5 = r2
            goto L_0x00a6
        L_0x00a5:
            r5 = r1
        L_0x00a6:
            if (r5 == 0) goto L_0x00c9
            int r2 = r5.size()     // Catch:{ all -> 0x00c5 }
            if (r2 <= 0) goto L_0x00c9
            r7.setMNumberOfAds(r0)     // Catch:{ all -> 0x00c5 }
            com.medscape.android.landingfeed.repository.FeedRepository$Companion r3 = com.medscape.android.landingfeed.repository.FeedRepository.Companion     // Catch:{ all -> 0x00c5 }
            androidx.fragment.app.FragmentActivity r4 = r11.context     // Catch:{ all -> 0x00c5 }
            com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel r6 = r11.adConfig     // Catch:{ all -> 0x00c5 }
            r8 = 1
            java.util.List r2 = r3.insertExtras(r4, r5, r6, r7, r8)     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = "0"
            java.lang.String r4 = "2"
            r13.onResult(r2, r3, r4)     // Catch:{ all -> 0x00c5 }
            r0 = 1
            goto L_0x00c9
        L_0x00c5:
            r2 = move-exception
            r2.printStackTrace()
        L_0x00c9:
            if (r0 == 0) goto L_0x00e6
            kotlin.jvm.functions.Function0 r1 = (kotlin.jvm.functions.Function0) r1
            r11.retry = r1
            androidx.lifecycle.MutableLiveData<com.medscape.android.landingfeed.repository.NetworkState> r12 = r11.networkState
            com.medscape.android.landingfeed.repository.NetworkState$Companion r13 = com.medscape.android.landingfeed.repository.NetworkState.Companion
            com.medscape.android.landingfeed.repository.NetworkState r13 = r13.getLOADED()
            r12.postValue(r13)
            androidx.lifecycle.MutableLiveData<com.medscape.android.landingfeed.repository.NetworkState> r12 = r11.initialLoad
            com.medscape.android.landingfeed.repository.NetworkState$Companion r13 = com.medscape.android.landingfeed.repository.NetworkState.Companion
            com.medscape.android.landingfeed.repository.NetworkState r13 = r13.getLOADED()
            r12.postValue(r13)
            goto L_0x0103
        L_0x00e6:
            com.medscape.android.landingfeed.repository.FeedDataSource$loadInitial$2 r0 = new com.medscape.android.landingfeed.repository.FeedDataSource$loadInitial$2
            r0.<init>(r11, r12, r13)
            kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
            r11.retry = r0
            com.medscape.android.landingfeed.repository.NetworkState$Companion r12 = com.medscape.android.landingfeed.repository.NetworkState.Companion
            java.lang.String r13 = r11.getNetworkErrorMsg()
            com.medscape.android.landingfeed.repository.NetworkState r12 = r12.error(r13)
            androidx.lifecycle.MutableLiveData<com.medscape.android.landingfeed.repository.NetworkState> r13 = r11.networkState
            r13.postValue(r12)
            androidx.lifecycle.MutableLiveData<com.medscape.android.landingfeed.repository.NetworkState> r13 = r11.initialLoad
            r13.postValue(r12)
        L_0x0103:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.repository.FeedDataSource.loadInitial(androidx.paging.PageKeyedDataSource$LoadInitialParams, androidx.paging.PageKeyedDataSource$LoadInitialCallback):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005e A[Catch:{ all -> 0x00dc }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadAfter(androidx.paging.PageKeyedDataSource.LoadParams<java.lang.String> r13, androidx.paging.PageKeyedDataSource.LoadCallback<java.lang.String, com.medscape.android.landingfeed.model.FeedDataItem> r14) {
        /*
            r12 = this;
            java.lang.String r0 = "params"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "callback"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            boolean r0 = r12.isLastItemLoaded
            if (r0 == 0) goto L_0x000f
            return
        L_0x000f:
            androidx.fragment.app.FragmentActivity r0 = r12.context
            androidx.lifecycle.ViewModelProvider r0 = androidx.lifecycle.ViewModelProviders.of((androidx.fragment.app.FragmentActivity) r0)
            java.lang.Class<com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel> r1 = com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel.class
            androidx.lifecycle.ViewModel r0 = r0.get(r1)
            java.lang.String r1 = "ViewModelProviders.of(co…eedViewModel::class.java)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r6 = r0
            com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel r6 = (com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel) r6
            androidx.lifecycle.MutableLiveData<com.medscape.android.landingfeed.repository.NetworkState> r0 = r12.networkState
            com.medscape.android.landingfeed.repository.NetworkState$Companion r1 = com.medscape.android.landingfeed.repository.NetworkState.Companion
            com.medscape.android.landingfeed.repository.NetworkState r1 = r1.getLOADING()
            r0.postValue(r1)
            Key r0 = r13.key
            java.lang.String r0 = (java.lang.String) r0
            r1 = 0
            if (r0 == 0) goto L_0x003e
            int r0 = java.lang.Integer.parseInt(r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L_0x003f
        L_0x003e:
            r0 = r1
        L_0x003f:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            int r0 = r0.intValue()
            r8 = 0
            r9 = 1
            com.medscape.android.landingfeed.api.LandingFeedApi r2 = r12.feedApi     // Catch:{ all -> 0x00dc }
            if (r2 == 0) goto L_0x00e0
            java.lang.String r2 = r12.feedPath     // Catch:{ all -> 0x00dc }
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ all -> 0x00dc }
            if (r2 == 0) goto L_0x005b
            int r2 = r2.length()     // Catch:{ all -> 0x00dc }
            if (r2 != 0) goto L_0x0059
            goto L_0x005b
        L_0x0059:
            r2 = 0
            goto L_0x005c
        L_0x005b:
            r2 = 1
        L_0x005c:
            if (r2 != 0) goto L_0x00e0
            com.medscape.android.landingfeed.api.LandingFeedApi r2 = r12.feedApi     // Catch:{ all -> 0x00dc }
            java.lang.String r3 = r12.feedPath     // Catch:{ all -> 0x00dc }
            androidx.fragment.app.FragmentActivity r4 = r12.context     // Catch:{ all -> 0x00dc }
            android.content.Context r4 = (android.content.Context) r4     // Catch:{ all -> 0x00dc }
            com.medscape.android.Settings r4 = com.medscape.android.Settings.singleton(r4)     // Catch:{ all -> 0x00dc }
            java.lang.String r5 = "pref_cookie_string"
            java.lang.String r7 = ""
            java.lang.String r4 = r4.getSetting((java.lang.String) r5, (java.lang.String) r7)     // Catch:{ all -> 0x00dc }
            java.lang.String r5 = "Settings.singleton(conte…s.PREF_COOKIE_STRING, \"\")"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch:{ all -> 0x00dc }
            com.medscape.android.landingfeed.model.LandingFeedApiRequest r5 = new com.medscape.android.landingfeed.model.LandingFeedApiRequest     // Catch:{ all -> 0x00dc }
            androidx.fragment.app.FragmentActivity r7 = r12.context     // Catch:{ all -> 0x00dc }
            android.content.Context r7 = (android.content.Context) r7     // Catch:{ all -> 0x00dc }
            java.lang.String r7 = r6.getCurrentSpecialityIDwithFallBack(r7)     // Catch:{ all -> 0x00dc }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ all -> 0x00dc }
            int r10 = r12.pageSize     // Catch:{ all -> 0x00dc }
            java.lang.String r11 = r6.getApiFeedType()     // Catch:{ all -> 0x00dc }
            r5.<init>(r7, r0, r10, r11)     // Catch:{ all -> 0x00dc }
            retrofit2.Call r2 = r2.getLandingContent(r3, r4, r5)     // Catch:{ all -> 0x00dc }
            retrofit2.Response r2 = r2.execute()     // Catch:{ all -> 0x00dc }
            java.lang.Object r2 = r2.body()     // Catch:{ all -> 0x00dc }
            com.medscape.android.landingfeed.model.FeedItems r2 = (com.medscape.android.landingfeed.model.FeedItems) r2     // Catch:{ all -> 0x00dc }
            if (r2 == 0) goto L_0x00b4
            java.util.List r2 = r2.getData()     // Catch:{ all -> 0x00dc }
            if (r2 == 0) goto L_0x00b4
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch:{ all -> 0x00dc }
            java.util.List r2 = kotlin.collections.CollectionsKt.filterNotNull(r2)     // Catch:{ all -> 0x00dc }
            if (r2 == 0) goto L_0x00b4
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x00dc }
            java.util.List r2 = kotlin.collections.CollectionsKt.toMutableList(r2)     // Catch:{ all -> 0x00dc }
            r4 = r2
            goto L_0x00b5
        L_0x00b4:
            r4 = r1
        L_0x00b5:
            if (r4 == 0) goto L_0x00e0
            int r2 = r4.size()     // Catch:{ all -> 0x00dc }
            if (r2 != 0) goto L_0x00c7
            r12.isLastItemLoaded = r9     // Catch:{ all -> 0x00dc }
            com.medscape.android.landingfeed.model.FeedNoMoreItems r2 = new com.medscape.android.landingfeed.model.FeedNoMoreItems     // Catch:{ all -> 0x00dc }
            r2.<init>()     // Catch:{ all -> 0x00dc }
            r4.add(r2)     // Catch:{ all -> 0x00dc }
        L_0x00c7:
            com.medscape.android.landingfeed.repository.FeedRepository$Companion r2 = com.medscape.android.landingfeed.repository.FeedRepository.Companion     // Catch:{ all -> 0x00dc }
            androidx.fragment.app.FragmentActivity r3 = r12.context     // Catch:{ all -> 0x00dc }
            com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel r5 = r12.adConfig     // Catch:{ all -> 0x00dc }
            r7 = 0
            java.util.List r2 = r2.insertExtras(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00dc }
            int r0 = r0 + r9
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x00dc }
            r14.onResult(r2, r0)     // Catch:{ all -> 0x00dc }
            r8 = 1
            goto L_0x00e0
        L_0x00dc:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00e0:
            if (r8 == 0) goto L_0x00f2
            kotlin.jvm.functions.Function0 r1 = (kotlin.jvm.functions.Function0) r1
            r12.retry = r1
            androidx.lifecycle.MutableLiveData<com.medscape.android.landingfeed.repository.NetworkState> r13 = r12.networkState
            com.medscape.android.landingfeed.repository.NetworkState$Companion r14 = com.medscape.android.landingfeed.repository.NetworkState.Companion
            com.medscape.android.landingfeed.repository.NetworkState r14 = r14.getLOADED()
            r13.postValue(r14)
            goto L_0x010a
        L_0x00f2:
            com.medscape.android.landingfeed.repository.FeedDataSource$loadAfter$2 r0 = new com.medscape.android.landingfeed.repository.FeedDataSource$loadAfter$2
            r0.<init>(r12, r13, r14)
            kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
            r12.retry = r0
            com.medscape.android.landingfeed.repository.NetworkState$Companion r13 = com.medscape.android.landingfeed.repository.NetworkState.Companion
            java.lang.String r14 = r12.getNetworkErrorMsg()
            com.medscape.android.landingfeed.repository.NetworkState r13 = r13.error(r14)
            androidx.lifecycle.MutableLiveData<com.medscape.android.landingfeed.repository.NetworkState> r14 = r12.networkState
            r14.postValue(r13)
        L_0x010a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.repository.FeedDataSource.loadAfter(androidx.paging.PageKeyedDataSource$LoadParams, androidx.paging.PageKeyedDataSource$LoadCallback):void");
    }

    private final String getNetworkErrorMsg() {
        String string = this.context.getString(Util.isOnline(this.context) ? R.string.connection_error_message : R.string.internet_required);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(error)");
        return string;
    }
}

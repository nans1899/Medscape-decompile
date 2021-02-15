package com.medscape.android.landingfeed.model;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import com.medscape.android.landingfeed.repository.NetworkState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002BQ\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\rJ\u0015\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003Je\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0014\b\u0002\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u00042\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0012\"\u0004\b\u0015\u0010\u0016¨\u0006$"}, d2 = {"Lcom/medscape/android/landingfeed/model/FeedListing;", "T", "", "pagedList", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "networkState", "Lcom/medscape/android/landingfeed/repository/NetworkState;", "refreshState", "refresh", "Lkotlin/Function0;", "", "retry", "(Landroidx/lifecycle/LiveData;Landroidx/lifecycle/LiveData;Landroidx/lifecycle/LiveData;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getNetworkState", "()Landroidx/lifecycle/LiveData;", "getPagedList", "getRefresh", "()Lkotlin/jvm/functions/Function0;", "getRefreshState", "getRetry", "setRetry", "(Lkotlin/jvm/functions/Function0;)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedListing.kt */
public final class FeedListing<T> {
    private final LiveData<NetworkState> networkState;
    private final LiveData<PagedList<T>> pagedList;
    private final Function0<Unit> refresh;
    private final LiveData<NetworkState> refreshState;
    private Function0<Unit> retry;

    public static /* synthetic */ FeedListing copy$default(FeedListing feedListing, LiveData<PagedList<T>> liveData, LiveData<NetworkState> liveData2, LiveData<NetworkState> liveData3, Function0<Unit> function0, Function0<Unit> function02, int i, Object obj) {
        if ((i & 1) != 0) {
            liveData = feedListing.pagedList;
        }
        if ((i & 2) != 0) {
            liveData2 = feedListing.networkState;
        }
        LiveData<NetworkState> liveData4 = liveData2;
        if ((i & 4) != 0) {
            liveData3 = feedListing.refreshState;
        }
        LiveData<NetworkState> liveData5 = liveData3;
        if ((i & 8) != 0) {
            function0 = feedListing.refresh;
        }
        Function0<Unit> function03 = function0;
        if ((i & 16) != 0) {
            function02 = feedListing.retry;
        }
        return feedListing.copy(liveData, liveData4, liveData5, function03, function02);
    }

    public final LiveData<PagedList<T>> component1() {
        return this.pagedList;
    }

    public final LiveData<NetworkState> component2() {
        return this.networkState;
    }

    public final LiveData<NetworkState> component3() {
        return this.refreshState;
    }

    public final Function0<Unit> component4() {
        return this.refresh;
    }

    public final Function0<Unit> component5() {
        return this.retry;
    }

    public final FeedListing<T> copy(LiveData<PagedList<T>> liveData, LiveData<NetworkState> liveData2, LiveData<NetworkState> liveData3, Function0<Unit> function0, Function0<Unit> function02) {
        Intrinsics.checkNotNullParameter(liveData, "pagedList");
        Intrinsics.checkNotNullParameter(liveData2, "networkState");
        Intrinsics.checkNotNullParameter(liveData3, "refreshState");
        Intrinsics.checkNotNullParameter(function0, "refresh");
        Intrinsics.checkNotNullParameter(function02, "retry");
        return new FeedListing(liveData, liveData2, liveData3, function0, function02);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FeedListing)) {
            return false;
        }
        FeedListing feedListing = (FeedListing) obj;
        return Intrinsics.areEqual((Object) this.pagedList, (Object) feedListing.pagedList) && Intrinsics.areEqual((Object) this.networkState, (Object) feedListing.networkState) && Intrinsics.areEqual((Object) this.refreshState, (Object) feedListing.refreshState) && Intrinsics.areEqual((Object) this.refresh, (Object) feedListing.refresh) && Intrinsics.areEqual((Object) this.retry, (Object) feedListing.retry);
    }

    public int hashCode() {
        LiveData<PagedList<T>> liveData = this.pagedList;
        int i = 0;
        int hashCode = (liveData != null ? liveData.hashCode() : 0) * 31;
        LiveData<NetworkState> liveData2 = this.networkState;
        int hashCode2 = (hashCode + (liveData2 != null ? liveData2.hashCode() : 0)) * 31;
        LiveData<NetworkState> liveData3 = this.refreshState;
        int hashCode3 = (hashCode2 + (liveData3 != null ? liveData3.hashCode() : 0)) * 31;
        Function0<Unit> function0 = this.refresh;
        int hashCode4 = (hashCode3 + (function0 != null ? function0.hashCode() : 0)) * 31;
        Function0<Unit> function02 = this.retry;
        if (function02 != null) {
            i = function02.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return "FeedListing(pagedList=" + this.pagedList + ", networkState=" + this.networkState + ", refreshState=" + this.refreshState + ", refresh=" + this.refresh + ", retry=" + this.retry + ")";
    }

    public FeedListing(LiveData<PagedList<T>> liveData, LiveData<NetworkState> liveData2, LiveData<NetworkState> liveData3, Function0<Unit> function0, Function0<Unit> function02) {
        Intrinsics.checkNotNullParameter(liveData, "pagedList");
        Intrinsics.checkNotNullParameter(liveData2, "networkState");
        Intrinsics.checkNotNullParameter(liveData3, "refreshState");
        Intrinsics.checkNotNullParameter(function0, "refresh");
        Intrinsics.checkNotNullParameter(function02, "retry");
        this.pagedList = liveData;
        this.networkState = liveData2;
        this.refreshState = liveData3;
        this.refresh = function0;
        this.retry = function02;
    }

    public final LiveData<PagedList<T>> getPagedList() {
        return this.pagedList;
    }

    public final LiveData<NetworkState> getNetworkState() {
        return this.networkState;
    }

    public final LiveData<NetworkState> getRefreshState() {
        return this.refreshState;
    }

    public final Function0<Unit> getRefresh() {
        return this.refresh;
    }

    public final Function0<Unit> getRetry() {
        return this.retry;
    }

    public final void setRetry(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.retry = function0;
    }
}

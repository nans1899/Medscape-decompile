package com.medscape.android.landingfeed.repository;

import androidx.paging.PageKeyedDataSource;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: FeedDataSource.kt */
final class FeedDataSource$loadAfter$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ PageKeyedDataSource.LoadCallback $callback;
    final /* synthetic */ PageKeyedDataSource.LoadParams $params;
    final /* synthetic */ FeedDataSource this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FeedDataSource$loadAfter$2(FeedDataSource feedDataSource, PageKeyedDataSource.LoadParams loadParams, PageKeyedDataSource.LoadCallback loadCallback) {
        super(0);
        this.this$0 = feedDataSource;
        this.$params = loadParams;
        this.$callback = loadCallback;
    }

    public final void invoke() {
        this.this$0.loadAfter(this.$params, this.$callback);
    }
}

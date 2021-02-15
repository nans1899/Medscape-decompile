package com.wbmd.ads;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/wbmd/ads/BaseAdListAdapter$onAttachedToRecyclerView$1", "Lcom/wbmd/ads/ScrollSpeedRecycleViewScrollListener;", "listNeedsRefresh", "", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: BaseAdListAdapter.kt */
public final class BaseAdListAdapter$onAttachedToRecyclerView$1 extends ScrollSpeedRecycleViewScrollListener {
    final /* synthetic */ BaseAdListAdapter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseAdListAdapter$onAttachedToRecyclerView$1(BaseAdListAdapter baseAdListAdapter, int i) {
        super(i);
        this.this$0 = baseAdListAdapter;
    }

    public void listNeedsRefresh() {
        this.this$0.notifyDataSetChanged();
    }
}

package com.wbmd.ads;

import com.wbmd.ads.model.AdContainer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"com/wbmd/ads/BaseAdListAdapter$loadAD$1", "Lcom/wbmd/ads/IAdListener;", "onAdFailed", "", "adContainer", "Lcom/wbmd/ads/model/AdContainer;", "errorCode", "", "onAdLoaded", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: BaseAdListAdapter.kt */
public final class BaseAdListAdapter$loadAD$1 implements IAdListener {
    final /* synthetic */ BaseAdListAdapter this$0;

    BaseAdListAdapter$loadAD$1(BaseAdListAdapter baseAdListAdapter) {
        this.this$0 = baseAdListAdapter;
    }

    public void onAdLoaded(AdContainer adContainer) {
        Intrinsics.checkNotNullParameter(adContainer, "adContainer");
        BaseAdListAdapter baseAdListAdapter = this.this$0;
        baseAdListAdapter.pendingAdRequestCount = baseAdListAdapter.pendingAdRequestCount - 1;
        this.this$0.mAdCache.add(adContainer);
        IAdListener adListener = this.this$0.getAdListener();
        if (adListener != null) {
            adListener.onAdLoaded(adContainer);
        }
        if (!this.this$0.adRequestStack.isEmpty()) {
            BaseAdListAdapter baseAdListAdapter2 = this.this$0;
            Object pop = baseAdListAdapter2.adRequestStack.pop();
            Intrinsics.checkNotNullExpressionValue(pop, "adRequestStack.pop()");
            baseAdListAdapter2.notifyItemChanged(((Number) pop).intValue());
            return;
        }
        this.this$0.notifyDataSetChanged();
    }

    public void onAdFailed(AdContainer adContainer, int i) {
        Intrinsics.checkNotNullParameter(adContainer, "adContainer");
        BaseAdListAdapter baseAdListAdapter = this.this$0;
        baseAdListAdapter.pendingAdRequestCount = baseAdListAdapter.pendingAdRequestCount - 1;
        IAdListener adListener = this.this$0.getAdListener();
        if (adListener != null) {
            adListener.onAdFailed(adContainer, i);
        }
    }
}

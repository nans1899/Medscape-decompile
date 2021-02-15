package com.wbmd.ads.bidding;

import android.content.Context;
import com.wbmd.ads.IAdParams;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH&J\b\u0010\f\u001a\u00020\u0003H&¨\u0006\r"}, d2 = {"Lcom/wbmd/ads/bidding/IAdBiddingProvider;", "", "canPerformBidding", "", "getBidsForRequest", "", "context", "Landroid/content/Context;", "adParams", "Lcom/wbmd/ads/IAdParams;", "completion", "Lkotlin/Function0;", "shouldCacheBids", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: IAdBiddingProvider.kt */
public interface IAdBiddingProvider {
    boolean canPerformBidding();

    void getBidsForRequest(Context context, IAdParams iAdParams, Function0<Unit> function0);

    boolean shouldCacheBids();
}

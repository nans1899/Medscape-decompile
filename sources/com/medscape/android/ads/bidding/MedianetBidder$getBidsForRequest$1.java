package com.medscape.android.ads.bidding;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.wbmd.qxcalculator.util.Log;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import net.media.android.bidder.dfp.callback.DfpBidderCallback;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "onCompleted"}, k = 3, mv = {1, 4, 0})
/* compiled from: MedianetBidder.kt */
final class MedianetBidder$getBidsForRequest$1 implements DfpBidderCallback {
    final /* synthetic */ Function0 $completion;
    final /* synthetic */ PublisherAdRequest $publisherAdRequest;
    final /* synthetic */ MedianetBidder this$0;

    MedianetBidder$getBidsForRequest$1(MedianetBidder medianetBidder, PublisherAdRequest publisherAdRequest, Function0 function0) {
        this.this$0 = medianetBidder;
        this.$publisherAdRequest = publisherAdRequest;
        this.$completion = function0;
    }

    public final void onCompleted() {
        this.this$0.setBids(this.$publisherAdRequest.getNetworkExtrasBundle(AdMobAdapter.class));
        this.$completion.invoke();
        Log.d("AdBidding", "MedianetBidder completed");
    }
}

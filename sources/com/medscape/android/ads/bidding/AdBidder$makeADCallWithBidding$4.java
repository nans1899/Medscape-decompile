package com.medscape.android.ads.bidding;

import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.medscape.android.ads.NativeAdAction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import net.media.android.bidder.dfp.callback.DfpBidderCallback;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "onCompleted"}, k = 3, mv = {1, 4, 0})
/* compiled from: AdBidder.kt */
final class AdBidder$makeADCallWithBidding$4 implements DfpBidderCallback {
    final /* synthetic */ NativeAdAction $adAction;
    final /* synthetic */ Ref.IntRef $i;
    final /* synthetic */ PublisherAdRequest $publisherAdRequest;
    final /* synthetic */ AdBidder this$0;

    AdBidder$makeADCallWithBidding$4(AdBidder adBidder, Ref.IntRef intRef, NativeAdAction nativeAdAction, PublisherAdRequest publisherAdRequest) {
        this.this$0 = adBidder;
        this.$i = intRef;
        this.$adAction = nativeAdAction;
        this.$publisherAdRequest = publisherAdRequest;
    }

    public final void onCompleted() {
        Ref.IntRef intRef = this.$i;
        intRef.element--;
        if (this.$i.element == 0) {
            NativeAdAction nativeAdAction = this.$adAction;
            PublisherAdRequest publisherAdRequest = this.$publisherAdRequest;
            Intrinsics.checkNotNullExpressionValue(publisherAdRequest, "publisherAdRequest");
            nativeAdAction.makeADRequestAfterBidding(publisherAdRequest, this.this$0.proclivityMap);
        }
    }
}

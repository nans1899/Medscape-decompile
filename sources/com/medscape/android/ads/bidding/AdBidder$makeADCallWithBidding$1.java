package com.medscape.android.ads.bidding;

import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.ads.proclivity.IProclivityCompleteListener;
import com.medscape.android.ads.proclivity.ProclivityDataModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00060\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "proclivityData", "", "Lcom/medscape/android/ads/proclivity/ProclivityDataModel;", "kotlin.jvm.PlatformType", "", "onProclivityCompleted"}, k = 3, mv = {1, 4, 0})
/* compiled from: AdBidder.kt */
final class AdBidder$makeADCallWithBidding$1 implements IProclivityCompleteListener {
    final /* synthetic */ DFPAdAction $adAction;
    final /* synthetic */ PublisherAdRequest $adRequest;
    final /* synthetic */ Ref.IntRef $i;
    final /* synthetic */ AdBidder this$0;

    AdBidder$makeADCallWithBidding$1(AdBidder adBidder, Ref.IntRef intRef, DFPAdAction dFPAdAction, PublisherAdRequest publisherAdRequest) {
        this.this$0 = adBidder;
        this.$i = intRef;
        this.$adAction = dFPAdAction;
        this.$adRequest = publisherAdRequest;
    }

    public final void onProclivityCompleted(List<ProclivityDataModel> list) {
        this.this$0.proclivityMap.clear();
        this.this$0.proclivityMap.addAll(list);
        Ref.IntRef intRef = this.$i;
        intRef.element--;
        if (this.$i.element == 0) {
            this.$adAction.makeADRequestAfterBidding(this.$adRequest, this.this$0.proclivityMap);
        }
    }
}

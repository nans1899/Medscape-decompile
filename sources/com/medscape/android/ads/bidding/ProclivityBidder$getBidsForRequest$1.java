package com.medscape.android.ads.bidding;

import android.os.Bundle;
import com.medscape.android.ads.proclivity.IProclivityCompleteListener;
import com.medscape.android.ads.proclivity.ProclivityDataModel;
import com.medscape.android.ads.proclivity.ProclivityUtils;
import com.wbmd.ads.IAdParams;
import com.wbmd.qxcalculator.util.Log;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00060\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "proclivityData", "", "Lcom/medscape/android/ads/proclivity/ProclivityDataModel;", "kotlin.jvm.PlatformType", "", "onProclivityCompleted"}, k = 3, mv = {1, 4, 0})
/* compiled from: ProclivityBidder.kt */
final class ProclivityBidder$getBidsForRequest$1 implements IProclivityCompleteListener {
    final /* synthetic */ IAdParams $adParams;
    final /* synthetic */ Function0 $completion;
    final /* synthetic */ ProclivityBidder this$0;

    ProclivityBidder$getBidsForRequest$1(ProclivityBidder proclivityBidder, IAdParams iAdParams, Function0 function0) {
        this.this$0 = proclivityBidder;
        this.$adParams = iAdParams;
        this.$completion = function0;
    }

    public final void onProclivityCompleted(List<ProclivityDataModel> list) {
        this.this$0.setBids(new Bundle());
        Map<String, String> proclivityMap = ProclivityUtils.getProclivityMap(list, this.$adParams.getAdSizes());
        Intrinsics.checkNotNullExpressionValue(list, "proclivityData");
        if (!list.isEmpty()) {
            for (String next : proclivityMap.keySet()) {
                Bundle bids = this.this$0.getBids();
                if (bids != null) {
                    bids.putString(next, proclivityMap.get(next));
                }
            }
        }
        this.$completion.invoke();
        Log.d("AdBidding", "ProclivityBidder completed");
    }
}

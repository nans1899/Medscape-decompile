package com.medscape.android.ads.bidding;

import android.content.Context;
import com.medscape.android.ads.proclivity.ProclivityUtils;
import com.medscape.android.capabilities.CapabilitiesManager;
import com.wbmd.ads.AdManager;
import com.wbmd.ads.IAdParams;
import com.wbmd.ads.bidding.AdBiddingProvider;
import com.wbmd.qxcalculator.util.Log;
import com.webmd.wbmdcmepulse.models.CPEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J&\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000eH\u0016J\b\u0010\u000f\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/medscape/android/ads/bidding/ProclivityBidder;", "Lcom/wbmd/ads/bidding/AdBiddingProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "canPerformBidding", "", "getBidsForRequest", "", "adParams", "Lcom/wbmd/ads/IAdParams;", "completion", "Lkotlin/Function0;", "shouldCacheBids", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ProclivityBidder.kt */
public final class ProclivityBidder extends AdBiddingProvider {
    private final Context context;

    public boolean shouldCacheBids() {
        return true;
    }

    public ProclivityBidder(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    public final Context getContext() {
        return this.context;
    }

    public void getBidsForRequest(Context context2, IAdParams iAdParams, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(iAdParams, "adParams");
        Intrinsics.checkNotNullParameter(function0, CPEvent.ACTIVITY_NAME_COMPLETION);
        Log.d("AdBidding", "ProclivityBidder started");
        ProclivityUtils.makeProclivityRequest(context2, AdManager.Companion.getADParamsMap(iAdParams), new ProclivityBidder$getBidsForRequest$1(this, iAdParams, function0));
    }

    public boolean canPerformBidding() {
        CapabilitiesManager instance = CapabilitiesManager.getInstance(this.context);
        Intrinsics.checkNotNullExpressionValue(instance, "CapabilitiesManager.getInstance(context)");
        return instance.isProclivityFeatureAvailable();
    }
}

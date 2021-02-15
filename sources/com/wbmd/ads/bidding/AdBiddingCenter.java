package com.wbmd.ads.bidding;

import android.content.Context;
import android.os.Bundle;
import com.wbmd.ads.IAdParams;
import com.webmd.wbmdcmepulse.models.CPEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J*\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\b0\u000eJ)\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0004H@ø\u0001\u0000¢\u0006\u0002\u0010\u0012R\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0004\n\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lcom/wbmd/ads/bidding/AdBiddingCenter;", "", "providers", "", "Lcom/wbmd/ads/bidding/AdBiddingProvider;", "([Lcom/wbmd/ads/bidding/AdBiddingProvider;)V", "[Lcom/wbmd/ads/bidding/AdBiddingProvider;", "getBidData", "", "context", "Landroid/content/Context;", "adParams", "Lcom/wbmd/ads/IAdParams;", "completion", "Lkotlin/Function1;", "Landroid/os/Bundle;", "performBidding", "provider", "(Landroid/content/Context;Lcom/wbmd/ads/IAdParams;Lcom/wbmd/ads/bidding/AdBiddingProvider;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdBiddingCenter.kt */
public final class AdBiddingCenter {
    /* access modifiers changed from: private */
    public static long AD_BIDDING_TIME_OUT = 500;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final AdBiddingProvider[] providers;

    public AdBiddingCenter(AdBiddingProvider[] adBiddingProviderArr) {
        Intrinsics.checkNotNullParameter(adBiddingProviderArr, "providers");
        this.providers = adBiddingProviderArr;
    }

    public final void getBidData(Context context, IAdParams iAdParams, Function1<? super Bundle, Unit> function1) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(iAdParams, "adParams");
        Intrinsics.checkNotNullParameter(function1, CPEvent.ACTIVITY_NAME_COMPLETION);
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new AdBiddingCenter$getBidData$1(this, context, iAdParams, function1, (Continuation) null), 2, (Object) null);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/wbmd/ads/bidding/AdBiddingCenter$Companion;", "", "()V", "AD_BIDDING_TIME_OUT", "", "getAD_BIDDING_TIME_OUT", "()J", "setAD_BIDDING_TIME_OUT", "(J)V", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: AdBiddingCenter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final long getAD_BIDDING_TIME_OUT() {
            return AdBiddingCenter.AD_BIDDING_TIME_OUT;
        }

        public final void setAD_BIDDING_TIME_OUT(long j) {
            AdBiddingCenter.AD_BIDDING_TIME_OUT = j;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object performBidding(Context context, IAdParams iAdParams, AdBiddingProvider adBiddingProvider, Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), (CoroutineStart) null, new AdBiddingCenter$performBidding$$inlined$suspendCancellableCoroutine$lambda$1(cancellableContinuationImpl, (Continuation) null, adBiddingProvider, context, iAdParams), 2, (Object) null);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }
}

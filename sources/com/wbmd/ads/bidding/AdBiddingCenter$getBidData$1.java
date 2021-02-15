package com.wbmd.ads.bidding;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.wbmd.ads.IAdParams;
import com.webmd.wbmdcmepulse.models.CPEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.wbmd.ads.bidding.AdBiddingCenter$getBidData$1", f = "AdBiddingCenter.kt", i = {0}, l = {23}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* compiled from: AdBiddingCenter.kt */
final class AdBiddingCenter$getBidData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ IAdParams $adParams;
    final /* synthetic */ Function1 $completion;
    final /* synthetic */ Context $context;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ AdBiddingCenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AdBiddingCenter$getBidData$1(AdBiddingCenter adBiddingCenter, Context context, IAdParams iAdParams, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = adBiddingCenter;
        this.$context = context;
        this.$adParams = iAdParams;
        this.$completion = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        AdBiddingCenter$getBidData$1 adBiddingCenter$getBidData$1 = new AdBiddingCenter$getBidData$1(this.this$0, this.$context, this.$adParams, this.$completion, continuation);
        adBiddingCenter$getBidData$1.p$ = (CoroutineScope) obj;
        return adBiddingCenter$getBidData$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((AdBiddingCenter$getBidData$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Bundle bids;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            Log.d("AdBidding", "Initiated");
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = TimeoutKt.withTimeoutOrNull(AdBiddingCenter.Companion.getAD_BIDDING_TIME_OUT(), new AdBiddingCenter$getBidData$1$result$1(this, (Continuation) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Log.d("AdBidding", "Bidding complete " + ((List) obj));
        Bundle bundle = new Bundle();
        for (AdBiddingProvider adBiddingProvider : this.this$0.providers) {
            if (adBiddingProvider.getBids() != null && (bids = adBiddingProvider.getBids()) != null && !bids.isEmpty()) {
                bundle.putAll(adBiddingProvider.getBids());
            } else if (adBiddingProvider.getCachedBidsData() != null) {
                bundle.putAll(adBiddingProvider.getCachedBidsData());
            }
        }
        this.$completion.invoke(bundle);
        return Unit.INSTANCE;
    }
}

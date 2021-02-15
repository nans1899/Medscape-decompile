package com.wbmd.ads.bidding;

import android.content.Context;
import android.util.Log;
import com.wbmd.ads.IAdParams;
import com.webmd.wbmdcmepulse.models.CPEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.wbmd.ads.bidding.AdBiddingCenter$getBidData$1$result$1", f = "AdBiddingCenter.kt", i = {0, 0}, l = {32}, m = "invokeSuspend", n = {"$this$withTimeoutOrNull", "providerTasks"}, s = {"L$0", "L$1"})
/* compiled from: AdBiddingCenter.kt */
final class AdBiddingCenter$getBidData$1$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Unit>>, Object> {
    Object L$0;
    Object L$1;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ AdBiddingCenter$getBidData$1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AdBiddingCenter$getBidData$1$result$1(AdBiddingCenter$getBidData$1 adBiddingCenter$getBidData$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = adBiddingCenter$getBidData$1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        AdBiddingCenter$getBidData$1$result$1 adBiddingCenter$getBidData$1$result$1 = new AdBiddingCenter$getBidData$1$result$1(this.this$0, continuation);
        adBiddingCenter$getBidData$1$result$1.p$ = (CoroutineScope) obj;
        return adBiddingCenter$getBidData$1$result$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((AdBiddingCenter$getBidData$1$result$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            ArrayList arrayList = new ArrayList();
            for (final AdBiddingProvider adBiddingProvider : this.this$0.this$0.providers) {
                if (adBiddingProvider.canPerformBidding()) {
                    arrayList.add(BuildersKt__Builders_commonKt.async$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this, (Continuation) null), 3, (Object) null));
                    Log.d("AdBidding", "provider initiated");
                }
            }
            this.L$0 = coroutineScope;
            this.L$1 = arrayList;
            this.label = 1;
            obj = AwaitKt.awaitAll(arrayList, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ArrayList arrayList2 = (ArrayList) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "com.wbmd.ads.bidding.AdBiddingCenter$getBidData$1$result$1$1", f = "AdBiddingCenter.kt", i = {0}, l = {28}, m = "invokeSuspend", n = {"$this$async"}, s = {"L$0"})
    /* renamed from: com.wbmd.ads.bidding.AdBiddingCenter$getBidData$1$result$1$1  reason: invalid class name */
    /* compiled from: AdBiddingCenter.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        private CoroutineScope p$;
        final /* synthetic */ AdBiddingCenter$getBidData$1$result$1 this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, adBiddingProvider, continuation);
            r0.p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                AdBiddingCenter adBiddingCenter = this.this$0.this$0.this$0;
                Context context = this.this$0.this$0.$context;
                IAdParams iAdParams = this.this$0.this$0.$adParams;
                AdBiddingProvider adBiddingProvider = adBiddingProvider;
                this.L$0 = coroutineScope;
                this.label = 1;
                if (adBiddingCenter.performBidding(context, iAdParams, adBiddingProvider, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }
}

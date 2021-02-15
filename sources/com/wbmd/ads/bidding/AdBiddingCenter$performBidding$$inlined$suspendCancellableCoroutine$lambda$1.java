package com.wbmd.ads.bidding;

import android.content.Context;
import com.wbmd.ads.IAdParams;
import com.webmd.wbmdcmepulse.models.CPEvent;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.TimeoutKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/wbmd/ads/bidding/AdBiddingCenter$performBidding$2$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: AdBiddingCenter.kt */
final class AdBiddingCenter$performBidding$$inlined$suspendCancellableCoroutine$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ IAdParams $adParams$inlined;
    final /* synthetic */ Context $context$inlined;
    final /* synthetic */ CancellableContinuation $continuation;
    final /* synthetic */ AdBiddingProvider $provider$inlined;
    Object L$0;
    int label;
    private CoroutineScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AdBiddingCenter$performBidding$$inlined$suspendCancellableCoroutine$lambda$1(CancellableContinuation cancellableContinuation, Continuation continuation, AdBiddingProvider adBiddingProvider, Context context, IAdParams iAdParams) {
        super(2, continuation);
        this.$continuation = cancellableContinuation;
        this.$provider$inlined = adBiddingProvider;
        this.$context$inlined = context;
        this.$adParams$inlined = iAdParams;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        AdBiddingCenter$performBidding$$inlined$suspendCancellableCoroutine$lambda$1 adBiddingCenter$performBidding$$inlined$suspendCancellableCoroutine$lambda$1 = new AdBiddingCenter$performBidding$$inlined$suspendCancellableCoroutine$lambda$1(this.$continuation, continuation, this.$provider$inlined, this.$context$inlined, this.$adParams$inlined);
        adBiddingCenter$performBidding$$inlined$suspendCancellableCoroutine$lambda$1.p$ = (CoroutineScope) obj;
        return adBiddingCenter$performBidding$$inlined$suspendCancellableCoroutine$lambda$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((AdBiddingCenter$performBidding$$inlined$suspendCancellableCoroutine$lambda$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/wbmd/ads/bidding/AdBiddingCenter$performBidding$2$1$1"}, k = 3, mv = {1, 4, 0})
    /* renamed from: com.wbmd.ads.bidding.AdBiddingCenter$performBidding$$inlined$suspendCancellableCoroutine$lambda$1$1  reason: invalid class name */
    /* compiled from: AdBiddingCenter.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        private CoroutineScope p$;
        final /* synthetic */ AdBiddingCenter$performBidding$$inlined$suspendCancellableCoroutine$lambda$1 this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, continuation);
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
                this.this$0.$provider$inlined.getBidsForRequest(this.this$0.$context$inlined, this.this$0.$adParams$inlined, new Function0<Unit>(this) {
                    final /* synthetic */ AnonymousClass1 this$0;

                    {
                        this.this$0 = r1;
                    }

                    public final void invoke() {
                        if (this.this$0.this$0.$continuation.isActive()) {
                            Unit unit = Unit.INSTANCE;
                            Result.Companion companion = Result.Companion;
                            this.this$0.this$0.$continuation.resumeWith(Result.m6constructorimpl(unit));
                        }
                    }
                });
                long ad_bidding_time_out = AdBiddingCenter.Companion.getAD_BIDDING_TIME_OUT();
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(ad_bidding_time_out, this) == coroutine_suspended) {
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

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.p$;
            this.label = 1;
            if (TimeoutKt.withTimeoutOrNull(AdBiddingCenter.Companion.getAD_BIDDING_TIME_OUT(), new AnonymousClass1(this, (Continuation) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (this.$continuation.isActive()) {
            Unit unit = Unit.INSTANCE;
            Result.Companion companion = Result.Companion;
            this.$continuation.resumeWith(Result.m6constructorimpl(unit));
        }
        return Unit.INSTANCE;
    }
}

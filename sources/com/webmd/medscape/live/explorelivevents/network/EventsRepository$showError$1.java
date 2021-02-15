package com.webmd.medscape.live.explorelivevents.network;

import com.webmd.medscape.live.explorelivevents.data.Error;
import com.webmd.medscape.live.explorelivevents.util.ExtensionsKt;
import com.webmd.wbmdcmepulse.models.CPEvent;
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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.webmd.medscape.live.explorelivevents.network.EventsRepository$showError$1", f = "EventsRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: EventsRepository.kt */
final class EventsRepository$showError$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $failureCallback;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ EventsRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EventsRepository$showError$1(EventsRepository eventsRepository, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = eventsRepository;
        this.$failureCallback = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        EventsRepository$showError$1 eventsRepository$showError$1 = new EventsRepository$showError$1(this.this$0, this.$failureCallback, continuation);
        eventsRepository$showError$1.p$ = (CoroutineScope) obj;
        return eventsRepository$showError$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((EventsRepository$showError$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.$failureCallback.invoke(new Error(true, "Something went wrong", ExtensionsKt.getErrorImageRes(0, this.this$0.context)));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}

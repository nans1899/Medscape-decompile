package com.webmd.medscape.live.explorelivevents.ui.adapters;

import com.webmd.wbmdcmepulse.models.CPEvent;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.webmd.medscape.live.explorelivevents.ui.adapters.FiltersRecyclerViewAdapter$selectAll$2", f = "FiltersRecyclerViewAdapter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FiltersRecyclerViewAdapter.kt */
final class FiltersRecyclerViewAdapter$selectAll$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    private CoroutineScope p$;
    final /* synthetic */ FiltersRecyclerViewAdapter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FiltersRecyclerViewAdapter$selectAll$2(FiltersRecyclerViewAdapter filtersRecyclerViewAdapter, Continuation continuation) {
        super(2, continuation);
        this.this$0 = filtersRecyclerViewAdapter;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        FiltersRecyclerViewAdapter$selectAll$2 filtersRecyclerViewAdapter$selectAll$2 = new FiltersRecyclerViewAdapter$selectAll$2(this.this$0, continuation);
        filtersRecyclerViewAdapter$selectAll$2.p$ = (CoroutineScope) obj;
        return filtersRecyclerViewAdapter$selectAll$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FiltersRecyclerViewAdapter$selectAll$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            FiltersRecyclerViewAdapter filtersRecyclerViewAdapter = this.this$0;
            filtersRecyclerViewAdapter.notifyItemRangeChanged(0, filtersRecyclerViewAdapter.filters.size());
            if (!this.this$0.chosenFilters.isEmpty()) {
                this.this$0.applyCallback.invoke(Boxing.boxBoolean(true));
            } else if (!this.this$0.selectedSpecialties.isEmpty()) {
                this.this$0.applyCallback.invoke(Boxing.boxBoolean(true));
            } else {
                this.this$0.applyCallback.invoke(Boxing.boxBoolean(false));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}

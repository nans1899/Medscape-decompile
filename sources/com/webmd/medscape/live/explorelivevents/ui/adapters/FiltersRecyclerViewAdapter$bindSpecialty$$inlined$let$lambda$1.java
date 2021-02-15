package com.webmd.medscape.live.explorelivevents.ui.adapters;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.medscape.live.explorelivevents.data.SearchFilter;
import com.webmd.medscape.live.explorelivevents.util.ExtensionsKt;
import com.webmd.wbmdcmepulse.models.CPEvent;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0007"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/webmd/medscape/live/explorelivevents/ui/adapters/FiltersRecyclerViewAdapter$bindSpecialty$1$1$1", "com/webmd/medscape/live/explorelivevents/ui/adapters/FiltersRecyclerViewAdapter$$special$$inlined$with$lambda$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: FiltersRecyclerViewAdapter.kt */
final class FiltersRecyclerViewAdapter$bindSpecialty$$inlined$let$lambda$1 implements View.OnClickListener {
    final /* synthetic */ RecyclerView.ViewHolder $holder$inlined;
    final /* synthetic */ int $position$inlined;
    final /* synthetic */ SearchFilter $this_with;
    final /* synthetic */ FiltersRecyclerViewAdapter this$0;

    FiltersRecyclerViewAdapter$bindSpecialty$$inlined$let$lambda$1(SearchFilter searchFilter, FiltersRecyclerViewAdapter filtersRecyclerViewAdapter, int i, RecyclerView.ViewHolder viewHolder) {
        this.$this_with = searchFilter;
        this.this$0 = filtersRecyclerViewAdapter;
        this.$position$inlined = i;
        this.$holder$inlined = viewHolder;
    }

    public final void onClick(View view) {
        this.this$0.unselectAllCallback.invoke();
        if (this.this$0.chosenFilters.contains(this.$this_with)) {
            this.this$0.chosenFilters.remove(this.$this_with);
        } else if (this.this$0.selectedSpecialties.contains(this.$this_with)) {
            this.this$0.selectedSpecialties.remove(this.$this_with);
        } else {
            this.this$0.chosenFilters.add(this.$this_with);
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), (CoroutineStart) null, new AnonymousClass1(this, (Continuation) null), 2, (Object) null);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/webmd/medscape/live/explorelivevents/ui/adapters/FiltersRecyclerViewAdapter$bindSpecialty$1$1$1$1", "com/webmd/medscape/live/explorelivevents/ui/adapters/FiltersRecyclerViewAdapter$$special$$inlined$with$lambda$1$1"}, k = 3, mv = {1, 4, 0})
    /* renamed from: com.webmd.medscape.live.explorelivevents.ui.adapters.FiltersRecyclerViewAdapter$bindSpecialty$$inlined$let$lambda$1$1  reason: invalid class name */
    /* compiled from: FiltersRecyclerViewAdapter.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;
        final /* synthetic */ FiltersRecyclerViewAdapter$bindSpecialty$$inlined$let$lambda$1 this$0;

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
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                this.this$0.this$0.notifyItemChanged(this.this$0.$position$inlined);
                this.this$0.this$0.applyCallback.invoke(Boxing.boxBoolean(ExtensionsKt.getTickStatus(this.this$0.this$0.chosenFilters, this.this$0.this$0.selectedSpecialties, this.this$0.this$0.previousSelectedSpecialties)));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}

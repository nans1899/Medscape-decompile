package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "downstream", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2", f = "Delay.kt", i = {0, 0, 0, 0}, l = {188}, m = "invokeSuspend", n = {"$this$scopedFlow", "downstream", "values", "lastValue"}, s = {"L$0", "L$1", "L$2", "L$3"})
/* compiled from: Delay.kt */
final class FlowKt__DelayKt$debounce$2 extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow $this_debounce;
    final /* synthetic */ long $timeoutMillis;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private CoroutineScope p$;
    private FlowCollector p$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowKt__DelayKt$debounce$2(Flow flow, long j, Continuation continuation) {
        super(3, continuation);
        this.$this_debounce = flow;
        this.$timeoutMillis = j;
    }

    public final Continuation<Unit> create(CoroutineScope coroutineScope, FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        FlowKt__DelayKt$debounce$2 flowKt__DelayKt$debounce$2 = new FlowKt__DelayKt$debounce$2(this.$this_debounce, this.$timeoutMillis, continuation);
        flowKt__DelayKt$debounce$2.p$ = coroutineScope;
        flowKt__DelayKt$debounce$2.p$0 = flowCollector;
        return flowKt__DelayKt$debounce$2;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((FlowKt__DelayKt$debounce$2) create((CoroutineScope) obj, (FlowCollector) obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:9|(2:10|11)|12|13|14|(4:16|17|18|19)(1:21)|27|(1:29)|(1:31)(4:32|33|7|(1:34)(0))|31) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:16|17|18|19) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00bb, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00c2, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00c6, code lost:
        r16 = r10;
        r19 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ca, code lost:
        r1.handleBuilderException(r0);
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            r20 = this;
            r1 = r20
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r3 = 1
            if (r2 == 0) goto L_0x0034
            if (r2 != r3) goto L_0x002c
            java.lang.Object r2 = r1.L$4
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2 r2 = (kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2) r2
            java.lang.Object r2 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref.ObjectRef) r2
            java.lang.Object r4 = r1.L$2
            kotlin.jvm.internal.Ref$ObjectRef r4 = (kotlin.jvm.internal.Ref.ObjectRef) r4
            java.lang.Object r5 = r1.L$1
            kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
            java.lang.Object r6 = r1.L$0
            kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
            kotlin.ResultKt.throwOnFailure(r21)
            r11 = r1
            r12 = r2
            r13 = r4
            r14 = r5
            r15 = r6
            r2 = r0
            goto L_0x00df
        L_0x002c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0034:
            kotlin.ResultKt.throwOnFailure(r21)
            kotlinx.coroutines.CoroutineScope r2 = r1.p$
            kotlinx.coroutines.flow.FlowCollector r10 = r1.p$0
            kotlin.jvm.internal.Ref$ObjectRef r11 = new kotlin.jvm.internal.Ref$ObjectRef
            r11.<init>()
            r5 = 0
            r6 = -1
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$values$1 r4 = new kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$values$1
            r12 = 0
            r4.<init>(r1, r12)
            r7 = r4
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = 1
            r9 = 0
            r4 = r2
            kotlinx.coroutines.channels.ReceiveChannel r4 = kotlinx.coroutines.channels.ProduceKt.produce$default(r4, r5, r6, r7, r8, r9)
            r11.element = r4
            kotlin.jvm.internal.Ref$ObjectRef r4 = new kotlin.jvm.internal.Ref$ObjectRef
            r4.<init>()
            r4.element = r12
            r15 = r2
            r12 = r4
            r14 = r10
            r13 = r11
            r2 = r0
            r11 = r1
        L_0x0061:
            T r0 = r12.element
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.flow.internal.NullSurrogateKt.DONE
            if (r0 == r4) goto L_0x00e4
            r11.L$0 = r15
            r11.L$1 = r14
            r11.L$2 = r13
            r11.L$3 = r12
            r11.L$4 = r11
            r11.label = r3
            r10 = r11
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            kotlinx.coroutines.selects.SelectBuilderImpl r9 = new kotlinx.coroutines.selects.SelectBuilderImpl
            r9.<init>(r10)
            r0 = r9
            kotlinx.coroutines.selects.SelectBuilder r0 = (kotlinx.coroutines.selects.SelectBuilder) r0     // Catch:{ all -> 0x00c4 }
            T r4 = r13.element     // Catch:{ all -> 0x00c4 }
            kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4     // Catch:{ all -> 0x00c4 }
            kotlinx.coroutines.selects.SelectClause1 r8 = r4.getOnReceiveOrNull()     // Catch:{ all -> 0x00c4 }
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$1 r16 = new kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$1     // Catch:{ all -> 0x00c4 }
            r5 = 0
            r4 = r16
            r6 = r11
            r7 = r13
            r3 = r8
            r8 = r12
            r1 = r9
            r9 = r14
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00c2 }
            r4 = r16
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4     // Catch:{ all -> 0x00c2 }
            r0.invoke(r3, r4)     // Catch:{ all -> 0x00c2 }
            T r5 = r12.element     // Catch:{ all -> 0x00c2 }
            if (r5 == 0) goto L_0x00bd
            long r8 = r11.$timeoutMillis     // Catch:{ all -> 0x00c2 }
            kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$2 r3 = new kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$2     // Catch:{ all -> 0x00c2 }
            r6 = 0
            r4 = r3
            r7 = r0
            r17 = r8
            r8 = r11
            r9 = r13
            r16 = r10
            r10 = r12
            r19 = r11
            r11 = r14
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x00bb }
            kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3     // Catch:{ all -> 0x00bb }
            r4 = r17
            r0.onTimeout(r4, r3)     // Catch:{ all -> 0x00bb }
            goto L_0x00cd
        L_0x00bb:
            r0 = move-exception
            goto L_0x00ca
        L_0x00bd:
            r16 = r10
            r19 = r11
            goto L_0x00cd
        L_0x00c2:
            r0 = move-exception
            goto L_0x00c6
        L_0x00c4:
            r0 = move-exception
            r1 = r9
        L_0x00c6:
            r16 = r10
            r19 = r11
        L_0x00ca:
            r1.handleBuilderException(r0)
        L_0x00cd:
            java.lang.Object r0 = r1.getResult()
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r0 != r1) goto L_0x00da
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r16)
        L_0x00da:
            if (r0 != r2) goto L_0x00dd
            return r2
        L_0x00dd:
            r11 = r19
        L_0x00df:
            r1 = r20
            r3 = 1
            goto L_0x0061
        L_0x00e4:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

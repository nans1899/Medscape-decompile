package kotlinx.coroutines.flow;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00060\u0002j\u0002`&2\b\u0012\u0004\u0012\u00028\u00000'2\b\u0012\u0004\u0012\u00028\u00000\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\f\u001a\u00020\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tH@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u0019R\u0016\u0010\u001b\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u0019R\u001e\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u001c8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR*\u0010\u001f\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00028\u00008V@VX\u000e¢\u0006\u0012\u0012\u0004\b#\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"Lkotlinx/coroutines/flow/StateFlowImpl;", "T", "", "initialValue", "<init>", "(Ljava/lang/Object;)V", "Lkotlinx/coroutines/flow/StateFlowSlot;", "allocateSlot", "()Lkotlinx/coroutines/flow/StateFlowSlot;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "", "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "slot", "freeSlot", "(Lkotlinx/coroutines/flow/StateFlowSlot;)V", "Lkotlin/coroutines/CoroutineContext;", "context", "", "capacity", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "fuse", "(Lkotlin/coroutines/CoroutineContext;I)Lkotlinx/coroutines/flow/internal/FusibleFlow;", "nSlots", "I", "nextIndex", "sequence", "", "slots", "[Lkotlinx/coroutines/flow/StateFlowSlot;", "value", "getValue", "()Ljava/lang/Object;", "setValue", "getValue$annotations", "()V", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/SynchronizedObject;", "Lkotlinx/coroutines/flow/MutableStateFlow;"}, k = 1, mv = {1, 4, 0})
/* compiled from: StateFlow.kt */
final class StateFlowImpl<T> implements MutableStateFlow<T>, FusibleFlow<T> {
    private volatile Object _state;
    private int nSlots;
    private int nextIndex;
    private int sequence;
    private StateFlowSlot[] slots = new StateFlowSlot[2];

    public static /* synthetic */ void getValue$annotations() {
    }

    public StateFlowImpl(Object obj) {
        this._state = obj;
    }

    public T getValue() {
        T t = NullSurrogateKt.NULL;
        T t2 = this._state;
        if (t2 == t) {
            return null;
        }
        return t2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        r1 = r0.length;
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        if (r2 >= r1) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        r3 = r0[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0027, code lost:
        if (r3 == null) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0029, code lost:
        r3.makePending();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002c, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0032, code lost:
        if (r4.sequence != r5) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0034, code lost:
        r4.sequence = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0038, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0039, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r5 = r4.sequence;
        r0 = r4.slots;
        r1 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0040, code lost:
        monitor-exit(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setValue(T r5) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x0003
            goto L_0x0005
        L_0x0003:
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
        L_0x0005:
            monitor-enter(r4)
            java.lang.Object r0 = r4._state     // Catch:{ all -> 0x004b }
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r5)     // Catch:{ all -> 0x004b }
            if (r0 == 0) goto L_0x0010
            monitor-exit(r4)
            return
        L_0x0010:
            r4._state = r5     // Catch:{ all -> 0x004b }
            int r5 = r4.sequence     // Catch:{ all -> 0x004b }
            r0 = r5 & 1
            if (r0 != 0) goto L_0x0045
            int r5 = r5 + 1
            r4.sequence = r5     // Catch:{ all -> 0x004b }
            kotlinx.coroutines.flow.StateFlowSlot[] r0 = r4.slots     // Catch:{ all -> 0x004b }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004b }
            monitor-exit(r4)
        L_0x0021:
            int r1 = r0.length
            r2 = 0
        L_0x0023:
            if (r2 >= r1) goto L_0x002f
            r3 = r0[r2]
            if (r3 == 0) goto L_0x002c
            r3.makePending()
        L_0x002c:
            int r2 = r2 + 1
            goto L_0x0023
        L_0x002f:
            monitor-enter(r4)
            int r0 = r4.sequence     // Catch:{ all -> 0x0042 }
            if (r0 != r5) goto L_0x003a
            int r5 = r5 + 1
            r4.sequence = r5     // Catch:{ all -> 0x0042 }
            monitor-exit(r4)
            return
        L_0x003a:
            int r5 = r4.sequence     // Catch:{ all -> 0x0042 }
            kotlinx.coroutines.flow.StateFlowSlot[] r0 = r4.slots     // Catch:{ all -> 0x0042 }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0042 }
            monitor-exit(r4)
            goto L_0x0021
        L_0x0042:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x0045:
            int r5 = r5 + 2
            r4.sequence = r5     // Catch:{ all -> 0x004b }
            monitor-exit(r4)
            return
        L_0x004b:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StateFlowImpl.setValue(java.lang.Object):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0077 A[Catch:{ all -> 0x005c }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0079 A[Catch:{ all -> 0x005c }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008c A[Catch:{ all -> 0x005c }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008d A[Catch:{ all -> 0x005c }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0099 A[Catch:{ all -> 0x005c }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector<? super T> r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof kotlinx.coroutines.flow.StateFlowImpl$collect$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            kotlinx.coroutines.flow.StateFlowImpl$collect$1 r0 = (kotlinx.coroutines.flow.StateFlowImpl$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.StateFlowImpl$collect$1 r0 = new kotlinx.coroutines.flow.StateFlowImpl$collect$1
            r0.<init>(r10, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x005e
            if (r2 == r5) goto L_0x0048
            if (r2 != r4) goto L_0x0040
            java.lang.Object r11 = r0.L$4
            java.lang.Object r11 = r0.L$3
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.flow.StateFlowSlot r2 = (kotlinx.coroutines.flow.StateFlowSlot) r2
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.flow.StateFlowImpl r7 = (kotlinx.coroutines.flow.StateFlowImpl) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x005c }
            r12 = r11
            r11 = r6
            goto L_0x0068
        L_0x0040:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0048:
            java.lang.Object r11 = r0.L$4
            java.lang.Object r2 = r0.L$3
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.flow.StateFlowSlot r2 = (kotlinx.coroutines.flow.StateFlowSlot) r2
            java.lang.Object r6 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
            java.lang.Object r7 = r0.L$0
            kotlinx.coroutines.flow.StateFlowImpl r7 = (kotlinx.coroutines.flow.StateFlowImpl) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x005c }
            goto L_0x0090
        L_0x005c:
            r11 = move-exception
            goto L_0x00ac
        L_0x005e:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.flow.StateFlowSlot r12 = r10.allocateSlot()
            r7 = r10
            r2 = r12
            r12 = r3
        L_0x0068:
            java.lang.Object r6 = r7._state     // Catch:{ all -> 0x005c }
            if (r12 == 0) goto L_0x0073
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r12)     // Catch:{ all -> 0x005c }
            r8 = r8 ^ r5
            if (r8 == 0) goto L_0x0093
        L_0x0073:
            kotlinx.coroutines.internal.Symbol r8 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL     // Catch:{ all -> 0x005c }
            if (r6 != r8) goto L_0x0079
            r8 = r3
            goto L_0x007a
        L_0x0079:
            r8 = r6
        L_0x007a:
            r0.L$0 = r7     // Catch:{ all -> 0x005c }
            r0.L$1 = r11     // Catch:{ all -> 0x005c }
            r0.L$2 = r2     // Catch:{ all -> 0x005c }
            r0.L$3 = r12     // Catch:{ all -> 0x005c }
            r0.L$4 = r6     // Catch:{ all -> 0x005c }
            r0.label = r5     // Catch:{ all -> 0x005c }
            java.lang.Object r12 = r11.emit(r8, r0)     // Catch:{ all -> 0x005c }
            if (r12 != r1) goto L_0x008d
            return r1
        L_0x008d:
            r9 = r6
            r6 = r11
            r11 = r9
        L_0x0090:
            r12 = r11
            r11 = r6
            r6 = r12
        L_0x0093:
            boolean r8 = r2.takePending()     // Catch:{ all -> 0x005c }
            if (r8 != 0) goto L_0x0068
            r0.L$0 = r7     // Catch:{ all -> 0x005c }
            r0.L$1 = r11     // Catch:{ all -> 0x005c }
            r0.L$2 = r2     // Catch:{ all -> 0x005c }
            r0.L$3 = r12     // Catch:{ all -> 0x005c }
            r0.L$4 = r6     // Catch:{ all -> 0x005c }
            r0.label = r4     // Catch:{ all -> 0x005c }
            java.lang.Object r6 = r2.awaitPending(r0)     // Catch:{ all -> 0x005c }
            if (r6 != r1) goto L_0x0068
            return r1
        L_0x00ac:
            r7.freeSlot(r2)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StateFlowImpl.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public FusibleFlow<T> fuse(CoroutineContext coroutineContext, int i) {
        if (i == -1 || i == 0) {
            return this;
        }
        return new ChannelFlowOperatorImpl<>(this, coroutineContext, i);
    }

    private final StateFlowSlot allocateSlot() {
        StateFlowSlot stateFlowSlot;
        synchronized (this) {
            int length = this.slots.length;
            if (this.nSlots >= length) {
                Object[] copyOf = Arrays.copyOf(this.slots, length * 2);
                Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                this.slots = (StateFlowSlot[]) copyOf;
            }
            int i = this.nextIndex;
            do {
                stateFlowSlot = this.slots[i];
                if (stateFlowSlot == null) {
                    stateFlowSlot = new StateFlowSlot();
                    this.slots[i] = stateFlowSlot;
                }
                i++;
                if (i >= this.slots.length) {
                    i = 0;
                }
            } while (!stateFlowSlot.allocate());
            this.nextIndex = i;
            this.nSlots++;
        }
        return stateFlowSlot;
    }

    private final void freeSlot(StateFlowSlot stateFlowSlot) {
        synchronized (this) {
            stateFlowSlot.free();
            this.nSlots--;
            Unit unit = Unit.INSTANCE;
        }
    }
}

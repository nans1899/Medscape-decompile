package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function4;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* compiled from: SafeCollector.common.kt */
public final class FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 implements Flow<T> {
    final /* synthetic */ Function4 $predicate$inlined;
    final /* synthetic */ Flow $this_retryWhen$inlined;

    public FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1(Flow flow, Function4 function4) {
        this.$this_retryWhen$inlined = flow;
        this.$predicate$inlined = function4;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector r14, kotlin.coroutines.Continuation r15) {
        /*
            r13 = this;
            boolean r0 = r15 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.AnonymousClass1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1
            r0.<init>(r13, r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0064
            if (r2 == r4) goto L_0x004c
            if (r2 != r3) goto L_0x0044
            java.lang.Object r14 = r0.L$4
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            long r5 = r0.J$0
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
            java.lang.Object r7 = r0.L$2
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 r9 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1) r9
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00bb
        L_0x0044:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L_0x004c:
            int r14 = r0.I$0
            long r5 = r0.J$0
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
            java.lang.Object r7 = r0.L$2
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            java.lang.Object r8 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            java.lang.Object r9 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1 r9 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1) r9
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0091
        L_0x0064:
            kotlin.ResultKt.throwOnFailure(r15)
            r15 = r0
            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15
            r5 = 0
            r7 = r13
            r2 = r1
            r1 = r0
            r0 = r15
            r15 = r14
        L_0x0071:
            r8 = 0
            kotlinx.coroutines.flow.Flow r9 = r7.$this_retryWhen$inlined
            r1.L$0 = r7
            r1.L$1 = r15
            r1.L$2 = r0
            r1.L$3 = r14
            r1.J$0 = r5
            r1.I$0 = r8
            r1.label = r4
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.catchImpl(r9, r14, r1)
            if (r9 != r2) goto L_0x0089
            return r2
        L_0x0089:
            r8 = r15
            r15 = r9
            r9 = r7
            r7 = r0
            r0 = r1
            r1 = r2
            r2 = r14
            r14 = 0
        L_0x0091:
            java.lang.Throwable r15 = (java.lang.Throwable) r15
            if (r15 == 0) goto L_0x00ca
            kotlin.jvm.functions.Function4 r14 = r9.$predicate$inlined
            java.lang.Long r10 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r5)
            r0.L$0 = r9
            r0.L$1 = r8
            r0.L$2 = r7
            r0.L$3 = r2
            r0.J$0 = r5
            r0.L$4 = r15
            r0.label = r3
            r11 = 6
            kotlin.jvm.internal.InlineMarker.mark((int) r11)
            java.lang.Object r14 = r14.invoke(r2, r15, r10, r0)
            r10 = 7
            kotlin.jvm.internal.InlineMarker.mark((int) r10)
            if (r14 != r1) goto L_0x00b8
            return r1
        L_0x00b8:
            r12 = r15
            r15 = r14
            r14 = r12
        L_0x00bb:
            java.lang.Boolean r15 = (java.lang.Boolean) r15
            boolean r15 = r15.booleanValue()
            if (r15 == 0) goto L_0x00c9
            r14 = 1
            long r5 = r5 + r14
            r15 = r8
            r14 = 1
            goto L_0x00cb
        L_0x00c9:
            throw r14
        L_0x00ca:
            r15 = r8
        L_0x00cb:
            r12 = r1
            r1 = r0
            r0 = r7
            r6 = r5
            r5 = r12
            if (r14 != 0) goto L_0x00d5
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x00d5:
            r14 = r2
            r2 = r5
            r5 = r6
            r7 = r9
            goto L_0x0071
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}

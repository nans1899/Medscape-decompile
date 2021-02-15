package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import com.webmd.wbmdcmepulse.models.CPEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Landroid/view/View;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "androidx.core.view.ViewGroupKt$descendants$1", f = "ViewGroup.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {97, 99}, m = "invokeSuspend", n = {"$this$sequence", "$this$forEach$iv", "index$iv", "child", "$this$sequence", "$this$forEach$iv", "index$iv", "child"}, s = {"L$0", "L$1", "I$0", "L$2", "L$0", "L$1", "I$0", "L$2"})
/* compiled from: ViewGroup.kt */
final class ViewGroupKt$descendants$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super View>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ViewGroup $this_descendants;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private SequenceScope p$;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ViewGroupKt$descendants$1(ViewGroup viewGroup, Continuation continuation) {
        super(2, continuation);
        this.$this_descendants = viewGroup;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        ViewGroupKt$descendants$1 viewGroupKt$descendants$1 = new ViewGroupKt$descendants$1(this.$this_descendants, continuation);
        viewGroupKt$descendants$1.p$ = (SequenceScope) obj;
        return viewGroupKt$descendants$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ViewGroupKt$descendants$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0041
            if (r1 == r3) goto L_0x002c
            if (r1 != r2) goto L_0x0024
            java.lang.Object r1 = r11.L$2
            android.view.View r1 = (android.view.View) r1
            int r1 = r11.I$1
            int r4 = r11.I$0
            java.lang.Object r5 = r11.L$1
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            java.lang.Object r6 = r11.L$0
            kotlin.sequences.SequenceScope r6 = (kotlin.sequences.SequenceScope) r6
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r11
            goto L_0x0096
        L_0x0024:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x002c:
            java.lang.Object r1 = r11.L$2
            android.view.View r1 = (android.view.View) r1
            int r4 = r11.I$1
            int r5 = r11.I$0
            java.lang.Object r6 = r11.L$1
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            java.lang.Object r7 = r11.L$0
            kotlin.sequences.SequenceScope r7 = (kotlin.sequences.SequenceScope) r7
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r11
            goto L_0x0074
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.sequences.SequenceScope r12 = r11.p$
            android.view.ViewGroup r1 = r11.$this_descendants
            r4 = 0
            int r5 = r1.getChildCount()
            r6 = r11
        L_0x004e:
            if (r4 >= r5) goto L_0x00a6
            android.view.View r7 = r1.getChildAt(r4)
            java.lang.String r8 = "getChildAt(index)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            r6.L$0 = r12
            r6.L$1 = r1
            r6.I$0 = r4
            r6.I$1 = r5
            r6.L$2 = r7
            r6.label = r3
            java.lang.Object r8 = r12.yield(r7, r6)
            if (r8 != r0) goto L_0x006c
            return r0
        L_0x006c:
            r9 = r7
            r7 = r12
            r12 = r6
            r6 = r1
            r1 = r9
            r10 = r5
            r5 = r4
            r4 = r10
        L_0x0074:
            boolean r8 = r1 instanceof android.view.ViewGroup
            if (r8 == 0) goto L_0x009e
            r8 = r1
            android.view.ViewGroup r8 = (android.view.ViewGroup) r8
            kotlin.sequences.Sequence r8 = androidx.core.view.ViewGroupKt.getDescendants(r8)
            r12.L$0 = r7
            r12.L$1 = r6
            r12.I$0 = r5
            r12.I$1 = r4
            r12.L$2 = r1
            r12.label = r2
            java.lang.Object r1 = r7.yieldAll(r8, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r12)
            if (r1 != r0) goto L_0x0092
            return r0
        L_0x0092:
            r1 = r4
            r4 = r5
            r5 = r6
            r6 = r7
        L_0x0096:
            r9 = r6
            r6 = r12
            r12 = r9
            r10 = r4
            r4 = r1
            r1 = r5
            r5 = r10
            goto L_0x00a1
        L_0x009e:
            r1 = r6
            r6 = r12
            r12 = r7
        L_0x00a1:
            int r5 = r5 + r3
            r9 = r5
            r5 = r4
            r4 = r9
            goto L_0x004e
        L_0x00a6:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.ViewGroupKt$descendants$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

package coil;

import androidx.lifecycle.Lifecycle;
import coil.memory.TargetDelegate;
import coil.request.SuccessResult;
import com.webmd.wbmdcmepulse.models.CPEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Lcoil/request/SuccessResult;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "coil.RealImageLoader$executeInternal$2$deferred$1", f = "RealImageLoader.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, l = {506, 532, 545, 226, 552, 243}, m = "invokeSuspend", n = {"$this$innerJob", "data", "target", "sizeResolver", "lazySizeResolver", "this_$iv", "mappedData$iv", "$this$forEachIndices$iv$iv", "i$iv$iv", "$dstr$type$mapper$iv", "type$iv", "mapper$iv", "this_$iv$iv", "cached$iv$iv", "$this$innerJob", "data", "target", "sizeResolver", "lazySizeResolver", "mappedData", "fetcher", "this_$iv", "parameters$iv", "transformations$iv", "baseKey$iv", "this_$iv$iv", "cached$iv$iv", "$this$innerJob", "data", "target", "sizeResolver", "lazySizeResolver", "mappedData", "fetcher", "cacheKey", "memoryCachePolicy", "cachedValue", "cachedDrawable", "this_$iv", "$this$innerJob", "data", "target", "sizeResolver", "lazySizeResolver", "mappedData", "fetcher", "cacheKey", "memoryCachePolicy", "cachedValue", "cachedDrawable", "size", "scale", "result", "$this$innerJob", "data", "target", "sizeResolver", "lazySizeResolver", "mappedData", "fetcher", "cacheKey", "memoryCachePolicy", "cachedValue", "cachedDrawable", "size", "scale", "this_$iv", "request$iv", "eventListener$iv", "$this$innerJob", "data", "target", "sizeResolver", "lazySizeResolver", "mappedData", "fetcher", "cacheKey", "memoryCachePolicy", "cachedValue", "cachedDrawable", "size", "scale", "drawable", "isSampled", "source", "result"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "I$0", "L$9", "L$10", "L$11", "L$12", "L$16", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$14", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "Z$0", "L$14", "L$15"})
/* compiled from: RealImageLoader.kt */
final class RealImageLoader$executeInternal$2$deferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SuccessResult>, Object> {
    final /* synthetic */ EventListener $eventListener;
    final /* synthetic */ Lifecycle $lifecycle;
    final /* synthetic */ TargetDelegate $targetDelegate;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$13;
    Object L$14;
    Object L$15;
    Object L$16;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    boolean Z$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ RealImageLoader$executeInternal$2 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RealImageLoader$executeInternal$2$deferred$1(RealImageLoader$executeInternal$2 realImageLoader$executeInternal$2, EventListener eventListener, Lifecycle lifecycle, TargetDelegate targetDelegate, Continuation continuation) {
        super(2, continuation);
        this.this$0 = realImageLoader$executeInternal$2;
        this.$eventListener = eventListener;
        this.$lifecycle = lifecycle;
        this.$targetDelegate = targetDelegate;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        RealImageLoader$executeInternal$2$deferred$1 realImageLoader$executeInternal$2$deferred$1 = new RealImageLoader$executeInternal$2$deferred$1(this.this$0, this.$eventListener, this.$lifecycle, this.$targetDelegate, continuation);
        realImageLoader$executeInternal$2$deferred$1.p$ = (CoroutineScope) obj;
        return realImageLoader$executeInternal$2$deferred$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((RealImageLoader$executeInternal$2$deferred$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v51, resolved type: coil.map.MeasuredMapper} */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x04f4, code lost:
        r5 = r8.this$0.this$0.context.getResources();
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, "context.resources");
        r7 = new android.graphics.drawable.BitmapDrawable(r5, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x050b, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x050c, code lost:
        r4 = r2.size;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0510, code lost:
        if (r4 == null) goto L_0x0521;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0512, code lost:
        r10 = r6;
        r15 = r8;
        r5 = r11;
        r11 = r14;
        r8 = r0;
        r14 = r1;
        r6 = r2;
        r0 = r4;
        r4 = r12;
        r1 = r19;
        r12 = r7;
        r7 = r9;
        r9 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0521, code lost:
        r2.beforeResolveSize(r7);
        r4 = r2.sizeResolver;
        r8.L$0 = r14;
        r8.L$1 = r13;
        r8.L$2 = r12;
        r8.L$3 = r11;
        r8.L$4 = r2;
        r8.L$5 = r9;
        r8.L$6 = r0;
        r8.L$7 = r3;
        r8.L$8 = r6;
        r8.L$9 = r1;
        r8.L$10 = r7;
        r8.L$11 = r2;
        r8.label = 3;
        r4 = r4.size(r8);
        r5 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0549, code lost:
        if (r4 != r5) goto L_0x054c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x054b, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x054c, code lost:
        r10 = r2;
        r36 = r5;
        r5 = r1;
        r1 = r36;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0552, code lost:
        r4 = (coil.size.Size) r4;
        r2.size = r4;
        r15 = kotlin.Unit.INSTANCE;
        r2.afterResolveSize(r4);
        r15 = r8;
        r8 = r0;
        r0 = r4;
        r4 = r12;
        r12 = r7;
        r7 = r9;
        r9 = r3;
        r36 = r14;
        r14 = r5;
        r5 = r11;
        r11 = r36;
        r37 = r10;
        r10 = r6;
        r6 = r37;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x056e, code lost:
        r3 = r15.this$0.this$0.requestService.scale(r15.this$0.$request, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x057e, code lost:
        if (r12 == null) goto L_0x0662;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0580, code lost:
        r18 = r3;
        r20 = r1;
        r1 = r4;
        r4 = r14;
        r41 = r5;
        r19 = r14;
        r14 = r6;
        r21 = r10;
        r10 = r7;
        r7 = r0;
        r23 = r0;
        r0 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x05ad, code lost:
        if (r15.this$0.this$0.memoryCacheService.isCachedValueValid(r9, r4, r15.this$0.$request, r41, r7, r18) == false) goto L_0x0655;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x05af, code lost:
        r2 = r15.this$0.this$0.getLogger$coil_base_release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x05b7, code lost:
        if (r2 == null) goto L_0x05da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x05be, code lost:
        if (r2.getLevel() > 4) goto L_0x05d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x05c0, code lost:
        r2.log(r22, 4, "🧠 Cached - " + r13, (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x05d8, code lost:
        r2 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x05da, code lost:
        r2 = new coil.request.SuccessResult(r12, coil.decode.DataSource.MEMORY_CACHE);
        r3 = r15.$targetDelegate;
        r4 = r15.this$0.$request.getTransition();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x05ee, code lost:
        if (r4 == null) goto L_0x05f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x05f1, code lost:
        r4 = r15.this$0.this$0.getDefaults().getTransition();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x05fd, code lost:
        r15.L$0 = r11;
        r15.L$1 = r13;
        r15.L$2 = r1;
        r15.L$3 = r41;
        r15.L$4 = r14;
        r15.L$5 = r10;
        r15.L$6 = r0;
        r15.L$7 = r9;
        r15.L$8 = r21;
        r15.L$9 = r19;
        r15.L$10 = r12;
        r15.L$11 = r23;
        r15.L$12 = r18;
        r15.L$13 = r2;
        r15.label = 4;
        r0 = r3.success(r2, r4, r15);
        r4 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x062c, code lost:
        if (r0 != r4) goto L_0x062f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x062e, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x062f, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0630, code lost:
        r15.$eventListener.onSuccess(r15.this$0.$request, r1.getSource());
        r0 = r15.this$0.$request.getListener();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0645, code lost:
        if (r0 == null) goto L_0x0654;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0647, code lost:
        r0.onSuccess(r15.this$0.$request, r1.getSource());
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0654, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0655, code lost:
        r7 = r41;
        r5 = r19;
        r4 = r20;
        r6 = r21;
        r8 = r22;
        r3 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0662, code lost:
        r2 = r3;
        r3 = r0;
        r0 = r8;
        r8 = r22;
        r36 = r4;
        r4 = r1;
        r1 = r36;
        r37 = r7;
        r7 = r5;
        r5 = r14;
        r14 = r6;
        r6 = r10;
        r10 = r37;
        r18 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0676, code lost:
        r2 = r15.this$0.this$0;
        r23 = r3;
        r3 = r15.this$0.$request;
        r19 = r11;
        r11 = r15.$eventListener;
        r20 = r3.getDispatcher();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0688, code lost:
        if (r20 == null) goto L_0x068b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x068b, code lost:
        r20 = r2.getDefaults().getDispatcher();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0693, code lost:
        r41 = r18;
        r18 = r2;
        r22 = r23;
        r23 = r3;
        r31 = r4;
        r24 = r12;
        r12 = r7;
        r32 = r8;
        r33 = r9;
        r9 = r0;
        r36 = r19;
        r19 = r0;
        r0 = r36;
        r15.L$0 = r0;
        r15.L$1 = r13;
        r15.L$2 = r1;
        r15.L$3 = r12;
        r15.L$4 = r14;
        r9 = r10;
        r15.L$5 = r9;
        r3 = r19;
        r15.L$6 = r3;
        r4 = r33;
        r15.L$7 = r4;
        r6 = r6;
        r15.L$8 = r6;
        r5 = r5;
        r15.L$9 = r5;
        r7 = r24;
        r15.L$10 = r7;
        r8 = r22;
        r15.L$11 = r8;
        r10 = r41;
        r15.L$12 = r10;
        r15.L$13 = r18;
        r15.L$14 = r23;
        r15.L$15 = r11;
        r15.label = 5;
        r2 = kotlinx.coroutines.BuildersKt.withContext(r20, new coil.RealImageLoader$loadData$2(r18, r23, r7, r22, r41, r11, r9, r10, (kotlin.coroutines.Continuation) null), r15);
        r11 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0714, code lost:
        if (r2 != r11) goto L_0x0717;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0716, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0717, code lost:
        r36 = r8;
        r8 = r0;
        r0 = r4;
        r4 = r36;
        r37 = r3;
        r3 = r1;
        r1 = r11;
        r11 = r37;
        r38 = r7;
        r7 = r5;
        r5 = r38;
        r39 = r9;
        r9 = r6;
        r6 = r13;
        r13 = r14;
        r14 = r12;
        r12 = r39;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0730, code lost:
        r2 = (coil.fetch.DrawableResult) r2;
        r18 = r1;
        r1 = r2.component1();
        r19 = r10;
        r10 = r2.component2();
        r2 = r2.component3();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0746, code lost:
        if (r9.getWriteEnabled() == false) goto L_0x0756;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0748, code lost:
        r20 = r4;
        coil.util.Extensions.putValue(r15.this$0.this$0.memoryCache, r0, r1, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0756, code lost:
        r20 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0758, code lost:
        r4 = r15.this$0.this$0.getLogger$coil_base_release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0760, code lost:
        if (r4 == null) goto L_0x07a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0762, code lost:
        r41 = r10;
        r21 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x076b, code lost:
        if (r4.getLevel() > 4) goto L_0x079d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x076d, code lost:
        r16 = r7;
        r17 = r9;
        r4.log(r32, 4, coil.util.Extensions.getEmoji(r2) + " Successful (" + r2.name() + ") - " + r6, (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x079d, code lost:
        r16 = r7;
        r17 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x07a1, code lost:
        r4 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x07a4, code lost:
        r21 = r5;
        r16 = r7;
        r17 = r9;
        r41 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x07ac, code lost:
        r4 = new coil.request.SuccessResult(r1, r2);
        r5 = r15.$targetDelegate;
        r7 = r15.this$0.$request.getTransition();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x07bb, code lost:
        if (r7 == null) goto L_0x07be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x07be, code lost:
        r7 = r15.this$0.this$0.getDefaults().getTransition();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x07ca, code lost:
        r15.L$0 = r8;
        r15.L$1 = r6;
        r15.L$2 = r3;
        r15.L$3 = r14;
        r15.L$4 = r13;
        r15.L$5 = r12;
        r15.L$6 = r11;
        r15.L$7 = r0;
        r15.L$8 = r17;
        r15.L$9 = r16;
        r15.L$10 = r21;
        r15.L$11 = r20;
        r15.L$12 = r19;
        r15.L$13 = r1;
        r15.Z$0 = r41;
        r15.L$14 = r2;
        r15.L$15 = r4;
        r15.label = 6;
        r1 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0801, code lost:
        if (r5.success(r4, r7, r15) != r1) goto L_0x0804;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0803, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0804, code lost:
        r1 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0805, code lost:
        r15.$eventListener.onSuccess(r15.this$0.$request, r2);
        r0 = r15.this$0.$request.getListener();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0816, code lost:
        if (r0 == null) goto L_0x0821;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0818, code lost:
        r0.onSuccess(r15.this$0.$request, r2);
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0821, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x027f, code lost:
        if (r11 >= r10) goto L_0x033e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0281, code lost:
        r9 = r12.get(r11);
        r0 = (java.lang.Class) r9.component1();
        r22 = r3;
        r3 = r9.component2();
        r21 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x02a5, code lost:
        if (r0.isAssignableFrom(r2.element.getClass()) == false) goto L_0x0333;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x02a7, code lost:
        if (r3 == null) goto L_0x032b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x02af, code lost:
        if (r3.handles(r2.element) == false) goto L_0x0333;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x02b1, code lost:
        r23 = r2.element;
        r1 = null;
        r24 = r7.size;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x02bf, code lost:
        if (r24 == null) goto L_0x02ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x02c1, code lost:
        r1 = r23;
        r0 = r24;
        r23 = r15;
        r15 = r14;
        r14 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x02ca, code lost:
        r7.beforeResolveSize(r1);
        r41 = r1;
        r1 = r7.sizeResolver;
        r8.L$0 = r6;
        r8.L$1 = r4;
        r8.L$2 = r13;
        r8.L$3 = r5;
        r8.L$4 = r7;
        r8.L$5 = r15;
        r8.L$6 = r14;
        r8.L$7 = r2;
        r8.L$8 = r12;
        r8.I$0 = r11;
        r8.I$1 = r10;
        r8.L$9 = r9;
        r8.L$10 = r0;
        r8.L$11 = r3;
        r8.L$12 = r7;
        r0 = r23;
        r8.L$13 = r0;
        r8.L$14 = r3;
        r8.L$15 = r2;
        r8.L$16 = r41;
        r8.label = 1;
        r1 = r1.size(r14);
        r9 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0306, code lost:
        if (r1 != r9) goto L_0x0309;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0308, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0309, code lost:
        r21 = r9;
        r23 = r15;
        r9 = r7;
        r15 = r14;
        r14 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0310, code lost:
        r1 = (coil.size.Size) r1;
        r9.size = r1;
        r9.afterResolveSize(r1);
        r36 = r1;
        r1 = r0;
        r0 = r36;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x031d, code lost:
        r2.element = r3.map(r1, r0);
        r2 = r14;
        r14 = r15;
        r1 = r21;
        r15 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0332, code lost:
        throw new kotlin.TypeCastException("null cannot be cast to non-null type coil.map.MeasuredMapper<kotlin.Any, *>");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0333, code lost:
        r1 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0337, code lost:
        r11 = r11 + 1;
        r0 = r40;
        r3 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x033e, code lost:
        r9 = r1;
        r22 = r3;
        r0 = r15.registry.getMappers$coil_base_release();
        r1 = r0.size();
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0351, code lost:
        if (r3 >= r1) goto L_0x038f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0353, code lost:
        r10 = r0.get(r3);
        r10 = (coil.map.Mapper) r10.component2();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x036f, code lost:
        if (((java.lang.Class) r10.component1()).isAssignableFrom(r2.element.getClass()) == false) goto L_0x038c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0371, code lost:
        if (r10 == null) goto L_0x0384;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0379, code lost:
        if (r10.handles(r2.element) == false) goto L_0x038c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x037b, code lost:
        r2.element = r10.map(r2.element);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x038b, code lost:
        throw new kotlin.TypeCastException("null cannot be cast to non-null type coil.map.Mapper<kotlin.Any, *>");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x038c, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x038f, code lost:
        r10 = r2.element;
        r8.$eventListener.mapEnd(r8.this$0.$request, r10);
        r0 = coil.util.Requests.validateFetcher(r8.this$0.$request, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x03a2, code lost:
        if (r0 == null) goto L_0x03a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x03a5, code lost:
        r0 = r8.this$0.this$0.registry.requireFetcher(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x03b1, code lost:
        r1 = r8.this$0.$request.getKey();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x03b9, code lost:
        if (r1 == null) goto L_0x03c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x03bb, code lost:
        r3 = new coil.memory.MemoryCache.Key(r1, (coil.request.Parameters) null, 2, (kotlin.jvm.internal.DefaultConstructorMarker) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x03c3, code lost:
        r11 = r5;
        r14 = r6;
        r2 = r7;
        r1 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x03c9, code lost:
        r1 = r8.this$0.this$0;
        r2 = r8.this$0.$request.getParameters();
        r3 = r8.this$0.$request.getTransformations();
        r11 = r0.key(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x03e1, code lost:
        if (r11 == null) goto L_0x0452;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x03e7, code lost:
        if (r3.isEmpty() == false) goto L_0x03f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x03e9, code lost:
        r3 = new coil.memory.MemoryCache.Key(r11, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x03f0, code lost:
        r14 = null;
        r12 = r7.size;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x03f8, code lost:
        if (r12 == null) goto L_0x03fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x03fa, code lost:
        r1 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x03fc, code lost:
        r7.beforeResolveSize(r14);
        r12 = r7.sizeResolver;
        r8.L$0 = r6;
        r8.L$1 = r4;
        r8.L$2 = r13;
        r8.L$3 = r5;
        r8.L$4 = r7;
        r8.L$5 = r10;
        r8.L$6 = r0;
        r8.L$7 = r1;
        r8.L$8 = r2;
        r8.L$9 = r3;
        r8.L$10 = r11;
        r8.L$11 = r7;
        r8.L$12 = r3;
        r8.L$13 = r11;
        r8.L$14 = r14;
        r8.label = 2;
        r1 = r12.size(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0428, code lost:
        if (r1 != r9) goto L_0x042b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x042a, code lost:
        return r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x042b, code lost:
        r14 = r4;
        r12 = r5;
        r15 = r6;
        r6 = r3;
        r3 = r0;
        r0 = r2;
        r2 = r11;
        r11 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0433, code lost:
        r1 = (coil.size.Size) r1;
        r7.size = r1;
        r7.afterResolveSize(r1);
        r7 = r11;
        r5 = r12;
        r4 = r14;
        r12 = r1;
        r11 = r2;
        r1 = r9;
        r2 = r0;
        r0 = r3;
        r3 = r6;
        r6 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0445, code lost:
        r9 = new coil.memory.MemoryCache.Key(r11, (java.util.List<? extends coil.transform.Transformation>) r3, r12, r2);
        r11 = r5;
        r14 = r6;
        r2 = r7;
        r3 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x044e, code lost:
        r9 = r10;
        r12 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0450, code lost:
        r13 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0452, code lost:
        r11 = r5;
        r14 = r6;
        r2 = r7;
        r1 = r9;
        r9 = r10;
        r12 = r13;
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x045a, code lost:
        r4 = r8.this$0.$request.getMemoryCachePolicy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0462, code lost:
        if (r4 == null) goto L_0x0465;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0465, code lost:
        r4 = r8.this$0.this$0.getDefaults().getMemoryCachePolicy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0471, code lost:
        r6 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0476, code lost:
        if (r6.getReadEnabled() == false) goto L_0x04c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0478, code lost:
        r4 = coil.util.Extensions.getValue(r8.this$0.this$0.memoryCache, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0484, code lost:
        if (r4 == null) goto L_0x048a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0486, code lost:
        r19 = r1;
        r1 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x048a, code lost:
        r4 = r8.this$0.$request.getAliasKeys();
        r5 = r4.size();
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x049a, code lost:
        if (r7 >= r5) goto L_0x04c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x049c, code lost:
        r41 = r4;
        r19 = r1;
        r18 = r5;
        r1 = coil.util.Extensions.getValue(r8.this$0.this$0.memoryCache, new coil.memory.MemoryCache.Key(r4.get(r7), (coil.request.Parameters) null, 2, (kotlin.jvm.internal.DefaultConstructorMarker) null));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x04bb, code lost:
        if (r1 == null) goto L_0x04be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x04be, code lost:
        r7 = r7 + 1;
        r4 = r41;
        r5 = r18;
        r1 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x04c7, code lost:
        r19 = r1;
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x04ca, code lost:
        if (r1 == null) goto L_0x050b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x04cc, code lost:
        r4 = r1.getBitmap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x04d0, code lost:
        if (r4 == null) goto L_0x050b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x04ee, code lost:
        if (kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r8.this$0.this$0.requestService.isConfigValidForHardware(r8.this$0.$request, coil.util.Bitmaps.getSafeConfig(r4))).booleanValue() == false) goto L_0x04f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x04f1, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x04f2, code lost:
        if (r4 == null) goto L_0x050b;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r41) {
        /*
            r40 = this;
            r0 = r40
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "RealImageLoader"
            switch(r2) {
                case 0: goto L_0x01b7;
                case 1: goto L_0x0156;
                case 2: goto L_0x010e;
                case 3: goto L_0x00d2;
                case 4: goto L_0x009c;
                case 5: goto L_0x0053;
                case 6: goto L_0x0015;
                default: goto L_0x000d;
            }
        L_0x000d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0015:
            java.lang.Object r1 = r0.L$15
            coil.request.SuccessResult r1 = (coil.request.SuccessResult) r1
            java.lang.Object r2 = r0.L$14
            coil.decode.DataSource r2 = (coil.decode.DataSource) r2
            java.lang.Object r3 = r0.L$13
            android.graphics.drawable.Drawable r3 = (android.graphics.drawable.Drawable) r3
            java.lang.Object r3 = r0.L$12
            coil.size.Scale r3 = (coil.size.Scale) r3
            java.lang.Object r3 = r0.L$11
            coil.size.Size r3 = (coil.size.Size) r3
            java.lang.Object r3 = r0.L$10
            android.graphics.drawable.BitmapDrawable r3 = (android.graphics.drawable.BitmapDrawable) r3
            java.lang.Object r3 = r0.L$9
            coil.memory.MemoryCache$Value r3 = (coil.memory.MemoryCache.Value) r3
            java.lang.Object r3 = r0.L$8
            coil.request.CachePolicy r3 = (coil.request.CachePolicy) r3
            java.lang.Object r3 = r0.L$7
            coil.memory.MemoryCache$Key r3 = (coil.memory.MemoryCache.Key) r3
            java.lang.Object r3 = r0.L$6
            coil.fetch.Fetcher r3 = (coil.fetch.Fetcher) r3
            java.lang.Object r3 = r0.L$4
            coil.RealImageLoader$LazySizeResolver r3 = (coil.RealImageLoader.LazySizeResolver) r3
            java.lang.Object r3 = r0.L$3
            coil.size.SizeResolver r3 = (coil.size.SizeResolver) r3
            java.lang.Object r3 = r0.L$2
            coil.target.Target r3 = (coil.target.Target) r3
            java.lang.Object r3 = r0.L$0
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            kotlin.ResultKt.throwOnFailure(r41)
            r15 = r0
            goto L_0x0805
        L_0x0053:
            java.lang.Object r2 = r0.L$15
            coil.EventListener r2 = (coil.EventListener) r2
            java.lang.Object r2 = r0.L$14
            coil.request.Request r2 = (coil.request.Request) r2
            java.lang.Object r2 = r0.L$13
            coil.RealImageLoader r2 = (coil.RealImageLoader) r2
            java.lang.Object r2 = r0.L$12
            coil.size.Scale r2 = (coil.size.Scale) r2
            java.lang.Object r4 = r0.L$11
            coil.size.Size r4 = (coil.size.Size) r4
            java.lang.Object r5 = r0.L$10
            android.graphics.drawable.BitmapDrawable r5 = (android.graphics.drawable.BitmapDrawable) r5
            java.lang.Object r7 = r0.L$9
            coil.memory.MemoryCache$Value r7 = (coil.memory.MemoryCache.Value) r7
            java.lang.Object r9 = r0.L$8
            coil.request.CachePolicy r9 = (coil.request.CachePolicy) r9
            java.lang.Object r10 = r0.L$7
            coil.memory.MemoryCache$Key r10 = (coil.memory.MemoryCache.Key) r10
            java.lang.Object r11 = r0.L$6
            coil.fetch.Fetcher r11 = (coil.fetch.Fetcher) r11
            java.lang.Object r12 = r0.L$5
            java.lang.Object r13 = r0.L$4
            coil.RealImageLoader$LazySizeResolver r13 = (coil.RealImageLoader.LazySizeResolver) r13
            java.lang.Object r14 = r0.L$3
            coil.size.SizeResolver r14 = (coil.size.SizeResolver) r14
            java.lang.Object r15 = r0.L$2
            coil.target.Target r15 = (coil.target.Target) r15
            java.lang.Object r6 = r0.L$1
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
            kotlin.ResultKt.throwOnFailure(r41)
            r32 = r3
            r3 = r15
            r15 = r0
            r0 = r10
            r10 = r2
            r2 = r41
            goto L_0x0730
        L_0x009c:
            java.lang.Object r1 = r0.L$13
            coil.request.SuccessResult r1 = (coil.request.SuccessResult) r1
            java.lang.Object r2 = r0.L$12
            coil.size.Scale r2 = (coil.size.Scale) r2
            java.lang.Object r2 = r0.L$11
            coil.size.Size r2 = (coil.size.Size) r2
            java.lang.Object r2 = r0.L$10
            android.graphics.drawable.BitmapDrawable r2 = (android.graphics.drawable.BitmapDrawable) r2
            java.lang.Object r2 = r0.L$9
            coil.memory.MemoryCache$Value r2 = (coil.memory.MemoryCache.Value) r2
            java.lang.Object r2 = r0.L$8
            coil.request.CachePolicy r2 = (coil.request.CachePolicy) r2
            java.lang.Object r2 = r0.L$7
            coil.memory.MemoryCache$Key r2 = (coil.memory.MemoryCache.Key) r2
            java.lang.Object r2 = r0.L$6
            coil.fetch.Fetcher r2 = (coil.fetch.Fetcher) r2
            java.lang.Object r2 = r0.L$4
            coil.RealImageLoader$LazySizeResolver r2 = (coil.RealImageLoader.LazySizeResolver) r2
            java.lang.Object r2 = r0.L$3
            coil.size.SizeResolver r2 = (coil.size.SizeResolver) r2
            java.lang.Object r2 = r0.L$2
            coil.target.Target r2 = (coil.target.Target) r2
            java.lang.Object r2 = r0.L$0
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            kotlin.ResultKt.throwOnFailure(r41)
            r15 = r0
            goto L_0x0630
        L_0x00d2:
            java.lang.Object r2 = r0.L$11
            coil.RealImageLoader$LazySizeResolver r2 = (coil.RealImageLoader.LazySizeResolver) r2
            java.lang.Object r4 = r0.L$10
            android.graphics.drawable.BitmapDrawable r4 = (android.graphics.drawable.BitmapDrawable) r4
            java.lang.Object r5 = r0.L$9
            coil.memory.MemoryCache$Value r5 = (coil.memory.MemoryCache.Value) r5
            java.lang.Object r6 = r0.L$8
            coil.request.CachePolicy r6 = (coil.request.CachePolicy) r6
            java.lang.Object r7 = r0.L$7
            coil.memory.MemoryCache$Key r7 = (coil.memory.MemoryCache.Key) r7
            java.lang.Object r8 = r0.L$6
            coil.fetch.Fetcher r8 = (coil.fetch.Fetcher) r8
            java.lang.Object r9 = r0.L$5
            java.lang.Object r10 = r0.L$4
            coil.RealImageLoader$LazySizeResolver r10 = (coil.RealImageLoader.LazySizeResolver) r10
            java.lang.Object r11 = r0.L$3
            coil.size.SizeResolver r11 = (coil.size.SizeResolver) r11
            java.lang.Object r12 = r0.L$2
            coil.target.Target r12 = (coil.target.Target) r12
            java.lang.Object r13 = r0.L$1
            java.lang.Object r14 = r0.L$0
            kotlinx.coroutines.CoroutineScope r14 = (kotlinx.coroutines.CoroutineScope) r14
            kotlin.ResultKt.throwOnFailure(r41)
            r22 = r3
            r3 = r7
            r7 = r4
            r4 = r41
            r36 = r8
            r8 = r0
            r0 = r36
            goto L_0x0552
        L_0x010e:
            java.lang.Object r2 = r0.L$14
            android.graphics.drawable.BitmapDrawable r2 = (android.graphics.drawable.BitmapDrawable) r2
            java.lang.Object r2 = r0.L$13
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r6 = r0.L$12
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r7 = r0.L$11
            coil.RealImageLoader$LazySizeResolver r7 = (coil.RealImageLoader.LazySizeResolver) r7
            java.lang.Object r8 = r0.L$10
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r8 = r0.L$9
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r8 = r0.L$8
            coil.request.Parameters r8 = (coil.request.Parameters) r8
            java.lang.Object r9 = r0.L$7
            coil.RealImageLoader r9 = (coil.RealImageLoader) r9
            java.lang.Object r9 = r0.L$6
            coil.fetch.Fetcher r9 = (coil.fetch.Fetcher) r9
            java.lang.Object r10 = r0.L$5
            java.lang.Object r11 = r0.L$4
            coil.RealImageLoader$LazySizeResolver r11 = (coil.RealImageLoader.LazySizeResolver) r11
            java.lang.Object r12 = r0.L$3
            coil.size.SizeResolver r12 = (coil.size.SizeResolver) r12
            java.lang.Object r13 = r0.L$2
            coil.target.Target r13 = (coil.target.Target) r13
            java.lang.Object r14 = r0.L$1
            java.lang.Object r15 = r0.L$0
            kotlinx.coroutines.CoroutineScope r15 = (kotlinx.coroutines.CoroutineScope) r15
            kotlin.ResultKt.throwOnFailure(r41)
            r22 = r3
            r3 = r9
            r9 = r1
            r1 = r41
            r36 = r8
            r8 = r0
            r0 = r36
            goto L_0x0433
        L_0x0156:
            java.lang.Object r2 = r0.L$16
            android.graphics.drawable.BitmapDrawable r2 = (android.graphics.drawable.BitmapDrawable) r2
            java.lang.Object r2 = r0.L$15
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref.ObjectRef) r2
            java.lang.Object r6 = r0.L$14
            coil.map.MeasuredMapper r6 = (coil.map.MeasuredMapper) r6
            java.lang.Object r8 = r0.L$13
            java.lang.Object r9 = r0.L$12
            coil.RealImageLoader$LazySizeResolver r9 = (coil.RealImageLoader.LazySizeResolver) r9
            java.lang.Object r10 = r0.L$11
            coil.map.MeasuredMapper r10 = (coil.map.MeasuredMapper) r10
            java.lang.Object r10 = r0.L$10
            java.lang.Class r10 = (java.lang.Class) r10
            java.lang.Object r10 = r0.L$9
            kotlin.Pair r10 = (kotlin.Pair) r10
            int r10 = r0.I$1
            int r11 = r0.I$0
            java.lang.Object r12 = r0.L$8
            java.util.List r12 = (java.util.List) r12
            java.lang.Object r13 = r0.L$7
            kotlin.jvm.internal.Ref$ObjectRef r13 = (kotlin.jvm.internal.Ref.ObjectRef) r13
            java.lang.Object r14 = r0.L$6
            coil.RealImageLoader$executeInternal$2$deferred$1 r14 = (coil.RealImageLoader$executeInternal$2$deferred$1) r14
            java.lang.Object r15 = r0.L$5
            coil.RealImageLoader r15 = (coil.RealImageLoader) r15
            java.lang.Object r5 = r0.L$4
            coil.RealImageLoader$LazySizeResolver r5 = (coil.RealImageLoader.LazySizeResolver) r5
            java.lang.Object r4 = r0.L$3
            coil.size.SizeResolver r4 = (coil.size.SizeResolver) r4
            java.lang.Object r7 = r0.L$2
            coil.target.Target r7 = (coil.target.Target) r7
            r21 = r1
            java.lang.Object r1 = r0.L$1
            r22 = r1
            java.lang.Object r1 = r0.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r41)
            r23 = r15
            r15 = r14
            r14 = r13
            r13 = r7
            r7 = r5
            r5 = r4
            r4 = r22
            r22 = r3
            r3 = r6
            r6 = r1
            r1 = r41
            r36 = r8
            r8 = r0
            r0 = r36
            goto L_0x0310
        L_0x01b7:
            r21 = r1
            kotlin.ResultKt.throwOnFailure(r41)
            kotlinx.coroutines.CoroutineScope r1 = r0.p$
            coil.RealImageLoader$executeInternal$2 r2 = r0.this$0
            coil.request.Request r2 = r2.$request
            java.lang.Object r2 = r2.getData()
            if (r2 == 0) goto L_0x0822
            coil.EventListener r4 = r0.$eventListener
            coil.RealImageLoader$executeInternal$2 r5 = r0.this$0
            coil.request.Request r5 = r5.$request
            r4.onDispatch(r5)
            boolean r4 = r2 instanceof android.graphics.drawable.BitmapDrawable
            if (r4 == 0) goto L_0x01ed
            coil.RealImageLoader$executeInternal$2 r4 = r0.this$0
            coil.RealImageLoader r4 = r4.this$0
            coil.memory.BitmapReferenceCounter r4 = r4.referenceCounter
            r5 = r2
            android.graphics.drawable.BitmapDrawable r5 = (android.graphics.drawable.BitmapDrawable) r5
            android.graphics.Bitmap r5 = r5.getBitmap()
            java.lang.String r6 = "data.bitmap"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            r4.invalidate(r5)
            goto L_0x01ff
        L_0x01ed:
            boolean r4 = r2 instanceof android.graphics.Bitmap
            if (r4 == 0) goto L_0x01ff
            coil.RealImageLoader$executeInternal$2 r4 = r0.this$0
            coil.RealImageLoader r4 = r4.this$0
            coil.memory.BitmapReferenceCounter r4 = r4.referenceCounter
            r5 = r2
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            r4.invalidate(r5)
        L_0x01ff:
            coil.RealImageLoader$executeInternal$2 r4 = r0.this$0
            coil.request.Request r4 = r4.$request
            coil.target.Target r4 = r4.getTarget()
            boolean r5 = r4 instanceof coil.target.ViewTarget
            if (r5 == 0) goto L_0x0217
            boolean r5 = r4 instanceof androidx.lifecycle.LifecycleObserver
            if (r5 == 0) goto L_0x0217
            androidx.lifecycle.Lifecycle r5 = r0.$lifecycle
            r6 = r4
            androidx.lifecycle.LifecycleObserver r6 = (androidx.lifecycle.LifecycleObserver) r6
            r5.addObserver(r6)
        L_0x0217:
            coil.RealImageLoader$executeInternal$2 r5 = r0.this$0
            coil.RealImageLoader r5 = r5.this$0
            coil.memory.RequestService r5 = r5.requestService
            coil.RealImageLoader$executeInternal$2 r6 = r0.this$0
            coil.request.Request r6 = r6.$request
            coil.RealImageLoader$executeInternal$2 r7 = r0.this$0
            coil.RealImageLoader r7 = r7.this$0
            android.content.Context r7 = r7.context
            coil.size.SizeResolver r5 = r5.sizeResolver(r6, r7)
            coil.RealImageLoader$LazySizeResolver r6 = new coil.RealImageLoader$LazySizeResolver
            coil.memory.TargetDelegate r7 = r0.$targetDelegate
            coil.RealImageLoader$executeInternal$2 r8 = r0.this$0
            coil.request.Request r8 = r8.$request
            coil.RealImageLoader$executeInternal$2 r9 = r0.this$0
            coil.RealImageLoader r9 = r9.this$0
            coil.DefaultRequestOptions r28 = r9.getDefaults()
            coil.EventListener r9 = r0.$eventListener
            r23 = r6
            r24 = r1
            r25 = r5
            r26 = r7
            r27 = r8
            r29 = r9
            r23.<init>(r24, r25, r26, r27, r28, r29)
            coil.EventListener r7 = r0.$eventListener
            coil.RealImageLoader$executeInternal$2 r8 = r0.this$0
            coil.request.Request r8 = r8.$request
            r7.mapStart(r8, r2)
            coil.RealImageLoader$executeInternal$2 r7 = r0.this$0
            coil.RealImageLoader r7 = r7.this$0
            kotlin.jvm.internal.Ref$ObjectRef r8 = new kotlin.jvm.internal.Ref$ObjectRef
            r8.<init>()
            r8.element = r2
            coil.ComponentRegistry r9 = r7.registry
            java.util.List r9 = r9.getMeasuredMappers$coil_base_release()
            r10 = r9
            java.util.Collection r10 = (java.util.Collection) r10
            int r10 = r10.size()
            r14 = r0
            r13 = r4
            r15 = r7
            r12 = r9
            r11 = 0
            r4 = r2
            r7 = r6
            r2 = r8
            r8 = r14
            r6 = r1
            r1 = r21
        L_0x027f:
            if (r11 >= r10) goto L_0x033e
            java.lang.Object r9 = r12.get(r11)
            kotlin.Pair r9 = (kotlin.Pair) r9
            java.lang.Object r21 = r9.component1()
            r0 = r21
            java.lang.Class r0 = (java.lang.Class) r0
            java.lang.Object r21 = r9.component2()
            r22 = r3
            r3 = r21
            coil.map.MeasuredMapper r3 = (coil.map.MeasuredMapper) r3
            r21 = r1
            T r1 = r2.element
            java.lang.Class r1 = r1.getClass()
            boolean r1 = r0.isAssignableFrom(r1)
            if (r1 == 0) goto L_0x0333
            if (r3 == 0) goto L_0x032b
            T r1 = r2.element
            boolean r1 = r3.handles(r1)
            if (r1 == 0) goto L_0x0333
            T r1 = r2.element
            r23 = r1
            r17 = 0
            r1 = r17
            android.graphics.drawable.BitmapDrawable r1 = (android.graphics.drawable.BitmapDrawable) r1
            coil.size.Size r24 = r7.size
            if (r24 == 0) goto L_0x02ca
            r1 = r23
            r0 = r24
            r23 = r15
            r15 = r14
            r14 = r2
            goto L_0x031d
        L_0x02ca:
            r7.beforeResolveSize(r1)
            r41 = r1
            coil.size.SizeResolver r1 = r7.sizeResolver
            r8.L$0 = r6
            r8.L$1 = r4
            r8.L$2 = r13
            r8.L$3 = r5
            r8.L$4 = r7
            r8.L$5 = r15
            r8.L$6 = r14
            r8.L$7 = r2
            r8.L$8 = r12
            r8.I$0 = r11
            r8.I$1 = r10
            r8.L$9 = r9
            r8.L$10 = r0
            r8.L$11 = r3
            r8.L$12 = r7
            r0 = r23
            r8.L$13 = r0
            r8.L$14 = r3
            r8.L$15 = r2
            r9 = r41
            r8.L$16 = r9
            r9 = 1
            r8.label = r9
            java.lang.Object r1 = r1.size(r14)
            r9 = r21
            if (r1 != r9) goto L_0x0309
            return r9
        L_0x0309:
            r21 = r9
            r23 = r15
            r9 = r7
            r15 = r14
            r14 = r2
        L_0x0310:
            coil.size.Size r1 = (coil.size.Size) r1
            r9.size = r1
            r9.afterResolveSize(r1)
            r36 = r1
            r1 = r0
            r0 = r36
        L_0x031d:
            java.lang.Object r0 = r3.map(r1, r0)
            r2.element = r0
            r2 = r14
            r14 = r15
            r1 = r21
            r15 = r23
        L_0x0329:
            r0 = 1
            goto L_0x0337
        L_0x032b:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type coil.map.MeasuredMapper<kotlin.Any, *>"
            r0.<init>(r1)
            throw r0
        L_0x0333:
            r9 = r21
            r1 = r9
            goto L_0x0329
        L_0x0337:
            int r11 = r11 + r0
            r0 = r40
            r3 = r22
            goto L_0x027f
        L_0x033e:
            r9 = r1
            r22 = r3
            coil.ComponentRegistry r0 = r15.registry
            java.util.List r0 = r0.getMappers$coil_base_release()
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            int r1 = r1.size()
            r3 = 0
        L_0x0351:
            if (r3 >= r1) goto L_0x038f
            java.lang.Object r10 = r0.get(r3)
            kotlin.Pair r10 = (kotlin.Pair) r10
            java.lang.Object r11 = r10.component1()
            java.lang.Class r11 = (java.lang.Class) r11
            java.lang.Object r10 = r10.component2()
            coil.map.Mapper r10 = (coil.map.Mapper) r10
            T r12 = r2.element
            java.lang.Class r12 = r12.getClass()
            boolean r11 = r11.isAssignableFrom(r12)
            if (r11 == 0) goto L_0x038c
            if (r10 == 0) goto L_0x0384
            T r11 = r2.element
            boolean r11 = r10.handles(r11)
            if (r11 == 0) goto L_0x038c
            T r11 = r2.element
            java.lang.Object r10 = r10.map(r11)
            r2.element = r10
            goto L_0x038c
        L_0x0384:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type coil.map.Mapper<kotlin.Any, *>"
            r0.<init>(r1)
            throw r0
        L_0x038c:
            int r3 = r3 + 1
            goto L_0x0351
        L_0x038f:
            T r10 = r2.element
            coil.EventListener r0 = r8.$eventListener
            coil.RealImageLoader$executeInternal$2 r1 = r8.this$0
            coil.request.Request r1 = r1.$request
            r0.mapEnd(r1, r10)
            coil.RealImageLoader$executeInternal$2 r0 = r8.this$0
            coil.request.Request r0 = r0.$request
            coil.fetch.Fetcher r0 = coil.util.Requests.validateFetcher(r0, r10)
            if (r0 == 0) goto L_0x03a5
            goto L_0x03b1
        L_0x03a5:
            coil.RealImageLoader$executeInternal$2 r0 = r8.this$0
            coil.RealImageLoader r0 = r0.this$0
            coil.ComponentRegistry r0 = r0.registry
            coil.fetch.Fetcher r0 = r0.requireFetcher(r10)
        L_0x03b1:
            coil.RealImageLoader$executeInternal$2 r1 = r8.this$0
            coil.request.Request r1 = r1.$request
            java.lang.String r1 = r1.getKey()
            if (r1 == 0) goto L_0x03c9
            coil.memory.MemoryCache$Key r2 = new coil.memory.MemoryCache$Key
            r3 = 2
            r11 = 0
            r2.<init>((java.lang.String) r1, (coil.request.Parameters) r11, (int) r3, (kotlin.jvm.internal.DefaultConstructorMarker) r11)
            r3 = r2
        L_0x03c3:
            r11 = r5
            r14 = r6
            r2 = r7
            r1 = r9
            goto L_0x044e
        L_0x03c9:
            coil.RealImageLoader$executeInternal$2 r1 = r8.this$0
            coil.RealImageLoader r1 = r1.this$0
            coil.RealImageLoader$executeInternal$2 r2 = r8.this$0
            coil.request.Request r2 = r2.$request
            coil.request.Parameters r2 = r2.getParameters()
            coil.RealImageLoader$executeInternal$2 r3 = r8.this$0
            coil.request.Request r3 = r3.$request
            java.util.List r3 = r3.getTransformations()
            java.lang.String r11 = r0.key(r10)
            if (r11 == 0) goto L_0x0452
            boolean r12 = r3.isEmpty()
            if (r12 == 0) goto L_0x03f0
            coil.memory.MemoryCache$Key r1 = new coil.memory.MemoryCache$Key
            r1.<init>(r11, r2)
            r3 = r1
            goto L_0x03c3
        L_0x03f0:
            r12 = 0
            r14 = r12
            android.graphics.drawable.BitmapDrawable r14 = (android.graphics.drawable.BitmapDrawable) r14
            coil.size.Size r12 = r7.size
            if (r12 == 0) goto L_0x03fc
            r1 = r9
            goto L_0x0445
        L_0x03fc:
            r7.beforeResolveSize(r14)
            coil.size.SizeResolver r12 = r7.sizeResolver
            r8.L$0 = r6
            r8.L$1 = r4
            r8.L$2 = r13
            r8.L$3 = r5
            r8.L$4 = r7
            r8.L$5 = r10
            r8.L$6 = r0
            r8.L$7 = r1
            r8.L$8 = r2
            r8.L$9 = r3
            r8.L$10 = r11
            r8.L$11 = r7
            r8.L$12 = r3
            r8.L$13 = r11
            r8.L$14 = r14
            r1 = 2
            r8.label = r1
            java.lang.Object r1 = r12.size(r8)
            if (r1 != r9) goto L_0x042b
            return r9
        L_0x042b:
            r14 = r4
            r12 = r5
            r15 = r6
            r6 = r3
            r3 = r0
            r0 = r2
            r2 = r11
            r11 = r7
        L_0x0433:
            coil.size.Size r1 = (coil.size.Size) r1
            r7.size = r1
            r7.afterResolveSize(r1)
            r7 = r11
            r5 = r12
            r4 = r14
            r12 = r1
            r11 = r2
            r1 = r9
            r2 = r0
            r0 = r3
            r3 = r6
            r6 = r15
        L_0x0445:
            coil.memory.MemoryCache$Key r9 = new coil.memory.MemoryCache$Key
            r9.<init>((java.lang.String) r11, (java.util.List<? extends coil.transform.Transformation>) r3, (coil.size.Size) r12, (coil.request.Parameters) r2)
            r11 = r5
            r14 = r6
            r2 = r7
            r3 = r9
        L_0x044e:
            r9 = r10
            r12 = r13
        L_0x0450:
            r13 = r4
            goto L_0x045a
        L_0x0452:
            r11 = r5
            r14 = r6
            r2 = r7
            r1 = r9
            r9 = r10
            r12 = r13
            r3 = 0
            goto L_0x0450
        L_0x045a:
            coil.RealImageLoader$executeInternal$2 r4 = r8.this$0
            coil.request.Request r4 = r4.$request
            coil.request.CachePolicy r4 = r4.getMemoryCachePolicy()
            if (r4 == 0) goto L_0x0465
            goto L_0x0471
        L_0x0465:
            coil.RealImageLoader$executeInternal$2 r4 = r8.this$0
            coil.RealImageLoader r4 = r4.this$0
            coil.DefaultRequestOptions r4 = r4.getDefaults()
            coil.request.CachePolicy r4 = r4.getMemoryCachePolicy()
        L_0x0471:
            r6 = r4
            boolean r4 = r6.getReadEnabled()
            if (r4 == 0) goto L_0x04c7
            coil.RealImageLoader$executeInternal$2 r4 = r8.this$0
            coil.RealImageLoader r4 = r4.this$0
            coil.memory.MemoryCache r4 = r4.memoryCache
            coil.memory.MemoryCache$Value r4 = coil.util.Extensions.getValue(r4, r3)
            if (r4 == 0) goto L_0x048a
            r19 = r1
            r1 = r4
            goto L_0x04ca
        L_0x048a:
            coil.RealImageLoader$executeInternal$2 r4 = r8.this$0
            coil.request.Request r4 = r4.$request
            java.util.List r4 = r4.getAliasKeys()
            r5 = r4
            java.util.Collection r5 = (java.util.Collection) r5
            int r5 = r5.size()
            r7 = 0
        L_0x049a:
            if (r7 >= r5) goto L_0x04c7
            java.lang.Object r10 = r4.get(r7)
            java.lang.String r10 = (java.lang.String) r10
            coil.RealImageLoader$executeInternal$2 r15 = r8.this$0
            coil.RealImageLoader r15 = r15.this$0
            coil.memory.MemoryCache r15 = r15.memoryCache
            r41 = r4
            coil.memory.MemoryCache$Key r4 = new coil.memory.MemoryCache$Key
            r19 = r1
            r18 = r5
            r1 = 0
            r5 = 2
            r4.<init>((java.lang.String) r10, (coil.request.Parameters) r1, (int) r5, (kotlin.jvm.internal.DefaultConstructorMarker) r1)
            coil.memory.MemoryCache$Value r1 = coil.util.Extensions.getValue(r15, r4)
            if (r1 == 0) goto L_0x04be
            goto L_0x04ca
        L_0x04be:
            int r7 = r7 + 1
            r4 = r41
            r5 = r18
            r1 = r19
            goto L_0x049a
        L_0x04c7:
            r19 = r1
            r1 = 0
        L_0x04ca:
            if (r1 == 0) goto L_0x050b
            android.graphics.Bitmap r4 = r1.getBitmap()
            if (r4 == 0) goto L_0x050b
            coil.RealImageLoader$executeInternal$2 r5 = r8.this$0
            coil.RealImageLoader r5 = r5.this$0
            coil.memory.RequestService r5 = r5.requestService
            coil.RealImageLoader$executeInternal$2 r7 = r8.this$0
            coil.request.Request r7 = r7.$request
            android.graphics.Bitmap$Config r10 = coil.util.Bitmaps.getSafeConfig(r4)
            boolean r5 = r5.isConfigValidForHardware(r7, r10)
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x04f1
            goto L_0x04f2
        L_0x04f1:
            r4 = 0
        L_0x04f2:
            if (r4 == 0) goto L_0x050b
            coil.RealImageLoader$executeInternal$2 r5 = r8.this$0
            coil.RealImageLoader r5 = r5.this$0
            android.content.Context r5 = r5.context
            android.content.res.Resources r5 = r5.getResources()
            java.lang.String r7 = "context.resources"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r7)
            android.graphics.drawable.BitmapDrawable r7 = new android.graphics.drawable.BitmapDrawable
            r7.<init>(r5, r4)
            goto L_0x050c
        L_0x050b:
            r7 = 0
        L_0x050c:
            coil.size.Size r4 = r2.size
            if (r4 == 0) goto L_0x0521
            r10 = r6
            r15 = r8
            r5 = r11
            r11 = r14
            r8 = r0
            r14 = r1
            r6 = r2
            r0 = r4
            r4 = r12
            r1 = r19
            r12 = r7
            r7 = r9
            r9 = r3
            goto L_0x056e
        L_0x0521:
            r2.beforeResolveSize(r7)
            coil.size.SizeResolver r4 = r2.sizeResolver
            r8.L$0 = r14
            r8.L$1 = r13
            r8.L$2 = r12
            r8.L$3 = r11
            r8.L$4 = r2
            r8.L$5 = r9
            r8.L$6 = r0
            r8.L$7 = r3
            r8.L$8 = r6
            r8.L$9 = r1
            r8.L$10 = r7
            r8.L$11 = r2
            r5 = 3
            r8.label = r5
            java.lang.Object r4 = r4.size(r8)
            r5 = r19
            if (r4 != r5) goto L_0x054c
            return r5
        L_0x054c:
            r10 = r2
            r36 = r5
            r5 = r1
            r1 = r36
        L_0x0552:
            coil.size.Size r4 = (coil.size.Size) r4
            r2.size = r4
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            r2.afterResolveSize(r4)
            r15 = r8
            r8 = r0
            r0 = r4
            r4 = r12
            r12 = r7
            r7 = r9
            r9 = r3
            r36 = r14
            r14 = r5
            r5 = r11
            r11 = r36
            r37 = r10
            r10 = r6
            r6 = r37
        L_0x056e:
            coil.RealImageLoader$executeInternal$2 r2 = r15.this$0
            coil.RealImageLoader r2 = r2.this$0
            coil.memory.RequestService r2 = r2.requestService
            coil.RealImageLoader$executeInternal$2 r3 = r15.this$0
            coil.request.Request r3 = r3.$request
            coil.size.Scale r3 = r2.scale(r3, r5)
            if (r12 == 0) goto L_0x0662
            coil.RealImageLoader$executeInternal$2 r2 = r15.this$0
            coil.RealImageLoader r2 = r2.this$0
            coil.memory.MemoryCacheService r2 = r2.memoryCacheService
            r41 = r3
            coil.RealImageLoader$executeInternal$2 r3 = r15.this$0
            coil.request.Request r3 = r3.$request
            r18 = r41
            r19 = r3
            r3 = r9
            r20 = r1
            r1 = r4
            r4 = r14
            r41 = r5
            r5 = r19
            r19 = r14
            r14 = r6
            r6 = r41
            r21 = r10
            r10 = r7
            r7 = r0
            r23 = r0
            r0 = r8
            r8 = r18
            boolean r2 = r2.isCachedValueValid(r3, r4, r5, r6, r7, r8)
            if (r2 == 0) goto L_0x0655
            coil.RealImageLoader$executeInternal$2 r2 = r15.this$0
            coil.RealImageLoader r2 = r2.this$0
            coil.util.Logger r2 = r2.getLogger$coil_base_release()
            if (r2 == 0) goto L_0x05da
            int r3 = r2.getLevel()
            r4 = 4
            if (r3 > r4) goto L_0x05d8
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "🧠 Cached - "
            r3.append(r5)
            r3.append(r13)
            java.lang.String r3 = r3.toString()
            r8 = r22
            r5 = 0
            r2.log(r8, r4, r3, r5)
        L_0x05d8:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
        L_0x05da:
            coil.request.SuccessResult r2 = new coil.request.SuccessResult
            r3 = r12
            android.graphics.drawable.Drawable r3 = (android.graphics.drawable.Drawable) r3
            coil.decode.DataSource r4 = coil.decode.DataSource.MEMORY_CACHE
            r2.<init>(r3, r4)
            coil.memory.TargetDelegate r3 = r15.$targetDelegate
            coil.RealImageLoader$executeInternal$2 r4 = r15.this$0
            coil.request.Request r4 = r4.$request
            coil.transition.Transition r4 = r4.getTransition()
            if (r4 == 0) goto L_0x05f1
            goto L_0x05fd
        L_0x05f1:
            coil.RealImageLoader$executeInternal$2 r4 = r15.this$0
            coil.RealImageLoader r4 = r4.this$0
            coil.DefaultRequestOptions r4 = r4.getDefaults()
            coil.transition.Transition r4 = r4.getTransition()
        L_0x05fd:
            r15.L$0 = r11
            r15.L$1 = r13
            r15.L$2 = r1
            r7 = r41
            r15.L$3 = r7
            r15.L$4 = r14
            r15.L$5 = r10
            r15.L$6 = r0
            r15.L$7 = r9
            r6 = r21
            r15.L$8 = r6
            r5 = r19
            r15.L$9 = r5
            r15.L$10 = r12
            r0 = r23
            r15.L$11 = r0
            r0 = r18
            r15.L$12 = r0
            r15.L$13 = r2
            r0 = 4
            r15.label = r0
            java.lang.Object r0 = r3.success(r2, r4, r15)
            r4 = r20
            if (r0 != r4) goto L_0x062f
            return r4
        L_0x062f:
            r1 = r2
        L_0x0630:
            coil.EventListener r0 = r15.$eventListener
            coil.RealImageLoader$executeInternal$2 r2 = r15.this$0
            coil.request.Request r2 = r2.$request
            coil.decode.DataSource r3 = r1.getSource()
            r0.onSuccess(r2, r3)
            coil.RealImageLoader$executeInternal$2 r0 = r15.this$0
            coil.request.Request r0 = r0.$request
            coil.request.Request$Listener r0 = r0.getListener()
            if (r0 == 0) goto L_0x0654
            coil.RealImageLoader$executeInternal$2 r2 = r15.this$0
            coil.request.Request r2 = r2.$request
            coil.decode.DataSource r3 = r1.getSource()
            r0.onSuccess(r2, r3)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x0654:
            return r1
        L_0x0655:
            r7 = r41
            r5 = r19
            r4 = r20
            r6 = r21
            r8 = r22
            r3 = r23
            goto L_0x0676
        L_0x0662:
            r2 = r3
            r3 = r0
            r0 = r8
            r8 = r22
            r36 = r4
            r4 = r1
            r1 = r36
            r37 = r7
            r7 = r5
            r5 = r14
            r14 = r6
            r6 = r10
            r10 = r37
            r18 = r2
        L_0x0676:
            coil.RealImageLoader$executeInternal$2 r2 = r15.this$0
            coil.RealImageLoader r2 = r2.this$0
            r23 = r3
            coil.RealImageLoader$executeInternal$2 r3 = r15.this$0
            coil.request.Request r3 = r3.$request
            r19 = r11
            coil.EventListener r11 = r15.$eventListener
            kotlinx.coroutines.CoroutineDispatcher r20 = r3.getDispatcher()
            if (r20 == 0) goto L_0x068b
            goto L_0x0693
        L_0x068b:
            coil.DefaultRequestOptions r20 = r2.getDefaults()
            kotlinx.coroutines.CoroutineDispatcher r20 = r20.getDispatcher()
        L_0x0693:
            kotlin.coroutines.CoroutineContext r20 = (kotlin.coroutines.CoroutineContext) r20
            r30 = r20
            coil.RealImageLoader$loadData$2 r20 = new coil.RealImageLoader$loadData$2
            r21 = 0
            r41 = r18
            r18 = r2
            r2 = r20
            r22 = r23
            r23 = r3
            r3 = r18
            r31 = r4
            r4 = r23
            r24 = r12
            r12 = r5
            r5 = r7
            r25 = r6
            r6 = r22
            r26 = r12
            r12 = r7
            r7 = r41
            r32 = r8
            r8 = r11
            r33 = r9
            r9 = r0
            r27 = r10
            r34 = r25
            r35 = r11
            r36 = r19
            r19 = r0
            r0 = r36
            r11 = r21
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r2 = r20
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r15.L$0 = r0
            r15.L$1 = r13
            r15.L$2 = r1
            r15.L$3 = r12
            r15.L$4 = r14
            r9 = r27
            r15.L$5 = r9
            r3 = r19
            r15.L$6 = r3
            r4 = r33
            r15.L$7 = r4
            r6 = r34
            r15.L$8 = r6
            r5 = r26
            r15.L$9 = r5
            r7 = r24
            r15.L$10 = r7
            r8 = r22
            r15.L$11 = r8
            r10 = r41
            r15.L$12 = r10
            r11 = r18
            r15.L$13 = r11
            r11 = r23
            r15.L$14 = r11
            r11 = r35
            r15.L$15 = r11
            r11 = 5
            r15.label = r11
            r11 = r30
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r11, r2, r15)
            r11 = r31
            if (r2 != r11) goto L_0x0717
            return r11
        L_0x0717:
            r36 = r8
            r8 = r0
            r0 = r4
            r4 = r36
            r37 = r3
            r3 = r1
            r1 = r11
            r11 = r37
            r38 = r7
            r7 = r5
            r5 = r38
            r39 = r9
            r9 = r6
            r6 = r13
            r13 = r14
            r14 = r12
            r12 = r39
        L_0x0730:
            coil.fetch.DrawableResult r2 = (coil.fetch.DrawableResult) r2
            r18 = r1
            android.graphics.drawable.Drawable r1 = r2.component1()
            r19 = r10
            boolean r10 = r2.component2()
            coil.decode.DataSource r2 = r2.component3()
            boolean r20 = r9.getWriteEnabled()
            if (r20 == 0) goto L_0x0756
            r20 = r4
            coil.RealImageLoader$executeInternal$2 r4 = r15.this$0
            coil.RealImageLoader r4 = r4.this$0
            coil.memory.MemoryCache r4 = r4.memoryCache
            coil.util.Extensions.putValue(r4, r0, r1, r10)
            goto L_0x0758
        L_0x0756:
            r20 = r4
        L_0x0758:
            coil.RealImageLoader$executeInternal$2 r4 = r15.this$0
            coil.RealImageLoader r4 = r4.this$0
            coil.util.Logger r4 = r4.getLogger$coil_base_release()
            if (r4 == 0) goto L_0x07a4
            r41 = r10
            int r10 = r4.getLevel()
            r21 = r5
            r5 = 4
            if (r10 > r5) goto L_0x079d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r10 = coil.util.Extensions.getEmoji(r2)
            r5.append(r10)
            java.lang.String r10 = " Successful ("
            r5.append(r10)
            java.lang.String r10 = r2.name()
            r5.append(r10)
            java.lang.String r10 = ") - "
            r5.append(r10)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r16 = r7
            r17 = r9
            r10 = r32
            r7 = 4
            r9 = 0
            r4.log(r10, r7, r5, r9)
            goto L_0x07a1
        L_0x079d:
            r16 = r7
            r17 = r9
        L_0x07a1:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x07ac
        L_0x07a4:
            r21 = r5
            r16 = r7
            r17 = r9
            r41 = r10
        L_0x07ac:
            coil.request.SuccessResult r4 = new coil.request.SuccessResult
            r4.<init>(r1, r2)
            coil.memory.TargetDelegate r5 = r15.$targetDelegate
            coil.RealImageLoader$executeInternal$2 r7 = r15.this$0
            coil.request.Request r7 = r7.$request
            coil.transition.Transition r7 = r7.getTransition()
            if (r7 == 0) goto L_0x07be
            goto L_0x07ca
        L_0x07be:
            coil.RealImageLoader$executeInternal$2 r7 = r15.this$0
            coil.RealImageLoader r7 = r7.this$0
            coil.DefaultRequestOptions r7 = r7.getDefaults()
            coil.transition.Transition r7 = r7.getTransition()
        L_0x07ca:
            r15.L$0 = r8
            r15.L$1 = r6
            r15.L$2 = r3
            r15.L$3 = r14
            r15.L$4 = r13
            r15.L$5 = r12
            r15.L$6 = r11
            r15.L$7 = r0
            r6 = r17
            r15.L$8 = r6
            r0 = r16
            r15.L$9 = r0
            r0 = r21
            r15.L$10 = r0
            r8 = r20
            r15.L$11 = r8
            r10 = r19
            r15.L$12 = r10
            r15.L$13 = r1
            r0 = r41
            r15.Z$0 = r0
            r15.L$14 = r2
            r15.L$15 = r4
            r0 = 6
            r15.label = r0
            java.lang.Object r0 = r5.success(r4, r7, r15)
            r1 = r18
            if (r0 != r1) goto L_0x0804
            return r1
        L_0x0804:
            r1 = r4
        L_0x0805:
            coil.EventListener r0 = r15.$eventListener
            coil.RealImageLoader$executeInternal$2 r3 = r15.this$0
            coil.request.Request r3 = r3.$request
            r0.onSuccess(r3, r2)
            coil.RealImageLoader$executeInternal$2 r0 = r15.this$0
            coil.request.Request r0 = r0.$request
            coil.request.Request$Listener r0 = r0.getListener()
            if (r0 == 0) goto L_0x0821
            coil.RealImageLoader$executeInternal$2 r3 = r15.this$0
            coil.request.Request r3 = r3.$request
            r0.onSuccess(r3, r2)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
        L_0x0821:
            return r1
        L_0x0822:
            coil.request.NullRequestDataException r0 = new coil.request.NullRequestDataException
            r0.<init>()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader$executeInternal$2$deferred$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}

package coil.memory;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import coil.EventListener;
import coil.memory.Poolable;
import coil.request.Request;
import coil.target.PoolableViewTarget;
import coil.util.Logger;
import com.facebook.share.internal.ShareConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B3\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\b\u0010\u0012\u001a\u00020\u0013H\u0016J!\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u001c\u0010\u001a\u001a\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J!\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020 2\u0006\u0010\u0017\u001a\u00020\u0018H@ø\u0001\u0000¢\u0006\u0002\u0010!R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"Lcoil/memory/PoolableTargetDelegate;", "Lcoil/memory/TargetDelegate;", "Lcoil/memory/Poolable;", "request", "Lcoil/request/Request;", "target", "Lcoil/target/PoolableViewTarget;", "referenceCounter", "Lcoil/memory/BitmapReferenceCounter;", "eventListener", "Lcoil/EventListener;", "logger", "Lcoil/util/Logger;", "(Lcoil/request/Request;Lcoil/target/PoolableViewTarget;Lcoil/memory/BitmapReferenceCounter;Lcoil/EventListener;Lcoil/util/Logger;)V", "getReferenceCounter", "()Lcoil/memory/BitmapReferenceCounter;", "getTarget", "()Lcoil/target/PoolableViewTarget;", "clear", "", "error", "result", "Lcoil/request/ErrorResult;", "transition", "Lcoil/transition/Transition;", "(Lcoil/request/ErrorResult;Lcoil/transition/Transition;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "cached", "Landroid/graphics/drawable/BitmapDrawable;", "placeholder", "Landroid/graphics/drawable/Drawable;", "success", "Lcoil/request/SuccessResult;", "(Lcoil/request/SuccessResult;Lcoil/transition/Transition;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TargetDelegate.kt */
public final class PoolableTargetDelegate extends TargetDelegate implements Poolable {
    private final EventListener eventListener;
    private final Logger logger;
    private final BitmapReferenceCounter referenceCounter;
    private final Request request;
    private final PoolableViewTarget<?> target;

    public void decrement(Bitmap bitmap) {
        Poolable.DefaultImpls.decrement(this, bitmap);
    }

    public void increment(Bitmap bitmap) {
        Poolable.DefaultImpls.increment(this, bitmap);
    }

    public PoolableViewTarget<?> getTarget() {
        return this.target;
    }

    public BitmapReferenceCounter getReferenceCounter() {
        return this.referenceCounter;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PoolableTargetDelegate(Request request2, PoolableViewTarget<?> poolableViewTarget, BitmapReferenceCounter bitmapReferenceCounter, EventListener eventListener2, Logger logger2) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(request2, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(poolableViewTarget, "target");
        Intrinsics.checkParameterIsNotNull(bitmapReferenceCounter, "referenceCounter");
        Intrinsics.checkParameterIsNotNull(eventListener2, "eventListener");
        this.request = request2;
        this.target = poolableViewTarget;
        this.referenceCounter = bitmapReferenceCounter;
        this.eventListener = eventListener2;
        this.logger = logger2;
    }

    public void start(BitmapDrawable bitmapDrawable, Drawable drawable) {
        Bitmap bitmap;
        if (bitmapDrawable != null) {
            bitmap = bitmapDrawable.getBitmap();
        } else {
            bitmap = null;
        }
        increment(bitmap);
        getTarget().onStart(drawable);
        decrement(bitmap);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object success(coil.request.SuccessResult r11, coil.transition.Transition r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r10 = this;
            boolean r0 = r13 instanceof coil.memory.PoolableTargetDelegate$success$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            coil.memory.PoolableTargetDelegate$success$1 r0 = (coil.memory.PoolableTargetDelegate$success$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            coil.memory.PoolableTargetDelegate$success$1 r0 = new coil.memory.PoolableTargetDelegate$success$1
            r0.<init>(r10, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0063
            if (r2 != r3) goto L_0x005b
            java.lang.Object r11 = r0.L$11
            coil.util.Logger r11 = (coil.util.Logger) r11
            java.lang.Object r11 = r0.L$10
            coil.EventListener r11 = (coil.EventListener) r11
            java.lang.Object r12 = r0.L$9
            coil.transition.Transition r12 = (coil.transition.Transition) r12
            java.lang.Object r1 = r0.L$8
            coil.request.SuccessResult r1 = (coil.request.SuccessResult) r1
            java.lang.Object r1 = r0.L$7
            coil.request.Request r1 = (coil.request.Request) r1
            java.lang.Object r2 = r0.L$6
            coil.target.Target r2 = (coil.target.Target) r2
            java.lang.Object r2 = r0.L$5
            coil.target.PoolableViewTarget r2 = (coil.target.PoolableViewTarget) r2
            java.lang.Object r2 = r0.L$4
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            java.lang.Object r3 = r0.L$3
            coil.memory.PoolableTargetDelegate r3 = (coil.memory.PoolableTargetDelegate) r3
            java.lang.Object r4 = r0.L$2
            coil.transition.Transition r4 = (coil.transition.Transition) r4
            java.lang.Object r4 = r0.L$1
            coil.request.SuccessResult r4 = (coil.request.SuccessResult) r4
            java.lang.Object r0 = r0.L$0
            coil.memory.PoolableTargetDelegate r0 = (coil.memory.PoolableTargetDelegate) r0
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00ed
        L_0x005b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r11
            coil.request.RequestResult r13 = (coil.request.RequestResult) r13
            android.graphics.Bitmap r2 = coil.memory.TargetDelegateKt.getBitmap(r13)
            r10.increment(r2)
            coil.target.PoolableViewTarget r4 = r10.getTarget()
            r5 = r4
            coil.target.Target r5 = (coil.target.Target) r5
            coil.request.Request r6 = r10.request
            coil.EventListener r7 = r10.eventListener
            coil.util.Logger r8 = r10.logger
            coil.transition.Transition r9 = coil.transition.Transition.NONE
            if (r12 != r9) goto L_0x0089
            android.graphics.drawable.Drawable r11 = r11.getDrawable()
            r5.onSuccess(r11)
            goto L_0x00c1
        L_0x0089:
            boolean r9 = r5 instanceof coil.transition.TransitionTarget
            if (r9 != 0) goto L_0x00c3
            if (r8 == 0) goto L_0x00ba
            r13 = 3
            int r0 = r8.getLevel()
            if (r0 > r13) goto L_0x00ba
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Ignoring '"
            r0.append(r1)
            r0.append(r12)
            java.lang.String r12 = "' as '"
            r0.append(r12)
            r0.append(r5)
            java.lang.String r12 = "' does not implement coil.transition.TransitionTarget."
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            r0 = 0
            java.lang.String r1 = "TargetDelegate"
            r8.log(r1, r13, r12, r0)
        L_0x00ba:
            android.graphics.drawable.Drawable r11 = r11.getDrawable()
            r5.onSuccess(r11)
        L_0x00c1:
            r3 = r10
            goto L_0x00f0
        L_0x00c3:
            r7.transitionStart(r6, r12)
            r9 = r5
            coil.transition.TransitionTarget r9 = (coil.transition.TransitionTarget) r9
            r0.L$0 = r10
            r0.L$1 = r11
            r0.L$2 = r12
            r0.L$3 = r10
            r0.L$4 = r2
            r0.L$5 = r4
            r0.L$6 = r5
            r0.L$7 = r6
            r0.L$8 = r11
            r0.L$9 = r12
            r0.L$10 = r7
            r0.L$11 = r8
            r0.label = r3
            java.lang.Object r11 = r12.transition(r9, r13, r0)
            if (r11 != r1) goto L_0x00ea
            return r1
        L_0x00ea:
            r3 = r10
            r1 = r6
            r11 = r7
        L_0x00ed:
            r11.transitionEnd(r1, r12)
        L_0x00f0:
            r3.decrement(r2)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.memory.PoolableTargetDelegate.success(coil.request.SuccessResult, coil.transition.Transition, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object error(coil.request.ErrorResult r11, coil.transition.Transition r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r10 = this;
            boolean r0 = r13 instanceof coil.memory.PoolableTargetDelegate$error$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            coil.memory.PoolableTargetDelegate$error$1 r0 = (coil.memory.PoolableTargetDelegate$error$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            coil.memory.PoolableTargetDelegate$error$1 r0 = new coil.memory.PoolableTargetDelegate$error$1
            r0.<init>(r10, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0060
            if (r2 != r3) goto L_0x0058
            java.lang.Object r11 = r0.L$10
            coil.util.Logger r11 = (coil.util.Logger) r11
            java.lang.Object r11 = r0.L$9
            coil.EventListener r11 = (coil.EventListener) r11
            java.lang.Object r12 = r0.L$8
            coil.transition.Transition r12 = (coil.transition.Transition) r12
            java.lang.Object r1 = r0.L$7
            coil.request.ErrorResult r1 = (coil.request.ErrorResult) r1
            java.lang.Object r1 = r0.L$6
            coil.request.Request r1 = (coil.request.Request) r1
            java.lang.Object r2 = r0.L$5
            coil.target.Target r2 = (coil.target.Target) r2
            java.lang.Object r2 = r0.L$4
            coil.target.PoolableViewTarget r2 = (coil.target.PoolableViewTarget) r2
            java.lang.Object r2 = r0.L$3
            coil.memory.PoolableTargetDelegate r2 = (coil.memory.PoolableTargetDelegate) r2
            java.lang.Object r3 = r0.L$2
            coil.transition.Transition r3 = (coil.transition.Transition) r3
            java.lang.Object r3 = r0.L$1
            coil.request.ErrorResult r3 = (coil.request.ErrorResult) r3
            java.lang.Object r0 = r0.L$0
            coil.memory.PoolableTargetDelegate r0 = (coil.memory.PoolableTargetDelegate) r0
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00e3
        L_0x0058:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0060:
            kotlin.ResultKt.throwOnFailure(r13)
            r10.increment(r4)
            coil.target.PoolableViewTarget r13 = r10.getTarget()
            r2 = r13
            coil.target.Target r2 = (coil.target.Target) r2
            coil.request.Request r5 = r10.request
            coil.EventListener r6 = r10.eventListener
            coil.util.Logger r7 = r10.logger
            coil.transition.Transition r8 = coil.transition.Transition.NONE
            if (r12 != r8) goto L_0x007f
            android.graphics.drawable.Drawable r11 = r11.getDrawable()
            r2.onError(r11)
            goto L_0x00b6
        L_0x007f:
            boolean r8 = r2 instanceof coil.transition.TransitionTarget
            if (r8 != 0) goto L_0x00b8
            if (r7 == 0) goto L_0x00af
            r13 = 3
            int r0 = r7.getLevel()
            if (r0 > r13) goto L_0x00af
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Ignoring '"
            r0.append(r1)
            r0.append(r12)
            java.lang.String r12 = "' as '"
            r0.append(r12)
            r0.append(r2)
            java.lang.String r12 = "' does not implement coil.transition.TransitionTarget."
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            java.lang.String r0 = "TargetDelegate"
            r7.log(r0, r13, r12, r4)
        L_0x00af:
            android.graphics.drawable.Drawable r11 = r11.getDrawable()
            r2.onError(r11)
        L_0x00b6:
            r2 = r10
            goto L_0x00e6
        L_0x00b8:
            r6.transitionStart(r5, r12)
            r8 = r2
            coil.transition.TransitionTarget r8 = (coil.transition.TransitionTarget) r8
            r9 = r11
            coil.request.RequestResult r9 = (coil.request.RequestResult) r9
            r0.L$0 = r10
            r0.L$1 = r11
            r0.L$2 = r12
            r0.L$3 = r10
            r0.L$4 = r13
            r0.L$5 = r2
            r0.L$6 = r5
            r0.L$7 = r11
            r0.L$8 = r12
            r0.L$9 = r6
            r0.L$10 = r7
            r0.label = r3
            java.lang.Object r11 = r12.transition(r8, r9, r0)
            if (r11 != r1) goto L_0x00e0
            return r1
        L_0x00e0:
            r2 = r10
            r1 = r5
            r11 = r6
        L_0x00e3:
            r11.transitionEnd(r1, r12)
        L_0x00e6:
            r2.decrement(r4)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.memory.PoolableTargetDelegate.error(coil.request.ErrorResult, coil.transition.Transition, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void clear() {
        increment((Bitmap) null);
        getTarget().onClear();
        decrement((Bitmap) null);
    }
}

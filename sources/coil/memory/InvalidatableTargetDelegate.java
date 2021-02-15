package coil.memory;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import coil.EventListener;
import coil.memory.Invalidatable;
import coil.request.Request;
import coil.target.Target;
import coil.util.Logger;
import com.facebook.share.internal.ShareConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ!\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u001c\u0010\u0017\u001a\u00020\u00112\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J!\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u0015H@ø\u0001\u0000¢\u0006\u0002\u0010\u001eR\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Lcoil/memory/InvalidatableTargetDelegate;", "Lcoil/memory/TargetDelegate;", "Lcoil/memory/Invalidatable;", "request", "Lcoil/request/Request;", "target", "Lcoil/target/Target;", "referenceCounter", "Lcoil/memory/BitmapReferenceCounter;", "eventListener", "Lcoil/EventListener;", "logger", "Lcoil/util/Logger;", "(Lcoil/request/Request;Lcoil/target/Target;Lcoil/memory/BitmapReferenceCounter;Lcoil/EventListener;Lcoil/util/Logger;)V", "getReferenceCounter", "()Lcoil/memory/BitmapReferenceCounter;", "error", "", "result", "Lcoil/request/ErrorResult;", "transition", "Lcoil/transition/Transition;", "(Lcoil/request/ErrorResult;Lcoil/transition/Transition;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "cached", "Landroid/graphics/drawable/BitmapDrawable;", "placeholder", "Landroid/graphics/drawable/Drawable;", "success", "Lcoil/request/SuccessResult;", "(Lcoil/request/SuccessResult;Lcoil/transition/Transition;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TargetDelegate.kt */
public final class InvalidatableTargetDelegate extends TargetDelegate implements Invalidatable {
    private final EventListener eventListener;
    private final Logger logger;
    private final BitmapReferenceCounter referenceCounter;
    private final Request request;
    private final Target target;

    public void invalidate(Bitmap bitmap) {
        Invalidatable.DefaultImpls.invalidate(this, bitmap);
    }

    public BitmapReferenceCounter getReferenceCounter() {
        return this.referenceCounter;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InvalidatableTargetDelegate(Request request2, Target target2, BitmapReferenceCounter bitmapReferenceCounter, EventListener eventListener2, Logger logger2) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(request2, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(target2, "target");
        Intrinsics.checkParameterIsNotNull(bitmapReferenceCounter, "referenceCounter");
        Intrinsics.checkParameterIsNotNull(eventListener2, "eventListener");
        this.request = request2;
        this.target = target2;
        this.referenceCounter = bitmapReferenceCounter;
        this.eventListener = eventListener2;
        this.logger = logger2;
    }

    public void start(BitmapDrawable bitmapDrawable, Drawable drawable) {
        invalidate(bitmapDrawable != null ? bitmapDrawable.getBitmap() : null);
        this.target.onStart(drawable);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object success(coil.request.SuccessResult r9, coil.transition.Transition r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof coil.memory.InvalidatableTargetDelegate$success$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            coil.memory.InvalidatableTargetDelegate$success$1 r0 = (coil.memory.InvalidatableTargetDelegate$success$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            coil.memory.InvalidatableTargetDelegate$success$1 r0 = new coil.memory.InvalidatableTargetDelegate$success$1
            r0.<init>(r8, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x004f
            if (r2 != r3) goto L_0x0047
            java.lang.Object r9 = r0.L$6
            coil.util.Logger r9 = (coil.util.Logger) r9
            java.lang.Object r9 = r0.L$5
            coil.EventListener r9 = (coil.EventListener) r9
            java.lang.Object r10 = r0.L$4
            coil.request.Request r10 = (coil.request.Request) r10
            java.lang.Object r1 = r0.L$3
            coil.target.Target r1 = (coil.target.Target) r1
            java.lang.Object r1 = r0.L$2
            coil.transition.Transition r1 = (coil.transition.Transition) r1
            java.lang.Object r2 = r0.L$1
            coil.request.SuccessResult r2 = (coil.request.SuccessResult) r2
            java.lang.Object r0 = r0.L$0
            coil.memory.InvalidatableTargetDelegate r0 = (coil.memory.InvalidatableTargetDelegate) r0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00c9
        L_0x0047:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x004f:
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = r9
            coil.request.RequestResult r11 = (coil.request.RequestResult) r11
            android.graphics.Bitmap r2 = coil.memory.TargetDelegateKt.getBitmap(r11)
            r8.invalidate(r2)
            coil.target.Target r2 = r8.target
            coil.request.Request r4 = r8.request
            coil.EventListener r5 = r8.eventListener
            coil.util.Logger r6 = r8.logger
            coil.transition.Transition r7 = coil.transition.Transition.NONE
            if (r10 != r7) goto L_0x0070
            android.graphics.drawable.Drawable r9 = r9.getDrawable()
            r2.onSuccess(r9)
            goto L_0x00cc
        L_0x0070:
            boolean r7 = r2 instanceof coil.transition.TransitionTarget
            if (r7 != 0) goto L_0x00a9
            if (r6 == 0) goto L_0x00a1
            r11 = 3
            int r0 = r6.getLevel()
            if (r0 > r11) goto L_0x00a1
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Ignoring '"
            r0.append(r1)
            r0.append(r10)
            java.lang.String r10 = "' as '"
            r0.append(r10)
            r0.append(r2)
            java.lang.String r10 = "' does not implement coil.transition.TransitionTarget."
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            r0 = 0
            java.lang.String r1 = "TargetDelegate"
            r6.log(r1, r11, r10, r0)
        L_0x00a1:
            android.graphics.drawable.Drawable r9 = r9.getDrawable()
            r2.onSuccess(r9)
            goto L_0x00cc
        L_0x00a9:
            r5.transitionStart(r4, r10)
            r7 = r2
            coil.transition.TransitionTarget r7 = (coil.transition.TransitionTarget) r7
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.L$3 = r2
            r0.L$4 = r4
            r0.L$5 = r5
            r0.L$6 = r6
            r0.label = r3
            java.lang.Object r9 = r10.transition(r7, r11, r0)
            if (r9 != r1) goto L_0x00c6
            return r1
        L_0x00c6:
            r1 = r10
            r10 = r4
            r9 = r5
        L_0x00c9:
            r9.transitionEnd(r10, r1)
        L_0x00cc:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.memory.InvalidatableTargetDelegate.success(coil.request.SuccessResult, coil.transition.Transition, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object error(coil.request.ErrorResult r9, coil.transition.Transition r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof coil.memory.InvalidatableTargetDelegate$error$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            coil.memory.InvalidatableTargetDelegate$error$1 r0 = (coil.memory.InvalidatableTargetDelegate$error$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            coil.memory.InvalidatableTargetDelegate$error$1 r0 = new coil.memory.InvalidatableTargetDelegate$error$1
            r0.<init>(r8, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x004f
            if (r2 != r3) goto L_0x0047
            java.lang.Object r9 = r0.L$6
            coil.util.Logger r9 = (coil.util.Logger) r9
            java.lang.Object r9 = r0.L$5
            coil.EventListener r9 = (coil.EventListener) r9
            java.lang.Object r10 = r0.L$4
            coil.request.Request r10 = (coil.request.Request) r10
            java.lang.Object r1 = r0.L$3
            coil.target.Target r1 = (coil.target.Target) r1
            java.lang.Object r1 = r0.L$2
            coil.transition.Transition r1 = (coil.transition.Transition) r1
            java.lang.Object r2 = r0.L$1
            coil.request.ErrorResult r2 = (coil.request.ErrorResult) r2
            java.lang.Object r0 = r0.L$0
            coil.memory.InvalidatableTargetDelegate r0 = (coil.memory.InvalidatableTargetDelegate) r0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00c2
        L_0x0047:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x004f:
            kotlin.ResultKt.throwOnFailure(r11)
            coil.target.Target r11 = r8.target
            coil.request.Request r2 = r8.request
            coil.EventListener r4 = r8.eventListener
            coil.util.Logger r5 = r8.logger
            coil.transition.Transition r6 = coil.transition.Transition.NONE
            if (r10 != r6) goto L_0x0066
            android.graphics.drawable.Drawable r9 = r9.getDrawable()
            r11.onError(r9)
            goto L_0x00c5
        L_0x0066:
            boolean r6 = r11 instanceof coil.transition.TransitionTarget
            if (r6 != 0) goto L_0x009f
            if (r5 == 0) goto L_0x0097
            r0 = 3
            int r1 = r5.getLevel()
            if (r1 > r0) goto L_0x0097
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Ignoring '"
            r1.append(r2)
            r1.append(r10)
            java.lang.String r10 = "' as '"
            r1.append(r10)
            r1.append(r11)
            java.lang.String r10 = "' does not implement coil.transition.TransitionTarget."
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            r1 = 0
            java.lang.String r2 = "TargetDelegate"
            r5.log(r2, r0, r10, r1)
        L_0x0097:
            android.graphics.drawable.Drawable r9 = r9.getDrawable()
            r11.onError(r9)
            goto L_0x00c5
        L_0x009f:
            r4.transitionStart(r2, r10)
            r6 = r11
            coil.transition.TransitionTarget r6 = (coil.transition.TransitionTarget) r6
            r7 = r9
            coil.request.RequestResult r7 = (coil.request.RequestResult) r7
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.L$3 = r11
            r0.L$4 = r2
            r0.L$5 = r4
            r0.L$6 = r5
            r0.label = r3
            java.lang.Object r9 = r10.transition(r6, r7, r0)
            if (r9 != r1) goto L_0x00bf
            return r1
        L_0x00bf:
            r1 = r10
            r10 = r2
            r9 = r4
        L_0x00c2:
            r9.transitionEnd(r10, r1)
        L_0x00c5:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.memory.InvalidatableTargetDelegate.error(coil.request.ErrorResult, coil.transition.Transition, kotlin.coroutines.Continuation):java.lang.Object");
    }
}

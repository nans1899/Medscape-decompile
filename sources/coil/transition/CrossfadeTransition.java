package coil.transition;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import coil.drawable.CrossfadeDrawable;
import coil.size.Scale;
import coil.util.Extensions;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0014B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J,\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J%\u0010\u0010\u001a\u00020\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u0011\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lcoil/transition/CrossfadeTransition;", "Lcoil/transition/Transition;", "durationMillis", "", "(I)V", "getDurationMillis", "()I", "createCrossfade", "Lcoil/drawable/CrossfadeDrawable;", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "", "target", "Lcoil/transition/TransitionTarget;", "drawable", "Landroid/graphics/drawable/Drawable;", "transition", "result", "Lcoil/request/RequestResult;", "(Lcoil/transition/TransitionTarget;Lcoil/request/RequestResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Callback", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CrossfadeTransition.kt */
public final class CrossfadeTransition implements Transition {
    private final int durationMillis;

    public CrossfadeTransition() {
        this(0, 1, (DefaultConstructorMarker) null);
    }

    public CrossfadeTransition(int i) {
        this.durationMillis = i;
        if (!(i > 0)) {
            throw new IllegalArgumentException("durationMillis must be > 0.".toString());
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CrossfadeTransition(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 100 : i);
    }

    public final int getDurationMillis() {
        return this.durationMillis;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [coil.transition.TransitionTarget<?>, coil.transition.TransitionTarget] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object transition(coil.transition.TransitionTarget<?> r5, coil.request.RequestResult r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof coil.request.SuccessResult
            if (r0 == 0) goto L_0x0019
            r1 = r6
            coil.request.SuccessResult r1 = (coil.request.SuccessResult) r1
            coil.decode.DataSource r2 = r1.getSource()
            coil.decode.DataSource r3 = coil.decode.DataSource.MEMORY_CACHE
            if (r2 != r3) goto L_0x0019
            android.graphics.drawable.Drawable r6 = r1.getDrawable()
            r5.onSuccess(r6)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x0019:
            android.view.View r1 = r5.getView()
            int r1 = r1.getVisibility()
            r2 = 1
            if (r1 != 0) goto L_0x0026
            r1 = 1
            goto L_0x0027
        L_0x0026:
            r1 = 0
        L_0x0027:
            if (r1 != 0) goto L_0x0043
            if (r0 == 0) goto L_0x0035
            coil.request.SuccessResult r6 = (coil.request.SuccessResult) r6
            android.graphics.drawable.Drawable r6 = r6.getDrawable()
            r5.onSuccess(r6)
            goto L_0x0040
        L_0x0035:
            boolean r7 = r6 instanceof coil.request.ErrorResult
            if (r7 == 0) goto L_0x0040
            android.graphics.drawable.Drawable r6 = r6.getDrawable()
            r5.onError(r6)
        L_0x0040:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x0043:
            kotlinx.coroutines.CancellableContinuationImpl r1 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r7)
            r1.<init>(r3, r2)
            r1.initCancellability()
            r2 = r1
            kotlinx.coroutines.CancellableContinuation r2 = (kotlinx.coroutines.CancellableContinuation) r2
            android.graphics.drawable.Drawable r3 = r6.getDrawable()
            coil.drawable.CrossfadeDrawable r2 = r4.createCrossfade(r2, r5, r3)
            if (r0 == 0) goto L_0x0062
            android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
            r5.onSuccess(r2)
            goto L_0x006b
        L_0x0062:
            boolean r6 = r6 instanceof coil.request.ErrorResult
            if (r6 == 0) goto L_0x006b
            android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
            r5.onError(r2)
        L_0x006b:
            java.lang.Object r5 = r1.getResult()
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r5 != r6) goto L_0x0078
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r7)
        L_0x0078:
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r5 != r6) goto L_0x007f
            return r5
        L_0x007f:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.transition.CrossfadeTransition.transition(coil.transition.TransitionTarget, coil.request.RequestResult, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final CrossfadeDrawable createCrossfade(CancellableContinuation<? super Unit> cancellableContinuation, TransitionTarget<?> transitionTarget, Drawable drawable) {
        Scale scale;
        Drawable drawable2 = transitionTarget.getDrawable();
        Object view = transitionTarget.getView();
        if (!(view instanceof ImageView)) {
            view = null;
        }
        ImageView imageView = (ImageView) view;
        if (imageView == null || (scale = Extensions.getScale(imageView)) == null) {
            scale = Scale.FILL;
        }
        CrossfadeDrawable crossfadeDrawable = new CrossfadeDrawable(drawable2, drawable, scale, this.durationMillis);
        Callback callback = new Callback(crossfadeDrawable, cancellableContinuation);
        crossfadeDrawable.registerAnimationCallback(callback);
        cancellableContinuation.invokeOnCancellation(callback);
        return crossfadeDrawable;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012#\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0002j\u0002`\bB\u001b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\f¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0002J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcoil/transition/CrossfadeTransition$Callback;", "Landroidx/vectordrawable/graphics/drawable/Animatable2Compat$AnimationCallback;", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "crossfade", "Lcoil/drawable/CrossfadeDrawable;", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "(Lcoil/drawable/CrossfadeDrawable;Lkotlinx/coroutines/CancellableContinuation;)V", "invoke", "onAnimationEnd", "drawable", "Landroid/graphics/drawable/Drawable;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: CrossfadeTransition.kt */
    private static final class Callback extends Animatable2Compat.AnimationCallback implements Function1<Throwable, Unit> {
        private final CancellableContinuation<Unit> continuation;
        private final CrossfadeDrawable crossfade;

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return Unit.INSTANCE;
        }

        public Callback(CrossfadeDrawable crossfadeDrawable, CancellableContinuation<? super Unit> cancellableContinuation) {
            Intrinsics.checkParameterIsNotNull(crossfadeDrawable, "crossfade");
            Intrinsics.checkParameterIsNotNull(cancellableContinuation, "continuation");
            this.crossfade = crossfadeDrawable;
            this.continuation = cancellableContinuation;
        }

        public void onAnimationEnd(Drawable drawable) {
            Intrinsics.checkParameterIsNotNull(drawable, "drawable");
            this.crossfade.unregisterAnimationCallback(this);
            Unit unit = Unit.INSTANCE;
            Result.Companion companion = Result.Companion;
            this.continuation.resumeWith(Result.m6constructorimpl(unit));
        }

        public void invoke(Throwable th) {
            this.crossfade.stop();
        }
    }
}

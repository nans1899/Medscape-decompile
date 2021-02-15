package coil.memory;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import coil.EventListener;
import coil.request.ErrorResult;
import coil.request.Request;
import coil.request.RequestResult;
import coil.request.SuccessResult;
import coil.target.PoolableViewTarget;
import coil.target.Target;
import coil.transition.Transition;
import coil.transition.TransitionTarget;
import coil.util.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u001b\u0010\b\u001a\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\u0004\u0012\u00020\u00060\t¢\u0006\u0002\b\u000bH\b\u001a?\u0010\f\u001a\u00020\u0006*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HHø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a?\u0010\u0019\u001a\u00020\u0006*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HHø\u0001\u0000¢\u0006\u0002\u0010\u001b\"\u001b\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028Â\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"bitmap", "Landroid/graphics/Bitmap;", "Lcoil/request/RequestResult;", "getBitmap", "(Lcoil/request/RequestResult;)Landroid/graphics/Bitmap;", "instrument", "", "Lcoil/memory/Poolable;", "update", "Lkotlin/Function1;", "Lcoil/target/PoolableViewTarget;", "Lkotlin/ExtensionFunctionType;", "onError", "Lcoil/target/Target;", "request", "Lcoil/request/Request;", "result", "Lcoil/request/ErrorResult;", "transition", "Lcoil/transition/Transition;", "eventListener", "Lcoil/EventListener;", "logger", "Lcoil/util/Logger;", "(Lcoil/target/Target;Lcoil/request/Request;Lcoil/request/ErrorResult;Lcoil/transition/Transition;Lcoil/EventListener;Lcoil/util/Logger;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onSuccess", "Lcoil/request/SuccessResult;", "(Lcoil/target/Target;Lcoil/request/Request;Lcoil/request/SuccessResult;Lcoil/transition/Transition;Lcoil/EventListener;Lcoil/util/Logger;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coil-base_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: TargetDelegate.kt */
public final class TargetDelegateKt {
    /* access modifiers changed from: private */
    public static final Bitmap getBitmap(RequestResult requestResult) {
        Drawable drawable = requestResult.getDrawable();
        if (!(drawable instanceof BitmapDrawable)) {
            drawable = null;
        }
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        if (bitmapDrawable != null) {
            return bitmapDrawable.getBitmap();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final void instrument(Poolable poolable, Bitmap bitmap, Function1<? super PoolableViewTarget<?>, Unit> function1) {
        poolable.increment(bitmap);
        function1.invoke(poolable.getTarget());
        poolable.decrement(bitmap);
    }

    private static final /* synthetic */ Object onSuccess(Target target, Request request, SuccessResult successResult, Transition transition, EventListener eventListener, Logger logger, Continuation<? super Unit> continuation) {
        if (transition == Transition.NONE) {
            target.onSuccess(successResult.getDrawable());
            return Unit.INSTANCE;
        } else if (!(target instanceof TransitionTarget)) {
            if (logger != null && logger.getLevel() <= 3) {
                logger.log("TargetDelegate", 3, "Ignoring '" + transition + "' as '" + target + "' does not implement coil.transition.TransitionTarget.", (Throwable) null);
            }
            target.onSuccess(successResult.getDrawable());
            return Unit.INSTANCE;
        } else {
            eventListener.transitionStart(request, transition);
            InlineMarker.mark(0);
            transition.transition((TransitionTarget) target, successResult, continuation);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            eventListener.transitionEnd(request, transition);
            return Unit.INSTANCE;
        }
    }

    private static final /* synthetic */ Object onError(Target target, Request request, ErrorResult errorResult, Transition transition, EventListener eventListener, Logger logger, Continuation<? super Unit> continuation) {
        if (transition == Transition.NONE) {
            target.onError(errorResult.getDrawable());
            return Unit.INSTANCE;
        } else if (!(target instanceof TransitionTarget)) {
            if (logger != null && logger.getLevel() <= 3) {
                logger.log("TargetDelegate", 3, "Ignoring '" + transition + "' as '" + target + "' does not implement coil.transition.TransitionTarget.", (Throwable) null);
            }
            target.onError(errorResult.getDrawable());
            return Unit.INSTANCE;
        } else {
            eventListener.transitionStart(request, transition);
            InlineMarker.mark(0);
            transition.transition((TransitionTarget) target, errorResult, continuation);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            eventListener.transitionEnd(request, transition);
            return Unit.INSTANCE;
        }
    }
}

package coil.memory;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import coil.request.ErrorResult;
import coil.request.SuccessResult;
import coil.transition.Transition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0017J!\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0017J!\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u0001\u0004\u0013\u0014\u0015\u0016\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lcoil/memory/TargetDelegate;", "", "()V", "clear", "", "error", "result", "Lcoil/request/ErrorResult;", "transition", "Lcoil/transition/Transition;", "(Lcoil/request/ErrorResult;Lcoil/transition/Transition;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "cached", "Landroid/graphics/drawable/BitmapDrawable;", "placeholder", "Landroid/graphics/drawable/Drawable;", "success", "Lcoil/request/SuccessResult;", "(Lcoil/request/SuccessResult;Lcoil/transition/Transition;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcoil/memory/EmptyTargetDelegate;", "Lcoil/memory/InvalidatableEmptyTargetDelegate;", "Lcoil/memory/InvalidatableTargetDelegate;", "Lcoil/memory/PoolableTargetDelegate;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TargetDelegate.kt */
public abstract class TargetDelegate {
    public void clear() {
    }

    public Object error(ErrorResult errorResult, Transition transition, Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    public void start(BitmapDrawable bitmapDrawable, Drawable drawable) {
    }

    public Object success(SuccessResult successResult, Transition transition, Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    private TargetDelegate() {
    }

    public /* synthetic */ TargetDelegate(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

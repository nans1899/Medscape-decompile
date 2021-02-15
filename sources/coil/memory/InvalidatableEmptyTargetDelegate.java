package coil.memory;

import android.graphics.Bitmap;
import coil.memory.Invalidatable;
import coil.request.SuccessResult;
import coil.transition.Transition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J!\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lcoil/memory/InvalidatableEmptyTargetDelegate;", "Lcoil/memory/TargetDelegate;", "Lcoil/memory/Invalidatable;", "referenceCounter", "Lcoil/memory/BitmapReferenceCounter;", "(Lcoil/memory/BitmapReferenceCounter;)V", "getReferenceCounter", "()Lcoil/memory/BitmapReferenceCounter;", "success", "", "result", "Lcoil/request/SuccessResult;", "transition", "Lcoil/transition/Transition;", "(Lcoil/request/SuccessResult;Lcoil/transition/Transition;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TargetDelegate.kt */
public final class InvalidatableEmptyTargetDelegate extends TargetDelegate implements Invalidatable {
    private final BitmapReferenceCounter referenceCounter;

    public void invalidate(Bitmap bitmap) {
        Invalidatable.DefaultImpls.invalidate(this, bitmap);
    }

    public BitmapReferenceCounter getReferenceCounter() {
        return this.referenceCounter;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InvalidatableEmptyTargetDelegate(BitmapReferenceCounter bitmapReferenceCounter) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(bitmapReferenceCounter, "referenceCounter");
        this.referenceCounter = bitmapReferenceCounter;
    }

    public Object success(SuccessResult successResult, Transition transition, Continuation<? super Unit> continuation) {
        invalidate(TargetDelegateKt.getBitmap(successResult));
        return Unit.INSTANCE;
    }
}

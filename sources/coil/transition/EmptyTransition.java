package coil.transition;

import coil.request.ErrorResult;
import coil.request.RequestResult;
import coil.request.SuccessResult;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\t\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lcoil/transition/EmptyTransition;", "Lcoil/transition/Transition;", "()V", "transition", "", "target", "Lcoil/transition/TransitionTarget;", "result", "Lcoil/request/RequestResult;", "(Lcoil/transition/TransitionTarget;Lcoil/request/RequestResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: EmptyTransition.kt */
public final class EmptyTransition implements Transition {
    public static final EmptyTransition INSTANCE = new EmptyTransition();

    private EmptyTransition() {
    }

    public Object transition(TransitionTarget<?> transitionTarget, RequestResult requestResult, Continuation<? super Unit> continuation) {
        if (requestResult instanceof SuccessResult) {
            transitionTarget.onSuccess(((SuccessResult) requestResult).getDrawable());
        } else if (requestResult instanceof ErrorResult) {
            transitionTarget.onError(requestResult.getDrawable());
        }
        return Unit.INSTANCE;
    }
}

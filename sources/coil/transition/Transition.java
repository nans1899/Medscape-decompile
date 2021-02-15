package coil.transition;

import coil.request.RequestResult;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000 \t2\u00020\u0001:\u0001\tJ%\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u0007H§@ø\u0001\u0001¢\u0006\u0002\u0010\b\u0002\u000b\n\u0005\bF0\u0001\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lcoil/transition/Transition;", "", "transition", "", "target", "Lcoil/transition/TransitionTarget;", "result", "Lcoil/request/RequestResult;", "(Lcoil/transition/TransitionTarget;Lcoil/request/RequestResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Transition.kt */
public interface Transition {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final Transition NONE = EmptyTransition.INSTANCE;

    Object transition(TransitionTarget<?> transitionTarget, RequestResult requestResult, Continuation<? super Unit> continuation);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000¨\u0006\u0001\u0002\u0007\n\u0005\bF0\u0001¨\u0006\u0005"}, d2 = {"Lcoil/transition/Transition$Companion;", "", "()V", "NONE", "Lcoil/transition/Transition;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Transition.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

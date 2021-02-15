package org.mockito.internal.stubbing;

import org.mockito.internal.MockitoCore;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.LenientStubber;
import org.mockito.stubbing.OngoingStubbing;
import org.mockito.stubbing.Stubber;

public class DefaultLenientStubber implements LenientStubber {
    private static final MockitoCore MOCKITO_CORE = new MockitoCore();

    public Stubber doThrow(Throwable... thArr) {
        return stubber().doThrow(thArr);
    }

    public Stubber doThrow(Class<? extends Throwable> cls) {
        return stubber().doThrow(cls);
    }

    public Stubber doThrow(Class<? extends Throwable> cls, Class<? extends Throwable>... clsArr) {
        return stubber().doThrow(cls, clsArr);
    }

    public Stubber doAnswer(Answer answer) {
        return stubber().doAnswer(answer);
    }

    public Stubber doNothing() {
        return stubber().doNothing();
    }

    public Stubber doReturn(Object obj) {
        return stubber().doReturn(obj);
    }

    public Stubber doReturn(Object obj, Object... objArr) {
        return stubber().doReturn(obj, objArr);
    }

    public Stubber doCallRealMethod() {
        return stubber().doCallRealMethod();
    }

    public <T> OngoingStubbing<T> when(T t) {
        OngoingStubbingImpl ongoingStubbingImpl = (OngoingStubbingImpl) MOCKITO_CORE.when(t);
        ongoingStubbingImpl.setStrictness(Strictness.LENIENT);
        return ongoingStubbingImpl;
    }

    private static Stubber stubber() {
        return MOCKITO_CORE.stubber(Strictness.LENIENT);
    }
}

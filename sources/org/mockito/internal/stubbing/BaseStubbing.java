package org.mockito.internal.stubbing;

import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.stubbing.answers.CallsRealMethods;
import org.mockito.internal.stubbing.answers.Returns;
import org.mockito.internal.stubbing.answers.ThrowsException;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;
import org.objenesis.ObjenesisHelper;

public abstract class BaseStubbing<T> implements OngoingStubbing<T> {
    private final Object strongMockRef;

    BaseStubbing(Object obj) {
        this.strongMockRef = obj;
    }

    public OngoingStubbing<T> then(Answer<?> answer) {
        return thenAnswer(answer);
    }

    public OngoingStubbing<T> thenReturn(T t) {
        return thenAnswer(new Returns(t));
    }

    public OngoingStubbing<T> thenReturn(T t, T... tArr) {
        OngoingStubbing<T> thenReturn = thenReturn(t);
        if (tArr == null) {
            return thenReturn.thenReturn(null);
        }
        for (T thenReturn2 : tArr) {
            thenReturn = thenReturn.thenReturn(thenReturn2);
        }
        return thenReturn;
    }

    private OngoingStubbing<T> thenThrow(Throwable th) {
        return thenAnswer(new ThrowsException(th));
    }

    public OngoingStubbing<T> thenThrow(Throwable... thArr) {
        OngoingStubbing<T> ongoingStubbing = null;
        if (thArr == null) {
            return thenThrow((Throwable) null);
        }
        for (Throwable th : thArr) {
            if (ongoingStubbing == null) {
                ongoingStubbing = thenThrow(th);
            } else {
                ongoingStubbing = ongoingStubbing.thenThrow(th);
            }
        }
        return ongoingStubbing;
    }

    public OngoingStubbing<T> thenThrow(Class<? extends Throwable> cls) {
        if (cls != null) {
            return thenThrow((Throwable) ObjenesisHelper.newInstance(cls));
        }
        ThreadSafeMockingProgress.mockingProgress().reset();
        throw Reporter.notAnException();
    }

    public OngoingStubbing<T> thenThrow(Class<? extends Throwable> cls, Class<? extends Throwable>... clsArr) {
        if (clsArr == null) {
            return thenThrow((Class<? extends Throwable>) null);
        }
        OngoingStubbing<? extends Throwable> thenThrow = thenThrow(cls);
        for (Class<? extends Throwable> thenThrow2 : clsArr) {
            thenThrow = thenThrow.thenThrow(thenThrow2);
        }
        return thenThrow;
    }

    public OngoingStubbing<T> thenCallRealMethod() {
        return thenAnswer(new CallsRealMethods());
    }

    public <M> M getMock() {
        return this.strongMockRef;
    }
}

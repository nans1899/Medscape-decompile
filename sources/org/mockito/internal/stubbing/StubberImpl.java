package org.mockito.internal.stubbing;

import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.stubbing.answers.CallsRealMethods;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.internal.stubbing.answers.Returns;
import org.mockito.internal.stubbing.answers.ThrowsException;
import org.mockito.internal.util.MockUtil;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;
import org.objenesis.ObjenesisHelper;

public class StubberImpl implements Stubber {
    private final List<Answer<?>> answers = new LinkedList();
    private final Strictness strictness;

    public StubberImpl(Strictness strictness2) {
        this.strictness = strictness2;
    }

    public <T> T when(T t) {
        if (t == null) {
            throw Reporter.nullPassedToWhenMethod();
        } else if (MockUtil.isMock(t)) {
            MockUtil.getInvocationContainer(t).setAnswersForStubbing(this.answers, this.strictness);
            return t;
        } else {
            throw Reporter.notAMockPassedToWhenMethod();
        }
    }

    public Stubber doReturn(Object obj) {
        return doReturnValues(obj);
    }

    public Stubber doReturn(Object obj, Object... objArr) {
        return doReturnValues(obj).doReturnValues(objArr);
    }

    private StubberImpl doReturnValues(Object... objArr) {
        if (objArr == null) {
            this.answers.add(new Returns((Object) null));
            return this;
        }
        for (Object returns : objArr) {
            this.answers.add(new Returns(returns));
        }
        return this;
    }

    public Stubber doThrow(Throwable... thArr) {
        if (thArr == null) {
            this.answers.add(new ThrowsException((Throwable) null));
            return this;
        }
        for (Throwable throwsException : thArr) {
            this.answers.add(new ThrowsException(throwsException));
        }
        return this;
    }

    public Stubber doThrow(Class<? extends Throwable> cls) {
        if (cls != null) {
            try {
                Throwable th = (Throwable) ObjenesisHelper.newInstance(cls);
                if (th == null) {
                }
                return doThrow(th);
            } finally {
                ThreadSafeMockingProgress.mockingProgress().reset();
            }
        } else {
            ThreadSafeMockingProgress.mockingProgress().reset();
            throw Reporter.notAnException();
        }
    }

    public Stubber doThrow(Class<? extends Throwable> cls, Class<? extends Throwable>... clsArr) {
        Stubber doThrow = doThrow(cls);
        if (clsArr != null) {
            for (Class<? extends Throwable> doThrow2 : clsArr) {
                doThrow = doThrow.doThrow(doThrow2);
            }
            return doThrow;
        }
        ThreadSafeMockingProgress.mockingProgress().reset();
        throw Reporter.notAnException();
    }

    public Stubber doNothing() {
        this.answers.add(DoesNothing.doesNothing());
        return this;
    }

    public Stubber doAnswer(Answer answer) {
        this.answers.add(answer);
        return this;
    }

    public Stubber doCallRealMethod() {
        this.answers.add(new CallsRealMethods());
        return this;
    }
}

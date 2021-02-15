package org.mockito.internal.stubbing.answers;

import java.io.Serializable;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.exceptions.stacktrace.ConditionalStackTraceFilter;
import org.mockito.internal.util.MockUtil;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.ValidableAnswer;

public class ThrowsException implements Answer<Object>, ValidableAnswer, Serializable {
    private static final long serialVersionUID = 1128820328555183980L;
    private final ConditionalStackTraceFilter filter = new ConditionalStackTraceFilter();
    private final Throwable throwable;

    public ThrowsException(Throwable th) {
        this.throwable = th;
    }

    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        Throwable th = this.throwable;
        if (th == null) {
            throw new IllegalStateException("throwable is null: you shall not call #answer if #validateFor fails!");
        } else if (!MockUtil.isMock(th)) {
            Throwable fillInStackTrace = this.throwable.fillInStackTrace();
            if (fillInStackTrace == null) {
                throw this.throwable;
            }
            this.filter.filter(fillInStackTrace);
            throw fillInStackTrace;
        } else {
            throw this.throwable;
        }
    }

    public void validateFor(InvocationOnMock invocationOnMock) {
        Throwable th = this.throwable;
        if (th == null) {
            throw Reporter.cannotStubWithNullThrowable();
        } else if (!(th instanceof RuntimeException) && !(th instanceof Error) && !new InvocationInfo(invocationOnMock).isValidException(this.throwable)) {
            throw Reporter.checkedExceptionInvalid(this.throwable);
        }
    }
}

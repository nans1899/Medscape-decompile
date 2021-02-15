package org.mockito.exceptions.base;

import org.mockito.internal.exceptions.stacktrace.ConditionalStackTraceFilter;

public class MockitoException extends RuntimeException {
    private static final long serialVersionUID = 1;
    private StackTraceElement[] unfilteredStackTrace;

    public MockitoException(String str, Throwable th) {
        super(str, th);
        filterStackTrace();
    }

    public MockitoException(String str) {
        super(str);
        filterStackTrace();
    }

    private void filterStackTrace() {
        this.unfilteredStackTrace = getStackTrace();
        new ConditionalStackTraceFilter().filter(this);
    }

    public StackTraceElement[] getUnfilteredStackTrace() {
        return this.unfilteredStackTrace;
    }
}

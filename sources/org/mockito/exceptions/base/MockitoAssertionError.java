package org.mockito.exceptions.base;

import org.apache.commons.io.IOUtils;
import org.mockito.internal.exceptions.stacktrace.ConditionalStackTraceFilter;

public class MockitoAssertionError extends AssertionError {
    private static final long serialVersionUID = 1;
    private final StackTraceElement[] unfilteredStackTrace;

    public MockitoAssertionError(String str) {
        super(str);
        this.unfilteredStackTrace = getStackTrace();
        new ConditionalStackTraceFilter().filter(this);
    }

    public MockitoAssertionError(MockitoAssertionError mockitoAssertionError, String str) {
        super(str + IOUtils.LINE_SEPARATOR_UNIX + mockitoAssertionError.getMessage());
        super.setStackTrace(mockitoAssertionError.getStackTrace());
        this.unfilteredStackTrace = mockitoAssertionError.getUnfilteredStackTrace();
    }

    public StackTraceElement[] getUnfilteredStackTrace() {
        return this.unfilteredStackTrace;
    }
}

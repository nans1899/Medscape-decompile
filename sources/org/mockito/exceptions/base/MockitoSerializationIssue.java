package org.mockito.exceptions.base;

import java.io.ObjectStreamException;
import org.mockito.internal.exceptions.stacktrace.ConditionalStackTraceFilter;

public class MockitoSerializationIssue extends ObjectStreamException {
    private StackTraceElement[] unfilteredStackTrace;

    public MockitoSerializationIssue(String str, Exception exc) {
        super(str);
        initCause(exc);
        filterStackTrace();
    }

    private void filterStackTrace() {
        this.unfilteredStackTrace = super.getStackTrace();
        new ConditionalStackTraceFilter().filter(this);
    }

    public StackTraceElement[] getUnfilteredStackTrace() {
        return this.unfilteredStackTrace;
    }
}

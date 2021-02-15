package org.mockito.internal.exceptions;

public class ExceptionIncludingMockitoWarnings extends RuntimeException {
    private static final long serialVersionUID = -5925150219446765679L;

    public ExceptionIncludingMockitoWarnings(String str, Throwable th) {
        super(str, th);
    }
}

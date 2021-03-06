package org.mockito.internal.exceptions.stacktrace;

import org.mockito.exceptions.stacktrace.StackTraceCleaner;
import org.mockito.plugins.StackTraceCleanerProvider;

public class DefaultStackTraceCleanerProvider implements StackTraceCleanerProvider {
    public StackTraceCleaner getStackTraceCleaner(StackTraceCleaner stackTraceCleaner) {
        return stackTraceCleaner;
    }
}

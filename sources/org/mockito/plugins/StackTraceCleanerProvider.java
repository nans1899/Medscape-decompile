package org.mockito.plugins;

import org.mockito.exceptions.stacktrace.StackTraceCleaner;

public interface StackTraceCleanerProvider {
    StackTraceCleaner getStackTraceCleaner(StackTraceCleaner stackTraceCleaner);
}

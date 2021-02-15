package org.mockito.internal.util;

import org.mockito.plugins.MockitoLogger;

public class ConsoleMockitoLogger implements MockitoLogger {
    public void log(Object obj) {
        System.out.println(obj);
    }
}

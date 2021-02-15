package org.mockito.internal.session;

import org.mockito.plugins.MockitoLogger;
import org.mockito.session.MockitoSessionLogger;

public class MockitoSessionLoggerAdapter implements MockitoSessionLogger {
    private final MockitoLogger logger;

    public MockitoSessionLoggerAdapter(MockitoLogger mockitoLogger) {
        this.logger = mockitoLogger;
    }

    public void log(String str) {
        this.logger.log(str);
    }
}

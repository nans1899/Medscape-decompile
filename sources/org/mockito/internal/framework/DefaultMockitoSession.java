package org.mockito.internal.framework;

import java.util.List;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoSession;
import org.mockito.exceptions.misusing.RedundantListenerException;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.junit.TestFinishedEvent;
import org.mockito.internal.junit.UniversalTestListener;
import org.mockito.plugins.MockitoLogger;
import org.mockito.quality.Strictness;

public class DefaultMockitoSession implements MockitoSession {
    private final UniversalTestListener listener;
    /* access modifiers changed from: private */
    public final String name;

    public DefaultMockitoSession(List<Object> list, String str, Strictness strictness, MockitoLogger mockitoLogger) {
        this.name = str;
        this.listener = new UniversalTestListener(strictness, mockitoLogger);
        try {
            Mockito.framework().addListener(this.listener);
        } catch (RedundantListenerException unused) {
            Reporter.unfinishedMockingSession();
        }
        try {
            for (Object initMocks : list) {
                MockitoAnnotations.initMocks(initMocks);
            }
        } catch (RuntimeException e) {
            this.listener.setListenerDirty();
            throw e;
        }
    }

    public void setStrictness(Strictness strictness) {
        this.listener.setStrictness(strictness);
    }

    public void finishMocking() {
        finishMocking((Throwable) null);
    }

    public void finishMocking(final Throwable th) {
        Mockito.framework().removeListener(this.listener);
        this.listener.testFinished(new TestFinishedEvent() {
            public Throwable getFailure() {
                return th;
            }

            public String getTestName() {
                return DefaultMockitoSession.this.name;
            }
        });
        if (th == null) {
            Mockito.validateMockitoUsage();
        }
    }
}

package org.mockito.internal.framework;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import org.mockito.MockitoFramework;
import org.mockito.internal.configuration.plugins.Plugins;
import org.mockito.internal.invocation.DefaultInvocationFactory;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.util.Checks;
import org.mockito.invocation.InvocationFactory;
import org.mockito.listeners.MockitoListener;
import org.mockito.plugins.InlineMockMaker;
import org.mockito.plugins.MockMaker;
import org.mockito.plugins.MockitoPlugins;

public class DefaultMockitoFramework implements MockitoFramework {
    public MockitoFramework addListener(MockitoListener mockitoListener) {
        Checks.checkNotNull(mockitoListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        ThreadSafeMockingProgress.mockingProgress().addListener(mockitoListener);
        return this;
    }

    public MockitoFramework removeListener(MockitoListener mockitoListener) {
        Checks.checkNotNull(mockitoListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        ThreadSafeMockingProgress.mockingProgress().removeListener(mockitoListener);
        return this;
    }

    public MockitoPlugins getPlugins() {
        return Plugins.getPlugins();
    }

    public InvocationFactory getInvocationFactory() {
        return new DefaultInvocationFactory();
    }

    private InlineMockMaker getInlineMockMaker() {
        MockMaker mockMaker = Plugins.getMockMaker();
        if (mockMaker instanceof InlineMockMaker) {
            return (InlineMockMaker) mockMaker;
        }
        return null;
    }

    public void clearInlineMocks() {
        InlineMockMaker inlineMockMaker = getInlineMockMaker();
        if (inlineMockMaker != null) {
            inlineMockMaker.clearAllMocks();
        }
    }

    public void clearInlineMock(Object obj) {
        InlineMockMaker inlineMockMaker = getInlineMockMaker();
        if (inlineMockMaker != null) {
            inlineMockMaker.clearMock(obj);
        }
    }
}

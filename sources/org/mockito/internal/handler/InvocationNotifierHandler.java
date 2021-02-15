package org.mockito.internal.handler;

import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationContainer;
import org.mockito.invocation.MockHandler;
import org.mockito.listeners.InvocationListener;
import org.mockito.mock.MockCreationSettings;

class InvocationNotifierHandler<T> implements MockHandler<T> {
    private final List<InvocationListener> invocationListeners;
    private final MockHandler<T> mockHandler;

    public InvocationNotifierHandler(MockHandler<T> mockHandler2, MockCreationSettings<T> mockCreationSettings) {
        this.mockHandler = mockHandler2;
        this.invocationListeners = mockCreationSettings.getInvocationListeners();
    }

    public Object handle(Invocation invocation) throws Throwable {
        try {
            Object handle = this.mockHandler.handle(invocation);
            notifyMethodCall(invocation, handle);
            return handle;
        } catch (Throwable th) {
            notifyMethodCallException(invocation, th);
            throw th;
        }
    }

    private void notifyMethodCall(Invocation invocation, Object obj) {
        for (InvocationListener next : this.invocationListeners) {
            try {
                next.reportInvocation(new NotifiedMethodInvocationReport(invocation, obj));
            } catch (Throwable th) {
                throw Reporter.invocationListenerThrewException(next, th);
            }
        }
    }

    private void notifyMethodCallException(Invocation invocation, Throwable th) {
        for (InvocationListener next : this.invocationListeners) {
            try {
                next.reportInvocation(new NotifiedMethodInvocationReport(invocation, th));
            } catch (Throwable th2) {
                throw Reporter.invocationListenerThrewException(next, th2);
            }
        }
    }

    public MockCreationSettings<T> getMockSettings() {
        return this.mockHandler.getMockSettings();
    }

    public InvocationContainer getInvocationContainer() {
        return this.mockHandler.getInvocationContainer();
    }
}

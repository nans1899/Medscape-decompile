package org.mockito.internal.handler;

import org.mockito.internal.util.Primitives;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationContainer;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;

class NullResultGuardian<T> implements MockHandler<T> {
    private final MockHandler<T> delegate;

    public NullResultGuardian(MockHandler<T> mockHandler) {
        this.delegate = mockHandler;
    }

    public Object handle(Invocation invocation) throws Throwable {
        Object handle = this.delegate.handle(invocation);
        Class<?> returnType = invocation.getMethod().getReturnType();
        return (handle != null || !returnType.isPrimitive()) ? handle : Primitives.defaultValue(returnType);
    }

    public MockCreationSettings<T> getMockSettings() {
        return this.delegate.getMockSettings();
    }

    public InvocationContainer getInvocationContainer() {
        return this.delegate.getInvocationContainer();
    }
}

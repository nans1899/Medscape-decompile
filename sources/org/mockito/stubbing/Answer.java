package org.mockito.stubbing;

import org.mockito.invocation.InvocationOnMock;

public interface Answer<T> {
    T answer(InvocationOnMock invocationOnMock) throws Throwable;
}

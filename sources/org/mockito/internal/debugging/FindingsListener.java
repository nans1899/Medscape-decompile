package org.mockito.internal.debugging;

import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.invocation.Invocation;

public interface FindingsListener {
    void foundStubCalledWithDifferentArgs(Invocation invocation, InvocationMatcher invocationMatcher);

    void foundUnstubbed(InvocationMatcher invocationMatcher);

    void foundUnusedStub(Invocation invocation);
}

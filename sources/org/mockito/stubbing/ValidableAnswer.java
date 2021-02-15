package org.mockito.stubbing;

import org.mockito.Incubating;
import org.mockito.invocation.InvocationOnMock;

@Incubating
public interface ValidableAnswer {
    void validateFor(InvocationOnMock invocationOnMock);
}

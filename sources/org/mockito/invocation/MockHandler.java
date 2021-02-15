package org.mockito.invocation;

import java.io.Serializable;
import org.mockito.Incubating;
import org.mockito.mock.MockCreationSettings;

public interface MockHandler<T> extends Serializable {
    @Incubating
    InvocationContainer getInvocationContainer();

    @Incubating
    MockCreationSettings<T> getMockSettings();

    Object handle(Invocation invocation) throws Throwable;
}

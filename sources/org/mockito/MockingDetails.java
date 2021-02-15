package org.mockito;

import java.util.Collection;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.stubbing.Stubbing;

public interface MockingDetails {
    Collection<Invocation> getInvocations();

    Object getMock();

    MockCreationSettings<?> getMockCreationSettings();

    @Incubating
    MockHandler getMockHandler();

    Collection<Stubbing> getStubbings();

    boolean isMock();

    boolean isSpy();

    String printInvocations();
}

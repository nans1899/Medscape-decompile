package org.mockito.internal.util;

import java.util.Collection;
import org.mockito.MockingDetails;
import org.mockito.exceptions.misusing.NotAMockException;
import org.mockito.internal.debugging.InvocationsPrinter;
import org.mockito.internal.stubbing.InvocationContainerImpl;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.stubbing.Stubbing;

public class DefaultMockingDetails implements MockingDetails {
    private final Object toInspect;

    public DefaultMockingDetails(Object obj) {
        this.toInspect = obj;
    }

    public boolean isMock() {
        return MockUtil.isMock(this.toInspect);
    }

    public boolean isSpy() {
        return MockUtil.isSpy(this.toInspect);
    }

    public Collection<Invocation> getInvocations() {
        return getInvocationContainer().getInvocations();
    }

    private InvocationContainerImpl getInvocationContainer() {
        assertGoodMock();
        return MockUtil.getInvocationContainer(this.toInspect);
    }

    public MockCreationSettings<?> getMockCreationSettings() {
        return mockHandler().getMockSettings();
    }

    public Collection<Stubbing> getStubbings() {
        return getInvocationContainer().getStubbingsAscending();
    }

    public String printInvocations() {
        assertGoodMock();
        return new InvocationsPrinter().printInvocations(this.toInspect);
    }

    public MockHandler getMockHandler() {
        return mockHandler();
    }

    public Object getMock() {
        return this.toInspect;
    }

    private MockHandler<Object> mockHandler() {
        assertGoodMock();
        return MockUtil.getMockHandler(this.toInspect);
    }

    private void assertGoodMock() {
        if (this.toInspect == null) {
            throw new NotAMockException("Argument passed to Mockito.mockingDetails() should be a mock, but is null!");
        } else if (!isMock()) {
            throw new NotAMockException("Argument passed to Mockito.mockingDetails() should be a mock, but is an instance of " + this.toInspect.getClass() + "!");
        }
    }
}

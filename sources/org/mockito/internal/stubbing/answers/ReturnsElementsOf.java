package org.mockito.internal.stubbing.answers;

import java.util.Collection;
import java.util.LinkedList;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ReturnsElementsOf implements Answer<Object> {
    private final LinkedList<Object> elements;

    public ReturnsElementsOf(Collection<?> collection) {
        if (collection != null) {
            this.elements = new LinkedList<>(collection);
            return;
        }
        throw new MockitoException("ReturnsElementsOf does not accept null as constructor argument.\nPlease pass a collection instance");
    }

    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        if (this.elements.size() == 1) {
            return this.elements.get(0);
        }
        return this.elements.poll();
    }
}

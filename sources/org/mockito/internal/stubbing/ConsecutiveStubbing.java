package org.mockito.internal.stubbing;

import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

public class ConsecutiveStubbing<T> extends BaseStubbing<T> {
    private final InvocationContainerImpl invocationContainer;

    ConsecutiveStubbing(InvocationContainerImpl invocationContainerImpl) {
        super(invocationContainerImpl.invokedMock());
        this.invocationContainer = invocationContainerImpl;
    }

    public OngoingStubbing<T> thenAnswer(Answer<?> answer) {
        this.invocationContainer.addConsecutiveAnswer(answer);
        return this;
    }
}

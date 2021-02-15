package org.mockito.internal.stubbing;

import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.invocation.Invocation;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

public class OngoingStubbingImpl<T> extends BaseStubbing<T> {
    private final InvocationContainerImpl invocationContainer;
    private Strictness strictness;

    public OngoingStubbingImpl(InvocationContainerImpl invocationContainerImpl) {
        super(invocationContainerImpl.invokedMock());
        this.invocationContainer = invocationContainerImpl;
    }

    public OngoingStubbing<T> thenAnswer(Answer<?> answer) {
        if (this.invocationContainer.hasInvocationForPotentialStubbing()) {
            this.invocationContainer.addAnswer(answer, this.strictness);
            return new ConsecutiveStubbing(this.invocationContainer);
        }
        throw Reporter.incorrectUseOfApi();
    }

    public List<Invocation> getRegisteredInvocations() {
        return this.invocationContainer.getInvocations();
    }

    public void setStrictness(Strictness strictness2) {
        this.strictness = strictness2;
    }
}

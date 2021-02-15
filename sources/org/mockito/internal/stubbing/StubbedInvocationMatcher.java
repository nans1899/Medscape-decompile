package org.mockito.internal.stubbing;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.invocation.MatchableInvocation;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubbing;

public class StubbedInvocationMatcher extends InvocationMatcher implements Serializable, Stubbing {
    private static final long serialVersionUID = 4919105134123672727L;
    private final Queue<Answer> answers;
    private final Strictness strictness;
    private DescribedInvocation usedAt;

    public StubbedInvocationMatcher(Answer answer, MatchableInvocation matchableInvocation, Strictness strictness2) {
        super(matchableInvocation.getInvocation(), matchableInvocation.getMatchers());
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        this.answers = concurrentLinkedQueue;
        this.strictness = strictness2;
        concurrentLinkedQueue.add(answer);
    }

    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        Answer answer;
        synchronized (this.answers) {
            answer = (Answer) (this.answers.size() == 1 ? this.answers.peek() : this.answers.poll());
        }
        return answer.answer(invocationOnMock);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void markStubUsed(DescribedInvocation describedInvocation) {
        this.usedAt = describedInvocation;
    }

    public boolean wasUsed() {
        return this.usedAt != null;
    }

    public String toString() {
        return super.toString() + " stubbed with: " + this.answers;
    }

    public Strictness getStrictness() {
        return this.strictness;
    }
}

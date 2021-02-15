package org.mockito.internal.stubbing;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.invocation.StubInfoImpl;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.verification.DefaultRegisteredInvocations;
import org.mockito.internal.verification.RegisteredInvocations;
import org.mockito.internal.verification.SingleRegisteredInvocation;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationContainer;
import org.mockito.invocation.MatchableInvocation;
import org.mockito.mock.MockCreationSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubbing;
import org.mockito.stubbing.ValidableAnswer;

public class InvocationContainerImpl implements InvocationContainer, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = -5334301962749537177L;
    private final DoAnswerStyleStubbing doAnswerStyleStubbing;
    private MatchableInvocation invocationForStubbing;
    private final Strictness mockStrictness;
    private final RegisteredInvocations registeredInvocations;
    private final LinkedList<StubbedInvocationMatcher> stubbed = new LinkedList<>();

    public InvocationContainerImpl(MockCreationSettings mockCreationSettings) {
        this.registeredInvocations = createRegisteredInvocations(mockCreationSettings);
        this.mockStrictness = mockCreationSettings.isLenient() ? Strictness.LENIENT : null;
        this.doAnswerStyleStubbing = new DoAnswerStyleStubbing();
    }

    public void setInvocationForPotentialStubbing(MatchableInvocation matchableInvocation) {
        this.registeredInvocations.add(matchableInvocation.getInvocation());
        this.invocationForStubbing = matchableInvocation;
    }

    public void resetInvocationForPotentialStubbing(MatchableInvocation matchableInvocation) {
        this.invocationForStubbing = matchableInvocation;
    }

    public void addAnswer(Answer answer, Strictness strictness) {
        this.registeredInvocations.removeLast();
        addAnswer(answer, false, strictness);
    }

    public void addConsecutiveAnswer(Answer answer) {
        addAnswer(answer, true, (Strictness) null);
    }

    public StubbedInvocationMatcher addAnswer(Answer answer, boolean z, Strictness strictness) {
        StubbedInvocationMatcher first;
        Invocation invocation = this.invocationForStubbing.getInvocation();
        ThreadSafeMockingProgress.mockingProgress().stubbingCompleted();
        if (answer instanceof ValidableAnswer) {
            ((ValidableAnswer) answer).validateFor(invocation);
        }
        synchronized (this.stubbed) {
            if (z) {
                try {
                    this.stubbed.getFirst().addAnswer(answer);
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                if (strictness == null) {
                    strictness = this.mockStrictness;
                }
                this.stubbed.addFirst(new StubbedInvocationMatcher(answer, this.invocationForStubbing, strictness));
            }
            first = this.stubbed.getFirst();
        }
        return first;
    }

    /* access modifiers changed from: package-private */
    public Object answerTo(Invocation invocation) throws Throwable {
        return findAnswerFor(invocation).answer(invocation);
    }

    public StubbedInvocationMatcher findAnswerFor(Invocation invocation) {
        synchronized (this.stubbed) {
            Iterator it = this.stubbed.iterator();
            while (it.hasNext()) {
                StubbedInvocationMatcher stubbedInvocationMatcher = (StubbedInvocationMatcher) it.next();
                if (stubbedInvocationMatcher.matches(invocation)) {
                    stubbedInvocationMatcher.markStubUsed(invocation);
                    invocation.markStubbed(new StubInfoImpl(stubbedInvocationMatcher));
                    return stubbedInvocationMatcher;
                }
            }
            return null;
        }
    }

    public void setAnswersForStubbing(List<Answer<?>> list, Strictness strictness) {
        this.doAnswerStyleStubbing.setAnswers(list, strictness);
    }

    public boolean hasAnswersForStubbing() {
        return !this.doAnswerStyleStubbing.isSet();
    }

    public boolean hasInvocationForPotentialStubbing() {
        return !this.registeredInvocations.isEmpty();
    }

    public void setMethodForStubbing(MatchableInvocation matchableInvocation) {
        this.invocationForStubbing = matchableInvocation;
        int i = 0;
        while (i < this.doAnswerStyleStubbing.getAnswers().size()) {
            addAnswer(this.doAnswerStyleStubbing.getAnswers().get(i), i != 0, this.doAnswerStyleStubbing.getStubbingStrictness());
            i++;
        }
        this.doAnswerStyleStubbing.clear();
    }

    public String toString() {
        return "invocationForStubbing: " + this.invocationForStubbing;
    }

    public List<Invocation> getInvocations() {
        return this.registeredInvocations.getAll();
    }

    public void clearInvocations() {
        this.registeredInvocations.clear();
    }

    public List<Stubbing> getStubbingsDescending() {
        return this.stubbed;
    }

    public Collection<Stubbing> getStubbingsAscending() {
        LinkedList linkedList = new LinkedList(this.stubbed);
        Collections.reverse(linkedList);
        return linkedList;
    }

    public Object invokedMock() {
        return this.invocationForStubbing.getInvocation().getMock();
    }

    public MatchableInvocation getInvocationForStubbing() {
        return this.invocationForStubbing;
    }

    private RegisteredInvocations createRegisteredInvocations(MockCreationSettings mockCreationSettings) {
        RegisteredInvocations registeredInvocations2;
        if (mockCreationSettings.isStubOnly()) {
            registeredInvocations2 = new SingleRegisteredInvocation();
        } else {
            registeredInvocations2 = new DefaultRegisteredInvocations();
        }
        return registeredInvocations2;
    }
}

package org.mockito.internal.handler;

import org.mockito.internal.creation.settings.CreationSettings;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.internal.invocation.MatchersBinder;
import org.mockito.internal.listeners.StubbingLookupNotifier;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.stubbing.InvocationContainerImpl;
import org.mockito.internal.stubbing.OngoingStubbingImpl;
import org.mockito.internal.stubbing.StubbedInvocationMatcher;
import org.mockito.internal.stubbing.answers.DefaultAnswerValidator;
import org.mockito.internal.verification.MockAwareVerificationMode;
import org.mockito.internal.verification.VerificationDataImpl;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationContainer;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.verification.VerificationMode;

public class MockHandlerImpl<T> implements MockHandler<T> {
    private static final long serialVersionUID = -2917871070982574165L;
    InvocationContainerImpl invocationContainer;
    MatchersBinder matchersBinder = new MatchersBinder();
    private final MockCreationSettings<T> mockSettings;

    public MockHandlerImpl(MockCreationSettings<T> mockCreationSettings) {
        this.mockSettings = mockCreationSettings;
        this.matchersBinder = new MatchersBinder();
        this.invocationContainer = new InvocationContainerImpl(mockCreationSettings);
    }

    public Object handle(Invocation invocation) throws Throwable {
        if (this.invocationContainer.hasAnswersForStubbing()) {
            this.invocationContainer.setMethodForStubbing(this.matchersBinder.bindMatchers(ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage(), invocation));
            return null;
        }
        VerificationMode pullVerificationMode = ThreadSafeMockingProgress.mockingProgress().pullVerificationMode();
        InvocationMatcher bindMatchers = this.matchersBinder.bindMatchers(ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage(), invocation);
        ThreadSafeMockingProgress.mockingProgress().validateState();
        if (pullVerificationMode != null) {
            if (((MockAwareVerificationMode) pullVerificationMode).getMock() == invocation.getMock()) {
                pullVerificationMode.verify(new VerificationDataImpl(this.invocationContainer, bindMatchers));
                return null;
            }
            ThreadSafeMockingProgress.mockingProgress().verificationStarted(pullVerificationMode);
        }
        this.invocationContainer.setInvocationForPotentialStubbing(bindMatchers);
        OngoingStubbingImpl ongoingStubbingImpl = new OngoingStubbingImpl(this.invocationContainer);
        ThreadSafeMockingProgress.mockingProgress().reportOngoingStubbing(ongoingStubbingImpl);
        StubbedInvocationMatcher findAnswerFor = this.invocationContainer.findAnswerFor(invocation);
        StubbingLookupNotifier.notifyStubbedAnswerLookup(invocation, findAnswerFor, this.invocationContainer.getStubbingsAscending(), (CreationSettings) this.mockSettings);
        if (findAnswerFor != null) {
            findAnswerFor.captureArgumentsFrom(invocation);
            try {
                return findAnswerFor.answer(invocation);
            } finally {
                ThreadSafeMockingProgress.mockingProgress().reportOngoingStubbing(ongoingStubbingImpl);
            }
        } else {
            Object answer = this.mockSettings.getDefaultAnswer().answer(invocation);
            DefaultAnswerValidator.validateReturnValueFor(invocation, answer);
            this.invocationContainer.resetInvocationForPotentialStubbing(bindMatchers);
            return answer;
        }
    }

    public MockCreationSettings<T> getMockSettings() {
        return this.mockSettings;
    }

    public InvocationContainer getInvocationContainer() {
        return this.invocationContainer;
    }
}

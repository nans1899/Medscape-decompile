package org.mockito.internal;

import java.util.Arrays;
import java.util.List;
import org.mockito.InOrder;
import org.mockito.MockSettings;
import org.mockito.MockingDetails;
import org.mockito.exceptions.misusing.NotAMockException;
import org.mockito.internal.creation.MockSettingsImpl;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.internal.invocation.finder.VerifiableInvocationsFinder;
import org.mockito.internal.listeners.VerificationStartedNotifier;
import org.mockito.internal.progress.MockingProgress;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.stubbing.DefaultLenientStubber;
import org.mockito.internal.stubbing.InvocationContainerImpl;
import org.mockito.internal.stubbing.OngoingStubbingImpl;
import org.mockito.internal.stubbing.StubberImpl;
import org.mockito.internal.util.DefaultMockingDetails;
import org.mockito.internal.util.MockUtil;
import org.mockito.internal.verification.MockAwareVerificationMode;
import org.mockito.internal.verification.VerificationDataImpl;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.internal.verification.api.InOrderContext;
import org.mockito.internal.verification.api.VerificationDataInOrderImpl;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;
import org.mockito.mock.MockCreationSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.LenientStubber;
import org.mockito.stubbing.OngoingStubbing;
import org.mockito.stubbing.Stubber;
import org.mockito.verification.VerificationMode;

public class MockitoCore {
    public boolean isTypeMockable(Class<?> cls) {
        return MockUtil.typeMockabilityOf(cls).mockable();
    }

    public <T> T mock(Class<T> cls, MockSettings mockSettings) {
        if (MockSettingsImpl.class.isInstance(mockSettings)) {
            MockCreationSettings<T> build = MockSettingsImpl.class.cast(mockSettings).build(cls);
            T createMock = MockUtil.createMock(build);
            ThreadSafeMockingProgress.mockingProgress().mockingStarted(createMock, build);
            return createMock;
        }
        throw new IllegalArgumentException("Unexpected implementation of '" + mockSettings.getClass().getCanonicalName() + "'\nAt the moment, you cannot provide your own implementations of that class.");
    }

    public <T> OngoingStubbing<T> when(T t) {
        MockingProgress mockingProgress = ThreadSafeMockingProgress.mockingProgress();
        mockingProgress.stubbingStarted();
        OngoingStubbing<?> pullOngoingStubbing = mockingProgress.pullOngoingStubbing();
        if (pullOngoingStubbing != null) {
            return pullOngoingStubbing;
        }
        mockingProgress.reset();
        throw Reporter.missingMethodInvocation();
    }

    public <T> T verify(T t, VerificationMode verificationMode) {
        if (t != null) {
            MockingDetails mockingDetails = mockingDetails(t);
            if (mockingDetails.isMock()) {
                assertNotStubOnlyMock(t);
                T notifyVerificationStarted = VerificationStartedNotifier.notifyVerificationStarted(mockingDetails.getMockHandler().getMockSettings().getVerificationStartedListeners(), mockingDetails);
                MockingProgress mockingProgress = ThreadSafeMockingProgress.mockingProgress();
                mockingProgress.verificationStarted(new MockAwareVerificationMode(notifyVerificationStarted, mockingProgress.maybeVerifyLazily(verificationMode), mockingProgress.verificationListeners()));
                return notifyVerificationStarted;
            }
            throw Reporter.notAMockPassedToVerify(t.getClass());
        }
        throw Reporter.nullPassedToVerify();
    }

    public <T> void reset(T... tArr) {
        MockingProgress mockingProgress = ThreadSafeMockingProgress.mockingProgress();
        mockingProgress.validateState();
        mockingProgress.reset();
        mockingProgress.resetOngoingStubbing();
        for (T resetMock : tArr) {
            MockUtil.resetMock(resetMock);
        }
    }

    public <T> void clearInvocations(T... tArr) {
        MockingProgress mockingProgress = ThreadSafeMockingProgress.mockingProgress();
        mockingProgress.validateState();
        mockingProgress.reset();
        mockingProgress.resetOngoingStubbing();
        for (T invocationContainer : tArr) {
            MockUtil.getInvocationContainer(invocationContainer).clearInvocations();
        }
    }

    public void verifyNoMoreInteractions(Object... objArr) {
        assertMocksNotEmpty(objArr);
        ThreadSafeMockingProgress.mockingProgress().validateState();
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Object obj = objArr[i];
            if (obj != null) {
                try {
                    InvocationContainerImpl invocationContainer = MockUtil.getInvocationContainer(obj);
                    assertNotStubOnlyMock(obj);
                    VerificationModeFactory.noMoreInteractions().verify(new VerificationDataImpl(invocationContainer, (InvocationMatcher) null));
                    i++;
                } catch (NotAMockException unused) {
                    throw Reporter.notAMockPassedToVerifyNoMoreInteractions();
                }
            } else {
                throw Reporter.nullPassedToVerifyNoMoreInteractions();
            }
        }
    }

    public void verifyNoMoreInteractionsInOrder(List<Object> list, InOrderContext inOrderContext) {
        ThreadSafeMockingProgress.mockingProgress().validateState();
        VerificationModeFactory.noMoreInteractions().verifyInOrder(new VerificationDataInOrderImpl(inOrderContext, VerifiableInvocationsFinder.find(list), (MatchableInvocation) null));
    }

    private void assertMocksNotEmpty(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            throw Reporter.mocksHaveToBePassedToVerifyNoMoreInteractions();
        }
    }

    private void assertNotStubOnlyMock(Object obj) {
        if (MockUtil.getMockHandler(obj).getMockSettings().isStubOnly()) {
            throw Reporter.stubPassedToVerify(obj);
        }
    }

    public InOrder inOrder(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            throw Reporter.mocksHaveToBePassedWhenCreatingInOrder();
        }
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Object obj = objArr[i];
            if (obj == null) {
                throw Reporter.nullPassedWhenCreatingInOrder();
            } else if (MockUtil.isMock(obj)) {
                assertNotStubOnlyMock(obj);
                i++;
            } else {
                throw Reporter.notAMockPassedWhenCreatingInOrder();
            }
        }
        return new InOrderImpl(Arrays.asList(objArr));
    }

    public Stubber stubber() {
        return stubber((Strictness) null);
    }

    public Stubber stubber(Strictness strictness) {
        MockingProgress mockingProgress = ThreadSafeMockingProgress.mockingProgress();
        mockingProgress.stubbingStarted();
        mockingProgress.resetOngoingStubbing();
        return new StubberImpl(strictness);
    }

    public void validateMockitoUsage() {
        ThreadSafeMockingProgress.mockingProgress().validateState();
    }

    public Invocation getLastInvocation() {
        List<Invocation> registeredInvocations = ((OngoingStubbingImpl) ThreadSafeMockingProgress.mockingProgress().pullOngoingStubbing()).getRegisteredInvocations();
        return registeredInvocations.get(registeredInvocations.size() - 1);
    }

    public Object[] ignoreStubs(Object... objArr) {
        for (Object invocationContainer : objArr) {
            for (Invocation next : MockUtil.getInvocationContainer(invocationContainer).getInvocations()) {
                if (next.stubInfo() != null) {
                    next.ignoreForVerification();
                }
            }
        }
        return objArr;
    }

    public MockingDetails mockingDetails(Object obj) {
        return new DefaultMockingDetails(obj);
    }

    public LenientStubber lenient() {
        return new DefaultLenientStubber();
    }
}

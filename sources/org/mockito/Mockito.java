package org.mockito;

import org.mockito.internal.MockitoCore;
import org.mockito.internal.creation.MockSettingsImpl;
import org.mockito.internal.debugging.MockitoDebuggerImpl;
import org.mockito.internal.framework.DefaultMockitoFramework;
import org.mockito.internal.session.DefaultMockitoSessionBuilder;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.session.MockitoSessionBuilder;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.LenientStubber;
import org.mockito.stubbing.OngoingStubbing;
import org.mockito.stubbing.Stubber;
import org.mockito.verification.After;
import org.mockito.verification.Timeout;
import org.mockito.verification.VerificationAfterDelay;
import org.mockito.verification.VerificationMode;
import org.mockito.verification.VerificationWithTimeout;

public class Mockito extends ArgumentMatchers {
    public static final Answer<Object> CALLS_REAL_METHODS = Answers.CALLS_REAL_METHODS;
    static final MockitoCore MOCKITO_CORE = new MockitoCore();
    public static final Answer<Object> RETURNS_DEEP_STUBS = Answers.RETURNS_DEEP_STUBS;
    public static final Answer<Object> RETURNS_DEFAULTS = Answers.RETURNS_DEFAULTS;
    public static final Answer<Object> RETURNS_MOCKS = Answers.RETURNS_MOCKS;
    public static final Answer<Object> RETURNS_SELF = Answers.RETURNS_SELF;
    public static final Answer<Object> RETURNS_SMART_NULLS = Answers.RETURNS_SMART_NULLS;

    public static <T> T mock(Class<T> cls) {
        return mock(cls, withSettings());
    }

    public static <T> T mock(Class<T> cls, String str) {
        return mock(cls, withSettings().name(str).defaultAnswer(RETURNS_DEFAULTS));
    }

    public static MockingDetails mockingDetails(Object obj) {
        return MOCKITO_CORE.mockingDetails(obj);
    }

    public static <T> T mock(Class<T> cls, Answer answer) {
        return mock(cls, withSettings().defaultAnswer(answer));
    }

    public static <T> T mock(Class<T> cls, MockSettings mockSettings) {
        return MOCKITO_CORE.mock(cls, mockSettings);
    }

    public static <T> T spy(T t) {
        return MOCKITO_CORE.mock(t.getClass(), withSettings().spiedInstance(t).defaultAnswer(CALLS_REAL_METHODS));
    }

    @Incubating
    public static <T> T spy(Class<T> cls) {
        return MOCKITO_CORE.mock(cls, withSettings().useConstructor(new Object[0]).defaultAnswer(CALLS_REAL_METHODS));
    }

    public static <T> OngoingStubbing<T> when(T t) {
        return MOCKITO_CORE.when(t);
    }

    public static <T> T verify(T t) {
        return MOCKITO_CORE.verify(t, times(1));
    }

    public static <T> T verify(T t, VerificationMode verificationMode) {
        return MOCKITO_CORE.verify(t, verificationMode);
    }

    public static <T> void reset(T... tArr) {
        MOCKITO_CORE.reset(tArr);
    }

    public static <T> void clearInvocations(T... tArr) {
        MOCKITO_CORE.clearInvocations(tArr);
    }

    public static void verifyNoMoreInteractions(Object... objArr) {
        MOCKITO_CORE.verifyNoMoreInteractions(objArr);
    }

    public static void verifyZeroInteractions(Object... objArr) {
        MOCKITO_CORE.verifyNoMoreInteractions(objArr);
    }

    public static Stubber doThrow(Throwable... thArr) {
        return MOCKITO_CORE.stubber().doThrow(thArr);
    }

    public static Stubber doThrow(Class<? extends Throwable> cls) {
        return MOCKITO_CORE.stubber().doThrow(cls);
    }

    public static Stubber doThrow(Class<? extends Throwable> cls, Class<? extends Throwable>... clsArr) {
        return MOCKITO_CORE.stubber().doThrow(cls, clsArr);
    }

    public static Stubber doCallRealMethod() {
        return MOCKITO_CORE.stubber().doCallRealMethod();
    }

    public static Stubber doAnswer(Answer answer) {
        return MOCKITO_CORE.stubber().doAnswer(answer);
    }

    public static Stubber doNothing() {
        return MOCKITO_CORE.stubber().doNothing();
    }

    public static Stubber doReturn(Object obj) {
        return MOCKITO_CORE.stubber().doReturn(obj);
    }

    public static Stubber doReturn(Object obj, Object... objArr) {
        return MOCKITO_CORE.stubber().doReturn(obj, objArr);
    }

    public static InOrder inOrder(Object... objArr) {
        return MOCKITO_CORE.inOrder(objArr);
    }

    public static Object[] ignoreStubs(Object... objArr) {
        return MOCKITO_CORE.ignoreStubs(objArr);
    }

    public static VerificationMode times(int i) {
        return VerificationModeFactory.times(i);
    }

    public static VerificationMode never() {
        return times(0);
    }

    public static VerificationMode atLeastOnce() {
        return VerificationModeFactory.atLeastOnce();
    }

    public static VerificationMode atLeast(int i) {
        return VerificationModeFactory.atLeast(i);
    }

    public static VerificationMode atMost(int i) {
        return VerificationModeFactory.atMost(i);
    }

    public static VerificationMode calls(int i) {
        return VerificationModeFactory.calls(i);
    }

    public static VerificationMode only() {
        return VerificationModeFactory.only();
    }

    public static VerificationWithTimeout timeout(long j) {
        return new Timeout(j, VerificationModeFactory.times(1));
    }

    public static VerificationAfterDelay after(long j) {
        return new After(j, VerificationModeFactory.times(1));
    }

    public static void validateMockitoUsage() {
        MOCKITO_CORE.validateMockitoUsage();
    }

    public static MockSettings withSettings() {
        return new MockSettingsImpl().defaultAnswer(RETURNS_DEFAULTS);
    }

    public static VerificationMode description(String str) {
        return times(1).description(str);
    }

    @Deprecated
    static MockitoDebugger debug() {
        return new MockitoDebuggerImpl();
    }

    @Incubating
    public static MockitoFramework framework() {
        return new DefaultMockitoFramework();
    }

    @Incubating
    public static MockitoSessionBuilder mockitoSession() {
        return new DefaultMockitoSessionBuilder();
    }

    @Incubating
    public static LenientStubber lenient() {
        return MOCKITO_CORE.lenient();
    }
}

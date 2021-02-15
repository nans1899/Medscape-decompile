package org.mockito;

import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;
import org.mockito.stubbing.Stubber;
import org.mockito.verification.VerificationMode;

public class BDDMockito extends Mockito {

    public interface BDDMyOngoingStubbing<T> {
        <M> M getMock();

        BDDMyOngoingStubbing<T> will(Answer<?> answer);

        BDDMyOngoingStubbing<T> willAnswer(Answer<?> answer);

        BDDMyOngoingStubbing<T> willCallRealMethod();

        BDDMyOngoingStubbing<T> willReturn(T t);

        BDDMyOngoingStubbing<T> willReturn(T t, T... tArr);

        BDDMyOngoingStubbing<T> willThrow(Class<? extends Throwable> cls);

        BDDMyOngoingStubbing<T> willThrow(Class<? extends Throwable> cls, Class<? extends Throwable>... clsArr);

        BDDMyOngoingStubbing<T> willThrow(Throwable... thArr);
    }

    public interface BDDStubber {
        <T> T given(T t);

        BDDStubber will(Answer<?> answer);

        BDDStubber willAnswer(Answer<?> answer);

        BDDStubber willCallRealMethod();

        BDDStubber willDoNothing();

        @Deprecated
        BDDStubber willNothing();

        BDDStubber willReturn(Object obj);

        BDDStubber willReturn(Object obj, Object... objArr);

        BDDStubber willThrow(Class<? extends Throwable> cls);

        BDDStubber willThrow(Class<? extends Throwable> cls, Class<? extends Throwable>... clsArr);

        BDDStubber willThrow(Throwable... thArr);
    }

    public interface Then<T> {
        T should();

        T should(InOrder inOrder);

        T should(InOrder inOrder, VerificationMode verificationMode);

        T should(VerificationMode verificationMode);

        void shouldHaveNoMoreInteractions();

        void shouldHaveZeroInteractions();
    }

    private static class BDDOngoingStubbingImpl<T> implements BDDMyOngoingStubbing<T> {
        private final OngoingStubbing<T> mockitoOngoingStubbing;

        public BDDOngoingStubbingImpl(OngoingStubbing<T> ongoingStubbing) {
            this.mockitoOngoingStubbing = ongoingStubbing;
        }

        public BDDMyOngoingStubbing<T> willAnswer(Answer<?> answer) {
            return new BDDOngoingStubbingImpl(this.mockitoOngoingStubbing.thenAnswer(answer));
        }

        public BDDMyOngoingStubbing<T> will(Answer<?> answer) {
            return new BDDOngoingStubbingImpl(this.mockitoOngoingStubbing.then(answer));
        }

        public BDDMyOngoingStubbing<T> willReturn(T t) {
            return new BDDOngoingStubbingImpl(this.mockitoOngoingStubbing.thenReturn(t));
        }

        public BDDMyOngoingStubbing<T> willReturn(T t, T... tArr) {
            return new BDDOngoingStubbingImpl(this.mockitoOngoingStubbing.thenReturn(t, tArr));
        }

        public BDDMyOngoingStubbing<T> willThrow(Throwable... thArr) {
            return new BDDOngoingStubbingImpl(this.mockitoOngoingStubbing.thenThrow(thArr));
        }

        public BDDMyOngoingStubbing<T> willThrow(Class<? extends Throwable> cls) {
            return new BDDOngoingStubbingImpl(this.mockitoOngoingStubbing.thenThrow(cls));
        }

        public BDDMyOngoingStubbing<T> willThrow(Class<? extends Throwable> cls, Class<? extends Throwable>... clsArr) {
            return new BDDOngoingStubbingImpl(this.mockitoOngoingStubbing.thenThrow(cls, clsArr));
        }

        public BDDMyOngoingStubbing<T> willCallRealMethod() {
            return new BDDOngoingStubbingImpl(this.mockitoOngoingStubbing.thenCallRealMethod());
        }

        public <M> M getMock() {
            return this.mockitoOngoingStubbing.getMock();
        }
    }

    public static <T> BDDMyOngoingStubbing<T> given(T t) {
        return new BDDOngoingStubbingImpl(Mockito.when(t));
    }

    public static <T> Then<T> then(T t) {
        return new ThenImpl(t);
    }

    private static class ThenImpl<T> implements Then<T> {
        private final T mock;

        ThenImpl(T t) {
            this.mock = t;
        }

        public T should() {
            return Mockito.verify(this.mock);
        }

        public T should(VerificationMode verificationMode) {
            return Mockito.verify(this.mock, verificationMode);
        }

        public T should(InOrder inOrder) {
            return inOrder.verify(this.mock);
        }

        public T should(InOrder inOrder, VerificationMode verificationMode) {
            return inOrder.verify(this.mock, verificationMode);
        }

        public void shouldHaveZeroInteractions() {
            Mockito.verifyZeroInteractions(this.mock);
        }

        public void shouldHaveNoMoreInteractions() {
            Mockito.verifyNoMoreInteractions(this.mock);
        }
    }

    private static class BDDStubberImpl implements BDDStubber {
        private final Stubber mockitoStubber;

        public BDDStubberImpl(Stubber stubber) {
            this.mockitoStubber = stubber;
        }

        public <T> T given(T t) {
            return this.mockitoStubber.when(t);
        }

        public BDDStubber willAnswer(Answer<?> answer) {
            return new BDDStubberImpl(this.mockitoStubber.doAnswer(answer));
        }

        public BDDStubber will(Answer<?> answer) {
            return new BDDStubberImpl(this.mockitoStubber.doAnswer(answer));
        }

        @Deprecated
        public BDDStubber willNothing() {
            return willDoNothing();
        }

        public BDDStubber willDoNothing() {
            return new BDDStubberImpl(this.mockitoStubber.doNothing());
        }

        public BDDStubber willReturn(Object obj) {
            return new BDDStubberImpl(this.mockitoStubber.doReturn(obj));
        }

        public BDDStubber willReturn(Object obj, Object... objArr) {
            return new BDDStubberImpl(this.mockitoStubber.doReturn(obj).doReturn(objArr));
        }

        public BDDStubber willThrow(Throwable... thArr) {
            return new BDDStubberImpl(this.mockitoStubber.doThrow(thArr));
        }

        public BDDStubber willThrow(Class<? extends Throwable> cls) {
            return new BDDStubberImpl(this.mockitoStubber.doThrow(cls));
        }

        public BDDStubber willThrow(Class<? extends Throwable> cls, Class<? extends Throwable>... clsArr) {
            return new BDDStubberImpl(this.mockitoStubber.doThrow(cls, clsArr));
        }

        public BDDStubber willCallRealMethod() {
            return new BDDStubberImpl(this.mockitoStubber.doCallRealMethod());
        }
    }

    public static BDDStubber willThrow(Throwable... thArr) {
        return new BDDStubberImpl(Mockito.doThrow(thArr));
    }

    public static BDDStubber willThrow(Class<? extends Throwable> cls) {
        return new BDDStubberImpl(Mockito.doThrow(cls));
    }

    public static BDDStubber willThrow(Class<? extends Throwable> cls, Class<? extends Throwable>... clsArr) {
        return new BDDStubberImpl(Mockito.doThrow(cls, clsArr));
    }

    public static BDDStubber willAnswer(Answer<?> answer) {
        return new BDDStubberImpl(Mockito.doAnswer(answer));
    }

    public static BDDStubber will(Answer<?> answer) {
        return new BDDStubberImpl(Mockito.doAnswer(answer));
    }

    public static BDDStubber willDoNothing() {
        return new BDDStubberImpl(Mockito.doNothing());
    }

    public static BDDStubber willReturn(Object obj) {
        return new BDDStubberImpl(Mockito.doReturn(obj));
    }

    public static BDDStubber willReturn(Object obj, Object... objArr) {
        return new BDDStubberImpl(Mockito.doReturn(obj, objArr));
    }

    public static BDDStubber willCallRealMethod() {
        return new BDDStubberImpl(Mockito.doCallRealMethod());
    }
}

package org.mockito.stubbing;

public interface OngoingStubbing<T> {
    <M> M getMock();

    OngoingStubbing<T> then(Answer<?> answer);

    OngoingStubbing<T> thenAnswer(Answer<?> answer);

    OngoingStubbing<T> thenCallRealMethod();

    OngoingStubbing<T> thenReturn(T t);

    OngoingStubbing<T> thenReturn(T t, T... tArr);

    OngoingStubbing<T> thenThrow(Class<? extends Throwable> cls);

    OngoingStubbing<T> thenThrow(Class<? extends Throwable> cls, Class<? extends Throwable>... clsArr);

    OngoingStubbing<T> thenThrow(Throwable... thArr);
}

package org.mockito.stubbing;

import org.mockito.NotExtensible;

@NotExtensible
public interface BaseStubber {
    Stubber doAnswer(Answer answer);

    Stubber doCallRealMethod();

    Stubber doNothing();

    Stubber doReturn(Object obj);

    Stubber doReturn(Object obj, Object... objArr);

    Stubber doThrow(Class<? extends Throwable> cls);

    Stubber doThrow(Class<? extends Throwable> cls, Class<? extends Throwable>... clsArr);

    Stubber doThrow(Throwable... thArr);
}

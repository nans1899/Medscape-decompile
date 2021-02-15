package org.mockito.invocation;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface InvocationOnMock extends Serializable {
    Object callRealMethod() throws Throwable;

    <T> T getArgument(int i);

    Object[] getArguments();

    Method getMethod();

    Object getMock();
}

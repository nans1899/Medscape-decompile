package org.mockito.listeners;

import org.mockito.invocation.DescribedInvocation;

public interface MethodInvocationReport {
    DescribedInvocation getInvocation();

    String getLocationOfStubbing();

    Object getReturnedValue();

    Throwable getThrowable();

    boolean threwException();
}

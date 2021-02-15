package org.mockito.invocation;

import org.mockito.NotExtensible;

@NotExtensible
public interface Invocation extends InvocationOnMock, DescribedInvocation {
    Location getLocation();

    Object[] getRawArguments();

    Class<?> getRawReturnType();

    int getSequenceNumber();

    void ignoreForVerification();

    boolean isIgnoredForVerification();

    boolean isVerified();

    void markStubbed(StubInfo stubInfo);

    void markVerified();

    StubInfo stubInfo();
}

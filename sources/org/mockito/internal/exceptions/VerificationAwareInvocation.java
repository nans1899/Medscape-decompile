package org.mockito.internal.exceptions;

import org.mockito.invocation.DescribedInvocation;

public interface VerificationAwareInvocation extends DescribedInvocation {
    boolean isVerified();
}

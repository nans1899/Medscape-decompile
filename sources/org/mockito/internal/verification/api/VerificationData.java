package org.mockito.internal.verification.api;

import java.util.List;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

public interface VerificationData {
    List<Invocation> getAllInvocations();

    MatchableInvocation getTarget();

    @Deprecated
    InvocationMatcher getWanted();
}

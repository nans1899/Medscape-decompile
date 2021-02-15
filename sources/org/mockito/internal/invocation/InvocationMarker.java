package org.mockito.internal.invocation;

import java.util.List;
import org.mockito.internal.verification.api.InOrderContext;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

public class InvocationMarker {
    private InvocationMarker() {
    }

    public static void markVerified(List<Invocation> list, MatchableInvocation matchableInvocation) {
        for (Invocation markVerified : list) {
            markVerified(markVerified, matchableInvocation);
        }
    }

    public static void markVerified(Invocation invocation, MatchableInvocation matchableInvocation) {
        invocation.markVerified();
        matchableInvocation.captureArgumentsFrom(invocation);
    }

    public static void markVerifiedInOrder(List<Invocation> list, MatchableInvocation matchableInvocation, InOrderContext inOrderContext) {
        markVerified(list, matchableInvocation);
        for (Invocation markVerified : list) {
            inOrderContext.markVerified(markVerified);
        }
    }
}

package org.mockito.internal.verification;

import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.invocation.InvocationMarker;
import org.mockito.internal.invocation.InvocationsFinder;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;
import org.mockito.verification.VerificationMode;

public class Only implements VerificationMode {
    public void verify(VerificationData verificationData) {
        MatchableInvocation target = verificationData.getTarget();
        List<Invocation> allInvocations = verificationData.getAllInvocations();
        List<Invocation> findInvocations = InvocationsFinder.findInvocations(allInvocations, target);
        if (allInvocations.size() != 1 && !findInvocations.isEmpty()) {
            throw Reporter.noMoreInteractionsWanted(InvocationsFinder.findFirstUnverified(allInvocations), allInvocations);
        } else if (allInvocations.size() != 1 || findInvocations.isEmpty()) {
            throw Reporter.wantedButNotInvoked(target);
        } else {
            InvocationMarker.markVerified(findInvocations.get(0), target);
        }
    }

    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }
}

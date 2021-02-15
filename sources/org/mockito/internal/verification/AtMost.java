package org.mockito.internal.verification;

import java.util.Iterator;
import java.util.List;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.invocation.InvocationMarker;
import org.mockito.internal.invocation.InvocationsFinder;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;
import org.mockito.verification.VerificationMode;

public class AtMost implements VerificationMode {
    private final int maxNumberOfInvocations;

    public AtMost(int i) {
        if (i >= 0) {
            this.maxNumberOfInvocations = i;
            return;
        }
        throw new MockitoException("Negative value is not allowed here");
    }

    public void verify(VerificationData verificationData) {
        List<Invocation> allInvocations = verificationData.getAllInvocations();
        MatchableInvocation target = verificationData.getTarget();
        List<Invocation> findInvocations = InvocationsFinder.findInvocations(allInvocations, target);
        int size = findInvocations.size();
        int i = this.maxNumberOfInvocations;
        if (size <= i) {
            removeAlreadyVerified(findInvocations);
            InvocationMarker.markVerified(findInvocations, target);
            return;
        }
        throw Reporter.wantedAtMostX(i, size);
    }

    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }

    private void removeAlreadyVerified(List<Invocation> list) {
        Iterator<Invocation> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().isVerified()) {
                it.remove();
            }
        }
    }
}

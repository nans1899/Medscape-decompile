package org.mockito.internal.verification;

import java.util.List;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.internal.verification.api.VerificationDataInOrder;
import org.mockito.internal.verification.api.VerificationInOrderMode;
import org.mockito.internal.verification.checkers.MissingInvocationChecker;
import org.mockito.internal.verification.checkers.NumberOfInvocationsChecker;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;
import org.mockito.verification.VerificationMode;

public class Calls implements VerificationMode, VerificationInOrderMode {
    final int wantedCount;

    public Calls(int i) {
        if (i > 0) {
            this.wantedCount = i;
            return;
        }
        throw new MockitoException("Negative and zero values are not allowed here");
    }

    public void verify(VerificationData verificationData) {
        throw new MockitoException("calls is only intended to work with InOrder");
    }

    public void verifyInOrder(VerificationDataInOrder verificationDataInOrder) {
        List<Invocation> allInvocations = verificationDataInOrder.getAllInvocations();
        MatchableInvocation wanted = verificationDataInOrder.getWanted();
        MissingInvocationChecker.checkMissingInvocation(allInvocations, wanted, verificationDataInOrder.getOrderingContext());
        NumberOfInvocationsChecker.checkNumberOfInvocationsNonGreedy(allInvocations, wanted, this.wantedCount, verificationDataInOrder.getOrderingContext());
    }

    public String toString() {
        return "Wanted invocations count (non-greedy): " + this.wantedCount;
    }

    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }
}

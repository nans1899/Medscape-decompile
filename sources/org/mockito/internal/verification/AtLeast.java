package org.mockito.internal.verification;

import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.internal.verification.api.VerificationDataInOrder;
import org.mockito.internal.verification.api.VerificationInOrderMode;
import org.mockito.internal.verification.checkers.AtLeastXNumberOfInvocationsChecker;
import org.mockito.internal.verification.checkers.MissingInvocationChecker;
import org.mockito.verification.VerificationMode;

public class AtLeast implements VerificationInOrderMode, VerificationMode {
    final int wantedCount;

    public AtLeast(int i) {
        if (i >= 0) {
            this.wantedCount = i;
            return;
        }
        throw new MockitoException("Negative value is not allowed here");
    }

    public void verify(VerificationData verificationData) {
        if (this.wantedCount == 1) {
            MissingInvocationChecker.checkMissingInvocation(verificationData.getAllInvocations(), verificationData.getTarget());
        }
        AtLeastXNumberOfInvocationsChecker.checkAtLeastNumberOfInvocations(verificationData.getAllInvocations(), verificationData.getTarget(), this.wantedCount);
    }

    public void verifyInOrder(VerificationDataInOrder verificationDataInOrder) {
        if (this.wantedCount == 1) {
            MissingInvocationChecker.checkMissingInvocation(verificationDataInOrder.getAllInvocations(), verificationDataInOrder.getWanted(), verificationDataInOrder.getOrderingContext());
        }
        AtLeastXNumberOfInvocationsChecker.checkAtLeastNumberOfInvocations(verificationDataInOrder.getAllInvocations(), verificationDataInOrder.getWanted(), this.wantedCount, verificationDataInOrder.getOrderingContext());
    }

    public String toString() {
        return "Wanted invocations count: at least " + this.wantedCount;
    }

    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }
}

package org.mockito.internal.verification;

import org.mockito.internal.InOrderImpl;
import org.mockito.internal.invocation.finder.VerifiableInvocationsFinder;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.internal.verification.api.VerificationDataInOrderImpl;
import org.mockito.internal.verification.api.VerificationInOrderMode;
import org.mockito.verification.VerificationMode;

public class InOrderWrapper implements VerificationMode {
    private final InOrderImpl inOrder;
    private final VerificationInOrderMode mode;

    public InOrderWrapper(VerificationInOrderMode verificationInOrderMode, InOrderImpl inOrderImpl) {
        this.mode = verificationInOrderMode;
        this.inOrder = inOrderImpl;
    }

    public void verify(VerificationData verificationData) {
        this.mode.verifyInOrder(new VerificationDataInOrderImpl(this.inOrder, VerifiableInvocationsFinder.find(this.inOrder.getMocksToBeVerifiedInOrder()), verificationData.getTarget()));
    }

    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }
}

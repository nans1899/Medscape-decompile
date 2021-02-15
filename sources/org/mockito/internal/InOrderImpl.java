package org.mockito.internal;

import java.util.LinkedList;
import java.util.List;
import org.mockito.InOrder;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.verification.InOrderContextImpl;
import org.mockito.internal.verification.InOrderWrapper;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.internal.verification.VerificationWrapper;
import org.mockito.internal.verification.VerificationWrapperInOrderWrapper;
import org.mockito.internal.verification.api.InOrderContext;
import org.mockito.internal.verification.api.VerificationInOrderMode;
import org.mockito.invocation.Invocation;
import org.mockito.verification.VerificationMode;

public class InOrderImpl implements InOrder, InOrderContext {
    private final InOrderContext inOrderContext = new InOrderContextImpl();
    private final MockitoCore mockitoCore = new MockitoCore();
    private final List<Object> mocksToBeVerifiedInOrder = new LinkedList();

    public List<Object> getMocksToBeVerifiedInOrder() {
        return this.mocksToBeVerifiedInOrder;
    }

    public InOrderImpl(List<?> list) {
        this.mocksToBeVerifiedInOrder.addAll(list);
    }

    public <T> T verify(T t) {
        return verify(t, VerificationModeFactory.times(1));
    }

    public <T> T verify(T t, VerificationMode verificationMode) {
        if (!this.mocksToBeVerifiedInOrder.contains(t)) {
            throw Reporter.inOrderRequiresFamiliarMock();
        } else if (verificationMode instanceof VerificationWrapper) {
            return this.mockitoCore.verify(t, new VerificationWrapperInOrderWrapper((VerificationWrapper) verificationMode, this));
        } else {
            if (verificationMode instanceof VerificationInOrderMode) {
                return this.mockitoCore.verify(t, new InOrderWrapper((VerificationInOrderMode) verificationMode, this));
            }
            throw new MockitoException(verificationMode.getClass().getSimpleName() + " is not implemented to work with InOrder");
        }
    }

    public boolean isVerified(Invocation invocation) {
        return this.inOrderContext.isVerified(invocation);
    }

    public void markVerified(Invocation invocation) {
        this.inOrderContext.markVerified(invocation);
    }

    public void verifyNoMoreInteractions() {
        this.mockitoCore.verifyNoMoreInteractionsInOrder(this.mocksToBeVerifiedInOrder, this);
    }
}

package org.mockito.internal.verification;

import java.util.Set;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.listeners.VerificationListener;
import org.mockito.verification.VerificationEvent;
import org.mockito.verification.VerificationMode;

public class MockAwareVerificationMode implements VerificationMode {
    private final Set<VerificationListener> listeners;
    private final Object mock;
    private final VerificationMode mode;

    public MockAwareVerificationMode(Object obj, VerificationMode verificationMode, Set<VerificationListener> set) {
        this.mock = obj;
        this.mode = verificationMode;
        this.listeners = set;
    }

    public void verify(VerificationData verificationData) {
        try {
            this.mode.verify(verificationData);
            notifyListeners(new VerificationEventImpl(this.mock, this.mode, verificationData, (Throwable) null));
        } catch (RuntimeException e) {
            notifyListeners(new VerificationEventImpl(this.mock, this.mode, verificationData, e));
            throw e;
        } catch (Error e2) {
            notifyListeners(new VerificationEventImpl(this.mock, this.mode, verificationData, e2));
            throw e2;
        }
    }

    private void notifyListeners(VerificationEvent verificationEvent) {
        for (VerificationListener onVerification : this.listeners) {
            onVerification.onVerification(verificationEvent);
        }
    }

    public Object getMock() {
        return this.mock;
    }

    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }
}

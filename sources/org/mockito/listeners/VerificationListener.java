package org.mockito.listeners;

import org.mockito.Incubating;
import org.mockito.verification.VerificationEvent;

@Incubating
public interface VerificationListener extends MockitoListener {
    void onVerification(VerificationEvent verificationEvent);
}

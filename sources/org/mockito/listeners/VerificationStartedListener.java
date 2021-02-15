package org.mockito.listeners;

import org.mockito.Incubating;

@Incubating
public interface VerificationStartedListener {
    @Incubating
    void onVerificationStarted(VerificationStartedEvent verificationStartedEvent);
}

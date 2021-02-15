package org.mockito.verification;

public interface VerificationAfterDelay extends VerificationMode {
    VerificationMode atLeast(int i);

    VerificationMode atLeastOnce();

    VerificationMode atMost(int i);

    VerificationMode never();

    VerificationMode only();

    VerificationMode times(int i);
}

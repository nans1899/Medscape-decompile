package org.mockito.verification;

public interface VerificationWithTimeout extends VerificationMode {
    VerificationMode atLeast(int i);

    VerificationMode atLeastOnce();

    VerificationMode only();

    VerificationMode times(int i);
}

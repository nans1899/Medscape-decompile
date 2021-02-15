package org.mockito.verification;

public interface VerificationStrategy {
    VerificationMode maybeVerifyLazily(VerificationMode verificationMode);
}

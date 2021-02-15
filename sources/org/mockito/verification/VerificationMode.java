package org.mockito.verification;

import org.mockito.internal.verification.api.VerificationData;

public interface VerificationMode {
    VerificationMode description(String str);

    void verify(VerificationData verificationData);
}

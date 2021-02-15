package org.mockito.internal.verification;

import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.verification.VerificationMode;

public class Description implements VerificationMode {
    private final String description;
    private final VerificationMode verification;

    public Description(VerificationMode verificationMode, String str) {
        this.verification = verificationMode;
        this.description = str;
    }

    public void verify(VerificationData verificationData) {
        try {
            this.verification.verify(verificationData);
        } catch (MockitoAssertionError e) {
            throw new MockitoAssertionError(e, this.description);
        }
    }

    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }
}

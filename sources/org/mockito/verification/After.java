package org.mockito.verification;

import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.internal.verification.VerificationOverTimeImpl;
import org.mockito.internal.verification.VerificationWrapper;

public class After extends VerificationWrapper<VerificationOverTimeImpl> implements VerificationAfterDelay {
    public After(long j, VerificationMode verificationMode) {
        this(10, j, verificationMode);
    }

    After(long j, long j2, VerificationMode verificationMode) {
        this(new VerificationOverTimeImpl(j, j2, verificationMode, false));
    }

    After(VerificationOverTimeImpl verificationOverTimeImpl) {
        super(verificationOverTimeImpl);
    }

    /* access modifiers changed from: protected */
    public VerificationMode copySelfWithNewVerificationMode(VerificationMode verificationMode) {
        return new After(((VerificationOverTimeImpl) this.wrappedVerification).copyWithVerificationMode(verificationMode));
    }

    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }
}

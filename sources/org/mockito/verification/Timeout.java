package org.mockito.verification;

import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.Timer;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.internal.verification.VerificationOverTimeImpl;
import org.mockito.internal.verification.VerificationWrapper;

public class Timeout extends VerificationWrapper<VerificationOverTimeImpl> implements VerificationWithTimeout {
    public Timeout(long j, VerificationMode verificationMode) {
        this(10, j, verificationMode);
    }

    Timeout(long j, long j2, VerificationMode verificationMode) {
        this(new VerificationOverTimeImpl(j, j2, verificationMode, true));
    }

    Timeout(long j, VerificationMode verificationMode, Timer timer) {
        this(new VerificationOverTimeImpl(j, verificationMode, true, timer));
    }

    Timeout(VerificationOverTimeImpl verificationOverTimeImpl) {
        super(verificationOverTimeImpl);
    }

    /* access modifiers changed from: protected */
    public VerificationMode copySelfWithNewVerificationMode(VerificationMode verificationMode) {
        return new Timeout(((VerificationOverTimeImpl) this.wrappedVerification).copyWithVerificationMode(verificationMode));
    }

    public VerificationMode atMost(int i) {
        throw Reporter.atMostAndNeverShouldNotBeUsedWithTimeout();
    }

    public VerificationMode never() {
        throw Reporter.atMostAndNeverShouldNotBeUsedWithTimeout();
    }

    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }
}

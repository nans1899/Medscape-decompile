package org.mockito.internal.verification;

import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.internal.util.Timer;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.verification.VerificationMode;

public class VerificationOverTimeImpl implements VerificationMode {
    private final VerificationMode delegate;
    private final long pollingPeriodMillis;
    private final boolean returnOnSuccess;
    private final Timer timer;

    public VerificationOverTimeImpl(long j, long j2, VerificationMode verificationMode, boolean z) {
        this(j, verificationMode, z, new Timer(j2));
    }

    public VerificationOverTimeImpl(long j, VerificationMode verificationMode, boolean z, Timer timer2) {
        this.pollingPeriodMillis = j;
        this.delegate = verificationMode;
        this.returnOnSuccess = z;
        this.timer = timer2;
    }

    public void verify(VerificationData verificationData) {
        this.timer.start();
        do {
            AssertionError assertionError = null;
            while (this.timer.isCounting()) {
                try {
                    this.delegate.verify(verificationData);
                } catch (MockitoAssertionError e) {
                    assertionError = handleVerifyException(e);
                } catch (AssertionError e2) {
                    assertionError = handleVerifyException(e2);
                }
            }
            if (assertionError != null) {
                throw assertionError;
            }
            return;
        } while (!this.returnOnSuccess);
    }

    private AssertionError handleVerifyException(AssertionError assertionError) {
        if (canRecoverFromFailure(this.delegate)) {
            sleep(this.pollingPeriodMillis);
            return assertionError;
        }
        throw assertionError;
    }

    /* access modifiers changed from: protected */
    public boolean canRecoverFromFailure(VerificationMode verificationMode) {
        return !(verificationMode instanceof AtMost) && !(verificationMode instanceof NoMoreInteractions);
    }

    public VerificationOverTimeImpl copyWithVerificationMode(VerificationMode verificationMode) {
        return new VerificationOverTimeImpl(this.pollingPeriodMillis, this.timer.duration(), verificationMode, this.returnOnSuccess);
    }

    private void sleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread sleep has been interrupted", e);
        }
    }

    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }

    public boolean isReturnOnSuccess() {
        return this.returnOnSuccess;
    }

    public long getPollingPeriodMillis() {
        return this.pollingPeriodMillis;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public VerificationMode getDelegate() {
        return this.delegate;
    }
}

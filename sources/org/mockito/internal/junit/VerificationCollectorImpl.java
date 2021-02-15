package org.mockito.internal.junit;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.internal.progress.MockingProgressImpl;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.junit.VerificationCollector;
import org.mockito.verification.VerificationMode;
import org.mockito.verification.VerificationStrategy;

public class VerificationCollectorImpl implements VerificationCollector {
    private StringBuilder builder;
    private int numberOfFailures;

    public VerificationCollectorImpl() {
        resetBuilder();
    }

    public Statement apply(final Statement statement, Description description) {
        return new Statement() {
            public void evaluate() throws Throwable {
                try {
                    VerificationCollectorImpl.this.assertLazily();
                    statement.evaluate();
                    VerificationCollectorImpl.this.collectAndReport();
                } finally {
                    ThreadSafeMockingProgress.mockingProgress().setVerificationStrategy(MockingProgressImpl.getDefaultVerificationStrategy());
                }
            }
        };
    }

    public void collectAndReport() throws MockitoAssertionError {
        ThreadSafeMockingProgress.mockingProgress().setVerificationStrategy(MockingProgressImpl.getDefaultVerificationStrategy());
        if (this.numberOfFailures > 0) {
            String sb = this.builder.toString();
            resetBuilder();
            throw new MockitoAssertionError(sb);
        }
    }

    public VerificationCollector assertLazily() {
        ThreadSafeMockingProgress.mockingProgress().setVerificationStrategy(new VerificationStrategy() {
            public VerificationMode maybeVerifyLazily(VerificationMode verificationMode) {
                return new VerificationWrapper(verificationMode);
            }
        });
        return this;
    }

    private void resetBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append("There were multiple verification failures:");
        this.builder = sb;
        this.numberOfFailures = 0;
    }

    /* access modifiers changed from: private */
    public void append(String str) {
        this.numberOfFailures++;
        StringBuilder sb = this.builder;
        sb.append(10);
        sb.append(this.numberOfFailures);
        sb.append(". ");
        sb.append(str.trim());
        sb.append(10);
    }

    private class VerificationWrapper implements VerificationMode {
        private final VerificationMode delegate;

        private VerificationWrapper(VerificationMode verificationMode) {
            this.delegate = verificationMode;
        }

        public void verify(VerificationData verificationData) {
            try {
                this.delegate.verify(verificationData);
            } catch (AssertionError e) {
                VerificationCollectorImpl.this.append(e.getMessage());
            }
        }

        public VerificationMode description(String str) {
            throw new IllegalStateException("Should not fail in this mode");
        }
    }
}

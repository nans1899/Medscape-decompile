package org.mockito.internal.runners.util;

import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class FailureDetector extends RunListener {
    private boolean failed;

    public void testFailure(Failure failure) throws Exception {
        FailureDetector.super.testFailure(failure);
        this.failed = true;
    }

    public boolean isSuccessful() {
        return !this.failed;
    }
}

package org.mockito.internal.progress;

public class ThreadSafeMockingProgress {
    private static final ThreadLocal<MockingProgress> MOCKING_PROGRESS_PROVIDER = new ThreadLocal<MockingProgress>() {
        /* access modifiers changed from: protected */
        public MockingProgress initialValue() {
            return new MockingProgressImpl();
        }
    };

    private ThreadSafeMockingProgress() {
    }

    public static final MockingProgress mockingProgress() {
        return MOCKING_PROGRESS_PROVIDER.get();
    }
}

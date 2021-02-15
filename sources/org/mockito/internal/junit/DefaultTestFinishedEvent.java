package org.mockito.internal.junit;

public class DefaultTestFinishedEvent implements TestFinishedEvent {
    private final Object testClassInstance;
    private final Throwable testFailure;
    private final String testMethodName;

    public DefaultTestFinishedEvent(Object obj, String str, Throwable th) {
        this.testClassInstance = obj;
        this.testMethodName = str;
        this.testFailure = th;
    }

    public Throwable getFailure() {
        return this.testFailure;
    }

    public String getTestName() {
        return this.testClassInstance.getClass().getSimpleName() + "." + this.testMethodName;
    }
}

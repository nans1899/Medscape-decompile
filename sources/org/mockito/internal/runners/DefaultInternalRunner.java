package org.mockito.internal.runners;

import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.junit.DefaultTestFinishedEvent;
import org.mockito.internal.junit.MockitoTestListener;
import org.mockito.internal.util.Supplier;

public class DefaultInternalRunner implements InternalRunner {
    private final BlockJUnit4ClassRunner runner;

    public DefaultInternalRunner(Class<?> cls, final Supplier<MockitoTestListener> supplier) throws InitializationError {
        this.runner = new BlockJUnit4ClassRunner(cls) {
            /* access modifiers changed from: private */
            public MockitoTestListener mockitoTestListener;
            public Object target;

            /* access modifiers changed from: protected */
            public Statement withBefores(FrameworkMethod frameworkMethod, Object obj, Statement statement) {
                this.target = obj;
                this.mockitoTestListener = (MockitoTestListener) supplier.get();
                Mockito.framework().addListener(this.mockitoTestListener);
                MockitoAnnotations.initMocks(obj);
                return DefaultInternalRunner.super.withBefores(frameworkMethod, obj, statement);
            }

            public void run(final RunNotifier runNotifier) {
                runNotifier.addListener(new RunListener() {
                    Throwable failure;
                    private boolean started;

                    public void testStarted(Description description) throws Exception {
                        this.started = true;
                    }

                    public void testFailure(Failure failure2) throws Exception {
                        this.failure = failure2.getException();
                        if (!this.started && AnonymousClass1.this.mockitoTestListener != null) {
                            Mockito.framework().removeListener(AnonymousClass1.this.mockitoTestListener);
                        }
                    }

                    public void testFinished(Description description) throws Exception {
                        try {
                            if (AnonymousClass1.this.mockitoTestListener != null) {
                                Mockito.framework().removeListener(AnonymousClass1.this.mockitoTestListener);
                                AnonymousClass1.this.mockitoTestListener.testFinished(new DefaultTestFinishedEvent(AnonymousClass1.this.target, description.getMethodName(), this.failure));
                            }
                            Mockito.validateMockitoUsage();
                        } catch (Throwable th) {
                            runNotifier.fireTestFailure(new Failure(description, th));
                        }
                    }
                });
                DefaultInternalRunner.super.run(runNotifier);
            }
        };
    }

    public void run(RunNotifier runNotifier) {
        this.runner.run(runNotifier);
    }

    public Description getDescription() {
        return this.runner.getDescription();
    }

    public void filter(Filter filter) throws NoTestsRemainException {
        this.runner.filter(filter);
    }
}

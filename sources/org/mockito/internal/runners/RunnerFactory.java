package org.mockito.internal.runners;

import java.lang.reflect.InvocationTargetException;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.configuration.plugins.Plugins;
import org.mockito.internal.junit.MismatchReportingTestListener;
import org.mockito.internal.junit.MockitoTestListener;
import org.mockito.internal.junit.NoOpTestListener;
import org.mockito.internal.junit.StrictStubsRunnerTestListener;
import org.mockito.internal.runners.util.RunnerProvider;
import org.mockito.internal.runners.util.TestMethodsFinder;
import org.mockito.internal.util.Supplier;

public class RunnerFactory {
    public InternalRunner create(Class<?> cls) throws InvocationTargetException {
        return create(cls, new Supplier<MockitoTestListener>() {
            public MockitoTestListener get() {
                return new NoOpTestListener();
            }
        });
    }

    public InternalRunner createStrict(Class<?> cls) throws InvocationTargetException {
        return create(cls, new Supplier<MockitoTestListener>() {
            public MockitoTestListener get() {
                return new MismatchReportingTestListener(Plugins.getMockitoLogger());
            }
        });
    }

    public InternalRunner createStrictStubs(Class<?> cls) throws InvocationTargetException {
        return create(cls, new Supplier<MockitoTestListener>() {
            public MockitoTestListener get() {
                return new StrictStubsRunnerTestListener();
            }
        });
    }

    public InternalRunner create(Class<?> cls, Supplier<MockitoTestListener> supplier) throws InvocationTargetException {
        try {
            return new RunnerProvider().newInstance("org.mockito.internal.runners.DefaultInternalRunner", cls, supplier);
        } catch (InvocationTargetException e) {
            if (!TestMethodsFinder.hasTestMethods(cls)) {
                throw new MockitoException("\n\nNo tests found in " + cls.getSimpleName() + "\nIs the method annotated with @Test?\nIs the method public?\n", e);
            }
            throw e;
        } catch (Throwable th) {
            throw new MockitoException("\n\nMockitoRunner can only be used with JUnit 4.5 or higher.\nYou can upgrade your JUnit version or write your own Runner (please consider contributing your runner to the Mockito community).\nBear in mind that you can still enjoy all features of the framework without using runners (they are completely optional).\nIf you get this error despite using JUnit 4.5 or higher then please report this error to the mockito mailing list.\n", th);
        }
    }
}

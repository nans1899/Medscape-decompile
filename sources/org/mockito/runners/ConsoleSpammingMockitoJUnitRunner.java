package org.mockito.runners;

import java.lang.reflect.InvocationTargetException;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.Filterable;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.mockito.internal.configuration.plugins.Plugins;
import org.mockito.internal.debugging.WarningsCollector;
import org.mockito.internal.runners.InternalRunner;
import org.mockito.internal.runners.RunnerFactory;
import org.mockito.plugins.MockitoLogger;

@Deprecated
public class ConsoleSpammingMockitoJUnitRunner extends Runner implements Filterable {
    /* access modifiers changed from: private */
    public final MockitoLogger logger;
    private final InternalRunner runner;

    public ConsoleSpammingMockitoJUnitRunner(Class<?> cls) throws InvocationTargetException {
        this(Plugins.getMockitoLogger(), new RunnerFactory().create(cls));
    }

    ConsoleSpammingMockitoJUnitRunner(MockitoLogger mockitoLogger, InternalRunner internalRunner) {
        this.runner = internalRunner;
        this.logger = mockitoLogger;
    }

    public void run(RunNotifier runNotifier) {
        runNotifier.addListener(new RunListener() {
            WarningsCollector warningsCollector;

            public void testStarted(Description description) throws Exception {
                this.warningsCollector = new WarningsCollector();
            }

            public void testFailure(Failure failure) throws Exception {
                ConsoleSpammingMockitoJUnitRunner.this.logger.log(this.warningsCollector.getWarnings());
            }
        });
        this.runner.run(runNotifier);
    }

    public Description getDescription() {
        return this.runner.getDescription();
    }

    public void filter(Filter filter) throws NoTestsRemainException {
        this.runner.filter(filter);
    }
}

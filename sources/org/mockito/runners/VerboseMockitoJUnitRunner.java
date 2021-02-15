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
import org.mockito.internal.debugging.WarningsCollector;
import org.mockito.internal.junit.util.JUnitFailureHacker;
import org.mockito.internal.runners.InternalRunner;
import org.mockito.internal.runners.RunnerFactory;

@Deprecated
public class VerboseMockitoJUnitRunner extends Runner implements Filterable {
    private final InternalRunner runner;

    public VerboseMockitoJUnitRunner(Class<?> cls) throws InvocationTargetException {
        this(new RunnerFactory().create(cls));
    }

    VerboseMockitoJUnitRunner(InternalRunner internalRunner) {
        this.runner = internalRunner;
    }

    public void run(RunNotifier runNotifier) {
        runNotifier.addFirstListener(new RunListener() {
            WarningsCollector warningsCollector;

            public void testStarted(Description description) throws Exception {
                this.warningsCollector = new WarningsCollector();
            }

            public void testFailure(Failure failure) throws Exception {
                new JUnitFailureHacker().appendWarnings(failure, this.warningsCollector.getWarnings());
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

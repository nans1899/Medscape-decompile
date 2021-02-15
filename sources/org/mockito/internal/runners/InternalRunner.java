package org.mockito.internal.runners;

import org.junit.runner.Description;
import org.junit.runner.manipulation.Filterable;
import org.junit.runner.notification.RunNotifier;

public interface InternalRunner extends Filterable {
    Description getDescription();

    void run(RunNotifier runNotifier);
}

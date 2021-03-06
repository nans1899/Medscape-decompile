package org.mockito.internal.junit;

import org.mockito.listeners.MockCreationListener;

public interface MockitoTestListener extends MockCreationListener {
    void testFinished(TestFinishedEvent testFinishedEvent);
}

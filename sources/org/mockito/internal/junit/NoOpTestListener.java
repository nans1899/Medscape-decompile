package org.mockito.internal.junit;

import org.mockito.mock.MockCreationSettings;

public class NoOpTestListener implements MockitoTestListener {
    public void onMockCreated(Object obj, MockCreationSettings mockCreationSettings) {
    }

    public void testFinished(TestFinishedEvent testFinishedEvent) {
    }
}

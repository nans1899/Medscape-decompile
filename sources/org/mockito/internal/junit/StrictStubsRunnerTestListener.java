package org.mockito.internal.junit;

import org.mockito.mock.MockCreationSettings;
import org.mockito.quality.Strictness;

public class StrictStubsRunnerTestListener implements MockitoTestListener {
    private final DefaultStubbingLookupListener stubbingLookupListener = new DefaultStubbingLookupListener(Strictness.STRICT_STUBS);

    public void testFinished(TestFinishedEvent testFinishedEvent) {
    }

    public void onMockCreated(Object obj, MockCreationSettings mockCreationSettings) {
        mockCreationSettings.getStubbingLookupListeners().add(this.stubbingLookupListener);
    }
}

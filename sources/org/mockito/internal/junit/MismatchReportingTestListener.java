package org.mockito.internal.junit;

import java.util.LinkedList;
import java.util.List;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.MockitoLogger;

public class MismatchReportingTestListener implements MockitoTestListener {
    private final MockitoLogger logger;
    private List<Object> mocks = new LinkedList();

    public MismatchReportingTestListener(MockitoLogger mockitoLogger) {
        this.logger = mockitoLogger;
    }

    public void testFinished(TestFinishedEvent testFinishedEvent) {
        List<Object> list = this.mocks;
        this.mocks = new LinkedList();
        if (testFinishedEvent.getFailure() != null) {
            new ArgMismatchFinder().getStubbingArgMismatches(list).format(testFinishedEvent.getTestName(), this.logger);
        }
    }

    public void onMockCreated(Object obj, MockCreationSettings mockCreationSettings) {
        this.mocks.add(obj);
    }
}

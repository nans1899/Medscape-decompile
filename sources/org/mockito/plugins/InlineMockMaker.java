package org.mockito.plugins;

import org.mockito.Incubating;

@Incubating
public interface InlineMockMaker extends MockMaker {
    @Incubating
    void clearAllMocks();

    @Incubating
    void clearMock(Object obj);
}

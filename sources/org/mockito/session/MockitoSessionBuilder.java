package org.mockito.session;

import org.mockito.Incubating;
import org.mockito.MockitoSession;
import org.mockito.exceptions.misusing.UnfinishedMockingSessionException;
import org.mockito.quality.Strictness;

@Incubating
public interface MockitoSessionBuilder {
    @Incubating
    MockitoSessionBuilder initMocks(Object obj);

    @Incubating
    MockitoSessionBuilder initMocks(Object... objArr);

    @Incubating
    MockitoSessionBuilder logger(MockitoSessionLogger mockitoSessionLogger);

    @Incubating
    MockitoSessionBuilder name(String str);

    @Incubating
    MockitoSession startMocking() throws UnfinishedMockingSessionException;

    @Incubating
    MockitoSessionBuilder strictness(Strictness strictness);
}

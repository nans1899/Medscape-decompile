package org.mockito.listeners;

import org.mockito.Incubating;

@Incubating
public interface VerificationStartedEvent {
    @Incubating
    Object getMock();

    @Incubating
    void setMock(Object obj);
}

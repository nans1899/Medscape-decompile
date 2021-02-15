package org.mockito;

import org.mockito.quality.Strictness;

@Incubating
public interface MockitoSession {
    @Incubating
    void finishMocking();

    @Incubating
    void finishMocking(Throwable th);

    @Incubating
    void setStrictness(Strictness strictness);
}

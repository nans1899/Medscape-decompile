package org.mockito.session;

import org.mockito.Incubating;

@Incubating
public interface MockitoSessionLogger {
    @Incubating
    void log(String str);
}

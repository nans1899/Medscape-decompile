package org.mockito.internal.verification;

import java.util.List;
import org.mockito.invocation.Invocation;

public interface RegisteredInvocations {
    void add(Invocation invocation);

    void clear();

    List<Invocation> getAll();

    boolean isEmpty();

    void removeLast();
}

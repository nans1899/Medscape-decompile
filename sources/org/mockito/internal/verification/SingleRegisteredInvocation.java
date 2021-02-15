package org.mockito.internal.verification;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.mockito.invocation.Invocation;

public class SingleRegisteredInvocation implements RegisteredInvocations, Serializable {
    private Invocation invocation;

    public void add(Invocation invocation2) {
        this.invocation = invocation2;
    }

    public void removeLast() {
        this.invocation = null;
    }

    public List<Invocation> getAll() {
        return Collections.emptyList();
    }

    public void clear() {
        this.invocation = null;
    }

    public boolean isEmpty() {
        return this.invocation == null;
    }
}

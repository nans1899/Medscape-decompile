package org.mockito.internal.invocation.mockref;

import java.io.ObjectStreamException;

public class MockStrongReference<T> implements MockReference<T> {
    private final boolean deserializeAsWeakRef;
    private final T ref;

    public MockStrongReference(T t, boolean z) {
        this.ref = t;
        this.deserializeAsWeakRef = z;
    }

    public T get() {
        return this.ref;
    }

    private Object readResolve() throws ObjectStreamException {
        return this.deserializeAsWeakRef ? new MockWeakReference(this.ref) : this;
    }
}

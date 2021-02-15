package org.mockito.internal.invocation.mockref;

import java.io.Serializable;

public interface MockReference<T> extends Serializable {
    T get();
}

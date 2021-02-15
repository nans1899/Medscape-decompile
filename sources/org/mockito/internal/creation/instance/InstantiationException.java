package org.mockito.internal.creation.instance;

import org.mockito.exceptions.base.MockitoException;

@Deprecated
public class InstantiationException extends MockitoException {
    public InstantiationException(String str, Throwable th) {
        super(str, th);
    }
}

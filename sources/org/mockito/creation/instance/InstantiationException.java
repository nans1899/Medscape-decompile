package org.mockito.creation.instance;

import org.mockito.exceptions.base.MockitoException;

public class InstantiationException extends MockitoException {
    public InstantiationException(String str, Throwable th) {
        super(str, th);
    }
}

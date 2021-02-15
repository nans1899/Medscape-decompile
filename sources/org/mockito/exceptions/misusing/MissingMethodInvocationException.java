package org.mockito.exceptions.misusing;

import org.mockito.exceptions.base.MockitoException;

public class MissingMethodInvocationException extends MockitoException {
    private static final long serialVersionUID = 1;

    public MissingMethodInvocationException(String str) {
        super(str);
    }
}

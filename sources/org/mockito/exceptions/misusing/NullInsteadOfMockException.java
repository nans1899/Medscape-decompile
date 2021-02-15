package org.mockito.exceptions.misusing;

import org.mockito.exceptions.base.MockitoException;

public class NullInsteadOfMockException extends MockitoException {
    private static final long serialVersionUID = 1;

    public NullInsteadOfMockException(String str) {
        super(str);
    }
}

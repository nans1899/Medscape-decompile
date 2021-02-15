package org.mockito.exceptions.misusing;

import org.mockito.exceptions.base.MockitoException;

public class WrongTypeOfReturnValue extends MockitoException {
    private static final long serialVersionUID = 1;

    public WrongTypeOfReturnValue(String str) {
        super(str);
    }
}

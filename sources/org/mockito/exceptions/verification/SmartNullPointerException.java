package org.mockito.exceptions.verification;

import org.mockito.exceptions.base.MockitoException;

public class SmartNullPointerException extends MockitoException {
    private static final long serialVersionUID = 1;

    public SmartNullPointerException(String str) {
        super(str);
    }
}

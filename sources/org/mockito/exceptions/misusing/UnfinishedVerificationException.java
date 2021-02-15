package org.mockito.exceptions.misusing;

import org.mockito.exceptions.base.MockitoException;

public class UnfinishedVerificationException extends MockitoException {
    private static final long serialVersionUID = 1;

    public UnfinishedVerificationException(String str) {
        super(str);
    }
}

package org.mockito.exceptions.misusing;

import org.mockito.exceptions.base.MockitoException;

public class UnfinishedStubbingException extends MockitoException {
    private static final long serialVersionUID = 1;

    public UnfinishedStubbingException(String str) {
        super(str);
    }
}

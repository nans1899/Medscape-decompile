package org.mockito.exceptions.misusing;

import org.mockito.exceptions.base.MockitoException;

public class UnnecessaryStubbingException extends MockitoException {
    public UnnecessaryStubbingException(String str) {
        super(str);
    }
}

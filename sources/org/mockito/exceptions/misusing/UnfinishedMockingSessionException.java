package org.mockito.exceptions.misusing;

import org.mockito.exceptions.base.MockitoException;

public class UnfinishedMockingSessionException extends MockitoException {
    public UnfinishedMockingSessionException(String str) {
        super(str);
    }
}

package org.mockito.exceptions.misusing;

import org.mockito.exceptions.base.MockitoException;

public class CannotVerifyStubOnlyMock extends MockitoException {
    public CannotVerifyStubOnlyMock(String str) {
        super(str);
    }
}

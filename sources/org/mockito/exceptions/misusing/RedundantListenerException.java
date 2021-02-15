package org.mockito.exceptions.misusing;

import org.mockito.exceptions.base.MockitoException;

public class RedundantListenerException extends MockitoException {
    public RedundantListenerException(String str) {
        super(str);
    }
}

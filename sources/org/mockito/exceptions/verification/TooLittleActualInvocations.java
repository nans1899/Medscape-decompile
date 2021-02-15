package org.mockito.exceptions.verification;

import org.mockito.exceptions.base.MockitoAssertionError;

public class TooLittleActualInvocations extends MockitoAssertionError {
    private static final long serialVersionUID = 1;

    public TooLittleActualInvocations(String str) {
        super(str);
    }
}

package org.mockito.exceptions.verification;

import org.mockito.exceptions.base.MockitoAssertionError;

public class MoreThanAllowedActualInvocations extends MockitoAssertionError {
    public MoreThanAllowedActualInvocations(String str) {
        super(str);
    }
}

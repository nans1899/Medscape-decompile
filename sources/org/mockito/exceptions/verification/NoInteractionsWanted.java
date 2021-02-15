package org.mockito.exceptions.verification;

import org.mockito.exceptions.base.MockitoAssertionError;

public class NoInteractionsWanted extends MockitoAssertionError {
    private static final long serialVersionUID = 1;

    public NoInteractionsWanted(String str) {
        super(str);
    }
}

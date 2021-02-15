package org.mockito.exceptions.misusing;

import org.mockito.exceptions.base.MockitoException;

public class FriendlyReminderException extends MockitoException {
    private static final long serialVersionUID = 1;

    public FriendlyReminderException(String str) {
        super(str);
    }
}

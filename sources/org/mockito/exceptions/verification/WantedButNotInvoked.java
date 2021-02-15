package org.mockito.exceptions.verification;

import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.internal.util.StringUtil;

public class WantedButNotInvoked extends MockitoAssertionError {
    private static final long serialVersionUID = 1;

    public WantedButNotInvoked(String str) {
        super(str);
    }

    public String toString() {
        return StringUtil.removeFirstLine(super.toString());
    }
}

package org.mockito.exceptions.verification;

import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.internal.util.StringUtil;

public class ArgumentsAreDifferent extends MockitoAssertionError {
    private static final long serialVersionUID = 1;

    public ArgumentsAreDifferent(String str) {
        super(str);
    }

    public String toString() {
        return StringUtil.removeFirstLine(super.toString());
    }
}

package org.mockito.exceptions.misusing;

import org.mockito.exceptions.base.MockitoException;

public class MockitoConfigurationException extends MockitoException {
    private static final long serialVersionUID = 1;

    public MockitoConfigurationException(String str) {
        super(str);
    }

    public MockitoConfigurationException(String str, Exception exc) {
        super(str, exc);
    }
}

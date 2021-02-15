package org.mockito.internal.stubbing.defaultanswers;

import java.io.Serializable;
import org.mockito.internal.configuration.GlobalConfiguration;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class GloballyConfiguredAnswer implements Answer<Object>, Serializable {
    private static final long serialVersionUID = 3585893470101750917L;

    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        return new GlobalConfiguration().getDefaultAnswer().answer(invocationOnMock);
    }
}

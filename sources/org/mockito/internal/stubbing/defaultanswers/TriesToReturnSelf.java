package org.mockito.internal.stubbing.defaultanswers;

import java.io.Serializable;
import org.mockito.internal.util.MockUtil;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class TriesToReturnSelf implements Answer<Object>, Serializable {
    private final ReturnsEmptyValues defaultReturn = new ReturnsEmptyValues();

    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        Class<?> returnType = invocationOnMock.getMethod().getReturnType();
        if (returnType.isAssignableFrom(MockUtil.getMockHandler(invocationOnMock.getMock()).getMockSettings().getTypeToMock())) {
            return invocationOnMock.getMock();
        }
        return this.defaultReturn.returnValueFor(returnType);
    }
}

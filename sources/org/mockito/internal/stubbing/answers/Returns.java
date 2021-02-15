package org.mockito.internal.stubbing.answers;

import java.io.Serializable;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.ValidableAnswer;

public class Returns implements Answer<Object>, ValidableAnswer, Serializable {
    private static final long serialVersionUID = -6245608253574215396L;
    private final Object value;

    public Returns(Object obj) {
        this.value = obj;
    }

    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        return this.value;
    }

    public void validateFor(InvocationOnMock invocationOnMock) {
        InvocationInfo invocationInfo = new InvocationInfo(invocationOnMock);
        if (invocationInfo.isVoid()) {
            throw Reporter.cannotStubVoidMethodWithAReturnValue(invocationInfo.getMethodName());
        } else if (returnsNull() && invocationInfo.returnsPrimitive()) {
            throw Reporter.wrongTypeOfReturnValue(invocationInfo.printMethodReturnType(), "null", invocationInfo.getMethodName());
        } else if (!returnsNull() && !invocationInfo.isValidReturnType(returnType())) {
            throw Reporter.wrongTypeOfReturnValue(invocationInfo.printMethodReturnType(), printReturnType(), invocationInfo.getMethodName());
        }
    }

    private String printReturnType() {
        return this.value.getClass().getSimpleName();
    }

    private Class<?> returnType() {
        return this.value.getClass();
    }

    private boolean returnsNull() {
        return this.value == null;
    }

    public String toString() {
        return "Returns: " + this.value;
    }
}

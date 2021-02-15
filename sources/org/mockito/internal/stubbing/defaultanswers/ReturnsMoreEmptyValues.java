package org.mockito.internal.stubbing.defaultanswers;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ReturnsMoreEmptyValues implements Answer<Object>, Serializable {
    private static final long serialVersionUID = -2816745041482698471L;
    private final Answer<Object> delegate = new ReturnsEmptyValues();

    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        Object answer = this.delegate.answer(invocationOnMock);
        if (answer != null) {
            return answer;
        }
        return returnValueFor(invocationOnMock.getMethod().getReturnType());
    }

    /* access modifiers changed from: package-private */
    public Object returnValueFor(Class<?> cls) {
        if (cls == String.class) {
            return "";
        }
        if (cls.isArray()) {
            return Array.newInstance(cls.getComponentType(), 0);
        }
        return null;
    }
}

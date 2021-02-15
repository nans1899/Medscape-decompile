package org.mockito.internal.stubbing.answers;

import org.mockito.internal.configuration.plugins.Plugins;
import org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues;
import org.mockito.internal.util.reflection.LenientCopyTool;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.mock.MockCreationSettings;
import org.mockito.stubbing.Answer;

public class ClonesArguments implements Answer<Object> {
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        Object[] arguments = invocationOnMock.getArguments();
        for (int i = 0; i < arguments.length; i++) {
            Object obj = arguments[i];
            Object newInstance = Plugins.getInstantiatorProvider().getInstantiator((MockCreationSettings<?>) null).newInstance(obj.getClass());
            new LenientCopyTool().copyToRealObject(obj, newInstance);
            arguments[i] = newInstance;
        }
        return new ReturnsEmptyValues().answer(invocationOnMock);
    }
}

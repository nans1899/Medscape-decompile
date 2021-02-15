package org.mockito.internal.stubbing.defaultanswers;

import java.io.Serializable;
import org.mockito.Mockito;
import org.mockito.internal.creation.MockSettingsImpl;
import org.mockito.internal.stubbing.defaultanswers.RetrieveGenericsForDefaultAnswers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ReturnsMocks implements Answer<Object>, Serializable {
    private static final long serialVersionUID = -6755257986994634579L;
    private final Answer<Object> delegate = new ReturnsMoreEmptyValues();

    public Object answer(final InvocationOnMock invocationOnMock) throws Throwable {
        Object answer = this.delegate.answer(invocationOnMock);
        if (answer != null) {
            return answer;
        }
        return RetrieveGenericsForDefaultAnswers.returnTypeForMockWithCorrectGenerics(invocationOnMock, new RetrieveGenericsForDefaultAnswers.AnswerCallback() {
            public Object apply(Class<?> cls) {
                if (cls == null) {
                    cls = invocationOnMock.getMethod().getReturnType();
                }
                return Mockito.mock(cls, new MockSettingsImpl().defaultAnswer(ReturnsMocks.this));
            }
        });
    }
}

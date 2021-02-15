package org.mockito.internal.stubbing.answers;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.ValidableAnswer;

public class AnswersWithDelay implements Answer<Object>, ValidableAnswer, Serializable {
    private static final long serialVersionUID = 2177950597971260246L;
    private final Answer<Object> answer;
    private final long sleepyTime;

    public AnswersWithDelay(long j, Answer<Object> answer2) {
        this.sleepyTime = j;
        this.answer = answer2;
    }

    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        TimeUnit.MILLISECONDS.sleep(this.sleepyTime);
        return this.answer.answer(invocationOnMock);
    }

    public void validateFor(InvocationOnMock invocationOnMock) {
        Answer<Object> answer2 = this.answer;
        if (answer2 instanceof ValidableAnswer) {
            ((ValidableAnswer) answer2).validateFor(invocationOnMock);
        }
    }
}

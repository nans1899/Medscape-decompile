package org.mockito;

import org.mockito.internal.stubbing.answers.CallsRealMethods;
import org.mockito.internal.stubbing.defaultanswers.GloballyConfiguredAnswer;
import org.mockito.internal.stubbing.defaultanswers.ReturnsDeepStubs;
import org.mockito.internal.stubbing.defaultanswers.ReturnsMocks;
import org.mockito.internal.stubbing.defaultanswers.ReturnsSmartNulls;
import org.mockito.internal.stubbing.defaultanswers.TriesToReturnSelf;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public enum Answers implements Answer<Object> {
    RETURNS_DEFAULTS(new GloballyConfiguredAnswer()),
    RETURNS_SMART_NULLS(new ReturnsSmartNulls()),
    RETURNS_MOCKS(new ReturnsMocks()),
    RETURNS_DEEP_STUBS(new ReturnsDeepStubs()),
    CALLS_REAL_METHODS(new CallsRealMethods()),
    RETURNS_SELF(new TriesToReturnSelf());
    
    private final Answer<Object> implementation;

    @Deprecated
    public Answer<Object> get() {
        return this;
    }

    private Answers(Answer<Object> answer) {
        this.implementation = answer;
    }

    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        return this.implementation.answer(invocationOnMock);
    }
}

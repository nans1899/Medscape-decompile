package org.mockito.internal.stubbing.answers;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Answer1;
import org.mockito.stubbing.Answer2;
import org.mockito.stubbing.Answer3;
import org.mockito.stubbing.Answer4;
import org.mockito.stubbing.Answer5;
import org.mockito.stubbing.VoidAnswer1;
import org.mockito.stubbing.VoidAnswer2;
import org.mockito.stubbing.VoidAnswer3;
import org.mockito.stubbing.VoidAnswer4;
import org.mockito.stubbing.VoidAnswer5;

public class AnswerFunctionalInterfaces {
    private AnswerFunctionalInterfaces() {
    }

    public static <T, A> Answer<T> toAnswer(final Answer1<T, A> answer1) {
        return new Answer<T>() {
            public T answer(InvocationOnMock invocationOnMock) throws Throwable {
                return Answer1.this.answer(invocationOnMock.getArgument(0));
            }
        };
    }

    public static <A> Answer<Void> toAnswer(final VoidAnswer1<A> voidAnswer1) {
        return new Answer<Void>() {
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                VoidAnswer1.this.answer(invocationOnMock.getArgument(0));
                return null;
            }
        };
    }

    public static <T, A, B> Answer<T> toAnswer(final Answer2<T, A, B> answer2) {
        return new Answer<T>() {
            public T answer(InvocationOnMock invocationOnMock) throws Throwable {
                return Answer2.this.answer(invocationOnMock.getArgument(0), invocationOnMock.getArgument(1));
            }
        };
    }

    public static <A, B> Answer<Void> toAnswer(final VoidAnswer2<A, B> voidAnswer2) {
        return new Answer<Void>() {
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                VoidAnswer2.this.answer(invocationOnMock.getArgument(0), invocationOnMock.getArgument(1));
                return null;
            }
        };
    }

    public static <T, A, B, C> Answer<T> toAnswer(final Answer3<T, A, B, C> answer3) {
        return new Answer<T>() {
            public T answer(InvocationOnMock invocationOnMock) throws Throwable {
                return Answer3.this.answer(invocationOnMock.getArgument(0), invocationOnMock.getArgument(1), invocationOnMock.getArgument(2));
            }
        };
    }

    public static <A, B, C> Answer<Void> toAnswer(final VoidAnswer3<A, B, C> voidAnswer3) {
        return new Answer<Void>() {
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                VoidAnswer3.this.answer(invocationOnMock.getArgument(0), invocationOnMock.getArgument(1), invocationOnMock.getArgument(2));
                return null;
            }
        };
    }

    public static <T, A, B, C, D> Answer<T> toAnswer(final Answer4<T, A, B, C, D> answer4) {
        return new Answer<T>() {
            public T answer(InvocationOnMock invocationOnMock) throws Throwable {
                return Answer4.this.answer(invocationOnMock.getArgument(0), invocationOnMock.getArgument(1), invocationOnMock.getArgument(2), invocationOnMock.getArgument(3));
            }
        };
    }

    public static <A, B, C, D> Answer<Void> toAnswer(final VoidAnswer4<A, B, C, D> voidAnswer4) {
        return new Answer<Void>() {
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                VoidAnswer4.this.answer(invocationOnMock.getArgument(0), invocationOnMock.getArgument(1), invocationOnMock.getArgument(2), invocationOnMock.getArgument(3));
                return null;
            }
        };
    }

    public static <T, A, B, C, D, E> Answer<T> toAnswer(final Answer5<T, A, B, C, D, E> answer5) {
        return new Answer<T>() {
            public T answer(InvocationOnMock invocationOnMock) throws Throwable {
                return Answer5.this.answer(invocationOnMock.getArgument(0), invocationOnMock.getArgument(1), invocationOnMock.getArgument(2), invocationOnMock.getArgument(3), invocationOnMock.getArgument(4));
            }
        };
    }

    public static <A, B, C, D, E> Answer<Void> toAnswer(final VoidAnswer5<A, B, C, D, E> voidAnswer5) {
        return new Answer<Void>() {
            public Void answer(InvocationOnMock invocationOnMock) throws Throwable {
                VoidAnswer5.this.answer(invocationOnMock.getArgument(0), invocationOnMock.getArgument(1), invocationOnMock.getArgument(2), invocationOnMock.getArgument(3), invocationOnMock.getArgument(4));
                return null;
            }
        };
    }
}

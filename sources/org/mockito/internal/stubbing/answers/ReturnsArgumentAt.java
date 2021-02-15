package org.mockito.internal.stubbing.answers;

import java.io.Serializable;
import java.lang.reflect.Method;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.ValidableAnswer;

public class ReturnsArgumentAt implements Answer<Object>, ValidableAnswer, Serializable {
    public static final int LAST_ARGUMENT = -1;
    private static final long serialVersionUID = -589315085166295101L;
    private final int wantedArgumentPosition;

    public ReturnsArgumentAt(int i) {
        if (i == -1 || i >= 0) {
            this.wantedArgumentPosition = i;
            return;
        }
        throw Reporter.invalidArgumentRangeAtIdentityAnswerCreationTime();
    }

    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        int inferWantedArgumentPosition = inferWantedArgumentPosition(invocationOnMock);
        validateIndexWithinInvocationRange(invocationOnMock, inferWantedArgumentPosition);
        if (wantedArgIndexIsVarargAndSameTypeAsReturnType(invocationOnMock.getMethod(), inferWantedArgumentPosition)) {
            return ((Invocation) invocationOnMock).getRawArguments()[inferWantedArgumentPosition];
        }
        return invocationOnMock.getArgument(inferWantedArgumentPosition);
    }

    public void validateFor(InvocationOnMock invocationOnMock) {
        int inferWantedArgumentPosition = inferWantedArgumentPosition(invocationOnMock);
        validateIndexWithinInvocationRange(invocationOnMock, inferWantedArgumentPosition);
        validateArgumentTypeCompatibility((Invocation) invocationOnMock, inferWantedArgumentPosition);
    }

    private int inferWantedArgumentPosition(InvocationOnMock invocationOnMock) {
        int i = this.wantedArgumentPosition;
        return i == -1 ? invocationOnMock.getArguments().length - 1 : i;
    }

    private void validateIndexWithinInvocationRange(InvocationOnMock invocationOnMock, int i) {
        if (!wantedArgumentPositionIsValidForInvocation(invocationOnMock, i)) {
            throw Reporter.invalidArgumentPositionRangeAtInvocationTime(invocationOnMock, this.wantedArgumentPosition == -1, this.wantedArgumentPosition);
        }
    }

    private void validateArgumentTypeCompatibility(Invocation invocation, int i) {
        InvocationInfo invocationInfo = new InvocationInfo(invocation);
        Class<?> inferArgumentType = inferArgumentType(invocation, i);
        if (!invocationInfo.isValidReturnType(inferArgumentType)) {
            throw Reporter.wrongTypeOfArgumentToReturn(invocation, invocationInfo.printMethodReturnType(), inferArgumentType, this.wantedArgumentPosition);
        }
    }

    private boolean wantedArgIndexIsVarargAndSameTypeAsReturnType(Method method, int i) {
        Class[] parameterTypes = method.getParameterTypes();
        if (!method.isVarArgs() || i != parameterTypes.length - 1 || !method.getReturnType().isAssignableFrom(parameterTypes[i])) {
            return false;
        }
        return true;
    }

    private boolean wantedArgumentPositionIsValidForInvocation(InvocationOnMock invocationOnMock, int i) {
        if (i < 0) {
            return false;
        }
        if (invocationOnMock.getMethod().isVarArgs() || invocationOnMock.getArguments().length > i) {
            return true;
        }
        return false;
    }

    private Class<?> inferArgumentType(Invocation invocation, int i) {
        Class<?>[] parameterTypes = invocation.getMethod().getParameterTypes();
        if (!invocation.getMethod().isVarArgs()) {
            Class<?> cls = parameterTypes[i];
            Object argument = invocation.getArgument(i);
            return (cls.isPrimitive() || argument == null) ? cls : argument.getClass();
        }
        int length = parameterTypes.length - 1;
        if (i < length) {
            return parameterTypes[i];
        }
        if (wantedArgIndexIsVarargAndSameTypeAsReturnType(invocation.getMethod(), i)) {
            return parameterTypes[i];
        }
        return parameterTypes[length].getComponentType();
    }
}

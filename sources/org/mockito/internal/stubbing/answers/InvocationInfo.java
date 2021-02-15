package org.mockito.internal.stubbing.answers;

import java.lang.reflect.Method;
import org.mockito.internal.invocation.AbstractAwareMethod;
import org.mockito.internal.util.Primitives;
import org.mockito.invocation.InvocationOnMock;

public class InvocationInfo implements AbstractAwareMethod {
    private final Method method;

    public InvocationInfo(InvocationOnMock invocationOnMock) {
        this.method = invocationOnMock.getMethod();
    }

    public boolean isValidException(Throwable th) {
        Class[] exceptionTypes = this.method.getExceptionTypes();
        Class<?> cls = th.getClass();
        for (Class isAssignableFrom : exceptionTypes) {
            if (isAssignableFrom.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidReturnType(Class<?> cls) {
        if (this.method.getReturnType().isPrimitive() || cls.isPrimitive()) {
            return Primitives.primitiveTypeOf(cls) == Primitives.primitiveTypeOf(this.method.getReturnType());
        }
        return this.method.getReturnType().isAssignableFrom(cls);
    }

    public boolean isVoid() {
        Class<?> returnType = this.method.getReturnType();
        return returnType == Void.TYPE || returnType == Void.class;
    }

    public String printMethodReturnType() {
        return this.method.getReturnType().getSimpleName();
    }

    public String getMethodName() {
        return this.method.getName();
    }

    public boolean returnsPrimitive() {
        return this.method.getReturnType().isPrimitive();
    }

    public Method getMethod() {
        return this.method;
    }

    public boolean isDeclaredOnInterface() {
        return this.method.getDeclaringClass().isInterface();
    }

    public boolean isAbstract() {
        return (this.method.getModifiers() & 1024) != 0;
    }
}

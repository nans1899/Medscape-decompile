package org.mockito.internal.creation;

import java.lang.reflect.Method;
import org.mockito.internal.invocation.MockitoMethod;

public class DelegatingMethod implements MockitoMethod {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Method method;
    private final Class<?>[] parameterTypes;

    public DelegatingMethod(Method method2) {
        this.method = method2;
        this.parameterTypes = SuspendMethod.trimSuspendParameterTypes(method2.getParameterTypes());
    }

    public Class<?>[] getExceptionTypes() {
        return this.method.getExceptionTypes();
    }

    public Method getJavaMethod() {
        return this.method;
    }

    public String getName() {
        return this.method.getName();
    }

    public Class<?>[] getParameterTypes() {
        return this.parameterTypes;
    }

    public Class<?> getReturnType() {
        return this.method.getReturnType();
    }

    public boolean isVarArgs() {
        return this.method.isVarArgs();
    }

    public boolean isAbstract() {
        return (this.method.getModifiers() & 1024) != 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DelegatingMethod) {
            return this.method.equals(((DelegatingMethod) obj).method);
        }
        return this.method.equals(obj);
    }

    public int hashCode() {
        return this.method.hashCode();
    }
}

package org.mockito.internal.stubbing.defaultanswers;

import java.io.Serializable;
import java.lang.reflect.Method;
import org.mockito.stubbing.Answer;

public class ForwardsInvocations implements Answer<Object>, Serializable {
    private static final long serialVersionUID = -8343690268123254910L;
    private Object delegatedObject = null;

    public ForwardsInvocations(Object obj) {
        this.delegatedObject = obj;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:4|5|6|7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0021 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object answer(org.mockito.invocation.InvocationOnMock r5) throws java.lang.Throwable {
        /*
            r4 = this;
            java.lang.reflect.Method r0 = r5.getMethod()
            java.lang.reflect.Method r1 = r4.getDelegateMethod(r0)     // Catch:{ NoSuchMethodException -> 0x0039, InvocationTargetException -> 0x0033 }
            java.lang.Class r2 = r0.getReturnType()     // Catch:{ NoSuchMethodException -> 0x0039, InvocationTargetException -> 0x0033 }
            java.lang.Class r3 = r1.getReturnType()     // Catch:{ NoSuchMethodException -> 0x0039, InvocationTargetException -> 0x0033 }
            boolean r2 = compatibleReturnTypes(r2, r3)     // Catch:{ NoSuchMethodException -> 0x0039, InvocationTargetException -> 0x0033 }
            if (r2 == 0) goto L_0x0028
            r2 = r5
            org.mockito.invocation.Invocation r2 = (org.mockito.invocation.Invocation) r2     // Catch:{ NoSuchMethodException -> 0x0039, InvocationTargetException -> 0x0033 }
            java.lang.Object[] r2 = r2.getRawArguments()     // Catch:{ NoSuchMethodException -> 0x0039, InvocationTargetException -> 0x0033 }
            r3 = 1
            r1.setAccessible(r3)     // Catch:{ SecurityException -> 0x0021 }
        L_0x0021:
            java.lang.Object r3 = r4.delegatedObject     // Catch:{ NoSuchMethodException -> 0x0039, InvocationTargetException -> 0x0033 }
            java.lang.Object r5 = r1.invoke(r3, r2)     // Catch:{ NoSuchMethodException -> 0x0039, InvocationTargetException -> 0x0033 }
            return r5
        L_0x0028:
            java.lang.Object r2 = r5.getMock()     // Catch:{ NoSuchMethodException -> 0x0039, InvocationTargetException -> 0x0033 }
            java.lang.Object r3 = r4.delegatedObject     // Catch:{ NoSuchMethodException -> 0x0039, InvocationTargetException -> 0x0033 }
            org.mockito.exceptions.base.MockitoException r1 = org.mockito.internal.exceptions.Reporter.delegatedMethodHasWrongReturnType(r0, r1, r2, r3)     // Catch:{ NoSuchMethodException -> 0x0039, InvocationTargetException -> 0x0033 }
            throw r1     // Catch:{ NoSuchMethodException -> 0x0039, InvocationTargetException -> 0x0033 }
        L_0x0033:
            r5 = move-exception
            java.lang.Throwable r5 = r5.getCause()
            throw r5
        L_0x0039:
            java.lang.Object r5 = r5.getMock()
            java.lang.Object r1 = r4.delegatedObject
            org.mockito.exceptions.base.MockitoException r5 = org.mockito.internal.exceptions.Reporter.delegatedMethodDoesNotExistOnDelegate(r0, r5, r1)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mockito.internal.stubbing.defaultanswers.ForwardsInvocations.answer(org.mockito.invocation.InvocationOnMock):java.lang.Object");
    }

    private Method getDelegateMethod(Method method) throws NoSuchMethodException {
        if (method.getDeclaringClass().isAssignableFrom(this.delegatedObject.getClass())) {
            return method;
        }
        return this.delegatedObject.getClass().getMethod(method.getName(), method.getParameterTypes());
    }

    private static boolean compatibleReturnTypes(Class<?> cls, Class<?> cls2) {
        return cls.equals(cls2) || cls.isAssignableFrom(cls2);
    }
}

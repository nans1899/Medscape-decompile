package org.mockito.internal.stubbing.defaultanswers;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.mockito.internal.MockitoCore;
import org.mockito.internal.util.MockUtil;
import org.mockito.internal.util.reflection.GenericMetadataSupport;
import org.mockito.invocation.InvocationOnMock;

class RetrieveGenericsForDefaultAnswers {
    private static final MockitoCore MOCKITO_CORE = new MockitoCore();

    interface AnswerCallback {
        Object apply(Class<?> cls);
    }

    RetrieveGenericsForDefaultAnswers() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0015, code lost:
        r0 = findTypeFromGeneric(r4, (java.lang.reflect.TypeVariable) r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Object returnTypeForMockWithCorrectGenerics(org.mockito.invocation.InvocationOnMock r4, org.mockito.internal.stubbing.defaultanswers.RetrieveGenericsForDefaultAnswers.AnswerCallback r5) {
        /*
            java.lang.reflect.Method r0 = r4.getMethod()
            java.lang.Class r0 = r0.getReturnType()
            java.lang.reflect.Method r1 = r4.getMethod()
            java.lang.reflect.Type r1 = r1.getGenericReturnType()
            boolean r2 = r1 instanceof java.lang.reflect.TypeVariable
            r3 = 0
            if (r2 == 0) goto L_0x0022
            java.lang.reflect.TypeVariable r1 = (java.lang.reflect.TypeVariable) r1
            java.lang.Class r0 = findTypeFromGeneric(r4, r1)
            if (r0 == 0) goto L_0x0022
            java.lang.Object r4 = delegateChains(r0)
            goto L_0x0023
        L_0x0022:
            r4 = r3
        L_0x0023:
            if (r4 == 0) goto L_0x0026
            return r4
        L_0x0026:
            if (r0 == 0) goto L_0x0036
            org.mockito.internal.MockitoCore r4 = MOCKITO_CORE
            boolean r4 = r4.isTypeMockable(r0)
            if (r4 != 0) goto L_0x0031
            return r3
        L_0x0031:
            java.lang.Object r4 = r5.apply(r0)
            return r4
        L_0x0036:
            java.lang.Object r4 = r5.apply(r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mockito.internal.stubbing.defaultanswers.RetrieveGenericsForDefaultAnswers.returnTypeForMockWithCorrectGenerics(org.mockito.invocation.InvocationOnMock, org.mockito.internal.stubbing.defaultanswers.RetrieveGenericsForDefaultAnswers$AnswerCallback):java.lang.Object");
    }

    private static Object delegateChains(Class<?> cls) {
        ReturnsEmptyValues returnsEmptyValues = new ReturnsEmptyValues();
        Object returnValueFor = returnsEmptyValues.returnValueFor(cls);
        if (returnValueFor == null) {
            for (Class<?> cls2 = cls; cls2 != null && returnValueFor == null; cls2 = cls2.getSuperclass()) {
                for (Class returnValueFor2 : cls2.getInterfaces()) {
                    returnValueFor = returnsEmptyValues.returnValueFor(returnValueFor2);
                    if (returnValueFor != null) {
                        break;
                    }
                }
            }
        }
        return returnValueFor == null ? new ReturnsMoreEmptyValues().returnValueFor(cls) : returnValueFor;
    }

    private static Class<?> findTypeFromGeneric(InvocationOnMock invocationOnMock, TypeVariable typeVariable) {
        Class<?> rawType = GenericMetadataSupport.inferFrom(MockUtil.getMockHandler(invocationOnMock.getMock()).getMockSettings().getTypeToMock()).resolveGenericReturnType(invocationOnMock.getMethod()).rawType();
        return rawType == Object.class ? findTypeFromGenericInArguments(invocationOnMock, typeVariable) : rawType;
    }

    private static Class<?> findTypeFromGenericInArguments(InvocationOnMock invocationOnMock, TypeVariable typeVariable) {
        Type[] genericParameterTypes = invocationOnMock.getMethod().getGenericParameterTypes();
        int i = 0;
        while (i < genericParameterTypes.length) {
            Type type = genericParameterTypes[i];
            if (typeVariable.equals(type)) {
                Object argument = invocationOnMock.getArgument(i);
                if (argument == null) {
                    return null;
                }
                return argument.getClass();
            } else if ((type instanceof GenericArrayType) && typeVariable.equals(((GenericArrayType) type).getGenericComponentType())) {
                return invocationOnMock.getArgument(i).getClass();
            } else {
                i++;
            }
        }
        return null;
    }
}

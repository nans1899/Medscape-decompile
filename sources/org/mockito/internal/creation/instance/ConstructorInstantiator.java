package org.mockito.internal.creation.instance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.mockito.creation.instance.InstantiationException;
import org.mockito.creation.instance.Instantiator;
import org.mockito.internal.util.Primitives;
import org.mockito.internal.util.StringUtil;
import org.mockito.internal.util.reflection.AccessibilityChanger;

public class ConstructorInstantiator implements Instantiator {
    private final Object[] constructorArgs;
    private final boolean hasOuterClassInstance;

    public ConstructorInstantiator(boolean z, Object... objArr) {
        this.hasOuterClassInstance = z;
        this.constructorArgs = objArr;
    }

    public <T> T newInstance(Class<T> cls) {
        return withParams(cls, this.constructorArgs);
    }

    private <T> T withParams(Class<T> cls, Object... objArr) {
        LinkedList linkedList = new LinkedList();
        try {
            for (Constructor constructor : cls.getDeclaredConstructors()) {
                if (paramsMatch(constructor.getParameterTypes(), objArr)) {
                    evaluateConstructor(linkedList, constructor);
                }
            }
            if (linkedList.size() == 1) {
                return invokeConstructor((Constructor) linkedList.get(0), objArr);
            }
            if (linkedList.size() == 0) {
                throw noMatchingConstructor(cls);
            }
            throw multipleMatchingConstructors(cls, linkedList);
        } catch (Exception e) {
            throw paramsException(cls, e);
        }
    }

    private static <T> T invokeConstructor(Constructor<?> constructor, Object... objArr) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        new AccessibilityChanger().enableAccess(constructor);
        return constructor.newInstance(objArr);
    }

    private InstantiationException paramsException(Class<?> cls, Exception exc) {
        return new InstantiationException(StringUtil.join("Unable to create instance of '" + cls.getSimpleName() + "'.", "Please ensure the target class has " + constructorArgsString() + " and executes cleanly."), exc);
    }

    private String constructorArgTypes() {
        int i = this.hasOuterClassInstance ? 1 : 0;
        String[] strArr = new String[(this.constructorArgs.length - i)];
        int i2 = i;
        while (true) {
            Object[] objArr = this.constructorArgs;
            if (i2 >= objArr.length) {
                return Arrays.toString(strArr);
            }
            strArr[i2 - i] = objArr[i2] == null ? null : objArr[i2].getClass().getName();
            i2++;
        }
    }

    private InstantiationException noMatchingConstructor(Class<?> cls) {
        String constructorArgsString = constructorArgsString();
        String str = this.hasOuterClassInstance ? " and provided outer instance is correct" : "";
        return new InstantiationException(StringUtil.join("Unable to create instance of '" + cls.getSimpleName() + "'.", "Please ensure that the target class has " + constructorArgsString + str + "."), (Throwable) null);
    }

    private String constructorArgsString() {
        Object[] objArr = this.constructorArgs;
        if (objArr.length == 0 || (this.hasOuterClassInstance && objArr.length == 1)) {
            return "a 0-arg constructor";
        }
        return "a constructor that matches these argument types: " + constructorArgTypes();
    }

    private InstantiationException multipleMatchingConstructors(Class<?> cls, List<Constructor<?>> list) {
        return new InstantiationException(StringUtil.join("Unable to create instance of '" + cls.getSimpleName() + "'.", "Multiple constructors could be matched to arguments of types " + constructorArgTypes() + ":", StringUtil.join("", " - ", list), "If you believe that Mockito could do a better job deciding on which constructor to use, please let us know.", "Ticket 685 contains the discussion and a workaround for ambiguous constructors using inner class.", "See https://github.com/mockito/mockito/issues/685"), (Throwable) null);
    }

    private static boolean paramsMatch(Class<?>[] clsArr, Object[] objArr) {
        if (objArr.length != clsArr.length) {
            return false;
        }
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] == null) {
                if (clsArr[i].isPrimitive()) {
                    return false;
                }
            } else if ((!clsArr[i].isPrimitive() && !clsArr[i].isInstance(objArr[i])) || (clsArr[i].isPrimitive() && !clsArr[i].equals(Primitives.primitiveTypeOf(objArr[i].getClass())))) {
                return false;
            }
        }
        return true;
    }

    private void evaluateConstructor(List<Constructor<?>> list, Constructor<?> constructor) {
        Class[] parameterTypes = constructor.getParameterTypes();
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < parameterTypes.length; i++) {
            Class cls = parameterTypes[i];
            if (!cls.isPrimitive()) {
                for (Constructor<?> parameterTypes2 : list) {
                    Class cls2 = parameterTypes2.getParameterTypes()[i];
                    if (cls != cls2) {
                        if (cls.isAssignableFrom(cls2)) {
                            z = true;
                        } else {
                            z2 = true;
                        }
                    }
                }
            }
        }
        if (!z) {
            list.clear();
        }
        if (z2 || !z) {
            list.add(constructor);
        }
    }
}

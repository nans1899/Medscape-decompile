package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class MethodPartFactory {
    private final AnnotationFactory factory;

    public MethodPartFactory(Detail detail, Support support) {
        this.factory = new AnnotationFactory(detail, support);
    }

    public MethodPart getInstance(Method method, Annotation[] annotationArr) throws Exception {
        Annotation annotation = getAnnotation(method);
        if (annotation != null) {
            return getInstance(method, annotation, annotationArr);
        }
        return null;
    }

    public MethodPart getInstance(Method method, Annotation annotation, Annotation[] annotationArr) throws Exception {
        MethodName name = getName(method, annotation);
        if (name.getType() == MethodType.SET) {
            return new SetPart(name, annotation, annotationArr);
        }
        return new GetPart(name, annotation, annotationArr);
    }

    private MethodName getName(Method method, Annotation annotation) throws Exception {
        MethodType methodType = getMethodType(method);
        if (methodType == MethodType.GET) {
            return getRead(method, methodType);
        }
        if (methodType == MethodType.IS) {
            return getRead(method, methodType);
        }
        if (methodType == MethodType.SET) {
            return getWrite(method, methodType);
        }
        throw new MethodException("Annotation %s must mark a set or get method", annotation);
    }

    private MethodType getMethodType(Method method) {
        String name = method.getName();
        if (name.startsWith("get")) {
            return MethodType.GET;
        }
        if (name.startsWith("is")) {
            return MethodType.IS;
        }
        if (name.startsWith("set")) {
            return MethodType.SET;
        }
        return MethodType.NONE;
    }

    private Annotation getAnnotation(Method method) throws Exception {
        Class[] dependents = getDependents(method);
        Class type = getType(method);
        if (type != null) {
            return this.factory.getInstance(type, dependents);
        }
        return null;
    }

    private Class[] getDependents(Method method) throws Exception {
        MethodType methodType = getMethodType(method);
        if (methodType == MethodType.SET) {
            return Reflector.getParameterDependents(method, 0);
        }
        if (methodType == MethodType.GET) {
            return Reflector.getReturnDependents(method);
        }
        if (methodType == MethodType.IS) {
            return Reflector.getReturnDependents(method);
        }
        return null;
    }

    public Class getType(Method method) throws Exception {
        MethodType methodType = getMethodType(method);
        if (methodType == MethodType.SET) {
            return getParameterType(method);
        }
        if (methodType == MethodType.GET) {
            return getReturnType(method);
        }
        if (methodType == MethodType.IS) {
            return getReturnType(method);
        }
        return null;
    }

    private Class getParameterType(Method method) throws Exception {
        if (method.getParameterTypes().length == 1) {
            return method.getParameterTypes()[0];
        }
        return null;
    }

    private Class getReturnType(Method method) throws Exception {
        if (method.getParameterTypes().length == 0) {
            return method.getReturnType();
        }
        return null;
    }

    private MethodName getRead(Method method, MethodType methodType) throws Exception {
        Class[] parameterTypes = method.getParameterTypes();
        String name = method.getName();
        if (parameterTypes.length == 0) {
            String typeName = getTypeName(name, methodType);
            if (typeName != null) {
                return new MethodName(method, methodType, typeName);
            }
            throw new MethodException("Could not get name for %s", method);
        }
        throw new MethodException("Get method %s is not a valid property", method);
    }

    private MethodName getWrite(Method method, MethodType methodType) throws Exception {
        Class[] parameterTypes = method.getParameterTypes();
        String name = method.getName();
        if (parameterTypes.length == 1) {
            String typeName = getTypeName(name, methodType);
            if (typeName != null) {
                return new MethodName(method, methodType, typeName);
            }
            throw new MethodException("Could not get name for %s", method);
        }
        throw new MethodException("Set method %s is not a valid property", method);
    }

    private String getTypeName(String str, MethodType methodType) {
        int prefix = methodType.getPrefix();
        int length = str.length();
        if (length > prefix) {
            str = str.substring(prefix, length);
        }
        return Reflector.getName(str);
    }
}

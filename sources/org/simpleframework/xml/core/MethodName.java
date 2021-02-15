package org.simpleframework.xml.core;

import java.lang.reflect.Method;

class MethodName {
    private Method method;
    private String name;
    private MethodType type;

    public MethodName(Method method2, MethodType methodType, String str) {
        this.method = method2;
        this.type = methodType;
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public MethodType getType() {
        return this.type;
    }

    public Method getMethod() {
        return this.method;
    }
}

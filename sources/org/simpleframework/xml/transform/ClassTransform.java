package org.simpleframework.xml.transform;

class ClassTransform implements Transform<Class> {
    private static final String BOOLEAN = "boolean";
    private static final String BYTE = "byte";
    private static final String CHARACTER = "char";
    private static final String DOUBLE = "double";
    private static final String FLOAT = "float";
    private static final String INTEGER = "int";
    private static final String LONG = "long";
    private static final String SHORT = "short";
    private static final String VOID = "void";

    ClassTransform() {
    }

    public Class read(String str) throws Exception {
        Class readPrimitive = readPrimitive(str);
        if (readPrimitive != null) {
            return readPrimitive;
        }
        ClassLoader classLoader = getClassLoader();
        if (classLoader == null) {
            classLoader = getCallerClassLoader();
        }
        return classLoader.loadClass(str);
    }

    private Class readPrimitive(String str) throws Exception {
        if (str.equals(BYTE)) {
            return Byte.TYPE;
        }
        if (str.equals(SHORT)) {
            return Short.TYPE;
        }
        if (str.equals(INTEGER)) {
            return Integer.TYPE;
        }
        if (str.equals("long")) {
            return Long.TYPE;
        }
        if (str.equals(CHARACTER)) {
            return Character.TYPE;
        }
        if (str.equals(FLOAT)) {
            return Float.TYPE;
        }
        if (str.equals(DOUBLE)) {
            return Double.TYPE;
        }
        if (str.equals(BOOLEAN)) {
            return Boolean.TYPE;
        }
        if (str.equals(VOID)) {
            return Void.TYPE;
        }
        return null;
    }

    public String write(Class cls) throws Exception {
        return cls.getName();
    }

    private ClassLoader getCallerClassLoader() {
        return getClass().getClassLoader();
    }

    private static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}

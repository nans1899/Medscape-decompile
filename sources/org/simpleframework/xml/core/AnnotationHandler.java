package org.simpleframework.xml.core;

import com.dd.plist.ASCIIPropertyListParser;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class AnnotationHandler implements InvocationHandler {
    private static final String ATTRIBUTE = "attribute";
    private static final String CLASS = "annotationType";
    private static final String EQUAL = "equals";
    private static final String REQUIRED = "required";
    private static final String STRING = "toString";
    private final boolean attribute;
    private final Comparer comparer;
    private final boolean required;
    private final Class type;

    public AnnotationHandler(Class cls) {
        this(cls, true);
    }

    public AnnotationHandler(Class cls, boolean z) {
        this(cls, z, false);
    }

    public AnnotationHandler(Class cls, boolean z, boolean z2) {
        this.comparer = new Comparer();
        this.attribute = z2;
        this.required = z;
        this.type = cls;
    }

    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        String name = method.getName();
        if (name.equals(STRING)) {
            return toString();
        }
        if (name.equals(EQUAL)) {
            return Boolean.valueOf(equals(obj, objArr));
        }
        if (name.equals(CLASS)) {
            return this.type;
        }
        if (name.equals(REQUIRED)) {
            return Boolean.valueOf(this.required);
        }
        if (name.equals(ATTRIBUTE)) {
            return Boolean.valueOf(this.attribute);
        }
        return method.getDefaultValue();
    }

    private boolean equals(Object obj, Object[] objArr) throws Throwable {
        Annotation annotation = (Annotation) obj;
        Annotation annotation2 = objArr[0];
        if (annotation.annotationType() == annotation2.annotationType()) {
            return this.comparer.equals(annotation, annotation2);
        }
        throw new PersistenceException("Annotation %s is not the same as %s", annotation, annotation2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.type != null) {
            name(sb);
            attributes(sb);
        }
        return sb.toString();
    }

    private void name(StringBuilder sb) {
        String name = this.type.getName();
        if (name != null) {
            sb.append('@');
            sb.append(name);
            sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
        }
    }

    private void attributes(StringBuilder sb) {
        Method[] declaredMethods = this.type.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            String name = declaredMethods[i].getName();
            Object value = value(declaredMethods[i]);
            if (i > 0) {
                sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
                sb.append(' ');
            }
            sb.append(name);
            sb.append('=');
            sb.append(value);
        }
        sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
    }

    private Object value(Method method) {
        String name = method.getName();
        if (name.equals(REQUIRED)) {
            return Boolean.valueOf(this.required);
        }
        if (name.equals(ATTRIBUTE)) {
            return Boolean.valueOf(this.attribute);
        }
        return method.getDefaultValue();
    }
}

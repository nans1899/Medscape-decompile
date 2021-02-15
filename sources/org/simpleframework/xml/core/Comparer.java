package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class Comparer {
    private static final String NAME = "name";
    private final String[] ignore;

    public Comparer() {
        this("name");
    }

    public Comparer(String... strArr) {
        this.ignore = strArr;
    }

    public boolean equals(Annotation annotation, Annotation annotation2) throws Exception {
        Class<? extends Annotation> annotationType = annotation.annotationType();
        Class<? extends Annotation> annotationType2 = annotation2.annotationType();
        Method[] declaredMethods = annotationType.getDeclaredMethods();
        if (!annotationType.equals(annotationType2)) {
            return false;
        }
        for (Method method : declaredMethods) {
            if (!isIgnore(method) && !method.invoke(annotation, new Object[0]).equals(method.invoke(annotation2, new Object[0]))) {
                return false;
            }
        }
        return true;
    }

    private boolean isIgnore(Method method) {
        String name = method.getName();
        String[] strArr = this.ignore;
        if (strArr != null) {
            for (String equals : strArr) {
                if (name.equals(equals)) {
                    return true;
                }
            }
        }
        return false;
    }
}

package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Map;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Verbosity;

class AnnotationFactory {
    private final Format format;
    private final boolean required;

    public AnnotationFactory(Detail detail, Support support) {
        this.required = detail.isRequired();
        this.format = support.getFormat();
    }

    public Annotation getInstance(Class cls, Class[] clsArr) throws Exception {
        ClassLoader classLoader = getClassLoader();
        if (Map.class.isAssignableFrom(cls)) {
            if (!isPrimitiveKey(clsArr) || !isAttribute()) {
                return getInstance(classLoader, ElementMap.class);
            }
            return getInstance(classLoader, ElementMap.class, true);
        } else if (Collection.class.isAssignableFrom(cls)) {
            return getInstance(classLoader, ElementList.class);
        } else {
            return getInstance(cls);
        }
    }

    private Annotation getInstance(Class cls) throws Exception {
        ClassLoader classLoader = getClassLoader();
        Class<?> componentType = cls.getComponentType();
        if (cls.isArray()) {
            if (isPrimitive(componentType)) {
                return getInstance(classLoader, Element.class);
            }
            return getInstance(classLoader, ElementArray.class);
        } else if (!isPrimitive(cls) || !isAttribute()) {
            return getInstance(classLoader, Element.class);
        } else {
            return getInstance(classLoader, Attribute.class);
        }
    }

    private Annotation getInstance(ClassLoader classLoader, Class cls) throws Exception {
        return getInstance(classLoader, cls, false);
    }

    private Annotation getInstance(ClassLoader classLoader, Class cls, boolean z) throws Exception {
        AnnotationHandler annotationHandler = new AnnotationHandler(cls, this.required, z);
        return (Annotation) Proxy.newProxyInstance(classLoader, new Class[]{cls}, annotationHandler);
    }

    private ClassLoader getClassLoader() throws Exception {
        return AnnotationFactory.class.getClassLoader();
    }

    private boolean isPrimitiveKey(Class[] clsArr) {
        if (clsArr == null || clsArr.length <= 0) {
            return false;
        }
        Class superclass = clsArr[0].getSuperclass();
        Class cls = clsArr[0];
        if (superclass == null || (!superclass.isEnum() && !cls.isEnum())) {
            return isPrimitive(cls);
        }
        return true;
    }

    private boolean isPrimitive(Class cls) {
        if (Number.class.isAssignableFrom(cls) || cls == Boolean.class || cls == Character.class) {
            return true;
        }
        return cls.isPrimitive();
    }

    private boolean isAttribute() {
        Verbosity verbosity = this.format.getVerbosity();
        if (verbosity == null || verbosity != Verbosity.LOW) {
            return false;
        }
        return true;
    }
}

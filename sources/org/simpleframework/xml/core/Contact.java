package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.strategy.Type;

interface Contact extends Type {
    Object get(Object obj) throws Exception;

    Annotation getAnnotation();

    Class getDeclaringClass();

    Class getDependent();

    Class[] getDependents();

    String getName();

    boolean isReadOnly();

    void set(Object obj, Object obj2) throws Exception;

    String toString();
}

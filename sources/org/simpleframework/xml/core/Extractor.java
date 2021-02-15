package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;

interface Extractor<T extends Annotation> {
    T[] getAnnotations() throws Exception;

    Label getLabel(T t) throws Exception;

    Class getType(T t) throws Exception;
}

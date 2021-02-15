package org.simpleframework.xml.convert;

import java.lang.annotation.Annotation;

interface Scanner {
    <T extends Annotation> T scan(Class<T> cls);
}

package org.simpleframework.xml.transform;

public interface Matcher {
    Transform match(Class cls) throws Exception;
}

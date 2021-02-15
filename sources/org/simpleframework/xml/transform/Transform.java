package org.simpleframework.xml.transform;

public interface Transform<T> {
    T read(String str) throws Exception;

    String write(T t) throws Exception;
}

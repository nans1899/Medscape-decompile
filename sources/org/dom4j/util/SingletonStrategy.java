package org.dom4j.util;

public interface SingletonStrategy {
    Object instance();

    void reset();

    void setSingletonClassName(String str);
}

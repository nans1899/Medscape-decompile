package org.simpleframework.xml.strategy;

public interface Value {
    int getLength();

    Class getType();

    Object getValue();

    boolean isReference();

    void setValue(Object obj);
}

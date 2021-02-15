package org.simpleframework.xml.core;

interface Instance {
    Object getInstance() throws Exception;

    Class getType();

    boolean isReference();

    Object setInstance(Object obj) throws Exception;
}

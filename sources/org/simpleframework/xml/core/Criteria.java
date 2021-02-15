package org.simpleframework.xml.core;

interface Criteria extends Iterable<Object> {
    void commit(Object obj) throws Exception;

    Variable get(Object obj) throws Exception;

    Variable get(Label label) throws Exception;

    Variable remove(Object obj) throws Exception;

    Variable resolve(String str) throws Exception;

    void set(Label label, Object obj) throws Exception;
}

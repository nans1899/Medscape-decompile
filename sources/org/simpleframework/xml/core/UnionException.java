package org.simpleframework.xml.core;

public class UnionException extends PersistenceException {
    public UnionException(String str, Object... objArr) {
        super(String.format(str, objArr), new Object[0]);
    }
}

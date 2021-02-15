package org.simpleframework.xml.core;

public class ElementException extends PersistenceException {
    public ElementException(String str, Object... objArr) {
        super(str, objArr);
    }

    public ElementException(Throwable th, String str, Object... objArr) {
        super(th, str, objArr);
    }
}

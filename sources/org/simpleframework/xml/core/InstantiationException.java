package org.simpleframework.xml.core;

public class InstantiationException extends PersistenceException {
    public InstantiationException(String str, Object... objArr) {
        super(str, objArr);
    }

    public InstantiationException(Throwable th, String str, Object... objArr) {
        super(th, str, objArr);
    }
}

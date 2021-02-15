package org.simpleframework.xml.core;

public class MethodException extends PersistenceException {
    public MethodException(String str, Object... objArr) {
        super(str, objArr);
    }

    public MethodException(Throwable th, String str, Object... objArr) {
        super(th, str, objArr);
    }
}

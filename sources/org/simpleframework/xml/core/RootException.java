package org.simpleframework.xml.core;

public class RootException extends PersistenceException {
    public RootException(String str, Object... objArr) {
        super(str, objArr);
    }

    public RootException(Throwable th, String str, Object... objArr) {
        super(th, str, objArr);
    }
}

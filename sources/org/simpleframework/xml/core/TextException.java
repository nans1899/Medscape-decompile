package org.simpleframework.xml.core;

public class TextException extends PersistenceException {
    public TextException(String str, Object... objArr) {
        super(str, objArr);
    }

    public TextException(Throwable th, String str, Object... objArr) {
        super(th, str, objArr);
    }
}

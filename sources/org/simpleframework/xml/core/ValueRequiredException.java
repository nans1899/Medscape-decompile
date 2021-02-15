package org.simpleframework.xml.core;

public class ValueRequiredException extends PersistenceException {
    public ValueRequiredException(String str, Object... objArr) {
        super(str, objArr);
    }

    public ValueRequiredException(Throwable th, String str, Object... objArr) {
        super(th, str, objArr);
    }
}

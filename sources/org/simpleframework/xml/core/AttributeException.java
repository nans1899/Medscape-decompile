package org.simpleframework.xml.core;

public class AttributeException extends PersistenceException {
    public AttributeException(String str, Object... objArr) {
        super(str, objArr);
    }

    public AttributeException(Throwable th, String str, Object... objArr) {
        super(th, str, objArr);
    }
}

package org.simpleframework.xml.transform;

public class InvalidFormatException extends TransformException {
    public InvalidFormatException(String str, Object... objArr) {
        super(String.format(str, objArr), new Object[0]);
    }

    public InvalidFormatException(Throwable th, String str, Object... objArr) {
        super(String.format(str, objArr), th);
    }
}

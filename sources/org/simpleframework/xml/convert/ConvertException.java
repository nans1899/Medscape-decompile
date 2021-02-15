package org.simpleframework.xml.convert;

public class ConvertException extends Exception {
    public ConvertException(String str, Object... objArr) {
        super(String.format(str, objArr));
    }
}

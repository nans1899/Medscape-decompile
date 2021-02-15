package org.simpleframework.xml.transform;

import java.lang.reflect.Constructor;
import java.util.Date;

class DateFactory<T extends Date> {
    private final Constructor<T> factory;

    public DateFactory(Class<T> cls) throws Exception {
        this(cls, Long.TYPE);
    }

    public DateFactory(Class<T> cls, Class... clsArr) throws Exception {
        this.factory = cls.getDeclaredConstructor(clsArr);
    }

    public T getInstance(Object... objArr) throws Exception {
        return (Date) this.factory.newInstance(objArr);
    }
}

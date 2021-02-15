package org.simpleframework.xml.transform;

import java.util.Date;

class DateTransform<T extends Date> implements Transform<T> {
    private final DateFactory<T> factory;

    public DateTransform(Class<T> cls) throws Exception {
        this.factory = new DateFactory<>(cls);
    }

    public synchronized T read(String str) throws Exception {
        Long valueOf;
        valueOf = Long.valueOf(DateType.getDate(str).getTime());
        return this.factory.getInstance(valueOf);
    }

    public synchronized String write(T t) throws Exception {
        return DateType.getText(t);
    }
}

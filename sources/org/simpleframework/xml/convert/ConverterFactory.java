package org.simpleframework.xml.convert;

import java.lang.reflect.Constructor;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class ConverterFactory {
    private final Cache<Converter> cache = new ConcurrentCache();

    public Converter getInstance(Class cls) throws Exception {
        Converter fetch = this.cache.fetch(cls);
        return fetch == null ? getConverter(cls) : fetch;
    }

    public Converter getInstance(Convert convert) throws Exception {
        Class<? extends Converter> value = convert.value();
        if (!value.isInterface()) {
            return getInstance((Class) value);
        }
        throw new ConvertException("Can not instantiate %s", value);
    }

    private Converter getConverter(Class cls) throws Exception {
        Constructor constructor = getConstructor(cls);
        if (constructor != null) {
            return getConverter(cls, constructor);
        }
        throw new ConvertException("No default constructor for %s", cls);
    }

    private Converter getConverter(Class cls, Constructor constructor) throws Exception {
        Converter converter = (Converter) constructor.newInstance(new Object[0]);
        if (converter != null) {
            this.cache.cache(cls, converter);
        }
        return converter;
    }

    private Constructor getConstructor(Class cls) throws Exception {
        Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        if (!declaredConstructor.isAccessible()) {
            declaredConstructor.setAccessible(true);
        }
        return declaredConstructor;
    }
}

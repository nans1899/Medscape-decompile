package org.simpleframework.xml.convert;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class RegistryBinder {
    private final Cache<Class> cache = new ConcurrentCache();
    private final ConverterFactory factory = new ConverterFactory();

    public Converter lookup(Class cls) throws Exception {
        Class fetch = this.cache.fetch(cls);
        if (fetch != null) {
            return create(fetch);
        }
        return null;
    }

    private Converter create(Class cls) throws Exception {
        return this.factory.getInstance(cls);
    }

    public void bind(Class cls, Class cls2) throws Exception {
        this.cache.cache(cls, cls2);
    }
}

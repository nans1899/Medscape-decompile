package org.simpleframework.xml.core;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class ScannerFactory {
    private final Cache<Scanner> cache = new ConcurrentCache();
    private final Support support;

    public ScannerFactory(Support support2) {
        this.support = support2;
    }

    public Scanner getInstance(Class cls) throws Exception {
        Scanner scanner;
        Scanner fetch = this.cache.fetch(cls);
        if (fetch != null) {
            return fetch;
        }
        Detail detail = this.support.getDetail(cls);
        if (this.support.isPrimitive(cls)) {
            scanner = new PrimitiveScanner(detail);
        } else {
            scanner = new ObjectScanner(detail, this.support);
            if (scanner.isPrimitive() && !this.support.isContainer(cls)) {
                scanner = new DefaultScanner(detail, this.support);
            }
        }
        Scanner scanner2 = scanner;
        this.cache.cache(cls, scanner2);
        return scanner2;
    }
}

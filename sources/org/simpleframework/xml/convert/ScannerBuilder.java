package org.simpleframework.xml.convert;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.util.ConcurrentCache;

class ScannerBuilder extends ConcurrentCache<Scanner> {
    public Scanner build(Class<?> cls) {
        Scanner scanner = (Scanner) get(cls);
        if (scanner != null) {
            return scanner;
        }
        Entry entry = new Entry(cls);
        put(cls, entry);
        return entry;
    }

    private static class Entry extends ConcurrentCache<Annotation> implements Scanner {
        private final Class root;

        public Entry(Class cls) {
            this.root = cls;
        }

        public <T extends Annotation> T scan(Class<T> cls) {
            if (!contains(cls)) {
                T find = find(cls);
                if (!(cls == null || find == null)) {
                    put(cls, find);
                }
            }
            return (Annotation) get(cls);
        }

        private <T extends Annotation> T find(Class<T> cls) {
            for (Class cls2 = this.root; cls2 != null; cls2 = cls2.getSuperclass()) {
                T annotation = cls2.getAnnotation(cls);
                if (annotation != null) {
                    return annotation;
                }
            }
            return null;
        }
    }
}

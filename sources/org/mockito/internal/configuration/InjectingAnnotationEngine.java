package org.mockito.internal.configuration;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import org.mockito.internal.configuration.injection.scanner.InjectMocksScanner;
import org.mockito.internal.configuration.injection.scanner.MockScanner;
import org.mockito.internal.util.collections.Sets;
import org.mockito.plugins.AnnotationEngine;

public class InjectingAnnotationEngine implements AnnotationEngine, org.mockito.configuration.AnnotationEngine {
    private final AnnotationEngine delegate = new IndependentAnnotationEngine();
    private final AnnotationEngine spyAnnotationEngine = new SpyAnnotationEngine();

    /* access modifiers changed from: protected */
    public void onInjection(Object obj, Class<?> cls, Set<Field> set, Set<Object> set2) {
    }

    public void process(Class<?> cls, Object obj) {
        processIndependentAnnotations(obj.getClass(), obj);
        injectMocks(obj);
    }

    private void processIndependentAnnotations(Class<?> cls, Object obj) {
        while (true) {
            Class<? super Object> cls2 = cls;
            if (cls2 != Object.class) {
                this.delegate.process(cls2, obj);
                this.spyAnnotationEngine.process(cls2, obj);
                cls2 = cls2.getSuperclass();
            } else {
                return;
            }
        }
    }

    public void injectMocks(Object obj) {
        HashSet hashSet = new HashSet();
        Set<Object> newMockSafeHashSet = Sets.newMockSafeHashSet(new Object[0]);
        for (Class cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            new InjectMocksScanner(cls).addTo(hashSet);
            new MockScanner(obj, cls).addPreparedMocks(newMockSafeHashSet);
            onInjection(obj, cls, hashSet, newMockSafeHashSet);
        }
        new DefaultInjectionEngine().injectMocksOnFields(hashSet, newMockSafeHashSet, obj);
    }
}

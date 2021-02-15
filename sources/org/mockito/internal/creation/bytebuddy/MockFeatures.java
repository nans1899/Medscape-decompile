package org.mockito.internal.creation.bytebuddy;

import java.util.Collections;
import java.util.Set;
import org.mockito.mock.SerializableMode;

class MockFeatures<T> {
    final Set<Class<?>> interfaces;
    final Class<T> mockedType;
    final SerializableMode serializableMode;
    final boolean stripAnnotations;

    private MockFeatures(Class<T> cls, Set<Class<?>> set, SerializableMode serializableMode2, boolean z) {
        this.mockedType = cls;
        this.interfaces = Collections.unmodifiableSet(set);
        this.serializableMode = serializableMode2;
        this.stripAnnotations = z;
    }

    public static <T> MockFeatures<T> withMockFeatures(Class<T> cls, Set<Class<?>> set, SerializableMode serializableMode2, boolean z) {
        return new MockFeatures<>(cls, set, serializableMode2, z);
    }
}

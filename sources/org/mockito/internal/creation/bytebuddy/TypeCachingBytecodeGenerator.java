package org.mockito.internal.creation.bytebuddy;

import java.lang.ref.ReferenceQueue;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.Callable;
import net.bytebuddy.TypeCache;
import org.mockito.mock.SerializableMode;

class TypeCachingBytecodeGenerator extends ReferenceQueue<ClassLoader> implements BytecodeGenerator {
    private final Object BOOTSTRAP_LOCK = new Object();
    /* access modifiers changed from: private */
    public final BytecodeGenerator bytecodeGenerator;
    private final TypeCache<MockitoMockKey> typeCache;

    public TypeCachingBytecodeGenerator(BytecodeGenerator bytecodeGenerator2, boolean z) {
        this.bytecodeGenerator = bytecodeGenerator2;
        this.typeCache = new TypeCache.WithInlineExpunction(z ? TypeCache.Sort.WEAK : TypeCache.Sort.SOFT);
    }

    public <T> Class<T> mockClass(final MockFeatures<T> mockFeatures) {
        try {
            return this.typeCache.findOrInsert(mockFeatures.mockedType.getClassLoader(), new MockitoMockKey(mockFeatures.mockedType, mockFeatures.interfaces, mockFeatures.serializableMode, mockFeatures.stripAnnotations), new Callable<Class<?>>() {
                public Class<?> call() throws Exception {
                    return TypeCachingBytecodeGenerator.this.bytecodeGenerator.mockClass(mockFeatures);
                }
            }, this.BOOTSTRAP_LOCK);
        } catch (IllegalArgumentException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw e;
        }
    }

    private static class MockitoMockKey extends TypeCache.SimpleKey {
        private final SerializableMode serializableMode;
        private final boolean stripAnnotations;

        private MockitoMockKey(Class<?> cls, Set<Class<?>> set, SerializableMode serializableMode2, boolean z) {
            super(cls, (Collection<? extends Class<?>>) set);
            this.serializableMode = serializableMode2;
            this.stripAnnotations = z;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                return false;
            }
            MockitoMockKey mockitoMockKey = (MockitoMockKey) obj;
            if (this.stripAnnotations != mockitoMockKey.stripAnnotations || !this.serializableMode.equals(mockitoMockKey.serializableMode)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((super.hashCode() * 31) + (this.stripAnnotations ? 1 : 0)) * 31) + this.serializableMode.hashCode();
        }
    }
}

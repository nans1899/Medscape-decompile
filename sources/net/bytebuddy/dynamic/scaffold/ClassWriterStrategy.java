package net.bytebuddy.dynamic.scaffold;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.pool.TypePool;

public interface ClassWriterStrategy {
    ClassWriter resolve(int i, TypePool typePool);

    ClassWriter resolve(int i, TypePool typePool, ClassReader classReader);

    public enum Default implements ClassWriterStrategy {
        CONSTANT_POOL_RETAINING {
            public ClassWriter resolve(int i, TypePool typePool, ClassReader classReader) {
                return new FrameComputingClassWriter(classReader, i, typePool);
            }
        },
        CONSTANT_POOL_DISCARDING {
            public ClassWriter resolve(int i, TypePool typePool, ClassReader classReader) {
                return resolve(i, typePool);
            }
        };

        public ClassWriter resolve(int i, TypePool typePool) {
            return new FrameComputingClassWriter(i, typePool);
        }
    }

    public static class FrameComputingClassWriter extends ClassWriter {
        private final TypePool typePool;

        public FrameComputingClassWriter(int i, TypePool typePool2) {
            super(i);
            this.typePool = typePool2;
        }

        public FrameComputingClassWriter(ClassReader classReader, int i, TypePool typePool2) {
            super(classReader, i);
            this.typePool = typePool2;
        }

        /* access modifiers changed from: protected */
        public String getCommonSuperClass(String str, String str2) {
            TypeDescription resolve = this.typePool.describe(str.replace('/', '.')).resolve();
            TypeDescription resolve2 = this.typePool.describe(str2.replace('/', '.')).resolve();
            if (resolve.isAssignableFrom(resolve2)) {
                return resolve.getInternalName();
            }
            if (resolve.isAssignableTo(resolve2)) {
                return resolve2.getInternalName();
            }
            if (resolve.isInterface() || resolve2.isInterface()) {
                return TypeDescription.OBJECT.getInternalName();
            }
            do {
                resolve = resolve.getSuperClass().asErasure();
            } while (!resolve.isAssignableFrom(resolve2));
            return resolve.getInternalName();
        }
    }
}

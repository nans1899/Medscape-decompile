package net.bytebuddy.dynamic.scaffold;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;

public interface TypeInitializer extends ByteCodeAppender {
    TypeInitializer expandWith(ByteCodeAppender byteCodeAppender);

    boolean isDefined();

    TypeWriter.MethodPool.Record wrap(TypeWriter.MethodPool.Record record);

    public interface Drain {
        void apply(ClassVisitor classVisitor, TypeInitializer typeInitializer, Implementation.Context context);

        @HashCodeAndEqualsPlugin.Enhance
        public static class Default implements Drain {
            protected final AnnotationValueFilter.Factory annotationValueFilterFactory;
            protected final TypeDescription instrumentedType;
            protected final TypeWriter.MethodPool methodPool;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Default defaultR = (Default) obj;
                return this.instrumentedType.equals(defaultR.instrumentedType) && this.methodPool.equals(defaultR.methodPool) && this.annotationValueFilterFactory.equals(defaultR.annotationValueFilterFactory);
            }

            public int hashCode() {
                return ((((527 + this.instrumentedType.hashCode()) * 31) + this.methodPool.hashCode()) * 31) + this.annotationValueFilterFactory.hashCode();
            }

            public Default(TypeDescription typeDescription, TypeWriter.MethodPool methodPool2, AnnotationValueFilter.Factory factory) {
                this.instrumentedType = typeDescription;
                this.methodPool = methodPool2;
                this.annotationValueFilterFactory = factory;
            }

            public void apply(ClassVisitor classVisitor, TypeInitializer typeInitializer, Implementation.Context context) {
                typeInitializer.wrap(this.methodPool.target(new MethodDescription.Latent.TypeInitializer(this.instrumentedType))).apply(classVisitor, context, this.annotationValueFilterFactory);
            }
        }
    }

    public enum None implements TypeInitializer {
        INSTANCE;

        public boolean isDefined() {
            return false;
        }

        public TypeWriter.MethodPool.Record wrap(TypeWriter.MethodPool.Record record) {
            return record;
        }

        public TypeInitializer expandWith(ByteCodeAppender byteCodeAppender) {
            return new Simple(byteCodeAppender);
        }

        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            return new ByteCodeAppender.Size(0, 0);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Simple implements TypeInitializer {
        private final ByteCodeAppender byteCodeAppender;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.byteCodeAppender.equals(((Simple) obj).byteCodeAppender);
        }

        public int hashCode() {
            return 527 + this.byteCodeAppender.hashCode();
        }

        public boolean isDefined() {
            return true;
        }

        public Simple(ByteCodeAppender byteCodeAppender2) {
            this.byteCodeAppender = byteCodeAppender2;
        }

        public TypeInitializer expandWith(ByteCodeAppender byteCodeAppender2) {
            return new Simple(new ByteCodeAppender.Compound(this.byteCodeAppender, byteCodeAppender2));
        }

        public TypeWriter.MethodPool.Record wrap(TypeWriter.MethodPool.Record record) {
            return record.prepend(this.byteCodeAppender);
        }

        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            return this.byteCodeAppender.apply(methodVisitor, context, methodDescription);
        }
    }
}

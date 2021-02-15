package net.bytebuddy.implementation.bytecode.constant;

import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;

public enum ClassConstant implements StackManipulation {
    VOID(Void.class),
    BOOLEAN(Boolean.class),
    BYTE(Byte.class),
    SHORT(Short.class),
    CHARACTER(Character.class),
    INTEGER(Integer.class),
    LONG(Long.class),
    FLOAT(Float.class),
    DOUBLE(Double.class);
    
    private static final String CLASS_TYPE_INTERNAL_NAME = "Ljava/lang/Class;";
    private static final String PRIMITIVE_TYPE_FIELD = "TYPE";
    /* access modifiers changed from: private */
    public static final StackManipulation.Size SIZE = null;
    private final String fieldOwnerInternalName;

    public boolean isValid() {
        return true;
    }

    static {
        SIZE = StackSize.SINGLE.toIncreasingSize();
    }

    private ClassConstant(Class<?> cls) {
        this.fieldOwnerInternalName = Type.getInternalName(cls);
    }

    public static StackManipulation of(TypeDescription typeDescription) {
        if (!typeDescription.isPrimitive()) {
            return new ForReferenceType(typeDescription);
        }
        if (typeDescription.represents(Boolean.TYPE)) {
            return BOOLEAN;
        }
        if (typeDescription.represents(Byte.TYPE)) {
            return BYTE;
        }
        if (typeDescription.represents(Short.TYPE)) {
            return SHORT;
        }
        if (typeDescription.represents(Character.TYPE)) {
            return CHARACTER;
        }
        if (typeDescription.represents(Integer.TYPE)) {
            return INTEGER;
        }
        if (typeDescription.represents(Long.TYPE)) {
            return LONG;
        }
        if (typeDescription.represents(Float.TYPE)) {
            return FLOAT;
        }
        if (typeDescription.represents(Double.TYPE)) {
            return DOUBLE;
        }
        return VOID;
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitFieldInsn(178, this.fieldOwnerInternalName, PRIMITIVE_TYPE_FIELD, CLASS_TYPE_INTERNAL_NAME);
        return SIZE;
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class ForReferenceType implements StackManipulation {
        private final TypeDescription typeDescription;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForReferenceType) obj).typeDescription);
        }

        public int hashCode() {
            return 527 + this.typeDescription.hashCode();
        }

        public boolean isValid() {
            return true;
        }

        protected ForReferenceType(TypeDescription typeDescription2) {
            this.typeDescription = typeDescription2;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            if (!context.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V5) || !this.typeDescription.isVisibleTo(context.getInstrumentedType())) {
                methodVisitor.visitLdcInsn(this.typeDescription.getName());
                methodVisitor.visitMethodInsn(184, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME, "forName", "(Ljava/lang/String;)Ljava/lang/Class;", false);
            } else {
                methodVisitor.visitLdcInsn(Type.getType(this.typeDescription.getDescriptor()));
            }
            return ClassConstant.SIZE;
        }
    }
}

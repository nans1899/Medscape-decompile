package net.bytebuddy.implementation.bytecode.assign.primitive;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.jar.asm.MethodVisitor;

public enum PrimitiveUnboxingDelegate implements StackManipulation {
    BOOLEAN(Boolean.class, Boolean.TYPE, StackSize.ZERO, "booleanValue", "()Z"),
    BYTE(Byte.class, Byte.TYPE, StackSize.ZERO, "byteValue", "()B"),
    SHORT(Short.class, Short.TYPE, StackSize.ZERO, "shortValue", "()S"),
    CHARACTER(Character.class, Character.TYPE, StackSize.ZERO, "charValue", "()C"),
    INTEGER(Integer.class, Integer.TYPE, StackSize.ZERO, "intValue", "()I"),
    LONG(Long.class, Long.TYPE, StackSize.SINGLE, "longValue", "()J"),
    FLOAT(Float.class, Float.TYPE, StackSize.ZERO, "floatValue", "()F"),
    DOUBLE(Double.class, Double.TYPE, StackSize.SINGLE, "doubleValue", "()D");
    
    /* access modifiers changed from: private */
    public final TypeDescription primitiveType;
    private final StackManipulation.Size size;
    private final String unboxingMethodDescriptor;
    private final String unboxingMethodName;
    private final TypeDescription wrapperType;

    public interface UnboxingResponsible {
        StackManipulation assignUnboxedTo(TypeDescription.Generic generic, Assigner assigner, Assigner.Typing typing);
    }

    public boolean isValid() {
        return true;
    }

    private PrimitiveUnboxingDelegate(Class<?> cls, Class<?> cls2, StackSize stackSize, String str, String str2) {
        this.size = stackSize.toIncreasingSize();
        this.wrapperType = TypeDescription.ForLoadedType.of(cls);
        this.primitiveType = TypeDescription.ForLoadedType.of(cls2);
        this.unboxingMethodName = str;
        this.unboxingMethodDescriptor = str2;
    }

    protected static PrimitiveUnboxingDelegate forPrimitive(TypeDescription.Generic generic) {
        if (generic.represents(Boolean.TYPE)) {
            return BOOLEAN;
        }
        if (generic.represents(Byte.TYPE)) {
            return BYTE;
        }
        if (generic.represents(Short.TYPE)) {
            return SHORT;
        }
        if (generic.represents(Character.TYPE)) {
            return CHARACTER;
        }
        if (generic.represents(Integer.TYPE)) {
            return INTEGER;
        }
        if (generic.represents(Long.TYPE)) {
            return LONG;
        }
        if (generic.represents(Float.TYPE)) {
            return FLOAT;
        }
        if (generic.represents(Double.TYPE)) {
            return DOUBLE;
        }
        throw new IllegalArgumentException("Expected non-void primitive type instead of " + generic);
    }

    public static UnboxingResponsible forReferenceType(TypeDefinition typeDefinition) {
        if (typeDefinition.isPrimitive()) {
            throw new IllegalArgumentException("Expected reference type instead of " + typeDefinition);
        } else if (typeDefinition.represents(Boolean.class)) {
            return ExplicitlyTypedUnboxingResponsible.BOOLEAN;
        } else {
            if (typeDefinition.represents(Byte.class)) {
                return ExplicitlyTypedUnboxingResponsible.BYTE;
            }
            if (typeDefinition.represents(Short.class)) {
                return ExplicitlyTypedUnboxingResponsible.SHORT;
            }
            if (typeDefinition.represents(Character.class)) {
                return ExplicitlyTypedUnboxingResponsible.CHARACTER;
            }
            if (typeDefinition.represents(Integer.class)) {
                return ExplicitlyTypedUnboxingResponsible.INTEGER;
            }
            if (typeDefinition.represents(Long.class)) {
                return ExplicitlyTypedUnboxingResponsible.LONG;
            }
            if (typeDefinition.represents(Float.class)) {
                return ExplicitlyTypedUnboxingResponsible.FLOAT;
            }
            if (typeDefinition.represents(Double.class)) {
                return ExplicitlyTypedUnboxingResponsible.DOUBLE;
            }
            return new ImplicitlyTypedUnboxingResponsible(typeDefinition.asGenericType());
        }
    }

    /* access modifiers changed from: protected */
    public TypeDescription.Generic getWrapperType() {
        return this.wrapperType.asGenericType();
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitMethodInsn(182, this.wrapperType.asErasure().getInternalName(), this.unboxingMethodName, this.unboxingMethodDescriptor, false);
        return this.size;
    }

    protected enum ExplicitlyTypedUnboxingResponsible implements UnboxingResponsible {
        BOOLEAN(PrimitiveUnboxingDelegate.BOOLEAN),
        BYTE(PrimitiveUnboxingDelegate.BYTE),
        SHORT(PrimitiveUnboxingDelegate.SHORT),
        CHARACTER(PrimitiveUnboxingDelegate.CHARACTER),
        INTEGER(PrimitiveUnboxingDelegate.INTEGER),
        LONG(PrimitiveUnboxingDelegate.LONG),
        FLOAT(PrimitiveUnboxingDelegate.FLOAT),
        DOUBLE(PrimitiveUnboxingDelegate.DOUBLE);
        
        private final PrimitiveUnboxingDelegate primitiveUnboxingDelegate;

        private ExplicitlyTypedUnboxingResponsible(PrimitiveUnboxingDelegate primitiveUnboxingDelegate2) {
            this.primitiveUnboxingDelegate = primitiveUnboxingDelegate2;
        }

        public StackManipulation assignUnboxedTo(TypeDescription.Generic generic, Assigner assigner, Assigner.Typing typing) {
            PrimitiveUnboxingDelegate primitiveUnboxingDelegate2 = this.primitiveUnboxingDelegate;
            return new StackManipulation.Compound(primitiveUnboxingDelegate2, PrimitiveWideningDelegate.forPrimitive(primitiveUnboxingDelegate2.primitiveType).widenTo(generic));
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class ImplicitlyTypedUnboxingResponsible implements UnboxingResponsible {
        private final TypeDescription.Generic originalType;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.originalType.equals(((ImplicitlyTypedUnboxingResponsible) obj).originalType);
        }

        public int hashCode() {
            return 527 + this.originalType.hashCode();
        }

        protected ImplicitlyTypedUnboxingResponsible(TypeDescription.Generic generic) {
            this.originalType = generic;
        }

        public StackManipulation assignUnboxedTo(TypeDescription.Generic generic, Assigner assigner, Assigner.Typing typing) {
            PrimitiveUnboxingDelegate forPrimitive = PrimitiveUnboxingDelegate.forPrimitive(generic);
            return new StackManipulation.Compound(assigner.assign(this.originalType, forPrimitive.getWrapperType(), typing), forPrimitive);
        }
    }
}

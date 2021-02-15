package net.bytebuddy.implementation.bytecode.constant;

import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;

public enum DefaultValue implements StackManipulation {
    INTEGER(IntegerConstant.ZERO),
    LONG(LongConstant.ZERO),
    FLOAT(FloatConstant.ZERO),
    DOUBLE(DoubleConstant.ZERO),
    VOID(StackManipulation.Trivial.INSTANCE),
    REFERENCE(NullConstant.INSTANCE);
    
    private final StackManipulation stackManipulation;

    private DefaultValue(StackManipulation stackManipulation2) {
        this.stackManipulation = stackManipulation2;
    }

    public static StackManipulation of(TypeDefinition typeDefinition) {
        if (!typeDefinition.isPrimitive()) {
            return REFERENCE;
        }
        if (typeDefinition.represents(Long.TYPE)) {
            return LONG;
        }
        if (typeDefinition.represents(Double.TYPE)) {
            return DOUBLE;
        }
        if (typeDefinition.represents(Float.TYPE)) {
            return FLOAT;
        }
        if (typeDefinition.represents(Void.TYPE)) {
            return VOID;
        }
        return INTEGER;
    }

    public boolean isValid() {
        return this.stackManipulation.isValid();
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        return this.stackManipulation.apply(methodVisitor, context);
    }
}

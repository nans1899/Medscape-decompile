package net.bytebuddy.implementation.bytecode.member;

import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;

public enum MethodReturn implements StackManipulation {
    INTEGER(172, StackSize.SINGLE),
    DOUBLE(175, StackSize.DOUBLE),
    FLOAT(174, StackSize.SINGLE),
    LONG(173, StackSize.DOUBLE),
    VOID(177, StackSize.ZERO),
    REFERENCE(176, StackSize.SINGLE);
    
    private final int returnOpcode;
    private final StackManipulation.Size size;

    public boolean isValid() {
        return true;
    }

    private MethodReturn(int i, StackSize stackSize) {
        this.returnOpcode = i;
        this.size = stackSize.toDecreasingSize();
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

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.returnOpcode);
        return this.size;
    }
}

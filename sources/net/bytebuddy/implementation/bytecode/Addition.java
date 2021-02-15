package net.bytebuddy.implementation.bytecode;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;

public enum Addition implements StackManipulation {
    INTEGER(96, StackSize.SINGLE),
    LONG(97, StackSize.DOUBLE),
    FLOAT(98, StackSize.SINGLE),
    DOUBLE(99, StackSize.DOUBLE);
    
    private final int opcode;
    private final StackSize stackSize;

    public boolean isValid() {
        return true;
    }

    private Addition(int i, StackSize stackSize2) {
        this.opcode = i;
        this.stackSize = stackSize2;
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.opcode);
        return this.stackSize.toDecreasingSize();
    }
}

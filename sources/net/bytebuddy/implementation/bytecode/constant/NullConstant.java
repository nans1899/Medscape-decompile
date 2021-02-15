package net.bytebuddy.implementation.bytecode.constant;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;

public enum NullConstant implements StackManipulation {
    INSTANCE(StackSize.SINGLE);
    
    private final StackManipulation.Size size;

    public boolean isValid() {
        return true;
    }

    private NullConstant(StackSize stackSize) {
        this.size = stackSize.toIncreasingSize();
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(1);
        return this.size;
    }
}

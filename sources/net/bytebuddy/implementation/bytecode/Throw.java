package net.bytebuddy.implementation.bytecode;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;

public enum Throw implements StackManipulation {
    INSTANCE;

    public boolean isValid() {
        return true;
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(191);
        return StackSize.SINGLE.toDecreasingSize();
    }
}

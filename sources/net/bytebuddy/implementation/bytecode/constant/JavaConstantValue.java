package net.bytebuddy.implementation.bytecode.constant;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.utility.JavaConstant;

@HashCodeAndEqualsPlugin.Enhance
public class JavaConstantValue implements StackManipulation {
    private final JavaConstant javaConstant;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.javaConstant.equals(((JavaConstantValue) obj).javaConstant);
    }

    public int hashCode() {
        return 527 + this.javaConstant.hashCode();
    }

    public boolean isValid() {
        return true;
    }

    public JavaConstantValue(JavaConstant javaConstant2) {
        this.javaConstant = javaConstant2;
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitLdcInsn(this.javaConstant.asConstantPoolValue());
        return StackSize.SINGLE.toIncreasingSize();
    }
}

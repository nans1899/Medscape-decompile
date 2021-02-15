package net.bytebuddy.implementation.bytecode.member;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.utility.JavaConstant;

@HashCodeAndEqualsPlugin.Enhance
public class HandleInvocation implements StackManipulation {
    private static final String INVOKE_EXACT = "invokeExact";
    private static final String METHOD_HANDLE_NAME = "java/lang/invoke/MethodHandle";
    private final JavaConstant.MethodType methodType;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.methodType.equals(((HandleInvocation) obj).methodType);
    }

    public int hashCode() {
        return 527 + this.methodType.hashCode();
    }

    public boolean isValid() {
        return true;
    }

    public HandleInvocation(JavaConstant.MethodType methodType2) {
        this.methodType = methodType2;
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitMethodInsn(182, METHOD_HANDLE_NAME, INVOKE_EXACT, this.methodType.getDescriptor(), false);
        int size = this.methodType.getReturnType().getStackSize().getSize() - this.methodType.getParameterTypes().getStackSize();
        return new StackManipulation.Size(size, Math.max(size, 0));
    }
}

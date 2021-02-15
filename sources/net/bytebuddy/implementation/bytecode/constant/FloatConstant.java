package net.bytebuddy.implementation.bytecode.constant;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;

public enum FloatConstant implements StackManipulation {
    ZERO(11),
    ONE(12),
    TWO(13);
    
    /* access modifiers changed from: private */
    public static final StackManipulation.Size SIZE = null;
    private final int opcode;

    public boolean isValid() {
        return true;
    }

    static {
        SIZE = StackSize.SINGLE.toIncreasingSize();
    }

    private FloatConstant(int i) {
        this.opcode = i;
    }

    public static StackManipulation forValue(float f) {
        if (f == 0.0f) {
            return ZERO;
        }
        if (f == 1.0f) {
            return ONE;
        }
        if (f == 2.0f) {
            return TWO;
        }
        return new ConstantPool(f);
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.opcode);
        return SIZE;
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class ConstantPool implements StackManipulation {
        private final float value;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.value == ((ConstantPool) obj).value;
        }

        public int hashCode() {
            return 527 + Float.floatToIntBits(this.value);
        }

        public boolean isValid() {
            return true;
        }

        protected ConstantPool(float f) {
            this.value = f;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitLdcInsn(Float.valueOf(this.value));
            return FloatConstant.SIZE;
        }
    }
}

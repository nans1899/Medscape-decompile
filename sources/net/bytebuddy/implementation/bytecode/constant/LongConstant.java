package net.bytebuddy.implementation.bytecode.constant;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;

public enum LongConstant implements StackManipulation {
    ZERO(9),
    ONE(10);
    
    /* access modifiers changed from: private */
    public static final StackManipulation.Size SIZE = null;
    private final int opcode;

    public boolean isValid() {
        return true;
    }

    static {
        SIZE = StackSize.DOUBLE.toIncreasingSize();
    }

    private LongConstant(int i) {
        this.opcode = i;
    }

    public static StackManipulation forValue(long j) {
        if (j == 0) {
            return ZERO;
        }
        if (j == 1) {
            return ONE;
        }
        return new ConstantPool(j);
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.opcode);
        return SIZE;
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class ConstantPool implements StackManipulation {
        private final long value;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.value == ((ConstantPool) obj).value;
        }

        public int hashCode() {
            long j = this.value;
            return 527 + ((int) (j ^ (j >>> 32)));
        }

        public boolean isValid() {
            return true;
        }

        protected ConstantPool(long j) {
            this.value = j;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitLdcInsn(Long.valueOf(this.value));
            return LongConstant.SIZE;
        }
    }
}

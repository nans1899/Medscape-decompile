package net.bytebuddy.implementation.bytecode.constant;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;

public enum DoubleConstant implements StackManipulation {
    ZERO(14),
    ONE(15);
    
    /* access modifiers changed from: private */
    public static final StackManipulation.Size SIZE = null;
    private final int opcode;

    public boolean isValid() {
        return true;
    }

    static {
        SIZE = StackSize.DOUBLE.toIncreasingSize();
    }

    private DoubleConstant(int i) {
        this.opcode = i;
    }

    public static StackManipulation forValue(double d) {
        if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return ZERO;
        }
        if (d == 1.0d) {
            return ONE;
        }
        return new ConstantPool(d);
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.opcode);
        return SIZE;
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class ConstantPool implements StackManipulation {
        private final double value;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.value == ((ConstantPool) obj).value;
        }

        public int hashCode() {
            long doubleToLongBits = Double.doubleToLongBits(this.value);
            return 527 + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        }

        public boolean isValid() {
            return true;
        }

        protected ConstantPool(double d) {
            this.value = d;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitLdcInsn(Double.valueOf(this.value));
            return DoubleConstant.SIZE;
        }
    }
}

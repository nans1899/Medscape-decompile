package net.bytebuddy.implementation.bytecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.MethodVisitor;

public interface StackManipulation {
    Size apply(MethodVisitor methodVisitor, Implementation.Context context);

    boolean isValid();

    public enum Illegal implements StackManipulation {
        INSTANCE;

        public boolean isValid() {
            return false;
        }

        public Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            throw new IllegalStateException("An illegal stack manipulation must not be applied");
        }
    }

    public enum Trivial implements StackManipulation {
        INSTANCE;

        public boolean isValid() {
            return true;
        }

        public Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return StackSize.ZERO.toIncreasingSize();
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Size {
        private final int maximalSize;
        private final int sizeImpact;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Size size = (Size) obj;
            return this.sizeImpact == size.sizeImpact && this.maximalSize == size.maximalSize;
        }

        public int hashCode() {
            return ((527 + this.sizeImpact) * 31) + this.maximalSize;
        }

        public Size(int i, int i2) {
            this.sizeImpact = i;
            this.maximalSize = i2;
        }

        public int getSizeImpact() {
            return this.sizeImpact;
        }

        public int getMaximalSize() {
            return this.maximalSize;
        }

        public Size aggregate(Size size) {
            return aggregate(size.sizeImpact, size.maximalSize);
        }

        private Size aggregate(int i, int i2) {
            int i3 = this.sizeImpact;
            return new Size(i + i3, Math.max(this.maximalSize, i3 + i2));
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Compound implements StackManipulation {
        private final List<StackManipulation> stackManipulations;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.stackManipulations.equals(((Compound) obj).stackManipulations);
        }

        public int hashCode() {
            return 527 + this.stackManipulations.hashCode();
        }

        public Compound(StackManipulation... stackManipulationArr) {
            this((List<? extends StackManipulation>) Arrays.asList(stackManipulationArr));
        }

        public Compound(List<? extends StackManipulation> list) {
            this.stackManipulations = new ArrayList();
            for (StackManipulation stackManipulation : list) {
                if (stackManipulation instanceof Compound) {
                    this.stackManipulations.addAll(((Compound) stackManipulation).stackManipulations);
                } else if (!(stackManipulation instanceof Trivial)) {
                    this.stackManipulations.add(stackManipulation);
                }
            }
        }

        public boolean isValid() {
            for (StackManipulation isValid : this.stackManipulations) {
                if (!isValid.isValid()) {
                    return false;
                }
            }
            return true;
        }

        public Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            Size size = new Size(0, 0);
            for (StackManipulation apply : this.stackManipulations) {
                size = size.aggregate(apply.apply(methodVisitor, context));
            }
            return size;
        }
    }
}

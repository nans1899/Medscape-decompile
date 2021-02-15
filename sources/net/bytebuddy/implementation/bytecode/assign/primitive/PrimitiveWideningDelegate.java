package net.bytebuddy.implementation.bytecode.assign.primitive;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;

public enum PrimitiveWideningDelegate {
    BOOLEAN(StackManipulation.Trivial.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE),
    BYTE(StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, StackManipulation.Trivial.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, new WideningStackManipulation(133, StackSize.SINGLE.toIncreasingSize()), new WideningStackManipulation(134, StackSize.ZERO.toIncreasingSize()), new WideningStackManipulation(133, StackSize.SINGLE.toIncreasingSize())),
    SHORT(StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, new WideningStackManipulation(133, StackSize.SINGLE.toIncreasingSize()), new WideningStackManipulation(134, StackSize.ZERO.toIncreasingSize()), new WideningStackManipulation(135, StackSize.SINGLE.toIncreasingSize())),
    CHARACTER(StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, StackManipulation.Trivial.INSTANCE, new WideningStackManipulation(133, StackSize.SINGLE.toIncreasingSize()), new WideningStackManipulation(134, StackSize.ZERO.toIncreasingSize()), new WideningStackManipulation(135, StackSize.SINGLE.toIncreasingSize())),
    INTEGER(StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, new WideningStackManipulation(133, StackSize.SINGLE.toIncreasingSize()), new WideningStackManipulation(134, StackSize.ZERO.toIncreasingSize()), new WideningStackManipulation(135, StackSize.SINGLE.toIncreasingSize())),
    LONG(StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, new WideningStackManipulation(137, StackSize.SINGLE.toDecreasingSize()), new WideningStackManipulation(138, StackSize.ZERO.toIncreasingSize())),
    FLOAT(StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, new WideningStackManipulation(141, StackSize.SINGLE.toIncreasingSize())),
    DOUBLE(StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE);
    
    private final StackManipulation toBooleanStackManipulation;
    private final StackManipulation toByteStackManipulation;
    private final StackManipulation toCharacterStackManipulation;
    private final StackManipulation toDoubleStackManipulation;
    private final StackManipulation toFloatStackManipulation;
    private final StackManipulation toIntegerStackManipulation;
    private final StackManipulation toLongStackManipulation;
    private final StackManipulation toShortStackManipulation;

    private PrimitiveWideningDelegate(StackManipulation stackManipulation, StackManipulation stackManipulation2, StackManipulation stackManipulation3, StackManipulation stackManipulation4, StackManipulation stackManipulation5, StackManipulation stackManipulation6, StackManipulation stackManipulation7, StackManipulation stackManipulation8) {
        this.toBooleanStackManipulation = stackManipulation;
        this.toByteStackManipulation = stackManipulation2;
        this.toShortStackManipulation = stackManipulation3;
        this.toCharacterStackManipulation = stackManipulation4;
        this.toIntegerStackManipulation = stackManipulation5;
        this.toLongStackManipulation = stackManipulation6;
        this.toFloatStackManipulation = stackManipulation7;
        this.toDoubleStackManipulation = stackManipulation8;
    }

    public static PrimitiveWideningDelegate forPrimitive(TypeDefinition typeDefinition) {
        if (typeDefinition.represents(Boolean.TYPE)) {
            return BOOLEAN;
        }
        if (typeDefinition.represents(Byte.TYPE)) {
            return BYTE;
        }
        if (typeDefinition.represents(Short.TYPE)) {
            return SHORT;
        }
        if (typeDefinition.represents(Character.TYPE)) {
            return CHARACTER;
        }
        if (typeDefinition.represents(Integer.TYPE)) {
            return INTEGER;
        }
        if (typeDefinition.represents(Long.TYPE)) {
            return LONG;
        }
        if (typeDefinition.represents(Float.TYPE)) {
            return FLOAT;
        }
        if (typeDefinition.represents(Double.TYPE)) {
            return DOUBLE;
        }
        throw new IllegalArgumentException("Not a primitive, non-void type: " + typeDefinition);
    }

    public StackManipulation widenTo(TypeDefinition typeDefinition) {
        if (typeDefinition.represents(Boolean.TYPE)) {
            return this.toBooleanStackManipulation;
        }
        if (typeDefinition.represents(Byte.TYPE)) {
            return this.toByteStackManipulation;
        }
        if (typeDefinition.represents(Short.TYPE)) {
            return this.toShortStackManipulation;
        }
        if (typeDefinition.represents(Character.TYPE)) {
            return this.toCharacterStackManipulation;
        }
        if (typeDefinition.represents(Integer.TYPE)) {
            return this.toIntegerStackManipulation;
        }
        if (typeDefinition.represents(Long.TYPE)) {
            return this.toLongStackManipulation;
        }
        if (typeDefinition.represents(Float.TYPE)) {
            return this.toFloatStackManipulation;
        }
        if (typeDefinition.represents(Double.TYPE)) {
            return this.toDoubleStackManipulation;
        }
        throw new IllegalArgumentException("Not a primitive non-void type: " + typeDefinition);
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class WideningStackManipulation implements StackManipulation {
        private final int conversionOpcode;
        private final StackManipulation.Size size;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
            return this.conversionOpcode == wideningStackManipulation.conversionOpcode && this.size.equals(wideningStackManipulation.size);
        }

        public int hashCode() {
            return ((527 + this.conversionOpcode) * 31) + this.size.hashCode();
        }

        public boolean isValid() {
            return true;
        }

        protected WideningStackManipulation(int i, StackManipulation.Size size2) {
            this.conversionOpcode = i;
            this.size = size2;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitInsn(this.conversionOpcode);
            return this.size;
        }
    }
}

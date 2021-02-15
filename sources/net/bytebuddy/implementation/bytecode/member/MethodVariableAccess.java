package net.bytebuddy.implementation.bytecode.member;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.jar.asm.MethodVisitor;

public enum MethodVariableAccess {
    INTEGER(21, 54, StackSize.SINGLE),
    LONG(22, 55, StackSize.DOUBLE),
    FLOAT(23, 56, StackSize.SINGLE),
    DOUBLE(24, 57, StackSize.DOUBLE),
    REFERENCE(25, 58, StackSize.SINGLE);
    
    /* access modifiers changed from: private */
    public final int loadOpcode;
    /* access modifiers changed from: private */
    public final StackSize size;
    /* access modifiers changed from: private */
    public final int storeOpcode;

    private MethodVariableAccess(int i, int i2, StackSize stackSize) {
        this.loadOpcode = i;
        this.size = stackSize;
        this.storeOpcode = i2;
    }

    public static MethodVariableAccess of(TypeDefinition typeDefinition) {
        if (!typeDefinition.isPrimitive()) {
            return REFERENCE;
        }
        if (typeDefinition.represents(Long.TYPE)) {
            return LONG;
        }
        if (typeDefinition.represents(Double.TYPE)) {
            return DOUBLE;
        }
        if (typeDefinition.represents(Float.TYPE)) {
            return FLOAT;
        }
        if (!typeDefinition.represents(Void.TYPE)) {
            return INTEGER;
        }
        throw new IllegalArgumentException("Variable type cannot be void");
    }

    public static MethodLoading allArgumentsOf(MethodDescription methodDescription) {
        return new MethodLoading(methodDescription, MethodLoading.TypeCastingHandler.NoOp.INSTANCE);
    }

    public static StackManipulation loadThis() {
        return REFERENCE.loadFrom(0);
    }

    public StackManipulation loadFrom(int i) {
        return new OffsetLoading(i);
    }

    public StackManipulation storeAt(int i) {
        return new OffsetWriting(i);
    }

    public StackManipulation increment(int i, int i2) {
        if (this == INTEGER) {
            return new OffsetIncrementing(i, i2);
        }
        throw new IllegalStateException("Cannot increment type: " + this);
    }

    public static StackManipulation load(ParameterDescription parameterDescription) {
        return of(parameterDescription.getType()).loadFrom(parameterDescription.getOffset());
    }

    public static StackManipulation store(ParameterDescription parameterDescription) {
        return of(parameterDescription.getType()).storeAt(parameterDescription.getOffset());
    }

    public static StackManipulation increment(ParameterDescription parameterDescription, int i) {
        return of(parameterDescription.getType()).increment(parameterDescription.getOffset(), i);
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class MethodLoading implements StackManipulation {
        private final MethodDescription methodDescription;
        private final TypeCastingHandler typeCastingHandler;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            MethodLoading methodLoading = (MethodLoading) obj;
            return this.methodDescription.equals(methodLoading.methodDescription) && this.typeCastingHandler.equals(methodLoading.typeCastingHandler);
        }

        public int hashCode() {
            return ((527 + this.methodDescription.hashCode()) * 31) + this.typeCastingHandler.hashCode();
        }

        public boolean isValid() {
            return true;
        }

        protected MethodLoading(MethodDescription methodDescription2, TypeCastingHandler typeCastingHandler2) {
            this.methodDescription = methodDescription2;
            this.typeCastingHandler = typeCastingHandler2;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            ArrayList arrayList = new ArrayList();
            Iterator it = this.methodDescription.getParameters().iterator();
            while (it.hasNext()) {
                ParameterDescription parameterDescription = (ParameterDescription) it.next();
                TypeDescription asErasure = parameterDescription.getType().asErasure();
                arrayList.add(MethodVariableAccess.of(asErasure).loadFrom(parameterDescription.getOffset()));
                arrayList.add(this.typeCastingHandler.ofIndex(asErasure, parameterDescription.getIndex()));
            }
            return new StackManipulation.Compound((List<? extends StackManipulation>) arrayList).apply(methodVisitor, context);
        }

        public StackManipulation prependThisReference() {
            if (this.methodDescription.isStatic()) {
                return this;
            }
            return new StackManipulation.Compound(MethodVariableAccess.loadThis(), this);
        }

        public MethodLoading asBridgeOf(MethodDescription methodDescription2) {
            return new MethodLoading(this.methodDescription, new TypeCastingHandler.ForBridgeTarget(methodDescription2));
        }

        protected interface TypeCastingHandler {
            StackManipulation ofIndex(TypeDescription typeDescription, int i);

            public enum NoOp implements TypeCastingHandler {
                INSTANCE;

                public StackManipulation ofIndex(TypeDescription typeDescription, int i) {
                    return StackManipulation.Trivial.INSTANCE;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForBridgeTarget implements TypeCastingHandler {
                private final MethodDescription bridgeTarget;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.bridgeTarget.equals(((ForBridgeTarget) obj).bridgeTarget);
                }

                public int hashCode() {
                    return 527 + this.bridgeTarget.hashCode();
                }

                public ForBridgeTarget(MethodDescription methodDescription) {
                    this.bridgeTarget = methodDescription;
                }

                public StackManipulation ofIndex(TypeDescription typeDescription, int i) {
                    TypeDescription asErasure = ((ParameterDescription) this.bridgeTarget.getParameters().get(i)).getType().asErasure();
                    if (typeDescription.equals(asErasure)) {
                        return StackManipulation.Trivial.INSTANCE;
                    }
                    return TypeCasting.to(asErasure);
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    protected class OffsetLoading implements StackManipulation {
        private final int offset;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            OffsetLoading offsetLoading = (OffsetLoading) obj;
            return this.offset == offsetLoading.offset && MethodVariableAccess.this.equals(MethodVariableAccess.this);
        }

        public int hashCode() {
            return ((527 + this.offset) * 31) + MethodVariableAccess.this.hashCode();
        }

        public boolean isValid() {
            return true;
        }

        protected OffsetLoading(int i) {
            this.offset = i;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitVarInsn(MethodVariableAccess.this.loadOpcode, this.offset);
            return MethodVariableAccess.this.size.toIncreasingSize();
        }
    }

    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    protected class OffsetWriting implements StackManipulation {
        private final int offset;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            OffsetWriting offsetWriting = (OffsetWriting) obj;
            return this.offset == offsetWriting.offset && MethodVariableAccess.this.equals(MethodVariableAccess.this);
        }

        public int hashCode() {
            return ((527 + this.offset) * 31) + MethodVariableAccess.this.hashCode();
        }

        public boolean isValid() {
            return true;
        }

        protected OffsetWriting(int i) {
            this.offset = i;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitVarInsn(MethodVariableAccess.this.storeOpcode, this.offset);
            return MethodVariableAccess.this.size.toDecreasingSize();
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class OffsetIncrementing implements StackManipulation {
        private final int offset;
        private final int value;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            OffsetIncrementing offsetIncrementing = (OffsetIncrementing) obj;
            return this.offset == offsetIncrementing.offset && this.value == offsetIncrementing.value;
        }

        public int hashCode() {
            return ((527 + this.offset) * 31) + this.value;
        }

        public boolean isValid() {
            return true;
        }

        protected OffsetIncrementing(int i, int i2) {
            this.offset = i;
            this.value = i2;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitIincInsn(this.offset, this.value);
            return new StackManipulation.Size(0, 0);
        }
    }
}

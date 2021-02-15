package net.bytebuddy.implementation.bytecode.collection;

import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.jar.asm.MethodVisitor;

@HashCodeAndEqualsPlugin.Enhance
public class ArrayFactory implements CollectionFactory {
    /* access modifiers changed from: private */
    public final ArrayCreator arrayCreator;
    private final TypeDescription.Generic componentType;
    /* access modifiers changed from: private */
    @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
    public final StackManipulation.Size sizeDecrease;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ArrayFactory arrayFactory = (ArrayFactory) obj;
        return this.componentType.equals(arrayFactory.componentType) && this.arrayCreator.equals(arrayFactory.arrayCreator);
    }

    public int hashCode() {
        return ((527 + this.componentType.hashCode()) * 31) + this.arrayCreator.hashCode();
    }

    protected ArrayFactory(TypeDescription.Generic generic, ArrayCreator arrayCreator2) {
        this.componentType = generic;
        this.arrayCreator = arrayCreator2;
        this.sizeDecrease = StackSize.DOUBLE.toDecreasingSize().aggregate(generic.getStackSize().toDecreasingSize());
    }

    public static ArrayFactory forType(TypeDescription.Generic generic) {
        return new ArrayFactory(generic, makeArrayCreatorFor(generic));
    }

    private static ArrayCreator makeArrayCreatorFor(TypeDefinition typeDefinition) {
        if (!typeDefinition.isPrimitive()) {
            return new ArrayCreator.ForReferenceType(typeDefinition.asErasure());
        }
        if (typeDefinition.represents(Boolean.TYPE)) {
            return ArrayCreator.ForPrimitiveType.BOOLEAN;
        }
        if (typeDefinition.represents(Byte.TYPE)) {
            return ArrayCreator.ForPrimitiveType.BYTE;
        }
        if (typeDefinition.represents(Short.TYPE)) {
            return ArrayCreator.ForPrimitiveType.SHORT;
        }
        if (typeDefinition.represents(Character.TYPE)) {
            return ArrayCreator.ForPrimitiveType.CHARACTER;
        }
        if (typeDefinition.represents(Integer.TYPE)) {
            return ArrayCreator.ForPrimitiveType.INTEGER;
        }
        if (typeDefinition.represents(Long.TYPE)) {
            return ArrayCreator.ForPrimitiveType.LONG;
        }
        if (typeDefinition.represents(Float.TYPE)) {
            return ArrayCreator.ForPrimitiveType.FLOAT;
        }
        if (typeDefinition.represents(Double.TYPE)) {
            return ArrayCreator.ForPrimitiveType.DOUBLE;
        }
        throw new IllegalArgumentException("Cannot create array of type " + typeDefinition);
    }

    public StackManipulation withValues(List<? extends StackManipulation> list) {
        return new ArrayStackManipulation(list);
    }

    public TypeDescription.Generic getComponentType() {
        return this.componentType;
    }

    protected interface ArrayCreator extends StackManipulation {
        public static final StackManipulation.Size ARRAY_CREATION_SIZE_CHANGE = StackSize.ZERO.toDecreasingSize();

        int getStorageOpcode();

        public enum ForPrimitiveType implements ArrayCreator {
            BOOLEAN(4, 84),
            BYTE(8, 84),
            SHORT(9, 86),
            CHARACTER(5, 85),
            INTEGER(10, 79),
            LONG(11, 80),
            FLOAT(6, 81),
            DOUBLE(7, 82);
            
            private final int creationOpcode;
            private final int storageOpcode;

            public boolean isValid() {
                return true;
            }

            private ForPrimitiveType(int i, int i2) {
                this.creationOpcode = i;
                this.storageOpcode = i2;
            }

            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitIntInsn(188, this.creationOpcode);
                return ARRAY_CREATION_SIZE_CHANGE;
            }

            public int getStorageOpcode() {
                return this.storageOpcode;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForReferenceType implements ArrayCreator {
            private final String internalTypeName;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.internalTypeName.equals(((ForReferenceType) obj).internalTypeName);
            }

            public int getStorageOpcode() {
                return 83;
            }

            public int hashCode() {
                return 527 + this.internalTypeName.hashCode();
            }

            public boolean isValid() {
                return true;
            }

            protected ForReferenceType(TypeDescription typeDescription) {
                this.internalTypeName = typeDescription.getInternalName();
            }

            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitTypeInsn(189, this.internalTypeName);
                return ARRAY_CREATION_SIZE_CHANGE;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    protected class ArrayStackManipulation implements StackManipulation {
        private final List<? extends StackManipulation> stackManipulations;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ArrayStackManipulation arrayStackManipulation = (ArrayStackManipulation) obj;
            return this.stackManipulations.equals(arrayStackManipulation.stackManipulations) && ArrayFactory.this.equals(ArrayFactory.this);
        }

        public int hashCode() {
            return ((527 + this.stackManipulations.hashCode()) * 31) + ArrayFactory.this.hashCode();
        }

        protected ArrayStackManipulation(List<? extends StackManipulation> list) {
            this.stackManipulations = list;
        }

        public boolean isValid() {
            for (StackManipulation isValid : this.stackManipulations) {
                if (!isValid.isValid()) {
                    return false;
                }
            }
            return ArrayFactory.this.arrayCreator.isValid();
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            StackManipulation.Size aggregate = IntegerConstant.forValue(this.stackManipulations.size()).apply(methodVisitor, context).aggregate(ArrayFactory.this.arrayCreator.apply(methodVisitor, context));
            int i = 0;
            for (StackManipulation apply : this.stackManipulations) {
                methodVisitor.visitInsn(89);
                StackManipulation.Size aggregate2 = aggregate.aggregate(StackSize.SINGLE.toIncreasingSize()).aggregate(IntegerConstant.forValue(i).apply(methodVisitor, context)).aggregate(apply.apply(methodVisitor, context));
                methodVisitor.visitInsn(ArrayFactory.this.arrayCreator.getStorageOpcode());
                aggregate = aggregate2.aggregate(ArrayFactory.this.sizeDecrease);
                i++;
            }
            return aggregate;
        }
    }
}

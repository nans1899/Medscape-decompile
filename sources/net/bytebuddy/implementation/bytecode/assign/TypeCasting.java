package net.bytebuddy.implementation.bytecode.assign;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;

@HashCodeAndEqualsPlugin.Enhance
public class TypeCasting implements StackManipulation {
    private final TypeDescription typeDescription;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((TypeCasting) obj).typeDescription);
    }

    public int hashCode() {
        return 527 + this.typeDescription.hashCode();
    }

    public boolean isValid() {
        return true;
    }

    protected TypeCasting(TypeDescription typeDescription2) {
        this.typeDescription = typeDescription2;
    }

    public static StackManipulation to(TypeDefinition typeDefinition) {
        if (!typeDefinition.isPrimitive()) {
            return new TypeCasting(typeDefinition.asErasure());
        }
        throw new IllegalArgumentException("Cannot cast to primitive type: " + typeDefinition);
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitTypeInsn(192, this.typeDescription.getInternalName());
        return StackSize.ZERO.toIncreasingSize();
    }
}

package net.bytebuddy.implementation.bytecode.assign.reference;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;

public enum ReferenceTypeAwareAssigner implements Assigner {
    INSTANCE;

    public StackManipulation assign(TypeDescription.Generic generic, TypeDescription.Generic generic2, Assigner.Typing typing) {
        if (generic.isPrimitive() || generic2.isPrimitive()) {
            return generic.equals(generic2) ? StackManipulation.Trivial.INSTANCE : StackManipulation.Illegal.INSTANCE;
        } else if (generic.asErasure().isAssignableTo(generic2.asErasure())) {
            return StackManipulation.Trivial.INSTANCE;
        } else {
            if (typing.isDynamic()) {
                return TypeCasting.to(generic2);
            }
            return StackManipulation.Illegal.INSTANCE;
        }
    }
}

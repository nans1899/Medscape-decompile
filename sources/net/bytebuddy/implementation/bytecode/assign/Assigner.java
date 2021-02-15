package net.bytebuddy.implementation.bytecode.assign;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveTypeAwareAssigner;
import net.bytebuddy.implementation.bytecode.assign.primitive.VoidAwareAssigner;
import net.bytebuddy.implementation.bytecode.assign.reference.ReferenceTypeAwareAssigner;

public interface Assigner {
    public static final Assigner DEFAULT = new VoidAwareAssigner(new PrimitiveTypeAwareAssigner(ReferenceTypeAwareAssigner.INSTANCE));

    public enum EqualTypesOnly implements Assigner {
        GENERIC {
            public StackManipulation assign(TypeDescription.Generic generic, TypeDescription.Generic generic2, Typing typing) {
                return generic.equals(generic2) ? StackManipulation.Trivial.INSTANCE : StackManipulation.Illegal.INSTANCE;
            }
        },
        ERASURE {
            public StackManipulation assign(TypeDescription.Generic generic, TypeDescription.Generic generic2, Typing typing) {
                return generic.asErasure().equals(generic2.asErasure()) ? StackManipulation.Trivial.INSTANCE : StackManipulation.Illegal.INSTANCE;
            }
        }
    }

    StackManipulation assign(TypeDescription.Generic generic, TypeDescription.Generic generic2, Typing typing);

    public enum Typing {
        STATIC(false),
        DYNAMIC(true);
        
        private final boolean dynamic;

        private Typing(boolean z) {
            this.dynamic = z;
        }

        public static Typing of(boolean z) {
            return z ? DYNAMIC : STATIC;
        }

        public boolean isDynamic() {
            return this.dynamic;
        }
    }

    public enum Refusing implements Assigner {
        INSTANCE;

        public StackManipulation assign(TypeDescription.Generic generic, TypeDescription.Generic generic2, Typing typing) {
            return StackManipulation.Illegal.INSTANCE;
        }
    }
}

package net.bytebuddy.implementation.bytecode;

import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;

public enum Removal implements StackManipulation {
    ZERO(StackSize.ZERO, 0) {
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return new StackManipulation.Size(0, 0);
        }
    },
    SINGLE(StackSize.SINGLE, 87),
    DOUBLE(StackSize.DOUBLE, 88);
    
    private final int opcode;
    private final StackManipulation.Size size;

    public boolean isValid() {
        return true;
    }

    private Removal(StackSize stackSize, int i) {
        this.size = stackSize.toDecreasingSize();
        this.opcode = i;
    }

    /* renamed from: net.bytebuddy.implementation.bytecode.Removal$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                net.bytebuddy.implementation.bytecode.StackSize[] r0 = net.bytebuddy.implementation.bytecode.StackSize.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize = r0
                net.bytebuddy.implementation.bytecode.StackSize r1 = net.bytebuddy.implementation.bytecode.StackSize.SINGLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize     // Catch:{ NoSuchFieldError -> 0x001d }
                net.bytebuddy.implementation.bytecode.StackSize r1 = net.bytebuddy.implementation.bytecode.StackSize.DOUBLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize     // Catch:{ NoSuchFieldError -> 0x0028 }
                net.bytebuddy.implementation.bytecode.StackSize r1 = net.bytebuddy.implementation.bytecode.StackSize.ZERO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.implementation.bytecode.Removal.AnonymousClass2.<clinit>():void");
        }
    }

    public static StackManipulation of(TypeDefinition typeDefinition) {
        int i = AnonymousClass2.$SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[typeDefinition.getStackSize().ordinal()];
        if (i == 1) {
            return SINGLE;
        }
        if (i == 2) {
            return DOUBLE;
        }
        if (i == 3) {
            return ZERO;
        }
        throw new AssertionError();
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.opcode);
        return this.size;
    }
}

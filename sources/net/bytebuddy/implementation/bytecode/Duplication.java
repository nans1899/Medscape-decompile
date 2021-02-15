package net.bytebuddy.implementation.bytecode;

import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;

public enum Duplication implements StackManipulation {
    ZERO(StackSize.ZERO, 0) {
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return this.size;
        }

        public StackManipulation flipOver(TypeDefinition typeDefinition) {
            throw new IllegalStateException("Cannot flip zero value");
        }
    },
    SINGLE(StackSize.SINGLE, 89) {
        public StackManipulation flipOver(TypeDefinition typeDefinition) {
            int i = AnonymousClass4.$SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[typeDefinition.getStackSize().ordinal()];
            if (i == 1) {
                return WithFlip.SINGLE_SINGLE;
            }
            if (i == 2) {
                return WithFlip.SINGLE_DOUBLE;
            }
            throw new IllegalArgumentException("Cannot flip: " + typeDefinition);
        }
    },
    DOUBLE(StackSize.DOUBLE, 92) {
        public StackManipulation flipOver(TypeDefinition typeDefinition) {
            int i = AnonymousClass4.$SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[typeDefinition.getStackSize().ordinal()];
            if (i == 1) {
                return WithFlip.DOUBLE_SINGLE;
            }
            if (i == 2) {
                return WithFlip.DOUBLE_DOUBLE;
            }
            throw new IllegalArgumentException("Cannot flip: " + typeDefinition);
        }
    };
    
    private final int opcode;
    protected final StackManipulation.Size size;

    public abstract StackManipulation flipOver(TypeDefinition typeDefinition);

    public boolean isValid() {
        return true;
    }

    /* renamed from: net.bytebuddy.implementation.bytecode.Duplication$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
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
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.implementation.bytecode.Duplication.AnonymousClass4.<clinit>():void");
        }
    }

    private Duplication(StackSize stackSize, int i) {
        this.size = stackSize.toIncreasingSize();
        this.opcode = i;
    }

    public static Duplication of(TypeDefinition typeDefinition) {
        int i = AnonymousClass4.$SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[typeDefinition.getStackSize().ordinal()];
        if (i == 1) {
            return SINGLE;
        }
        if (i == 2) {
            return DOUBLE;
        }
        if (i == 3) {
            return ZERO;
        }
        throw new AssertionError("Unexpected type: " + typeDefinition);
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.opcode);
        return this.size;
    }

    protected enum WithFlip implements StackManipulation {
        SINGLE_SINGLE(90, StackSize.SINGLE),
        SINGLE_DOUBLE(91, StackSize.SINGLE),
        DOUBLE_SINGLE(93, StackSize.DOUBLE),
        DOUBLE_DOUBLE(94, StackSize.DOUBLE);
        
        private final int opcode;
        private final StackSize stackSize;

        public boolean isValid() {
            return true;
        }

        private WithFlip(int i, StackSize stackSize2) {
            this.opcode = i;
            this.stackSize = stackSize2;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitInsn(this.opcode);
            return this.stackSize.toIncreasingSize();
        }
    }
}

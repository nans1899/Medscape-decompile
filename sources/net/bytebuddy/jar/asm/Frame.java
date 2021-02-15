package net.bytebuddy.jar.asm;

import androidx.core.view.ViewCompat;
import com.dd.plist.ASCIIPropertyListParser;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.pool.TypePool;

class Frame {
    static final int APPEND_FRAME = 252;
    private static final int ARRAY_OF = 268435456;
    private static final int BOOLEAN = 16777225;
    private static final int BYTE = 16777226;
    private static final int CHAR = 16777227;
    static final int CHOP_FRAME = 248;
    private static final int CONSTANT_KIND = 16777216;
    private static final int DIM_MASK = -268435456;
    private static final int DIM_SHIFT = 28;
    private static final int DOUBLE = 16777219;
    private static final int ELEMENT_OF = -268435456;
    private static final int FLAGS_MASK = 15728640;
    private static final int FLOAT = 16777218;
    static final int FULL_FRAME = 255;
    private static final int INTEGER = 16777217;
    private static final int ITEM_ASM_BOOLEAN = 9;
    private static final int ITEM_ASM_BYTE = 10;
    private static final int ITEM_ASM_CHAR = 11;
    private static final int ITEM_ASM_SHORT = 12;
    static final int ITEM_DOUBLE = 3;
    static final int ITEM_FLOAT = 2;
    static final int ITEM_INTEGER = 1;
    static final int ITEM_LONG = 4;
    static final int ITEM_NULL = 5;
    static final int ITEM_OBJECT = 7;
    static final int ITEM_TOP = 0;
    static final int ITEM_UNINITIALIZED = 8;
    static final int ITEM_UNINITIALIZED_THIS = 6;
    private static final int KIND_MASK = 251658240;
    private static final int LOCAL_KIND = 67108864;
    private static final int LONG = 16777220;
    private static final int NULL = 16777221;
    private static final int REFERENCE_KIND = 33554432;
    static final int RESERVED = 128;
    static final int SAME_FRAME = 0;
    static final int SAME_FRAME_EXTENDED = 251;
    static final int SAME_LOCALS_1_STACK_ITEM_FRAME = 64;
    static final int SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;
    private static final int SHORT = 16777228;
    private static final int STACK_KIND = 83886080;
    private static final int TOP = 16777216;
    private static final int TOP_IF_LONG_OR_DOUBLE_FLAG = 1048576;
    private static final int UNINITIALIZED_KIND = 50331648;
    private static final int UNINITIALIZED_THIS = 16777222;
    private static final int VALUE_MASK = 1048575;
    private int initializationCount;
    private int[] initializations;
    private int[] inputLocals;
    private int[] inputStack;
    private int[] outputLocals;
    private int[] outputStack;
    private short outputStackStart;
    private short outputStackTop;
    Label owner;

    Frame(Label label) {
        this.owner = label;
    }

    /* access modifiers changed from: package-private */
    public final void copyFrom(Frame frame) {
        this.inputLocals = frame.inputLocals;
        this.inputStack = frame.inputStack;
        this.outputStackStart = 0;
        this.outputLocals = frame.outputLocals;
        this.outputStack = frame.outputStack;
        this.outputStackTop = frame.outputStackTop;
        this.initializationCount = frame.initializationCount;
        this.initializations = frame.initializations;
    }

    static int getAbstractTypeFromApiFormat(SymbolTable symbolTable, Object obj) {
        if (obj instanceof Integer) {
            return 16777216 | ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            return getAbstractTypeFromDescriptor(symbolTable, Type.getObjectType((String) obj).getDescriptor(), 0);
        }
        return symbolTable.addUninitializedType("", ((Label) obj).bytecodeOffset) | UNINITIALIZED_KIND;
    }

    static int getAbstractTypeFromInternalName(SymbolTable symbolTable, String str) {
        return symbolTable.addType(str) | REFERENCE_KIND;
    }

    private static int getAbstractTypeFromDescriptor(SymbolTable symbolTable, String str, int i) {
        SymbolTable symbolTable2 = symbolTable;
        String str2 = str;
        char charAt = str.charAt(i);
        int i2 = FLOAT;
        if (charAt == 'F') {
            return FLOAT;
        }
        if (charAt == 'L') {
            return symbolTable2.addType(str2.substring(i + 1, str.length() - 1)) | REFERENCE_KIND;
        }
        if (charAt != 'S') {
            if (charAt == 'V') {
                return 0;
            }
            if (charAt != 'I') {
                if (charAt == 'J') {
                    return LONG;
                }
                if (charAt != 'Z') {
                    if (charAt != '[') {
                        switch (charAt) {
                            case 'B':
                            case 'C':
                                break;
                            case 'D':
                                return DOUBLE;
                            default:
                                throw new IllegalArgumentException();
                        }
                    } else {
                        int i3 = i + 1;
                        while (str2.charAt(i3) == '[') {
                            i3++;
                        }
                        char charAt2 = str2.charAt(i3);
                        if (charAt2 != 'F') {
                            if (charAt2 == 'L') {
                                i2 = symbolTable2.addType(str2.substring(i3 + 1, str.length() - 1)) | REFERENCE_KIND;
                            } else if (charAt2 == 'S') {
                                i2 = SHORT;
                            } else if (charAt2 == 'Z') {
                                i2 = BOOLEAN;
                            } else if (charAt2 == 'I') {
                                i2 = INTEGER;
                            } else if (charAt2 != 'J') {
                                switch (charAt2) {
                                    case 'B':
                                        i2 = BYTE;
                                        break;
                                    case 'C':
                                        i2 = CHAR;
                                        break;
                                    case 'D':
                                        i2 = DOUBLE;
                                        break;
                                    default:
                                        throw new IllegalArgumentException();
                                }
                            } else {
                                i2 = LONG;
                            }
                        }
                        return ((i3 - i) << 28) | i2;
                    }
                }
            }
        }
        return INTEGER;
    }

    /* access modifiers changed from: package-private */
    public final void setInputFrameFromDescriptor(SymbolTable symbolTable, int i, String str, int i2) {
        int i3;
        int[] iArr = new int[i2];
        this.inputLocals = iArr;
        this.inputStack = new int[0];
        int i4 = 1;
        if ((i & 8) != 0) {
            i4 = 0;
        } else if ((i & 262144) == 0) {
            iArr[0] = REFERENCE_KIND | symbolTable.addType(symbolTable.getClassName());
        } else {
            iArr[0] = UNINITIALIZED_THIS;
        }
        for (Type descriptor : Type.getArgumentTypes(str)) {
            int abstractTypeFromDescriptor = getAbstractTypeFromDescriptor(symbolTable, descriptor.getDescriptor(), 0);
            int i5 = i3 + 1;
            this.inputLocals[i3] = abstractTypeFromDescriptor;
            if (abstractTypeFromDescriptor == LONG || abstractTypeFromDescriptor == DOUBLE) {
                this.inputLocals[i5] = 16777216;
                i3 = i5 + 1;
            } else {
                i3 = i5;
            }
        }
        while (i3 < i2) {
            this.inputLocals[i3] = 16777216;
            i3++;
        }
    }

    /* access modifiers changed from: package-private */
    public final void setInputFrameFromApiFormat(SymbolTable symbolTable, int i, Object[] objArr, int i2, Object[] objArr2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = i3 + 1;
            this.inputLocals[i3] = getAbstractTypeFromApiFormat(symbolTable, objArr[i4]);
            if (objArr[i4] == Opcodes.LONG || objArr[i4] == Opcodes.DOUBLE) {
                this.inputLocals[i5] = 16777216;
                i3 = i5 + 1;
            } else {
                i3 = i5;
            }
        }
        while (true) {
            int[] iArr = this.inputLocals;
            if (i3 >= iArr.length) {
                break;
            }
            iArr[i3] = 16777216;
            i3++;
        }
        int i6 = 0;
        for (int i7 = 0; i7 < i2; i7++) {
            if (objArr2[i7] == Opcodes.LONG || objArr2[i7] == Opcodes.DOUBLE) {
                i6++;
            }
        }
        this.inputStack = new int[(i6 + i2)];
        int i8 = 0;
        for (int i9 = 0; i9 < i2; i9++) {
            int i10 = i8 + 1;
            this.inputStack[i8] = getAbstractTypeFromApiFormat(symbolTable, objArr2[i9]);
            if (objArr2[i9] == Opcodes.LONG || objArr2[i9] == Opcodes.DOUBLE) {
                this.inputStack[i10] = 16777216;
                i8 = i10 + 1;
            } else {
                i8 = i10;
            }
        }
        this.outputStackTop = 0;
        this.initializationCount = 0;
    }

    /* access modifiers changed from: package-private */
    public final int getInputStackSize() {
        return this.inputStack.length;
    }

    private int getLocal(int i) {
        int[] iArr = this.outputLocals;
        if (iArr == null || i >= iArr.length) {
            return i | LOCAL_KIND;
        }
        int i2 = iArr[i];
        if (i2 != 0) {
            return i2;
        }
        int i3 = i | LOCAL_KIND;
        iArr[i] = i3;
        return i3;
    }

    private void setLocal(int i, int i2) {
        if (this.outputLocals == null) {
            this.outputLocals = new int[10];
        }
        int length = this.outputLocals.length;
        if (i >= length) {
            int[] iArr = new int[Math.max(i + 1, length * 2)];
            System.arraycopy(this.outputLocals, 0, iArr, 0, length);
            this.outputLocals = iArr;
        }
        this.outputLocals[i] = i2;
    }

    private void push(int i) {
        if (this.outputStack == null) {
            this.outputStack = new int[10];
        }
        int length = this.outputStack.length;
        short s = this.outputStackTop;
        if (s >= length) {
            int[] iArr = new int[Math.max(s + 1, length * 2)];
            System.arraycopy(this.outputStack, 0, iArr, 0, length);
            this.outputStack = iArr;
        }
        int[] iArr2 = this.outputStack;
        short s2 = this.outputStackTop;
        short s3 = (short) (s2 + 1);
        this.outputStackTop = s3;
        iArr2[s2] = i;
        short s4 = (short) (this.outputStackStart + s3);
        if (s4 > this.owner.outputStackMax) {
            this.owner.outputStackMax = s4;
        }
    }

    private void push(SymbolTable symbolTable, String str) {
        int i = 0;
        if (str.charAt(0) == '(') {
            i = str.indexOf(41) + 1;
        }
        int abstractTypeFromDescriptor = getAbstractTypeFromDescriptor(symbolTable, str, i);
        if (abstractTypeFromDescriptor != 0) {
            push(abstractTypeFromDescriptor);
            if (abstractTypeFromDescriptor == LONG || abstractTypeFromDescriptor == DOUBLE) {
                push(16777216);
            }
        }
    }

    private int pop() {
        short s = this.outputStackTop;
        if (s > 0) {
            int[] iArr = this.outputStack;
            short s2 = (short) (s - 1);
            this.outputStackTop = s2;
            return iArr[s2];
        }
        short s3 = (short) (this.outputStackStart - 1);
        this.outputStackStart = s3;
        return STACK_KIND | (-s3);
    }

    private void pop(int i) {
        short s = this.outputStackTop;
        if (s >= i) {
            this.outputStackTop = (short) (s - i);
            return;
        }
        this.outputStackStart = (short) (this.outputStackStart - (i - s));
        this.outputStackTop = 0;
    }

    private void pop(String str) {
        char charAt = str.charAt(0);
        if (charAt == '(') {
            pop((Type.getArgumentsAndReturnSizes(str) >> 2) - 1);
        } else if (charAt == 'J' || charAt == 'D') {
            pop(2);
        } else {
            pop(1);
        }
    }

    private void addInitializedType(int i) {
        if (this.initializations == null) {
            this.initializations = new int[2];
        }
        int length = this.initializations.length;
        int i2 = this.initializationCount;
        if (i2 >= length) {
            int[] iArr = new int[Math.max(i2 + 1, length * 2)];
            System.arraycopy(this.initializations, 0, iArr, 0, length);
            this.initializations = iArr;
        }
        int[] iArr2 = this.initializations;
        int i3 = this.initializationCount;
        this.initializationCount = i3 + 1;
        iArr2[i3] = i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0051 A[LOOP:0: B:5:0x000d->B:21:0x0051, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0037 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getInitializedType(net.bytebuddy.jar.asm.SymbolTable r9, int r10) {
        /*
            r8 = this;
            r0 = 16777222(0x1000006, float:2.3509904E-38)
            if (r10 == r0) goto L_0x000c
            r1 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r1 = r1 & r10
            r2 = 50331648(0x3000000, float:3.761582E-37)
            if (r1 != r2) goto L_0x0054
        L_0x000c:
            r1 = 0
        L_0x000d:
            int r2 = r8.initializationCount
            if (r1 >= r2) goto L_0x0054
            int[] r2 = r8.initializations
            r2 = r2[r1]
            r3 = -268435456(0xfffffffff0000000, float:-1.58456325E29)
            r3 = r3 & r2
            r4 = 251658240(0xf000000, float:6.3108872E-30)
            r4 = r4 & r2
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r2 & r5
            r7 = 67108864(0x4000000, float:1.5046328E-36)
            if (r4 != r7) goto L_0x002a
            int[] r2 = r8.inputLocals
            r2 = r2[r6]
        L_0x0028:
            int r2 = r2 + r3
            goto L_0x0035
        L_0x002a:
            r7 = 83886080(0x5000000, float:6.018531E-36)
            if (r4 != r7) goto L_0x0035
            int[] r2 = r8.inputStack
            int r4 = r2.length
            int r4 = r4 - r6
            r2 = r2[r4]
            goto L_0x0028
        L_0x0035:
            if (r10 != r2) goto L_0x0051
            r1 = 33554432(0x2000000, float:9.403955E-38)
            if (r10 != r0) goto L_0x0045
            java.lang.String r10 = r9.getClassName()
            int r9 = r9.addType(r10)
        L_0x0043:
            r9 = r9 | r1
            return r9
        L_0x0045:
            r10 = r10 & r5
            net.bytebuddy.jar.asm.Symbol r10 = r9.getType(r10)
            java.lang.String r10 = r10.value
            int r9 = r9.addType(r10)
            goto L_0x0043
        L_0x0051:
            int r1 = r1 + 1
            goto L_0x000d
        L_0x0054:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.Frame.getInitializedType(net.bytebuddy.jar.asm.SymbolTable, int):int");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0344, code lost:
        push(DOUBLE);
        push(16777216);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x034b, code lost:
        push(FLOAT);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x034f, code lost:
        push(LONG);
        push(16777216);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0356, code lost:
        push(INTEGER);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0245, code lost:
        pop(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x02bf, code lost:
        pop(2);
        push(DOUBLE);
        push(16777216);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x02ca, code lost:
        pop(2);
        push(FLOAT);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x02d2, code lost:
        pop(2);
        push(LONG);
        push(16777216);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02dd, code lost:
        pop(2);
        push(INTEGER);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute(int r17, int r18, net.bytebuddy.jar.asm.Symbol r19, net.bytebuddy.jar.asm.SymbolTable r20) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = r20
            r6 = 16777218(0x1000002, float:2.3509893E-38)
            r7 = 33554432(0x2000000, float:9.403955E-38)
            r8 = 16777217(0x1000001, float:2.350989E-38)
            r9 = 16777219(0x1000003, float:2.3509895E-38)
            r10 = 16777220(0x1000004, float:2.3509898E-38)
            r11 = 16777216(0x1000000, float:2.3509887E-38)
            switch(r1) {
                case 0: goto L_0x0360;
                case 1: goto L_0x035a;
                case 2: goto L_0x0356;
                case 3: goto L_0x0356;
                case 4: goto L_0x0356;
                case 5: goto L_0x0356;
                case 6: goto L_0x0356;
                case 7: goto L_0x0356;
                case 8: goto L_0x0356;
                case 9: goto L_0x034f;
                case 10: goto L_0x034f;
                case 11: goto L_0x034b;
                case 12: goto L_0x034b;
                case 13: goto L_0x034b;
                case 14: goto L_0x0344;
                case 15: goto L_0x0344;
                case 16: goto L_0x0356;
                case 17: goto L_0x0356;
                case 18: goto L_0x02ee;
                default: goto L_0x001d;
            }
        L_0x001d:
            switch(r1) {
                case 21: goto L_0x0356;
                case 22: goto L_0x034f;
                case 23: goto L_0x034b;
                case 24: goto L_0x0344;
                case 25: goto L_0x02e5;
                default: goto L_0x0020;
            }
        L_0x0020:
            r12 = 83886080(0x5000000, float:6.018531E-36)
            r14 = 67108864(0x4000000, float:1.5046328E-36)
            r15 = 251658240(0xf000000, float:6.3108872E-30)
            r13 = 1
            switch(r1) {
                case 46: goto L_0x019c;
                case 47: goto L_0x0147;
                case 48: goto L_0x018e;
                case 49: goto L_0x0152;
                case 50: goto L_0x02aa;
                case 51: goto L_0x019c;
                case 52: goto L_0x019c;
                case 53: goto L_0x019c;
                case 54: goto L_0x0283;
                case 55: goto L_0x0254;
                case 56: goto L_0x0283;
                case 57: goto L_0x0254;
                case 58: goto L_0x0283;
                default: goto L_0x002a;
            }
        L_0x002a:
            r12 = 3
            r14 = 91
            r15 = 0
            r5 = 4
            switch(r1) {
                case 79: goto L_0x024f;
                case 80: goto L_0x024a;
                case 81: goto L_0x024f;
                case 82: goto L_0x024a;
                case 83: goto L_0x024f;
                case 84: goto L_0x024f;
                case 85: goto L_0x024f;
                case 86: goto L_0x024f;
                case 87: goto L_0x0245;
                case 88: goto L_0x023f;
                case 89: goto L_0x0233;
                case 90: goto L_0x0220;
                case 91: goto L_0x0206;
                case 92: goto L_0x01f0;
                case 93: goto L_0x01d3;
                case 94: goto L_0x01af;
                case 95: goto L_0x019f;
                case 96: goto L_0x019c;
                case 97: goto L_0x0191;
                case 98: goto L_0x018e;
                case 99: goto L_0x0183;
                case 100: goto L_0x019c;
                case 101: goto L_0x0191;
                case 102: goto L_0x018e;
                case 103: goto L_0x0183;
                case 104: goto L_0x019c;
                case 105: goto L_0x0191;
                case 106: goto L_0x018e;
                case 107: goto L_0x0183;
                case 108: goto L_0x019c;
                case 109: goto L_0x0191;
                case 110: goto L_0x018e;
                case 111: goto L_0x0183;
                case 112: goto L_0x019c;
                case 113: goto L_0x0191;
                case 114: goto L_0x018e;
                case 115: goto L_0x0183;
                case 116: goto L_0x0360;
                case 117: goto L_0x0360;
                case 118: goto L_0x0360;
                case 119: goto L_0x0360;
                case 120: goto L_0x019c;
                case 121: goto L_0x0178;
                case 122: goto L_0x019c;
                case 123: goto L_0x0178;
                case 124: goto L_0x019c;
                case 125: goto L_0x0178;
                case 126: goto L_0x019c;
                case 127: goto L_0x0191;
                case 128: goto L_0x019c;
                case 129: goto L_0x0191;
                case 130: goto L_0x019c;
                case 131: goto L_0x0191;
                case 132: goto L_0x0173;
                case 133: goto L_0x0168;
                case 134: goto L_0x0160;
                case 135: goto L_0x0155;
                case 136: goto L_0x019c;
                case 137: goto L_0x018e;
                case 138: goto L_0x0152;
                case 139: goto L_0x014a;
                case 140: goto L_0x0168;
                case 141: goto L_0x0155;
                case 142: goto L_0x019c;
                case 143: goto L_0x0147;
                case 144: goto L_0x018e;
                case 145: goto L_0x0360;
                case 146: goto L_0x0360;
                case 147: goto L_0x0360;
                case 148: goto L_0x013f;
                case 149: goto L_0x019c;
                case 150: goto L_0x019c;
                case 151: goto L_0x013f;
                case 152: goto L_0x013f;
                case 153: goto L_0x0245;
                case 154: goto L_0x0245;
                case 155: goto L_0x0245;
                case 156: goto L_0x0245;
                case 157: goto L_0x0245;
                case 158: goto L_0x0245;
                case 159: goto L_0x023f;
                case 160: goto L_0x023f;
                case 161: goto L_0x023f;
                case 162: goto L_0x023f;
                case 163: goto L_0x023f;
                case 164: goto L_0x023f;
                case 165: goto L_0x023f;
                case 166: goto L_0x023f;
                case 167: goto L_0x0360;
                case 168: goto L_0x0137;
                case 169: goto L_0x0137;
                case 170: goto L_0x0245;
                case 171: goto L_0x0245;
                case 172: goto L_0x0245;
                case 173: goto L_0x023f;
                case 174: goto L_0x0245;
                case 175: goto L_0x023f;
                case 176: goto L_0x0245;
                case 177: goto L_0x0360;
                case 178: goto L_0x0130;
                case 179: goto L_0x0129;
                case 180: goto L_0x011f;
                case 181: goto L_0x0115;
                case 182: goto L_0x00f0;
                case 183: goto L_0x00f0;
                case 184: goto L_0x00f0;
                case 185: goto L_0x00f0;
                case 186: goto L_0x00e4;
                case 187: goto L_0x00d6;
                case 188: goto L_0x008a;
                case 189: goto L_0x005f;
                case 190: goto L_0x014a;
                case 191: goto L_0x0245;
                case 192: goto L_0x0045;
                case 193: goto L_0x014a;
                case 194: goto L_0x0245;
                case 195: goto L_0x0245;
                default: goto L_0x0032;
            }
        L_0x0032:
            switch(r1) {
                case 197: goto L_0x003b;
                case 198: goto L_0x0245;
                case 199: goto L_0x0245;
                default: goto L_0x0035;
            }
        L_0x0035:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>()
            throw r1
        L_0x003b:
            r0.pop((int) r2)
            java.lang.String r1 = r3.value
            r0.push(r4, r1)
            goto L_0x0360
        L_0x0045:
            java.lang.String r1 = r3.value
            r16.pop()
            char r2 = r1.charAt(r15)
            if (r2 != r14) goto L_0x0055
            r0.push(r4, r1)
            goto L_0x0360
        L_0x0055:
            int r1 = r4.addType(r1)
            r1 = r1 | r7
            r0.push(r1)
            goto L_0x0360
        L_0x005f:
            java.lang.String r1 = r3.value
            r16.pop()
            char r2 = r1.charAt(r15)
            if (r2 != r14) goto L_0x007e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r14)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.push(r4, r1)
            goto L_0x0360
        L_0x007e:
            r2 = 301989888(0x12000000, float:4.0389678E-28)
            int r1 = r4.addType(r1)
            r1 = r1 | r2
            r0.push(r1)
            goto L_0x0360
        L_0x008a:
            r16.pop()
            switch(r2) {
                case 4: goto L_0x00ce;
                case 5: goto L_0x00c6;
                case 6: goto L_0x00be;
                case 7: goto L_0x00b6;
                case 8: goto L_0x00ae;
                case 9: goto L_0x00a6;
                case 10: goto L_0x009e;
                case 11: goto L_0x0096;
                default: goto L_0x0090;
            }
        L_0x0090:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>()
            throw r1
        L_0x0096:
            r1 = 285212676(0x11000004, float:1.0097424E-28)
            r0.push(r1)
            goto L_0x0360
        L_0x009e:
            r1 = 285212673(0x11000001, float:1.0097421E-28)
            r0.push(r1)
            goto L_0x0360
        L_0x00a6:
            r1 = 285212684(0x1100000c, float:1.0097434E-28)
            r0.push(r1)
            goto L_0x0360
        L_0x00ae:
            r1 = 285212682(0x1100000a, float:1.0097432E-28)
            r0.push(r1)
            goto L_0x0360
        L_0x00b6:
            r1 = 285212675(0x11000003, float:1.0097423E-28)
            r0.push(r1)
            goto L_0x0360
        L_0x00be:
            r1 = 285212674(0x11000002, float:1.0097422E-28)
            r0.push(r1)
            goto L_0x0360
        L_0x00c6:
            r1 = 285212683(0x1100000b, float:1.0097433E-28)
            r0.push(r1)
            goto L_0x0360
        L_0x00ce:
            r1 = 285212681(0x11000009, float:1.009743E-28)
            r0.push(r1)
            goto L_0x0360
        L_0x00d6:
            r1 = 50331648(0x3000000, float:3.761582E-37)
            java.lang.String r3 = r3.value
            int r2 = r4.addUninitializedType(r3, r2)
            r1 = r1 | r2
            r0.push(r1)
            goto L_0x0360
        L_0x00e4:
            java.lang.String r1 = r3.value
            r0.pop((java.lang.String) r1)
            java.lang.String r1 = r3.value
            r0.push(r4, r1)
            goto L_0x0360
        L_0x00f0:
            java.lang.String r2 = r3.value
            r0.pop((java.lang.String) r2)
            r2 = 184(0xb8, float:2.58E-43)
            if (r1 == r2) goto L_0x010e
            int r2 = r16.pop()
            r5 = 183(0xb7, float:2.56E-43)
            if (r1 != r5) goto L_0x010e
            java.lang.String r1 = r3.name
            char r1 = r1.charAt(r15)
            r5 = 60
            if (r1 != r5) goto L_0x010e
            r0.addInitializedType(r2)
        L_0x010e:
            java.lang.String r1 = r3.value
            r0.push(r4, r1)
            goto L_0x0360
        L_0x0115:
            java.lang.String r1 = r3.value
            r0.pop((java.lang.String) r1)
            r16.pop()
            goto L_0x0360
        L_0x011f:
            r0.pop((int) r13)
            java.lang.String r1 = r3.value
            r0.push(r4, r1)
            goto L_0x0360
        L_0x0129:
            java.lang.String r1 = r3.value
            r0.pop((java.lang.String) r1)
            goto L_0x0360
        L_0x0130:
            java.lang.String r1 = r3.value
            r0.push(r4, r1)
            goto L_0x0360
        L_0x0137:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "JSR/RET are not supported with computeFrames option"
            r1.<init>(r2)
            throw r1
        L_0x013f:
            r0.pop((int) r5)
            r0.push(r8)
            goto L_0x0360
        L_0x0147:
            r1 = 2
            goto L_0x02d2
        L_0x014a:
            r0.pop((int) r13)
            r0.push(r8)
            goto L_0x0360
        L_0x0152:
            r1 = 2
            goto L_0x02bf
        L_0x0155:
            r0.pop((int) r13)
            r0.push(r9)
            r0.push(r11)
            goto L_0x0360
        L_0x0160:
            r0.pop((int) r13)
            r0.push(r6)
            goto L_0x0360
        L_0x0168:
            r0.pop((int) r13)
            r0.push(r10)
            r0.push(r11)
            goto L_0x0360
        L_0x0173:
            r0.setLocal(r2, r8)
            goto L_0x0360
        L_0x0178:
            r0.pop((int) r12)
            r0.push(r10)
            r0.push(r11)
            goto L_0x0360
        L_0x0183:
            r0.pop((int) r5)
            r0.push(r9)
            r0.push(r11)
            goto L_0x0360
        L_0x018e:
            r1 = 2
            goto L_0x02ca
        L_0x0191:
            r0.pop((int) r5)
            r0.push(r10)
            r0.push(r11)
            goto L_0x0360
        L_0x019c:
            r1 = 2
            goto L_0x02dd
        L_0x019f:
            int r1 = r16.pop()
            int r2 = r16.pop()
            r0.push(r1)
            r0.push(r2)
            goto L_0x0360
        L_0x01af:
            int r1 = r16.pop()
            int r2 = r16.pop()
            int r3 = r16.pop()
            int r4 = r16.pop()
            r0.push(r2)
            r0.push(r1)
            r0.push(r4)
            r0.push(r3)
            r0.push(r2)
            r0.push(r1)
            goto L_0x0360
        L_0x01d3:
            int r1 = r16.pop()
            int r2 = r16.pop()
            int r3 = r16.pop()
            r0.push(r2)
            r0.push(r1)
            r0.push(r3)
            r0.push(r2)
            r0.push(r1)
            goto L_0x0360
        L_0x01f0:
            int r1 = r16.pop()
            int r2 = r16.pop()
            r0.push(r2)
            r0.push(r1)
            r0.push(r2)
            r0.push(r1)
            goto L_0x0360
        L_0x0206:
            int r1 = r16.pop()
            int r2 = r16.pop()
            int r3 = r16.pop()
            r0.push(r1)
            r0.push(r3)
            r0.push(r2)
            r0.push(r1)
            goto L_0x0360
        L_0x0220:
            int r1 = r16.pop()
            int r2 = r16.pop()
            r0.push(r1)
            r0.push(r2)
            r0.push(r1)
            goto L_0x0360
        L_0x0233:
            int r1 = r16.pop()
            r0.push(r1)
            r0.push(r1)
            goto L_0x0360
        L_0x023f:
            r1 = 2
            r0.pop((int) r1)
            goto L_0x0360
        L_0x0245:
            r0.pop((int) r13)
            goto L_0x0360
        L_0x024a:
            r0.pop((int) r5)
            goto L_0x0360
        L_0x024f:
            r0.pop((int) r12)
            goto L_0x0360
        L_0x0254:
            r0.pop((int) r13)
            int r1 = r16.pop()
            r0.setLocal(r2, r1)
            int r1 = r2 + 1
            r0.setLocal(r1, r11)
            if (r2 <= 0) goto L_0x0360
            int r1 = r2 + -1
            int r2 = r0.getLocal(r1)
            if (r2 == r10) goto L_0x027e
            if (r2 != r9) goto L_0x0270
            goto L_0x027e
        L_0x0270:
            r3 = r2 & r15
            if (r3 == r14) goto L_0x0276
            if (r3 != r12) goto L_0x0360
        L_0x0276:
            r3 = 1048576(0x100000, float:1.469368E-39)
            r2 = r2 | r3
            r0.setLocal(r1, r2)
            goto L_0x0360
        L_0x027e:
            r0.setLocal(r1, r11)
            goto L_0x0360
        L_0x0283:
            int r1 = r16.pop()
            r0.setLocal(r2, r1)
            if (r2 <= 0) goto L_0x0360
            int r1 = r2 + -1
            int r2 = r0.getLocal(r1)
            if (r2 == r10) goto L_0x02a5
            if (r2 != r9) goto L_0x0297
            goto L_0x02a5
        L_0x0297:
            r3 = r2 & r15
            if (r3 == r14) goto L_0x029d
            if (r3 != r12) goto L_0x0360
        L_0x029d:
            r3 = 1048576(0x100000, float:1.469368E-39)
            r2 = r2 | r3
            r0.setLocal(r1, r2)
            goto L_0x0360
        L_0x02a5:
            r0.setLocal(r1, r11)
            goto L_0x0360
        L_0x02aa:
            r0.pop((int) r13)
            int r1 = r16.pop()
            r2 = 16777221(0x1000005, float:2.35099E-38)
            if (r1 != r2) goto L_0x02b7
            goto L_0x02ba
        L_0x02b7:
            r2 = -268435456(0xfffffffff0000000, float:-1.58456325E29)
            int r1 = r1 + r2
        L_0x02ba:
            r0.push(r1)
            goto L_0x0360
        L_0x02bf:
            r0.pop((int) r1)
            r0.push(r9)
            r0.push(r11)
            goto L_0x0360
        L_0x02ca:
            r0.pop((int) r1)
            r0.push(r6)
            goto L_0x0360
        L_0x02d2:
            r0.pop((int) r1)
            r0.push(r10)
            r0.push(r11)
            goto L_0x0360
        L_0x02dd:
            r0.pop((int) r1)
            r0.push(r8)
            goto L_0x0360
        L_0x02e5:
            int r1 = r0.getLocal(r2)
            r0.push(r1)
            goto L_0x0360
        L_0x02ee:
            int r1 = r3.tag
            switch(r1) {
                case 3: goto L_0x0340;
                case 4: goto L_0x033c;
                case 5: goto L_0x0335;
                case 6: goto L_0x032e;
                case 7: goto L_0x0323;
                case 8: goto L_0x0318;
                default: goto L_0x02f3;
            }
        L_0x02f3:
            switch(r1) {
                case 15: goto L_0x030d;
                case 16: goto L_0x0302;
                case 17: goto L_0x02fc;
                default: goto L_0x02f6;
            }
        L_0x02f6:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            throw r1
        L_0x02fc:
            java.lang.String r1 = r3.value
            r0.push(r4, r1)
            goto L_0x0360
        L_0x0302:
            java.lang.String r1 = "java/lang/invoke/MethodType"
            int r1 = r4.addType(r1)
            r1 = r1 | r7
            r0.push(r1)
            goto L_0x0360
        L_0x030d:
            java.lang.String r1 = "java/lang/invoke/MethodHandle"
            int r1 = r4.addType(r1)
            r1 = r1 | r7
            r0.push(r1)
            goto L_0x0360
        L_0x0318:
            java.lang.String r1 = "java/lang/String"
            int r1 = r4.addType(r1)
            r1 = r1 | r7
            r0.push(r1)
            goto L_0x0360
        L_0x0323:
            java.lang.String r1 = "java/lang/Class"
            int r1 = r4.addType(r1)
            r1 = r1 | r7
            r0.push(r1)
            goto L_0x0360
        L_0x032e:
            r0.push(r9)
            r0.push(r11)
            goto L_0x0360
        L_0x0335:
            r0.push(r10)
            r0.push(r11)
            goto L_0x0360
        L_0x033c:
            r0.push(r6)
            goto L_0x0360
        L_0x0340:
            r0.push(r8)
            goto L_0x0360
        L_0x0344:
            r0.push(r9)
            r0.push(r11)
            goto L_0x0360
        L_0x034b:
            r0.push(r6)
            goto L_0x0360
        L_0x034f:
            r0.push(r10)
            r0.push(r11)
            goto L_0x0360
        L_0x0356:
            r0.push(r8)
            goto L_0x0360
        L_0x035a:
            r1 = 16777221(0x1000005, float:2.35099E-38)
            r0.push(r1)
        L_0x0360:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.Frame.execute(int, int, net.bytebuddy.jar.asm.Symbol, net.bytebuddy.jar.asm.SymbolTable):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0051, code lost:
        if (r8 == DOUBLE) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0069, code lost:
        if (r8 == DOUBLE) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00f6, code lost:
        if (r6 == DOUBLE) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x010e, code lost:
        if (r6 == DOUBLE) goto L_0x00f8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0119 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean merge(net.bytebuddy.jar.asm.SymbolTable r20, net.bytebuddy.jar.asm.Frame r21, int r22) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            int[] r4 = r0.inputLocals
            int r4 = r4.length
            int[] r5 = r0.inputStack
            int r5 = r5.length
            int[] r6 = r2.inputLocals
            if (r6 != 0) goto L_0x0018
            int[] r6 = new int[r4]
            r2.inputLocals = r6
            r6 = 1
            goto L_0x0019
        L_0x0018:
            r6 = 0
        L_0x0019:
            r9 = 0
        L_0x001a:
            r10 = 83886080(0x5000000, float:6.018531E-36)
            r11 = 67108864(0x4000000, float:1.5046328E-36)
            r12 = 251658240(0xf000000, float:6.3108872E-30)
            r13 = -268435456(0xfffffffff0000000, float:-1.58456325E29)
            r14 = 16777219(0x1000003, float:2.3509895E-38)
            r15 = 16777220(0x1000004, float:2.3509898E-38)
            r16 = 1048576(0x100000, float:1.469368E-39)
            r17 = 1048575(0xfffff, float:1.469367E-39)
            if (r9 >= r4) goto L_0x0084
            int[] r7 = r0.outputLocals
            if (r7 == 0) goto L_0x006e
            int r8 = r7.length
            if (r9 >= r8) goto L_0x006e
            r7 = r7[r9]
            if (r7 != 0) goto L_0x003f
            int[] r7 = r0.inputLocals
            r15 = r7[r9]
            goto L_0x0072
        L_0x003f:
            r8 = r7 & r13
            r12 = r12 & r7
            if (r12 != r11) goto L_0x0058
            int[] r10 = r0.inputLocals
            r11 = r7 & r17
            r10 = r10[r11]
            int r8 = r8 + r10
            r7 = r7 & r16
            if (r7 == 0) goto L_0x0056
            if (r8 == r15) goto L_0x0053
            if (r8 != r14) goto L_0x0056
        L_0x0053:
            r15 = 16777216(0x1000000, float:2.3509887E-38)
            goto L_0x0072
        L_0x0056:
            r15 = r8
            goto L_0x0072
        L_0x0058:
            if (r12 != r10) goto L_0x006c
            int[] r10 = r0.inputStack
            r11 = r7 & r17
            int r11 = r5 - r11
            r10 = r10[r11]
            int r8 = r8 + r10
            r7 = r7 & r16
            if (r7 == 0) goto L_0x0056
            if (r8 == r15) goto L_0x0053
            if (r8 != r14) goto L_0x0056
            goto L_0x0053
        L_0x006c:
            r15 = r7
            goto L_0x0072
        L_0x006e:
            int[] r7 = r0.inputLocals
            r15 = r7[r9]
        L_0x0072:
            int[] r7 = r0.initializations
            if (r7 == 0) goto L_0x007a
            int r15 = r0.getInitializedType(r1, r15)
        L_0x007a:
            int[] r7 = r2.inputLocals
            boolean r7 = merge(r1, r15, r7, r9)
            r6 = r6 | r7
            int r9 = r9 + 1
            goto L_0x001a
        L_0x0084:
            if (r3 <= 0) goto L_0x00ac
            r5 = 0
        L_0x0087:
            if (r5 >= r4) goto L_0x0097
            int[] r7 = r0.inputLocals
            r7 = r7[r5]
            int[] r8 = r2.inputLocals
            boolean r7 = merge(r1, r7, r8, r5)
            r6 = r6 | r7
            int r5 = r5 + 1
            goto L_0x0087
        L_0x0097:
            int[] r4 = r2.inputStack
            if (r4 != 0) goto L_0x00a2
            r4 = 1
            int[] r5 = new int[r4]
            r2.inputStack = r5
            r8 = 1
            goto L_0x00a3
        L_0x00a2:
            r8 = r6
        L_0x00a3:
            int[] r2 = r2.inputStack
            r7 = 0
            boolean r1 = merge(r1, r3, r2, r7)
            r1 = r1 | r8
            return r1
        L_0x00ac:
            r4 = 1
            r7 = 0
            int[] r3 = r0.inputStack
            int r3 = r3.length
            short r8 = r0.outputStackStart
            int r3 = r3 + r8
            int[] r8 = r2.inputStack
            if (r8 != 0) goto L_0x00c1
            short r6 = r0.outputStackTop
            int r6 = r6 + r3
            int[] r6 = new int[r6]
            r2.inputStack = r6
            r8 = 1
            goto L_0x00c2
        L_0x00c1:
            r8 = r6
        L_0x00c2:
            r4 = 0
        L_0x00c3:
            if (r4 >= r3) goto L_0x00db
            int[] r6 = r0.inputStack
            r6 = r6[r4]
            int[] r9 = r0.initializations
            if (r9 == 0) goto L_0x00d1
            int r6 = r0.getInitializedType(r1, r6)
        L_0x00d1:
            int[] r9 = r2.inputStack
            boolean r6 = merge(r1, r6, r9, r4)
            r8 = r8 | r6
            int r4 = r4 + 1
            goto L_0x00c3
        L_0x00db:
            short r4 = r0.outputStackTop
            if (r7 >= r4) goto L_0x0125
            int[] r4 = r0.outputStack
            r4 = r4[r7]
            r6 = r4 & r13
            r9 = r4 & r12
            if (r9 != r11) goto L_0x00fd
            int[] r9 = r0.inputLocals
            r18 = r4 & r17
            r9 = r9[r18]
            int r6 = r6 + r9
            r4 = r4 & r16
            if (r4 == 0) goto L_0x00fb
            if (r6 == r15) goto L_0x00f8
            if (r6 != r14) goto L_0x00fb
        L_0x00f8:
            r4 = 16777216(0x1000000, float:2.3509887E-38)
            goto L_0x0111
        L_0x00fb:
            r4 = r6
            goto L_0x0111
        L_0x00fd:
            if (r9 != r10) goto L_0x0111
            int[] r9 = r0.inputStack
            r18 = r4 & r17
            int r18 = r5 - r18
            r9 = r9[r18]
            int r6 = r6 + r9
            r4 = r4 & r16
            if (r4 == 0) goto L_0x00fb
            if (r6 == r15) goto L_0x00f8
            if (r6 != r14) goto L_0x00fb
            goto L_0x00f8
        L_0x0111:
            int[] r6 = r0.initializations
            if (r6 == 0) goto L_0x0119
            int r4 = r0.getInitializedType(r1, r4)
        L_0x0119:
            int[] r6 = r2.inputStack
            int r9 = r3 + r7
            boolean r4 = merge(r1, r4, r6, r9)
            r8 = r8 | r4
            int r7 = r7 + 1
            goto L_0x00db
        L_0x0125:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.Frame.merge(net.bytebuddy.jar.asm.SymbolTable, net.bytebuddy.jar.asm.Frame, int):boolean");
    }

    private static boolean merge(SymbolTable symbolTable, int i, int[] iArr, int i2) {
        int min;
        int addType;
        int i3 = iArr[i2];
        if (i3 == i) {
            return false;
        }
        if ((268435455 & i) == NULL) {
            if (i3 == NULL) {
                return false;
            }
            i = NULL;
        }
        if (i3 == 0) {
            iArr[i2] = i;
            return true;
        }
        int i4 = i3 & -268435456;
        int i5 = 16777216;
        if (i4 != 0 || (i3 & KIND_MASK) == REFERENCE_KIND) {
            if (i == NULL) {
                return false;
            }
            if ((i & ViewCompat.MEASURED_STATE_MASK) != (-16777216 & i3)) {
                int i6 = i & -268435456;
                if (i6 != 0 || (i & KIND_MASK) == REFERENCE_KIND) {
                    if (!(i6 == 0 || (i & KIND_MASK) == REFERENCE_KIND)) {
                        i6 -= 268435456;
                    }
                    if (!(i4 == 0 || (i3 & KIND_MASK) == REFERENCE_KIND)) {
                        i4 -= 268435456;
                    }
                    min = Math.min(i6, i4) | REFERENCE_KIND;
                    addType = symbolTable.addType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
                }
            } else if ((i3 & KIND_MASK) == REFERENCE_KIND) {
                i5 = (i & -268435456) | REFERENCE_KIND | symbolTable.addMergedType(i & VALUE_MASK, VALUE_MASK & i3);
            } else {
                min = ((i & -268435456) - 268435456) | REFERENCE_KIND;
                addType = symbolTable.addType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
            }
            i5 = min | addType;
        } else if (i3 == NULL) {
            if ((i & -268435456) == 0 && (i & KIND_MASK) != REFERENCE_KIND) {
                i = 16777216;
            }
            i5 = i;
        }
        if (i5 == i3) {
            return false;
        }
        iArr[i2] = i5;
        return true;
    }

    /* access modifiers changed from: package-private */
    public final void accept(MethodWriter methodWriter) {
        int[] iArr = this.inputLocals;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        loop0:
        while (true) {
            int i4 = 0;
            while (true) {
                int i5 = 2;
                if (i2 >= iArr.length) {
                    break loop0;
                }
                int i6 = iArr[i2];
                if (!(i6 == LONG || i6 == DOUBLE)) {
                    i5 = 1;
                }
                i2 += i5;
                if (i6 != 16777216) {
                    break;
                }
                i4++;
            }
            i3 += i4 + 1;
        }
        int[] iArr2 = this.inputStack;
        int i7 = 0;
        int i8 = 0;
        while (i7 < iArr2.length) {
            int i9 = iArr2[i7];
            i7 += (i9 == LONG || i9 == DOUBLE) ? 2 : 1;
            i8++;
        }
        int visitFrameStart = methodWriter.visitFrameStart(this.owner.bytecodeOffset, i3, i8);
        int i10 = 0;
        while (true) {
            int i11 = i3 - 1;
            if (i3 <= 0) {
                break;
            }
            int i12 = iArr[i10];
            i10 += (i12 == LONG || i12 == DOUBLE) ? 2 : 1;
            methodWriter.visitAbstractType(visitFrameStart, i12);
            i3 = i11;
            visitFrameStart++;
        }
        while (true) {
            int i13 = i8 - 1;
            if (i8 > 0) {
                int i14 = iArr2[i];
                i += (i14 == LONG || i14 == DOUBLE) ? 2 : 1;
                methodWriter.visitAbstractType(visitFrameStart, i14);
                visitFrameStart++;
                i8 = i13;
            } else {
                methodWriter.visitFrameEnd();
                return;
            }
        }
    }

    static void putAbstractType(SymbolTable symbolTable, int i, ByteVector byteVector) {
        int i2 = (-268435456 & i) >> 28;
        if (i2 == 0) {
            int i3 = i & VALUE_MASK;
            int i4 = i & KIND_MASK;
            if (i4 == 16777216) {
                byteVector.putByte(i3);
            } else if (i4 == REFERENCE_KIND) {
                byteVector.putByte(7).putShort(symbolTable.addConstantClass(symbolTable.getType(i3).value).index);
            } else if (i4 == UNINITIALIZED_KIND) {
                byteVector.putByte(8).putShort((int) symbolTable.getType(i3).data);
            } else {
                throw new AssertionError();
            }
        } else {
            StringBuilder sb = new StringBuilder();
            while (true) {
                int i5 = i2 - 1;
                if (i2 <= 0) {
                    break;
                }
                sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
                i2 = i5;
            }
            if ((i & KIND_MASK) == REFERENCE_KIND) {
                sb.append('L');
                sb.append(symbolTable.getType(i & VALUE_MASK).value);
                sb.append(';');
            } else {
                int i6 = i & VALUE_MASK;
                if (i6 == 1) {
                    sb.append(ASCIIPropertyListParser.DATA_GSINT_BEGIN_TOKEN);
                } else if (i6 == 2) {
                    sb.append('F');
                } else if (i6 == 3) {
                    sb.append(ASCIIPropertyListParser.DATA_GSDATE_BEGIN_TOKEN);
                } else if (i6 != 4) {
                    switch (i6) {
                        case 9:
                            sb.append(ASCIIPropertyListParser.DATE_APPLE_END_TOKEN);
                            break;
                        case 10:
                            sb.append(ASCIIPropertyListParser.DATA_GSBOOL_BEGIN_TOKEN);
                            break;
                        case 11:
                            sb.append('C');
                            break;
                        case 12:
                            sb.append('S');
                            break;
                        default:
                            throw new AssertionError();
                    }
                } else {
                    sb.append('J');
                }
            }
            byteVector.putByte(7).putShort(symbolTable.addConstantClass(sb.toString()).index);
        }
    }
}

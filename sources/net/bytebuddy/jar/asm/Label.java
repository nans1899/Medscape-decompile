package net.bytebuddy.jar.asm;

import com.google.common.base.Ascii;
import kotlin.UShort;

public class Label {
    static final Label EMPTY_LIST = new Label();
    static final int FLAG_DEBUG_ONLY = 1;
    static final int FLAG_JUMP_TARGET = 2;
    static final int FLAG_REACHABLE = 8;
    static final int FLAG_RESOLVED = 4;
    static final int FLAG_SUBROUTINE_CALLER = 16;
    static final int FLAG_SUBROUTINE_END = 64;
    static final int FLAG_SUBROUTINE_START = 32;
    static final int FORWARD_REFERENCES_CAPACITY_INCREMENT = 6;
    static final int FORWARD_REFERENCE_HANDLE_MASK = 268435455;
    static final int FORWARD_REFERENCE_TYPE_MASK = -268435456;
    static final int FORWARD_REFERENCE_TYPE_SHORT = 268435456;
    static final int FORWARD_REFERENCE_TYPE_WIDE = 536870912;
    static final int LINE_NUMBERS_CAPACITY_INCREMENT = 4;
    int bytecodeOffset;
    short flags;
    private int[] forwardReferences;
    Frame frame;
    public Object info;
    short inputStackSize;
    private short lineNumber;
    Label nextBasicBlock;
    Label nextListElement;
    private int[] otherLineNumbers;
    Edge outgoingEdges;
    short outputStackMax;
    short outputStackSize;
    short subroutineId;

    public int getOffset() {
        if ((this.flags & 4) != 0) {
            return this.bytecodeOffset;
        }
        throw new IllegalStateException("Label offset position has not been resolved yet");
    }

    /* access modifiers changed from: package-private */
    public final Label getCanonicalInstance() {
        Frame frame2 = this.frame;
        return frame2 == null ? this : frame2.owner;
    }

    /* access modifiers changed from: package-private */
    public final void addLineNumber(int i) {
        if (this.lineNumber == 0) {
            this.lineNumber = (short) i;
            return;
        }
        if (this.otherLineNumbers == null) {
            this.otherLineNumbers = new int[4];
        }
        int[] iArr = this.otherLineNumbers;
        int i2 = iArr[0] + 1;
        iArr[0] = i2;
        if (i2 >= iArr.length) {
            int[] iArr2 = new int[(iArr.length + 4)];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.otherLineNumbers = iArr2;
        }
        this.otherLineNumbers[i2] = i;
    }

    /* access modifiers changed from: package-private */
    public final void accept(MethodVisitor methodVisitor, boolean z) {
        short s;
        methodVisitor.visitLabel(this);
        if (z && (s = this.lineNumber) != 0) {
            methodVisitor.visitLineNumber(s & UShort.MAX_VALUE, this);
            if (this.otherLineNumbers != null) {
                int i = 1;
                while (true) {
                    int[] iArr = this.otherLineNumbers;
                    if (i <= iArr[0]) {
                        methodVisitor.visitLineNumber(iArr[i], this);
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void put(ByteVector byteVector, int i, boolean z) {
        if ((this.flags & 4) == 0) {
            if (z) {
                addForwardReference(i, FORWARD_REFERENCE_TYPE_WIDE, byteVector.length);
                byteVector.putInt(-1);
                return;
            }
            addForwardReference(i, FORWARD_REFERENCE_TYPE_SHORT, byteVector.length);
            byteVector.putShort(-1);
        } else if (z) {
            byteVector.putInt(this.bytecodeOffset - i);
        } else {
            byteVector.putShort(this.bytecodeOffset - i);
        }
    }

    private void addForwardReference(int i, int i2, int i3) {
        if (this.forwardReferences == null) {
            this.forwardReferences = new int[6];
        }
        int[] iArr = this.forwardReferences;
        int i4 = iArr[0];
        if (i4 + 2 >= iArr.length) {
            int[] iArr2 = new int[(iArr.length + 6)];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.forwardReferences = iArr2;
        }
        int[] iArr3 = this.forwardReferences;
        int i5 = i4 + 1;
        iArr3[i5] = i;
        int i6 = i5 + 1;
        iArr3[i6] = i2 | i3;
        iArr3[0] = i6;
    }

    /* access modifiers changed from: package-private */
    public final boolean resolve(byte[] bArr, int i) {
        this.flags = (short) (this.flags | 4);
        this.bytecodeOffset = i;
        int[] iArr = this.forwardReferences;
        boolean z = false;
        if (iArr == null) {
            return false;
        }
        for (int i2 = iArr[0]; i2 > 0; i2 -= 2) {
            int[] iArr2 = this.forwardReferences;
            int i3 = iArr2[i2 - 1];
            int i4 = iArr2[i2];
            int i5 = i - i3;
            int i6 = FORWARD_REFERENCE_HANDLE_MASK & i4;
            if ((i4 & FORWARD_REFERENCE_TYPE_MASK) == FORWARD_REFERENCE_TYPE_SHORT) {
                if (i5 < -32768 || i5 > 32767) {
                    byte b = bArr[i3] & 255;
                    if (b < 198) {
                        bArr[i3] = (byte) (b + 49);
                    } else {
                        bArr[i3] = (byte) (b + Ascii.DC4);
                    }
                    z = true;
                }
                bArr[i6] = (byte) (i5 >>> 8);
                bArr[i6 + 1] = (byte) i5;
            } else {
                int i7 = i6 + 1;
                bArr[i6] = (byte) (i5 >>> 24);
                int i8 = i7 + 1;
                bArr[i7] = (byte) (i5 >>> 16);
                bArr[i8] = (byte) (i5 >>> 8);
                bArr[i8 + 1] = (byte) i5;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public final void markSubroutine(short s) {
        this.nextListElement = EMPTY_LIST;
        Label label = this;
        while (label != EMPTY_LIST) {
            Label label2 = label.nextListElement;
            label.nextListElement = null;
            if (label.subroutineId == 0) {
                label.subroutineId = s;
                label = label.pushSuccessors(label2);
            } else {
                label = label2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void addSubroutineRetSuccessors(Label label) {
        Label label2 = EMPTY_LIST;
        this.nextListElement = label2;
        Label label3 = label2;
        Label label4 = this;
        while (label4 != EMPTY_LIST) {
            Label label5 = label4.nextListElement;
            label4.nextListElement = label3;
            if (!((label4.flags & 64) == 0 || label4.subroutineId == label.subroutineId)) {
                label4.outgoingEdges = new Edge(label4.outputStackSize, label.outgoingEdges.successor, label4.outgoingEdges);
            }
            label3 = label4;
            label4 = label4.pushSuccessors(label5);
        }
        while (label3 != EMPTY_LIST) {
            Label label6 = label3.nextListElement;
            label3.nextListElement = null;
            label3 = label6;
        }
    }

    private Label pushSuccessors(Label label) {
        Edge edge = this.outgoingEdges;
        while (edge != null) {
            if (!((this.flags & 16) != 0 && edge == this.outgoingEdges.nextEdge) && edge.successor.nextListElement == null) {
                edge.successor.nextListElement = label;
                label = edge.successor;
            }
            edge = edge.nextEdge;
        }
        return label;
    }

    public String toString() {
        return "L" + System.identityHashCode(this);
    }
}

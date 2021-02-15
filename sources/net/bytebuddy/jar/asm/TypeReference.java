package net.bytebuddy.jar.asm;

import androidx.core.view.MotionEventCompat;

public class TypeReference {
    public static final int CAST = 71;
    public static final int CLASS_EXTENDS = 16;
    public static final int CLASS_TYPE_PARAMETER = 0;
    public static final int CLASS_TYPE_PARAMETER_BOUND = 17;
    public static final int CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT = 72;
    public static final int CONSTRUCTOR_REFERENCE = 69;
    public static final int CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT = 74;
    public static final int EXCEPTION_PARAMETER = 66;
    public static final int FIELD = 19;
    public static final int INSTANCEOF = 67;
    public static final int LOCAL_VARIABLE = 64;
    public static final int METHOD_FORMAL_PARAMETER = 22;
    public static final int METHOD_INVOCATION_TYPE_ARGUMENT = 73;
    public static final int METHOD_RECEIVER = 21;
    public static final int METHOD_REFERENCE = 70;
    public static final int METHOD_REFERENCE_TYPE_ARGUMENT = 75;
    public static final int METHOD_RETURN = 20;
    public static final int METHOD_TYPE_PARAMETER = 1;
    public static final int METHOD_TYPE_PARAMETER_BOUND = 18;
    public static final int NEW = 68;
    public static final int RESOURCE_VARIABLE = 65;
    public static final int THROWS = 23;
    private final int targetTypeAndInfo;

    public TypeReference(int i) {
        this.targetTypeAndInfo = i;
    }

    public static TypeReference newTypeReference(int i) {
        return new TypeReference(i << 24);
    }

    public static TypeReference newTypeParameterReference(int i, int i2) {
        return new TypeReference((i << 24) | (i2 << 16));
    }

    public static TypeReference newTypeParameterBoundReference(int i, int i2, int i3) {
        return new TypeReference((i << 24) | (i2 << 16) | (i3 << 8));
    }

    public static TypeReference newSuperTypeReference(int i) {
        return new TypeReference(((i & 65535) << 8) | 268435456);
    }

    public static TypeReference newFormalParameterReference(int i) {
        return new TypeReference((i << 16) | 369098752);
    }

    public static TypeReference newExceptionReference(int i) {
        return new TypeReference((i << 8) | 385875968);
    }

    public static TypeReference newTryCatchReference(int i) {
        return new TypeReference((i << 8) | 1107296256);
    }

    public static TypeReference newTypeArgumentReference(int i, int i2) {
        return new TypeReference((i << 24) | i2);
    }

    public int getSort() {
        return this.targetTypeAndInfo >>> 24;
    }

    public int getTypeParameterIndex() {
        return (this.targetTypeAndInfo & 16711680) >> 16;
    }

    public int getTypeParameterBoundIndex() {
        return (this.targetTypeAndInfo & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
    }

    public int getSuperTypeIndex() {
        return (short) ((this.targetTypeAndInfo & 16776960) >> 8);
    }

    public int getFormalParameterIndex() {
        return (this.targetTypeAndInfo & 16711680) >> 16;
    }

    public int getExceptionIndex() {
        return (this.targetTypeAndInfo & 16776960) >> 8;
    }

    public int getTryCatchBlockIndex() {
        return (this.targetTypeAndInfo & 16776960) >> 8;
    }

    public int getTypeArgumentIndex() {
        return this.targetTypeAndInfo & 255;
    }

    public int getValue() {
        return this.targetTypeAndInfo;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        r3.put12(r0, (r2 & 16776960) >> 8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void putTarget(int r2, net.bytebuddy.jar.asm.ByteVector r3) {
        /*
            int r0 = r2 >>> 24
            if (r0 == 0) goto L_0x0025
            r1 = 1
            if (r0 == r1) goto L_0x0025
            switch(r0) {
                case 16: goto L_0x001b;
                case 17: goto L_0x001b;
                case 18: goto L_0x001b;
                case 19: goto L_0x0017;
                case 20: goto L_0x0017;
                case 21: goto L_0x0017;
                case 22: goto L_0x0025;
                case 23: goto L_0x001b;
                default: goto L_0x000a;
            }
        L_0x000a:
            switch(r0) {
                case 66: goto L_0x001b;
                case 67: goto L_0x001b;
                case 68: goto L_0x001b;
                case 69: goto L_0x001b;
                case 70: goto L_0x001b;
                case 71: goto L_0x0013;
                case 72: goto L_0x0013;
                case 73: goto L_0x0013;
                case 74: goto L_0x0013;
                case 75: goto L_0x0013;
                default: goto L_0x000d;
            }
        L_0x000d:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            r2.<init>()
            throw r2
        L_0x0013:
            r3.putInt(r2)
            goto L_0x002a
        L_0x0017:
            r3.putByte(r0)
            goto L_0x002a
        L_0x001b:
            r1 = 16776960(0xffff00, float:2.3509528E-38)
            r2 = r2 & r1
            int r2 = r2 >> 8
            r3.put12(r0, r2)
            goto L_0x002a
        L_0x0025:
            int r2 = r2 >>> 16
            r3.putShort(r2)
        L_0x002a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.TypeReference.putTarget(int, net.bytebuddy.jar.asm.ByteVector):void");
    }
}

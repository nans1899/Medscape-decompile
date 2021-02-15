package net.bytebuddy.jar.asm;

import net.bytebuddy.pool.TypePool;

public final class TypePath {
    public static final int ARRAY_ELEMENT = 0;
    public static final int INNER_TYPE = 1;
    public static final int TYPE_ARGUMENT = 3;
    public static final int WILDCARD_BOUND = 2;
    private final byte[] typePathContainer;
    private final int typePathOffset;

    TypePath(byte[] bArr, int i) {
        this.typePathContainer = bArr;
        this.typePathOffset = i;
    }

    public int getLength() {
        return this.typePathContainer[this.typePathOffset];
    }

    public int getStep(int i) {
        return this.typePathContainer[this.typePathOffset + (i * 2) + 1];
    }

    public int getStepArgument(int i) {
        return this.typePathContainer[this.typePathOffset + (i * 2) + 2];
    }

    public static TypePath fromString(String str) {
        int i;
        char charAt;
        if (str == null || str.length() == 0) {
            return null;
        }
        int length = str.length();
        ByteVector byteVector = new ByteVector(length);
        byteVector.putByte(0);
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            char charAt2 = str.charAt(i2);
            if (charAt2 == '[') {
                byteVector.put11(0, 0);
            } else if (charAt2 == '.') {
                byteVector.put11(1, 0);
            } else if (charAt2 == '*') {
                byteVector.put11(2, 0);
            } else if (charAt2 < '0' || charAt2 > '9') {
                throw new IllegalArgumentException();
            } else {
                int i4 = charAt2 - '0';
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    i = i3 + 1;
                    charAt = str.charAt(i3);
                    if (charAt >= '0' && charAt <= '9') {
                        i4 = ((i4 * 10) + charAt) - 48;
                        i3 = i;
                    }
                }
                if (charAt == ';') {
                    i3 = i;
                    byteVector.put11(3, i4);
                } else {
                    throw new IllegalArgumentException();
                }
            }
            i2 = i3;
        }
        byteVector.data[0] = (byte) (byteVector.length / 2);
        return new TypePath(byteVector.data, 0);
    }

    public String toString() {
        int length = getLength();
        StringBuilder sb = new StringBuilder(length * 2);
        for (int i = 0; i < length; i++) {
            int step = getStep(i);
            if (step == 0) {
                sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
            } else if (step == 1) {
                sb.append('.');
            } else if (step == 2) {
                sb.append('*');
            } else if (step == 3) {
                sb.append(getStepArgument(i));
                sb.append(';');
            } else {
                throw new AssertionError();
            }
        }
        return sb.toString();
    }

    static void put(TypePath typePath, ByteVector byteVector) {
        if (typePath == null) {
            byteVector.putByte(0);
            return;
        }
        byte[] bArr = typePath.typePathContainer;
        int i = typePath.typePathOffset;
        byteVector.putByteArray(bArr, i, (bArr[i] * 2) + 1);
    }
}

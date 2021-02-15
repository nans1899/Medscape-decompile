package org.apache.commons.io;

import java.io.Serializable;
import java.util.Locale;
import net.bytebuddy.pool.TypePool;
import org.mozilla.classfile.ByteCode;

public class ByteOrderMark implements Serializable {
    public static final ByteOrderMark UTF_16BE = new ByteOrderMark("UTF-16BE", ByteCode.IMPDEP1, 255);
    public static final ByteOrderMark UTF_16LE = new ByteOrderMark("UTF-16LE", 255, ByteCode.IMPDEP1);
    public static final ByteOrderMark UTF_32BE = new ByteOrderMark("UTF-32BE", 0, 0, ByteCode.IMPDEP1, 255);
    public static final ByteOrderMark UTF_32LE = new ByteOrderMark("UTF-32LE", 255, ByteCode.IMPDEP1, 0, 0);
    public static final ByteOrderMark UTF_8 = new ByteOrderMark("UTF-8", 239, 187, 191);
    public static final char UTF_BOM = '﻿';
    private static final long serialVersionUID = 1;
    private final int[] bytes;
    private final String charsetName;

    public ByteOrderMark(String str, int... iArr) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("No charsetName specified");
        } else if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("No bytes specified");
        } else {
            this.charsetName = str;
            int[] iArr2 = new int[iArr.length];
            this.bytes = iArr2;
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        }
    }

    public String getCharsetName() {
        return this.charsetName;
    }

    public int length() {
        return this.bytes.length;
    }

    public int get(int i) {
        return this.bytes[i];
    }

    public byte[] getBytes() {
        byte[] bArr = new byte[this.bytes.length];
        int i = 0;
        while (true) {
            int[] iArr = this.bytes;
            if (i >= iArr.length) {
                return bArr;
            }
            bArr[i] = (byte) iArr[i];
            i++;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ByteOrderMark)) {
            return false;
        }
        ByteOrderMark byteOrderMark = (ByteOrderMark) obj;
        if (this.bytes.length != byteOrderMark.length()) {
            return false;
        }
        int i = 0;
        while (true) {
            int[] iArr = this.bytes;
            if (i >= iArr.length) {
                return true;
            }
            if (iArr[i] != byteOrderMark.get(i)) {
                return false;
            }
            i++;
        }
    }

    public int hashCode() {
        int hashCode = getClass().hashCode();
        for (int i : this.bytes) {
            hashCode += i;
        }
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        sb.append(this.charsetName);
        sb.append(": ");
        for (int i = 0; i < this.bytes.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append("0x");
            sb.append(Integer.toHexString(this.bytes[i] & 255).toUpperCase(Locale.ROOT));
        }
        sb.append(']');
        return sb.toString();
    }
}

package com.comscore.util;

import com.google.common.base.Ascii;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import okio.Utf8;

public class Base64Coder {
    private static char[] a = new char[64];
    private static byte[] b = new byte[128];

    static {
        char c = 'A';
        int i = 0;
        while (c <= 'Z') {
            a[i] = c;
            c = (char) (c + 1);
            i++;
        }
        char c2 = 'a';
        while (c2 <= 'z') {
            a[i] = c2;
            c2 = (char) (c2 + 1);
            i++;
        }
        char c3 = '0';
        while (c3 <= '9') {
            a[i] = c3;
            c3 = (char) (c3 + 1);
            i++;
        }
        char[] cArr = a;
        cArr[i] = SignatureVisitor.EXTENDS;
        cArr[i + 1] = '/';
        int i2 = 0;
        while (true) {
            byte[] bArr = b;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        for (int i3 = 0; i3 < 64; i3++) {
            b[a[i3]] = (byte) i3;
        }
    }

    private Base64Coder() {
    }

    public static byte[] decode(String str) {
        return decode(str.toCharArray());
    }

    public static byte[] decode(char[] cArr) {
        int i;
        char c;
        int length = cArr.length;
        if (length % 4 == 0) {
            while (length > 0 && cArr[length - 1] == '=') {
                length--;
            }
            int i2 = (length * 3) / 4;
            byte[] bArr = new byte[i2];
            int i3 = 0;
            for (int i4 = 0; i4 < length; i4 = i) {
                int i5 = i4 + 1;
                char c2 = cArr[i4];
                i = i5 + 1;
                char c3 = cArr[i5];
                char c4 = 'A';
                if (i < length) {
                    c = cArr[i];
                    i++;
                } else {
                    c = 'A';
                }
                if (i < length) {
                    c4 = cArr[i];
                    i++;
                }
                if (c2 > 127 || c3 > 127 || c > 127 || c4 > 127) {
                    throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
                }
                byte[] bArr2 = b;
                byte b2 = bArr2[c2];
                byte b3 = bArr2[c3];
                byte b4 = bArr2[c];
                byte b5 = bArr2[c4];
                if (b2 < 0 || b3 < 0 || b4 < 0 || b5 < 0) {
                    throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
                }
                int i6 = (b2 << 2) | (b3 >>> 4);
                int i7 = ((b3 & Ascii.SI) << 4) | (b4 >>> 2);
                byte b6 = b5 | ((b4 & 3) << 6);
                int i8 = i3 + 1;
                bArr[i3] = (byte) i6;
                if (i8 < i2) {
                    bArr[i8] = (byte) i7;
                    i8++;
                }
                if (i8 < i2) {
                    bArr[i8] = (byte) b6;
                    i3 = i8 + 1;
                } else {
                    i3 = i8;
                }
            }
            return bArr;
        }
        throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
    }

    public static String decodeString(String str) {
        return new String(decode(str));
    }

    public static char[] encode(byte[] bArr) {
        return encode(bArr, bArr.length);
    }

    public static char[] encode(byte[] bArr, int i) {
        byte b2;
        byte b3;
        int i2 = ((i * 4) + 2) / 3;
        char[] cArr = new char[(((i + 2) / 3) * 4)];
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            int i5 = i3 + 1;
            byte b4 = bArr[i3] & 255;
            if (i5 < i) {
                b2 = bArr[i5] & 255;
                i5++;
            } else {
                b2 = 0;
            }
            if (i5 < i) {
                b3 = bArr[i5] & 255;
                i5++;
            } else {
                b3 = 0;
            }
            int i6 = b4 >>> 2;
            int i7 = ((b4 & 3) << 4) | (b2 >>> 4);
            int i8 = ((b2 & Ascii.SI) << 2) | (b3 >>> 6);
            byte b5 = b3 & Utf8.REPLACEMENT_BYTE;
            int i9 = i4 + 1;
            char[] cArr2 = a;
            cArr[i4] = cArr2[i6];
            int i10 = i9 + 1;
            cArr[i9] = cArr2[i7];
            char c = '=';
            cArr[i10] = i10 < i2 ? cArr2[i8] : '=';
            int i11 = i10 + 1;
            if (i11 < i2) {
                c = a[b5];
            }
            cArr[i11] = c;
            i4 = i11 + 1;
            i3 = i5;
        }
        return cArr;
    }

    public static String encodeString(String str) {
        return new String(encode(str.getBytes()));
    }
}

package okio.internal;

import com.fasterxml.jackson.core.base.GeneratorBase;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Utf8;
import org.jetbrains.anko.DimensionsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001e\u0010\u0003\u001a\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"commonAsUtf8ToByteArray", "", "", "commonToUtf8String", "beginIndex", "", "endIndex", "okio"}, k = 2, mv = {1, 4, 0})
/* compiled from: -Utf8.kt */
public final class _Utf8Kt {
    public static /* synthetic */ String commonToUtf8String$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        return commonToUtf8String(bArr, i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x009d, code lost:
        if (((r0[r5] & 192) == 128) == false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x011e, code lost:
        if (((r0[r5] & 192) == 128) == false) goto L_0x0124;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String commonToUtf8String(byte[] r16, int r17, int r18) {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            java.lang.String r3 = "$this$commonToUtf8String"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r3)
            if (r1 < 0) goto L_0x01c2
            int r3 = r0.length
            if (r2 > r3) goto L_0x01c2
            if (r1 > r2) goto L_0x01c2
            int r3 = r2 - r1
            char[] r3 = new char[r3]
            r4 = 0
            r5 = 0
        L_0x0018:
            if (r1 >= r2) goto L_0x01bc
            byte r6 = r0[r1]
            if (r6 < 0) goto L_0x0039
            char r6 = (char) r6
            int r7 = r5 + 1
            r3[r5] = r6
            int r1 = r1 + 1
        L_0x0025:
            if (r1 >= r2) goto L_0x0037
            byte r5 = r0[r1]
            if (r5 < 0) goto L_0x0037
            int r5 = r1 + 1
            byte r1 = r0[r1]
            char r1 = (char) r1
            int r6 = r7 + 1
            r3[r7] = r1
            r1 = r5
            r7 = r6
            goto L_0x0025
        L_0x0037:
            r5 = r7
            goto L_0x0018
        L_0x0039:
            int r7 = r6 >> 5
            r8 = -2
            r10 = 128(0x80, float:1.794E-43)
            r11 = 65533(0xfffd, float:9.1831E-41)
            if (r7 != r8) goto L_0x007a
            int r6 = r1 + 1
            if (r2 > r6) goto L_0x0050
            char r6 = (char) r11
            int r7 = r5 + 1
            r3[r5] = r6
        L_0x004c:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
        L_0x004e:
            r9 = 1
            goto L_0x0078
        L_0x0050:
            byte r7 = r0[r1]
            byte r6 = r0[r6]
            r8 = r6 & 192(0xc0, float:2.69E-43)
            if (r8 != r10) goto L_0x005a
            r8 = 1
            goto L_0x005b
        L_0x005a:
            r8 = 0
        L_0x005b:
            if (r8 != 0) goto L_0x0063
            char r6 = (char) r11
            int r7 = r5 + 1
            r3[r5] = r6
            goto L_0x004c
        L_0x0063:
            r6 = r6 ^ 3968(0xf80, float:5.56E-42)
            int r7 = r7 << 6
            r6 = r6 ^ r7
            if (r6 >= r10) goto L_0x0070
            char r6 = (char) r11
            int r7 = r5 + 1
            r3[r5] = r6
            goto L_0x0075
        L_0x0070:
            char r6 = (char) r6
            int r7 = r5 + 1
            r3[r5] = r6
        L_0x0075:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
        L_0x0077:
            r9 = 2
        L_0x0078:
            int r1 = r1 + r9
            goto L_0x0037
        L_0x007a:
            int r7 = r6 >> 4
            r13 = 55296(0xd800, float:7.7486E-41)
            r14 = 57343(0xdfff, float:8.0355E-41)
            r15 = 3
            if (r7 != r8) goto L_0x00f3
            int r6 = r1 + 2
            if (r2 > r6) goto L_0x00a0
            char r6 = (char) r11
            int r7 = r5 + 1
            r3[r5] = r6
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            int r5 = r1 + 1
            if (r2 <= r5) goto L_0x004e
            byte r5 = r0[r5]
            r5 = r5 & 192(0xc0, float:2.69E-43)
            if (r5 != r10) goto L_0x009c
            r5 = 1
            goto L_0x009d
        L_0x009c:
            r5 = 0
        L_0x009d:
            if (r5 != 0) goto L_0x0077
            goto L_0x004e
        L_0x00a0:
            byte r7 = r0[r1]
            int r8 = r1 + 1
            byte r8 = r0[r8]
            r9 = r8 & 192(0xc0, float:2.69E-43)
            if (r9 != r10) goto L_0x00ac
            r9 = 1
            goto L_0x00ad
        L_0x00ac:
            r9 = 0
        L_0x00ad:
            if (r9 != 0) goto L_0x00b7
            char r6 = (char) r11
            int r7 = r5 + 1
            r3[r5] = r6
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            goto L_0x004e
        L_0x00b7:
            byte r6 = r0[r6]
            r9 = r6 & 192(0xc0, float:2.69E-43)
            if (r9 != r10) goto L_0x00bf
            r12 = 1
            goto L_0x00c0
        L_0x00bf:
            r12 = 0
        L_0x00c0:
            if (r12 != 0) goto L_0x00ca
            char r6 = (char) r11
            int r7 = r5 + 1
            r3[r5] = r6
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            goto L_0x0077
        L_0x00ca:
            r9 = -123008(0xfffffffffffe1f80, float:NaN)
            r6 = r6 ^ r9
            int r8 = r8 << 6
            r6 = r6 ^ r8
            int r7 = r7 << 12
            r6 = r6 ^ r7
            r7 = 2048(0x800, float:2.87E-42)
            if (r6 >= r7) goto L_0x00e0
            char r6 = (char) r11
            int r7 = r5 + 1
            r3[r5] = r6
        L_0x00dd:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            goto L_0x00f1
        L_0x00e0:
            if (r13 <= r6) goto L_0x00e3
            goto L_0x00eb
        L_0x00e3:
            if (r14 < r6) goto L_0x00eb
            char r6 = (char) r11
            int r7 = r5 + 1
            r3[r5] = r6
            goto L_0x00dd
        L_0x00eb:
            char r6 = (char) r6
            int r7 = r5 + 1
            r3[r5] = r6
            goto L_0x00dd
        L_0x00f1:
            r9 = 3
            goto L_0x0078
        L_0x00f3:
            int r6 = r6 >> 3
            if (r6 != r8) goto L_0x01b3
            int r6 = r1 + 3
            if (r2 > r6) goto L_0x012a
            int r6 = r5 + 1
            r3[r5] = r11
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            int r5 = r1 + 1
            if (r2 <= r5) goto L_0x0127
            byte r5 = r0[r5]
            r5 = r5 & 192(0xc0, float:2.69E-43)
            if (r5 != r10) goto L_0x010d
            r5 = 1
            goto L_0x010e
        L_0x010d:
            r5 = 0
        L_0x010e:
            if (r5 != 0) goto L_0x0111
            goto L_0x0127
        L_0x0111:
            int r5 = r1 + 2
            if (r2 <= r5) goto L_0x0124
            byte r5 = r0[r5]
            r5 = r5 & 192(0xc0, float:2.69E-43)
            if (r5 != r10) goto L_0x011d
            r12 = 1
            goto L_0x011e
        L_0x011d:
            r12 = 0
        L_0x011e:
            if (r12 != 0) goto L_0x0121
            goto L_0x0124
        L_0x0121:
            r9 = 3
            goto L_0x01b1
        L_0x0124:
            r9 = 2
            goto L_0x01b1
        L_0x0127:
            r9 = 1
            goto L_0x01b1
        L_0x012a:
            byte r7 = r0[r1]
            int r8 = r1 + 1
            byte r8 = r0[r8]
            r9 = r8 & 192(0xc0, float:2.69E-43)
            if (r9 != r10) goto L_0x0136
            r9 = 1
            goto L_0x0137
        L_0x0136:
            r9 = 0
        L_0x0137:
            if (r9 != 0) goto L_0x0140
            int r6 = r5 + 1
            r3[r5] = r11
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            goto L_0x0127
        L_0x0140:
            int r9 = r1 + 2
            byte r9 = r0[r9]
            r12 = r9 & 192(0xc0, float:2.69E-43)
            if (r12 != r10) goto L_0x014a
            r12 = 1
            goto L_0x014b
        L_0x014a:
            r12 = 0
        L_0x014b:
            if (r12 != 0) goto L_0x0154
            int r6 = r5 + 1
            r3[r5] = r11
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            goto L_0x0124
        L_0x0154:
            byte r6 = r0[r6]
            r12 = r6 & 192(0xc0, float:2.69E-43)
            if (r12 != r10) goto L_0x015c
            r12 = 1
            goto L_0x015d
        L_0x015c:
            r12 = 0
        L_0x015d:
            if (r12 != 0) goto L_0x0166
            int r6 = r5 + 1
            r3[r5] = r11
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            goto L_0x0121
        L_0x0166:
            r10 = 3678080(0x381f80, float:5.154088E-39)
            r6 = r6 ^ r10
            int r9 = r9 << 6
            r6 = r6 ^ r9
            int r8 = r8 << 12
            r6 = r6 ^ r8
            int r7 = r7 << 18
            r6 = r6 ^ r7
            r7 = 1114111(0x10ffff, float:1.561202E-39)
            if (r6 <= r7) goto L_0x017f
            int r6 = r5 + 1
            r3[r5] = r11
        L_0x017c:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            goto L_0x01b0
        L_0x017f:
            if (r13 <= r6) goto L_0x0182
            goto L_0x0189
        L_0x0182:
            if (r14 < r6) goto L_0x0189
            int r6 = r5 + 1
            r3[r5] = r11
            goto L_0x017c
        L_0x0189:
            r7 = 65536(0x10000, float:9.18355E-41)
            if (r6 >= r7) goto L_0x0192
            int r6 = r5 + 1
            r3[r5] = r11
            goto L_0x017c
        L_0x0192:
            if (r6 == r11) goto L_0x01ab
            int r7 = r6 >>> 10
            r8 = 55232(0xd7c0, float:7.7397E-41)
            int r7 = r7 + r8
            char r7 = (char) r7
            int r8 = r5 + 1
            r3[r5] = r7
            r5 = r6 & 1023(0x3ff, float:1.434E-42)
            r6 = 56320(0xdc00, float:7.8921E-41)
            int r5 = r5 + r6
            char r5 = (char) r5
            int r6 = r8 + 1
            r3[r8] = r5
            goto L_0x017c
        L_0x01ab:
            int r6 = r5 + 1
            r3[r5] = r11
            goto L_0x017c
        L_0x01b0:
            r9 = 4
        L_0x01b1:
            int r1 = r1 + r9
            goto L_0x01b9
        L_0x01b3:
            int r6 = r5 + 1
            r3[r5] = r11
            int r1 = r1 + 1
        L_0x01b9:
            r5 = r6
            goto L_0x0018
        L_0x01bc:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r3, r4, r5)
            return r0
        L_0x01c2:
            java.lang.ArrayIndexOutOfBoundsException r3 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "size="
            r4.append(r5)
            int r0 = r0.length
            r4.append(r0)
            java.lang.String r0 = " beginIndex="
            r4.append(r0)
            r4.append(r1)
            java.lang.String r0 = " endIndex="
            r4.append(r0)
            r4.append(r2)
            java.lang.String r0 = r4.toString()
            r3.<init>(r0)
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal._Utf8Kt.commonToUtf8String(byte[], int, int):java.lang.String");
    }

    public static final byte[] commonAsUtf8ToByteArray(String str) {
        int i;
        int i2;
        int i3;
        char charAt;
        int i4;
        Intrinsics.checkNotNullParameter(str, "$this$commonAsUtf8ToByteArray");
        byte[] bArr = new byte[(str.length() * 4)];
        int length = str.length();
        int i5 = 0;
        while (i < length) {
            char charAt2 = str.charAt(i);
            if (Intrinsics.compare((int) charAt2, 128) >= 0) {
                int length2 = str.length();
                int i6 = i;
                while (i < length2) {
                    char charAt3 = str.charAt(i);
                    if (Intrinsics.compare((int) charAt3, 128) < 0) {
                        int i7 = i6 + 1;
                        bArr[i6] = (byte) charAt3;
                        i++;
                        while (i < length2 && Intrinsics.compare((int) str.charAt(i), 128) < 0) {
                            bArr[i7] = (byte) str.charAt(i);
                            i++;
                            i7++;
                        }
                        i6 = i7;
                    } else {
                        if (Intrinsics.compare((int) charAt3, 2048) < 0) {
                            int i8 = i6 + 1;
                            bArr[i6] = (byte) ((charAt3 >> 6) | 192);
                            i2 = i8 + 1;
                            bArr[i8] = (byte) ((charAt3 & '?') | 128);
                        } else if (55296 > charAt3 || 57343 < charAt3) {
                            int i9 = i6 + 1;
                            bArr[i6] = (byte) ((charAt3 >> 12) | 224);
                            int i10 = i9 + 1;
                            bArr[i9] = (byte) (((charAt3 >> 6) & 63) | 128);
                            i2 = i10 + 1;
                            bArr[i10] = (byte) ((charAt3 & '?') | 128);
                        } else if (Intrinsics.compare((int) charAt3, (int) GeneratorBase.SURR1_LAST) > 0 || length2 <= (i3 = i + 1) || 56320 > (charAt = str.charAt(i3)) || 57343 < charAt) {
                            i2 = i6 + 1;
                            bArr[i6] = Utf8.REPLACEMENT_BYTE;
                        } else {
                            int charAt4 = ((charAt3 << 10) + str.charAt(i3)) - 56613888;
                            int i11 = i6 + 1;
                            bArr[i6] = (byte) ((charAt4 >> 18) | DimensionsKt.HDPI);
                            int i12 = i11 + 1;
                            bArr[i11] = (byte) (((charAt4 >> 12) & 63) | 128);
                            int i13 = i12 + 1;
                            bArr[i12] = (byte) (((charAt4 >> 6) & 63) | 128);
                            i2 = i13 + 1;
                            bArr[i13] = (byte) ((charAt4 & 63) | 128);
                            i4 = i + 2;
                            i6 = i2;
                        }
                        i4 = i + 1;
                        i6 = i2;
                    }
                }
                byte[] copyOf = Arrays.copyOf(bArr, i6);
                Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                return copyOf;
            }
            bArr[i] = (byte) charAt2;
            i5 = i + 1;
        }
        byte[] copyOf2 = Arrays.copyOf(bArr, str.length());
        Intrinsics.checkNotNullExpressionValue(copyOf2, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf2;
    }
}

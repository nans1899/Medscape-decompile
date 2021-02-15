package okio;

import com.fasterxml.jackson.core.base.GeneratorBase;
import com.google.common.base.Ascii;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.DimensionsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0001H\b\u001a\u0011\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0007H\b\u001a4\u0010\u0010\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\bø\u0001\u0000\u001a4\u0010\u0017\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\bø\u0001\u0000\u001a4\u0010\u0018\u001a\u00020\u0001*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\bø\u0001\u0000\u001a4\u0010\u0019\u001a\u00020\u0016*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00160\u0015H\bø\u0001\u0000\u001a4\u0010\u001a\u001a\u00020\u0016*\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00160\u0015H\bø\u0001\u0000\u001a4\u0010\u001c\u001a\u00020\u0016*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u0015H\bø\u0001\u0000\u001a%\u0010\u001d\u001a\u00020\u001e*\u00020\u001b2\b\b\u0002\u0010\u0012\u001a\u00020\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u0001H\u0007¢\u0006\u0002\b\u001f\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006 "}, d2 = {"HIGH_SURROGATE_HEADER", "", "LOG_SURROGATE_HEADER", "MASK_2BYTES", "MASK_3BYTES", "MASK_4BYTES", "REPLACEMENT_BYTE", "", "REPLACEMENT_CHARACTER", "", "REPLACEMENT_CODE_POINT", "isIsoControl", "", "codePoint", "isUtf8Continuation", "byte", "process2Utf8Bytes", "", "beginIndex", "endIndex", "yield", "Lkotlin/Function1;", "", "process3Utf8Bytes", "process4Utf8Bytes", "processUtf16Chars", "processUtf8Bytes", "", "processUtf8CodePoints", "utf8Size", "", "size", "okio"}, k = 2, mv = {1, 4, 0})
/* compiled from: Utf8.kt */
public final class Utf8 {
    public static final int HIGH_SURROGATE_HEADER = 55232;
    public static final int LOG_SURROGATE_HEADER = 56320;
    public static final int MASK_2BYTES = 3968;
    public static final int MASK_3BYTES = -123008;
    public static final int MASK_4BYTES = 3678080;
    public static final byte REPLACEMENT_BYTE = 63;
    public static final char REPLACEMENT_CHARACTER = '�';
    public static final int REPLACEMENT_CODE_POINT = 65533;

    public static final boolean isIsoControl(int i) {
        return (i >= 0 && 31 >= i) || (127 <= i && 159 >= i);
    }

    public static final boolean isUtf8Continuation(byte b) {
        return (b & 192) == 128;
    }

    public static final long size(String str) {
        return size$default(str, 0, 0, 3, (Object) null);
    }

    public static final long size(String str, int i) {
        return size$default(str, i, 0, 2, (Object) null);
    }

    public static /* synthetic */ long size$default(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return size(str, i, i2);
    }

    public static final long size(String str, int i, int i2) {
        int i3;
        char c;
        Intrinsics.checkNotNullParameter(str, "$this$utf8Size");
        boolean z = true;
        if (i >= 0) {
            if (i2 >= i) {
                if (i2 > str.length()) {
                    z = false;
                }
                if (z) {
                    long j = 0;
                    while (i < i2) {
                        char charAt = str.charAt(i);
                        if (charAt < 128) {
                            j++;
                        } else {
                            if (charAt < 2048) {
                                i3 = 2;
                            } else if (charAt < 55296 || charAt > 57343) {
                                i3 = 3;
                            } else {
                                int i4 = i + 1;
                                if (i4 < i2) {
                                    c = str.charAt(i4);
                                } else {
                                    c = 0;
                                }
                                if (charAt > 56319 || c < 56320 || c > 57343) {
                                    j++;
                                    i = i4;
                                } else {
                                    j += (long) 4;
                                    i += 2;
                                }
                            }
                            j += (long) i3;
                        }
                        i++;
                    }
                    return j;
                }
                throw new IllegalArgumentException(("endIndex > string.length: " + i2 + " > " + str.length()).toString());
            }
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i2 + " < " + i).toString());
        }
        throw new IllegalArgumentException(("beginIndex < 0: " + i).toString());
    }

    public static final void processUtf8Bytes(String str, int i, int i2, Function1<? super Byte, Unit> function1) {
        int i3;
        char charAt;
        Intrinsics.checkNotNullParameter(str, "$this$processUtf8Bytes");
        Intrinsics.checkNotNullParameter(function1, "yield");
        while (i < i2) {
            char charAt2 = str.charAt(i);
            if (Intrinsics.compare((int) charAt2, 128) < 0) {
                function1.invoke(Byte.valueOf((byte) charAt2));
                i++;
                while (i < i2 && Intrinsics.compare((int) str.charAt(i), 128) < 0) {
                    function1.invoke(Byte.valueOf((byte) str.charAt(i)));
                    i++;
                }
            } else {
                if (Intrinsics.compare((int) charAt2, 2048) < 0) {
                    function1.invoke(Byte.valueOf((byte) ((charAt2 >> 6) | 192)));
                    function1.invoke(Byte.valueOf((byte) ((charAt2 & '?') | 128)));
                } else if (55296 > charAt2 || 57343 < charAt2) {
                    function1.invoke(Byte.valueOf((byte) ((charAt2 >> 12) | 224)));
                    function1.invoke(Byte.valueOf((byte) (((charAt2 >> 6) & 63) | 128)));
                    function1.invoke(Byte.valueOf((byte) ((charAt2 & '?') | 128)));
                } else if (Intrinsics.compare((int) charAt2, (int) GeneratorBase.SURR1_LAST) > 0 || i2 <= (i3 = i + 1) || 56320 > (charAt = str.charAt(i3)) || 57343 < charAt) {
                    function1.invoke(Byte.valueOf(REPLACEMENT_BYTE));
                } else {
                    int charAt3 = ((charAt2 << 10) + str.charAt(i3)) - 56613888;
                    function1.invoke(Byte.valueOf((byte) ((charAt3 >> 18) | DimensionsKt.HDPI)));
                    function1.invoke(Byte.valueOf((byte) (((charAt3 >> 12) & 63) | 128)));
                    function1.invoke(Byte.valueOf((byte) (((charAt3 >> 6) & 63) | 128)));
                    function1.invoke(Byte.valueOf((byte) ((charAt3 & 63) | 128)));
                    i += 2;
                }
                i++;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0096, code lost:
        if (r8 == false) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0117, code lost:
        if (r8 == false) goto L_0x0070;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void processUtf8CodePoints(byte[] r16, int r17, int r18, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> r19) {
        /*
            r0 = r16
            r1 = r18
            r2 = r19
            java.lang.String r3 = "$this$processUtf8CodePoints"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r3)
            java.lang.String r3 = "yield"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r3)
            r3 = r17
        L_0x0012:
            if (r3 >= r1) goto L_0x019a
            byte r4 = r0[r3]
            if (r4 < 0) goto L_0x0034
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2.invoke(r4)
            int r3 = r3 + 1
        L_0x0021:
            if (r3 >= r1) goto L_0x0012
            byte r4 = r0[r3]
            if (r4 < 0) goto L_0x0012
            int r4 = r3 + 1
            byte r3 = r0[r3]
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2.invoke(r3)
            r3 = r4
            goto L_0x0021
        L_0x0034:
            int r5 = r4 >> 5
            r6 = -2
            r8 = 0
            r9 = 128(0x80, float:1.794E-43)
            r10 = 65533(0xfffd, float:9.1831E-41)
            r11 = 1
            if (r5 != r6) goto L_0x0073
            int r4 = r3 + 1
            if (r1 > r4) goto L_0x004f
        L_0x0044:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
        L_0x004d:
            r7 = 1
            goto L_0x0071
        L_0x004f:
            byte r5 = r0[r3]
            byte r4 = r0[r4]
            r6 = r4 & 192(0xc0, float:2.69E-43)
            if (r6 != r9) goto L_0x0058
            r8 = 1
        L_0x0058:
            if (r8 != 0) goto L_0x005b
            goto L_0x0044
        L_0x005b:
            r4 = r4 ^ 3968(0xf80, float:5.56E-42)
            int r5 = r5 << 6
            r4 = r4 ^ r5
            if (r4 >= r9) goto L_0x0067
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            goto L_0x006b
        L_0x0067:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
        L_0x006b:
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
        L_0x0070:
            r7 = 2
        L_0x0071:
            int r3 = r3 + r7
            goto L_0x0012
        L_0x0073:
            int r5 = r4 >> 4
            r12 = 55296(0xd800, float:7.7486E-41)
            r13 = 57343(0xdfff, float:8.0355E-41)
            r14 = 3
            if (r5 != r6) goto L_0x00ea
            int r4 = r3 + 2
            if (r1 > r4) goto L_0x0099
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            int r4 = r3 + 1
            if (r1 <= r4) goto L_0x004d
            byte r4 = r0[r4]
            r4 = r4 & 192(0xc0, float:2.69E-43)
            if (r4 != r9) goto L_0x0096
            r8 = 1
        L_0x0096:
            if (r8 != 0) goto L_0x0070
            goto L_0x004d
        L_0x0099:
            byte r5 = r0[r3]
            int r6 = r3 + 1
            byte r6 = r0[r6]
            r15 = r6 & 192(0xc0, float:2.69E-43)
            if (r15 != r9) goto L_0x00a5
            r15 = 1
            goto L_0x00a6
        L_0x00a5:
            r15 = 0
        L_0x00a6:
            if (r15 != 0) goto L_0x00b2
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x004d
        L_0x00b2:
            byte r4 = r0[r4]
            r15 = r4 & 192(0xc0, float:2.69E-43)
            if (r15 != r9) goto L_0x00b9
            r8 = 1
        L_0x00b9:
            if (r8 != 0) goto L_0x00c5
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x0070
        L_0x00c5:
            r7 = -123008(0xfffffffffffe1f80, float:NaN)
            r4 = r4 ^ r7
            int r6 = r6 << 6
            r4 = r4 ^ r6
            int r5 = r5 << 12
            r4 = r4 ^ r5
            r5 = 2048(0x800, float:2.87E-42)
            if (r4 >= r5) goto L_0x00dd
        L_0x00d3:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
        L_0x00d7:
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x00e8
        L_0x00dd:
            if (r12 <= r4) goto L_0x00e0
            goto L_0x00e3
        L_0x00e0:
            if (r13 < r4) goto L_0x00e3
            goto L_0x00d3
        L_0x00e3:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            goto L_0x00d7
        L_0x00e8:
            r7 = 3
            goto L_0x0071
        L_0x00ea:
            int r4 = r4 >> 3
            if (r4 != r6) goto L_0x018f
            int r4 = r3 + 3
            if (r1 > r4) goto L_0x011b
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            int r4 = r3 + 1
            if (r1 <= r4) goto L_0x004d
            byte r4 = r0[r4]
            r4 = r4 & 192(0xc0, float:2.69E-43)
            if (r4 != r9) goto L_0x0107
            r4 = 1
            goto L_0x0108
        L_0x0107:
            r4 = 0
        L_0x0108:
            if (r4 != 0) goto L_0x010c
            goto L_0x004d
        L_0x010c:
            int r4 = r3 + 2
            if (r1 <= r4) goto L_0x0070
            byte r4 = r0[r4]
            r4 = r4 & 192(0xc0, float:2.69E-43)
            if (r4 != r9) goto L_0x0117
            r8 = 1
        L_0x0117:
            if (r8 != 0) goto L_0x00e8
            goto L_0x0070
        L_0x011b:
            byte r5 = r0[r3]
            int r6 = r3 + 1
            byte r6 = r0[r6]
            r15 = r6 & 192(0xc0, float:2.69E-43)
            if (r15 != r9) goto L_0x0127
            r15 = 1
            goto L_0x0128
        L_0x0127:
            r15 = 0
        L_0x0128:
            if (r15 != 0) goto L_0x0135
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x004d
        L_0x0135:
            int r15 = r3 + 2
            byte r15 = r0[r15]
            r7 = r15 & 192(0xc0, float:2.69E-43)
            if (r7 != r9) goto L_0x013f
            r7 = 1
            goto L_0x0140
        L_0x013f:
            r7 = 0
        L_0x0140:
            if (r7 != 0) goto L_0x014d
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x0070
        L_0x014d:
            byte r4 = r0[r4]
            r7 = r4 & 192(0xc0, float:2.69E-43)
            if (r7 != r9) goto L_0x0154
            r8 = 1
        L_0x0154:
            if (r8 != 0) goto L_0x0160
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x00e8
        L_0x0160:
            r7 = 3678080(0x381f80, float:5.154088E-39)
            r4 = r4 ^ r7
            int r7 = r15 << 6
            r4 = r4 ^ r7
            int r6 = r6 << 12
            r4 = r4 ^ r6
            int r5 = r5 << 18
            r4 = r4 ^ r5
            r5 = 1114111(0x10ffff, float:1.561202E-39)
            if (r4 <= r5) goto L_0x017c
        L_0x0172:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
        L_0x0176:
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x018c
        L_0x017c:
            if (r12 <= r4) goto L_0x017f
            goto L_0x0182
        L_0x017f:
            if (r13 < r4) goto L_0x0182
            goto L_0x0172
        L_0x0182:
            r5 = 65536(0x10000, float:9.18355E-41)
            if (r4 >= r5) goto L_0x0187
            goto L_0x0172
        L_0x0187:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            goto L_0x0176
        L_0x018c:
            r7 = 4
            goto L_0x0071
        L_0x018f:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r2.invoke(r4)
            int r3 = r3 + 1
            goto L_0x0012
        L_0x019a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Utf8.processUtf8CodePoints(byte[], int, int, kotlin.jvm.functions.Function1):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0098, code lost:
        if (r8 == false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0119, code lost:
        if (r8 == false) goto L_0x0071;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void processUtf16Chars(byte[] r16, int r17, int r18, kotlin.jvm.functions.Function1<? super java.lang.Character, kotlin.Unit> r19) {
        /*
            r0 = r16
            r1 = r18
            r2 = r19
            java.lang.String r3 = "$this$processUtf16Chars"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r3)
            java.lang.String r3 = "yield"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r3)
            r3 = r17
        L_0x0012:
            if (r3 >= r1) goto L_0x01b6
            byte r4 = r0[r3]
            if (r4 < 0) goto L_0x0036
            char r4 = (char) r4
            java.lang.Character r4 = java.lang.Character.valueOf(r4)
            r2.invoke(r4)
            int r3 = r3 + 1
        L_0x0022:
            if (r3 >= r1) goto L_0x0012
            byte r4 = r0[r3]
            if (r4 < 0) goto L_0x0012
            int r4 = r3 + 1
            byte r3 = r0[r3]
            char r3 = (char) r3
            java.lang.Character r3 = java.lang.Character.valueOf(r3)
            r2.invoke(r3)
            r3 = r4
            goto L_0x0022
        L_0x0036:
            int r5 = r4 >> 5
            r6 = -2
            r8 = 0
            r9 = 128(0x80, float:1.794E-43)
            r10 = 65533(0xfffd, float:9.1831E-41)
            r11 = 1
            if (r5 != r6) goto L_0x0074
            int r4 = r3 + 1
            if (r1 > r4) goto L_0x0052
        L_0x0046:
            char r4 = (char) r10
            java.lang.Character r4 = java.lang.Character.valueOf(r4)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
        L_0x0050:
            r7 = 1
            goto L_0x0072
        L_0x0052:
            byte r5 = r0[r3]
            byte r4 = r0[r4]
            r6 = r4 & 192(0xc0, float:2.69E-43)
            if (r6 != r9) goto L_0x005b
            r8 = 1
        L_0x005b:
            if (r8 != 0) goto L_0x005e
            goto L_0x0046
        L_0x005e:
            r4 = r4 ^ 3968(0xf80, float:5.56E-42)
            int r5 = r5 << 6
            r4 = r4 ^ r5
            if (r4 >= r9) goto L_0x0067
            char r4 = (char) r10
            goto L_0x0068
        L_0x0067:
            char r4 = (char) r4
        L_0x0068:
            java.lang.Character r4 = java.lang.Character.valueOf(r4)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
        L_0x0071:
            r7 = 2
        L_0x0072:
            int r3 = r3 + r7
            goto L_0x0012
        L_0x0074:
            int r5 = r4 >> 4
            r12 = 55296(0xd800, float:7.7486E-41)
            r13 = 57343(0xdfff, float:8.0355E-41)
            r14 = 3
            if (r5 != r6) goto L_0x00ec
            int r4 = r3 + 2
            if (r1 > r4) goto L_0x009b
            char r4 = (char) r10
            java.lang.Character r4 = java.lang.Character.valueOf(r4)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            int r4 = r3 + 1
            if (r1 <= r4) goto L_0x0050
            byte r4 = r0[r4]
            r4 = r4 & 192(0xc0, float:2.69E-43)
            if (r4 != r9) goto L_0x0098
            r8 = 1
        L_0x0098:
            if (r8 != 0) goto L_0x0071
            goto L_0x0050
        L_0x009b:
            byte r5 = r0[r3]
            int r6 = r3 + 1
            byte r6 = r0[r6]
            r15 = r6 & 192(0xc0, float:2.69E-43)
            if (r15 != r9) goto L_0x00a7
            r15 = 1
            goto L_0x00a8
        L_0x00a7:
            r15 = 0
        L_0x00a8:
            if (r15 != 0) goto L_0x00b5
            char r4 = (char) r10
            java.lang.Character r4 = java.lang.Character.valueOf(r4)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x0050
        L_0x00b5:
            byte r4 = r0[r4]
            r15 = r4 & 192(0xc0, float:2.69E-43)
            if (r15 != r9) goto L_0x00bc
            r8 = 1
        L_0x00bc:
            if (r8 != 0) goto L_0x00c9
            char r4 = (char) r10
            java.lang.Character r4 = java.lang.Character.valueOf(r4)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x0071
        L_0x00c9:
            r7 = -123008(0xfffffffffffe1f80, float:NaN)
            r4 = r4 ^ r7
            int r6 = r6 << 6
            r4 = r4 ^ r6
            int r5 = r5 << 12
            r4 = r4 ^ r5
            r5 = 2048(0x800, float:2.87E-42)
            if (r4 >= r5) goto L_0x00e2
        L_0x00d7:
            char r4 = (char) r10
        L_0x00d8:
            java.lang.Character r4 = java.lang.Character.valueOf(r4)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x00ea
        L_0x00e2:
            if (r12 <= r4) goto L_0x00e5
            goto L_0x00e8
        L_0x00e5:
            if (r13 < r4) goto L_0x00e8
            goto L_0x00d7
        L_0x00e8:
            char r4 = (char) r4
            goto L_0x00d8
        L_0x00ea:
            r7 = 3
            goto L_0x0072
        L_0x00ec:
            int r4 = r4 >> 3
            if (r4 != r6) goto L_0x01ab
            int r4 = r3 + 3
            if (r1 > r4) goto L_0x011d
            java.lang.Character r4 = java.lang.Character.valueOf(r10)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            int r4 = r3 + 1
            if (r1 <= r4) goto L_0x0050
            byte r4 = r0[r4]
            r4 = r4 & 192(0xc0, float:2.69E-43)
            if (r4 != r9) goto L_0x0109
            r4 = 1
            goto L_0x010a
        L_0x0109:
            r4 = 0
        L_0x010a:
            if (r4 != 0) goto L_0x010e
            goto L_0x0050
        L_0x010e:
            int r4 = r3 + 2
            if (r1 <= r4) goto L_0x0071
            byte r4 = r0[r4]
            r4 = r4 & 192(0xc0, float:2.69E-43)
            if (r4 != r9) goto L_0x0119
            r8 = 1
        L_0x0119:
            if (r8 != 0) goto L_0x00ea
            goto L_0x0071
        L_0x011d:
            byte r5 = r0[r3]
            int r6 = r3 + 1
            byte r6 = r0[r6]
            r15 = r6 & 192(0xc0, float:2.69E-43)
            if (r15 != r9) goto L_0x0129
            r15 = 1
            goto L_0x012a
        L_0x0129:
            r15 = 0
        L_0x012a:
            if (r15 != 0) goto L_0x0137
            java.lang.Character r4 = java.lang.Character.valueOf(r10)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x0050
        L_0x0137:
            int r15 = r3 + 2
            byte r15 = r0[r15]
            r7 = r15 & 192(0xc0, float:2.69E-43)
            if (r7 != r9) goto L_0x0141
            r7 = 1
            goto L_0x0142
        L_0x0141:
            r7 = 0
        L_0x0142:
            if (r7 != 0) goto L_0x014f
            java.lang.Character r4 = java.lang.Character.valueOf(r10)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x0071
        L_0x014f:
            byte r4 = r0[r4]
            r7 = r4 & 192(0xc0, float:2.69E-43)
            if (r7 != r9) goto L_0x0156
            r8 = 1
        L_0x0156:
            if (r8 != 0) goto L_0x0162
            java.lang.Character r4 = java.lang.Character.valueOf(r10)
            r2.invoke(r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x00ea
        L_0x0162:
            r7 = 3678080(0x381f80, float:5.154088E-39)
            r4 = r4 ^ r7
            int r7 = r15 << 6
            r4 = r4 ^ r7
            int r6 = r6 << 12
            r4 = r4 ^ r6
            int r5 = r5 << 18
            r4 = r4 ^ r5
            r5 = 1114111(0x10ffff, float:1.561202E-39)
            if (r4 <= r5) goto L_0x017e
        L_0x0174:
            java.lang.Character r4 = java.lang.Character.valueOf(r10)
            r2.invoke(r4)
        L_0x017b:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            goto L_0x01a8
        L_0x017e:
            if (r12 <= r4) goto L_0x0181
            goto L_0x0184
        L_0x0181:
            if (r13 < r4) goto L_0x0184
            goto L_0x0174
        L_0x0184:
            r5 = 65536(0x10000, float:9.18355E-41)
            if (r4 >= r5) goto L_0x0189
            goto L_0x0174
        L_0x0189:
            if (r4 == r10) goto L_0x0174
            int r5 = r4 >>> 10
            r6 = 55232(0xd7c0, float:7.7397E-41)
            int r5 = r5 + r6
            char r5 = (char) r5
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r2.invoke(r5)
            r4 = r4 & 1023(0x3ff, float:1.434E-42)
            r5 = 56320(0xdc00, float:7.8921E-41)
            int r4 = r4 + r5
            char r4 = (char) r4
            java.lang.Character r4 = java.lang.Character.valueOf(r4)
            r2.invoke(r4)
            goto L_0x017b
        L_0x01a8:
            r7 = 4
            goto L_0x0072
        L_0x01ab:
            java.lang.Character r4 = java.lang.Character.valueOf(r10)
            r2.invoke(r4)
            int r3 = r3 + 1
            goto L_0x0012
        L_0x01b6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Utf8.processUtf16Chars(byte[], int, int, kotlin.jvm.functions.Function1):void");
    }

    public static final int process2Utf8Bytes(byte[] bArr, int i, int i2, Function1<? super Integer, Unit> function1) {
        Intrinsics.checkNotNullParameter(bArr, "$this$process2Utf8Bytes");
        Intrinsics.checkNotNullParameter(function1, "yield");
        int i3 = i + 1;
        Integer valueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        if (i2 <= i3) {
            function1.invoke(valueOf);
            return 1;
        }
        byte b = bArr[i];
        byte b2 = bArr[i3];
        if (!((b2 & 192) == 128)) {
            function1.invoke(valueOf);
            return 1;
        }
        byte b3 = (b2 ^ 3968) ^ (b << 6);
        if (b3 < 128) {
            function1.invoke(valueOf);
            return 2;
        }
        function1.invoke(Integer.valueOf(b3));
        return 2;
    }

    public static final int process3Utf8Bytes(byte[] bArr, int i, int i2, Function1<? super Integer, Unit> function1) {
        Intrinsics.checkNotNullParameter(bArr, "$this$process3Utf8Bytes");
        Intrinsics.checkNotNullParameter(function1, "yield");
        int i3 = i + 2;
        boolean z = false;
        Integer valueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        if (i2 <= i3) {
            function1.invoke(valueOf);
            int i4 = i + 1;
            if (i2 > i4) {
                if ((bArr[i4] & 192) == 128) {
                    z = true;
                }
                return !z ? 1 : 2;
            }
        }
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        if (!((b2 & 192) == 128)) {
            function1.invoke(valueOf);
            return 1;
        }
        byte b3 = bArr[i3];
        if ((b3 & 192) == 128) {
            z = true;
        }
        if (!z) {
            function1.invoke(valueOf);
            return 2;
        }
        byte b4 = ((b3 ^ -123008) ^ (b2 << 6)) ^ (b << Ascii.FF);
        if (b4 < 2048) {
            function1.invoke(valueOf);
            return 3;
        } else if (55296 <= b4 && 57343 >= b4) {
            function1.invoke(valueOf);
            return 3;
        } else {
            function1.invoke(Integer.valueOf(b4));
            return 3;
        }
    }

    public static final int process4Utf8Bytes(byte[] bArr, int i, int i2, Function1<? super Integer, Unit> function1) {
        Intrinsics.checkNotNullParameter(bArr, "$this$process4Utf8Bytes");
        Intrinsics.checkNotNullParameter(function1, "yield");
        int i3 = i + 3;
        boolean z = false;
        Integer valueOf = Integer.valueOf(REPLACEMENT_CODE_POINT);
        if (i2 <= i3) {
            function1.invoke(valueOf);
            int i4 = i + 1;
            if (i2 > i4) {
                if ((bArr[i4] & 192) == 128) {
                    int i5 = i + 2;
                    if (i2 > i5) {
                        if ((bArr[i5] & 192) == 128) {
                            z = true;
                        }
                        return !z ? 2 : 3;
                    }
                }
            }
            return 1;
        }
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        if (!((b2 & 192) == 128)) {
            function1.invoke(valueOf);
            return 1;
        }
        byte b3 = bArr[i + 2];
        if (!((b3 & 192) == 128)) {
            function1.invoke(valueOf);
            return 2;
        }
        byte b4 = bArr[i3];
        if ((b4 & 192) == 128) {
            z = true;
        }
        if (!z) {
            function1.invoke(valueOf);
            return 3;
        }
        byte b5 = (((b4 ^ 3678080) ^ (b3 << 6)) ^ (b2 << Ascii.FF)) ^ (b << Ascii.DC2);
        if (b5 > 1114111) {
            function1.invoke(valueOf);
            return 4;
        } else if (55296 <= b5 && 57343 >= b5) {
            function1.invoke(valueOf);
            return 4;
        } else if (b5 < 65536) {
            function1.invoke(valueOf);
            return 4;
        } else {
            function1.invoke(Integer.valueOf(b5));
            return 4;
        }
    }
}

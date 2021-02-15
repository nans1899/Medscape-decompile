package org.mozilla.javascript;

import java.math.BigInteger;

class DToA {
    private static final int Bias = 1023;
    private static final int Bletch = 16;
    private static final int Bndry_mask = 1048575;
    static final int DTOSTR_EXPONENTIAL = 3;
    static final int DTOSTR_FIXED = 2;
    static final int DTOSTR_PRECISION = 4;
    static final int DTOSTR_STANDARD = 0;
    static final int DTOSTR_STANDARD_EXPONENTIAL = 1;
    private static final int Exp_11 = 1072693248;
    private static final int Exp_mask = 2146435072;
    private static final int Exp_mask_shifted = 2047;
    private static final int Exp_msk1 = 1048576;
    private static final long Exp_msk1L = 4503599627370496L;
    private static final int Exp_shift = 20;
    private static final int Exp_shift1 = 20;
    private static final int Exp_shiftL = 52;
    private static final int Frac_mask = 1048575;
    private static final int Frac_mask1 = 1048575;
    private static final long Frac_maskL = 4503599627370495L;
    private static final int Int_max = 14;
    private static final int Log2P = 1;
    private static final int P = 53;
    private static final int Quick_max = 14;
    private static final int Sign_bit = Integer.MIN_VALUE;
    private static final int Ten_pmax = 22;
    private static final double[] bigtens = {1.0E16d, 1.0E32d, 1.0E64d, 1.0E128d, 1.0E256d};
    private static final int[] dtoaModes = {0, 0, 3, 2, 2};
    private static final int n_bigtens = 5;
    private static final double[] tens = {1.0d, 10.0d, 100.0d, 1000.0d, 10000.0d, 100000.0d, 1000000.0d, 1.0E7d, 1.0E8d, 1.0E9d, 1.0E10d, 1.0E11d, 1.0E12d, 1.0E13d, 1.0E14d, 1.0E15d, 1.0E16d, 1.0E17d, 1.0E18d, 1.0E19d, 1.0E20d, 1.0E21d, 1.0E22d};

    private static char BASEDIGIT(int i) {
        return (char) (i >= 10 ? i + 87 : i + 48);
    }

    private static int hi0bits(int i) {
        int i2;
        if ((-65536 & i) == 0) {
            i <<= 16;
            i2 = 16;
        } else {
            i2 = 0;
        }
        if ((-16777216 & i) == 0) {
            i2 += 8;
            i <<= 8;
        }
        if ((-268435456 & i) == 0) {
            i2 += 4;
            i <<= 4;
        }
        if ((-1073741824 & i) == 0) {
            i2 += 2;
            i <<= 2;
        }
        if ((Integer.MIN_VALUE & i) == 0) {
            i2++;
            if ((i & 1073741824) == 0) {
                return 32;
            }
        }
        return i2;
    }

    private static int lo0bits(int i) {
        int i2 = 0;
        if ((i & 7) == 0) {
            if ((65535 & i) == 0) {
                i >>>= 16;
                i2 = 16;
            }
            if ((i & 255) == 0) {
                i2 += 8;
                i >>>= 8;
            }
            if ((i & 15) == 0) {
                i2 += 4;
                i >>>= 4;
            }
            if ((i & 3) == 0) {
                i2 += 2;
                i >>>= 2;
            }
            if ((i & 1) == 0) {
                i2++;
                if (((i >>> 1) & 1) == 0) {
                    return 32;
                }
            }
            return i2;
        } else if ((i & 1) != 0) {
            return 0;
        } else {
            return (i & 2) != 0 ? 1 : 2;
        }
    }

    DToA() {
    }

    private static void stuffBits(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0058  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.math.BigInteger d2b(double r8, int[] r10, int[] r11) {
        /*
            long r8 = java.lang.Double.doubleToLongBits(r8)
            r0 = 32
            long r1 = r8 >>> r0
            int r2 = (int) r1
            int r9 = (int) r8
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r8 & r2
            r1 = 2147483647(0x7fffffff, float:NaN)
            r1 = r1 & r2
            int r1 = r1 >>> 20
            if (r1 == 0) goto L_0x0019
            r2 = 1048576(0x100000, float:1.469368E-39)
            r8 = r8 | r2
        L_0x0019:
            r2 = 1
            r3 = 4
            r4 = 0
            if (r9 == 0) goto L_0x003d
            r5 = 8
            byte[] r5 = new byte[r5]
            int r6 = lo0bits(r9)
            int r9 = r9 >>> r6
            if (r6 == 0) goto L_0x0033
            int r7 = 32 - r6
            int r7 = r8 << r7
            r9 = r9 | r7
            stuffBits(r5, r3, r9)
            int r8 = r8 >> r6
            goto L_0x0036
        L_0x0033:
            stuffBits(r5, r3, r9)
        L_0x0036:
            stuffBits(r5, r4, r8)
            if (r8 == 0) goto L_0x0049
            r9 = 2
            goto L_0x004a
        L_0x003d:
            byte[] r5 = new byte[r3]
            int r9 = lo0bits(r8)
            int r8 = r8 >>> r9
            stuffBits(r5, r4, r8)
            int r6 = r9 + 32
        L_0x0049:
            r9 = 1
        L_0x004a:
            if (r1 == 0) goto L_0x0058
            int r1 = r1 + -1023
            int r1 = r1 + -52
            int r1 = r1 + r6
            r10[r4] = r1
            int r8 = 53 - r6
            r11[r4] = r8
            goto L_0x0069
        L_0x0058:
            int r1 = r1 + -1023
            int r1 = r1 + -52
            int r1 = r1 + r2
            int r1 = r1 + r6
            r10[r4] = r1
            int r9 = r9 * 32
            int r8 = hi0bits(r8)
            int r9 = r9 - r8
            r11[r4] = r9
        L_0x0069:
            java.math.BigInteger r8 = new java.math.BigInteger
            r8.<init>(r5)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.DToA.d2b(double, int[], int[]):java.math.BigInteger");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0115, code lost:
        if (r7 > 0) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0121, code lost:
        if (r9 > 0) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0130, code lost:
        if (r6.compareTo(r3) > 0) goto L_0x0123;
     */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0142 A[LOOP:0: B:52:0x00d9->B:78:0x0142, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x013d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String JS_dtobasestr(int r11, double r12) {
        /*
            r0 = 2
            if (r0 > r11) goto L_0x0144
            r0 = 36
            if (r11 > r0) goto L_0x0144
            boolean r0 = java.lang.Double.isNaN(r12)
            if (r0 == 0) goto L_0x0010
            java.lang.String r11 = "NaN"
            return r11
        L_0x0010:
            boolean r0 = java.lang.Double.isInfinite(r12)
            r1 = 0
            if (r0 == 0) goto L_0x0022
            int r11 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r11 <= 0) goto L_0x001f
            java.lang.String r11 = "Infinity"
            goto L_0x0021
        L_0x001f:
            java.lang.String r11 = "-Infinity"
        L_0x0021:
            return r11
        L_0x0022:
            int r0 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x0029
            java.lang.String r11 = "0"
            return r11
        L_0x0029:
            r1 = 0
            r2 = 1
            if (r0 < 0) goto L_0x002f
            r0 = 0
            goto L_0x0031
        L_0x002f:
            double r12 = -r12
            r0 = 1
        L_0x0031:
            double r3 = java.lang.Math.floor(r12)
            long r5 = (long) r3
            double r7 = (double) r5
            int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r9 != 0) goto L_0x0043
            if (r0 == 0) goto L_0x003e
            long r5 = -r5
        L_0x003e:
            java.lang.String r0 = java.lang.Long.toString(r5, r11)
            goto L_0x0077
        L_0x0043:
            long r5 = java.lang.Double.doubleToLongBits(r3)
            r7 = 52
            long r7 = r5 >> r7
            int r8 = (int) r7
            r7 = r8 & 2047(0x7ff, float:2.868E-42)
            r8 = 4503599627370495(0xfffffffffffff, double:2.225073858507201E-308)
            if (r7 != 0) goto L_0x0058
            long r5 = r5 & r8
            long r5 = r5 << r2
            goto L_0x005c
        L_0x0058:
            long r5 = r5 & r8
            r8 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
            long r5 = r5 | r8
        L_0x005c:
            if (r0 == 0) goto L_0x005f
            long r5 = -r5
        L_0x005f:
            int r7 = r7 + -1075
            java.math.BigInteger r0 = java.math.BigInteger.valueOf(r5)
            if (r7 <= 0) goto L_0x006c
            java.math.BigInteger r0 = r0.shiftLeft(r7)
            goto L_0x0073
        L_0x006c:
            if (r7 >= 0) goto L_0x0073
            int r5 = -r7
            java.math.BigInteger r0 = r0.shiftRight(r5)
        L_0x0073:
            java.lang.String r0 = r0.toString(r11)
        L_0x0077:
            int r5 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x007c
            return r0
        L_0x007c:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            r0 = 46
            r5.append(r0)
            double r3 = r12 - r3
            long r12 = java.lang.Double.doubleToLongBits(r12)
            r0 = 32
            long r6 = r12 >> r0
            int r0 = (int) r6
            int r13 = (int) r12
            int[] r12 = new int[r2]
            int[] r6 = new int[r2]
            java.math.BigInteger r3 = d2b(r3, r12, r6)
            int r4 = r0 >>> 20
            r4 = r4 & 2047(0x7ff, float:2.868E-42)
            int r4 = -r4
            if (r4 != 0) goto L_0x00a5
            r4 = -1
        L_0x00a5:
            int r4 = r4 + 1076
            r6 = 1
            java.math.BigInteger r8 = java.math.BigInteger.valueOf(r6)
            if (r13 != 0) goto L_0x00c3
            r9 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r9 & r0
            if (r9 != 0) goto L_0x00c3
            r9 = 2145386496(0x7fe00000, float:NaN)
            r0 = r0 & r9
            if (r0 == 0) goto L_0x00c3
            int r4 = r4 + 1
            r9 = 2
            java.math.BigInteger r0 = java.math.BigInteger.valueOf(r9)
            goto L_0x00c4
        L_0x00c3:
            r0 = r8
        L_0x00c4:
            r12 = r12[r1]
            int r12 = r12 + r4
            java.math.BigInteger r12 = r3.shiftLeft(r12)
            java.math.BigInteger r3 = java.math.BigInteger.valueOf(r6)
            java.math.BigInteger r3 = r3.shiftLeft(r4)
            long r6 = (long) r11
            java.math.BigInteger r4 = java.math.BigInteger.valueOf(r6)
            r11 = 0
        L_0x00d9:
            java.math.BigInteger r12 = r12.multiply(r4)
            java.math.BigInteger[] r12 = r12.divideAndRemainder(r3)
            r6 = r12[r2]
            r12 = r12[r1]
            int r12 = r12.intValue()
            char r12 = (char) r12
            if (r8 != r0) goto L_0x00f2
            java.math.BigInteger r0 = r8.multiply(r4)
            r8 = r0
            goto L_0x00fb
        L_0x00f2:
            java.math.BigInteger r7 = r8.multiply(r4)
            java.math.BigInteger r0 = r0.multiply(r4)
            r8 = r7
        L_0x00fb:
            int r7 = r6.compareTo(r8)
            java.math.BigInteger r9 = r3.subtract(r0)
            int r10 = r9.signum()
            if (r10 > 0) goto L_0x010b
            r9 = 1
            goto L_0x010f
        L_0x010b:
            int r9 = r6.compareTo(r9)
        L_0x010f:
            if (r9 != 0) goto L_0x0118
            r10 = r13 & 1
            if (r10 != 0) goto L_0x0118
            if (r7 <= 0) goto L_0x0133
            goto L_0x0123
        L_0x0118:
            if (r7 < 0) goto L_0x0126
            if (r7 != 0) goto L_0x0121
            r7 = r13 & 1
            if (r7 != 0) goto L_0x0121
            goto L_0x0126
        L_0x0121:
            if (r9 <= 0) goto L_0x0134
        L_0x0123:
            int r12 = r12 + 1
            goto L_0x0133
        L_0x0126:
            if (r9 <= 0) goto L_0x0133
            java.math.BigInteger r6 = r6.shiftLeft(r2)
            int r11 = r6.compareTo(r3)
            if (r11 <= 0) goto L_0x0133
            goto L_0x0123
        L_0x0133:
            r11 = 1
        L_0x0134:
            char r12 = BASEDIGIT(r12)
            r5.append(r12)
            if (r11 == 0) goto L_0x0142
            java.lang.String r11 = r5.toString()
            return r11
        L_0x0142:
            r12 = r6
            goto L_0x00d9
        L_0x0144:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r0 = "Bad base: "
            r13.append(r0)
            r13.append(r11)
            java.lang.String r11 = r13.toString()
            r12.<init>(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.DToA.JS_dtobasestr(int, double):java.lang.String");
    }

    static int word0(double d) {
        return (int) (Double.doubleToLongBits(d) >> 32);
    }

    static double setWord0(double d, int i) {
        return Double.longBitsToDouble((Double.doubleToLongBits(d) & 4294967295L) | (((long) i) << 32));
    }

    static int word1(double d) {
        return (int) Double.doubleToLongBits(d);
    }

    static BigInteger pow5mult(BigInteger bigInteger, int i) {
        return bigInteger.multiply(BigInteger.valueOf(5).pow(i));
    }

    static boolean roundOff(StringBuilder sb) {
        int length = sb.length();
        while (length != 0) {
            length--;
            char charAt = sb.charAt(length);
            if (charAt != '9') {
                sb.setCharAt(length, (char) (charAt + 1));
                sb.setLength(length + 1);
                return false;
            }
        }
        sb.setLength(0);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:362:0x0619, code lost:
        r1.append(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:0x061d, code lost:
        return r14 + r11;
     */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0209  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0231  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0235  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x030c  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x031b  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x031f  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x0347  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x03cf  */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x04c2  */
    /* JADX WARNING: Removed duplicated region for block: B:271:0x04c8  */
    /* JADX WARNING: Removed duplicated region for block: B:276:0x04d6  */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x04ed  */
    /* JADX WARNING: Removed duplicated region for block: B:284:0x04f4  */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x0521  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int JS_dtoa(double r41, int r43, boolean r44, int r45, boolean[] r46, java.lang.StringBuilder r47) {
        /*
            r0 = r43
            r1 = r47
            r2 = 1
            int[] r3 = new int[r2]
            int[] r4 = new int[r2]
            int r5 = word0(r41)
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r5 & r6
            r6 = 0
            if (r5 == 0) goto L_0x0024
            r46[r6] = r2
            int r5 = word0(r41)
            r7 = 2147483647(0x7fffffff, float:NaN)
            r5 = r5 & r7
            r7 = r41
            double r7 = setWord0(r7, r5)
            goto L_0x0028
        L_0x0024:
            r7 = r41
            r46[r6] = r6
        L_0x0028:
            int r5 = word0(r7)
            r9 = 2146435072(0x7ff00000, float:NaN)
            r5 = r5 & r9
            r10 = 1048575(0xfffff, float:1.469367E-39)
            if (r5 != r9) goto L_0x004c
            int r0 = word1(r7)
            if (r0 != 0) goto L_0x0044
            int r0 = word0(r7)
            r0 = r0 & r10
            if (r0 != 0) goto L_0x0044
            java.lang.String r0 = "Infinity"
            goto L_0x0046
        L_0x0044:
            java.lang.String r0 = "NaN"
        L_0x0046:
            r1.append(r0)
            r0 = 9999(0x270f, float:1.4012E-41)
            return r0
        L_0x004c:
            r11 = 0
            r5 = 48
            int r9 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r9 != 0) goto L_0x005b
            r1.setLength(r6)
            r1.append(r5)
            return r2
        L_0x005b:
            java.math.BigInteger r9 = d2b(r7, r3, r4)
            int r13 = word0(r7)
            int r13 = r13 >>> 20
            r13 = r13 & 2047(0x7ff, float:2.868E-42)
            r14 = 32
            if (r13 == 0) goto L_0x007f
            int r15 = word0(r7)
            r15 = r15 & r10
            r16 = 1072693248(0x3ff00000, float:1.875)
            r15 = r15 | r16
            double r15 = setWord0(r7, r15)
            int r13 = r13 + -1023
            r17 = r3
            r2 = r15
            r10 = 0
            goto L_0x00b7
        L_0x007f:
            r13 = r4[r6]
            r15 = r3[r6]
            int r13 = r13 + r15
            int r13 = r13 + 1074
            if (r13 <= r14) goto L_0x009e
            int r15 = word0(r7)
            long r14 = (long) r15
            int r16 = 64 - r13
            long r14 = r14 << r16
            int r16 = word1(r7)
            int r17 = r13 + -32
            int r10 = r16 >>> r17
            r17 = r3
            long r2 = (long) r10
            long r2 = r2 | r14
            goto L_0x00a8
        L_0x009e:
            r17 = r3
            int r2 = word1(r7)
            long r2 = (long) r2
            int r10 = 32 - r13
            long r2 = r2 << r10
        L_0x00a8:
            double r2 = (double) r2
            int r10 = word0(r2)
            r14 = 32505856(0x1f00000, float:8.8162076E-38)
            int r10 = r10 - r14
            double r2 = setWord0(r2, r10)
            int r13 = r13 + -1075
            r10 = 1
        L_0x00b7:
            r14 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            double r2 = r2 - r14
            r14 = 4598887322496222049(0x3fd287a7636f4361, double:0.289529654602168)
            double r2 = r2 * r14
            r14 = 4595512376519870643(0x3fc68a288b60c8b3, double:0.1760912590558)
            double r2 = r2 + r14
            double r14 = (double) r13
            r18 = 4599094494223104507(0x3fd34413509f79fb, double:0.301029995663981)
            double r14 = r14 * r18
            double r2 = r2 + r14
            int r14 = (int) r2
            int r15 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r15 >= 0) goto L_0x00dc
            double r11 = (double) r14
            int r15 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r15 == 0) goto L_0x00dc
            int r14 = r14 + -1
        L_0x00dc:
            if (r14 < 0) goto L_0x00ee
            r2 = 22
            if (r14 > r2) goto L_0x00ee
            double[] r2 = tens
            r11 = r2[r14]
            int r2 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r2 >= 0) goto L_0x00ec
            int r14 = r14 + -1
        L_0x00ec:
            r2 = 0
            goto L_0x00ef
        L_0x00ee:
            r2 = 1
        L_0x00ef:
            r3 = r4[r6]
            int r3 = r3 - r13
            r11 = 1
            int r3 = r3 - r11
            if (r3 < 0) goto L_0x00f9
            r11 = r3
            r3 = 0
            goto L_0x00fb
        L_0x00f9:
            int r3 = -r3
            r11 = 0
        L_0x00fb:
            if (r14 < 0) goto L_0x0101
            int r11 = r11 + r14
            r13 = r14
            r12 = 0
            goto L_0x0104
        L_0x0101:
            int r3 = r3 - r14
            int r12 = -r14
            r13 = 0
        L_0x0104:
            if (r0 < 0) goto L_0x010a
            r15 = 9
            if (r0 <= r15) goto L_0x010b
        L_0x010a:
            r0 = 0
        L_0x010b:
            r15 = 5
            if (r0 <= r15) goto L_0x0113
            int r0 = r0 + -4
            r20 = 0
            goto L_0x0115
        L_0x0113:
            r20 = 1
        L_0x0115:
            r5 = 3
            r6 = 2
            r21 = -1
            if (r0 == 0) goto L_0x0150
            r15 = 1
            if (r0 == r15) goto L_0x0150
            if (r0 == r6) goto L_0x0143
            if (r0 == r5) goto L_0x0132
            r5 = 4
            if (r0 == r5) goto L_0x0144
            r5 = 5
            if (r0 == r5) goto L_0x0130
            r5 = r45
            r24 = r9
            r6 = 0
            r21 = 0
            goto L_0x0155
        L_0x0130:
            r5 = 1
            goto L_0x0133
        L_0x0132:
            r5 = 0
        L_0x0133:
            int r16 = r45 + r14
            int r21 = r16 + 1
            int r15 = r21 + -1
            r24 = r9
            r6 = r21
            r21 = r15
            r15 = r5
            r5 = r45
            goto L_0x0155
        L_0x0143:
            r15 = 0
        L_0x0144:
            if (r45 > 0) goto L_0x0148
            r5 = 1
            goto L_0x014a
        L_0x0148:
            r5 = r45
        L_0x014a:
            r6 = r5
            r21 = r6
            r24 = r9
            goto L_0x0155
        L_0x0150:
            r24 = r9
            r5 = 0
            r6 = -1
            r15 = 1
        L_0x0155:
            r25 = 48
            r27 = 4621819117588971520(0x4024000000000000, double:10.0)
            if (r6 < 0) goto L_0x0324
            r9 = 14
            if (r6 > r9) goto L_0x0324
            if (r20 == 0) goto L_0x0324
            if (r14 <= 0) goto L_0x019c
            double[] r9 = tens
            r20 = r14 & 15
            r29 = r9[r20]
            int r9 = r14 >> 4
            r20 = r9 & 16
            if (r20 == 0) goto L_0x017e
            r9 = r9 & 15
            double[] r20 = bigtens
            r22 = 4
            r31 = r20[r22]
            double r31 = r7 / r31
            r20 = 0
            r23 = 3
            goto L_0x0184
        L_0x017e:
            r31 = r7
            r20 = 0
            r23 = 2
        L_0x0184:
            if (r9 == 0) goto L_0x0197
            r33 = r9 & 1
            if (r33 == 0) goto L_0x0192
            int r23 = r23 + 1
            double[] r33 = bigtens
            r34 = r33[r20]
            double r29 = r29 * r34
        L_0x0192:
            int r9 = r9 >> 1
            int r20 = r20 + 1
            goto L_0x0184
        L_0x0197:
            double r31 = r31 / r29
            r9 = r23
            goto L_0x01c8
        L_0x019c:
            int r9 = -r14
            if (r9 == 0) goto L_0x01c5
            double[] r20 = tens
            r23 = r9 & 15
            r29 = r20[r23]
            double r29 = r29 * r7
            r20 = 4
            int r9 = r9 >> 4
            r20 = r9
            r31 = r29
            r9 = 2
            r23 = 0
        L_0x01b2:
            if (r20 == 0) goto L_0x01c8
            r29 = r20 & 1
            if (r29 == 0) goto L_0x01c0
            int r9 = r9 + 1
            double[] r29 = bigtens
            r33 = r29[r23]
            double r31 = r31 * r33
        L_0x01c0:
            int r20 = r20 >> 1
            int r23 = r23 + 1
            goto L_0x01b2
        L_0x01c5:
            r31 = r7
            r9 = 2
        L_0x01c8:
            if (r2 == 0) goto L_0x01ec
            r29 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r20 = (r31 > r29 ? 1 : (r31 == r29 ? 0 : -1))
            if (r20 >= 0) goto L_0x01ec
            if (r6 <= 0) goto L_0x01ec
            if (r21 > 0) goto L_0x01dd
            r29 = r6
            r33 = r7
            r23 = r14
            r20 = 1
            goto L_0x01f4
        L_0x01dd:
            int r20 = r14 + -1
            double r31 = r31 * r27
            int r9 = r9 + 1
            r29 = r6
            r33 = r7
            r23 = r20
            r6 = r21
            goto L_0x01f2
        L_0x01ec:
            r29 = r6
            r33 = r7
            r23 = r14
        L_0x01f2:
            r20 = 0
        L_0x01f4:
            double r7 = (double) r9
            double r7 = r7 * r31
            r35 = 4619567317775286272(0x401c000000000000, double:7.0)
            double r7 = r7 + r35
            int r9 = word0(r7)
            r30 = 54525952(0x3400000, float:5.642373E-37)
            int r9 = r9 - r30
            double r7 = setWord0(r7, r9)
            if (r6 != 0) goto L_0x0231
            r35 = 4617315517961601024(0x4014000000000000, double:5.0)
            double r31 = r31 - r35
            int r9 = (r31 > r7 ? 1 : (r31 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x021c
            r9 = 49
            r1.append(r9)
            r9 = 1
            int r23 = r23 + 1
        L_0x0219:
            int r23 = r23 + 1
            return r23
        L_0x021c:
            r30 = r10
            double r9 = -r7
            int r20 = (r31 > r9 ? 1 : (r31 == r9 ? 0 : -1))
            if (r20 >= 0) goto L_0x022e
            r9 = 0
            r1.setLength(r9)
            r0 = 48
            r1.append(r0)
            r0 = 1
            return r0
        L_0x022e:
            r20 = 1
            goto L_0x0233
        L_0x0231:
            r30 = r10
        L_0x0233:
            if (r20 != 0) goto L_0x030c
            r9 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            if (r15 == 0) goto L_0x02a0
            double[] r20 = tens
            int r35 = r6 + -1
            r35 = r20[r35]
            double r9 = r9 / r35
            double r9 = r9 - r7
            r35 = r2
            r36 = r3
            r38 = r13
            r37 = r14
            r2 = r31
            r7 = 0
        L_0x024d:
            long r13 = (long) r2
            r39 = r11
            r40 = r12
            double r11 = (double) r13
            double r31 = r2 - r11
            long r13 = r13 + r25
            int r2 = (int) r13
            char r2 = (char) r2
            r1.append(r2)
            int r2 = (r31 > r9 ? 1 : (r31 == r9 ? 0 : -1))
            if (r2 >= 0) goto L_0x0262
            r2 = 1
            goto L_0x0219
        L_0x0262:
            r2 = 1
            r11 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r11 = r11 - r31
            int r3 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r3 >= 0) goto L_0x0292
        L_0x026b:
            int r0 = r47.length()
            int r0 = r0 - r2
            char r0 = r1.charAt(r0)
            int r3 = r47.length()
            int r3 = r3 - r2
            r1.setLength(r3)
            r3 = 57
            if (r0 == r3) goto L_0x0282
            r5 = r0
            goto L_0x028c
        L_0x0282:
            int r0 = r47.length()
            if (r0 != 0) goto L_0x026b
            int r23 = r23 + 1
            r5 = 48
        L_0x028c:
            int r5 = r5 + r2
            char r0 = (char) r5
            r1.append(r0)
            goto L_0x0219
        L_0x0292:
            int r7 = r7 + r2
            if (r7 < r6) goto L_0x0297
            goto L_0x0302
        L_0x0297:
            double r9 = r9 * r27
            double r2 = r31 * r27
            r11 = r39
            r12 = r40
            goto L_0x024d
        L_0x02a0:
            r35 = r2
            r36 = r3
            r39 = r11
            r40 = r12
            r38 = r13
            r37 = r14
            double[] r2 = tens
            int r3 = r6 + -1
            r11 = r2[r3]
            double r7 = r7 * r11
            r11 = r31
            r2 = 1
        L_0x02b7:
            long r13 = (long) r11
            double r9 = (double) r13
            double r31 = r11 - r9
            long r13 = r13 + r25
            int r3 = (int) r13
            char r3 = (char) r3
            r1.append(r3)
            if (r2 != r6) goto L_0x0305
            r9 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r2 = r7 + r9
            int r9 = (r31 > r2 ? 1 : (r31 == r2 ? 0 : -1))
            if (r9 <= 0) goto L_0x02f5
        L_0x02cc:
            int r0 = r47.length()
            r2 = 1
            int r0 = r0 - r2
            char r0 = r1.charAt(r0)
            int r3 = r47.length()
            int r3 = r3 - r2
            r1.setLength(r3)
            r3 = 57
            if (r0 == r3) goto L_0x02e4
            r5 = r0
            goto L_0x02ee
        L_0x02e4:
            int r0 = r47.length()
            if (r0 != 0) goto L_0x02cc
            int r23 = r23 + 1
            r5 = 48
        L_0x02ee:
            int r5 = r5 + r2
            char r0 = (char) r5
            r1.append(r0)
            goto L_0x0219
        L_0x02f5:
            r2 = 1
            r9 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r9 = r9 - r7
            int r3 = (r31 > r9 ? 1 : (r31 == r9 ? 0 : -1))
            if (r3 >= 0) goto L_0x0302
            stripTrailingZeroes(r47)
            goto L_0x0219
        L_0x0302:
            r20 = 1
            goto L_0x0318
        L_0x0305:
            r9 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r2 = r2 + 1
            double r11 = r31 * r27
            goto L_0x02b7
        L_0x030c:
            r35 = r2
            r36 = r3
            r39 = r11
            r40 = r12
            r38 = r13
            r37 = r14
        L_0x0318:
            r2 = 0
            if (r20 == 0) goto L_0x031f
            r1.setLength(r2)
            goto L_0x0337
        L_0x031f:
            r14 = r23
            r7 = r31
            goto L_0x033d
        L_0x0324:
            r35 = r2
            r36 = r3
            r29 = r6
            r33 = r7
            r30 = r10
            r39 = r11
            r40 = r12
            r38 = r13
            r37 = r14
            r2 = 0
        L_0x0337:
            r6 = r29
            r7 = r33
            r14 = r37
        L_0x033d:
            r3 = r17[r2]
            r9 = 1
            if (r3 < 0) goto L_0x03cf
            r2 = 14
            if (r14 > r2) goto L_0x03cf
            double[] r0 = tens
            r2 = r0[r14]
            if (r5 >= 0) goto L_0x0374
            if (r6 > 0) goto L_0x0374
            if (r6 < 0) goto L_0x0369
            r4 = 4617315517961601024(0x4014000000000000, double:5.0)
            double r2 = r2 * r4
            int r0 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r0 < 0) goto L_0x0369
            if (r44 != 0) goto L_0x0360
            int r0 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x0360
            goto L_0x0369
        L_0x0360:
            r0 = 49
            r1.append(r0)
            r0 = 1
            int r14 = r14 + r0
            int r14 = r14 + r0
            return r14
        L_0x0369:
            r0 = 1
            r2 = 0
            r1.setLength(r2)
            r2 = 48
            r1.append(r2)
            return r0
        L_0x0374:
            r0 = 1
        L_0x0375:
            double r4 = r7 / r2
            long r4 = (long) r4
            double r11 = (double) r4
            double r11 = r11 * r2
            double r7 = r7 - r11
            long r11 = r4 + r25
            int r12 = (int) r11
            char r11 = (char) r12
            r1.append(r11)
            if (r0 != r6) goto L_0x03c1
            double r7 = r7 + r7
            int r0 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r0 > 0) goto L_0x0399
            if (r0 != 0) goto L_0x0397
            long r2 = r4 & r9
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x0399
            if (r44 == 0) goto L_0x0397
            goto L_0x0399
        L_0x0397:
            r4 = 1
            goto L_0x03ca
        L_0x0399:
            int r0 = r47.length()
            r4 = 1
            int r0 = r0 - r4
            char r0 = r1.charAt(r0)
            int r2 = r47.length()
            int r2 = r2 - r4
            r1.setLength(r2)
            r2 = 57
            if (r0 == r2) goto L_0x03b1
            r5 = r0
            goto L_0x03bb
        L_0x03b1:
            int r0 = r47.length()
            if (r0 != 0) goto L_0x0399
            int r14 = r14 + 1
            r5 = 48
        L_0x03bb:
            int r5 = r5 + r4
            char r0 = (char) r5
            r1.append(r0)
            goto L_0x03ca
        L_0x03c1:
            r4 = 1
            double r7 = r7 * r27
            r11 = 0
            int r5 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r5 != 0) goto L_0x03cc
        L_0x03ca:
            int r14 = r14 + r4
            return r14
        L_0x03cc:
            int r0 = r0 + 1
            goto L_0x0375
        L_0x03cf:
            r2 = 0
            if (r15 == 0) goto L_0x0413
            r3 = 2
            if (r0 >= r3) goto L_0x03e9
            r2 = 0
            if (r30 == 0) goto L_0x03de
            r3 = r17[r2]
            int r3 = r3 + 1075
            r2 = r3
            goto L_0x03e2
        L_0x03de:
            r3 = r4[r2]
            int r2 = 54 - r3
        L_0x03e2:
            r3 = r36
            r13 = r38
            r12 = r40
            goto L_0x0408
        L_0x03e9:
            int r2 = r6 + -1
            r12 = r40
            if (r12 < r2) goto L_0x03f4
            int r2 = r12 - r2
            r13 = r38
            goto L_0x03f9
        L_0x03f4:
            int r2 = r2 - r12
            int r13 = r38 + r2
            int r12 = r12 + r2
            r2 = 0
        L_0x03f9:
            if (r6 >= 0) goto L_0x0402
            int r3 = r36 - r6
            r40 = r12
            r12 = r2
            r2 = 0
            goto L_0x0408
        L_0x0402:
            r40 = r12
            r3 = r36
            r12 = r2
            r2 = r6
        L_0x0408:
            int r4 = r36 + r2
            int r11 = r39 + r2
            java.math.BigInteger r2 = java.math.BigInteger.valueOf(r9)
            r5 = r40
            goto L_0x041d
        L_0x0413:
            r12 = r40
            r5 = r12
            r3 = r36
            r4 = r3
            r13 = r38
            r11 = r39
        L_0x041d:
            if (r3 <= 0) goto L_0x042e
            if (r11 <= 0) goto L_0x042e
            if (r3 >= r11) goto L_0x0426
            r17 = r3
            goto L_0x0428
        L_0x0426:
            r17 = r11
        L_0x0428:
            int r4 = r4 - r17
            int r3 = r3 - r17
            int r11 = r11 - r17
        L_0x042e:
            if (r5 <= 0) goto L_0x0450
            if (r15 == 0) goto L_0x0449
            if (r12 <= 0) goto L_0x043f
            java.math.BigInteger r2 = pow5mult(r2, r12)
            r9 = r24
            java.math.BigInteger r9 = r2.multiply(r9)
            goto L_0x0441
        L_0x043f:
            r9 = r24
        L_0x0441:
            int r5 = r5 - r12
            if (r5 == 0) goto L_0x0452
            java.math.BigInteger r9 = pow5mult(r9, r5)
            goto L_0x0452
        L_0x0449:
            r9 = r24
            java.math.BigInteger r9 = pow5mult(r9, r5)
            goto L_0x0452
        L_0x0450:
            r9 = r24
        L_0x0452:
            r17 = 1
            java.math.BigInteger r5 = java.math.BigInteger.valueOf(r17)
            if (r13 <= 0) goto L_0x045e
            java.math.BigInteger r5 = pow5mult(r5, r13)
        L_0x045e:
            r10 = 2
            if (r0 >= r10) goto L_0x0480
            int r10 = word1(r7)
            if (r10 != 0) goto L_0x0480
            int r10 = word0(r7)
            r12 = 1048575(0xfffff, float:1.469367E-39)
            r10 = r10 & r12
            if (r10 != 0) goto L_0x0480
            int r10 = word0(r7)
            r12 = 2145386496(0x7fe00000, float:NaN)
            r10 = r10 & r12
            if (r10 == 0) goto L_0x0480
            int r4 = r4 + 1
            int r11 = r11 + 1
            r10 = 1
            goto L_0x0481
        L_0x0480:
            r10 = 0
        L_0x0481:
            byte[] r12 = r5.toByteArray()
            r42 = r6
            r18 = r7
            r6 = 0
            r7 = 4
            r17 = 0
        L_0x048d:
            if (r6 >= r7) goto L_0x049f
            int r7 = r17 << 8
            int r8 = r12.length
            if (r6 >= r8) goto L_0x0499
            byte r8 = r12[r6]
            r8 = r8 & 255(0xff, float:3.57E-43)
            r7 = r7 | r8
        L_0x0499:
            r17 = r7
            int r6 = r6 + 1
            r7 = 4
            goto L_0x048d
        L_0x049f:
            if (r13 == 0) goto L_0x04aa
            int r6 = hi0bits(r17)
            r7 = 32
            int r6 = 32 - r6
            goto L_0x04ab
        L_0x04aa:
            r6 = 1
        L_0x04ab:
            int r6 = r6 + r11
            r6 = r6 & 31
            if (r6 == 0) goto L_0x04b2
            int r6 = 32 - r6
        L_0x04b2:
            r7 = 4
            if (r6 <= r7) goto L_0x04bb
            int r6 = r6 + -4
        L_0x04b7:
            int r4 = r4 + r6
            int r3 = r3 + r6
            int r11 = r11 + r6
            goto L_0x04c0
        L_0x04bb:
            if (r6 >= r7) goto L_0x04c0
            int r6 = r6 + 28
            goto L_0x04b7
        L_0x04c0:
            if (r4 <= 0) goto L_0x04c6
            java.math.BigInteger r9 = r9.shiftLeft(r4)
        L_0x04c6:
            if (r11 <= 0) goto L_0x04cc
            java.math.BigInteger r5 = r5.shiftLeft(r11)
        L_0x04cc:
            r6 = 10
            if (r35 == 0) goto L_0x04ed
            int r4 = r9.compareTo(r5)
            if (r4 >= 0) goto L_0x04ed
            int r14 = r14 + -1
            java.math.BigInteger r4 = java.math.BigInteger.valueOf(r6)
            java.math.BigInteger r9 = r9.multiply(r4)
            if (r15 == 0) goto L_0x04ea
            java.math.BigInteger r4 = java.math.BigInteger.valueOf(r6)
            java.math.BigInteger r2 = r2.multiply(r4)
        L_0x04ea:
            r4 = r21
            goto L_0x04ef
        L_0x04ed:
            r4 = r42
        L_0x04ef:
            if (r4 > 0) goto L_0x0521
            r8 = 2
            if (r0 <= r8) goto L_0x0521
            if (r4 < 0) goto L_0x0516
            r2 = 5
            java.math.BigInteger r0 = java.math.BigInteger.valueOf(r2)
            java.math.BigInteger r0 = r5.multiply(r0)
            int r0 = r9.compareTo(r0)
            if (r0 < 0) goto L_0x0516
            if (r0 != 0) goto L_0x050d
            if (r44 != 0) goto L_0x050d
            r0 = 0
            r8 = 1
            goto L_0x0518
        L_0x050d:
            r0 = 49
            r1.append(r0)
            r8 = 1
            int r14 = r14 + r8
            int r14 = r14 + r8
            return r14
        L_0x0516:
            r8 = 1
            r0 = 0
        L_0x0518:
            r1.setLength(r0)
            r0 = 48
            r1.append(r0)
            return r8
        L_0x0521:
            r8 = 1
            if (r15 == 0) goto L_0x061e
            if (r3 <= 0) goto L_0x052a
            java.math.BigInteger r2 = r2.shiftLeft(r3)
        L_0x052a:
            if (r10 == 0) goto L_0x0531
            java.math.BigInteger r3 = r2.shiftLeft(r8)
            goto L_0x0532
        L_0x0531:
            r3 = r2
        L_0x0532:
            r10 = 1
        L_0x0533:
            java.math.BigInteger[] r9 = r9.divideAndRemainder(r5)
            r11 = r9[r8]
            r8 = 0
            r9 = r9[r8]
            int r8 = r9.intValue()
            r9 = 48
            int r8 = r8 + r9
            char r8 = (char) r8
            int r9 = r11.compareTo(r2)
            java.math.BigInteger r12 = r5.subtract(r3)
            int r13 = r12.signum()
            if (r13 > 0) goto L_0x0554
            r12 = 1
            goto L_0x0558
        L_0x0554:
            int r12 = r11.compareTo(r12)
        L_0x0558:
            if (r12 != 0) goto L_0x0584
            if (r0 != 0) goto L_0x0584
            int r13 = word1(r18)
            r15 = 1
            r13 = r13 & r15
            if (r13 != 0) goto L_0x0585
            r13 = 57
            if (r8 != r13) goto L_0x057a
            r1.append(r13)
            boolean r0 = roundOff(r47)
            if (r0 == 0) goto L_0x0578
            int r14 = r14 + 1
            r0 = 49
            r1.append(r0)
        L_0x0578:
            int r14 = r14 + r15
            return r14
        L_0x057a:
            if (r9 <= 0) goto L_0x057f
            int r8 = r8 + 1
            char r8 = (char) r8
        L_0x057f:
            r1.append(r8)
            int r14 = r14 + r15
            return r14
        L_0x0584:
            r15 = 1
        L_0x0585:
            if (r9 < 0) goto L_0x05e6
            if (r9 != 0) goto L_0x0593
            if (r0 != 0) goto L_0x0593
            int r9 = word1(r18)
            r9 = r9 & r15
            if (r9 != 0) goto L_0x0593
            goto L_0x05e6
        L_0x0593:
            if (r12 <= 0) goto L_0x05b4
            r9 = 57
            if (r8 != r9) goto L_0x05ac
            r1.append(r9)
            boolean r0 = roundOff(r47)
            if (r0 == 0) goto L_0x05a9
            int r14 = r14 + 1
            r0 = 49
            r1.append(r0)
        L_0x05a9:
            r0 = 1
            int r14 = r14 + r0
            return r14
        L_0x05ac:
            r0 = 1
            int r8 = r8 + r0
            char r2 = (char) r8
            r1.append(r2)
            int r14 = r14 + r0
            return r14
        L_0x05b4:
            r1.append(r8)
            if (r10 != r4) goto L_0x05bd
            r3 = r11
            r11 = 1
            goto L_0x0637
        L_0x05bd:
            java.math.BigInteger r8 = java.math.BigInteger.valueOf(r6)
            java.math.BigInteger r9 = r11.multiply(r8)
            if (r2 != r3) goto L_0x05d1
            java.math.BigInteger r2 = java.math.BigInteger.valueOf(r6)
            java.math.BigInteger r2 = r3.multiply(r2)
            r3 = r2
            goto L_0x05e1
        L_0x05d1:
            java.math.BigInteger r8 = java.math.BigInteger.valueOf(r6)
            java.math.BigInteger r2 = r2.multiply(r8)
            java.math.BigInteger r8 = java.math.BigInteger.valueOf(r6)
            java.math.BigInteger r3 = r3.multiply(r8)
        L_0x05e1:
            int r10 = r10 + 1
            r8 = 1
            goto L_0x0533
        L_0x05e6:
            if (r12 <= 0) goto L_0x0618
            r0 = 1
            java.math.BigInteger r2 = r11.shiftLeft(r0)
            int r2 = r2.compareTo(r5)
            if (r2 > 0) goto L_0x05fb
            if (r2 != 0) goto L_0x0618
            r2 = r8 & 1
            if (r2 == r0) goto L_0x05fb
            if (r44 == 0) goto L_0x0618
        L_0x05fb:
            int r0 = r8 + 1
            char r0 = (char) r0
            r2 = 57
            if (r8 != r2) goto L_0x0615
            r1.append(r2)
            boolean r0 = roundOff(r47)
            if (r0 == 0) goto L_0x0612
            int r14 = r14 + 1
            r0 = 49
            r1.append(r0)
        L_0x0612:
            r11 = 1
            int r14 = r14 + r11
            return r14
        L_0x0615:
            r11 = 1
            r8 = r0
            goto L_0x0619
        L_0x0618:
            r11 = 1
        L_0x0619:
            r1.append(r8)
            int r14 = r14 + r11
            return r14
        L_0x061e:
            r11 = 1
            r0 = 1
        L_0x0620:
            java.math.BigInteger[] r2 = r9.divideAndRemainder(r5)
            r3 = r2[r11]
            r8 = 0
            r2 = r2[r8]
            int r2 = r2.intValue()
            r9 = 48
            int r2 = r2 + r9
            char r2 = (char) r2
            r1.append(r2)
            if (r0 < r4) goto L_0x065e
            r8 = r2
        L_0x0637:
            java.math.BigInteger r0 = r3.shiftLeft(r11)
            int r0 = r0.compareTo(r5)
            if (r0 > 0) goto L_0x064e
            if (r0 != 0) goto L_0x064a
            r0 = r8 & 1
            if (r0 == r11) goto L_0x064e
            if (r44 == 0) goto L_0x064a
            goto L_0x064e
        L_0x064a:
            stripTrailingZeroes(r47)
            goto L_0x065c
        L_0x064e:
            boolean r0 = roundOff(r47)
            if (r0 == 0) goto L_0x065c
            int r14 = r14 + r11
            r2 = 49
            r1.append(r2)
            int r14 = r14 + r11
            return r14
        L_0x065c:
            int r14 = r14 + r11
            return r14
        L_0x065e:
            r2 = 49
            java.math.BigInteger r10 = java.math.BigInteger.valueOf(r6)
            java.math.BigInteger r3 = r3.multiply(r10)
            int r0 = r0 + 1
            r9 = r3
            goto L_0x0620
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.DToA.JS_dtoa(double, int, boolean, int, boolean[], java.lang.StringBuilder):int");
    }

    private static void stripTrailingZeroes(StringBuilder sb) {
        int i;
        int length = sb.length();
        while (true) {
            i = length - 1;
            if (length <= 0 || sb.charAt(i) != '0') {
                sb.setLength(i + 1);
            } else {
                length = i;
            }
        }
        sb.setLength(i + 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0045, code lost:
        if (r1 <= r13) goto L_0x0057;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005f A[LOOP:0: B:37:0x005f->B:38:0x0066, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void JS_dtostr(java.lang.StringBuilder r11, int r12, int r13, double r14) {
        /*
            r0 = 1
            boolean[] r8 = new boolean[r0]
            r9 = 2
            r10 = 0
            if (r12 != r9) goto L_0x001a
            r1 = 4921056587992461136(0x444b1ae4d6e2ef50, double:1.0E21)
            int r3 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1))
            if (r3 >= 0) goto L_0x0019
            r1 = -4302315448862314672(0xc44b1ae4d6e2ef50, double:-1.0E21)
            int r3 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1))
            if (r3 > 0) goto L_0x001a
        L_0x0019:
            r12 = 0
        L_0x001a:
            int[] r1 = dtoaModes
            r3 = r1[r12]
            if (r12 < r9) goto L_0x0022
            r4 = 1
            goto L_0x0023
        L_0x0022:
            r4 = 0
        L_0x0023:
            r1 = r14
            r5 = r13
            r6 = r8
            r7 = r11
            int r1 = JS_dtoa(r1, r3, r4, r5, r6, r7)
            int r2 = r11.length()
            r3 = 9999(0x270f, float:1.4012E-41)
            if (r1 == r3) goto L_0x0099
            r3 = -5
            if (r12 == 0) goto L_0x004f
            if (r12 == r0) goto L_0x004c
            if (r12 == r9) goto L_0x0048
            r4 = 3
            if (r12 == r4) goto L_0x004d
            r4 = 4
            if (r12 == r4) goto L_0x0043
            r12 = 0
        L_0x0041:
            r13 = 0
            goto L_0x005b
        L_0x0043:
            if (r1 < r3) goto L_0x004d
            if (r1 <= r13) goto L_0x0057
            goto L_0x004d
        L_0x0048:
            if (r13 < 0) goto L_0x0056
            int r13 = r13 + r1
            goto L_0x0057
        L_0x004c:
            r13 = 0
        L_0x004d:
            r12 = 1
            goto L_0x005b
        L_0x004f:
            if (r1 < r3) goto L_0x0059
            r12 = 21
            if (r1 <= r12) goto L_0x0056
            goto L_0x0059
        L_0x0056:
            r13 = r1
        L_0x0057:
            r12 = 0
            goto L_0x005b
        L_0x0059:
            r12 = 1
            goto L_0x0041
        L_0x005b:
            r3 = 48
            if (r2 >= r13) goto L_0x0069
        L_0x005f:
            r11.append(r3)
            int r2 = r11.length()
            if (r2 != r13) goto L_0x005f
            r2 = r13
        L_0x0069:
            r13 = 46
            if (r12 == 0) goto L_0x0083
            if (r2 == r0) goto L_0x0072
            r11.insert(r0, r13)
        L_0x0072:
            r12 = 101(0x65, float:1.42E-43)
            r11.append(r12)
            int r1 = r1 - r0
            if (r1 < 0) goto L_0x007f
            r12 = 43
            r11.append(r12)
        L_0x007f:
            r11.append(r1)
            goto L_0x0099
        L_0x0083:
            if (r1 == r2) goto L_0x0099
            if (r1 <= 0) goto L_0x008b
            r11.insert(r1, r13)
            goto L_0x0099
        L_0x008b:
            r12 = 0
        L_0x008c:
            int r2 = 1 - r1
            if (r12 >= r2) goto L_0x0096
            r11.insert(r10, r3)
            int r12 = r12 + 1
            goto L_0x008c
        L_0x0096:
            r11.insert(r0, r13)
        L_0x0099:
            boolean r12 = r8[r10]
            if (r12 == 0) goto L_0x00c9
            int r12 = word0(r14)
            r13 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r12 != r13) goto L_0x00ab
            int r12 = word1(r14)
            if (r12 == 0) goto L_0x00c9
        L_0x00ab:
            int r12 = word0(r14)
            r13 = 2146435072(0x7ff00000, float:NaN)
            r12 = r12 & r13
            if (r12 != r13) goto L_0x00c4
            int r12 = word1(r14)
            if (r12 != 0) goto L_0x00c9
            int r12 = word0(r14)
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r12 = r12 & r13
            if (r12 != 0) goto L_0x00c9
        L_0x00c4:
            r12 = 45
            r11.insert(r10, r12)
        L_0x00c9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.DToA.JS_dtostr(java.lang.StringBuilder, int, int, double):void");
    }
}

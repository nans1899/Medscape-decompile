package com.fasterxml.jackson.core.io;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.AppEventsConstants;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;

public final class NumberOutput {
    private static int BILLION = 1000000000;
    private static final char[] FULL_3 = new char[4000];
    private static final byte[] FULL_TRIPLETS_B = new byte[4000];
    private static final char[] LEAD_3 = new char[4000];
    private static long MAX_INT_AS_LONG = 2147483647L;
    private static int MILLION = 1000000;
    private static long MIN_INT_AS_LONG = -2147483648L;
    private static final char NC = '\u0000';
    static final String SMALLEST_LONG = String.valueOf(Long.MIN_VALUE);
    private static long TEN_BILLION_L = 10000000000L;
    private static long THOUSAND_L = 1000;
    private static final String[] sSmallIntStrs = {AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_YES, ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", UserProfile.NURSE_PRACTITIONER_ID, "6", "7", UserProfile.PHARMACIST_ID, "9", UserProfile.PHYSICIAN_ID};
    private static final String[] sSmallIntStrs2 = {"-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};

    static {
        int i = 0;
        int i2 = 0;
        while (i < 10) {
            char c = (char) (i + 48);
            char c2 = i == 0 ? 0 : c;
            int i3 = 0;
            while (i3 < 10) {
                char c3 = (char) (i3 + 48);
                char c4 = (i == 0 && i3 == 0) ? 0 : c3;
                for (int i4 = 0; i4 < 10; i4++) {
                    char c5 = (char) (i4 + 48);
                    char[] cArr = LEAD_3;
                    cArr[i2] = c2;
                    int i5 = i2 + 1;
                    cArr[i5] = c4;
                    int i6 = i2 + 2;
                    cArr[i6] = c5;
                    char[] cArr2 = FULL_3;
                    cArr2[i2] = c;
                    cArr2[i5] = c3;
                    cArr2[i6] = c5;
                    i2 += 4;
                }
                i3++;
            }
            i++;
        }
        for (int i7 = 0; i7 < 4000; i7++) {
            FULL_TRIPLETS_B[i7] = (byte) FULL_3[i7];
        }
    }

    public static int outputInt(int i, char[] cArr, int i2) {
        int i3;
        int i4;
        if (i < 0) {
            if (i == Integer.MIN_VALUE) {
                return outputLong((long) i, cArr, i2);
            }
            cArr[i2] = '-';
            i = -i;
            i2++;
        }
        if (r4 >= MILLION) {
            boolean z = r4 >= BILLION;
            if (z) {
                int i5 = BILLION;
                r4 -= i5;
                if (r4 >= i5) {
                    r4 -= i5;
                    i4 = i2 + 1;
                    cArr[i2] = '2';
                } else {
                    i4 = i2 + 1;
                    cArr[i2] = '1';
                }
                i2 = i4;
            }
            int i6 = r4 / 1000;
            int i7 = r4 - (i6 * 1000);
            int i8 = i6 / 1000;
            int i9 = i6 - (i8 * 1000);
            if (z) {
                i3 = full3(i8, cArr, i2);
            } else {
                i3 = leading3(i8, cArr, i2);
            }
            return full3(i7, cArr, full3(i9, cArr, i3));
        } else if (r4 >= 1000) {
            int i10 = r4 / 1000;
            return full3(r4 - (i10 * 1000), cArr, leading3(i10, cArr, i2));
        } else if (r4 >= 10) {
            return leading3(r4, cArr, i2);
        } else {
            int i11 = i2 + 1;
            cArr[i2] = (char) (r4 + 48);
            return i11;
        }
    }

    public static int outputInt(int i, byte[] bArr, int i2) {
        int i3;
        int i4;
        if (i < 0) {
            if (i == Integer.MIN_VALUE) {
                return outputLong((long) i, bArr, i2);
            }
            bArr[i2] = 45;
            i = -i;
            i2++;
        }
        if (r4 >= MILLION) {
            boolean z = r4 >= BILLION;
            if (z) {
                int i5 = BILLION;
                r4 -= i5;
                if (r4 >= i5) {
                    r4 -= i5;
                    i4 = i2 + 1;
                    bArr[i2] = 50;
                } else {
                    i4 = i2 + 1;
                    bArr[i2] = 49;
                }
                i2 = i4;
            }
            int i6 = r4 / 1000;
            int i7 = r4 - (i6 * 1000);
            int i8 = i6 / 1000;
            int i9 = i6 - (i8 * 1000);
            if (z) {
                i3 = full3(i8, bArr, i2);
            } else {
                i3 = leading3(i8, bArr, i2);
            }
            return full3(i7, bArr, full3(i9, bArr, i3));
        } else if (r4 >= 1000) {
            int i10 = r4 / 1000;
            return full3(r4 - (i10 * 1000), bArr, leading3(i10, bArr, i2));
        } else if (r4 >= 10) {
            return leading3(r4, bArr, i2);
        } else {
            int i11 = i2 + 1;
            bArr[i2] = (byte) (r4 + 48);
            return i11;
        }
    }

    public static int outputLong(long j, char[] cArr, int i) {
        if (j < 0) {
            if (j > MIN_INT_AS_LONG) {
                return outputInt((int) j, cArr, i);
            }
            if (j == Long.MIN_VALUE) {
                int length = SMALLEST_LONG.length();
                SMALLEST_LONG.getChars(0, length, cArr, i);
                return i + length;
            }
            cArr[i] = '-';
            j = -j;
            i++;
        } else if (j <= MAX_INT_AS_LONG) {
            return outputInt((int) j, cArr, i);
        }
        int calcLongStrLength = calcLongStrLength(j) + i;
        int i2 = calcLongStrLength;
        while (j > MAX_INT_AS_LONG) {
            i2 -= 3;
            long j2 = THOUSAND_L;
            long j3 = j / j2;
            full3((int) (j - (j2 * j3)), cArr, i2);
            j = j3;
        }
        int i3 = (int) j;
        while (i3 >= 1000) {
            i2 -= 3;
            int i4 = i3 / 1000;
            full3(i3 - (i4 * 1000), cArr, i2);
            i3 = i4;
        }
        leading3(i3, cArr, i);
        return calcLongStrLength;
    }

    public static int outputLong(long j, byte[] bArr, int i) {
        if (j < 0) {
            if (j > MIN_INT_AS_LONG) {
                return outputInt((int) j, bArr, i);
            }
            if (j == Long.MIN_VALUE) {
                int length = SMALLEST_LONG.length();
                int i2 = 0;
                while (i2 < length) {
                    bArr[i] = (byte) SMALLEST_LONG.charAt(i2);
                    i2++;
                    i++;
                }
                return i;
            }
            bArr[i] = 45;
            j = -j;
            i++;
        } else if (j <= MAX_INT_AS_LONG) {
            return outputInt((int) j, bArr, i);
        }
        int calcLongStrLength = calcLongStrLength(j) + i;
        int i3 = calcLongStrLength;
        while (j > MAX_INT_AS_LONG) {
            i3 -= 3;
            long j2 = THOUSAND_L;
            long j3 = j / j2;
            full3((int) (j - (j2 * j3)), bArr, i3);
            j = j3;
        }
        int i4 = (int) j;
        while (i4 >= 1000) {
            i3 -= 3;
            int i5 = i4 / 1000;
            full3(i4 - (i5 * 1000), bArr, i3);
            i4 = i5;
        }
        leading3(i4, bArr, i);
        return calcLongStrLength;
    }

    public static String toString(int i) {
        String[] strArr = sSmallIntStrs;
        if (i < strArr.length) {
            if (i >= 0) {
                return strArr[i];
            }
            int i2 = (-i) - 1;
            String[] strArr2 = sSmallIntStrs2;
            if (i2 < strArr2.length) {
                return strArr2[i2];
            }
        }
        return Integer.toString(i);
    }

    public static String toString(long j) {
        if (j > 2147483647L || j < -2147483648L) {
            return Long.toString(j);
        }
        return toString((int) j);
    }

    public static String toString(double d) {
        return Double.toString(d);
    }

    public static String toString(float f) {
        return Float.toString(f);
    }

    private static int leading3(int i, char[] cArr, int i2) {
        int i3 = i << 2;
        int i4 = i3 + 1;
        char c = LEAD_3[i3];
        if (c != 0) {
            cArr[i2] = c;
            i2++;
        }
        int i5 = i4 + 1;
        char c2 = LEAD_3[i4];
        if (c2 != 0) {
            cArr[i2] = c2;
            i2++;
        }
        int i6 = i2 + 1;
        cArr[i2] = LEAD_3[i5];
        return i6;
    }

    private static int leading3(int i, byte[] bArr, int i2) {
        int i3 = i << 2;
        int i4 = i3 + 1;
        char c = LEAD_3[i3];
        if (c != 0) {
            bArr[i2] = (byte) c;
            i2++;
        }
        int i5 = i4 + 1;
        char c2 = LEAD_3[i4];
        if (c2 != 0) {
            bArr[i2] = (byte) c2;
            i2++;
        }
        int i6 = i2 + 1;
        bArr[i2] = (byte) LEAD_3[i5];
        return i6;
    }

    private static int full3(int i, char[] cArr, int i2) {
        int i3 = i << 2;
        int i4 = i2 + 1;
        char[] cArr2 = FULL_3;
        int i5 = i3 + 1;
        cArr[i2] = cArr2[i3];
        int i6 = i4 + 1;
        cArr[i4] = cArr2[i5];
        int i7 = i6 + 1;
        cArr[i6] = cArr2[i5 + 1];
        return i7;
    }

    private static int full3(int i, byte[] bArr, int i2) {
        int i3 = i << 2;
        int i4 = i2 + 1;
        byte[] bArr2 = FULL_TRIPLETS_B;
        int i5 = i3 + 1;
        bArr[i2] = bArr2[i3];
        int i6 = i4 + 1;
        bArr[i4] = bArr2[i5];
        int i7 = i6 + 1;
        bArr[i6] = bArr2[i5 + 1];
        return i7;
    }

    private static int calcLongStrLength(long j) {
        int i = 10;
        for (long j2 = TEN_BILLION_L; j >= j2 && i != 19; j2 = (j2 << 1) + (j2 << 3)) {
            i++;
        }
        return i;
    }
}

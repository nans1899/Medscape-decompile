package com.webmd.wbmdcmepulse.models.utils.crypto;

import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;

public class InsecureSHA1PRNGKeyDerivator {
    private static final int BYTES_OFFSET = 81;
    private static final int COUNTER_BASE = 0;
    private static final int DIGEST_LENGTH = 20;
    private static final int[] END_FLAGS = {Integer.MIN_VALUE, 8388608, 32768, 128};
    private static final int EXTRAFRAME_OFFSET = 5;
    private static final int FRAME_LENGTH = 16;
    private static final int FRAME_OFFSET = 21;
    private static final int H0 = 1732584193;
    private static final int H1 = -271733879;
    private static final int H2 = -1732584194;
    private static final int H3 = 271733878;
    private static final int H4 = -1009589776;
    private static final int HASHBYTES_TO_USE = 20;
    private static final int HASHCOPY_OFFSET = 0;
    private static final int HASH_OFFSET = 82;
    private static final int[] LEFT = {0, 24, 16, 8};
    private static final int[] MASK = {-1, ViewCompat.MEASURED_SIZE_MASK, 65535, 255};
    private static final int MAX_BYTES = 48;
    private static final int NEXT_BYTES = 2;
    private static final int[] RIGHT1 = {0, 40, 48, 56};
    private static final int[] RIGHT2 = {0, 8, 16, 24};
    private static final int SET_SEED = 1;
    private static final int UNDEFINED = 0;
    private transient int[] copies = new int[37];
    private transient long counter = 0;
    private transient int nextBIndex = 20;
    private transient byte[] nextBytes = new byte[20];
    private transient int[] seed;
    private transient long seedLength = 0;
    private transient int state = 0;

    public static byte[] deriveInsecureKey(byte[] bArr, int i) {
        InsecureSHA1PRNGKeyDerivator insecureSHA1PRNGKeyDerivator = new InsecureSHA1PRNGKeyDerivator();
        insecureSHA1PRNGKeyDerivator.setSeed(bArr);
        byte[] bArr2 = new byte[i];
        insecureSHA1PRNGKeyDerivator.nextBytes(bArr2);
        return bArr2;
    }

    private InsecureSHA1PRNGKeyDerivator() {
        int[] iArr = new int[87];
        this.seed = iArr;
        iArr[82] = H0;
        iArr[83] = H1;
        iArr[84] = H2;
        iArr[85] = H3;
        iArr[86] = H4;
    }

    private void updateSeed(byte[] bArr) {
        updateHash(this.seed, bArr, 0, bArr.length - 1);
        this.seedLength += (long) bArr.length;
    }

    private void setSeed(byte[] bArr) {
        if (bArr != null) {
            if (this.state == 2) {
                System.arraycopy(this.copies, 0, this.seed, 82, 5);
            }
            this.state = 1;
            if (bArr.length != 0) {
                updateSeed(bArr);
                return;
            }
            return;
        }
        throw new NullPointerException("seed == null");
    }

    /* access modifiers changed from: protected */
    public synchronized void nextBytes(byte[] bArr) {
        int i;
        byte[] bArr2 = bArr;
        synchronized (this) {
            if (bArr2 != null) {
                try {
                    int i2 = this.seed[81] == 0 ? 0 : (this.seed[81] + 7) >> 2;
                    if (this.state != 0) {
                        char c = ' ';
                        int i3 = 48;
                        if (this.state == 1) {
                            System.arraycopy(this.seed, 82, this.copies, 0, 5);
                            for (int i4 = i2 + 3; i4 < 18; i4++) {
                                this.seed[i4] = 0;
                            }
                            long j = (this.seedLength << 3) + 64;
                            if (this.seed[81] < 48) {
                                this.seed[14] = (int) (j >>> 32);
                                this.seed[15] = (int) (j & -1);
                            } else {
                                this.copies[19] = (int) (j >>> 32);
                                this.copies[20] = (int) (j & -1);
                            }
                            this.nextBIndex = 20;
                        }
                        this.state = 2;
                        if (bArr2.length != 0) {
                            int length = 20 - this.nextBIndex < bArr2.length - 0 ? 20 - this.nextBIndex : bArr2.length - 0;
                            if (length > 0) {
                                System.arraycopy(this.nextBytes, this.nextBIndex, bArr2, 0, length);
                                this.nextBIndex += length;
                                i = length + 0;
                            } else {
                                i = 0;
                            }
                            if (i < bArr2.length) {
                                int i5 = this.seed[81] & 3;
                                while (true) {
                                    if (i5 == 0) {
                                        this.seed[i2] = (int) (this.counter >>> c);
                                        this.seed[i2 + 1] = (int) (this.counter & -1);
                                        this.seed[i2 + 2] = END_FLAGS[0];
                                    } else {
                                        int[] iArr = this.seed;
                                        iArr[i2] = ((int) (((long) MASK[i5]) & (this.counter >>> RIGHT1[i5]))) | iArr[i2];
                                        this.seed[i2 + 1] = (int) ((this.counter >>> RIGHT2[i5]) & -1);
                                        this.seed[i2 + 2] = (int) ((this.counter << LEFT[i5]) | ((long) END_FLAGS[i5]));
                                    }
                                    if (this.seed[81] > i3) {
                                        this.copies[5] = this.seed[16];
                                        this.copies[6] = this.seed[17];
                                    }
                                    computeHash(this.seed);
                                    if (this.seed[81] > i3) {
                                        System.arraycopy(this.seed, 0, this.copies, 21, 16);
                                        System.arraycopy(this.copies, 5, this.seed, 0, 16);
                                        computeHash(this.seed);
                                        System.arraycopy(this.copies, 21, this.seed, 0, 16);
                                    }
                                    this.counter++;
                                    int i6 = 0;
                                    for (int i7 = 0; i7 < 5; i7++) {
                                        int i8 = this.seed[i7 + 82];
                                        this.nextBytes[i6] = (byte) (i8 >>> 24);
                                        this.nextBytes[i6 + 1] = (byte) (i8 >>> 16);
                                        this.nextBytes[i6 + 2] = (byte) (i8 >>> 8);
                                        this.nextBytes[i6 + 3] = (byte) i8;
                                        i6 += 4;
                                    }
                                    this.nextBIndex = 0;
                                    int length2 = 20 < bArr2.length - i ? 20 : bArr2.length - i;
                                    if (length2 > 0) {
                                        System.arraycopy(this.nextBytes, 0, bArr2, i, length2);
                                        i += length2;
                                        this.nextBIndex += length2;
                                    }
                                    if (i < bArr2.length) {
                                        c = ' ';
                                        i3 = 48;
                                    } else {
                                        return;
                                    }
                                }
                            }
                        }
                    } else {
                        throw new IllegalStateException("No seed supplied!");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                throw new NullPointerException("bytes == null");
            }
        }
    }

    private static void computeHash(int[] iArr) {
        int i;
        int i2;
        int i3;
        int i4 = iArr[82];
        int i5 = iArr[83];
        int i6 = iArr[84];
        int i7 = iArr[85];
        int i8 = iArr[86];
        for (int i9 = 16; i9 < 80; i9++) {
            int i10 = ((iArr[i9 - 3] ^ iArr[i9 - 8]) ^ iArr[i9 - 14]) ^ iArr[i9 - 16];
            iArr[i9] = (i10 >>> 31) | (i10 << 1);
        }
        int i11 = 0;
        while (true) {
            i = 20;
            if (i11 >= 20) {
                break;
            }
            int i12 = i8 + iArr[i11] + 1518500249 + ((i4 << 5) | (i4 >>> 27)) + ((i5 & i6) | ((~i5) & i7));
            i11++;
            int i13 = (i5 >>> 2) | (i5 << 30);
            i5 = i4;
            i4 = i12;
            i8 = i7;
            i7 = i6;
            i6 = i13;
        }
        while (true) {
            i2 = 40;
            if (i >= 40) {
                break;
            }
            int i14 = i8 + iArr[i] + 1859775393 + ((i4 << 5) | (i4 >>> 27)) + ((i5 ^ i6) ^ i7);
            i++;
            int i15 = (i5 >>> 2) | (i5 << 30);
            i5 = i4;
            i4 = i14;
            i8 = i7;
            i7 = i6;
            i6 = i15;
        }
        while (true) {
            i3 = 60;
            if (i2 >= 60) {
                break;
            }
            int i16 = ((i8 + iArr[i2]) - 1894007588) + ((i4 << 5) | (i4 >>> 27)) + ((i5 & i6) | (i5 & i7) | (i6 & i7));
            i2++;
            int i17 = (i5 >>> 2) | (i5 << 30);
            i5 = i4;
            i4 = i16;
            i8 = i7;
            i7 = i6;
            i6 = i17;
        }
        while (i3 < 80) {
            int i18 = ((i8 + iArr[i3]) - 899497514) + ((i4 << 5) | (i4 >>> 27)) + ((i5 ^ i6) ^ i7);
            i3++;
            int i19 = (i5 >>> 2) | (i5 << 30);
            i5 = i4;
            i4 = i18;
            i8 = i7;
            i7 = i6;
            i6 = i19;
        }
        iArr[82] = iArr[82] + i4;
        iArr[83] = iArr[83] + i5;
        iArr[84] = iArr[84] + i6;
        iArr[85] = iArr[85] + i7;
        iArr[86] = iArr[86] + i8;
    }

    private static void updateHash(int[] iArr, byte[] bArr, int i, int i2) {
        int i3;
        int i4 = iArr[81];
        int i5 = i4 >> 2;
        int i6 = i4 & 3;
        iArr[81] = (((i4 + i2) - i) + 1) & 63;
        if (i6 != 0) {
            while (i <= i2 && i6 < 4) {
                iArr[i5] = iArr[i5] | ((bArr[i] & 255) << ((3 - i6) << 3));
                i6++;
                i++;
            }
            if (i6 == 4 && (i5 = i5 + 1) == 16) {
                computeHash(iArr);
                i5 = 0;
            }
            if (i > i2) {
                return;
            }
        }
        int i7 = ((i2 - i) + 1) >> 2;
        for (int i8 = 0; i8 < i7; i8++) {
            iArr[i3] = ((bArr[i] & 255) << Ascii.CAN) | ((bArr[i + 1] & 255) << Ascii.DLE) | ((bArr[i + 2] & 255) << 8) | (bArr[i + 3] & 255);
            i += 4;
            i3++;
            if (i3 >= 16) {
                computeHash(iArr);
                i3 = 0;
            }
        }
        int i9 = (i2 - i) + 1;
        if (i9 != 0) {
            int i10 = (bArr[i] & 255) << Ascii.CAN;
            if (i9 != 1) {
                i10 |= (bArr[i + 1] & 255) << Ascii.DLE;
                if (i9 != 2) {
                    i10 |= (bArr[i + 2] & 255) << 8;
                }
            }
            iArr[i3] = i10;
        }
    }
}

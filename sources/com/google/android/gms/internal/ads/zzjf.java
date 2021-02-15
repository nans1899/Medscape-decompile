package com.google.android.gms.internal.ads;

import java.nio.ShortBuffer;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzjf {
    private final int zzahn;
    private float zzaia = 1.0f;
    private float zzaib = 1.0f;
    private final int zzana;
    private final int zzanb;
    private final int zzanc;
    private final int zzand;
    private final short[] zzane;
    private int zzanf;
    private short[] zzang;
    private int zzanh;
    private short[] zzani;
    private int zzanj;
    private short[] zzank;
    private int zzanl = 0;
    private int zzanm = 0;
    private int zzann;
    private int zzano;
    private int zzanp;
    private int zzanq;
    private int zzanr = 0;
    private int zzans;
    private int zzant;
    private int zzanu;

    public zzjf(int i, int i2) {
        this.zzahn = i;
        this.zzana = i2;
        this.zzanb = i / 400;
        int i3 = i / 65;
        this.zzanc = i3;
        int i4 = i3 * 2;
        this.zzand = i4;
        this.zzane = new short[i4];
        this.zzanf = i4;
        this.zzang = new short[(i4 * i2)];
        this.zzanh = i4;
        this.zzani = new short[(i4 * i2)];
        this.zzanj = i4;
        this.zzank = new short[(i4 * i2)];
    }

    public final void setSpeed(float f) {
        this.zzaia = f;
    }

    public final void zzc(float f) {
        this.zzaib = f;
    }

    public final void zza(ShortBuffer shortBuffer) {
        int remaining = shortBuffer.remaining();
        int i = this.zzana;
        int i2 = remaining / i;
        zzab(i2);
        shortBuffer.get(this.zzang, this.zzann * this.zzana, ((i * i2) << 1) / 2);
        this.zzann += i2;
        zzgi();
    }

    public final void zzb(ShortBuffer shortBuffer) {
        int min = Math.min(shortBuffer.remaining() / this.zzana, this.zzano);
        shortBuffer.put(this.zzani, 0, this.zzana * min);
        int i = this.zzano - min;
        this.zzano = i;
        short[] sArr = this.zzani;
        int i2 = this.zzana;
        System.arraycopy(sArr, min * i2, sArr, 0, i * i2);
    }

    public final void zzfl() {
        int i;
        int i2 = this.zzann;
        float f = this.zzaia;
        float f2 = this.zzaib;
        int i3 = this.zzano + ((int) ((((((float) i2) / (f / f2)) + ((float) this.zzanp)) / f2) + 0.5f));
        zzab((this.zzand * 2) + i2);
        int i4 = 0;
        while (true) {
            i = this.zzand;
            int i5 = this.zzana;
            if (i4 >= i * 2 * i5) {
                break;
            }
            this.zzang[(i5 * i2) + i4] = 0;
            i4++;
        }
        this.zzann += i * 2;
        zzgi();
        if (this.zzano > i3) {
            this.zzano = i3;
        }
        this.zzann = 0;
        this.zzanq = 0;
        this.zzanp = 0;
    }

    public final int zzgh() {
        return this.zzano;
    }

    private final void zzaa(int i) {
        int i2 = this.zzano + i;
        int i3 = this.zzanh;
        if (i2 > i3) {
            int i4 = i3 + (i3 / 2) + i;
            this.zzanh = i4;
            this.zzani = Arrays.copyOf(this.zzani, i4 * this.zzana);
        }
    }

    private final void zzab(int i) {
        int i2 = this.zzann + i;
        int i3 = this.zzanf;
        if (i2 > i3) {
            int i4 = i3 + (i3 / 2) + i;
            this.zzanf = i4;
            this.zzang = Arrays.copyOf(this.zzang, i4 * this.zzana);
        }
    }

    private final void zza(short[] sArr, int i, int i2) {
        zzaa(i2);
        int i3 = this.zzana;
        System.arraycopy(sArr, i * i3, this.zzani, this.zzano * i3, i3 * i2);
        this.zzano += i2;
    }

    private final void zzb(short[] sArr, int i, int i2) {
        int i3 = this.zzand / i2;
        int i4 = this.zzana;
        int i5 = i2 * i4;
        int i6 = i * i4;
        for (int i7 = 0; i7 < i3; i7++) {
            int i8 = 0;
            for (int i9 = 0; i9 < i5; i9++) {
                i8 += sArr[(i7 * i5) + i6 + i9];
            }
            this.zzane[i7] = (short) (i8 / i5);
        }
    }

    private final int zza(short[] sArr, int i, int i2, int i3) {
        int i4 = i * this.zzana;
        int i5 = 1;
        int i6 = 255;
        int i7 = 0;
        int i8 = 0;
        while (i2 <= i3) {
            int i9 = 0;
            for (int i10 = 0; i10 < i2; i10++) {
                short s = sArr[i4 + i10];
                short s2 = sArr[i4 + i2 + i10];
                i9 += s >= s2 ? s - s2 : s2 - s;
            }
            if (i9 * i7 < i5 * i2) {
                i7 = i2;
                i5 = i9;
            }
            if (i9 * i6 > i8 * i2) {
                i6 = i2;
                i8 = i9;
            }
            i2++;
        }
        this.zzant = i5 / i7;
        this.zzanu = i8 / i6;
        return i7;
    }

    private final void zzgi() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = this.zzano;
        float f = this.zzaia / this.zzaib;
        double d = (double) f;
        int i8 = 1;
        if (d > 1.00001d || d < 0.99999d) {
            int i9 = this.zzann;
            if (i9 >= this.zzand) {
                int i10 = 0;
                while (true) {
                    int i11 = this.zzanq;
                    if (i11 > 0) {
                        int min = Math.min(this.zzand, i11);
                        zza(this.zzang, i10, min);
                        this.zzanq -= min;
                        i10 += min;
                    } else {
                        short[] sArr = this.zzang;
                        int i12 = this.zzahn;
                        int i13 = i12 > 4000 ? i12 / 4000 : 1;
                        if (this.zzana == i8 && i13 == i8) {
                            i4 = zza(sArr, i10, this.zzanb, this.zzanc);
                        } else {
                            zzb(sArr, i10, i13);
                            int zza = zza(this.zzane, 0, this.zzanb / i13, this.zzanc / i13);
                            if (i13 != i8) {
                                int i14 = zza * i13;
                                int i15 = i13 << 2;
                                int i16 = i14 - i15;
                                int i17 = i14 + i15;
                                int i18 = this.zzanb;
                                if (i16 < i18) {
                                    i16 = i18;
                                }
                                int i19 = this.zzanc;
                                if (i17 > i19) {
                                    i17 = i19;
                                }
                                if (this.zzana == i8) {
                                    i4 = zza(sArr, i10, i16, i17);
                                } else {
                                    zzb(sArr, i10, i8);
                                    i4 = zza(this.zzane, 0, i16, i17);
                                }
                            } else {
                                i4 = zza;
                            }
                        }
                        int i20 = this.zzant;
                        int i21 = i20 != 0 && this.zzanr != 0 && this.zzanu <= i20 * 3 && (i20 << 1) > this.zzans * 3 ? this.zzanr : i4;
                        this.zzans = this.zzant;
                        this.zzanr = i4;
                        if (d > 1.0d) {
                            short[] sArr2 = this.zzang;
                            if (f >= 2.0f) {
                                i6 = (int) (((float) i21) / (f - 1.0f));
                            } else {
                                this.zzanq = (int) ((((float) i21) * (2.0f - f)) / (f - 1.0f));
                                i6 = i21;
                            }
                            zzaa(i6);
                            int i22 = i6;
                            zza(i6, this.zzana, this.zzani, this.zzano, sArr2, i10, sArr2, i10 + i21);
                            this.zzano += i22;
                            i10 += i21 + i22;
                        } else {
                            int i23 = i21;
                            short[] sArr3 = this.zzang;
                            if (f < 0.5f) {
                                i5 = (int) ((((float) i23) * f) / (1.0f - f));
                            } else {
                                this.zzanq = (int) ((((float) i23) * ((2.0f * f) - 1.0f)) / (1.0f - f));
                                i5 = i23;
                            }
                            int i24 = i23 + i5;
                            zzaa(i24);
                            int i25 = this.zzana;
                            System.arraycopy(sArr3, i10 * i25, this.zzani, this.zzano * i25, i25 * i23);
                            zza(i5, this.zzana, this.zzani, this.zzano + i23, sArr3, i23 + i10, sArr3, i10);
                            this.zzano += i24;
                            i10 += i5;
                        }
                    }
                    if (this.zzand + i10 > i9) {
                        break;
                    }
                    i8 = 1;
                }
                int i26 = this.zzann - i10;
                short[] sArr4 = this.zzang;
                int i27 = this.zzana;
                System.arraycopy(sArr4, i10 * i27, sArr4, 0, i27 * i26);
                this.zzann = i26;
            }
        } else {
            zza(this.zzang, 0, this.zzann);
            this.zzann = 0;
        }
        float f2 = this.zzaib;
        if (f2 != 1.0f && this.zzano != i7) {
            int i28 = this.zzahn;
            int i29 = (int) (((float) i28) / f2);
            while (true) {
                if (i29 <= 16384 && i28 <= 16384) {
                    break;
                }
                i29 /= 2;
                i28 /= 2;
            }
            int i30 = this.zzano - i7;
            int i31 = this.zzanp + i30;
            int i32 = this.zzanj;
            if (i31 > i32) {
                int i33 = i32 + (i32 / 2) + i30;
                this.zzanj = i33;
                this.zzank = Arrays.copyOf(this.zzank, i33 * this.zzana);
            }
            short[] sArr5 = this.zzani;
            int i34 = this.zzana;
            System.arraycopy(sArr5, i7 * i34, this.zzank, this.zzanp * i34, i34 * i30);
            this.zzano = i7;
            this.zzanp += i30;
            int i35 = 0;
            while (true) {
                i = this.zzanp;
                if (i35 >= i - 1) {
                    break;
                }
                while (true) {
                    i2 = this.zzanl;
                    int i36 = (i2 + 1) * i29;
                    i3 = this.zzanm;
                    if (i36 <= i3 * i28) {
                        break;
                    }
                    zzaa(1);
                    int i37 = 0;
                    while (true) {
                        int i38 = this.zzana;
                        if (i37 >= i38) {
                            break;
                        }
                        short[] sArr6 = this.zzank;
                        int i39 = (i35 * i38) + i37;
                        short s = sArr6[i39];
                        short s2 = sArr6[i39 + i38];
                        int i40 = this.zzanl;
                        int i41 = i40 * i29;
                        int i42 = (i40 + 1) * i29;
                        int i43 = i42 - (this.zzanm * i28);
                        int i44 = i42 - i41;
                        this.zzani[(this.zzano * i38) + i37] = (short) (((s * i43) + ((i44 - i43) * s2)) / i44);
                        i37++;
                    }
                    this.zzanm++;
                    this.zzano++;
                }
                int i45 = i2 + 1;
                this.zzanl = i45;
                if (i45 == i28) {
                    this.zzanl = 0;
                    zzoz.checkState(i3 == i29);
                    this.zzanm = 0;
                }
                i35++;
            }
            int i46 = i - 1;
            if (i46 != 0) {
                short[] sArr7 = this.zzank;
                int i47 = this.zzana;
                System.arraycopy(sArr7, i46 * i47, sArr7, 0, (i - i46) * i47);
                this.zzanp -= i46;
            }
        }
    }

    private static void zza(int i, int i2, short[] sArr, int i3, short[] sArr2, int i4, short[] sArr3, int i5) {
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = (i3 * i2) + i6;
            int i8 = (i5 * i2) + i6;
            int i9 = (i4 * i2) + i6;
            for (int i10 = 0; i10 < i; i10++) {
                sArr[i7] = (short) (((sArr2[i9] * (i - i10)) + (sArr3[i8] * i10)) / i);
                i7 += i2;
                i9 += i2;
                i8 += i2;
            }
        }
    }
}

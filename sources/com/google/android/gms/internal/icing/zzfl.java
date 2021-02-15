package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdx;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzfl<T> implements zzfu<T> {
    private static final int[] zzmr = new int[0];
    private static final Unsafe zzms = zzgs.zzdp();
    private final zzfh zzmn;
    private final zzgm<?, ?> zzmo;
    private final boolean zzmp;
    private final zzdn<?> zzmq;
    private final int[] zzmt;
    private final Object[] zzmu;
    private final int zzmv;
    private final int zzmw;
    private final boolean zzmx;
    private final boolean zzmy;
    private final boolean zzmz;
    private final int[] zzna;
    private final int zznb;
    private final int zznc;
    private final zzfm zznd;
    private final zzer zzne;
    private final zzfa zznf;

    private zzfl(int[] iArr, Object[] objArr, int i, int i2, zzfh zzfh, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzfm zzfm, zzer zzer, zzgm<?, ?> zzgm, zzdn<?> zzdn, zzfa zzfa) {
        this.zzmt = iArr;
        this.zzmu = objArr;
        this.zzmv = i;
        this.zzmw = i2;
        this.zzmx = zzfh instanceof zzdx;
        this.zzmy = z;
        this.zzmp = zzdn != null && zzdn.zze(zzfh);
        this.zzmz = false;
        this.zzna = iArr2;
        this.zznb = i3;
        this.zznc = i4;
        this.zznd = zzfm;
        this.zzne = zzer;
        this.zzmo = zzgm;
        this.zzmq = zzdn;
        this.zzmn = zzfh;
        this.zznf = zzfa;
    }

    static <T> zzfl<T> zza(Class<T> cls, zzff zzff, zzfm zzfm, zzer zzer, zzgm<?, ?> zzgm, zzdn<?> zzdn, zzfa zzfa) {
        int i;
        char c;
        int[] iArr;
        char c2;
        int i2;
        char c3;
        char c4;
        char c5;
        char c6;
        int i3;
        boolean z;
        int i4;
        zzfv zzfv;
        int i5;
        Class<?> cls2;
        int i6;
        String str;
        int i7;
        int i8;
        char c7;
        char c8;
        Field field;
        int i9;
        char charAt;
        int i10;
        Field field2;
        Field field3;
        int i11;
        char charAt2;
        int i12;
        char charAt3;
        int i13;
        char charAt4;
        int i14;
        int i15;
        char charAt5;
        int i16;
        char charAt6;
        int i17;
        char charAt7;
        int i18;
        char charAt8;
        int i19;
        char charAt9;
        int i20;
        char charAt10;
        int i21;
        char charAt11;
        int i22;
        char charAt12;
        int i23;
        char charAt13;
        char charAt14;
        zzff zzff2 = zzff;
        if (zzff2 instanceof zzfv) {
            zzfv zzfv2 = (zzfv) zzff2;
            char c9 = 0;
            boolean z2 = zzfv2.zzco() == zzdx.zze.zzkv;
            String zzcw = zzfv2.zzcw();
            int length = zzcw.length();
            char charAt15 = zzcw.charAt(0);
            if (charAt15 >= 55296) {
                char c10 = charAt15 & 8191;
                int i24 = 1;
                int i25 = 13;
                while (true) {
                    i = i24 + 1;
                    charAt14 = zzcw.charAt(i24);
                    if (charAt14 < 55296) {
                        break;
                    }
                    c10 |= (charAt14 & 8191) << i25;
                    i25 += 13;
                    i24 = i;
                }
                charAt15 = c10 | (charAt14 << i25);
            } else {
                i = 1;
            }
            int i26 = i + 1;
            char charAt16 = zzcw.charAt(i);
            if (charAt16 >= 55296) {
                char c11 = charAt16 & 8191;
                int i27 = 13;
                while (true) {
                    i23 = i26 + 1;
                    charAt13 = zzcw.charAt(i26);
                    if (charAt13 < 55296) {
                        break;
                    }
                    c11 |= (charAt13 & 8191) << i27;
                    i27 += 13;
                    i26 = i23;
                }
                charAt16 = c11 | (charAt13 << i27);
                i26 = i23;
            }
            if (charAt16 == 0) {
                iArr = zzmr;
                c5 = 0;
                c4 = 0;
                c3 = 0;
                i2 = 0;
                c2 = 0;
                c = 0;
            } else {
                int i28 = i26 + 1;
                char charAt17 = zzcw.charAt(i26);
                if (charAt17 >= 55296) {
                    char c12 = charAt17 & 8191;
                    int i29 = 13;
                    while (true) {
                        i22 = i28 + 1;
                        charAt12 = zzcw.charAt(i28);
                        if (charAt12 < 55296) {
                            break;
                        }
                        c12 |= (charAt12 & 8191) << i29;
                        i29 += 13;
                        i28 = i22;
                    }
                    charAt17 = c12 | (charAt12 << i29);
                    i28 = i22;
                }
                int i30 = i28 + 1;
                char charAt18 = zzcw.charAt(i28);
                if (charAt18 >= 55296) {
                    char c13 = charAt18 & 8191;
                    int i31 = 13;
                    while (true) {
                        i21 = i30 + 1;
                        charAt11 = zzcw.charAt(i30);
                        if (charAt11 < 55296) {
                            break;
                        }
                        c13 |= (charAt11 & 8191) << i31;
                        i31 += 13;
                        i30 = i21;
                    }
                    charAt18 = c13 | (charAt11 << i31);
                    i30 = i21;
                }
                int i32 = i30 + 1;
                c3 = zzcw.charAt(i30);
                if (c3 >= 55296) {
                    char c14 = c3 & 8191;
                    int i33 = 13;
                    while (true) {
                        i20 = i32 + 1;
                        charAt10 = zzcw.charAt(i32);
                        if (charAt10 < 55296) {
                            break;
                        }
                        c14 |= (charAt10 & 8191) << i33;
                        i33 += 13;
                        i32 = i20;
                    }
                    c3 = c14 | (charAt10 << i33);
                    i32 = i20;
                }
                int i34 = i32 + 1;
                char charAt19 = zzcw.charAt(i32);
                if (charAt19 >= 55296) {
                    char c15 = charAt19 & 8191;
                    int i35 = 13;
                    while (true) {
                        i19 = i34 + 1;
                        charAt9 = zzcw.charAt(i34);
                        if (charAt9 < 55296) {
                            break;
                        }
                        c15 |= (charAt9 & 8191) << i35;
                        i35 += 13;
                        i34 = i19;
                    }
                    charAt19 = c15 | (charAt9 << i35);
                    i34 = i19;
                }
                int i36 = i34 + 1;
                c2 = zzcw.charAt(i34);
                if (c2 >= 55296) {
                    char c16 = c2 & 8191;
                    int i37 = 13;
                    while (true) {
                        i18 = i36 + 1;
                        charAt8 = zzcw.charAt(i36);
                        if (charAt8 < 55296) {
                            break;
                        }
                        c16 |= (charAt8 & 8191) << i37;
                        i37 += 13;
                        i36 = i18;
                    }
                    c2 = c16 | (charAt8 << i37);
                    i36 = i18;
                }
                int i38 = i36 + 1;
                char charAt20 = zzcw.charAt(i36);
                if (charAt20 >= 55296) {
                    char c17 = charAt20 & 8191;
                    int i39 = 13;
                    while (true) {
                        i17 = i38 + 1;
                        charAt7 = zzcw.charAt(i38);
                        if (charAt7 < 55296) {
                            break;
                        }
                        c17 |= (charAt7 & 8191) << i39;
                        i39 += 13;
                        i38 = i17;
                    }
                    charAt20 = c17 | (charAt7 << i39);
                    i38 = i17;
                }
                int i40 = i38 + 1;
                char charAt21 = zzcw.charAt(i38);
                if (charAt21 >= 55296) {
                    char c18 = charAt21 & 8191;
                    int i41 = i40;
                    int i42 = 13;
                    while (true) {
                        i16 = i41 + 1;
                        charAt6 = zzcw.charAt(i41);
                        if (charAt6 < 55296) {
                            break;
                        }
                        c18 |= (charAt6 & 8191) << i42;
                        i42 += 13;
                        i41 = i16;
                    }
                    charAt21 = c18 | (charAt6 << i42);
                    i14 = i16;
                } else {
                    i14 = i40;
                }
                int i43 = i14 + 1;
                char charAt22 = zzcw.charAt(i14);
                if (charAt22 >= 55296) {
                    char c19 = charAt22 & 8191;
                    int i44 = i43;
                    int i45 = 13;
                    while (true) {
                        i15 = i44 + 1;
                        charAt5 = zzcw.charAt(i44);
                        if (charAt5 < 55296) {
                            break;
                        }
                        c19 |= (charAt5 & 8191) << i45;
                        i45 += 13;
                        i44 = i15;
                    }
                    charAt22 = c19 | (charAt5 << i45);
                    i43 = i15;
                }
                int i46 = (charAt17 << 1) + charAt18;
                c4 = charAt19;
                i2 = i46;
                c = charAt22;
                c9 = charAt17;
                i26 = i43;
                char c20 = charAt20;
                iArr = new int[(charAt22 + charAt20 + charAt21)];
                c5 = c20;
            }
            Unsafe unsafe = zzms;
            Object[] zzcx = zzfv2.zzcx();
            Class<?> cls3 = zzfv2.zzcq().getClass();
            int i47 = i26;
            int[] iArr2 = new int[(c2 * 3)];
            Object[] objArr = new Object[(c2 << 1)];
            int i48 = c + c5;
            char c21 = c;
            int i49 = i47;
            int i50 = i48;
            int i51 = 0;
            int i52 = 0;
            while (i49 < length) {
                int i53 = i49 + 1;
                int charAt23 = zzcw.charAt(i49);
                int i54 = length;
                if (charAt23 >= 55296) {
                    int i55 = charAt23 & 8191;
                    int i56 = i53;
                    int i57 = 13;
                    while (true) {
                        i13 = i56 + 1;
                        charAt4 = zzcw.charAt(i56);
                        c6 = c;
                        if (charAt4 < 55296) {
                            break;
                        }
                        i55 |= (charAt4 & 8191) << i57;
                        i57 += 13;
                        i56 = i13;
                        c = c6;
                    }
                    charAt23 = i55 | (charAt4 << i57);
                    i3 = i13;
                } else {
                    c6 = c;
                    i3 = i53;
                }
                int i58 = i3 + 1;
                char charAt24 = zzcw.charAt(i3);
                int i59 = i58;
                if (charAt24 >= 55296) {
                    char c22 = charAt24 & 8191;
                    int i60 = i59;
                    int i61 = 13;
                    while (true) {
                        i12 = i60 + 1;
                        charAt3 = zzcw.charAt(i60);
                        z = z2;
                        if (charAt3 < 55296) {
                            break;
                        }
                        c22 |= (charAt3 & 8191) << i61;
                        i61 += 13;
                        i60 = i12;
                        z2 = z;
                    }
                    charAt24 = c22 | (charAt3 << i61);
                    i4 = i12;
                } else {
                    z = z2;
                    i4 = i59;
                }
                char c23 = charAt24 & 255;
                char c24 = c4;
                if ((charAt24 & 1024) != 0) {
                    iArr[i51] = i52;
                    i51++;
                }
                char c25 = c3;
                if (c23 >= '3') {
                    int i62 = i4 + 1;
                    char charAt25 = zzcw.charAt(i4);
                    char c26 = 55296;
                    if (charAt25 >= 55296) {
                        char c27 = charAt25 & 8191;
                        int i63 = 13;
                        while (true) {
                            i11 = i62 + 1;
                            charAt2 = zzcw.charAt(i62);
                            if (charAt2 < c26) {
                                break;
                            }
                            c27 |= (charAt2 & 8191) << i63;
                            i63 += 13;
                            i62 = i11;
                            c26 = 55296;
                        }
                        charAt25 = c27 | (charAt2 << i63);
                        i62 = i11;
                    }
                    int i64 = c23 - '3';
                    int i65 = i62;
                    if (i64 == 9 || i64 == 17) {
                        objArr[((i52 / 3) << 1) + 1] = zzcx[i2];
                        i2++;
                    } else if (i64 == 12 && (charAt15 & 1) == 1) {
                        objArr[((i52 / 3) << 1) + 1] = zzcx[i2];
                        i2++;
                    }
                    int i66 = charAt25 << 1;
                    Object obj = zzcx[i66];
                    if (obj instanceof Field) {
                        field2 = (Field) obj;
                    } else {
                        field2 = zza(cls3, (String) obj);
                        zzcx[i66] = field2;
                    }
                    zzfv = zzfv2;
                    String str2 = zzcw;
                    i8 = (int) unsafe.objectFieldOffset(field2);
                    int i67 = i66 + 1;
                    Object obj2 = zzcx[i67];
                    if (obj2 instanceof Field) {
                        field3 = (Field) obj2;
                    } else {
                        field3 = zza(cls3, (String) obj2);
                        zzcx[i67] = field3;
                    }
                    cls2 = cls3;
                    i5 = i2;
                    i4 = i65;
                    str = str2;
                    i6 = 0;
                    i7 = (int) unsafe.objectFieldOffset(field3);
                    c7 = c9;
                } else {
                    zzfv = zzfv2;
                    String str3 = zzcw;
                    int i68 = i2 + 1;
                    Field zza = zza(cls3, (String) zzcx[i2]);
                    if (c23 == 9 || c23 == 17) {
                        c8 = 1;
                        objArr[((i52 / 3) << 1) + 1] = zza.getType();
                    } else {
                        if (c23 == 27 || c23 == '1') {
                            c8 = 1;
                            i10 = i68 + 1;
                            objArr[((i52 / 3) << 1) + 1] = zzcx[i68];
                        } else if (c23 == 12 || c23 == 30 || c23 == ',') {
                            c8 = 1;
                            if ((charAt15 & 1) == 1) {
                                i10 = i68 + 1;
                                objArr[((i52 / 3) << 1) + 1] = zzcx[i68];
                            }
                        } else {
                            if (c23 == '2') {
                                int i69 = c21 + 1;
                                iArr[c21] = i52;
                                int i70 = (i52 / 3) << 1;
                                int i71 = i68 + 1;
                                objArr[i70] = zzcx[i68];
                                if ((charAt24 & 2048) != 0) {
                                    i68 = i71 + 1;
                                    objArr[i70 + 1] = zzcx[i71];
                                    c21 = i69;
                                } else {
                                    i68 = i71;
                                    c8 = 1;
                                    c21 = i69;
                                }
                            }
                            c8 = 1;
                        }
                        i68 = i10;
                    }
                    i8 = (int) unsafe.objectFieldOffset(zza);
                    if ((charAt15 & 1) != c8 || c23 > 17) {
                        i5 = i68;
                        c7 = c9;
                        cls2 = cls3;
                        str = str3;
                        i7 = 0;
                        i6 = 0;
                    } else {
                        int i72 = i4 + 1;
                        str = str3;
                        char charAt26 = str.charAt(i4);
                        if (charAt26 >= 55296) {
                            char c28 = charAt26 & 8191;
                            int i73 = 13;
                            while (true) {
                                i9 = i72 + 1;
                                charAt = str.charAt(i72);
                                if (charAt < 55296) {
                                    break;
                                }
                                c28 |= (charAt & 8191) << i73;
                                i73 += 13;
                                i72 = i9;
                            }
                            charAt26 = c28 | (charAt << i73);
                            i72 = i9;
                        }
                        int i74 = (c9 << 1) + (charAt26 / ' ');
                        Object obj3 = zzcx[i74];
                        i5 = i68;
                        if (obj3 instanceof Field) {
                            field = (Field) obj3;
                        } else {
                            field = zza(cls3, (String) obj3);
                            zzcx[i74] = field;
                        }
                        c7 = c9;
                        cls2 = cls3;
                        i7 = (int) unsafe.objectFieldOffset(field);
                        i6 = charAt26 % ' ';
                        i4 = i72;
                    }
                    if (c23 >= 18 && c23 <= '1') {
                        iArr[i50] = i8;
                        i50++;
                    }
                }
                int i75 = i52 + 1;
                iArr2[i52] = charAt23;
                int i76 = i75 + 1;
                iArr2[i75] = i8 | ((charAt24 & 256) != 0 ? 268435456 : 0) | ((charAt24 & 512) != 0 ? 536870912 : 0) | (c23 << 20);
                i52 = i76 + 1;
                iArr2[i76] = (i6 << 20) | i7;
                c9 = c7;
                zzcw = str;
                i49 = i4;
                cls3 = cls2;
                c4 = c24;
                length = i54;
                c = c6;
                z2 = z;
                c3 = c25;
                i2 = i5;
                zzfv2 = zzfv;
            }
            char c29 = c4;
            boolean z3 = z2;
            return new zzfl(iArr2, objArr, c3, c4, zzfv2.zzcq(), z2, false, iArr, c, i48, zzfm, zzer, zzgm, zzdn, zzfa);
        }
        int zzco = ((zzgj) zzff2).zzco();
        int i77 = zzdx.zze.zzkv;
        throw new NoSuchMethodError();
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006a, code lost:
        if (com.google.android.gms.internal.icing.zzfw.zzd(com.google.android.gms.internal.icing.zzgs.zzo(r10, r6), com.google.android.gms.internal.icing.zzgs.zzo(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007e, code lost:
        if (com.google.android.gms.internal.icing.zzgs.zzk(r10, r6) == com.google.android.gms.internal.icing.zzgs.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0090, code lost:
        if (com.google.android.gms.internal.icing.zzgs.zzj(r10, r6) == com.google.android.gms.internal.icing.zzgs.zzj(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a4, code lost:
        if (com.google.android.gms.internal.icing.zzgs.zzk(r10, r6) == com.google.android.gms.internal.icing.zzgs.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b6, code lost:
        if (com.google.android.gms.internal.icing.zzgs.zzj(r10, r6) == com.google.android.gms.internal.icing.zzgs.zzj(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c8, code lost:
        if (com.google.android.gms.internal.icing.zzgs.zzj(r10, r6) == com.google.android.gms.internal.icing.zzgs.zzj(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00da, code lost:
        if (com.google.android.gms.internal.icing.zzgs.zzj(r10, r6) == com.google.android.gms.internal.icing.zzgs.zzj(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f0, code lost:
        if (com.google.android.gms.internal.icing.zzfw.zzd(com.google.android.gms.internal.icing.zzgs.zzo(r10, r6), com.google.android.gms.internal.icing.zzgs.zzo(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0106, code lost:
        if (com.google.android.gms.internal.icing.zzfw.zzd(com.google.android.gms.internal.icing.zzgs.zzo(r10, r6), com.google.android.gms.internal.icing.zzgs.zzo(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x011c, code lost:
        if (com.google.android.gms.internal.icing.zzfw.zzd(com.google.android.gms.internal.icing.zzgs.zzo(r10, r6), com.google.android.gms.internal.icing.zzgs.zzo(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x012e, code lost:
        if (com.google.android.gms.internal.icing.zzgs.zzl(r10, r6) == com.google.android.gms.internal.icing.zzgs.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0140, code lost:
        if (com.google.android.gms.internal.icing.zzgs.zzj(r10, r6) == com.google.android.gms.internal.icing.zzgs.zzj(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0154, code lost:
        if (com.google.android.gms.internal.icing.zzgs.zzk(r10, r6) == com.google.android.gms.internal.icing.zzgs.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0165, code lost:
        if (com.google.android.gms.internal.icing.zzgs.zzj(r10, r6) == com.google.android.gms.internal.icing.zzgs.zzj(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0178, code lost:
        if (com.google.android.gms.internal.icing.zzgs.zzk(r10, r6) == com.google.android.gms.internal.icing.zzgs.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x018b, code lost:
        if (com.google.android.gms.internal.icing.zzgs.zzk(r10, r6) == com.google.android.gms.internal.icing.zzgs.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01a4, code lost:
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.icing.zzgs.zzm(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.icing.zzgs.zzm(r11, r6))) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01bf, code lost:
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.icing.zzgs.zzn(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.icing.zzgs.zzn(r11, r6))) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01c1, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        if (com.google.android.gms.internal.icing.zzfw.zzd(com.google.android.gms.internal.icing.zzgs.zzo(r10, r6), com.google.android.gms.internal.icing.zzgs.zzo(r11, r6)) != false) goto L_0x01c2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(T r10, T r11) {
        /*
            r9 = this;
            int[] r0 = r9.zzmt
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            r3 = 1
            if (r2 >= r0) goto L_0x01c9
            int r4 = r9.zzag(r2)
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r4 & r5
            long r6 = (long) r6
            r8 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = r4 & r8
            int r4 = r4 >>> 20
            switch(r4) {
                case 0: goto L_0x01a7;
                case 1: goto L_0x018e;
                case 2: goto L_0x017b;
                case 3: goto L_0x0168;
                case 4: goto L_0x0157;
                case 5: goto L_0x0144;
                case 6: goto L_0x0132;
                case 7: goto L_0x0120;
                case 8: goto L_0x010a;
                case 9: goto L_0x00f4;
                case 10: goto L_0x00de;
                case 11: goto L_0x00cc;
                case 12: goto L_0x00ba;
                case 13: goto L_0x00a8;
                case 14: goto L_0x0094;
                case 15: goto L_0x0082;
                case 16: goto L_0x006e;
                case 17: goto L_0x0058;
                case 18: goto L_0x004a;
                case 19: goto L_0x004a;
                case 20: goto L_0x004a;
                case 21: goto L_0x004a;
                case 22: goto L_0x004a;
                case 23: goto L_0x004a;
                case 24: goto L_0x004a;
                case 25: goto L_0x004a;
                case 26: goto L_0x004a;
                case 27: goto L_0x004a;
                case 28: goto L_0x004a;
                case 29: goto L_0x004a;
                case 30: goto L_0x004a;
                case 31: goto L_0x004a;
                case 32: goto L_0x004a;
                case 33: goto L_0x004a;
                case 34: goto L_0x004a;
                case 35: goto L_0x004a;
                case 36: goto L_0x004a;
                case 37: goto L_0x004a;
                case 38: goto L_0x004a;
                case 39: goto L_0x004a;
                case 40: goto L_0x004a;
                case 41: goto L_0x004a;
                case 42: goto L_0x004a;
                case 43: goto L_0x004a;
                case 44: goto L_0x004a;
                case 45: goto L_0x004a;
                case 46: goto L_0x004a;
                case 47: goto L_0x004a;
                case 48: goto L_0x004a;
                case 49: goto L_0x004a;
                case 50: goto L_0x003c;
                case 51: goto L_0x001c;
                case 52: goto L_0x001c;
                case 53: goto L_0x001c;
                case 54: goto L_0x001c;
                case 55: goto L_0x001c;
                case 56: goto L_0x001c;
                case 57: goto L_0x001c;
                case 58: goto L_0x001c;
                case 59: goto L_0x001c;
                case 60: goto L_0x001c;
                case 61: goto L_0x001c;
                case 62: goto L_0x001c;
                case 63: goto L_0x001c;
                case 64: goto L_0x001c;
                case 65: goto L_0x001c;
                case 66: goto L_0x001c;
                case 67: goto L_0x001c;
                case 68: goto L_0x001c;
                default: goto L_0x001a;
            }
        L_0x001a:
            goto L_0x01c2
        L_0x001c:
            int r4 = r9.zzah(r2)
            r4 = r4 & r5
            long r4 = (long) r4
            int r8 = com.google.android.gms.internal.icing.zzgs.zzj(r10, r4)
            int r4 = com.google.android.gms.internal.icing.zzgs.zzj(r11, r4)
            if (r8 != r4) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzgs.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzgs.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.icing.zzfw.zzd((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x003c:
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzgs.zzo(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzgs.zzo(r11, r6)
            boolean r3 = com.google.android.gms.internal.icing.zzfw.zzd((java.lang.Object) r3, (java.lang.Object) r4)
            goto L_0x01c2
        L_0x004a:
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzgs.zzo(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzgs.zzo(r11, r6)
            boolean r3 = com.google.android.gms.internal.icing.zzfw.zzd((java.lang.Object) r3, (java.lang.Object) r4)
            goto L_0x01c2
        L_0x0058:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzgs.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzgs.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.icing.zzfw.zzd((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x006e:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.icing.zzgs.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.icing.zzgs.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0082:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.icing.zzgs.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.icing.zzgs.zzj(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0094:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.icing.zzgs.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.icing.zzgs.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x00a8:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.icing.zzgs.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.icing.zzgs.zzj(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00ba:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.icing.zzgs.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.icing.zzgs.zzj(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00cc:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.icing.zzgs.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.icing.zzgs.zzj(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00de:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzgs.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzgs.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.icing.zzfw.zzd((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x00f4:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzgs.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzgs.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.icing.zzfw.zzd((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x010a:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.icing.zzgs.zzo(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzgs.zzo(r11, r6)
            boolean r4 = com.google.android.gms.internal.icing.zzfw.zzd((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0120:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            boolean r4 = com.google.android.gms.internal.icing.zzgs.zzl(r10, r6)
            boolean r5 = com.google.android.gms.internal.icing.zzgs.zzl(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0132:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.icing.zzgs.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.icing.zzgs.zzj(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0144:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.icing.zzgs.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.icing.zzgs.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0157:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.icing.zzgs.zzj(r10, r6)
            int r5 = com.google.android.gms.internal.icing.zzgs.zzj(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0168:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.icing.zzgs.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.icing.zzgs.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x017b:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.icing.zzgs.zzk(r10, r6)
            long r6 = com.google.android.gms.internal.icing.zzgs.zzk(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x018e:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            float r4 = com.google.android.gms.internal.icing.zzgs.zzm(r10, r6)
            int r4 = java.lang.Float.floatToIntBits(r4)
            float r5 = com.google.android.gms.internal.icing.zzgs.zzm(r11, r6)
            int r5 = java.lang.Float.floatToIntBits(r5)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x01a7:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01c1
            double r4 = com.google.android.gms.internal.icing.zzgs.zzn(r10, r6)
            long r4 = java.lang.Double.doubleToLongBits(r4)
            double r6 = com.google.android.gms.internal.icing.zzgs.zzn(r11, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
        L_0x01c1:
            r3 = 0
        L_0x01c2:
            if (r3 != 0) goto L_0x01c5
            return r1
        L_0x01c5:
            int r2 = r2 + 3
            goto L_0x0005
        L_0x01c9:
            com.google.android.gms.internal.icing.zzgm<?, ?> r0 = r9.zzmo
            java.lang.Object r0 = r0.zzp(r10)
            com.google.android.gms.internal.icing.zzgm<?, ?> r2 = r9.zzmo
            java.lang.Object r2 = r2.zzp(r11)
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x01dc
            return r1
        L_0x01dc:
            boolean r0 = r9.zzmp
            if (r0 == 0) goto L_0x01f1
            com.google.android.gms.internal.icing.zzdn<?> r0 = r9.zzmq
            com.google.android.gms.internal.icing.zzds r10 = r0.zzd(r10)
            com.google.android.gms.internal.icing.zzdn<?> r0 = r9.zzmq
            com.google.android.gms.internal.icing.zzds r11 = r0.zzd(r11)
            boolean r10 = r10.equals(r11)
            return r10
        L_0x01f1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzfl.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01c3, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0227, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0228, code lost:
        r1 = r1 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int hashCode(T r9) {
        /*
            r8 = this;
            int[] r0 = r8.zzmt
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            if (r1 >= r0) goto L_0x022c
            int r3 = r8.zzag(r1)
            int[] r4 = r8.zzmt
            r4 = r4[r1]
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r3
            long r5 = (long) r5
            r7 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = r3 & r7
            int r3 = r3 >>> 20
            r7 = 37
            switch(r3) {
                case 0: goto L_0x0219;
                case 1: goto L_0x020e;
                case 2: goto L_0x0203;
                case 3: goto L_0x01f8;
                case 4: goto L_0x01f1;
                case 5: goto L_0x01e6;
                case 6: goto L_0x01df;
                case 7: goto L_0x01d4;
                case 8: goto L_0x01c7;
                case 9: goto L_0x01b9;
                case 10: goto L_0x01ad;
                case 11: goto L_0x01a5;
                case 12: goto L_0x019d;
                case 13: goto L_0x0195;
                case 14: goto L_0x0189;
                case 15: goto L_0x0181;
                case 16: goto L_0x0175;
                case 17: goto L_0x016a;
                case 18: goto L_0x015e;
                case 19: goto L_0x015e;
                case 20: goto L_0x015e;
                case 21: goto L_0x015e;
                case 22: goto L_0x015e;
                case 23: goto L_0x015e;
                case 24: goto L_0x015e;
                case 25: goto L_0x015e;
                case 26: goto L_0x015e;
                case 27: goto L_0x015e;
                case 28: goto L_0x015e;
                case 29: goto L_0x015e;
                case 30: goto L_0x015e;
                case 31: goto L_0x015e;
                case 32: goto L_0x015e;
                case 33: goto L_0x015e;
                case 34: goto L_0x015e;
                case 35: goto L_0x015e;
                case 36: goto L_0x015e;
                case 37: goto L_0x015e;
                case 38: goto L_0x015e;
                case 39: goto L_0x015e;
                case 40: goto L_0x015e;
                case 41: goto L_0x015e;
                case 42: goto L_0x015e;
                case 43: goto L_0x015e;
                case 44: goto L_0x015e;
                case 45: goto L_0x015e;
                case 46: goto L_0x015e;
                case 47: goto L_0x015e;
                case 48: goto L_0x015e;
                case 49: goto L_0x015e;
                case 50: goto L_0x0152;
                case 51: goto L_0x013c;
                case 52: goto L_0x012a;
                case 53: goto L_0x0118;
                case 54: goto L_0x0106;
                case 55: goto L_0x00f8;
                case 56: goto L_0x00e6;
                case 57: goto L_0x00d8;
                case 58: goto L_0x00c6;
                case 59: goto L_0x00b2;
                case 60: goto L_0x00a0;
                case 61: goto L_0x008e;
                case 62: goto L_0x0080;
                case 63: goto L_0x0072;
                case 64: goto L_0x0064;
                case 65: goto L_0x0052;
                case 66: goto L_0x0044;
                case 67: goto L_0x0032;
                case 68: goto L_0x0020;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x0228
        L_0x0020:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzgs.zzo(r9, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x0032:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzh(r9, r5)
            int r3 = com.google.android.gms.internal.icing.zzeb.zzk(r3)
            goto L_0x0227
        L_0x0044:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzg(r9, r5)
            goto L_0x0227
        L_0x0052:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzh(r9, r5)
            int r3 = com.google.android.gms.internal.icing.zzeb.zzk(r3)
            goto L_0x0227
        L_0x0064:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzg(r9, r5)
            goto L_0x0227
        L_0x0072:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzg(r9, r5)
            goto L_0x0227
        L_0x0080:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzg(r9, r5)
            goto L_0x0227
        L_0x008e:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzgs.zzo(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00a0:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzgs.zzo(r9, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00b2:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzgs.zzo(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00c6:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            boolean r3 = zzi(r9, r5)
            int r3 = com.google.android.gms.internal.icing.zzeb.zzg(r3)
            goto L_0x0227
        L_0x00d8:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzg(r9, r5)
            goto L_0x0227
        L_0x00e6:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzh(r9, r5)
            int r3 = com.google.android.gms.internal.icing.zzeb.zzk(r3)
            goto L_0x0227
        L_0x00f8:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzg(r9, r5)
            goto L_0x0227
        L_0x0106:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzh(r9, r5)
            int r3 = com.google.android.gms.internal.icing.zzeb.zzk(r3)
            goto L_0x0227
        L_0x0118:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzh(r9, r5)
            int r3 = com.google.android.gms.internal.icing.zzeb.zzk(r3)
            goto L_0x0227
        L_0x012a:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            float r3 = zzf(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0227
        L_0x013c:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            double r3 = zze(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.icing.zzeb.zzk(r3)
            goto L_0x0227
        L_0x0152:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzgs.zzo(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x015e:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzgs.zzo(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x016a:
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzgs.zzo(r9, r5)
            if (r3 == 0) goto L_0x01c3
            int r7 = r3.hashCode()
            goto L_0x01c3
        L_0x0175:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.icing.zzgs.zzk(r9, r5)
            int r3 = com.google.android.gms.internal.icing.zzeb.zzk(r3)
            goto L_0x0227
        L_0x0181:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.icing.zzgs.zzj(r9, r5)
            goto L_0x0227
        L_0x0189:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.icing.zzgs.zzk(r9, r5)
            int r3 = com.google.android.gms.internal.icing.zzeb.zzk(r3)
            goto L_0x0227
        L_0x0195:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.icing.zzgs.zzj(r9, r5)
            goto L_0x0227
        L_0x019d:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.icing.zzgs.zzj(r9, r5)
            goto L_0x0227
        L_0x01a5:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.icing.zzgs.zzj(r9, r5)
            goto L_0x0227
        L_0x01ad:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzgs.zzo(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x01b9:
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzgs.zzo(r9, r5)
            if (r3 == 0) goto L_0x01c3
            int r7 = r3.hashCode()
        L_0x01c3:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x0228
        L_0x01c7:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.icing.zzgs.zzo(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x01d4:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.icing.zzgs.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.icing.zzeb.zzg(r3)
            goto L_0x0227
        L_0x01df:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.icing.zzgs.zzj(r9, r5)
            goto L_0x0227
        L_0x01e6:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.icing.zzgs.zzk(r9, r5)
            int r3 = com.google.android.gms.internal.icing.zzeb.zzk(r3)
            goto L_0x0227
        L_0x01f1:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.icing.zzgs.zzj(r9, r5)
            goto L_0x0227
        L_0x01f8:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.icing.zzgs.zzk(r9, r5)
            int r3 = com.google.android.gms.internal.icing.zzeb.zzk(r3)
            goto L_0x0227
        L_0x0203:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.icing.zzgs.zzk(r9, r5)
            int r3 = com.google.android.gms.internal.icing.zzeb.zzk(r3)
            goto L_0x0227
        L_0x020e:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.icing.zzgs.zzm(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0227
        L_0x0219:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.icing.zzgs.zzn(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.icing.zzeb.zzk(r3)
        L_0x0227:
            int r2 = r2 + r3
        L_0x0228:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x022c:
            int r2 = r2 * 53
            com.google.android.gms.internal.icing.zzgm<?, ?> r0 = r8.zzmo
            java.lang.Object r0 = r0.zzp(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.zzmp
            if (r0 == 0) goto L_0x024a
            int r2 = r2 * 53
            com.google.android.gms.internal.icing.zzdn<?> r0 = r8.zzmq
            com.google.android.gms.internal.icing.zzds r9 = r0.zzd(r9)
            int r9 = r9.hashCode()
            int r2 = r2 + r9
        L_0x024a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzfl.hashCode(java.lang.Object):int");
    }

    public final void zzc(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzmt.length; i += 3) {
                int zzag = zzag(i);
                long j = (long) (1048575 & zzag);
                int i2 = this.zzmt[i];
                switch ((zzag & 267386880) >>> 20) {
                    case 0:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzn(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 1:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzm(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 2:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 3:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 4:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzj(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 5:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 6:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzj(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 7:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzl(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 8:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzo(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 9:
                        zza(t, t2, i);
                        break;
                    case 10:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzo(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 11:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzj(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 12:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzj(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 13:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzj(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 14:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 15:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzj(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 16:
                        if (!zza(t2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzk(t2, j));
                            zzb(t, i);
                            break;
                        }
                    case 17:
                        zza(t, t2, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zzne.zza(t, t2, j);
                        break;
                    case 50:
                        zzfw.zza(this.zznf, t, t2, j);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (!zza(t2, i2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzo(t2, j));
                            zzb(t, i2, i);
                            break;
                        }
                    case 60:
                        zzb(t, t2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!zza(t2, i2, i)) {
                            break;
                        } else {
                            zzgs.zza((Object) t, j, zzgs.zzo(t2, j));
                            zzb(t, i2, i);
                            break;
                        }
                    case 68:
                        zzb(t, t2, i);
                        break;
                }
            }
            zzfw.zza(this.zzmo, t, t2);
            if (this.zzmp) {
                zzfw.zza(this.zzmq, t, t2);
                return;
            }
            return;
        }
        throw null;
    }

    private final void zza(T t, T t2, int i) {
        long zzag = (long) (zzag(i) & 1048575);
        if (zza(t2, i)) {
            Object zzo = zzgs.zzo(t, zzag);
            Object zzo2 = zzgs.zzo(t2, zzag);
            if (zzo != null && zzo2 != null) {
                zzgs.zza((Object) t, zzag, zzeb.zza(zzo, zzo2));
                zzb(t, i);
            } else if (zzo2 != null) {
                zzgs.zza((Object) t, zzag, zzo2);
                zzb(t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzag = zzag(i);
        int i2 = this.zzmt[i];
        long j = (long) (zzag & 1048575);
        if (zza(t2, i2, i)) {
            Object zzo = zzgs.zzo(t, j);
            Object zzo2 = zzgs.zzo(t2, j);
            if (zzo != null && zzo2 != null) {
                zzgs.zza((Object) t, j, zzeb.zza(zzo, zzo2));
                zzb(t, i2, i);
            } else if (zzo2 != null) {
                zzgs.zza((Object) t, j, zzo2);
                zzb(t, i2, i);
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:397:0x0831, code lost:
        r8 = (r8 + r9) + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:414:0x08fc, code lost:
        r6 = r6 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:419:0x090d, code lost:
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:435:0x0955, code lost:
        r6 = r6 + r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:472:0x0a06, code lost:
        r6 = r6 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:481:0x0a2a, code lost:
        r5 = r5 + 3;
        r9 = r13;
        r8 = 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzn(T r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            boolean r2 = r0.zzmy
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = 0
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r8 = 1
            r9 = 0
            r11 = 0
            if (r2 == 0) goto L_0x04f2
            sun.misc.Unsafe r2 = zzms
            r12 = 0
            r13 = 0
        L_0x0016:
            int[] r14 = r0.zzmt
            int r14 = r14.length
            if (r12 >= r14) goto L_0x04ea
            int r14 = r0.zzag(r12)
            r15 = r14 & r3
            int r15 = r15 >>> 20
            int[] r3 = r0.zzmt
            r3 = r3[r12]
            r14 = r14 & r7
            long r5 = (long) r14
            com.google.android.gms.internal.icing.zzdt r14 = com.google.android.gms.internal.icing.zzdt.DOUBLE_LIST_PACKED
            int r14 = r14.id()
            if (r15 < r14) goto L_0x0041
            com.google.android.gms.internal.icing.zzdt r14 = com.google.android.gms.internal.icing.zzdt.SINT64_LIST_PACKED
            int r14 = r14.id()
            if (r15 > r14) goto L_0x0041
            int[] r14 = r0.zzmt
            int r17 = r12 + 2
            r14 = r14[r17]
            r14 = r14 & r7
            goto L_0x0042
        L_0x0041:
            r14 = 0
        L_0x0042:
            switch(r15) {
                case 0: goto L_0x04d6;
                case 1: goto L_0x04ca;
                case 2: goto L_0x04ba;
                case 3: goto L_0x04aa;
                case 4: goto L_0x049a;
                case 5: goto L_0x048e;
                case 6: goto L_0x0482;
                case 7: goto L_0x0476;
                case 8: goto L_0x0458;
                case 9: goto L_0x0444;
                case 10: goto L_0x0433;
                case 11: goto L_0x0424;
                case 12: goto L_0x0415;
                case 13: goto L_0x040a;
                case 14: goto L_0x03ff;
                case 15: goto L_0x03f0;
                case 16: goto L_0x03e1;
                case 17: goto L_0x03cc;
                case 18: goto L_0x03c1;
                case 19: goto L_0x03b8;
                case 20: goto L_0x03af;
                case 21: goto L_0x03a6;
                case 22: goto L_0x039d;
                case 23: goto L_0x0394;
                case 24: goto L_0x038b;
                case 25: goto L_0x0382;
                case 26: goto L_0x0379;
                case 27: goto L_0x036c;
                case 28: goto L_0x0363;
                case 29: goto L_0x035a;
                case 30: goto L_0x0350;
                case 31: goto L_0x0346;
                case 32: goto L_0x033c;
                case 33: goto L_0x0332;
                case 34: goto L_0x0328;
                case 35: goto L_0x0308;
                case 36: goto L_0x02eb;
                case 37: goto L_0x02ce;
                case 38: goto L_0x02b1;
                case 39: goto L_0x0293;
                case 40: goto L_0x0275;
                case 41: goto L_0x0257;
                case 42: goto L_0x0239;
                case 43: goto L_0x021b;
                case 44: goto L_0x01fd;
                case 45: goto L_0x01df;
                case 46: goto L_0x01c1;
                case 47: goto L_0x01a3;
                case 48: goto L_0x0185;
                case 49: goto L_0x0177;
                case 50: goto L_0x0167;
                case 51: goto L_0x0159;
                case 52: goto L_0x014d;
                case 53: goto L_0x013d;
                case 54: goto L_0x012d;
                case 55: goto L_0x011d;
                case 56: goto L_0x0111;
                case 57: goto L_0x0105;
                case 58: goto L_0x00f9;
                case 59: goto L_0x00db;
                case 60: goto L_0x00c7;
                case 61: goto L_0x00b5;
                case 62: goto L_0x00a5;
                case 63: goto L_0x0095;
                case 64: goto L_0x0089;
                case 65: goto L_0x007d;
                case 66: goto L_0x006d;
                case 67: goto L_0x005d;
                case 68: goto L_0x0047;
                default: goto L_0x0045;
            }
        L_0x0045:
            goto L_0x04e4
        L_0x0047:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzgs.zzo(r1, r5)
            com.google.android.gms.internal.icing.zzfh r5 = (com.google.android.gms.internal.icing.zzfh) r5
            com.google.android.gms.internal.icing.zzfu r6 = r0.zzae(r12)
            int r3 = com.google.android.gms.internal.icing.zzdk.zzc(r3, r5, r6)
            goto L_0x03c9
        L_0x005d:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzdk.zzf((int) r3, (long) r5)
            goto L_0x03c9
        L_0x006d:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = zzg(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzdk.zzi(r3, r5)
            goto L_0x03c9
        L_0x007d:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.icing.zzdk.zzh((int) r3, (long) r9)
            goto L_0x03c9
        L_0x0089:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.icing.zzdk.zzk(r3, r11)
            goto L_0x03c9
        L_0x0095:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = zzg(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzdk.zzl(r3, r5)
            goto L_0x03c9
        L_0x00a5:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = zzg(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzdk.zzh((int) r3, (int) r5)
            goto L_0x03c9
        L_0x00b5:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzgs.zzo(r1, r5)
            com.google.android.gms.internal.icing.zzct r5 = (com.google.android.gms.internal.icing.zzct) r5
            int r3 = com.google.android.gms.internal.icing.zzdk.zzc((int) r3, (com.google.android.gms.internal.icing.zzct) r5)
            goto L_0x03c9
        L_0x00c7:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzgs.zzo(r1, r5)
            com.google.android.gms.internal.icing.zzfu r6 = r0.zzae(r12)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzc((int) r3, (java.lang.Object) r5, (com.google.android.gms.internal.icing.zzfu) r6)
            goto L_0x03c9
        L_0x00db:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzgs.zzo(r1, r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.icing.zzct
            if (r6 == 0) goto L_0x00f1
            com.google.android.gms.internal.icing.zzct r5 = (com.google.android.gms.internal.icing.zzct) r5
            int r3 = com.google.android.gms.internal.icing.zzdk.zzc((int) r3, (com.google.android.gms.internal.icing.zzct) r5)
            goto L_0x03c9
        L_0x00f1:
            java.lang.String r5 = (java.lang.String) r5
            int r3 = com.google.android.gms.internal.icing.zzdk.zzb((int) r3, (java.lang.String) r5)
            goto L_0x03c9
        L_0x00f9:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.icing.zzdk.zzb((int) r3, (boolean) r8)
            goto L_0x03c9
        L_0x0105:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.icing.zzdk.zzj(r3, r11)
            goto L_0x03c9
        L_0x0111:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.icing.zzdk.zzg((int) r3, (long) r9)
            goto L_0x03c9
        L_0x011d:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = zzg(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzdk.zzg((int) r3, (int) r5)
            goto L_0x03c9
        L_0x012d:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzdk.zze((int) r3, (long) r5)
            goto L_0x03c9
        L_0x013d:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzdk.zzd((int) r3, (long) r5)
            goto L_0x03c9
        L_0x014d:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.icing.zzdk.zzb((int) r3, (float) r4)
            goto L_0x03c9
        L_0x0159:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x04e4
            r5 = 0
            int r3 = com.google.android.gms.internal.icing.zzdk.zzb((int) r3, (double) r5)
            goto L_0x03c9
        L_0x0167:
            com.google.android.gms.internal.icing.zzfa r14 = r0.zznf
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzgs.zzo(r1, r5)
            java.lang.Object r6 = r0.zzaf(r12)
            int r3 = r14.zzb(r3, r5, r6)
            goto L_0x03c9
        L_0x0177:
            java.util.List r5 = zzd(r1, r5)
            com.google.android.gms.internal.icing.zzfu r6 = r0.zzae(r12)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzd(r3, r5, r6)
            goto L_0x03c9
        L_0x0185:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.icing.zzfw.zzc(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzmz
            if (r6 == 0) goto L_0x0199
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x0199:
            int r3 = com.google.android.gms.internal.icing.zzdk.zzs(r3)
            int r6 = com.google.android.gms.internal.icing.zzdk.zzu(r5)
            goto L_0x0324
        L_0x01a3:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.icing.zzfw.zzg(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzmz
            if (r6 == 0) goto L_0x01b7
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x01b7:
            int r3 = com.google.android.gms.internal.icing.zzdk.zzs(r3)
            int r6 = com.google.android.gms.internal.icing.zzdk.zzu(r5)
            goto L_0x0324
        L_0x01c1:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.icing.zzfw.zzi(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzmz
            if (r6 == 0) goto L_0x01d5
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x01d5:
            int r3 = com.google.android.gms.internal.icing.zzdk.zzs(r3)
            int r6 = com.google.android.gms.internal.icing.zzdk.zzu(r5)
            goto L_0x0324
        L_0x01df:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.icing.zzfw.zzh((java.util.List<?>) r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzmz
            if (r6 == 0) goto L_0x01f3
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x01f3:
            int r3 = com.google.android.gms.internal.icing.zzdk.zzs(r3)
            int r6 = com.google.android.gms.internal.icing.zzdk.zzu(r5)
            goto L_0x0324
        L_0x01fd:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.icing.zzfw.zzd(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzmz
            if (r6 == 0) goto L_0x0211
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x0211:
            int r3 = com.google.android.gms.internal.icing.zzdk.zzs(r3)
            int r6 = com.google.android.gms.internal.icing.zzdk.zzu(r5)
            goto L_0x0324
        L_0x021b:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.icing.zzfw.zzf((java.util.List<java.lang.Integer>) r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzmz
            if (r6 == 0) goto L_0x022f
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x022f:
            int r3 = com.google.android.gms.internal.icing.zzdk.zzs(r3)
            int r6 = com.google.android.gms.internal.icing.zzdk.zzu(r5)
            goto L_0x0324
        L_0x0239:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.icing.zzfw.zzj(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzmz
            if (r6 == 0) goto L_0x024d
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x024d:
            int r3 = com.google.android.gms.internal.icing.zzdk.zzs(r3)
            int r6 = com.google.android.gms.internal.icing.zzdk.zzu(r5)
            goto L_0x0324
        L_0x0257:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.icing.zzfw.zzh((java.util.List<?>) r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzmz
            if (r6 == 0) goto L_0x026b
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x026b:
            int r3 = com.google.android.gms.internal.icing.zzdk.zzs(r3)
            int r6 = com.google.android.gms.internal.icing.zzdk.zzu(r5)
            goto L_0x0324
        L_0x0275:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.icing.zzfw.zzi(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzmz
            if (r6 == 0) goto L_0x0289
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x0289:
            int r3 = com.google.android.gms.internal.icing.zzdk.zzs(r3)
            int r6 = com.google.android.gms.internal.icing.zzdk.zzu(r5)
            goto L_0x0324
        L_0x0293:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.icing.zzfw.zze(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzmz
            if (r6 == 0) goto L_0x02a7
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x02a7:
            int r3 = com.google.android.gms.internal.icing.zzdk.zzs(r3)
            int r6 = com.google.android.gms.internal.icing.zzdk.zzu(r5)
            goto L_0x0324
        L_0x02b1:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.icing.zzfw.zzb(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzmz
            if (r6 == 0) goto L_0x02c5
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x02c5:
            int r3 = com.google.android.gms.internal.icing.zzdk.zzs(r3)
            int r6 = com.google.android.gms.internal.icing.zzdk.zzu(r5)
            goto L_0x0324
        L_0x02ce:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.icing.zzfw.zza(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzmz
            if (r6 == 0) goto L_0x02e2
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x02e2:
            int r3 = com.google.android.gms.internal.icing.zzdk.zzs(r3)
            int r6 = com.google.android.gms.internal.icing.zzdk.zzu(r5)
            goto L_0x0324
        L_0x02eb:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.icing.zzfw.zzh((java.util.List<?>) r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzmz
            if (r6 == 0) goto L_0x02ff
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x02ff:
            int r3 = com.google.android.gms.internal.icing.zzdk.zzs(r3)
            int r6 = com.google.android.gms.internal.icing.zzdk.zzu(r5)
            goto L_0x0324
        L_0x0308:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.icing.zzfw.zzi(r5)
            if (r5 <= 0) goto L_0x04e4
            boolean r6 = r0.zzmz
            if (r6 == 0) goto L_0x031c
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x031c:
            int r3 = com.google.android.gms.internal.icing.zzdk.zzs(r3)
            int r6 = com.google.android.gms.internal.icing.zzdk.zzu(r5)
        L_0x0324:
            int r3 = r3 + r6
            int r3 = r3 + r5
            goto L_0x03c9
        L_0x0328:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzq(r3, r5, r11)
            goto L_0x03c9
        L_0x0332:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzu(r3, r5, r11)
            goto L_0x03c9
        L_0x033c:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzw(r3, r5, r11)
            goto L_0x03c9
        L_0x0346:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzv(r3, r5, r11)
            goto L_0x03c9
        L_0x0350:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzr(r3, r5, r11)
            goto L_0x03c9
        L_0x035a:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzt(r3, r5, r11)
            goto L_0x03c9
        L_0x0363:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzd((int) r3, (java.util.List<com.google.android.gms.internal.icing.zzct>) r5)
            goto L_0x03c9
        L_0x036c:
            java.util.List r5 = zzd(r1, r5)
            com.google.android.gms.internal.icing.zzfu r6 = r0.zzae(r12)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzc((int) r3, (java.util.List<?>) r5, (com.google.android.gms.internal.icing.zzfu) r6)
            goto L_0x03c9
        L_0x0379:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzc(r3, r5)
            goto L_0x03c9
        L_0x0382:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzx(r3, r5, r11)
            goto L_0x03c9
        L_0x038b:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzv(r3, r5, r11)
            goto L_0x03c9
        L_0x0394:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzw(r3, r5, r11)
            goto L_0x03c9
        L_0x039d:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzs(r3, r5, r11)
            goto L_0x03c9
        L_0x03a6:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzp(r3, r5, r11)
            goto L_0x03c9
        L_0x03af:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzo(r3, r5, r11)
            goto L_0x03c9
        L_0x03b8:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzv(r3, r5, r11)
            goto L_0x03c9
        L_0x03c1:
            java.util.List r5 = zzd(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzw(r3, r5, r11)
        L_0x03c9:
            int r13 = r13 + r3
            goto L_0x04e4
        L_0x03cc:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzgs.zzo(r1, r5)
            com.google.android.gms.internal.icing.zzfh r5 = (com.google.android.gms.internal.icing.zzfh) r5
            com.google.android.gms.internal.icing.zzfu r6 = r0.zzae(r12)
            int r3 = com.google.android.gms.internal.icing.zzdk.zzc(r3, r5, r6)
            goto L_0x03c9
        L_0x03e1:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = com.google.android.gms.internal.icing.zzgs.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzdk.zzf((int) r3, (long) r5)
            goto L_0x03c9
        L_0x03f0:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = com.google.android.gms.internal.icing.zzgs.zzj(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzdk.zzi(r3, r5)
            goto L_0x03c9
        L_0x03ff:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.icing.zzdk.zzh((int) r3, (long) r9)
            goto L_0x03c9
        L_0x040a:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.icing.zzdk.zzk(r3, r11)
            goto L_0x03c9
        L_0x0415:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = com.google.android.gms.internal.icing.zzgs.zzj(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzdk.zzl(r3, r5)
            goto L_0x03c9
        L_0x0424:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = com.google.android.gms.internal.icing.zzgs.zzj(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzdk.zzh((int) r3, (int) r5)
            goto L_0x03c9
        L_0x0433:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzgs.zzo(r1, r5)
            com.google.android.gms.internal.icing.zzct r5 = (com.google.android.gms.internal.icing.zzct) r5
            int r3 = com.google.android.gms.internal.icing.zzdk.zzc((int) r3, (com.google.android.gms.internal.icing.zzct) r5)
            goto L_0x03c9
        L_0x0444:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzgs.zzo(r1, r5)
            com.google.android.gms.internal.icing.zzfu r6 = r0.zzae(r12)
            int r3 = com.google.android.gms.internal.icing.zzfw.zzc((int) r3, (java.lang.Object) r5, (com.google.android.gms.internal.icing.zzfu) r6)
            goto L_0x03c9
        L_0x0458:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x04e4
            java.lang.Object r5 = com.google.android.gms.internal.icing.zzgs.zzo(r1, r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.icing.zzct
            if (r6 == 0) goto L_0x046e
            com.google.android.gms.internal.icing.zzct r5 = (com.google.android.gms.internal.icing.zzct) r5
            int r3 = com.google.android.gms.internal.icing.zzdk.zzc((int) r3, (com.google.android.gms.internal.icing.zzct) r5)
            goto L_0x03c9
        L_0x046e:
            java.lang.String r5 = (java.lang.String) r5
            int r3 = com.google.android.gms.internal.icing.zzdk.zzb((int) r3, (java.lang.String) r5)
            goto L_0x03c9
        L_0x0476:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.icing.zzdk.zzb((int) r3, (boolean) r8)
            goto L_0x03c9
        L_0x0482:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.icing.zzdk.zzj(r3, r11)
            goto L_0x03c9
        L_0x048e:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.icing.zzdk.zzg((int) r3, (long) r9)
            goto L_0x03c9
        L_0x049a:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x04e4
            int r5 = com.google.android.gms.internal.icing.zzgs.zzj(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzdk.zzg((int) r3, (int) r5)
            goto L_0x03c9
        L_0x04aa:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = com.google.android.gms.internal.icing.zzgs.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzdk.zze((int) r3, (long) r5)
            goto L_0x03c9
        L_0x04ba:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x04e4
            long r5 = com.google.android.gms.internal.icing.zzgs.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.icing.zzdk.zzd((int) r3, (long) r5)
            goto L_0x03c9
        L_0x04ca:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x04e4
            int r3 = com.google.android.gms.internal.icing.zzdk.zzb((int) r3, (float) r4)
            goto L_0x03c9
        L_0x04d6:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x04e4
            r5 = 0
            int r3 = com.google.android.gms.internal.icing.zzdk.zzb((int) r3, (double) r5)
            goto L_0x03c9
        L_0x04e4:
            int r12 = r12 + 3
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            goto L_0x0016
        L_0x04ea:
            com.google.android.gms.internal.icing.zzgm<?, ?> r2 = r0.zzmo
            int r1 = zza(r2, r1)
            int r13 = r13 + r1
            return r13
        L_0x04f2:
            sun.misc.Unsafe r2 = zzms
            r3 = -1
            r5 = 0
            r6 = 0
            r12 = 0
        L_0x04f8:
            int[] r13 = r0.zzmt
            int r13 = r13.length
            if (r5 >= r13) goto L_0x0a32
            int r13 = r0.zzag(r5)
            int[] r14 = r0.zzmt
            r15 = r14[r5]
            r16 = 267386880(0xff00000, float:2.3665827E-29)
            r17 = r13 & r16
            int r4 = r17 >>> 20
            r11 = 17
            if (r4 > r11) goto L_0x0523
            int r11 = r5 + 2
            r11 = r14[r11]
            r14 = r11 & r7
            int r18 = r11 >>> 20
            int r18 = r8 << r18
            if (r14 == r3) goto L_0x0521
            long r8 = (long) r14
            int r12 = r2.getInt(r1, r8)
            r3 = r14
        L_0x0521:
            r8 = r11
            goto L_0x0542
        L_0x0523:
            boolean r8 = r0.zzmz
            if (r8 == 0) goto L_0x053f
            com.google.android.gms.internal.icing.zzdt r8 = com.google.android.gms.internal.icing.zzdt.DOUBLE_LIST_PACKED
            int r8 = r8.id()
            if (r4 < r8) goto L_0x053f
            com.google.android.gms.internal.icing.zzdt r8 = com.google.android.gms.internal.icing.zzdt.SINT64_LIST_PACKED
            int r8 = r8.id()
            if (r4 > r8) goto L_0x053f
            int[] r8 = r0.zzmt
            int r9 = r5 + 2
            r8 = r8[r9]
            r8 = r8 & r7
            goto L_0x0540
        L_0x053f:
            r8 = 0
        L_0x0540:
            r18 = 0
        L_0x0542:
            r9 = r13 & r7
            long r9 = (long) r9
            switch(r4) {
                case 0: goto L_0x0a1a;
                case 1: goto L_0x0a09;
                case 2: goto L_0x09f6;
                case 3: goto L_0x09e5;
                case 4: goto L_0x09d4;
                case 5: goto L_0x09c7;
                case 6: goto L_0x09ba;
                case 7: goto L_0x09ae;
                case 8: goto L_0x0992;
                case 9: goto L_0x0980;
                case 10: goto L_0x0971;
                case 11: goto L_0x0964;
                case 12: goto L_0x0957;
                case 13: goto L_0x094c;
                case 14: goto L_0x0941;
                case 15: goto L_0x0934;
                case 16: goto L_0x0927;
                case 17: goto L_0x0914;
                case 18: goto L_0x08ff;
                case 19: goto L_0x08f1;
                case 20: goto L_0x08e5;
                case 21: goto L_0x08d9;
                case 22: goto L_0x08cd;
                case 23: goto L_0x08c1;
                case 24: goto L_0x08b5;
                case 25: goto L_0x08a9;
                case 26: goto L_0x089e;
                case 27: goto L_0x088f;
                case 28: goto L_0x0883;
                case 29: goto L_0x0876;
                case 30: goto L_0x0869;
                case 31: goto L_0x085c;
                case 32: goto L_0x084f;
                case 33: goto L_0x0842;
                case 34: goto L_0x0835;
                case 35: goto L_0x0815;
                case 36: goto L_0x07f8;
                case 37: goto L_0x07db;
                case 38: goto L_0x07be;
                case 39: goto L_0x07a0;
                case 40: goto L_0x0782;
                case 41: goto L_0x0764;
                case 42: goto L_0x0746;
                case 43: goto L_0x0728;
                case 44: goto L_0x070a;
                case 45: goto L_0x06ec;
                case 46: goto L_0x06ce;
                case 47: goto L_0x06b0;
                case 48: goto L_0x0692;
                case 49: goto L_0x0682;
                case 50: goto L_0x0672;
                case 51: goto L_0x0664;
                case 52: goto L_0x0657;
                case 53: goto L_0x0647;
                case 54: goto L_0x0637;
                case 55: goto L_0x0627;
                case 56: goto L_0x0619;
                case 57: goto L_0x060c;
                case 58: goto L_0x05ff;
                case 59: goto L_0x05e1;
                case 60: goto L_0x05cd;
                case 61: goto L_0x05bb;
                case 62: goto L_0x05ab;
                case 63: goto L_0x059b;
                case 64: goto L_0x058e;
                case 65: goto L_0x0580;
                case 66: goto L_0x0570;
                case 67: goto L_0x0560;
                case 68: goto L_0x054a;
                default: goto L_0x0548;
            }
        L_0x0548:
            goto L_0x090b
        L_0x054a:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            com.google.android.gms.internal.icing.zzfh r4 = (com.google.android.gms.internal.icing.zzfh) r4
            com.google.android.gms.internal.icing.zzfu r8 = r0.zzae(r5)
            int r4 = com.google.android.gms.internal.icing.zzdk.zzc(r15, r4, r8)
            goto L_0x090a
        L_0x0560:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            long r8 = zzh(r1, r9)
            int r4 = com.google.android.gms.internal.icing.zzdk.zzf((int) r15, (long) r8)
            goto L_0x090a
        L_0x0570:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            int r4 = zzg(r1, r9)
            int r4 = com.google.android.gms.internal.icing.zzdk.zzi(r15, r4)
            goto L_0x090a
        L_0x0580:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            r8 = 0
            int r4 = com.google.android.gms.internal.icing.zzdk.zzh((int) r15, (long) r8)
            goto L_0x090a
        L_0x058e:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            r4 = 0
            int r8 = com.google.android.gms.internal.icing.zzdk.zzk(r15, r4)
            goto L_0x0955
        L_0x059b:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            int r4 = zzg(r1, r9)
            int r4 = com.google.android.gms.internal.icing.zzdk.zzl(r15, r4)
            goto L_0x090a
        L_0x05ab:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            int r4 = zzg(r1, r9)
            int r4 = com.google.android.gms.internal.icing.zzdk.zzh((int) r15, (int) r4)
            goto L_0x090a
        L_0x05bb:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            com.google.android.gms.internal.icing.zzct r4 = (com.google.android.gms.internal.icing.zzct) r4
            int r4 = com.google.android.gms.internal.icing.zzdk.zzc((int) r15, (com.google.android.gms.internal.icing.zzct) r4)
            goto L_0x090a
        L_0x05cd:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            com.google.android.gms.internal.icing.zzfu r8 = r0.zzae(r5)
            int r4 = com.google.android.gms.internal.icing.zzfw.zzc((int) r15, (java.lang.Object) r4, (com.google.android.gms.internal.icing.zzfu) r8)
            goto L_0x090a
        L_0x05e1:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            boolean r8 = r4 instanceof com.google.android.gms.internal.icing.zzct
            if (r8 == 0) goto L_0x05f7
            com.google.android.gms.internal.icing.zzct r4 = (com.google.android.gms.internal.icing.zzct) r4
            int r4 = com.google.android.gms.internal.icing.zzdk.zzc((int) r15, (com.google.android.gms.internal.icing.zzct) r4)
            goto L_0x090a
        L_0x05f7:
            java.lang.String r4 = (java.lang.String) r4
            int r4 = com.google.android.gms.internal.icing.zzdk.zzb((int) r15, (java.lang.String) r4)
            goto L_0x090a
        L_0x05ff:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            r4 = 1
            int r8 = com.google.android.gms.internal.icing.zzdk.zzb((int) r15, (boolean) r4)
            goto L_0x0955
        L_0x060c:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            r4 = 0
            int r8 = com.google.android.gms.internal.icing.zzdk.zzj(r15, r4)
            goto L_0x0955
        L_0x0619:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            r8 = 0
            int r4 = com.google.android.gms.internal.icing.zzdk.zzg((int) r15, (long) r8)
            goto L_0x090a
        L_0x0627:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            int r4 = zzg(r1, r9)
            int r4 = com.google.android.gms.internal.icing.zzdk.zzg((int) r15, (int) r4)
            goto L_0x090a
        L_0x0637:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            long r8 = zzh(r1, r9)
            int r4 = com.google.android.gms.internal.icing.zzdk.zze((int) r15, (long) r8)
            goto L_0x090a
        L_0x0647:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            long r8 = zzh(r1, r9)
            int r4 = com.google.android.gms.internal.icing.zzdk.zzd((int) r15, (long) r8)
            goto L_0x090a
        L_0x0657:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            r4 = 0
            int r8 = com.google.android.gms.internal.icing.zzdk.zzb((int) r15, (float) r4)
            goto L_0x0955
        L_0x0664:
            boolean r4 = r0.zza(r1, (int) r15, (int) r5)
            if (r4 == 0) goto L_0x090b
            r8 = 0
            int r4 = com.google.android.gms.internal.icing.zzdk.zzb((int) r15, (double) r8)
            goto L_0x090a
        L_0x0672:
            com.google.android.gms.internal.icing.zzfa r4 = r0.zznf
            java.lang.Object r8 = r2.getObject(r1, r9)
            java.lang.Object r9 = r0.zzaf(r5)
            int r4 = r4.zzb(r15, r8, r9)
            goto L_0x090a
        L_0x0682:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzfu r8 = r0.zzae(r5)
            int r4 = com.google.android.gms.internal.icing.zzfw.zzd(r15, r4, r8)
            goto L_0x090a
        L_0x0692:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzc(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzmz
            if (r9 == 0) goto L_0x06a6
            long r8 = (long) r8
            r2.putInt(r1, r8, r4)
        L_0x06a6:
            int r8 = com.google.android.gms.internal.icing.zzdk.zzs(r15)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzu(r4)
            goto L_0x0831
        L_0x06b0:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzg(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzmz
            if (r9 == 0) goto L_0x06c4
            long r8 = (long) r8
            r2.putInt(r1, r8, r4)
        L_0x06c4:
            int r8 = com.google.android.gms.internal.icing.zzdk.zzs(r15)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzu(r4)
            goto L_0x0831
        L_0x06ce:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzi(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzmz
            if (r9 == 0) goto L_0x06e2
            long r8 = (long) r8
            r2.putInt(r1, r8, r4)
        L_0x06e2:
            int r8 = com.google.android.gms.internal.icing.zzdk.zzs(r15)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzu(r4)
            goto L_0x0831
        L_0x06ec:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzh((java.util.List<?>) r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzmz
            if (r9 == 0) goto L_0x0700
            long r8 = (long) r8
            r2.putInt(r1, r8, r4)
        L_0x0700:
            int r8 = com.google.android.gms.internal.icing.zzdk.zzs(r15)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzu(r4)
            goto L_0x0831
        L_0x070a:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzd(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzmz
            if (r9 == 0) goto L_0x071e
            long r8 = (long) r8
            r2.putInt(r1, r8, r4)
        L_0x071e:
            int r8 = com.google.android.gms.internal.icing.zzdk.zzs(r15)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzu(r4)
            goto L_0x0831
        L_0x0728:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzf((java.util.List<java.lang.Integer>) r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzmz
            if (r9 == 0) goto L_0x073c
            long r8 = (long) r8
            r2.putInt(r1, r8, r4)
        L_0x073c:
            int r8 = com.google.android.gms.internal.icing.zzdk.zzs(r15)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzu(r4)
            goto L_0x0831
        L_0x0746:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzj(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzmz
            if (r9 == 0) goto L_0x075a
            long r8 = (long) r8
            r2.putInt(r1, r8, r4)
        L_0x075a:
            int r8 = com.google.android.gms.internal.icing.zzdk.zzs(r15)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzu(r4)
            goto L_0x0831
        L_0x0764:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzh((java.util.List<?>) r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzmz
            if (r9 == 0) goto L_0x0778
            long r8 = (long) r8
            r2.putInt(r1, r8, r4)
        L_0x0778:
            int r8 = com.google.android.gms.internal.icing.zzdk.zzs(r15)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzu(r4)
            goto L_0x0831
        L_0x0782:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzi(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzmz
            if (r9 == 0) goto L_0x0796
            long r8 = (long) r8
            r2.putInt(r1, r8, r4)
        L_0x0796:
            int r8 = com.google.android.gms.internal.icing.zzdk.zzs(r15)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzu(r4)
            goto L_0x0831
        L_0x07a0:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zze(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzmz
            if (r9 == 0) goto L_0x07b4
            long r8 = (long) r8
            r2.putInt(r1, r8, r4)
        L_0x07b4:
            int r8 = com.google.android.gms.internal.icing.zzdk.zzs(r15)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzu(r4)
            goto L_0x0831
        L_0x07be:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzb(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzmz
            if (r9 == 0) goto L_0x07d2
            long r8 = (long) r8
            r2.putInt(r1, r8, r4)
        L_0x07d2:
            int r8 = com.google.android.gms.internal.icing.zzdk.zzs(r15)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzu(r4)
            goto L_0x0831
        L_0x07db:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zza(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzmz
            if (r9 == 0) goto L_0x07ef
            long r8 = (long) r8
            r2.putInt(r1, r8, r4)
        L_0x07ef:
            int r8 = com.google.android.gms.internal.icing.zzdk.zzs(r15)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzu(r4)
            goto L_0x0831
        L_0x07f8:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzh((java.util.List<?>) r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzmz
            if (r9 == 0) goto L_0x080c
            long r8 = (long) r8
            r2.putInt(r1, r8, r4)
        L_0x080c:
            int r8 = com.google.android.gms.internal.icing.zzdk.zzs(r15)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzu(r4)
            goto L_0x0831
        L_0x0815:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzi(r4)
            if (r4 <= 0) goto L_0x090b
            boolean r9 = r0.zzmz
            if (r9 == 0) goto L_0x0829
            long r8 = (long) r8
            r2.putInt(r1, r8, r4)
        L_0x0829:
            int r8 = com.google.android.gms.internal.icing.zzdk.zzs(r15)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzu(r4)
        L_0x0831:
            int r8 = r8 + r9
            int r8 = r8 + r4
            goto L_0x0955
        L_0x0835:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            r8 = 0
            int r4 = com.google.android.gms.internal.icing.zzfw.zzq(r15, r4, r8)
            goto L_0x08fc
        L_0x0842:
            r8 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzu(r15, r4, r8)
            goto L_0x08fc
        L_0x084f:
            r8 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzw(r15, r4, r8)
            goto L_0x08fc
        L_0x085c:
            r8 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzv(r15, r4, r8)
            goto L_0x08fc
        L_0x0869:
            r8 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzr(r15, r4, r8)
            goto L_0x08fc
        L_0x0876:
            r8 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzt(r15, r4, r8)
            goto L_0x090a
        L_0x0883:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzd((int) r15, (java.util.List<com.google.android.gms.internal.icing.zzct>) r4)
            goto L_0x090a
        L_0x088f:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.icing.zzfu r8 = r0.zzae(r5)
            int r4 = com.google.android.gms.internal.icing.zzfw.zzc((int) r15, (java.util.List<?>) r4, (com.google.android.gms.internal.icing.zzfu) r8)
            goto L_0x090a
        L_0x089e:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzc(r15, r4)
            goto L_0x090a
        L_0x08a9:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            r8 = 0
            int r4 = com.google.android.gms.internal.icing.zzfw.zzx(r15, r4, r8)
            goto L_0x08fc
        L_0x08b5:
            r8 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzv(r15, r4, r8)
            goto L_0x08fc
        L_0x08c1:
            r8 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzw(r15, r4, r8)
            goto L_0x08fc
        L_0x08cd:
            r8 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzs(r15, r4, r8)
            goto L_0x08fc
        L_0x08d9:
            r8 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzp(r15, r4, r8)
            goto L_0x08fc
        L_0x08e5:
            r8 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzo(r15, r4, r8)
            goto L_0x08fc
        L_0x08f1:
            r8 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzv(r15, r4, r8)
        L_0x08fc:
            int r6 = r6 + r4
            r4 = 1
            goto L_0x090d
        L_0x08ff:
            r8 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.icing.zzfw.zzw(r15, r4, r8)
        L_0x090a:
            int r6 = r6 + r4
        L_0x090b:
            r4 = 1
        L_0x090c:
            r8 = 0
        L_0x090d:
            r9 = 0
            r10 = 0
            r13 = 0
            goto L_0x0a2a
        L_0x0914:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            com.google.android.gms.internal.icing.zzfh r4 = (com.google.android.gms.internal.icing.zzfh) r4
            com.google.android.gms.internal.icing.zzfu r8 = r0.zzae(r5)
            int r4 = com.google.android.gms.internal.icing.zzdk.zzc(r15, r4, r8)
            goto L_0x090a
        L_0x0927:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            long r8 = r2.getLong(r1, r9)
            int r4 = com.google.android.gms.internal.icing.zzdk.zzf((int) r15, (long) r8)
            goto L_0x090a
        L_0x0934:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            int r4 = r2.getInt(r1, r9)
            int r4 = com.google.android.gms.internal.icing.zzdk.zzi(r15, r4)
            goto L_0x090a
        L_0x0941:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            r8 = 0
            int r4 = com.google.android.gms.internal.icing.zzdk.zzh((int) r15, (long) r8)
            goto L_0x090a
        L_0x094c:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            r4 = 0
            int r8 = com.google.android.gms.internal.icing.zzdk.zzk(r15, r4)
        L_0x0955:
            int r6 = r6 + r8
            goto L_0x090b
        L_0x0957:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            int r4 = r2.getInt(r1, r9)
            int r4 = com.google.android.gms.internal.icing.zzdk.zzl(r15, r4)
            goto L_0x090a
        L_0x0964:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            int r4 = r2.getInt(r1, r9)
            int r4 = com.google.android.gms.internal.icing.zzdk.zzh((int) r15, (int) r4)
            goto L_0x090a
        L_0x0971:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            com.google.android.gms.internal.icing.zzct r4 = (com.google.android.gms.internal.icing.zzct) r4
            int r4 = com.google.android.gms.internal.icing.zzdk.zzc((int) r15, (com.google.android.gms.internal.icing.zzct) r4)
            goto L_0x090a
        L_0x0980:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            com.google.android.gms.internal.icing.zzfu r8 = r0.zzae(r5)
            int r4 = com.google.android.gms.internal.icing.zzfw.zzc((int) r15, (java.lang.Object) r4, (com.google.android.gms.internal.icing.zzfu) r8)
            goto L_0x090a
        L_0x0992:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            java.lang.Object r4 = r2.getObject(r1, r9)
            boolean r8 = r4 instanceof com.google.android.gms.internal.icing.zzct
            if (r8 == 0) goto L_0x09a6
            com.google.android.gms.internal.icing.zzct r4 = (com.google.android.gms.internal.icing.zzct) r4
            int r4 = com.google.android.gms.internal.icing.zzdk.zzc((int) r15, (com.google.android.gms.internal.icing.zzct) r4)
            goto L_0x090a
        L_0x09a6:
            java.lang.String r4 = (java.lang.String) r4
            int r4 = com.google.android.gms.internal.icing.zzdk.zzb((int) r15, (java.lang.String) r4)
            goto L_0x090a
        L_0x09ae:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x090b
            r4 = 1
            int r8 = com.google.android.gms.internal.icing.zzdk.zzb((int) r15, (boolean) r4)
            int r6 = r6 + r8
            goto L_0x090c
        L_0x09ba:
            r4 = 1
            r8 = r12 & r18
            if (r8 == 0) goto L_0x090c
            r8 = 0
            int r9 = com.google.android.gms.internal.icing.zzdk.zzj(r15, r8)
            int r6 = r6 + r9
            goto L_0x090d
        L_0x09c7:
            r4 = 1
            r8 = 0
            r9 = r12 & r18
            r13 = 0
            if (r9 == 0) goto L_0x0a07
            int r9 = com.google.android.gms.internal.icing.zzdk.zzg((int) r15, (long) r13)
            goto L_0x0a06
        L_0x09d4:
            r4 = 1
            r8 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x0a07
            int r9 = r2.getInt(r1, r9)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzg((int) r15, (int) r9)
            goto L_0x0a06
        L_0x09e5:
            r4 = 1
            r8 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x0a07
            long r9 = r2.getLong(r1, r9)
            int r9 = com.google.android.gms.internal.icing.zzdk.zze((int) r15, (long) r9)
            goto L_0x0a06
        L_0x09f6:
            r4 = 1
            r8 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x0a07
            long r9 = r2.getLong(r1, r9)
            int r9 = com.google.android.gms.internal.icing.zzdk.zzd((int) r15, (long) r9)
        L_0x0a06:
            int r6 = r6 + r9
        L_0x0a07:
            r9 = 0
            goto L_0x0a17
        L_0x0a09:
            r4 = 1
            r8 = 0
            r13 = 0
            r9 = r12 & r18
            if (r9 == 0) goto L_0x0a07
            r9 = 0
            int r10 = com.google.android.gms.internal.icing.zzdk.zzb((int) r15, (float) r9)
            int r6 = r6 + r10
        L_0x0a17:
            r10 = 0
            goto L_0x0a2a
        L_0x0a1a:
            r4 = 1
            r8 = 0
            r9 = 0
            r13 = 0
            r10 = r12 & r18
            if (r10 == 0) goto L_0x0a17
            r10 = 0
            int r15 = com.google.android.gms.internal.icing.zzdk.zzb((int) r15, (double) r10)
            int r6 = r6 + r15
        L_0x0a2a:
            int r5 = r5 + 3
            r9 = r13
            r4 = 0
            r8 = 1
            r11 = 0
            goto L_0x04f8
        L_0x0a32:
            r8 = 0
            com.google.android.gms.internal.icing.zzgm<?, ?> r2 = r0.zzmo
            int r2 = zza(r2, r1)
            int r6 = r6 + r2
            boolean r2 = r0.zzmp
            if (r2 == 0) goto L_0x0a8c
            com.google.android.gms.internal.icing.zzdn<?> r2 = r0.zzmq
            com.google.android.gms.internal.icing.zzds r1 = r2.zzd(r1)
            r11 = 0
        L_0x0a45:
            com.google.android.gms.internal.icing.zzfz<T, java.lang.Object> r2 = r1.zzhk
            int r2 = r2.zzdd()
            if (r11 >= r2) goto L_0x0a65
            com.google.android.gms.internal.icing.zzfz<T, java.lang.Object> r2 = r1.zzhk
            java.util.Map$Entry r2 = r2.zzaj(r11)
            java.lang.Object r3 = r2.getKey()
            com.google.android.gms.internal.icing.zzdu r3 = (com.google.android.gms.internal.icing.zzdu) r3
            java.lang.Object r2 = r2.getValue()
            int r2 = com.google.android.gms.internal.icing.zzds.zzb((com.google.android.gms.internal.icing.zzdu<?>) r3, (java.lang.Object) r2)
            int r8 = r8 + r2
            int r11 = r11 + 1
            goto L_0x0a45
        L_0x0a65:
            com.google.android.gms.internal.icing.zzfz<T, java.lang.Object> r1 = r1.zzhk
            java.lang.Iterable r1 = r1.zzde()
            java.util.Iterator r1 = r1.iterator()
        L_0x0a6f:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0a8b
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            com.google.android.gms.internal.icing.zzdu r3 = (com.google.android.gms.internal.icing.zzdu) r3
            java.lang.Object r2 = r2.getValue()
            int r2 = com.google.android.gms.internal.icing.zzds.zzb((com.google.android.gms.internal.icing.zzdu<?>) r3, (java.lang.Object) r2)
            int r8 = r8 + r2
            goto L_0x0a6f
        L_0x0a8b:
            int r6 = r6 + r8
        L_0x0a8c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzfl.zzn(java.lang.Object):int");
    }

    private static <UT, UB> int zza(zzgm<UT, UB> zzgm, T t) {
        return zzgm.zzn(zzgm.zzp(t));
    }

    private static List<?> zzd(Object obj, long j) {
        return (List) zzgs.zzo(obj, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0513  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x0552  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0a2a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r14, com.google.android.gms.internal.icing.zzhg r15) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r15.zzay()
            int r1 = com.google.android.gms.internal.icing.zzdx.zze.zzky
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x0529
            com.google.android.gms.internal.icing.zzgm<?, ?> r0 = r13.zzmo
            zza(r0, r14, (com.google.android.gms.internal.icing.zzhg) r15)
            boolean r0 = r13.zzmp
            if (r0 == 0) goto L_0x0032
            com.google.android.gms.internal.icing.zzdn<?> r0 = r13.zzmq
            com.google.android.gms.internal.icing.zzds r0 = r0.zzd(r14)
            com.google.android.gms.internal.icing.zzfz<T, java.lang.Object> r1 = r0.zzhk
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0032
            java.util.Iterator r0 = r0.descendingIterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0034
        L_0x0032:
            r0 = r3
            r1 = r0
        L_0x0034:
            int[] r7 = r13.zzmt
            int r7 = r7.length
            int r7 = r7 + -3
        L_0x0039:
            if (r7 < 0) goto L_0x0511
            int r8 = r13.zzag(r7)
            int[] r9 = r13.zzmt
            r9 = r9[r7]
        L_0x0043:
            if (r1 == 0) goto L_0x0061
            com.google.android.gms.internal.icing.zzdn<?> r10 = r13.zzmq
            int r10 = r10.zza(r1)
            if (r10 <= r9) goto L_0x0061
            com.google.android.gms.internal.icing.zzdn<?> r10 = r13.zzmq
            r10.zza(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005f
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0043
        L_0x005f:
            r1 = r3
            goto L_0x0043
        L_0x0061:
            r10 = r8 & r2
            int r10 = r10 >>> 20
            switch(r10) {
                case 0: goto L_0x04fe;
                case 1: goto L_0x04ee;
                case 2: goto L_0x04de;
                case 3: goto L_0x04ce;
                case 4: goto L_0x04be;
                case 5: goto L_0x04ae;
                case 6: goto L_0x049e;
                case 7: goto L_0x048d;
                case 8: goto L_0x047c;
                case 9: goto L_0x0467;
                case 10: goto L_0x0454;
                case 11: goto L_0x0443;
                case 12: goto L_0x0432;
                case 13: goto L_0x0421;
                case 14: goto L_0x0410;
                case 15: goto L_0x03ff;
                case 16: goto L_0x03ee;
                case 17: goto L_0x03d9;
                case 18: goto L_0x03c8;
                case 19: goto L_0x03b7;
                case 20: goto L_0x03a6;
                case 21: goto L_0x0395;
                case 22: goto L_0x0384;
                case 23: goto L_0x0373;
                case 24: goto L_0x0362;
                case 25: goto L_0x0351;
                case 26: goto L_0x0340;
                case 27: goto L_0x032b;
                case 28: goto L_0x031a;
                case 29: goto L_0x0309;
                case 30: goto L_0x02f8;
                case 31: goto L_0x02e7;
                case 32: goto L_0x02d6;
                case 33: goto L_0x02c5;
                case 34: goto L_0x02b4;
                case 35: goto L_0x02a3;
                case 36: goto L_0x0292;
                case 37: goto L_0x0281;
                case 38: goto L_0x0270;
                case 39: goto L_0x025f;
                case 40: goto L_0x024e;
                case 41: goto L_0x023d;
                case 42: goto L_0x022c;
                case 43: goto L_0x021b;
                case 44: goto L_0x020a;
                case 45: goto L_0x01f9;
                case 46: goto L_0x01e8;
                case 47: goto L_0x01d7;
                case 48: goto L_0x01c6;
                case 49: goto L_0x01b1;
                case 50: goto L_0x01a6;
                case 51: goto L_0x0195;
                case 52: goto L_0x0184;
                case 53: goto L_0x0173;
                case 54: goto L_0x0162;
                case 55: goto L_0x0151;
                case 56: goto L_0x0140;
                case 57: goto L_0x012f;
                case 58: goto L_0x011e;
                case 59: goto L_0x010d;
                case 60: goto L_0x00f8;
                case 61: goto L_0x00e5;
                case 62: goto L_0x00d4;
                case 63: goto L_0x00c3;
                case 64: goto L_0x00b2;
                case 65: goto L_0x00a1;
                case 66: goto L_0x0090;
                case 67: goto L_0x007f;
                case 68: goto L_0x006a;
                default: goto L_0x0068;
            }
        L_0x0068:
            goto L_0x050d
        L_0x006a:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzfu r10 = r13.zzae(r7)
            r15.zzb((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.icing.zzfu) r10)
            goto L_0x050d
        L_0x007f:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zzb((int) r9, (long) r10)
            goto L_0x050d
        L_0x0090:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zze(r9, r8)
            goto L_0x050d
        L_0x00a1:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x050d
        L_0x00b2:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzm(r9, r8)
            goto L_0x050d
        L_0x00c3:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzn(r9, r8)
            goto L_0x050d
        L_0x00d4:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzd(r9, r8)
            goto L_0x050d
        L_0x00e5:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzct r8 = (com.google.android.gms.internal.icing.zzct) r8
            r15.zza((int) r9, (com.google.android.gms.internal.icing.zzct) r8)
            goto L_0x050d
        L_0x00f8:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzfu r10 = r13.zzae(r7)
            r15.zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.icing.zzfu) r10)
            goto L_0x050d
        L_0x010d:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.icing.zzhg) r15)
            goto L_0x050d
        L_0x011e:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = zzi(r14, r10)
            r15.zza((int) r9, (boolean) r8)
            goto L_0x050d
        L_0x012f:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x050d
        L_0x0140:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zzc((int) r9, (long) r10)
            goto L_0x050d
        L_0x0151:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzg(r14, r10)
            r15.zzc((int) r9, (int) r8)
            goto L_0x050d
        L_0x0162:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zza((int) r9, (long) r10)
            goto L_0x050d
        L_0x0173:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzh(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x050d
        L_0x0184:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = zzf(r14, r10)
            r15.zza((int) r9, (float) r8)
            goto L_0x050d
        L_0x0195:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = zze(r14, r10)
            r15.zza((int) r9, (double) r10)
            goto L_0x050d
        L_0x01a6:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            r13.zza((com.google.android.gms.internal.icing.zzhg) r15, (int) r9, (java.lang.Object) r8, (int) r7)
            goto L_0x050d
        L_0x01b1:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfu r10 = r13.zzae(r7)
            com.google.android.gms.internal.icing.zzfw.zzb((int) r9, (java.util.List<?>) r8, (com.google.android.gms.internal.icing.zzhg) r15, (com.google.android.gms.internal.icing.zzfu) r10)
            goto L_0x050d
        L_0x01c6:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zze(r9, r8, r15, r4)
            goto L_0x050d
        L_0x01d7:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzj(r9, r8, r15, r4)
            goto L_0x050d
        L_0x01e8:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzg(r9, r8, r15, r4)
            goto L_0x050d
        L_0x01f9:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzl(r9, r8, r15, r4)
            goto L_0x050d
        L_0x020a:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzm(r9, r8, r15, r4)
            goto L_0x050d
        L_0x021b:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzi(r9, r8, r15, r4)
            goto L_0x050d
        L_0x022c:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzn(r9, r8, r15, r4)
            goto L_0x050d
        L_0x023d:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzk(r9, r8, r15, r4)
            goto L_0x050d
        L_0x024e:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzf(r9, r8, r15, r4)
            goto L_0x050d
        L_0x025f:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzh(r9, r8, r15, r4)
            goto L_0x050d
        L_0x0270:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzd(r9, r8, r15, r4)
            goto L_0x050d
        L_0x0281:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzc(r9, r8, r15, r4)
            goto L_0x050d
        L_0x0292:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzb((int) r9, (java.util.List<java.lang.Float>) r8, (com.google.android.gms.internal.icing.zzhg) r15, (boolean) r4)
            goto L_0x050d
        L_0x02a3:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zza((int) r9, (java.util.List<java.lang.Double>) r8, (com.google.android.gms.internal.icing.zzhg) r15, (boolean) r4)
            goto L_0x050d
        L_0x02b4:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zze(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02c5:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzj(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02d6:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzg(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02e7:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzl(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02f8:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzm(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0309:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzi(r9, r8, r15, r5)
            goto L_0x050d
        L_0x031a:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzb(r9, r8, r15)
            goto L_0x050d
        L_0x032b:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfu r10 = r13.zzae(r7)
            com.google.android.gms.internal.icing.zzfw.zza((int) r9, (java.util.List<?>) r8, (com.google.android.gms.internal.icing.zzhg) r15, (com.google.android.gms.internal.icing.zzfu) r10)
            goto L_0x050d
        L_0x0340:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zza((int) r9, (java.util.List<java.lang.String>) r8, (com.google.android.gms.internal.icing.zzhg) r15)
            goto L_0x050d
        L_0x0351:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzn(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0362:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzk(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0373:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzf(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0384:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzh(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0395:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzd(r9, r8, r15, r5)
            goto L_0x050d
        L_0x03a6:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzc(r9, r8, r15, r5)
            goto L_0x050d
        L_0x03b7:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zzb((int) r9, (java.util.List<java.lang.Float>) r8, (com.google.android.gms.internal.icing.zzhg) r15, (boolean) r5)
            goto L_0x050d
        L_0x03c8:
            int[] r9 = r13.zzmt
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.icing.zzfw.zza((int) r9, (java.util.List<java.lang.Double>) r8, (com.google.android.gms.internal.icing.zzhg) r15, (boolean) r5)
            goto L_0x050d
        L_0x03d9:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzfu r10 = r13.zzae(r7)
            r15.zzb((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.icing.zzfu) r10)
            goto L_0x050d
        L_0x03ee:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzgs.zzk(r14, r10)
            r15.zzb((int) r9, (long) r10)
            goto L_0x050d
        L_0x03ff:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzgs.zzj(r14, r10)
            r15.zze(r9, r8)
            goto L_0x050d
        L_0x0410:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzgs.zzk(r14, r10)
            r15.zzj(r9, r10)
            goto L_0x050d
        L_0x0421:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzgs.zzj(r14, r10)
            r15.zzm(r9, r8)
            goto L_0x050d
        L_0x0432:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzgs.zzj(r14, r10)
            r15.zzn(r9, r8)
            goto L_0x050d
        L_0x0443:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzgs.zzj(r14, r10)
            r15.zzd(r9, r8)
            goto L_0x050d
        L_0x0454:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzct r8 = (com.google.android.gms.internal.icing.zzct) r8
            r15.zza((int) r9, (com.google.android.gms.internal.icing.zzct) r8)
            goto L_0x050d
        L_0x0467:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            com.google.android.gms.internal.icing.zzfu r10 = r13.zzae(r7)
            r15.zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.icing.zzfu) r10)
            goto L_0x050d
        L_0x047c:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r10)
            zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.icing.zzhg) r15)
            goto L_0x050d
        L_0x048d:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.icing.zzgs.zzl(r14, r10)
            r15.zza((int) r9, (boolean) r8)
            goto L_0x050d
        L_0x049e:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzgs.zzj(r14, r10)
            r15.zzf(r9, r8)
            goto L_0x050d
        L_0x04ae:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzgs.zzk(r14, r10)
            r15.zzc((int) r9, (long) r10)
            goto L_0x050d
        L_0x04be:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.icing.zzgs.zzj(r14, r10)
            r15.zzc((int) r9, (int) r8)
            goto L_0x050d
        L_0x04ce:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzgs.zzk(r14, r10)
            r15.zza((int) r9, (long) r10)
            goto L_0x050d
        L_0x04de:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.icing.zzgs.zzk(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x050d
        L_0x04ee:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.icing.zzgs.zzm(r14, r10)
            r15.zza((int) r9, (float) r8)
            goto L_0x050d
        L_0x04fe:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.icing.zzgs.zzn(r14, r10)
            r15.zza((int) r9, (double) r10)
        L_0x050d:
            int r7 = r7 + -3
            goto L_0x0039
        L_0x0511:
            if (r1 == 0) goto L_0x0528
            com.google.android.gms.internal.icing.zzdn<?> r14 = r13.zzmq
            r14.zza(r15, r1)
            boolean r14 = r0.hasNext()
            if (r14 == 0) goto L_0x0526
            java.lang.Object r14 = r0.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            r1 = r14
            goto L_0x0511
        L_0x0526:
            r1 = r3
            goto L_0x0511
        L_0x0528:
            return
        L_0x0529:
            boolean r0 = r13.zzmy
            if (r0 == 0) goto L_0x0a44
            boolean r0 = r13.zzmp
            if (r0 == 0) goto L_0x054a
            com.google.android.gms.internal.icing.zzdn<?> r0 = r13.zzmq
            com.google.android.gms.internal.icing.zzds r0 = r0.zzd(r14)
            com.google.android.gms.internal.icing.zzfz<T, java.lang.Object> r1 = r0.zzhk
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x054a
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x054c
        L_0x054a:
            r0 = r3
            r1 = r0
        L_0x054c:
            int[] r7 = r13.zzmt
            int r7 = r7.length
            r8 = 0
        L_0x0550:
            if (r8 >= r7) goto L_0x0a28
            int r9 = r13.zzag(r8)
            int[] r10 = r13.zzmt
            r10 = r10[r8]
        L_0x055a:
            if (r1 == 0) goto L_0x0578
            com.google.android.gms.internal.icing.zzdn<?> r11 = r13.zzmq
            int r11 = r11.zza(r1)
            if (r11 > r10) goto L_0x0578
            com.google.android.gms.internal.icing.zzdn<?> r11 = r13.zzmq
            r11.zza(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0576
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x055a
        L_0x0576:
            r1 = r3
            goto L_0x055a
        L_0x0578:
            r11 = r9 & r2
            int r11 = r11 >>> 20
            switch(r11) {
                case 0: goto L_0x0a15;
                case 1: goto L_0x0a05;
                case 2: goto L_0x09f5;
                case 3: goto L_0x09e5;
                case 4: goto L_0x09d5;
                case 5: goto L_0x09c5;
                case 6: goto L_0x09b5;
                case 7: goto L_0x09a4;
                case 8: goto L_0x0993;
                case 9: goto L_0x097e;
                case 10: goto L_0x096b;
                case 11: goto L_0x095a;
                case 12: goto L_0x0949;
                case 13: goto L_0x0938;
                case 14: goto L_0x0927;
                case 15: goto L_0x0916;
                case 16: goto L_0x0905;
                case 17: goto L_0x08f0;
                case 18: goto L_0x08df;
                case 19: goto L_0x08ce;
                case 20: goto L_0x08bd;
                case 21: goto L_0x08ac;
                case 22: goto L_0x089b;
                case 23: goto L_0x088a;
                case 24: goto L_0x0879;
                case 25: goto L_0x0868;
                case 26: goto L_0x0857;
                case 27: goto L_0x0842;
                case 28: goto L_0x0831;
                case 29: goto L_0x0820;
                case 30: goto L_0x080f;
                case 31: goto L_0x07fe;
                case 32: goto L_0x07ed;
                case 33: goto L_0x07dc;
                case 34: goto L_0x07cb;
                case 35: goto L_0x07ba;
                case 36: goto L_0x07a9;
                case 37: goto L_0x0798;
                case 38: goto L_0x0787;
                case 39: goto L_0x0776;
                case 40: goto L_0x0765;
                case 41: goto L_0x0754;
                case 42: goto L_0x0743;
                case 43: goto L_0x0732;
                case 44: goto L_0x0721;
                case 45: goto L_0x0710;
                case 46: goto L_0x06ff;
                case 47: goto L_0x06ee;
                case 48: goto L_0x06dd;
                case 49: goto L_0x06c8;
                case 50: goto L_0x06bd;
                case 51: goto L_0x06ac;
                case 52: goto L_0x069b;
                case 53: goto L_0x068a;
                case 54: goto L_0x0679;
                case 55: goto L_0x0668;
                case 56: goto L_0x0657;
                case 57: goto L_0x0646;
                case 58: goto L_0x0635;
                case 59: goto L_0x0624;
                case 60: goto L_0x060f;
                case 61: goto L_0x05fc;
                case 62: goto L_0x05eb;
                case 63: goto L_0x05da;
                case 64: goto L_0x05c9;
                case 65: goto L_0x05b8;
                case 66: goto L_0x05a7;
                case 67: goto L_0x0596;
                case 68: goto L_0x0581;
                default: goto L_0x057f;
            }
        L_0x057f:
            goto L_0x0a24
        L_0x0581:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzfu r11 = r13.zzae(r8)
            r15.zzb((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.icing.zzfu) r11)
            goto L_0x0a24
        L_0x0596:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zzb((int) r10, (long) r11)
            goto L_0x0a24
        L_0x05a7:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0a24
        L_0x05b8:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0a24
        L_0x05c9:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzm(r10, r9)
            goto L_0x0a24
        L_0x05da:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzn(r10, r9)
            goto L_0x0a24
        L_0x05eb:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzd(r10, r9)
            goto L_0x0a24
        L_0x05fc:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzct r9 = (com.google.android.gms.internal.icing.zzct) r9
            r15.zza((int) r10, (com.google.android.gms.internal.icing.zzct) r9)
            goto L_0x0a24
        L_0x060f:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzfu r11 = r13.zzae(r8)
            r15.zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.icing.zzfu) r11)
            goto L_0x0a24
        L_0x0624:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.icing.zzhg) r15)
            goto L_0x0a24
        L_0x0635:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = zzi(r14, r11)
            r15.zza((int) r10, (boolean) r9)
            goto L_0x0a24
        L_0x0646:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0a24
        L_0x0657:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zzc((int) r10, (long) r11)
            goto L_0x0a24
        L_0x0668:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzg(r14, r11)
            r15.zzc((int) r10, (int) r9)
            goto L_0x0a24
        L_0x0679:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zza((int) r10, (long) r11)
            goto L_0x0a24
        L_0x068a:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzh(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0a24
        L_0x069b:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = zzf(r14, r11)
            r15.zza((int) r10, (float) r9)
            goto L_0x0a24
        L_0x06ac:
            boolean r11 = r13.zza(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = zze(r14, r11)
            r15.zza((int) r10, (double) r11)
            goto L_0x0a24
        L_0x06bd:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            r13.zza((com.google.android.gms.internal.icing.zzhg) r15, (int) r10, (java.lang.Object) r9, (int) r8)
            goto L_0x0a24
        L_0x06c8:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfu r11 = r13.zzae(r8)
            com.google.android.gms.internal.icing.zzfw.zzb((int) r10, (java.util.List<?>) r9, (com.google.android.gms.internal.icing.zzhg) r15, (com.google.android.gms.internal.icing.zzfu) r11)
            goto L_0x0a24
        L_0x06dd:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zze(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x06ee:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzj(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x06ff:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzg(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0710:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzl(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0721:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzm(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0732:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzi(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0743:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzn(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0754:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzk(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0765:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzf(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0776:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzh(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0787:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzd(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0798:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzc(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x07a9:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzb((int) r10, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.icing.zzhg) r15, (boolean) r4)
            goto L_0x0a24
        L_0x07ba:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zza((int) r10, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.icing.zzhg) r15, (boolean) r4)
            goto L_0x0a24
        L_0x07cb:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zze(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x07dc:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzj(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x07ed:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzg(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x07fe:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzl(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x080f:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzm(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x0820:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzi(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x0831:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzb(r10, r9, r15)
            goto L_0x0a24
        L_0x0842:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfu r11 = r13.zzae(r8)
            com.google.android.gms.internal.icing.zzfw.zza((int) r10, (java.util.List<?>) r9, (com.google.android.gms.internal.icing.zzhg) r15, (com.google.android.gms.internal.icing.zzfu) r11)
            goto L_0x0a24
        L_0x0857:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zza((int) r10, (java.util.List<java.lang.String>) r9, (com.google.android.gms.internal.icing.zzhg) r15)
            goto L_0x0a24
        L_0x0868:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzn(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x0879:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzk(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x088a:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzf(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x089b:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzh(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x08ac:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzd(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x08bd:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzc(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x08ce:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzb((int) r10, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.icing.zzhg) r15, (boolean) r5)
            goto L_0x0a24
        L_0x08df:
            int[] r10 = r13.zzmt
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zza((int) r10, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.icing.zzhg) r15, (boolean) r5)
            goto L_0x0a24
        L_0x08f0:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzfu r11 = r13.zzae(r8)
            r15.zzb((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.icing.zzfu) r11)
            goto L_0x0a24
        L_0x0905:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzgs.zzk(r14, r11)
            r15.zzb((int) r10, (long) r11)
            goto L_0x0a24
        L_0x0916:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzgs.zzj(r14, r11)
            r15.zze(r10, r9)
            goto L_0x0a24
        L_0x0927:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzgs.zzk(r14, r11)
            r15.zzj(r10, r11)
            goto L_0x0a24
        L_0x0938:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzgs.zzj(r14, r11)
            r15.zzm(r10, r9)
            goto L_0x0a24
        L_0x0949:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzgs.zzj(r14, r11)
            r15.zzn(r10, r9)
            goto L_0x0a24
        L_0x095a:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzgs.zzj(r14, r11)
            r15.zzd(r10, r9)
            goto L_0x0a24
        L_0x096b:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzct r9 = (com.google.android.gms.internal.icing.zzct) r9
            r15.zza((int) r10, (com.google.android.gms.internal.icing.zzct) r9)
            goto L_0x0a24
        L_0x097e:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            com.google.android.gms.internal.icing.zzfu r11 = r13.zzae(r8)
            r15.zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.icing.zzfu) r11)
            goto L_0x0a24
        L_0x0993:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.icing.zzgs.zzo(r14, r11)
            zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.icing.zzhg) r15)
            goto L_0x0a24
        L_0x09a4:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.icing.zzgs.zzl(r14, r11)
            r15.zza((int) r10, (boolean) r9)
            goto L_0x0a24
        L_0x09b5:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzgs.zzj(r14, r11)
            r15.zzf(r10, r9)
            goto L_0x0a24
        L_0x09c5:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzgs.zzk(r14, r11)
            r15.zzc((int) r10, (long) r11)
            goto L_0x0a24
        L_0x09d5:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.icing.zzgs.zzj(r14, r11)
            r15.zzc((int) r10, (int) r9)
            goto L_0x0a24
        L_0x09e5:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzgs.zzk(r14, r11)
            r15.zza((int) r10, (long) r11)
            goto L_0x0a24
        L_0x09f5:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.icing.zzgs.zzk(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0a24
        L_0x0a05:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.icing.zzgs.zzm(r14, r11)
            r15.zza((int) r10, (float) r9)
            goto L_0x0a24
        L_0x0a15:
            boolean r11 = r13.zza(r14, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.icing.zzgs.zzn(r14, r11)
            r15.zza((int) r10, (double) r11)
        L_0x0a24:
            int r8 = r8 + 3
            goto L_0x0550
        L_0x0a28:
            if (r1 == 0) goto L_0x0a3e
            com.google.android.gms.internal.icing.zzdn<?> r2 = r13.zzmq
            r2.zza(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0a3c
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0a28
        L_0x0a3c:
            r1 = r3
            goto L_0x0a28
        L_0x0a3e:
            com.google.android.gms.internal.icing.zzgm<?, ?> r0 = r13.zzmo
            zza(r0, r14, (com.google.android.gms.internal.icing.zzhg) r15)
            return
        L_0x0a44:
            r13.zzb(r14, (com.google.android.gms.internal.icing.zzhg) r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzfl.zza(java.lang.Object, com.google.android.gms.internal.icing.zzhg):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:170:0x0496  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r19, com.google.android.gms.internal.icing.zzhg r20) throws java.io.IOException {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            boolean r3 = r0.zzmp
            if (r3 == 0) goto L_0x0023
            com.google.android.gms.internal.icing.zzdn<?> r3 = r0.zzmq
            com.google.android.gms.internal.icing.zzds r3 = r3.zzd(r1)
            com.google.android.gms.internal.icing.zzfz<T, java.lang.Object> r5 = r3.zzhk
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x0023
            java.util.Iterator r3 = r3.iterator()
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0025
        L_0x0023:
            r3 = 0
            r5 = 0
        L_0x0025:
            r6 = -1
            int[] r7 = r0.zzmt
            int r7 = r7.length
            sun.misc.Unsafe r8 = zzms
            r10 = 0
            r11 = 0
        L_0x002d:
            if (r10 >= r7) goto L_0x0494
            int r12 = r0.zzag(r10)
            int[] r13 = r0.zzmt
            r14 = r13[r10]
            r15 = 267386880(0xff00000, float:2.3665827E-29)
            r15 = r15 & r12
            int r15 = r15 >>> 20
            boolean r4 = r0.zzmy
            r16 = 1048575(0xfffff, float:1.469367E-39)
            if (r4 != 0) goto L_0x005d
            r4 = 17
            if (r15 > r4) goto L_0x005d
            int r4 = r10 + 2
            r4 = r13[r4]
            r13 = r4 & r16
            r17 = r10
            if (r13 == r6) goto L_0x0057
            long r9 = (long) r13
            int r11 = r8.getInt(r1, r9)
            r6 = r13
        L_0x0057:
            int r4 = r4 >>> 20
            r9 = 1
            int r4 = r9 << r4
            goto L_0x0060
        L_0x005d:
            r17 = r10
            r4 = 0
        L_0x0060:
            if (r5 == 0) goto L_0x007e
            com.google.android.gms.internal.icing.zzdn<?> r9 = r0.zzmq
            int r9 = r9.zza(r5)
            if (r9 > r14) goto L_0x007e
            com.google.android.gms.internal.icing.zzdn<?> r9 = r0.zzmq
            r9.zza(r2, r5)
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x007c
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0060
        L_0x007c:
            r5 = 0
            goto L_0x0060
        L_0x007e:
            r9 = r12 & r16
            long r9 = (long) r9
            r12 = r17
            switch(r15) {
                case 0: goto L_0x0485;
                case 1: goto L_0x0479;
                case 2: goto L_0x046d;
                case 3: goto L_0x0461;
                case 4: goto L_0x0455;
                case 5: goto L_0x0449;
                case 6: goto L_0x043d;
                case 7: goto L_0x0431;
                case 8: goto L_0x0425;
                case 9: goto L_0x0414;
                case 10: goto L_0x0405;
                case 11: goto L_0x03f8;
                case 12: goto L_0x03eb;
                case 13: goto L_0x03de;
                case 14: goto L_0x03d1;
                case 15: goto L_0x03c4;
                case 16: goto L_0x03b7;
                case 17: goto L_0x03a6;
                case 18: goto L_0x0396;
                case 19: goto L_0x0386;
                case 20: goto L_0x0376;
                case 21: goto L_0x0366;
                case 22: goto L_0x0356;
                case 23: goto L_0x0346;
                case 24: goto L_0x0336;
                case 25: goto L_0x0326;
                case 26: goto L_0x0317;
                case 27: goto L_0x0304;
                case 28: goto L_0x02f5;
                case 29: goto L_0x02e5;
                case 30: goto L_0x02d5;
                case 31: goto L_0x02c5;
                case 32: goto L_0x02b5;
                case 33: goto L_0x02a5;
                case 34: goto L_0x0295;
                case 35: goto L_0x0285;
                case 36: goto L_0x0275;
                case 37: goto L_0x0265;
                case 38: goto L_0x0255;
                case 39: goto L_0x0245;
                case 40: goto L_0x0235;
                case 41: goto L_0x0225;
                case 42: goto L_0x0215;
                case 43: goto L_0x0205;
                case 44: goto L_0x01f5;
                case 45: goto L_0x01e5;
                case 46: goto L_0x01d5;
                case 47: goto L_0x01c5;
                case 48: goto L_0x01b5;
                case 49: goto L_0x01a2;
                case 50: goto L_0x0199;
                case 51: goto L_0x018a;
                case 52: goto L_0x017b;
                case 53: goto L_0x016c;
                case 54: goto L_0x015d;
                case 55: goto L_0x014e;
                case 56: goto L_0x013f;
                case 57: goto L_0x0130;
                case 58: goto L_0x0121;
                case 59: goto L_0x0112;
                case 60: goto L_0x00ff;
                case 61: goto L_0x00ef;
                case 62: goto L_0x00e1;
                case 63: goto L_0x00d3;
                case 64: goto L_0x00c5;
                case 65: goto L_0x00b7;
                case 66: goto L_0x00a9;
                case 67: goto L_0x009b;
                case 68: goto L_0x0089;
                default: goto L_0x0086;
            }
        L_0x0086:
            r13 = 0
            goto L_0x0490
        L_0x0089:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            java.lang.Object r4 = r8.getObject(r1, r9)
            com.google.android.gms.internal.icing.zzfu r9 = r0.zzae(r12)
            r2.zzb((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.icing.zzfu) r9)
            goto L_0x0086
        L_0x009b:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            long r9 = zzh(r1, r9)
            r2.zzb((int) r14, (long) r9)
            goto L_0x0086
        L_0x00a9:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            int r4 = zzg(r1, r9)
            r2.zze(r14, r4)
            goto L_0x0086
        L_0x00b7:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            long r9 = zzh(r1, r9)
            r2.zzj(r14, r9)
            goto L_0x0086
        L_0x00c5:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            int r4 = zzg(r1, r9)
            r2.zzm(r14, r4)
            goto L_0x0086
        L_0x00d3:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            int r4 = zzg(r1, r9)
            r2.zzn(r14, r4)
            goto L_0x0086
        L_0x00e1:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            int r4 = zzg(r1, r9)
            r2.zzd(r14, r4)
            goto L_0x0086
        L_0x00ef:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            java.lang.Object r4 = r8.getObject(r1, r9)
            com.google.android.gms.internal.icing.zzct r4 = (com.google.android.gms.internal.icing.zzct) r4
            r2.zza((int) r14, (com.google.android.gms.internal.icing.zzct) r4)
            goto L_0x0086
        L_0x00ff:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            java.lang.Object r4 = r8.getObject(r1, r9)
            com.google.android.gms.internal.icing.zzfu r9 = r0.zzae(r12)
            r2.zza((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.icing.zzfu) r9)
            goto L_0x0086
        L_0x0112:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            java.lang.Object r4 = r8.getObject(r1, r9)
            zza((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.icing.zzhg) r2)
            goto L_0x0086
        L_0x0121:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            boolean r4 = zzi(r1, r9)
            r2.zza((int) r14, (boolean) r4)
            goto L_0x0086
        L_0x0130:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            int r4 = zzg(r1, r9)
            r2.zzf(r14, r4)
            goto L_0x0086
        L_0x013f:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            long r9 = zzh(r1, r9)
            r2.zzc((int) r14, (long) r9)
            goto L_0x0086
        L_0x014e:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            int r4 = zzg(r1, r9)
            r2.zzc((int) r14, (int) r4)
            goto L_0x0086
        L_0x015d:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            long r9 = zzh(r1, r9)
            r2.zza((int) r14, (long) r9)
            goto L_0x0086
        L_0x016c:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            long r9 = zzh(r1, r9)
            r2.zzi(r14, r9)
            goto L_0x0086
        L_0x017b:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            float r4 = zzf(r1, r9)
            r2.zza((int) r14, (float) r4)
            goto L_0x0086
        L_0x018a:
            boolean r4 = r0.zza(r1, (int) r14, (int) r12)
            if (r4 == 0) goto L_0x0086
            double r9 = zze(r1, r9)
            r2.zza((int) r14, (double) r9)
            goto L_0x0086
        L_0x0199:
            java.lang.Object r4 = r8.getObject(r1, r9)
            r0.zza((com.google.android.gms.internal.icing.zzhg) r2, (int) r14, (java.lang.Object) r4, (int) r12)
            goto L_0x0086
        L_0x01a2:
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfu r10 = r0.zzae(r12)
            com.google.android.gms.internal.icing.zzfw.zzb((int) r4, (java.util.List<?>) r9, (com.google.android.gms.internal.icing.zzhg) r2, (com.google.android.gms.internal.icing.zzfu) r10)
            goto L_0x0086
        L_0x01b5:
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r13 = 1
            com.google.android.gms.internal.icing.zzfw.zze(r4, r9, r2, r13)
            goto L_0x0086
        L_0x01c5:
            r13 = 1
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzj(r4, r9, r2, r13)
            goto L_0x0086
        L_0x01d5:
            r13 = 1
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzg(r4, r9, r2, r13)
            goto L_0x0086
        L_0x01e5:
            r13 = 1
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzl(r4, r9, r2, r13)
            goto L_0x0086
        L_0x01f5:
            r13 = 1
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzm(r4, r9, r2, r13)
            goto L_0x0086
        L_0x0205:
            r13 = 1
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzi(r4, r9, r2, r13)
            goto L_0x0086
        L_0x0215:
            r13 = 1
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzn(r4, r9, r2, r13)
            goto L_0x0086
        L_0x0225:
            r13 = 1
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzk(r4, r9, r2, r13)
            goto L_0x0086
        L_0x0235:
            r13 = 1
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzf(r4, r9, r2, r13)
            goto L_0x0086
        L_0x0245:
            r13 = 1
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzh(r4, r9, r2, r13)
            goto L_0x0086
        L_0x0255:
            r13 = 1
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzd(r4, r9, r2, r13)
            goto L_0x0086
        L_0x0265:
            r13 = 1
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzc(r4, r9, r2, r13)
            goto L_0x0086
        L_0x0275:
            r13 = 1
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzb((int) r4, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.icing.zzhg) r2, (boolean) r13)
            goto L_0x0086
        L_0x0285:
            r13 = 1
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zza((int) r4, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.icing.zzhg) r2, (boolean) r13)
            goto L_0x0086
        L_0x0295:
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.icing.zzfw.zze(r4, r9, r2, r13)
            goto L_0x0490
        L_0x02a5:
            r13 = 0
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzj(r4, r9, r2, r13)
            goto L_0x0490
        L_0x02b5:
            r13 = 0
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzg(r4, r9, r2, r13)
            goto L_0x0490
        L_0x02c5:
            r13 = 0
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzl(r4, r9, r2, r13)
            goto L_0x0490
        L_0x02d5:
            r13 = 0
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzm(r4, r9, r2, r13)
            goto L_0x0490
        L_0x02e5:
            r13 = 0
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzi(r4, r9, r2, r13)
            goto L_0x0490
        L_0x02f5:
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzb(r4, r9, r2)
            goto L_0x0086
        L_0x0304:
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfu r10 = r0.zzae(r12)
            com.google.android.gms.internal.icing.zzfw.zza((int) r4, (java.util.List<?>) r9, (com.google.android.gms.internal.icing.zzhg) r2, (com.google.android.gms.internal.icing.zzfu) r10)
            goto L_0x0086
        L_0x0317:
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zza((int) r4, (java.util.List<java.lang.String>) r9, (com.google.android.gms.internal.icing.zzhg) r2)
            goto L_0x0086
        L_0x0326:
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            r13 = 0
            com.google.android.gms.internal.icing.zzfw.zzn(r4, r9, r2, r13)
            goto L_0x0490
        L_0x0336:
            r13 = 0
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzk(r4, r9, r2, r13)
            goto L_0x0490
        L_0x0346:
            r13 = 0
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzf(r4, r9, r2, r13)
            goto L_0x0490
        L_0x0356:
            r13 = 0
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzh(r4, r9, r2, r13)
            goto L_0x0490
        L_0x0366:
            r13 = 0
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzd(r4, r9, r2, r13)
            goto L_0x0490
        L_0x0376:
            r13 = 0
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzc(r4, r9, r2, r13)
            goto L_0x0490
        L_0x0386:
            r13 = 0
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zzb((int) r4, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.icing.zzhg) r2, (boolean) r13)
            goto L_0x0490
        L_0x0396:
            r13 = 0
            int[] r4 = r0.zzmt
            r4 = r4[r12]
            java.lang.Object r9 = r8.getObject(r1, r9)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.icing.zzfw.zza((int) r4, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.icing.zzhg) r2, (boolean) r13)
            goto L_0x0490
        L_0x03a6:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            java.lang.Object r4 = r8.getObject(r1, r9)
            com.google.android.gms.internal.icing.zzfu r9 = r0.zzae(r12)
            r2.zzb((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.icing.zzfu) r9)
            goto L_0x0490
        L_0x03b7:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            long r9 = r8.getLong(r1, r9)
            r2.zzb((int) r14, (long) r9)
            goto L_0x0490
        L_0x03c4:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            int r4 = r8.getInt(r1, r9)
            r2.zze(r14, r4)
            goto L_0x0490
        L_0x03d1:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            long r9 = r8.getLong(r1, r9)
            r2.zzj(r14, r9)
            goto L_0x0490
        L_0x03de:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            int r4 = r8.getInt(r1, r9)
            r2.zzm(r14, r4)
            goto L_0x0490
        L_0x03eb:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            int r4 = r8.getInt(r1, r9)
            r2.zzn(r14, r4)
            goto L_0x0490
        L_0x03f8:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            int r4 = r8.getInt(r1, r9)
            r2.zzd(r14, r4)
            goto L_0x0490
        L_0x0405:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            java.lang.Object r4 = r8.getObject(r1, r9)
            com.google.android.gms.internal.icing.zzct r4 = (com.google.android.gms.internal.icing.zzct) r4
            r2.zza((int) r14, (com.google.android.gms.internal.icing.zzct) r4)
            goto L_0x0490
        L_0x0414:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            java.lang.Object r4 = r8.getObject(r1, r9)
            com.google.android.gms.internal.icing.zzfu r9 = r0.zzae(r12)
            r2.zza((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.icing.zzfu) r9)
            goto L_0x0490
        L_0x0425:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            java.lang.Object r4 = r8.getObject(r1, r9)
            zza((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.icing.zzhg) r2)
            goto L_0x0490
        L_0x0431:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            boolean r4 = com.google.android.gms.internal.icing.zzgs.zzl(r1, r9)
            r2.zza((int) r14, (boolean) r4)
            goto L_0x0490
        L_0x043d:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            int r4 = r8.getInt(r1, r9)
            r2.zzf(r14, r4)
            goto L_0x0490
        L_0x0449:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            long r9 = r8.getLong(r1, r9)
            r2.zzc((int) r14, (long) r9)
            goto L_0x0490
        L_0x0455:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            int r4 = r8.getInt(r1, r9)
            r2.zzc((int) r14, (int) r4)
            goto L_0x0490
        L_0x0461:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            long r9 = r8.getLong(r1, r9)
            r2.zza((int) r14, (long) r9)
            goto L_0x0490
        L_0x046d:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            long r9 = r8.getLong(r1, r9)
            r2.zzi(r14, r9)
            goto L_0x0490
        L_0x0479:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            float r4 = com.google.android.gms.internal.icing.zzgs.zzm(r1, r9)
            r2.zza((int) r14, (float) r4)
            goto L_0x0490
        L_0x0485:
            r13 = 0
            r4 = r4 & r11
            if (r4 == 0) goto L_0x0490
            double r9 = com.google.android.gms.internal.icing.zzgs.zzn(r1, r9)
            r2.zza((int) r14, (double) r9)
        L_0x0490:
            int r10 = r12 + 3
            goto L_0x002d
        L_0x0494:
            if (r5 == 0) goto L_0x04ab
            com.google.android.gms.internal.icing.zzdn<?> r4 = r0.zzmq
            r4.zza(r2, r5)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x04a9
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r5 = r4
            goto L_0x0494
        L_0x04a9:
            r5 = 0
            goto L_0x0494
        L_0x04ab:
            com.google.android.gms.internal.icing.zzgm<?, ?> r3 = r0.zzmo
            zza(r3, r1, (com.google.android.gms.internal.icing.zzhg) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzfl.zzb(java.lang.Object, com.google.android.gms.internal.icing.zzhg):void");
    }

    private final <K, V> void zza(zzhg zzhg, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzhg.zza(i, this.zznf.zzk(zzaf(i2)), this.zznf.zzi(obj));
        }
    }

    private static <UT, UB> void zza(zzgm<UT, UB> zzgm, T t, zzhg zzhg) throws IOException {
        zzgm.zza(zzgm.zzp(t), zzhg);
    }

    private final zzfu zzae(int i) {
        int i2 = (i / 3) << 1;
        zzfu zzfu = (zzfu) this.zzmu[i2];
        if (zzfu != null) {
            return zzfu;
        }
        zzfu zze = zzft.zzcv().zze((Class) this.zzmu[i2 + 1]);
        this.zzmu[i2] = zze;
        return zze;
    }

    private final Object zzaf(int i) {
        return this.zzmu[(i / 3) << 1];
    }

    public final void zzf(T t) {
        int i;
        int i2 = this.zznb;
        while (true) {
            i = this.zznc;
            if (i2 >= i) {
                break;
            }
            long zzag = (long) (zzag(this.zzna[i2]) & 1048575);
            Object zzo = zzgs.zzo(t, zzag);
            if (zzo != null) {
                zzgs.zza((Object) t, zzag, this.zznf.zzj(zzo));
            }
            i2++;
        }
        int length = this.zzna.length;
        while (i < length) {
            this.zzne.zza(t, (long) this.zzna[i]);
            i++;
        }
        this.zzmo.zzf(t);
        if (this.zzmp) {
            this.zzmq.zzf(t);
        }
    }

    public final boolean zzm(T t) {
        int i;
        int i2 = -1;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            boolean z = true;
            if (i3 >= this.zznb) {
                return !this.zzmp || this.zzmq.zzd(t).isInitialized();
            }
            int i5 = this.zzna[i3];
            int i6 = this.zzmt[i5];
            int zzag = zzag(i5);
            if (!this.zzmy) {
                int i7 = this.zzmt[i5 + 2];
                int i8 = i7 & 1048575;
                i = 1 << (i7 >>> 20);
                if (i8 != i2) {
                    i4 = zzms.getInt(t, (long) i8);
                    i2 = i8;
                }
            } else {
                i = 0;
            }
            if (((268435456 & zzag) != 0) && !zza(t, i5, i4, i)) {
                return false;
            }
            int i9 = (267386880 & zzag) >>> 20;
            if (i9 != 9 && i9 != 17) {
                if (i9 != 27) {
                    if (i9 == 60 || i9 == 68) {
                        if (zza(t, i6, i5) && !zza((Object) t, zzag, zzae(i5))) {
                            return false;
                        }
                    } else if (i9 != 49) {
                        if (i9 != 50) {
                            continue;
                        } else {
                            Map<?, ?> zzi = this.zznf.zzi(zzgs.zzo(t, (long) (zzag & 1048575)));
                            if (!zzi.isEmpty()) {
                                if (this.zznf.zzk(zzaf(i5)).zzmj.zzdt() == zzhh.MESSAGE) {
                                    zzfu<?> zzfu = null;
                                    Iterator<?> it = zzi.values().iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        Object next = it.next();
                                        if (zzfu == null) {
                                            zzfu = zzft.zzcv().zze(next.getClass());
                                        }
                                        if (!zzfu.zzm(next)) {
                                            z = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!z) {
                                return false;
                            }
                        }
                    }
                }
                List list = (List) zzgs.zzo(t, (long) (zzag & 1048575));
                if (!list.isEmpty()) {
                    zzfu zzae = zzae(i5);
                    int i10 = 0;
                    while (true) {
                        if (i10 >= list.size()) {
                            break;
                        } else if (!zzae.zzm(list.get(i10))) {
                            z = false;
                            break;
                        } else {
                            i10++;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (zza(t, i5, i4, i) && !zza((Object) t, zzag, zzae(i5))) {
                return false;
            }
            i3++;
        }
    }

    private static boolean zza(Object obj, int i, zzfu zzfu) {
        return zzfu.zzm(zzgs.zzo(obj, (long) (i & 1048575)));
    }

    private static void zza(int i, Object obj, zzhg zzhg) throws IOException {
        if (obj instanceof String) {
            zzhg.zza(i, (String) obj);
        } else {
            zzhg.zza(i, (zzct) obj);
        }
    }

    private final int zzag(int i) {
        return this.zzmt[i + 1];
    }

    private final int zzah(int i) {
        return this.zzmt[i + 2];
    }

    private static <T> double zze(T t, long j) {
        return ((Double) zzgs.zzo(t, j)).doubleValue();
    }

    private static <T> float zzf(T t, long j) {
        return ((Float) zzgs.zzo(t, j)).floatValue();
    }

    private static <T> int zzg(T t, long j) {
        return ((Integer) zzgs.zzo(t, j)).intValue();
    }

    private static <T> long zzh(T t, long j) {
        return ((Long) zzgs.zzo(t, j)).longValue();
    }

    private static <T> boolean zzi(T t, long j) {
        return ((Boolean) zzgs.zzo(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzmy) {
            return zza(t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zza(T t, int i) {
        if (this.zzmy) {
            int zzag = zzag(i);
            long j = (long) (zzag & 1048575);
            switch ((zzag & 267386880) >>> 20) {
                case 0:
                    return zzgs.zzn(t, j) != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                case 1:
                    return zzgs.zzm(t, j) != 0.0f;
                case 2:
                    return zzgs.zzk(t, j) != 0;
                case 3:
                    return zzgs.zzk(t, j) != 0;
                case 4:
                    return zzgs.zzj(t, j) != 0;
                case 5:
                    return zzgs.zzk(t, j) != 0;
                case 6:
                    return zzgs.zzj(t, j) != 0;
                case 7:
                    return zzgs.zzl(t, j);
                case 8:
                    Object zzo = zzgs.zzo(t, j);
                    if (zzo instanceof String) {
                        return !((String) zzo).isEmpty();
                    }
                    if (zzo instanceof zzct) {
                        return !zzct.zzgi.equals(zzo);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzgs.zzo(t, j) != null;
                case 10:
                    return !zzct.zzgi.equals(zzgs.zzo(t, j));
                case 11:
                    return zzgs.zzj(t, j) != 0;
                case 12:
                    return zzgs.zzj(t, j) != 0;
                case 13:
                    return zzgs.zzj(t, j) != 0;
                case 14:
                    return zzgs.zzk(t, j) != 0;
                case 15:
                    return zzgs.zzj(t, j) != 0;
                case 16:
                    return zzgs.zzk(t, j) != 0;
                case 17:
                    return zzgs.zzo(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzah = zzah(i);
            return (zzgs.zzj(t, (long) (zzah & 1048575)) & (1 << (zzah >>> 20))) != 0;
        }
    }

    private final void zzb(T t, int i) {
        if (!this.zzmy) {
            int zzah = zzah(i);
            long j = (long) (zzah & 1048575);
            zzgs.zza((Object) t, j, zzgs.zzj(t, j) | (1 << (zzah >>> 20)));
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzgs.zzj(t, (long) (zzah(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzgs.zza((Object) t, (long) (zzah(i2) & 1048575), i);
    }
}

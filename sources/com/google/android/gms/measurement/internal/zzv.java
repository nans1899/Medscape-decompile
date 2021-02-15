package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzbv;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
abstract class zzv {
    String zza;
    int zzb;
    Boolean zzc;
    Boolean zzd;
    Long zze;
    Long zzf;

    zzv(String str, int i) {
        this.zza = str;
        this.zzb = i;
    }

    /* access modifiers changed from: package-private */
    public abstract int zza();

    /* access modifiers changed from: package-private */
    public abstract boolean zzb();

    /* access modifiers changed from: package-private */
    public abstract boolean zzc();

    static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() != z);
    }

    static Boolean zza(String str, zzbv.zzf zzf2, zzer zzer) {
        String str2;
        List<String> list;
        Preconditions.checkNotNull(zzf2);
        if (str == null || !zzf2.zza() || zzf2.zzb() == zzbv.zzf.zzb.UNKNOWN_MATCH_TYPE) {
            return null;
        }
        if (zzf2.zzb() == zzbv.zzf.zzb.IN_LIST) {
            if (zzf2.zzh() == 0) {
                return null;
            }
        } else if (!zzf2.zzc()) {
            return null;
        }
        zzbv.zzf.zzb zzb2 = zzf2.zzb();
        boolean zzf3 = zzf2.zzf();
        if (zzf3 || zzb2 == zzbv.zzf.zzb.REGEXP || zzb2 == zzbv.zzf.zzb.IN_LIST) {
            str2 = zzf2.zzd();
        } else {
            str2 = zzf2.zzd().toUpperCase(Locale.ENGLISH);
        }
        String str3 = str2;
        if (zzf2.zzh() == 0) {
            list = null;
        } else {
            List<String> zzg = zzf2.zzg();
            if (!zzf3) {
                ArrayList arrayList = new ArrayList(zzg.size());
                for (String upperCase : zzg) {
                    arrayList.add(upperCase.toUpperCase(Locale.ENGLISH));
                }
                zzg = Collections.unmodifiableList(arrayList);
            }
            list = zzg;
        }
        return zza(str, zzb2, zzf3, str3, list, zzb2 == zzbv.zzf.zzb.REGEXP ? str3 : null, zzer);
    }

    private static Boolean zza(String str, zzbv.zzf.zzb zzb2, boolean z, String str2, List<String> list, String str3, zzer zzer) {
        if (str == null) {
            return null;
        }
        if (zzb2 == zzbv.zzf.zzb.IN_LIST) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && zzb2 != zzbv.zzf.zzb.REGEXP) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (zzr.zza[zzb2.ordinal()]) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException unused) {
                    if (zzer != null) {
                        zzer.zzh().zza("Invalid regular expression in REGEXP audience filter. expression", str3);
                    }
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    static Boolean zza(long j, zzbv.zzd zzd2) {
        try {
            return zza(new BigDecimal(j), zzd2, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static Boolean zza(double d, zzbv.zzd zzd2) {
        try {
            return zza(new BigDecimal(d), zzd2, Math.ulp(d));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static Boolean zza(String str, zzbv.zzd zzd2) {
        if (!zzks.zza(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzd2, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0084, code lost:
        if (r2 != null) goto L_0x0086;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Boolean zza(java.math.BigDecimal r9, com.google.android.gms.internal.measurement.zzbv.zzd r10, double r11) {
        /*
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r10)
            boolean r0 = r10.zza()
            r1 = 0
            if (r0 == 0) goto L_0x010f
            com.google.android.gms.internal.measurement.zzbv$zzd$zza r0 = r10.zzb()
            com.google.android.gms.internal.measurement.zzbv$zzd$zza r2 = com.google.android.gms.internal.measurement.zzbv.zzd.zza.UNKNOWN_COMPARISON_TYPE
            if (r0 != r2) goto L_0x0014
            goto L_0x010f
        L_0x0014:
            com.google.android.gms.internal.measurement.zzbv$zzd$zza r0 = r10.zzb()
            com.google.android.gms.internal.measurement.zzbv$zzd$zza r2 = com.google.android.gms.internal.measurement.zzbv.zzd.zza.BETWEEN
            if (r0 != r2) goto L_0x0029
            boolean r0 = r10.zzg()
            if (r0 == 0) goto L_0x0028
            boolean r0 = r10.zzi()
            if (r0 != 0) goto L_0x0030
        L_0x0028:
            return r1
        L_0x0029:
            boolean r0 = r10.zze()
            if (r0 != 0) goto L_0x0030
            return r1
        L_0x0030:
            com.google.android.gms.internal.measurement.zzbv$zzd$zza r0 = r10.zzb()
            com.google.android.gms.internal.measurement.zzbv$zzd$zza r2 = r10.zzb()
            com.google.android.gms.internal.measurement.zzbv$zzd$zza r3 = com.google.android.gms.internal.measurement.zzbv.zzd.zza.BETWEEN
            if (r2 != r3) goto L_0x0067
            java.lang.String r2 = r10.zzh()
            boolean r2 = com.google.android.gms.measurement.internal.zzks.zza((java.lang.String) r2)
            if (r2 == 0) goto L_0x0066
            java.lang.String r2 = r10.zzj()
            boolean r2 = com.google.android.gms.measurement.internal.zzks.zza((java.lang.String) r2)
            if (r2 != 0) goto L_0x0051
            goto L_0x0066
        L_0x0051:
            java.math.BigDecimal r2 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0066 }
            java.lang.String r3 = r10.zzh()     // Catch:{ NumberFormatException -> 0x0066 }
            r2.<init>(r3)     // Catch:{ NumberFormatException -> 0x0066 }
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0066 }
            java.lang.String r10 = r10.zzj()     // Catch:{ NumberFormatException -> 0x0066 }
            r3.<init>(r10)     // Catch:{ NumberFormatException -> 0x0066 }
            r10 = r2
            r2 = r1
            goto L_0x007d
        L_0x0066:
            return r1
        L_0x0067:
            java.lang.String r2 = r10.zzf()
            boolean r2 = com.google.android.gms.measurement.internal.zzks.zza((java.lang.String) r2)
            if (r2 != 0) goto L_0x0072
            return r1
        L_0x0072:
            java.math.BigDecimal r2 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x010f }
            java.lang.String r10 = r10.zzf()     // Catch:{ NumberFormatException -> 0x010f }
            r2.<init>(r10)     // Catch:{ NumberFormatException -> 0x010f }
            r10 = r1
            r3 = r10
        L_0x007d:
            com.google.android.gms.internal.measurement.zzbv$zzd$zza r4 = com.google.android.gms.internal.measurement.zzbv.zzd.zza.BETWEEN
            if (r0 != r4) goto L_0x0084
            if (r10 != 0) goto L_0x0086
            return r1
        L_0x0084:
            if (r2 == 0) goto L_0x010f
        L_0x0086:
            int[] r4 = com.google.android.gms.measurement.internal.zzr.zzb
            int r0 = r0.ordinal()
            r0 = r4[r0]
            r4 = -1
            r5 = 0
            r6 = 1
            if (r0 == r6) goto L_0x0103
            r7 = 2
            if (r0 == r7) goto L_0x00f7
            r8 = 3
            if (r0 == r8) goto L_0x00af
            r11 = 4
            if (r0 == r11) goto L_0x009d
            goto L_0x010f
        L_0x009d:
            int r10 = r9.compareTo(r10)
            if (r10 == r4) goto L_0x00aa
            int r9 = r9.compareTo(r3)
            if (r9 == r6) goto L_0x00aa
            r5 = 1
        L_0x00aa:
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r5)
            return r9
        L_0x00af:
            r0 = 0
            int r10 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r10 == 0) goto L_0x00eb
            java.math.BigDecimal r10 = new java.math.BigDecimal
            r10.<init>(r11)
            java.math.BigDecimal r0 = new java.math.BigDecimal
            r0.<init>(r7)
            java.math.BigDecimal r10 = r10.multiply(r0)
            java.math.BigDecimal r10 = r2.subtract(r10)
            int r10 = r9.compareTo(r10)
            if (r10 != r6) goto L_0x00e6
            java.math.BigDecimal r10 = new java.math.BigDecimal
            r10.<init>(r11)
            java.math.BigDecimal r11 = new java.math.BigDecimal
            r11.<init>(r7)
            java.math.BigDecimal r10 = r10.multiply(r11)
            java.math.BigDecimal r10 = r2.add(r10)
            int r9 = r9.compareTo(r10)
            if (r9 != r4) goto L_0x00e6
            r5 = 1
        L_0x00e6:
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r5)
            return r9
        L_0x00eb:
            int r9 = r9.compareTo(r2)
            if (r9 != 0) goto L_0x00f2
            r5 = 1
        L_0x00f2:
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r5)
            return r9
        L_0x00f7:
            int r9 = r9.compareTo(r2)
            if (r9 != r6) goto L_0x00fe
            r5 = 1
        L_0x00fe:
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r5)
            return r9
        L_0x0103:
            int r9 = r9.compareTo(r2)
            if (r9 != r4) goto L_0x010a
            r5 = 1
        L_0x010a:
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r5)
            return r9
        L_0x010f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzv.zza(java.math.BigDecimal, com.google.android.gms.internal.measurement.zzbv$zzd, double):java.lang.Boolean");
    }
}

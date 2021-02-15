package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzfw {
    private static final Class<?> zznn = zzdb();
    private static final zzgm<?, ?> zzno = zzh(false);
    private static final zzgm<?, ?> zznp = zzh(true);
    private static final zzgm<?, ?> zznq = new zzgo();

    public static void zzf(Class<?> cls) {
        Class<?> cls2;
        if (!zzdx.class.isAssignableFrom(cls) && (cls2 = zznn) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzhg zzhg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzhg zzhg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzhg zzhg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzhg zzhg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzhg zzhg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzhg zzhg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzhg zzhg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzhg zzhg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzhg zzhg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzhg zzhg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzhg zzhg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzhg zzhg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzhg zzhg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzhg zzhg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzhg zzhg) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zza(i, list);
        }
    }

    public static void zzb(int i, List<zzct> list, zzhg zzhg) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zzb(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzhg zzhg, zzfu zzfu) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zza(i, list, zzfu);
        }
    }

    public static void zzb(int i, List<?> list, zzhg zzhg, zzfu zzfu) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhg.zzb(i, list, zzfu);
        }
    }

    static int zza(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzev) {
            zzev zzev = (zzev) list;
            i = 0;
            while (i2 < size) {
                i += zzdk.zze(zzev.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzdk.zze(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zza(list) + (list.size() * zzdk.zzs(i));
    }

    static int zzb(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzev) {
            zzev zzev = (zzev) list;
            i = 0;
            while (i2 < size) {
                i += zzdk.zzf(zzev.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzdk.zzf(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzp(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzdk.zzs(i));
    }

    static int zzc(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzev) {
            zzev zzev = (zzev) list;
            i = 0;
            while (i2 < size) {
                i += zzdk.zzg(zzev.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzdk.zzg(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzq(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzc(list) + (size * zzdk.zzs(i));
    }

    static int zzd(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdz) {
            zzdz zzdz = (zzdz) list;
            i = 0;
            while (i2 < size) {
                i += zzdk.zzy(zzdz.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzdk.zzy(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzr(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzdk.zzs(i));
    }

    static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdz) {
            zzdz zzdz = (zzdz) list;
            i = 0;
            while (i2 < size) {
                i += zzdk.zzt(zzdz.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzdk.zzt(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzs(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzdk.zzs(i));
    }

    static int zzf(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdz) {
            zzdz zzdz = (zzdz) list;
            i = 0;
            while (i2 < size) {
                i += zzdk.zzu(zzdz.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzdk.zzu(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzt(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzf(list) + (size * zzdk.zzs(i));
    }

    static int zzg(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdz) {
            zzdz zzdz = (zzdz) list;
            i = 0;
            while (i2 < size) {
                i += zzdk.zzv(zzdz.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzdk.zzv(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzu(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzdk.zzs(i));
    }

    static int zzh(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzdk.zzj(i, 0);
    }

    static int zzi(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzdk.zzg(i, 0);
    }

    static int zzj(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzdk.zzb(i, true);
    }

    static int zzc(int i, List<?> list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzs = zzdk.zzs(i) * size;
        if (list instanceof zzeo) {
            zzeo zzeo = (zzeo) list;
            while (i4 < size) {
                Object zzad = zzeo.zzad(i4);
                if (zzad instanceof zzct) {
                    i3 = zzdk.zzb((zzct) zzad);
                } else {
                    i3 = zzdk.zzr((String) zzad);
                }
                zzs += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzct) {
                    i2 = zzdk.zzb((zzct) obj);
                } else {
                    i2 = zzdk.zzr((String) obj);
                }
                zzs += i2;
                i4++;
            }
        }
        return zzs;
    }

    static int zzc(int i, Object obj, zzfu zzfu) {
        if (obj instanceof zzem) {
            return zzdk.zza(i, (zzem) obj);
        }
        return zzdk.zzb(i, (zzfh) obj, zzfu);
    }

    static int zzc(int i, List<?> list, zzfu zzfu) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzs = zzdk.zzs(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzem) {
                i2 = zzdk.zza((zzem) obj);
            } else {
                i2 = zzdk.zza((zzfh) obj, zzfu);
            }
            zzs += i2;
        }
        return zzs;
    }

    static int zzd(int i, List<zzct> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzs = size * zzdk.zzs(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzs += zzdk.zzb(list.get(i2));
        }
        return zzs;
    }

    static int zzd(int i, List<zzfh> list, zzfu zzfu) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzdk.zzc(i, list.get(i3), zzfu);
        }
        return i2;
    }

    public static zzgm<?, ?> zzcy() {
        return zzno;
    }

    public static zzgm<?, ?> zzcz() {
        return zznp;
    }

    public static zzgm<?, ?> zzda() {
        return zznq;
    }

    private static zzgm<?, ?> zzh(boolean z) {
        try {
            Class<?> zzdc = zzdc();
            if (zzdc == null) {
                return null;
            }
            return (zzgm) zzdc.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzdb() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzdc() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zzd(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static <T> void zza(zzfa zzfa, T t, T t2, long j) {
        zzgs.zza((Object) t, j, zzfa.zzb(zzgs.zzo(t, j), zzgs.zzo(t2, j)));
    }

    static <T, FT extends zzdu<FT>> void zza(zzdn<FT> zzdn, T t, T t2) {
        zzds<FT> zzd = zzdn.zzd(t2);
        if (!zzd.zzhk.isEmpty()) {
            zzdn.zze((Object) t).zza(zzd);
        }
    }

    static <T, UT, UB> void zza(zzgm<UT, UB> zzgm, T t, T t2) {
        zzgm.zze(t, zzgm.zzf(zzgm.zzp(t), zzgm.zzp(t2)));
    }
}

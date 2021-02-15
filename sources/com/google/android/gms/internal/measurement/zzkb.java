package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zzkb {
    private static final Class<?> zza = zzd();
    private static final zzkr<?, ?> zzb = zza(false);
    private static final zzkr<?, ?> zzc = zza(true);
    private static final zzkr<?, ?> zzd = new zzkt();

    public static void zza(Class<?> cls) {
        Class<?> cls2;
        if (!zzhz.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzlo zzlo, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzlo zzlo, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzlo zzlo, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzlo zzlo, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzlo zzlo, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzlo zzlo, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzlo zzlo, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzlo zzlo, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzlo zzlo, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzlo zzlo, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzlo zzlo, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzlo zzlo, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzlo zzlo, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzlo zzlo, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzlo zzlo) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zza(i, list);
        }
    }

    public static void zzb(int i, List<zzgr> list, zzlo zzlo) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zzb(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzlo zzlo, zzjz zzjz) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zza(i, list, zzjz);
        }
    }

    public static void zzb(int i, List<?> list, zzlo zzlo, zzjz zzjz) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlo.zzb(i, list, zzjz);
        }
    }

    static int zza(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zziv) {
            zziv zziv = (zziv) list;
            i = 0;
            while (i2 < size) {
                i += zzhg.zzd(zziv.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhg.zzd(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zza(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zza(list) + (list.size() * zzhg.zze(i));
    }

    static int zzb(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zziv) {
            zziv zziv = (zziv) list;
            i = 0;
            while (i2 < size) {
                i += zzhg.zze(zziv.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhg.zze(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzb(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzhg.zze(i));
    }

    static int zzc(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zziv) {
            zziv zziv = (zziv) list;
            i = 0;
            while (i2 < size) {
                i += zzhg.zzf(zziv.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhg.zzf(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzc(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzc(list) + (size * zzhg.zze(i));
    }

    static int zzd(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzia) {
            zzia zzia = (zzia) list;
            i = 0;
            while (i2 < size) {
                i += zzhg.zzk(zzia.zzc(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhg.zzk(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzd(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzhg.zze(i));
    }

    static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzia) {
            zzia zzia = (zzia) list;
            i = 0;
            while (i2 < size) {
                i += zzhg.zzf(zzia.zzc(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhg.zzf(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zze(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzhg.zze(i));
    }

    static int zzf(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzia) {
            zzia zzia = (zzia) list;
            i = 0;
            while (i2 < size) {
                i += zzhg.zzg(zzia.zzc(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhg.zzg(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzf(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzf(list) + (size * zzhg.zze(i));
    }

    static int zzg(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzia) {
            zzia zzia = (zzia) list;
            i = 0;
            while (i2 < size) {
                i += zzhg.zzh(zzia.zzc(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhg.zzh(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzg(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzhg.zze(i));
    }

    static int zzh(List<?> list) {
        return list.size() << 2;
    }

    static int zzh(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzhg.zzi(i, 0);
    }

    static int zzi(List<?> list) {
        return list.size() << 3;
    }

    static int zzi(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzhg.zzg(i, 0);
    }

    static int zzj(List<?> list) {
        return list.size();
    }

    static int zzj(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzhg.zzb(i, true);
    }

    static int zza(int i, List<?> list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zze = zzhg.zze(i) * size;
        if (list instanceof zzis) {
            zzis zzis = (zzis) list;
            while (i4 < size) {
                Object zzb2 = zzis.zzb(i4);
                if (zzb2 instanceof zzgr) {
                    i3 = zzhg.zzb((zzgr) zzb2);
                } else {
                    i3 = zzhg.zzb((String) zzb2);
                }
                zze += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzgr) {
                    i2 = zzhg.zzb((zzgr) obj);
                } else {
                    i2 = zzhg.zzb((String) obj);
                }
                zze += i2;
                i4++;
            }
        }
        return zze;
    }

    static int zza(int i, Object obj, zzjz zzjz) {
        if (obj instanceof zziq) {
            return zzhg.zza(i, (zziq) obj);
        }
        return zzhg.zzb(i, (zzjh) obj, zzjz);
    }

    static int zza(int i, List<?> list, zzjz zzjz) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zze = zzhg.zze(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zziq) {
                i2 = zzhg.zza((zziq) obj);
            } else {
                i2 = zzhg.zza((zzjh) obj, zzjz);
            }
            zze += i2;
        }
        return zze;
    }

    static int zzb(int i, List<zzgr> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zze = size * zzhg.zze(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zze += zzhg.zzb(list.get(i2));
        }
        return zze;
    }

    static int zzb(int i, List<zzjh> list, zzjz zzjz) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzhg.zzc(i, list.get(i3), zzjz);
        }
        return i2;
    }

    public static zzkr<?, ?> zza() {
        return zzb;
    }

    public static zzkr<?, ?> zzb() {
        return zzc;
    }

    public static zzkr<?, ?> zzc() {
        return zzd;
    }

    private static zzkr<?, ?> zza(boolean z) {
        try {
            Class<?> zze = zze();
            if (zze == null) {
                return null;
            }
            return (zzkr) zze.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzd() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zze() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static <T> void zza(zzje zzje, T t, T t2, long j) {
        zzkx.zza((Object) t, j, zzje.zza(zzkx.zzf(t, j), zzkx.zzf(t2, j)));
    }

    static <T, FT extends zzhr<FT>> void zza(zzho<FT> zzho, T t, T t2) {
        zzhp<FT> zza2 = zzho.zza((Object) t2);
        if (!zza2.zza.isEmpty()) {
            zzho.zzb(t).zza(zza2);
        }
    }

    static <T, UT, UB> void zza(zzkr<UT, UB> zzkr, T t, T t2) {
        zzkr.zza((Object) t, zzkr.zzc(zzkr.zzb(t), zzkr.zzb(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzid zzid, UB ub, zzkr<UT, UB> zzkr) {
        if (zzid == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzid.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = zza(i, intValue, ub, zzkr);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzid.zza(intValue2)) {
                    ub = zza(i, intValue2, ub, zzkr);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzkr<UT, UB> zzkr) {
        if (ub == null) {
            ub = zzkr.zza();
        }
        zzkr.zza(ub, i, (long) i2);
        return ub;
    }
}

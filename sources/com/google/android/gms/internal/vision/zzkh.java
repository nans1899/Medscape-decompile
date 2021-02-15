package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzkh {
    private static final Class<?> zzabq = zziv();
    private static final zzkx<?, ?> zzabr = zzn(false);
    private static final zzkx<?, ?> zzabs = zzn(true);
    private static final zzkx<?, ?> zzabt = new zzkz();

    public static void zzg(Class<?> cls) {
        Class<?> cls2;
        if (!zzid.class.isAssignableFrom(cls) && (cls2 = zzabq) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzlq zzlq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzlq zzlq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzlq zzlq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzlq zzlq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzlq zzlq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzlq zzlq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzlq zzlq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzlq zzlq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzlq zzlq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzlq zzlq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzlq zzlq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzlq zzlq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzlq zzlq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzlq zzlq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzlq zzlq) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zza(i, list);
        }
    }

    public static void zzb(int i, List<zzgs> list, zzlq zzlq) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zzb(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzlq zzlq, zzkf zzkf) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zza(i, list, zzkf);
        }
    }

    public static void zzb(int i, List<?> list, zzlq zzlq, zzkf zzkf) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzlq.zzb(i, list, zzkf);
        }
    }

    static int zzq(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjb) {
            zzjb zzjb = (zzjb) list;
            i = 0;
            while (i2 < size) {
                i += zzhl.zzv(zzjb.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhl.zzv(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzq(list) + (list.size() * zzhl.zzbh(i));
    }

    static int zzr(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjb) {
            zzjb zzjb = (zzjb) list;
            i = 0;
            while (i2 < size) {
                i += zzhl.zzw(zzjb.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhl.zzw(list.get(i2).longValue());
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
        return zzr(list) + (size * zzhl.zzbh(i));
    }

    static int zzs(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjb) {
            zzjb zzjb = (zzjb) list;
            i = 0;
            while (i2 < size) {
                i += zzhl.zzx(zzjb.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhl.zzx(list.get(i2).longValue());
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
        return zzs(list) + (size * zzhl.zzbh(i));
    }

    static int zzt(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            i = 0;
            while (i2 < size) {
                i += zzhl.zzbn(zzif.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhl.zzbn(list.get(i2).intValue());
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
        return zzt(list) + (size * zzhl.zzbh(i));
    }

    static int zzu(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            i = 0;
            while (i2 < size) {
                i += zzhl.zzbi(zzif.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhl.zzbi(list.get(i2).intValue());
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
        return zzu(list) + (size * zzhl.zzbh(i));
    }

    static int zzv(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            i = 0;
            while (i2 < size) {
                i += zzhl.zzbj(zzif.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhl.zzbj(list.get(i2).intValue());
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
        return zzv(list) + (size * zzhl.zzbh(i));
    }

    static int zzw(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            i = 0;
            while (i2 < size) {
                i += zzhl.zzbk(zzif.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzhl.zzbk(list.get(i2).intValue());
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
        return zzw(list) + (size * zzhl.zzbh(i));
    }

    static int zzx(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzhl.zzq(i, 0);
    }

    static int zzy(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzhl.zzg(i, 0);
    }

    static int zzz(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzhl.zzb(i, true);
    }

    static int zzc(int i, List<?> list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzbh = zzhl.zzbh(i) * size;
        if (list instanceof zziu) {
            zziu zziu = (zziu) list;
            while (i4 < size) {
                Object zzbt = zziu.zzbt(i4);
                if (zzbt instanceof zzgs) {
                    i3 = zzhl.zzb((zzgs) zzbt);
                } else {
                    i3 = zzhl.zzx((String) zzbt);
                }
                zzbh += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzgs) {
                    i2 = zzhl.zzb((zzgs) obj);
                } else {
                    i2 = zzhl.zzx((String) obj);
                }
                zzbh += i2;
                i4++;
            }
        }
        return zzbh;
    }

    static int zzc(int i, Object obj, zzkf zzkf) {
        if (obj instanceof zzis) {
            return zzhl.zza(i, (zzis) obj);
        }
        return zzhl.zzb(i, (zzjn) obj, zzkf);
    }

    static int zzc(int i, List<?> list, zzkf zzkf) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbh = zzhl.zzbh(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzis) {
                i2 = zzhl.zza((zzis) obj);
            } else {
                i2 = zzhl.zza((zzjn) obj, zzkf);
            }
            zzbh += i2;
        }
        return zzbh;
    }

    static int zzd(int i, List<zzgs> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbh = size * zzhl.zzbh(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzbh += zzhl.zzb(list.get(i2));
        }
        return zzbh;
    }

    static int zzd(int i, List<zzjn> list, zzkf zzkf) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzhl.zzc(i, list.get(i3), zzkf);
        }
        return i2;
    }

    public static zzkx<?, ?> zzis() {
        return zzabr;
    }

    public static zzkx<?, ?> zzit() {
        return zzabs;
    }

    public static zzkx<?, ?> zziu() {
        return zzabt;
    }

    private static zzkx<?, ?> zzn(boolean z) {
        try {
            Class<?> zziw = zziw();
            if (zziw == null) {
                return null;
            }
            return (zzkx) zziw.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zziv() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zziw() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zze(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static <T> void zza(zzjg zzjg, T t, T t2, long j) {
        zzld.zza((Object) t, j, zzjg.zzc(zzld.zzp(t, j), zzld.zzp(t2, j)));
    }

    static <T, FT extends zzhv<FT>> void zza(zzhq<FT> zzhq, T t, T t2) {
        zzht<FT> zzh = zzhq.zzh(t2);
        if (!zzh.zzux.isEmpty()) {
            zzhq.zzi(t).zza(zzh);
        }
    }

    static <T, UT, UB> void zza(zzkx<UT, UB> zzkx, T t, T t2) {
        zzkx.zzf(t, zzkx.zzh(zzkx.zzy(t), zzkx.zzy(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzij zzij, UB ub, zzkx<UT, UB> zzkx) {
        if (zzij == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzij.zzg(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = zza(i, intValue, ub, zzkx);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzij.zzg(intValue2)) {
                    ub = zza(i, intValue2, ub, zzkx);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzkx<UT, UB> zzkx) {
        if (ub == null) {
            ub = zzkx.zzjd();
        }
        zzkx.zza(ub, i, (long) i2);
        return ub;
    }
}

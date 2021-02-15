package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzemh {
    private static final Class<?> zzinc = zzbik();
    private static final zzemx<?, ?> zzind = zzbv(false);
    private static final zzemx<?, ?> zzine = zzbv(true);
    private static final zzemx<?, ?> zzinf = new zzemz();

    public static void zzi(Class<?> cls) {
        Class<?> cls2;
        if (!zzejz.class.isAssignableFrom(cls) && (cls2 = zzinc) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int i, List<Double> list, zzenu zzenu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<Float> list, zzenu zzenu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzenu zzenu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzenu zzenu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzenu zzenu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzenu zzenu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzenu zzenu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzenu zzenu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzenu zzenu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzenu zzenu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzenu zzenu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzenu zzenu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzenu zzenu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzenu zzenu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zzi(i, list, z);
        }
    }

    public static void zza(int i, List<String> list, zzenu zzenu) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zza(i, list);
        }
    }

    public static void zzb(int i, List<zzeip> list, zzenu zzenu) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zzb(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzenu zzenu, zzemf zzemf) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zza(i, list, zzemf);
        }
    }

    public static void zzb(int i, List<?> list, zzenu zzenu, zzemf zzemf) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzenu.zzb(i, list, zzemf);
        }
    }

    static int zzab(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzekx) {
            zzekx zzekx = (zzekx) list;
            i = 0;
            while (i2 < size) {
                i += zzejj.zzfl(zzekx.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzejj.zzfl(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzo(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzab(list) + (list.size() * zzejj.zzgq(i));
    }

    static int zzac(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzekx) {
            zzekx zzekx = (zzekx) list;
            i = 0;
            while (i2 < size) {
                i += zzejj.zzfm(zzekx.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzejj.zzfm(list.get(i2).longValue());
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
        return zzac(list) + (size * zzejj.zzgq(i));
    }

    static int zzad(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzekx) {
            zzekx zzekx = (zzekx) list;
            i = 0;
            while (i2 < size) {
                i += zzejj.zzfn(zzekx.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzejj.zzfn(list.get(i2).longValue());
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
        return zzad(list) + (size * zzejj.zzgq(i));
    }

    static int zzae(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzekc) {
            zzekc zzekc = (zzekc) list;
            i = 0;
            while (i2 < size) {
                i += zzejj.zzgw(zzekc.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzejj.zzgw(list.get(i2).intValue());
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
        return zzae(list) + (size * zzejj.zzgq(i));
    }

    static int zzaf(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzekc) {
            zzekc zzekc = (zzekc) list;
            i = 0;
            while (i2 < size) {
                i += zzejj.zzgr(zzekc.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzejj.zzgr(list.get(i2).intValue());
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
        return zzaf(list) + (size * zzejj.zzgq(i));
    }

    static int zzag(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzekc) {
            zzekc zzekc = (zzekc) list;
            i = 0;
            while (i2 < size) {
                i += zzejj.zzgs(zzekc.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzejj.zzgs(list.get(i2).intValue());
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
        return zzag(list) + (size * zzejj.zzgq(i));
    }

    static int zzah(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzekc) {
            zzekc zzekc = (zzekc) list;
            i = 0;
            while (i2 < size) {
                i += zzejj.zzgt(zzekc.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzejj.zzgt(list.get(i2).intValue());
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
        return zzah(list) + (size * zzejj.zzgq(i));
    }

    static int zzai(List<?> list) {
        return list.size() << 2;
    }

    static int zzv(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzejj.zzai(i, 0);
    }

    static int zzaj(List<?> list) {
        return list.size() << 3;
    }

    static int zzw(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzejj.zzn(i, 0);
    }

    static int zzak(List<?> list) {
        return list.size();
    }

    static int zzx(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzejj.zzi(i, true);
    }

    static int zzc(int i, List<?> list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzgq = zzejj.zzgq(i) * size;
        if (list instanceof zzeku) {
            zzeku zzeku = (zzeku) list;
            while (i4 < size) {
                Object zzhe = zzeku.zzhe(i4);
                if (zzhe instanceof zzeip) {
                    i3 = zzejj.zzai((zzeip) zzhe);
                } else {
                    i3 = zzejj.zzhw((String) zzhe);
                }
                zzgq += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzeip) {
                    i2 = zzejj.zzai((zzeip) obj);
                } else {
                    i2 = zzejj.zzhw((String) obj);
                }
                zzgq += i2;
                i4++;
            }
        }
        return zzgq;
    }

    static int zzc(int i, Object obj, zzemf zzemf) {
        if (obj instanceof zzeks) {
            return zzejj.zza(i, (zzeks) obj);
        }
        return zzejj.zzb(i, (zzelj) obj, zzemf);
    }

    static int zzc(int i, List<?> list, zzemf zzemf) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzgq = zzejj.zzgq(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzeks) {
                i2 = zzejj.zza((zzeks) obj);
            } else {
                i2 = zzejj.zza((zzelj) obj, zzemf);
            }
            zzgq += i2;
        }
        return zzgq;
    }

    static int zzd(int i, List<zzeip> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzgq = size * zzejj.zzgq(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzgq += zzejj.zzai(list.get(i2));
        }
        return zzgq;
    }

    static int zzd(int i, List<zzelj> list, zzemf zzemf) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzejj.zzc(i, list.get(i3), zzemf);
        }
        return i2;
    }

    public static zzemx<?, ?> zzbih() {
        return zzind;
    }

    public static zzemx<?, ?> zzbii() {
        return zzine;
    }

    public static zzemx<?, ?> zzbij() {
        return zzinf;
    }

    private static zzemx<?, ?> zzbv(boolean z) {
        try {
            Class<?> zzbil = zzbil();
            if (zzbil == null) {
                return null;
            }
            return (zzemx) zzbil.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzbik() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzbil() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zzh(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static <T> void zza(zzelg zzelg, T t, T t2, long j) {
        zzend.zza((Object) t, j, zzelg.zzf(zzend.zzp(t, j), zzend.zzp(t2, j)));
    }

    static <T, FT extends zzeju<FT>> void zza(zzejo<FT> zzejo, T t, T t2) {
        zzejs<FT> zzah = zzejo.zzah(t2);
        if (!zzah.zzigh.isEmpty()) {
            zzejo.zzai(t).zza(zzah);
        }
    }

    static <T, UT, UB> void zza(zzemx<UT, UB> zzemx, T t, T t2) {
        zzemx.zzi(t, zzemx.zzk(zzemx.zzax(t), zzemx.zzax(t2)));
    }

    static <UT, UB> UB zza(int i, List<Integer> list, zzekg zzekg, UB ub, zzemx<UT, UB> zzemx) {
        if (zzekg == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzekg.zzi(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = zza(i, intValue, ub, zzemx);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzekg.zzi(intValue2)) {
                    ub = zza(i, intValue2, ub, zzemx);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB zza(int i, int i2, UB ub, zzemx<UT, UB> zzemx) {
        if (ub == null) {
            ub = zzemx.zzbiu();
        }
        zzemx.zza(ub, i, (long) i2);
        return ub;
    }
}

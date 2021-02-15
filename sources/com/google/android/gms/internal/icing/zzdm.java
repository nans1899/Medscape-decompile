package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdx;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzdm implements zzhg {
    private final zzdk zzgo;

    public static zzdm zza(zzdk zzdk) {
        if (zzdk.zzgy != null) {
            return zzdk.zzgy;
        }
        return new zzdm(zzdk);
    }

    private zzdm(zzdk zzdk) {
        zzdk zzdk2 = (zzdk) zzeb.zza(zzdk, "output");
        this.zzgo = zzdk2;
        zzdk2.zzgy = this;
    }

    public final int zzay() {
        return zzdx.zze.zzkx;
    }

    public final void zzm(int i, int i2) throws IOException {
        this.zzgo.zzf(i, i2);
    }

    public final void zzi(int i, long j) throws IOException {
        this.zzgo.zza(i, j);
    }

    public final void zzj(int i, long j) throws IOException {
        this.zzgo.zzc(i, j);
    }

    public final void zza(int i, float f) throws IOException {
        this.zzgo.zza(i, f);
    }

    public final void zza(int i, double d) throws IOException {
        this.zzgo.zza(i, d);
    }

    public final void zzn(int i, int i2) throws IOException {
        this.zzgo.zzc(i, i2);
    }

    public final void zza(int i, long j) throws IOException {
        this.zzgo.zza(i, j);
    }

    public final void zzc(int i, int i2) throws IOException {
        this.zzgo.zzc(i, i2);
    }

    public final void zzc(int i, long j) throws IOException {
        this.zzgo.zzc(i, j);
    }

    public final void zzf(int i, int i2) throws IOException {
        this.zzgo.zzf(i, i2);
    }

    public final void zza(int i, boolean z) throws IOException {
        this.zzgo.zza(i, z);
    }

    public final void zza(int i, String str) throws IOException {
        this.zzgo.zza(i, str);
    }

    public final void zza(int i, zzct zzct) throws IOException {
        this.zzgo.zza(i, zzct);
    }

    public final void zzd(int i, int i2) throws IOException {
        this.zzgo.zzd(i, i2);
    }

    public final void zze(int i, int i2) throws IOException {
        this.zzgo.zze(i, i2);
    }

    public final void zzb(int i, long j) throws IOException {
        this.zzgo.zzb(i, j);
    }

    public final void zza(int i, Object obj, zzfu zzfu) throws IOException {
        this.zzgo.zza(i, (zzfh) obj, zzfu);
    }

    public final void zzb(int i, Object obj, zzfu zzfu) throws IOException {
        zzdk zzdk = this.zzgo;
        zzdk.zzb(i, 3);
        zzfu.zza((zzfh) obj, zzdk.zzgy);
        zzdk.zzb(i, 4);
    }

    public final void zzab(int i) throws IOException {
        this.zzgo.zzb(i, 3);
    }

    public final void zzac(int i) throws IOException {
        this.zzgo.zzb(i, 4);
    }

    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzct) {
            this.zzgo.zzb(i, (zzct) obj);
        } else {
            this.zzgo.zza(i, (zzfh) obj);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgo.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdk.zzt(list.get(i4).intValue());
            }
            this.zzgo.zzp(i3);
            while (i2 < list.size()) {
                this.zzgo.zzo(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zzc(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgo.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdk.zzw(list.get(i4).intValue());
            }
            this.zzgo.zzp(i3);
            while (i2 < list.size()) {
                this.zzgo.zzr(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgo.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdk.zze(list.get(i4).longValue());
            }
            this.zzgo.zzp(i3);
            while (i2 < list.size()) {
                this.zzgo.zzb(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgo.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdk.zzf(list.get(i4).longValue());
            }
            this.zzgo.zzp(i3);
            while (i2 < list.size()) {
                this.zzgo.zzb(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgo.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdk.zzh(list.get(i4).longValue());
            }
            this.zzgo.zzp(i3);
            while (i2 < list.size()) {
                this.zzgo.zzd(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgo.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdk.zzb(list.get(i4).floatValue());
            }
            this.zzgo.zzp(i3);
            while (i2 < list.size()) {
                this.zzgo.zza(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgo.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdk.zzb(list.get(i4).doubleValue());
            }
            this.zzgo.zzp(i3);
            while (i2 < list.size()) {
                this.zzgo.zza(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zza(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgo.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdk.zzy(list.get(i4).intValue());
            }
            this.zzgo.zzp(i3);
            while (i2 < list.size()) {
                this.zzgo.zzo(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zzc(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgo.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdk.zzf(list.get(i4).booleanValue());
            }
            this.zzgo.zzp(i3);
            while (i2 < list.size()) {
                this.zzgo.zze(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zza(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzeo) {
            zzeo zzeo = (zzeo) list;
            while (i2 < list.size()) {
                Object zzad = zzeo.zzad(i2);
                if (zzad instanceof String) {
                    this.zzgo.zza(i, (String) zzad);
                } else {
                    this.zzgo.zza(i, (zzct) zzad);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zza(i, list.get(i2));
            i2++;
        }
    }

    public final void zzb(int i, List<zzct> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzgo.zza(i, list.get(i2));
        }
    }

    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgo.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdk.zzu(list.get(i4).intValue());
            }
            this.zzgo.zzp(i3);
            while (i2 < list.size()) {
                this.zzgo.zzp(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zzd(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgo.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdk.zzx(list.get(i4).intValue());
            }
            this.zzgo.zzp(i3);
            while (i2 < list.size()) {
                this.zzgo.zzr(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgo.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdk.zzi(list.get(i4).longValue());
            }
            this.zzgo.zzp(i3);
            while (i2 < list.size()) {
                this.zzgo.zzd(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgo.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdk.zzv(list.get(i4).intValue());
            }
            this.zzgo.zzp(i3);
            while (i2 < list.size()) {
                this.zzgo.zzq(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zze(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzgo.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzdk.zzg(list.get(i4).longValue());
            }
            this.zzgo.zzp(i3);
            while (i2 < list.size()) {
                this.zzgo.zzc(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzgo.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzfu zzfu) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, (Object) list.get(i2), zzfu);
        }
    }

    public final void zzb(int i, List<?> list, zzfu zzfu) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, (Object) list.get(i2), zzfu);
        }
    }

    public final <K, V> void zza(int i, zzey<K, V> zzey, Map<K, V> map) throws IOException {
        for (Map.Entry next : map.entrySet()) {
            this.zzgo.zzb(i, 2);
            this.zzgo.zzp(zzds.zza(zzey.zzmi, 1, next.getKey()) + zzds.zza(zzey.zzmj, 2, next.getValue()));
            zzdk zzdk = this.zzgo;
            Object key = next.getKey();
            Object value = next.getValue();
            zzds.zza(zzdk, zzey.zzmi, 1, key);
            zzds.zza(zzdk, zzey.zzmj, 2, value);
        }
    }
}

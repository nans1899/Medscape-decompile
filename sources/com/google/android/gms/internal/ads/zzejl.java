package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzejl implements zzenu {
    private final zzejj zziew;

    public static zzejl zza(zzejj zzejj) {
        if (zzejj.zzifv != null) {
            return zzejj.zzifv;
        }
        return new zzejl(zzejj);
    }

    private zzejl(zzejj zzejj) {
        zzejj zzejj2 = (zzejj) zzekb.zza(zzejj, "output");
        this.zziew = zzejj2;
        zzejj2.zzifv = this;
    }

    public final int zzbfs() {
        return zzejz.zze.zzijv;
    }

    public final void zzal(int i, int i2) throws IOException {
        this.zziew.zzae(i, i2);
    }

    public final void zzp(int i, long j) throws IOException {
        this.zziew.zzh(i, j);
    }

    public final void zzq(int i, long j) throws IOException {
        this.zziew.zzj(i, j);
    }

    public final void zza(int i, float f) throws IOException {
        this.zziew.zza(i, f);
    }

    public final void zzb(int i, double d) throws IOException {
        this.zziew.zzb(i, d);
    }

    public final void zzam(int i, int i2) throws IOException {
        this.zziew.zzab(i, i2);
    }

    public final void zzh(int i, long j) throws IOException {
        this.zziew.zzh(i, j);
    }

    public final void zzab(int i, int i2) throws IOException {
        this.zziew.zzab(i, i2);
    }

    public final void zzj(int i, long j) throws IOException {
        this.zziew.zzj(i, j);
    }

    public final void zzae(int i, int i2) throws IOException {
        this.zziew.zzae(i, i2);
    }

    public final void zzh(int i, boolean z) throws IOException {
        this.zziew.zzh(i, z);
    }

    public final void zzi(int i, String str) throws IOException {
        this.zziew.zzi(i, str);
    }

    public final void zza(int i, zzeip zzeip) throws IOException {
        this.zziew.zza(i, zzeip);
    }

    public final void zzac(int i, int i2) throws IOException {
        this.zziew.zzac(i, i2);
    }

    public final void zzad(int i, int i2) throws IOException {
        this.zziew.zzad(i, i2);
    }

    public final void zzi(int i, long j) throws IOException {
        this.zziew.zzi(i, j);
    }

    public final void zza(int i, Object obj, zzemf zzemf) throws IOException {
        this.zziew.zza(i, (zzelj) obj, zzemf);
    }

    public final void zzb(int i, Object obj, zzemf zzemf) throws IOException {
        zzejj zzejj = this.zziew;
        zzejj.writeTag(i, 3);
        zzemf.zza((zzelj) obj, zzejj.zzifv);
        zzejj.writeTag(i, 4);
    }

    public final void zzgz(int i) throws IOException {
        this.zziew.writeTag(i, 3);
    }

    public final void zzha(int i) throws IOException {
        this.zziew.writeTag(i, 4);
    }

    public final void zzc(int i, Object obj) throws IOException {
        if (obj instanceof zzeip) {
            this.zziew.zzb(i, (zzeip) obj);
        } else {
            this.zziew.zza(i, (zzelj) obj);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zziew.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzejj.zzgr(list.get(i4).intValue());
            }
            this.zziew.zzgn(i3);
            while (i2 < list.size()) {
                this.zziew.zzgm(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zzab(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zziew.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzejj.zzgu(list.get(i4).intValue());
            }
            this.zziew.zzgn(i3);
            while (i2 < list.size()) {
                this.zziew.zzgp(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zzae(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zziew.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzejj.zzfl(list.get(i4).longValue());
            }
            this.zziew.zzgn(i3);
            while (i2 < list.size()) {
                this.zziew.zzfi(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zzh(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zziew.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzejj.zzfm(list.get(i4).longValue());
            }
            this.zziew.zzgn(i3);
            while (i2 < list.size()) {
                this.zziew.zzfi(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zzh(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zziew.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzejj.zzfo(list.get(i4).longValue());
            }
            this.zziew.zzgn(i3);
            while (i2 < list.size()) {
                this.zziew.zzfk(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zzj(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zziew.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzejj.zzg(list.get(i4).floatValue());
            }
            this.zziew.zzgn(i3);
            while (i2 < list.size()) {
                this.zziew.zzf(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zziew.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzejj.zzd(list.get(i4).doubleValue());
            }
            this.zziew.zzgn(i3);
            while (i2 < list.size()) {
                this.zziew.zzc(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zzb(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zziew.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzejj.zzgw(list.get(i4).intValue());
            }
            this.zziew.zzgn(i3);
            while (i2 < list.size()) {
                this.zziew.zzgm(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zzab(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zziew.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzejj.zzbt(list.get(i4).booleanValue());
            }
            this.zziew.zzgn(i3);
            while (i2 < list.size()) {
                this.zziew.zzbs(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zzh(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzeku) {
            zzeku zzeku = (zzeku) list;
            while (i2 < list.size()) {
                Object zzhe = zzeku.zzhe(i2);
                if (zzhe instanceof String) {
                    this.zziew.zzi(i, (String) zzhe);
                } else {
                    this.zziew.zza(i, (zzeip) zzhe);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zzi(i, list.get(i2));
            i2++;
        }
    }

    public final void zzb(int i, List<zzeip> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zziew.zza(i, list.get(i2));
        }
    }

    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zziew.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzejj.zzgs(list.get(i4).intValue());
            }
            this.zziew.zzgn(i3);
            while (i2 < list.size()) {
                this.zziew.zzgn(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zzac(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zziew.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzejj.zzgv(list.get(i4).intValue());
            }
            this.zziew.zzgn(i3);
            while (i2 < list.size()) {
                this.zziew.zzgp(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zzae(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zziew.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzejj.zzfp(list.get(i4).longValue());
            }
            this.zziew.zzgn(i3);
            while (i2 < list.size()) {
                this.zziew.zzfk(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zzj(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zziew.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzejj.zzgt(list.get(i4).intValue());
            }
            this.zziew.zzgn(i3);
            while (i2 < list.size()) {
                this.zziew.zzgo(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zzad(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zziew.writeTag(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzejj.zzfn(list.get(i4).longValue());
            }
            this.zziew.zzgn(i3);
            while (i2 < list.size()) {
                this.zziew.zzfj(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zziew.zzi(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzemf zzemf) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, (Object) list.get(i2), zzemf);
        }
    }

    public final void zzb(int i, List<?> list, zzemf zzemf) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, (Object) list.get(i2), zzemf);
        }
    }

    public final <K, V> void zza(int i, zzele<K, V> zzele, Map<K, V> map) throws IOException {
        for (Map.Entry next : map.entrySet()) {
            this.zziew.writeTag(i, 2);
            this.zziew.zzgn(zzelb.zza(zzele, next.getKey(), next.getValue()));
            zzelb.zza(this.zziew, zzele, next.getKey(), next.getValue());
        }
    }
}

package com.google.android.gms.internal.ads;

import androidx.collection.SimpleArrayMap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcer {
    zzaff zzgai;
    zzafe zzgaj;
    zzaft zzgak;
    zzafs zzgal;
    zzajk zzgam;
    final SimpleArrayMap<String, zzafl> zzgan = new SimpleArrayMap<>();
    final SimpleArrayMap<String, zzafk> zzgao = new SimpleArrayMap<>();

    public final zzcer zzb(zzaff zzaff) {
        this.zzgai = zzaff;
        return this;
    }

    public final zzcer zzb(zzafe zzafe) {
        this.zzgaj = zzafe;
        return this;
    }

    public final zzcer zzb(zzaft zzaft) {
        this.zzgak = zzaft;
        return this;
    }

    public final zzcer zza(zzafs zzafs) {
        this.zzgal = zzafs;
        return this;
    }

    public final zzcer zzb(zzajk zzajk) {
        this.zzgam = zzajk;
        return this;
    }

    public final zzcer zzb(String str, zzafl zzafl, zzafk zzafk) {
        this.zzgan.put(str, zzafl);
        this.zzgao.put(str, zzafk);
        return this;
    }

    public final zzcep zzamw() {
        return new zzcep(this);
    }
}

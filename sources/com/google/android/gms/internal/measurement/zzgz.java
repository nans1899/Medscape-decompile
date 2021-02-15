package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zzgz {
    private final zzhg zza;
    private final byte[] zzb;

    private zzgz(int i) {
        byte[] bArr = new byte[i];
        this.zzb = bArr;
        this.zza = zzhg.zza(bArr);
    }

    public final zzgr zza() {
        this.zza.zzb();
        return new zzhb(this.zzb);
    }

    public final zzhg zzb() {
        return this.zza;
    }

    /* synthetic */ zzgz(int i, zzgq zzgq) {
        this(i);
    }
}

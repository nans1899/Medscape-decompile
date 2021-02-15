package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zzgq extends zzgs {
    private int zza = 0;
    private final int zzb = this.zzc.zza();
    private final /* synthetic */ zzgr zzc;

    zzgq(zzgr zzgr) {
        this.zzc = zzgr;
    }

    public final boolean hasNext() {
        return this.zza < this.zzb;
    }

    public final byte zza() {
        int i = this.zza;
        if (i < this.zzb) {
            this.zza = i + 1;
            return this.zzc.zzb(i);
        }
        throw new NoSuchElementException();
    }
}

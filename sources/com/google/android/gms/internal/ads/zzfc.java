package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzcf;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzfc implements Runnable {
    private final /* synthetic */ zzex zzzk;
    private final /* synthetic */ int zzzn;
    private final /* synthetic */ boolean zzzo;

    zzfc(zzex zzex, int i, boolean z) {
        this.zzzk = zzex;
        this.zzzn = i;
        this.zzzo = z;
    }

    public final void run() {
        zzcf.zza zzb = this.zzzk.zzb(this.zzzn, this.zzzo);
        zzcf.zza unused = this.zzzk.zzzb = zzb;
        if (zzex.zza(this.zzzn, zzb)) {
            this.zzzk.zza(this.zzzn + 1, this.zzzo);
        }
    }
}

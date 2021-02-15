package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzayd extends zzayl {
    private final /* synthetic */ zzayb zzdzk;

    zzayd(zzayb zzayb) {
        this.zzdzk = zzayb;
    }

    public final void zzvb() {
        zzabf zzabf = new zzabf(this.zzdzk.zzvr, this.zzdzk.zzbpe.zzbre);
        synchronized (this.zzdzk.lock) {
            try {
                zzp.zzla();
                zzabk.zza(this.zzdzk.zzdzc, zzabf);
            } catch (IllegalArgumentException e) {
                zzayp.zzd("Cannot config CSI reporter.", e);
            }
        }
    }
}

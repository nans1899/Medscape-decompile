package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdxx implements Runnable {
    private final /* synthetic */ zzdxu zzhsm;
    private final /* synthetic */ zzdzc zzhsn;
    private final /* synthetic */ int zzhso;

    zzdxx(zzdxu zzdxu, zzdzc zzdzc, int i) {
        this.zzhsm = zzdxu;
        this.zzhsn = zzdzc;
        this.zzhso = i;
    }

    public final void run() {
        try {
            if (this.zzhsn.isCancelled()) {
                zzdwk unused = this.zzhsm.zzhsi = null;
                this.zzhsm.cancel(false);
            } else {
                this.zzhsm.zza(this.zzhso, this.zzhsn);
            }
        } finally {
            this.zzhsm.zza((zzdwk) null);
        }
    }
}

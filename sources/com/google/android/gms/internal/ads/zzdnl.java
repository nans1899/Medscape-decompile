package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdnl implements zzdag<zzcjg> {
    private final /* synthetic */ zzdnj zzhcw;

    zzdnl(zzdnj zzdnj) {
        this.zzhcw = zzdnj;
    }

    public final void zzaqv() {
        synchronized (this.zzhcw) {
            zzcjg unused = this.zzhcw.zzhcz = null;
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzcjg zzcjg = (zzcjg) obj;
        synchronized (this.zzhcw) {
            zzcjg unused = this.zzhcw.zzhcz = zzcjg;
            this.zzhcw.zzhcz.zzaih();
        }
    }
}

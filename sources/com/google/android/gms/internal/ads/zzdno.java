package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdno implements zzdag<zzcjg> {
    private final /* synthetic */ zzdnp zzhda;

    zzdno(zzdnp zzdnp) {
        this.zzhda = zzdnp;
    }

    public final void zzaqv() {
        synchronized (this.zzhda) {
            zzcjg unused = this.zzhda.zzhcz = null;
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzcjg zzcjg = (zzcjg) obj;
        synchronized (this.zzhda) {
            zzcjg unused = this.zzhda.zzhcz = zzcjg;
            this.zzhda.zzhcz.zzaih();
        }
    }
}

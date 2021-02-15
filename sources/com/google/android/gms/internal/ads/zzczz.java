package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzczz implements zzdag<zzbqo> {
    private final /* synthetic */ zzdaa zzgto;

    zzczz(zzdaa zzdaa) {
        this.zzgto = zzdaa;
    }

    public final void zzaqv() {
        synchronized (this.zzgto) {
            boolean unused = this.zzgto.zzafc = false;
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzbqo zzbqo = (zzbqo) obj;
        synchronized (this.zzgto) {
            boolean unused = this.zzgto.zzafc = false;
            zzyn unused2 = this.zzgto.zzadt = zzbqo.zzaix();
            zzbqo.zzaih();
        }
    }
}

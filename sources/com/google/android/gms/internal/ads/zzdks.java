package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdks implements zzdag<zzboh> {
    private final /* synthetic */ zzdko zzhbb;

    zzdks(zzdko zzdko) {
        this.zzhbb = zzdko;
    }

    public final void zzaqv() {
        synchronized (this.zzhbb) {
            this.zzhbb.zzhaz = null;
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzboh zzboh = (zzboh) obj;
        synchronized (this.zzhbb) {
            if (this.zzhbb.zzhaz != null) {
                this.zzhbb.zzhaz.destroy();
            }
            this.zzhbb.zzhaz = zzboh;
            this.zzhbb.zza(zzboh);
            this.zzhbb.zzgzu.zzb((zzsg) new zzbok(zzboh, this.zzhbb, this.zzhbb.zzgzu));
            zzboh.zzaih();
        }
    }
}

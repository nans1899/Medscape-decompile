package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdqt implements zzdyr<zzdqk<AdT>> {
    private final /* synthetic */ zzdqs zzhjg;

    zzdqt(zzdqs zzdqs) {
        this.zzhjg = zzdqs;
    }

    public final void zzb(Throwable th) {
        synchronized (this.zzhjg) {
            this.zzhjg.zzhjd.zzb(th);
            this.zzhjg.zzhja.setException(th);
        }
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzdqk zzdqk = (zzdqk) obj;
        synchronized (this.zzhjg) {
            this.zzhjg.zzhjd.zza(zzdqk);
            if (this.zzhjg.zzhjc != zzdqi.zzhin) {
                this.zzhjg.zzhbu.zza(this.zzhjg.zzhiz.zzasx(), zzdqk);
            }
            if (this.zzhjg.zzhjc == zzdqi.zzhim) {
                this.zzhjg.zzd(this.zzhjg.zzhiz);
            }
            int unused = this.zzhjg.zzhjc = zzdqi.zzhim;
            this.zzhjg.zzhja.set(zzdqk);
        }
    }
}

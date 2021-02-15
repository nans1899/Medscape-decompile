package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbqo;
import com.google.android.gms.internal.ads.zzbtn;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdku<R extends zzbtn<AdT>, AdT extends zzbqo> implements zzdlv<R, zzdqk<AdT>> {
    private final Executor executor = zzdze.zzayh();
    private R zzhbc;

    public final zzdzc<zzdqk<AdT>> zza(zzdlw zzdlw, zzdlx<R> zzdlx) {
        zzdzc<zzdog> zzdzc;
        zzbtq<R> zzc = zzdlx.zzc(zzdlw.zzhcf);
        zzc.zza(new zzdmd(true));
        R r = (zzbtn) zzc.zzaey();
        this.zzhbc = r;
        zzbrl zzaev = r.zzaev();
        zzdqk zzdqk = new zzdqk();
        if (zzdlw.zzhce != null) {
            zzdzc = zzaev.zza(zzdlw.zzhce);
        } else {
            zzdzc = zzaev.zzajg();
        }
        return zzdyl.zzg(zzdzc).zzb(new zzdkx(this, zzdqk, zzaev), this.executor).zza(new zzdkw(zzdqk), this.executor);
    }

    public final /* synthetic */ Object zzasv() {
        return this.zzhbc;
    }
}
